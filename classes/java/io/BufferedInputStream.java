/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class BufferedInputStream extends FilterInputStream {

  protected int pos;
  protected int count;
  protected byte[] buf;
  protected int markpos = -1;
  protected int marklimit;

  public BufferedInputStream(InputStream in) {
    this(in, 512);
  }

  public BufferedInputStream(InputStream in, int size) {
    super(in);
    if (size <= 0)
      throw new IllegalArgumentException("Invalid buffer size");
    buf = new byte[size];
  }

  private int readFill() throws IOException {
    if (pos == count) {
      if (markpos != -1)
        if (pos >= markpos+marklimit)
          markpos = -1;
      if (markpos == -1) {
        pos = 0;
        count = 0;
      } else if (markpos > 0) {
        count -= markpos;
        System.arraycopy(buf, markpos, buf, 0, count);
        markpos = 0;
        pos = count;
      }
      if (count == buf.length) {
        byte[] tmp = buf;
        buf = new byte[2*tmp.length];
        System.arraycopy(tmp, 0, buf, 0, tmp.length);
      }
      while (pos == count) {
        int read = super.read(buf, count, buf.length-count);
        if (read == -1)
          return -1;
        count += read;
      }
    }
    return count-pos;
  }

  public synchronized int read() throws IOException {
    int read = readFill();
    if (read == -1)
      return -1;
    return buf[pos++] & 0xFF;
  }

  public synchronized int read(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length == 0)
      return 0;
    int read = readFill();
    if (read == -1)
      return -1;
    int total = 0;
    while (total < length) {
      read = readFill();
      if (read == -1)
        break;
      int remainder = length-total;
      if (read > remainder)
        read = remainder;
      System.arraycopy(buf, pos, array, start+total, read);
      pos += read;
      total += read;
    }
    return total;
  }

  public synchronized long skip(long count) throws IOException {
    long total = 0;
    while (total < count) {
      int read = readFill();
      if (read == -1)
        break;
      long remainder = count-total;
      if (read > remainder)
        read = (int)remainder;
      pos += read;
      total += read;
    }
    return total;
  }

  public synchronized int available() throws IOException {
    return count-pos+super.available();
  }

  public synchronized void close() throws IOException {
    pos = count;
    markpos = -1;
    super.close();
  }

  public boolean markSupported() {
    return true;
  }

  public synchronized void mark(int limit) {
    markpos = pos;
    marklimit = limit;
  }

  public synchronized void reset() throws IOException {
    if (markpos == -1)
      throw new IOException("Reset without previous mark");
    pos = markpos;
  }

}

