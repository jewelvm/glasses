/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class FilterInputStream extends InputStream {
    
  protected InputStream in;

  protected FilterInputStream(InputStream in) {
    this.in = in;
  }

  public int read() throws IOException {
    return in.read();
  }

  public int read(byte[] array, int start, int length) throws IOException {
    return in.read(array, start, length);
  }

  public long skip(long count) throws IOException {
    return in.skip(count);
  }

  public int available() throws IOException {
    return in.available();
  }

  public void close() throws IOException {
    in.close();
  }

  public boolean markSupported() {
    return in.markSupported();
  }

  public void mark(int limit) {
    in.mark(limit);
  }

  public void reset() throws IOException {
    in.reset();
  }

}

