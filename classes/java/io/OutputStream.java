/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public abstract class OutputStream {

  protected OutputStream() { }

  public abstract void write(int bite) throws IOException;

  public void write(byte[] array) throws IOException {
    write(array, 0, array.length);
  }

  public void write(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      write(array[i]);
  }
  
  public void flush() throws IOException { }

  public void close() throws IOException { }

}

