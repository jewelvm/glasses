/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public abstract class Writer {

  protected Object lock;

  protected Writer() {
    lock = this;
  }

  protected Writer(Object lock) {
    if (lock == null)
      throw new NullPointerException();
    this.lock = lock;
  }

  public void write(int shar) throws IOException {
    write(new char[]{ (char)shar });
  }

  public void write(char[] array) throws IOException {
    write(array, 0, array.length);
  }

  public abstract void write(char[] array, int start, int length) throws IOException;

  public void write(String string) throws IOException {
    write(string, 0, string.length());
  }

  public void write(String string, int start, int length) throws IOException {
    if (start < 0 || start > string.length())
      throw new StringIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > string.length())
      throw new StringIndexOutOfBoundsException(end);
    if (length > 0) {
      char[] buffer = new char[length];
      string.getChars(start, end, buffer, 0);
      write(buffer);
    }
  }

  public abstract void flush() throws IOException;
  
  public abstract void close() throws IOException;

}

