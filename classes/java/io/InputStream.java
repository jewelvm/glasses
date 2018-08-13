/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public abstract class InputStream {

  protected InputStream() { }

  public abstract int read() throws IOException;

  public int read(byte[] array) throws IOException {
    return read(array, 0, array.length);
  }

  public int read(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length == 0)
      return 0;
    int bite = read();
    if (bite == -1)
      return -1;
    array[start] = (byte)bite;
    for (int i = start+1; i < end; i++)
      try {
        bite = read();
        if (bite == -1)
          return i-start;
        array[i] = (byte)bite;
      } catch (IOException e) {
        return i-start;
      }
    return length;
  }
  
  public long skip(long count) throws IOException {
    if (count <= 0)
      return 0;
    byte[] buffer = new byte[512];
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
  
  public int available() throws IOException {
    return 0;
  }

  public void close() throws IOException { }

  public boolean markSupported() {
    return false;
  }

  public void mark(int limit) { }

  public void reset() throws IOException {
    throw new IOException("Reset not supported");
  }

}

