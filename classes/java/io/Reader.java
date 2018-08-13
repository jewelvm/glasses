/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public abstract class Reader {

  protected Object lock;

  protected Reader() {
    lock = this;
  }

  protected Reader(Object lock) {
    if (lock == null)
      throw new NullPointerException();
    this.lock = lock;
  }

  public int read() throws IOException {
    char[] buffer = new char[1];
    if (read(buffer) != 1)
      return -1;
    return buffer[0];
  }

  public int read(char[] array) throws IOException {
    return read(array, 0, array.length);
  }

  public abstract int read(char[] array, int start, int length) throws IOException;

  public long skip(long count) throws IOException {
    if (count < 0)
      return 0;
    char[] buffer = new char[256];
    long remaining = count;
    while (remaining >= buffer.length) {
      int read = read(buffer);
      if (read == -1)
        return count-remaining;
      remaining -= read;
    }
    while (remaining > 0) {
      int read = read(buffer, 0, (int)remaining);
      if (read == -1)
        return count-remaining;
      remaining -= read;
    }
    return count;
  }

  public boolean ready() throws IOException {
    return false;
  }

  public abstract void close() throws IOException;

  public boolean markSupported() {
    return false;
  }

  public void mark(int limit) throws IOException {
    throw new IOException("Mark not supported");
  }

  public void reset() throws IOException {
    throw new IOException("Reset not supported");
  }

}

