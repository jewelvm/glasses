/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class FilterOutputStream extends OutputStream {

  protected OutputStream out;

  public FilterOutputStream(OutputStream out) {
    this.out = out;
  }

  public void write(int bite) throws IOException {
    out.write(bite);
  }

  public void write(byte[] array, int start, int length) throws IOException {
    out.write(array, start, length);
  }

  public void flush() throws IOException {
    out.flush();
  }

  public void close() throws IOException {
    flush();
    out.close();
  }

}

