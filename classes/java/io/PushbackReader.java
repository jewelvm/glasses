/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PushbackReader extends FilterReader {

  private int pos;
  private char[] buf;
  private int markPos;
  private char[] markBuf;

  public PushbackReader(Reader in) {
    this(in, 1);
  }

  public PushbackReader(Reader in, int size) {
    super(in);
    if (size <= 0)
      throw new IllegalArgumentException("Invalid buffer size");
    this.buf = new char[size];
    this.pos = buf.length;
  }

  public int read() throws IOException {
    synchronized (lock) {
      if (pos < buf.length)
        return buf[pos++];
      return super.read();
    }
  }

  public int read(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    synchronized (lock) {
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
  }

  public void unread(int shar) throws IOException {
    synchronized (lock) {
      if (pos == 0)
        throw new IOException("Pushback buffer overflow");
      buf[--pos] = (char)shar;
    }
  }

  public void unread(char[] array) throws IOException {
    unread(array, 0, array.length);
  }

  public void unread(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    synchronized (lock) {
      if (length > pos)
        throw new IOException("Pushback buffer overflow");
      pos -= length;
      System.arraycopy(array, start, buf, pos, length);
    }
  }

  public long skip(long count) throws IOException {
    if (count <= 0)
      return 0;
    synchronized (lock) {
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
  }

  public boolean ready() throws IOException {
    synchronized (lock) {
      return pos < buf.length || super.ready();
    }
  }

  public void close() throws IOException {
    synchronized (lock) {
      super.close();
    }
  }

  public void mark(int limit) throws IOException {
    synchronized (lock) {
      super.mark(limit);
      markPos = 0;
      markBuf = new char[buf.length-pos];
      System.arraycopy(buf, pos, markBuf, markPos, markBuf.length);
    }
  }

  public void reset() throws IOException {
    synchronized (lock) {
      super.reset();
      pos = markPos;
      buf = markBuf;
    }
  }

}

