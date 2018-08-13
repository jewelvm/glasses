/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PushbackInputStream extends FilterInputStream {

  protected int pos;
  protected byte[] buf;
  private int markPos;
  private byte[] markBuf;

  public PushbackInputStream(InputStream in) {
    this(in, 1);
  }

  public PushbackInputStream(InputStream in, int size) {
    super(in);
    if (size <= 0)
      throw new IllegalArgumentException("Invalid buffer size");
    this.buf = new byte[size];
    this.pos = buf.length;
  }

  public synchronized int read() throws IOException {
    if (pos < buf.length)
      return buf[pos++] & 0xFF;
    return super.read();
  }

  public synchronized int read(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int available = buf.length-pos;
    if (available == 0)
      return super.read(array, start, length);
    if (length <= available) {
      System.arraycopy(buf, pos, array, start, length);
      pos += length;
      return length;
    }
    System.arraycopy(buf, pos, array, start, available);
    pos += available;
    int read = super.read(array, start+available, length-available);
    if (read == -1)
      return available;
    return available+read;
  }

  public synchronized void unread(int bite) throws IOException {
    if (pos == 0)
      throw new IOException("Pushback buffer overflow");
    buf[--pos] = (byte)bite;
  }

  public void unread(byte[] array) throws IOException {
    unread(array, 0, array.length);
  }

  public synchronized void unread(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length > pos)
      throw new IOException("Pushback buffer overflow");
    pos -= length;
    System.arraycopy(array, start, buf, pos, length);
  }

  public synchronized long skip(long count) throws IOException {
    if (count <= 0)
      return 0;
    int available = buf.length-pos;
    if (available == 0)
      return super.skip(count);
    if (count <= available) {
      pos += count;
      return count;
    }
    pos += available;
    return available+super.skip(count-available);
  }

  public synchronized int available() throws IOException {
    return buf.length-pos+super.available();
  }

  public synchronized void close() throws IOException {
    super.close();
  }

  public synchronized void mark(int limit) {
    super.mark(limit);
    markPos = 0;
    markBuf = new byte[buf.length-pos];
    System.arraycopy(buf, pos, markBuf, markPos, markBuf.length);
  }

  public synchronized void reset() throws IOException {
    super.reset();
    pos = markPos;
    buf = markBuf;
  }

}

