/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class LineNumberReader extends BufferedReader {

  private int lineNumber;
  private int markedLineNumber;

  public LineNumberReader(Reader in) {
    super(in);
  }

  public LineNumberReader(Reader in, int size) {
    super(in, size);
  }

  public void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public int read() throws IOException {
    synchronized (lock) {
      int shar = super.read();
      if (shar == '\r') {
        shar = super.read();
        if (shar != '\n') {
          super.unread(shar);
          shar = '\r';
        }
      }
      if (shar == '\n')
        lineNumber++;
      return shar;
    }
  }

  public int read(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length == 0)
      return 0;
    synchronized (lock) {
      int shar = read();
      if (shar == -1)
        return -1;
      array[start] = (char)shar;
      for (int i = start+1; i < end; i++)
        try {
          shar = read();
          if (shar == -1)
            return i-start;
          array[i] = (char)shar;
        } catch (IOException e) {
          return i-start;
        }
      return length;
    }
  }

  public String readLine() throws IOException {
    synchronized (lock) {
      String line = super.readLine();
      if (line != null)
        lineNumber++;
      return line;
    }
  }

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

  public void mark(int limit) throws IOException {
    synchronized (lock) {
      super.mark(limit);
      markedLineNumber = lineNumber;
    }
  }

  public void reset() throws IOException {
    synchronized (lock) {
      super.reset();
      lineNumber = markedLineNumber;
    }
  }

}

