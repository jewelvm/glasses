/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class ByteArrayOutputStream extends OutputStream {

  protected int count;
  protected byte[] buf;

  public ByteArrayOutputStream() {
    this(32);
  }

  public ByteArrayOutputStream(int size) {
    if (size < 0)
      throw new IllegalArgumentException("Negative size");
    buf = new byte[size];
  }

  public int size() {
    return count;
  }

  public synchronized void reset() {
    count = 0;
  }

  public synchronized void write(int bite) {
    if (count == buf.length) {
      byte[] tmp = buf;
      buf = new byte[2*count+1];
      System.arraycopy(tmp, 0, buf, 0, count);
    }
    buf[count++] = (byte)bite;
  }

  public synchronized void write(byte[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (count+length >= buf.length) {
      byte[] tmp = buf;
      buf = new byte[2*count+length];
      System.arraycopy(tmp, 0, buf, 0, count);
    }
    System.arraycopy(array, start, buf, count, length);
    count += length;
  }
  
  public synchronized void writeTo(OutputStream out) throws IOException {
    out.write(buf, 0, count);
  }

  public void close() { }

  public synchronized byte[] toByteArray() {
    byte[] array = new byte[count];
    System.arraycopy(buf, 0, array, 0, count);
    return array;
  }
  
  public String toString() {
    return new String(buf, 0, count);
  }

  /** @deprecated */
  public String toString(int hibyte) {
    return new String(buf, hibyte, 0, count);
  }

  public String toString(String encoding) throws UnsupportedEncodingException {
    return new String(buf, 0, count, encoding);
  }

}

