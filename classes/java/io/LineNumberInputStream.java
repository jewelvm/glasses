/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class LineNumberInputStream extends PushbackInputStream {

  private int lineNumber;
  private int markLineNumber;

  public LineNumberInputStream(InputStream in) {
    super(in);
  }

  public void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public synchronized int read() throws IOException {
    int bite = super.read();
    if (bite == '\r') {
      bite = super.read();
      if (bite != '\n') {
        super.unread(bite);
        bite = '\r';
      }
    }
    if (bite == '\n')
      lineNumber++;
    return bite;
  }

  public synchronized int read(byte[] array, int start, int length) throws IOException {
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

  public synchronized long skip(long count) throws IOException {
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
    return super.available()/2;
  }

  public synchronized void mark(int limit) {
    super.mark(limit);
    markLineNumber = lineNumber;
  }

  public synchronized void reset() throws IOException {
    super.reset();
    lineNumber = markLineNumber;
  }

}

