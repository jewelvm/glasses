/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class CharArrayWriter extends Writer {

  protected int count;
  protected char[] buf;

  public CharArrayWriter() {
    this(32);
  }

  public CharArrayWriter(int size) {
    if (size < 0)
      throw new IllegalArgumentException("Negative size");
    buf = new char[size];
  }

  public int size() {
    return count;
  }

  public void reset() {
    synchronized (lock) {
      count = 0;
    }
  }

  public void write(int shar) {
    synchronized (lock) {
      if (count == buf.length) {
        char[] tmp = buf;
        buf = new char[2*count+1];
        System.arraycopy(tmp, 0, buf, 0, count);
      }
      buf[count++] = (char)shar;
    }
  }

  public void write(char[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    synchronized (lock) {
      if (count+length >= buf.length) {
        char[] tmp = buf;
        buf = new char[2*count+length];
        System.arraycopy(tmp, 0, buf, 0, count);
      }
      System.arraycopy(array, start, buf, count, length);
      count += length;
    }
  }

  public void write(String string, int start, int length) {
    if (start < 0 || start > string.length())
      throw new StringIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > string.length())
      throw new StringIndexOutOfBoundsException(end);
    synchronized (lock) {
      if (count+length >= buf.length) {
        char[] tmp = buf;
        buf = new char[2*count+length];
        System.arraycopy(tmp, 0, buf, 0, count);
      }
      string.getChars(start, end, buf, count);
      count += length;
    }
  }

  public void writeTo(Writer out) throws IOException {
    synchronized (lock) {
      out.write(buf, 0, count);
    }
  }

  public void flush() { }

  public void close() { }

  public char[] toCharArray() {
    synchronized (lock) {
      char[] array = new char[count];
      System.arraycopy(buf, 0, array, 0, count);
      return array;
    }
  }

  public String toString() {
    return new String(buf, 0, count);
  }

}

