/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.SequenceInputStream;

public class GZIPInputStream extends InflaterInputStream {

  public static final int GZIP_MAGIC = 0x8B1F;

  private static int readUnsignedByte(InputStream in) throws IOException {
    int bite = in.read();
    if (bite < 0)
      throw new EOFException();
    return bite;
  }

  private static int readUnsignedShort(InputStream in) throws IOException {
    int byte0 = readUnsignedByte(in);
    int byte1 = readUnsignedByte(in);
    return byte1 << 8 | byte0;
  }

  private static int readInt(InputStream in) throws IOException {
    int short0 = readUnsignedShort(in);
    int short1 = readUnsignedShort(in);
    return short1 << 16 | short0;
  }

  private static void skipBytes(InputStream in, long total) throws IOException {
    long count = 0;
    while (count < total) {
      long read = in.skip(total-count);
      if (read == -1)
        throw new EOFException();
      count += read;
    }
  }

  protected CRC32 crc = new CRC32(); 
  protected boolean eos;

  public GZIPInputStream(InputStream in) throws IOException {
    this(in, 512);
  }

  public GZIPInputStream(InputStream in, int size) throws IOException {
    super(in, new Inflater(true), size);
    readHeader();
  }

  public int read(byte[] array, int start, int length) throws IOException {
    if (eos)
      return -1;
    int read = super.read(array, start, length);
    if (read == -1) {
      readFooter();
      eos = true;
      return -1;
    }
    crc.update(array, start, read);
    return read;
  }

  private void readHeader() throws IOException {
    CheckedInputStream in = new CheckedInputStream(this.in, new CRC32());
    int magic = readUnsignedShort(in);
    if (magic != GZIP_MAGIC)
      throw new IOException("Invalid magic");
    int method = readUnsignedByte(in);
    if (method != 8)
      throw new IOException("Unsupported compression method");
    int flags = readUnsignedByte(in);
    skipBytes(in, 6);
    if ((flags & 0x4) != 0)
      skipBytes(in, readUnsignedShort(in));
    if ((flags & 0x8) != 0)
      while (readUnsignedByte(in) > 0);
    if ((flags & 0x10) != 0)
      while (readUnsignedByte(in) > 0);
    if ((flags & 0x2) != 0)
      if ((char)in.getChecksum().getValue() != readUnsignedShort(in))
        throw new IOException("Corrupt header (CRC mismatch)");
  }

  private void readFooter() throws IOException {
    InputStream in = this.in;
    int remaining = inf.getRemaining();
    if (remaining > 0)
      in = new SequenceInputStream(new ByteArrayInputStream(buf, len-remaining, remaining), in);
    int CRC = readInt(in);
    if (CRC != (int)crc.getValue())
      throw new IOException("Corrupt file (CRC mismatch)");
    int totalOut = readInt(in);
    if (totalOut != inf.getTotalOut())
      throw new IOException("Corrupt file (size mismatch)");
  }

  public void close() throws IOException {
    super.close();
    eos = true;
  }

}

