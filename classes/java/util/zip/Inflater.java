/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

// borrowed from JAZZLIB
public class Inflater
{

  private static final int Deflater_DEFLATED = 8;
  private final static int DeflaterConstants_STORED_BLOCK = 0;
  private final static int DeflaterConstants_STATIC_TREES = 1;
  private final static int DeflaterConstants_DYN_TREES    = 2;

  /* Copy lengths for literal codes 257..285 */
  private static final int CPLENS[] = 
  { 
    3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 23, 27, 31,
    35, 43, 51, 59, 67, 83, 99, 115, 131, 163, 195, 227, 258
  };
  
  /* Extra bits for literal codes 257..285 */  
  private static final int CPLEXT[] = 
  { 
    0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2,
    3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0
  };

  /* Copy offsets for distance codes 0..29 */
  private static final int CPDIST[] = {
    1, 2, 3, 4, 5, 7, 9, 13, 17, 25, 33, 49, 65, 97, 129, 193,
    257, 385, 513, 769, 1025, 1537, 2049, 3073, 4097, 6145,
    8193, 12289, 16385, 24577
  };
  
  /* Extra bits for distance codes */
  private static final int CPDEXT[] = {
    0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6,
    7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 
    12, 12, 13, 13
  };

  /* This are the state in which the inflater can be.  */
  private static final int DECODE_HEADER           = 0;
  private static final int DECODE_DICT             = 1;
  private static final int DECODE_BLOCKS           = 2;
  private static final int DECODE_STORED_LEN1      = 3;
  private static final int DECODE_STORED_LEN2      = 4;
  private static final int DECODE_STORED           = 5;
  private static final int DECODE_DYN_HEADER       = 6;
  private static final int DECODE_HUFFMAN          = 7;
  private static final int DECODE_HUFFMAN_LENBITS  = 8;
  private static final int DECODE_HUFFMAN_DIST     = 9;
  private static final int DECODE_HUFFMAN_DISTBITS = 10;
  private static final int DECODE_CHKSUM           = 11;
  private static final int FINISHED                = 12;

  /** This variable contains the current state. */
  private int mode;

  /**
   * The adler checksum of the dictionary or of the decompressed
   * stream, as it is written in the header resp. footer of the
   * compressed stream.  <br>
   *
   * Only valid if mode is DECODE_DICT or DECODE_CHKSUM.
   */
  private int readAdler;
  /** 
   * The number of bits needed to complete the current state.  This
   * is valid, if mode is DECODE_DICT, DECODE_CHKSUM,
   * DECODE_HUFFMAN_LENBITS or DECODE_HUFFMAN_DISTBITS.  
   */
  private int neededBits;
  private int repLength, repDist;
  private int uncomprLen;
  /**
   * True, if the last block flag was set in the last block of the
   * inflated stream.  This means that the stream ends after the
   * current block.  
   */
  private boolean isLastBlock;

  /**
   * The total number of inflated bytes.
   */
  private int totalOut;
  /**
   * The total number of bytes set with setInput().  This is not the
   * value returned by getTotalIn(), since this also includes the 
   * unprocessed input.
   */
  private int totalIn;
  /**
   * This variable stores the nowrap flag that was given to the constructor.
   * True means, that the inflated stream doesn't contain a header nor the
   * checksum in the footer.
   */
  private boolean nowrap;

  private Input input;
  private Output outputWindow;
  private _InflaterDynHeader dynHeader;
  private _InflaterHuffmanTree litlenTree, distTree;
  private Adler32 adler;

  /**
   * Creates a new inflater.
   */
  public Inflater() 
  {
    this(false);
  }
  
  /**
   * Creates a new inflater.
   * @param nowrap true if no header and checksum field appears in the
   * stream.  This is used for GZIPed input.  For compatibility with
   * Sun JDK you should provide one byte of input more than needed in
   * this case.
   */
  public Inflater(boolean nowrap) 
  {
    this.nowrap = nowrap;
    this.adler = new Adler32();
    input = new Input();
    outputWindow = new Output();
    mode = nowrap ? DECODE_BLOCKS : DECODE_HEADER;
  }

  /**
   * Resets the inflater so that a new stream can be decompressed.  All
   * pending input and output will be discarded.
   */
  public void reset()
  {
    mode = nowrap ? DECODE_BLOCKS : DECODE_HEADER;
    totalIn = totalOut = 0;
    input.reset();
    outputWindow.reset();
    dynHeader = null;
    litlenTree = null;
    distTree = null;
    isLastBlock = false;
    adler.reset();
  }

  /**
   * Decodes the deflate header.
   * @return false if more input is needed. 
   * @exception DataFormatException, if header is invalid.
   */
  private boolean decodeHeader() throws DataFormatException
  {
    int header = input.peekBits(16);
    if (header < 0)
      return false;
    input.dropBits(16);
    
    /* The header is written in "wrong" byte order */
    header = ((header << 8) | (header >> 8)) & 0xffff;
    if (header % 31 != 0)
      throw new DataFormatException("Header checksum illegal");
    
    if ((header & 0x0f00) != (Deflater_DEFLATED << 8))
      throw new DataFormatException("Compression Method unknown");

    /* Maximum size of the backwards window in bits. 
     * We currently ignore this, but we could use it to make the
     * inflater window more space efficient. On the other hand the
     * full window (15 bits) is needed most times, anyway.
     int max_wbits = ((header & 0x7000) >> 12) + 8;
     */
    
    if ((header & 0x0020) == 0) // Dictionary flag?
      {
	mode = DECODE_BLOCKS;
      }
    else
      {
	mode = DECODE_DICT;
	neededBits = 32;      
      }
    return true;
  }
   
  /**
   * Decodes the dictionary checksum after the deflate header.
   * @return false if more input is needed. 
   */
  private boolean decodeDict()
  {
    while (neededBits > 0)
      {
	int dictByte = input.peekBits(8);
	if (dictByte < 0)
	  return false;
	input.dropBits(8);
	readAdler = (readAdler << 8) | dictByte;
	neededBits -= 8;
      }
    return false;
  }

  /**
   * Decodes the huffman encoded symbols in the input stream.
   * @return false if more input is needed, true if output window is
   * full or the current block ends.
   * @exception DataFormatException, if deflated stream is invalid.  
   */
  private boolean decodeHuffman() throws DataFormatException
  {
    int free = outputWindow.getFreeSpace();
    while (free >= 258)
      {
	int symbol;
	switch (mode)
	  {
	  case DECODE_HUFFMAN:
	    /* This is the inner loop so it is optimized a bit */
	    while (((symbol = litlenTree.getSymbol(input)) & ~0xff) == 0)
	      {
		outputWindow.write(symbol);
		if (--free < 258)
		  return true;
	      } 
	    if (symbol < 257)
	      {
		if (symbol < 0)
		  return false;
		else
		  {
		    /* symbol == 256: end of block */
		    distTree = null;
		    litlenTree = null;
		    mode = DECODE_BLOCKS;
		    return true;
		  }
	      }
		
	    try
	      {
		repLength = CPLENS[symbol - 257];
		neededBits = CPLEXT[symbol - 257];
	      }
	    catch (ArrayIndexOutOfBoundsException ex)
	      {
		throw new DataFormatException("Illegal rep length code");
	      }
	    /* fall through */
	  case DECODE_HUFFMAN_LENBITS:
	    if (neededBits > 0)
	      {
		mode = DECODE_HUFFMAN_LENBITS;
		int i = input.peekBits(neededBits);
		if (i < 0)
		  return false;
		input.dropBits(neededBits);
		repLength += i;
	      }
	    mode = DECODE_HUFFMAN_DIST;
	    /* fall through */
	  case DECODE_HUFFMAN_DIST:
	    symbol = distTree.getSymbol(input);
	    if (symbol < 0)
	      return false;
	    try 
	      {
		repDist = CPDIST[symbol];
		neededBits = CPDEXT[symbol];
	      }
	    catch (ArrayIndexOutOfBoundsException ex)
	      {
		throw new DataFormatException("Illegal rep dist code");
	      }
	    /* fall through */
	  case DECODE_HUFFMAN_DISTBITS:
	    if (neededBits > 0)
	      {
		mode = DECODE_HUFFMAN_DISTBITS;
		int i = input.peekBits(neededBits);
		if (i < 0)
		  return false;
		input.dropBits(neededBits);
		repDist += i;
	      }
	    outputWindow.repeat(repLength, repDist);
	    free -= repLength;
	    mode = DECODE_HUFFMAN;
	    break;
	  default:
	    throw new IllegalStateException();
	  }
      }
    return true;
  }

  /**
   * Decodes the adler checksum after the deflate stream.
   * @return false if more input is needed. 
   * @exception DataFormatException, if checksum doesn't match.
   */
  private boolean decodeChksum() throws DataFormatException
  {
    while (neededBits > 0)
      {
	int chkByte = input.peekBits(8);
	if (chkByte < 0)
	  return false;
	input.dropBits(8);
	readAdler = (readAdler << 8) | chkByte;
	neededBits -= 8;
      }
    if ((int) adler.getValue() != readAdler)
      throw new DataFormatException("Adler chksum doesn't match: "
				    +Integer.toHexString((int)adler.getValue())
				    +" vs. "+Integer.toHexString(readAdler));
    mode = FINISHED;
    return false;
  }

  /**
   * Decodes the deflated stream.
   * @return false if more input is needed, or if finished. 
   * @exception DataFormatException, if deflated stream is invalid.
   */
  private boolean decode() throws DataFormatException
  {
    switch (mode) 
      {
      case DECODE_HEADER:
	return decodeHeader();
      case DECODE_DICT:
	return decodeDict();
      case DECODE_CHKSUM:
	return decodeChksum();

      case DECODE_BLOCKS:
	if (isLastBlock)
	  {
	    if (nowrap)
	      {
		mode = FINISHED;
		return false;
	      }
	    else
	      {
		input.skipToByteBoundary();
		neededBits = 32;
		mode = DECODE_CHKSUM;
		return true;
	      }
	  }

	int type = input.peekBits(3);
	if (type < 0)
	  return false;
	input.dropBits(3);

	if ((type & 1) != 0)
	  isLastBlock = true;
	switch (type >> 1)
	  {
	  case DeflaterConstants_STORED_BLOCK:
	    input.skipToByteBoundary();
	    mode = DECODE_STORED_LEN1;
	    break;
	  case DeflaterConstants_STATIC_TREES:
	    litlenTree = _InflaterHuffmanTree.defLitLenTree;
	    distTree = _InflaterHuffmanTree.defDistTree;
	    mode = DECODE_HUFFMAN;
	    break;
	  case DeflaterConstants_DYN_TREES:
	    dynHeader = new _InflaterDynHeader();
	    mode = DECODE_DYN_HEADER;
	    break;
	  default:
	    throw new DataFormatException("Unknown block type "+type);
	  }
	return true;

      case DECODE_STORED_LEN1:
	{
	  if ((uncomprLen = input.peekBits(16)) < 0)
	    return false;
	  input.dropBits(16);
	  mode = DECODE_STORED_LEN2;
	}
	/* fall through */
      case DECODE_STORED_LEN2:
	{
	  int nlen = input.peekBits(16);
	  if (nlen < 0)
	    return false;
	  input.dropBits(16);
	  if (nlen != (uncomprLen ^ 0xffff))
	    throw new DataFormatException("broken uncompressed block");
	  mode = DECODE_STORED;
	}
	/* fall through */
      case DECODE_STORED:
	{
	  int more = outputWindow.copyStored(input, uncomprLen);
	  uncomprLen -= more;
	  if (uncomprLen == 0)
	    {
	      mode = DECODE_BLOCKS;
	      return true;
	    }
	  return !input.needsInput();
	}

      case DECODE_DYN_HEADER:
	if (!dynHeader.decode(input))
	  return false;
	litlenTree = dynHeader.buildLitLenTree();
	distTree = dynHeader.buildDistTree();
	mode = DECODE_HUFFMAN;
	/* fall through */
      case DECODE_HUFFMAN:
      case DECODE_HUFFMAN_LENBITS:
      case DECODE_HUFFMAN_DIST:
      case DECODE_HUFFMAN_DISTBITS:
	return decodeHuffman();
      case FINISHED:
	return false;
      default:
	throw new IllegalStateException();
      }	
  }

  /**
   * Sets the preset dictionary.  This should only be called, if
   * needsDictionary() returns true and it should set the same
   * dictionary, that was used for deflating.  The getAdler()
   * function returns the checksum of the dictionary needed.
   * @param buffer the dictionary.
   * @exception IllegalStateException if no dictionary is needed.
   * @exception IllegalArgumentException if the dictionary checksum is
   * wrong.  
   */
  public void setDictionary(byte[] buffer)
  {
    setDictionary(buffer, 0, buffer.length);
  }

  /**
   * Sets the preset dictionary.  This should only be called, if
   * needsDictionary() returns true and it should set the same
   * dictionary, that was used for deflating.  The getAdler()
   * function returns the checksum of the dictionary needed.
   * @param buffer the dictionary.
   * @param off the offset into buffer where the dictionary starts.
   * @param len the length of the dictionary.
   * @exception IllegalStateException if no dictionary is needed.
   * @exception IllegalArgumentException if the dictionary checksum is
   * wrong.  
   * @exception IndexOutOfBoundsException if the off and/or len are wrong.
   */
  public void setDictionary(byte[] buffer, int off, int len)
  {
    if (!needsDictionary())
      throw new IllegalStateException();

    adler.update(buffer, off, len);
    if ((int) adler.getValue() != readAdler)
      throw new IllegalArgumentException("Wrong adler checksum");
    adler.reset();
    outputWindow.copyDict(buffer, off, len);
    mode = DECODE_BLOCKS;
  }

  /**
   * Sets the input.  This should only be called, if needsInput()
   * returns true.
   * @param buffer the input.
   * @exception IllegalStateException if no input is needed.
   */
  public void setInput(byte[] buf) 
  {
    setInput(buf, 0, buf.length);
  }

  /**
   * Sets the input.  This should only be called, if needsInput()
   * returns true.
   * @param buffer the input.
   * @param off the offset into buffer where the input starts.
   * @param len the length of the input.  
   * @exception IllegalStateException if no input is needed.
   * @exception IndexOutOfBoundsException if the off and/or len are wrong.
   */
  public void setInput(byte[] buf, int off, int len) 
  {
    input.setInput(buf, off, len);
    totalIn += len;
  }

  /**
   * Inflates the compressed stream to the output buffer.  If this
   * returns 0, you should check, whether needsDictionary(),
   * needsInput() or finished() returns true, to determine why no 
   * further output is produced.
   * @param buffer the output buffer.
   * @return the number of bytes written to the buffer, 0 if no further
   * output can be produced.  
   * @exception DataFormatException, if deflated stream is invalid.
   * @exception IllegalArgumentException, if buf has length 0.
   */
  public int inflate(byte[] buf) throws DataFormatException
  {
    return inflate(buf, 0, buf.length);
  }
  
  /**
   * Inflates the compressed stream to the output buffer.  If this
   * returns 0, you should check, whether needsDictionary(),
   * needsInput() or finished() returns true, to determine why no 
   * further output is produced.
   * @param buffer the output buffer.
   * @param off the offset into buffer where the output should start.
   * @param len the maximum length of the output.
   * @return the number of bytes written to the buffer, 0 if no further
   * output can be produced.  
   * @exception DataFormatException, if deflated stream is invalid.
   * @exception IllegalArgumentException, if len is <= 0.
   * @exception IndexOutOfBoundsException if the off and/or len are wrong.
   */
  public int inflate(byte[] buf, int off, int len) throws DataFormatException
  {
    if (len <= 0)
      throw new IllegalArgumentException("len <= 0");
    int count = 0;
    int more;
    do
      {
	if (mode != DECODE_CHKSUM)
	  {
	    /* Don't give away any output, if we are waiting for the
	     * checksum in the input stream.
	     *
	     * With this trick we have always:
	     *   needsInput() and not finished() 
	     *   implies more output can be produced.  
	     */
	    more = outputWindow.copyOutput(buf, off, len);
	    adler.update(buf, off, more);
	    off += more;
	    count += more;
	    totalOut += more;
	    len -= more;
	    if (len == 0)
	      return count;
	  }
      }
    while (decode() || (outputWindow.getAvailable() > 0
			&& mode != DECODE_CHKSUM));
    return count;
  }

  /**
   * Returns true, if the input buffer is empty.
   * You should then call setInput(). <br>
   *
   * <em>NOTE</em>: This method also returns true when the stream is finished.
   */
  public boolean needsInput() 
  {
    return input.needsInput();
  }

  /**
   * Returns true, if a preset dictionary is needed to inflate the input.
   */
  public boolean needsDictionary()
  {
    return mode == DECODE_DICT && neededBits == 0;
  }

  /**
   * Returns true, if the inflater has finished.  This means, that no
   * input is needed and no output can be produced.
   */
  public boolean finished() 
  {
    return mode == FINISHED && outputWindow.getAvailable() == 0;
  }

  /**
   * Gets the adler checksum.  This is either the checksum of all
   * uncompressed bytes returned by inflate(), or if needsDictionary()
   * returns true (and thus no output was yet produced) this is the
   * adler checksum of the expected dictionary.
   * @returns the adler checksum.
   */
  public int getAdler()
  {
    return needsDictionary() ? readAdler : (int) adler.getValue();
  }

  /**
   * Gets the total number of output bytes returned by inflate().
   * @return the total number of output bytes.
   */
  public int getTotalOut()
  {
    return totalOut;
  }

  /**
   * Gets the total number of processed compressed input bytes.
   * @return the total number of bytes of processed input bytes.
   */
  public int getTotalIn()
  {
    return totalIn - getRemaining();
  }

  /**
   * Gets the number of unprocessed input.  Useful, if the end of the
   * stream is reached and you want to further process the bytes after
   * the deflate stream.  
   * @return the number of bytes of the input which were not processed.
   */
  public int getRemaining()
  {
    return input.getAvailableBytes();
  }

  /**
   * Frees all objects allocated by the inflater.  There's no reason
   * to call this, since you can just rely on garbage collection (even
   * for the Sun implementation).  Exists only for compatibility
   * with Sun's JDK, where the compressor allocates native memory.
   * If you call any method (even reset) afterwards the behaviour is
   * <i>undefined</i>.  
   * @deprecated Just clear all references to inflater instead.
   */
  public void end()
  {
    outputWindow = null;
    input = null;
    dynHeader = null;
    litlenTree = null;
    distTree = null;
    adler = null;
  }

  /**
   * Finalizes this object.
   */
  protected void finalize()
  {
    /* Exists only for compatibility */
  }
}

class Input
{
  private byte[] window;
  private int window_start = 0;
  private int window_end = 0;

  private int buffer = 0;
  private int bits_in_buffer = 0;

  /**
   * Get the next n bits but don't increase input pointer.  n must be
   * less or equal 16 and if you if this call succeeds, you must drop
   * at least n-8 bits in the next call.
   * 
   * @return the value of the bits, or -1 if not enough bits available.  */
  public final int peekBits(int n)
  {
    if (bits_in_buffer < n)
      {
	if (window_start == window_end)
	  return -1;
	buffer |= (window[window_start++] & 0xff
		   | (window[window_start++] & 0xff) << 8) << bits_in_buffer;
	bits_in_buffer += 16;
      }
    return buffer & ((1 << n) - 1);
  }

  /* Drops the next n bits from the input.  You should have called peekBits
   * with a bigger or equal n before, to make sure that enough bits are in
   * the bit buffer.
   */
  public final void dropBits(int n)
  {
    buffer >>>= n;
    bits_in_buffer -= n;
  }

  /**
   * Gets the next n bits and increases input pointer.  This is equivalent
   * to peekBits followed by dropBits, except for correct error handling.
   * @return the value of the bits, or -1 if not enough bits available. 
   */
  public final int getBits(int n)
  {
    int bits = peekBits(n);
    if (bits >= 0)
      dropBits(n);
    return bits;
  }
  /**
   * Gets the number of bits available in the bit buffer.  This must be
   * only called when a previous peekBits() returned -1.
   * @return the number of bits available.
   */
  public final int getAvailableBits()
  {
    return bits_in_buffer;
  }

  /**
   * Gets the number of bytes available.  
   * @return the number of bytes available.
   */
  public final int getAvailableBytes()
  {
    return window_end - window_start + (bits_in_buffer >> 3);
  }

  /**
   * Skips to the next byte boundary.
   */
  public void skipToByteBoundary()
  {
    buffer >>= (bits_in_buffer & 7);
    bits_in_buffer &= ~7;
  }

  public final boolean needsInput() {
    return window_start == window_end;
  }


  /* Copies length bytes from input buffer to output buffer starting
   * at output[offset].  You have to make sure, that the buffer is
   * byte aligned.  If not enough bytes are available, copies fewer
   * bytes.
   * @param length the length to copy, 0 is allowed.
   * @return the number of bytes copied, 0 if no byte is available.  
   */
  public int copyBytes(byte[] output, int offset, int length)
  {
    if (length < 0)
      throw new IllegalArgumentException("length negative");
    if ((bits_in_buffer & 7) != 0)  
      /* bits_in_buffer may only be 0 or 8 */
      throw new IllegalStateException("Bit buffer is not aligned!");

    int count = 0;
    while (bits_in_buffer > 0 && length > 0)
      {
	output[offset++] = (byte) buffer;
	buffer >>>= 8;
	bits_in_buffer -= 8;
	length--;
	count++;
      }
    if (length == 0)
      return count;

    int avail = window_end - window_start;
    if (length > avail)
      length = avail;
    System.arraycopy(window, window_start, output, offset, length);
    window_start += length;

    if (((window_start - window_end) & 1) != 0)
      {
	/* We always want an even number of bytes in input, see peekBits */
	buffer = (window[window_start++] & 0xff);
	bits_in_buffer = 8;
      }
    return count + length;
  }

  public Input()
  {
  }

  public void reset()
  {
    window_start = window_end = buffer = bits_in_buffer = 0;
  }

  public void setInput(byte[] buf, int off, int len)
  {
    if (window_start < window_end)
      throw new IllegalStateException
	("Old input was not completely processed");

    int end = off + len;

    /* We want to throw an ArrayIndexOutOfBoundsException early.  The
     * check is very tricky: it also handles integer wrap around.  
     */
    if (0 > off || off > end || end > buf.length)
      throw new ArrayIndexOutOfBoundsException();
    
    if ((len & 1) != 0)
      {
	/* We always want an even number of bytes in input, see peekBits */
	buffer |= (buf[off++] & 0xff) << bits_in_buffer;
	bits_in_buffer += 8;
      }
    
    window = buf;
    window_start = off;
    window_end = end;
  }
}

class Output
{
  private final int WINDOW_SIZE = 1 << 15;
  private final int WINDOW_MASK = WINDOW_SIZE - 1;

  private byte[] window = new byte[WINDOW_SIZE]; //The window is 2^15 bytes
  private int window_end  = 0;
  private int window_filled = 0;

  public void write(int abyte)
  {
    if (window_filled++ == WINDOW_SIZE)
      throw new IllegalStateException("Window full");
    window[window_end++] = (byte) abyte;
    window_end &= WINDOW_MASK;
  }


  private final void slowRepeat(int rep_start, int len, int dist)
  {
    while (len-- > 0)
      {
	window[window_end++] = window[rep_start++];
	window_end &= WINDOW_MASK;
	rep_start &= WINDOW_MASK;
      }
  }

  public void repeat(int len, int dist)
  {
    if ((window_filled += len) > WINDOW_SIZE)
      throw new IllegalStateException("Window full");

    int rep_start = (window_end - dist) & WINDOW_MASK;
    int border = WINDOW_SIZE - len;
    if (rep_start <= border && window_end < border)
      {
	if (len <= dist)
	  {
	    System.arraycopy(window, rep_start, window, window_end, len);
	    window_end += len;
	  }
	else
	  {
	    /* We have to copy manually, since the repeat pattern overlaps.
	     */
	    while (len-- > 0)
	      window[window_end++] = window[rep_start++];
	  }
      }
    else
      slowRepeat(rep_start, len, dist);
  }

  public int copyStored(Input input, int len)
  {
    len = Math.min(Math.min(len, WINDOW_SIZE - window_filled), 
		   input.getAvailableBytes());
    int copied;

    int tailLen = WINDOW_SIZE - window_end;
    if (len > tailLen)
      {
	copied = input.copyBytes(window, window_end, tailLen);
	if (copied == tailLen)
	  copied += input.copyBytes(window, 0, len - tailLen);
      }
    else
      copied = input.copyBytes(window, window_end, len);

    window_end = (window_end + copied) & WINDOW_MASK;
    window_filled += copied;
    return copied;
  }

  public void copyDict(byte[] dict, int offset, int len)
  {
    if (window_filled > 0)
      throw new IllegalStateException();

    if (len > WINDOW_SIZE)
      {
	offset += len - WINDOW_SIZE;
	len = WINDOW_SIZE;
      }
    System.arraycopy(dict, offset, window, 0, len);
    window_end = len & WINDOW_MASK;
  }

  public int getFreeSpace()
  {
    return WINDOW_SIZE - window_filled;
  }

  public int getAvailable()
  {
    return window_filled;
  }

  public int copyOutput(byte[] output, int offset, int len)
  {
    int copy_end = window_end;
    if (len > window_filled)
      len = window_filled;
    else
      copy_end = (window_end - window_filled + len) & WINDOW_MASK;

    int copied = len;
    int tailLen = len - copy_end;

    if (tailLen > 0)
      {
	System.arraycopy(window, WINDOW_SIZE - tailLen,
			 output, offset, tailLen);
	offset += tailLen;
	len = copy_end;
      }
    System.arraycopy(window, copy_end - len, output, offset, len);
    window_filled -= copied;
    if (window_filled < 0)
      throw new IllegalStateException();
    return copied;
  }

  public void reset() {
    window_filled = window_end = 0;
  }
}

class _InflaterDynHeader
{
  private static final int LNUM   = 0;
  private static final int DNUM   = 1;
  private static final int BLNUM  = 2;
  private static final int BLLENS = 3;
  private static final int LLENS  = 4;
  private static final int DLENS  = 5;
  private static final int LREPS  = 6;
  private static final int DREPS  = 7;
  private static final int FINISH = 8;
  
  private byte[] blLens;
  private byte[] litlenLens;
  private byte[] distLens;

  private _InflaterHuffmanTree blTree;
  
  private int mode;
  private int lnum, dnum, blnum;
  private int repBits;
  private byte repeatedLen;
  private int ptr;

  private static final int[] BL_ORDER =
  { 16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15 };
  
  public _InflaterDynHeader()
  {
  }
  
  public boolean decode(Input input) throws DataFormatException
  {
  decode_loop:
    for (;;)
      {
	switch (mode)
	  {
	  case LNUM:
	    lnum = input.peekBits(5);
	    if (lnum < 0)
	      return false;
	    lnum += 257;
	    input.dropBits(5);
	    litlenLens = new byte[lnum];
//  	    System.err.println("LNUM: "+lnum);
	    mode = DNUM;
	    /* fall through */
	  case DNUM:
	    dnum = input.peekBits(5);
	    if (dnum < 0)
	      return false;
	    dnum++;
	    input.dropBits(5);
	    distLens = new byte[dnum];
//  	    System.err.println("DNUM: "+dnum);
	    mode = BLNUM;
	    /* fall through */
	  case BLNUM:
	    blnum = input.peekBits(4);
	    if (blnum < 0)
	      return false;
	    blnum += 4;
	    input.dropBits(4);
	    blLens = new byte[19];
	    ptr = 0;
//  	    System.err.println("BLNUM: "+blnum);
	    mode = BLLENS;
	    /* fall through */
	  case BLLENS:
	    while (ptr < blnum)
	      {
		int len = input.peekBits(3);
		if (len < 0)
		  return false;
		input.dropBits(3);
//  		System.err.println("blLens["+BL_ORDER[ptr]+"]: "+len);
		blLens[BL_ORDER[ptr]] = (byte) len;
		ptr++;
	      }
	    blTree = new _InflaterHuffmanTree(blLens);
	    blLens = null;
	    ptr = 0;
	    mode = LLENS;
	    /* fall through */
	  case LLENS:
	    while (ptr < lnum)
	      {
		int symbol = blTree.getSymbol(input);
		if (symbol < 0)
		  return false;
		switch (symbol)
		  {
		  default:
//  		    System.err.println("litlenLens["+ptr+"]: "+symbol);
		    litlenLens[ptr++] = (byte) symbol;
		    break;
		  case 16: /* repeat last len 3-6 times */
		    if (ptr == 0)
		      throw new DataFormatException
			("Repeating, but no prev len");
//  		    System.err.println("litlenLens["+ptr+"]: repeat");
		    repeatedLen = litlenLens[ptr-1];
		    repBits = 2;
		    for (int i = 3; i-- > 0; )
		      {
			if (ptr >= lnum)
			  throw new DataFormatException();
			litlenLens[ptr++] = repeatedLen;
		      }
		    mode = LREPS;
		    continue decode_loop;
		  case 17: /* repeat zero 3-10 times */
//  		    System.err.println("litlenLens["+ptr+"]: zero repeat");
		    repeatedLen = 0;
		    repBits = 3;
		    for (int i = 3; i-- > 0; )
		      {
			if (ptr >= lnum)
			  throw new DataFormatException();
			litlenLens[ptr++] = repeatedLen;
		      }
		    mode = LREPS;
		    continue decode_loop;
		  case 18: /* repeat zero 11-138 times */
//  		    System.err.println("litlenLens["+ptr+"]: zero repeat");
		    repeatedLen = 0;
		    repBits = 7;
		    for (int i = 11; i-- > 0; )
		      {
			if (ptr >= lnum)
			  throw new DataFormatException();
			litlenLens[ptr++] = repeatedLen;
		      }
		    mode = LREPS;
		    continue decode_loop;
		  }
	      }
	    ptr = 0;
	    mode = DLENS;
	    /* fall through */
	  case DLENS:
	    while (ptr < dnum)
	      {
		int symbol = blTree.getSymbol(input);
		if (symbol < 0)
		  return false;
		switch (symbol)
		  {
		  default:
		    distLens[ptr++] = (byte) symbol;
//  		    System.err.println("distLens["+ptr+"]: "+symbol);
		    break;
		  case 16: /* repeat last len 3-6 times */
		    if (ptr == 0)
		      throw new DataFormatException
			("Repeating, but no prev len");
//  		    System.err.println("distLens["+ptr+"]: repeat");
		    repeatedLen = distLens[ptr-1];
		    repBits = 2;
		    for (int i = 3; i-- > 0; )
		      {
			if (ptr >= dnum)
			  throw new DataFormatException();
			distLens[ptr++] = repeatedLen;
		      }
		    mode = DREPS;
		    continue decode_loop;
		  case 17: /* repeat zero 3-10 times */
//  		    System.err.println("distLens["+ptr+"]: repeat zero");
		    repeatedLen = 0;
		    repBits = 3;
		    for (int i = 3; i-- > 0; )
		      {
			if (ptr >= dnum)
			  throw new DataFormatException();
			distLens[ptr++] = repeatedLen;
		      }
		    mode = DREPS;
		    continue decode_loop;
		  case 18: /* repeat zero 11-138 times */
//  		    System.err.println("distLens["+ptr+"]: repeat zero");
		    repeatedLen = 0;
		    repBits = 7;
		    for (int i = 11; i-- > 0; )
		      {
			if (ptr >= dnum)
			  throw new DataFormatException();
			distLens[ptr++] = repeatedLen;
		      }
		    mode = DREPS;
		    continue decode_loop;
		  }
	      }
	    mode = FINISH;
	    return true;
	  case LREPS:
	    {
	      int count = input.peekBits(repBits);
	      if (count < 0)
		return false;
	      input.dropBits(repBits);
//  	      System.err.println("litlenLens repeat: "+repBits);
	      while (count-- > 0)
		{
		  if (ptr >= lnum)
		    throw new DataFormatException();
		  litlenLens[ptr++] = repeatedLen;
		}
	    }
	    mode = LLENS;
	    continue decode_loop;
	  case DREPS:
	    {
	      int count = input.peekBits(repBits);
	      if (count < 0)
		return false;
	      input.dropBits(repBits);
	      while (count-- > 0)
		{
		  if (ptr >= dnum)
		    throw new DataFormatException();
		  distLens[ptr++] = repeatedLen;
		}
	    }
	    mode = DLENS;
	    continue decode_loop;
	  }
      }
  }

  public _InflaterHuffmanTree buildLitLenTree() throws DataFormatException
  {
    return new _InflaterHuffmanTree(litlenLens);
  }

  public _InflaterHuffmanTree buildDistTree() throws DataFormatException
  {
    return new _InflaterHuffmanTree(distLens);
  }
}

class _InflaterHuffmanTree {
  private final static int MAX_BITLEN = 15;
  private short[] tree;

  public static _InflaterHuffmanTree defLitLenTree, defDistTree;

  static
  {
    try 
      {
	byte[] codeLengths = new byte[288];
	int i = 0;
	while (i < 144)
	  codeLengths[i++] = 8;
	while (i < 256)
	  codeLengths[i++] = 9;
	while (i < 280)
	  codeLengths[i++] = 7;
	while (i < 288)
	  codeLengths[i++] = 8;
	defLitLenTree = new _InflaterHuffmanTree(codeLengths);
	
	codeLengths = new byte[32];
	i = 0;
	while (i < 32)
	  codeLengths[i++] = 5;
	defDistTree = new _InflaterHuffmanTree(codeLengths);
      } 
    catch (DataFormatException ex)
      {
	throw new InternalError
	  ("InflaterHuffmanTree: static tree length illegal");
      }
  }

  private final static String bit4Reverse =
    "\000\010\004\014\002\012\006\016\001\011\005\015\003\013\007\017";

  /**
   * Reverse the bits of a 16 bit value.
   */
  private static short bitReverse(int value) {
    return (short) (bit4Reverse.charAt(value & 0xf) << 12
		    | bit4Reverse.charAt((value >> 4) & 0xf) << 8
		    | bit4Reverse.charAt((value >> 8) & 0xf) << 4
		    | bit4Reverse.charAt(value >> 12));
  }

  /**
   * Constructs a Huffman tree from the array of code lengths.
   *
   * @param codeLengths the array of code lengths
   */
  public _InflaterHuffmanTree(byte[] codeLengths) throws DataFormatException
  {
    buildTree(codeLengths);
  }
  
  private void buildTree(byte[] codeLengths) throws DataFormatException
  {
    int[] blCount = new int[MAX_BITLEN+1];
    int[] nextCode = new int[MAX_BITLEN+1];
    for (int i = 0; i < codeLengths.length; i++)
      {
	int bits = codeLengths[i];
	if (bits > 0)
	  blCount[bits]++;
      }

    int code = 0;
    int treeSize = 512;
    for (int bits = 1; bits <= MAX_BITLEN; bits++)
      {
	nextCode[bits] = code;
	code += blCount[bits] << (16 - bits);
	if (bits >= 10)
	  {
	    /* We need an extra table for bit lengths >= 10. */
	    int start = nextCode[bits] & 0x1ff80;
	    int end   = code & 0x1ff80;
	    treeSize += (end - start) >> (16 - bits);
	  }
      }
    if (code != 65536)
      throw new DataFormatException("Code lengths don't add up properly.");

    /* Now create and fill the extra tables from longest to shortest
     * bit len.  This way the sub trees will be aligned.
     */
    tree = new short[treeSize];
    int treePtr = 512;
    for (int bits = MAX_BITLEN; bits >= 10; bits--)
      {
	int end   = code & 0x1ff80;
	code -= blCount[bits] << (16 - bits);
	int start = code & 0x1ff80;
	for (int i = start; i < end; i += 1 << 7)
	  {
	    tree[bitReverse(i)]
	      = (short) ((-treePtr << 4) | bits);
	    treePtr += 1 << (bits-9);
	  }
      }
    
    for (int i = 0; i < codeLengths.length; i++)
      {
	int bits = codeLengths[i];
	if (bits == 0)
	  continue;
	code = nextCode[bits];
	int revcode = bitReverse(code);
	if (bits <= 9)
	  {
	    do
	      {
		tree[revcode] = (short) ((i << 4) | bits);
		revcode += 1 << bits;
	      }
	    while (revcode < 512);
	  }
	else
	  {
	    int subTree = tree[revcode & 511];
	    int treeLen = 1 << (subTree & 15);
	    subTree = -(subTree >> 4);
	    do
	      { 
		tree[subTree | (revcode >> 9)] = (short) ((i << 4) | bits);
		revcode += 1 << bits;
	      }
	    while (revcode < treeLen);
	  }
	nextCode[bits] = code + (1 << (16 - bits));
      }
  }

  /**
   * Reads the next symbol from input.  The symbol is encoded using the
   * huffman tree.
   * @param input the input source.
   * @return the next symbol, or -1 if not enough input is available.
   */
  public int getSymbol(Input input) throws DataFormatException
  {
    int lookahead, symbol;
    if ((lookahead = input.peekBits(9)) >= 0)
      {
	if ((symbol = tree[lookahead]) >= 0)
	  {
	    input.dropBits(symbol & 15);
	    return symbol >> 4;
	  }
	int subtree = -(symbol >> 4);
	int bitlen = symbol & 15;
	if ((lookahead = input.peekBits(bitlen)) >= 0)
	  {
	    symbol = tree[subtree | (lookahead >> 9)];
	    input.dropBits(symbol & 15);
	    return symbol >> 4;
	  }
	else
	  {
	    int bits = input.getAvailableBits();
	    lookahead = input.peekBits(bits);
	    symbol = tree[subtree | (lookahead >> 9)];
	    if ((symbol & 15) <= bits)
	      {
		input.dropBits(symbol & 15);
		return symbol >> 4;
	      }
	    else
	      return -1;
	  }
      }
    else
      {
	int bits = input.getAvailableBits();
	lookahead = input.peekBits(bits);
	symbol = tree[lookahead];
	if (symbol >= 0 && (symbol & 15) <= bits)
	  {
	    input.dropBits(symbol & 15);
	    return symbol >> 4;
	  }
	else
	  return -1;
      }
  }
}

