/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class CharArrayReader extends Reader {

  protected int pos;
  protected int count;
  protected char buf[];
  protected int markedPos;

  public CharArrayReader(char[] array) {
    this(array, 0, array.length);
  }

  public CharArrayReader(char[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    buf = array;
    pos = start;
    count = end;
    markedPos = pos;
  }

  public int read() {
    synchronized (lock) {
      if (pos == count)
        return -1;
      return buf[pos++];
    }
  }

  public int read(char[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    synchronized (lock) {
      if (pos == count)
        return -1;
      int total = length;
      int available = count-pos;
      if (total > available)
        total = available;
      System.arraycopy(buf, pos, array, start, total);
      pos += total;
      return total;
    }
  }

  public long skip(long count) {
    if (count <= 0)
      return 0;
    synchronized (lock) {
      long total = count;
      int available = this.count-pos;
      if (total > available)
        total = available;
      pos += total;
      return total;
    }
  }

  public boolean ready() {
    synchronized (lock) {
      return count-pos > 0;
    }
  }

  public void close() { }

  public boolean markSupported() {
    return true;
  }

  public void mark(int limit) {
    synchronized (lock) {
      markedPos = pos;
    }
  }

  public void reset() {
    synchronized (lock) {
      pos = markedPos;
    }
  }

}

