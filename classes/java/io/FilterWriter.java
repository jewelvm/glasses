/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public abstract class FilterWriter extends Writer {

  protected Writer out;

  protected FilterWriter(Writer out) {
    super(out);
    this.out = out;
  }

  public void write(int shar) throws IOException {
    out.write(shar);
  }

  public void write(char[] array, int start, int length) throws IOException {
    out.write(array, start, length);
  }

  public void write(String string, int start, int length) throws IOException {
    out.write(string, start, length);
  }

  public void flush() throws IOException {
    out.flush();
  }

  public void close() throws IOException {
    out.close();
  }

}

