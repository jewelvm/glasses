/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class StringReader extends Reader {

  private final String string;
  private int pos;
  private int markpos;

  public StringReader(String string) {
    this.string = string;
  }

  public int read() {
    synchronized (lock) {
      if (pos == string.length())
        return -1;
      return string.charAt(pos++);
    }
  }

  public int read(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    synchronized (lock) {
      int remaining = string.length()-pos;
      if (remaining == 0)
        return -1;
      int read = length;
      if (read > remaining)
        read = remaining;
      string.getChars(pos, pos+read, array, start);
      pos += read;
      return read;
    }
  }

  public long skip(long count) throws IOException {
    if (count <= 0)
      return 0;
    synchronized (lock) {
      int remaining = string.length()-pos;
      long total = count;
      if (total > remaining)
        total = remaining;
      pos += total;
      return total;
    }
  }

  public boolean ready() throws IOException {
    return true;
  }

  public void close() { }

  public boolean markSupported() {
    return true;
  }

  public void mark(int limit) throws IOException {
    synchronized (lock) {
      markpos = pos;
    }
  }

  public void reset() throws IOException {
    synchronized (lock) {
      pos = markpos;
    }
  }

}

