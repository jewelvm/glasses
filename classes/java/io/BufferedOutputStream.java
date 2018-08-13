/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class BufferedOutputStream extends FilterOutputStream {

  protected int count;
  protected byte[] buf;

  public BufferedOutputStream(OutputStream out) {
    this(out, 512);
  }

  public BufferedOutputStream(OutputStream out, int size) {
    super(out);
    if (size <= 0)
      throw new IllegalArgumentException("Invalid buffer size");
    buf = new byte[size];
  }

  public synchronized void write(int bite) throws IOException {
    if (count == buf.length) {
      out.write(buf);
      count = 0;
    }
    buf[count++] = (byte)bite;
  }

  public synchronized void write(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
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

  public synchronized void flush() throws IOException {
    if (count > 0) {
      out.write(buf, 0, count);
      count = 0;
    }
    out.flush();
  }

  public synchronized void close() throws IOException {
    super.close();
  }

}

