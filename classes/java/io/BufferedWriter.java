/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class BufferedWriter extends FilterWriter {

  private static final String eoln = System.getProperty("line.separator");

  private int count;
  private final char[] buf;

  public BufferedWriter(Writer out) {
    this(out, 512);
  }

  public BufferedWriter(Writer out, int size) {
    super(out);
    if (size <= 0)
      throw new IllegalArgumentException("Invalid buffer size");
    buf = new char[size];
  }

  public void write(int shar) throws IOException {
    synchronized (lock) {
      if (count == buf.length) {
        out.write(buf);
        count = 0;
      }
      buf[count++] = (char)shar;
    }
  }

  public void write(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    synchronized (lock) {
      if (length <= buf.length-count) {
        System.arraycopy(array, start, buf, count, length);
        count += length;
      } else {
        if (count > 0) {
          out.write(buf, 0, count);
          count = 0;
        }
        if (length > buf.length)
          out.write(array, start, length);
        else {
          System.arraycopy(array, start, buf, 0, length);
          count = length;
        }
      }
    }
  }

  public void write(String string, int start, int length) throws IOException {
    if (start < 0 || start > string.length())
      throw new StringIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > string.length())
      throw new StringIndexOutOfBoundsException(end);
    synchronized (lock) {
      if (length <= buf.length-count) {
        string.getChars(start, end, buf, count);
        count += length;
      } else {
        if (count > 0) {
          out.write(buf, 0, count);
          count = 0;
        }
        if (length > buf.length)
          out.write(string, start, length);
        else {
          string.getChars(start, end, buf, 0);
          count = length;
        }
      }
    }
  }

  public void newLine() throws IOException {
    write(eoln);
  }

  public void flush() throws IOException {
    synchronized (lock) {
      if (count > 0) {
        out.write(buf, 0, count);
        count = 0;
      }
      out.flush();
    }
  }

  public void close() throws IOException {
    synchronized (lock) {
      if (count > 0) {
        out.write(buf, 0, count);
        count = 0;
      }
      out.close();
    }
  }

}

