/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public abstract class FilterReader extends Reader {

  protected Reader in;

  protected FilterReader(Reader in) {
    super(in);
    this.in = in;
  }

  public int read() throws IOException {
    return in.read();
  }

  public int read(char[] array, int start, int length) throws IOException {
    return in.read(array, start, length);
  }

  public long skip(long count) throws IOException {
    return in.skip(count);
  }

  public boolean ready() throws IOException {
    return in.ready();
  }

  public void close() throws IOException {
    in.close();
  }

  public boolean markSupported() {
    return in.markSupported();
  }

  public void mark(int limit) throws IOException {
    in.mark(limit);
  }

  public void reset() throws IOException {
    in.reset();
  }

}

