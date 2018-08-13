/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class ByteArrayInputStream extends InputStream {

  protected int pos;
  protected int count;
  protected byte[] buf;
  protected int mark;

  public ByteArrayInputStream(byte[] array) {
    this(array, 0, array.length);
  }

  public ByteArrayInputStream(byte[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    buf = array;
    pos = start;
    count = end;
    mark = pos;
  }

  public synchronized int read() {
    if (pos == count)
      return -1;
    return buf[pos++] & 0xFF;
  }

  public synchronized int read(byte[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
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

  public synchronized long skip(long count) {
    if (count <= 0)
      return 0;
    long total = count;
    int available = this.count-pos;
    if (total > available)
      total = available;
    pos += total;
    return total;
  }

  public synchronized int available() {
    return count-pos;
  }

  public boolean markSupported() {
    return true;
  }

  public synchronized void mark(int limit) {
    mark = pos;
  }

  public synchronized void reset() {
    pos = mark;
  }

}

