/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

/** @deprecated */
public class StringBufferInputStream extends InputStream {

  protected int pos;
  protected int count;
  protected String buffer;

  public StringBufferInputStream(String string) {
    buffer = string;
    count = string.length();
  }

  public synchronized int read() {
    if (pos == count)
      return -1;
    return buffer.charAt(pos++) & 0xFF;
  }

  public synchronized int read(byte[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int remaining = count-pos;
    if (remaining == 0)
      return -1;
    int read = length;
    if (read > remaining)
      read = remaining;
    for (int i = 0; i < read; i++)
      array[start+i] = (byte)buffer.charAt(pos+i);
    pos += read;
    return read;
  }

  public synchronized long skip(long count) {
    if (count <= 0)
      return 0;
    int remaining = this.count-pos;
    long total = count;
    if (total > remaining)
      total = remaining;
    pos += total;
    return total;
  }

  public synchronized int available() {
    return count-pos;
  }

  public synchronized void reset() {
    pos = 0;
  }

}

