/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class BufferedReader extends FilterReader {

  private int pos;
  private int count;
  private char[] buf;
  private int markpos = -1;
  private int marklimit;

  public BufferedReader(Reader in) {
    this(in, 512);
  }

  public BufferedReader(Reader in, int size) {
    super(in);
    if (size <= 0)
      throw new IllegalArgumentException("Invalid buffer size");
    buf = new char[size];
  }

  private int readFill() throws IOException {
    if (pos == count) {
      if (markpos != -1)
        if (pos >= markpos+marklimit)
          markpos = -1;
      if (markpos == -1) {
        pos = 0;
        count = 0;
      } else if (markpos > 0) {
        count -= markpos;
        System.arraycopy(buf, markpos, buf, 0, count);
        markpos = 0;
        pos = count;
      }
      if (count == buf.length) {
        char[] tmp = buf;
        buf = new char[2*tmp.length];
        System.arraycopy(tmp, 0, buf, 0, tmp.length);
      }
      while (pos == count) {
        int read = super.read(buf, count, buf.length-count);
        if (read == -1)
          return -1;
        count += read;
      }
    }
    return count-pos;
  }

  public int read() throws IOException {
    synchronized (lock) {
      int read = readFill();
      if (read == -1)
        return -1;
      return buf[pos++];
    }
  }

  protected void unread(int shar) {
    if (pos == 0) {
      char[] tmp = buf;
      buf = new char[buf.length+1];
      System.arraycopy(tmp, 0, buf, 1, tmp.length);
      pos++;
      count++;
      if (markpos > 0)
        markpos++;
    }
    buf[--pos] = (char)shar;
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
      int read = readFill();
      if (read == -1)
        return -1;
      int total = 0;
      while (total < length) {
        read = readFill();
        if (read == -1)
          break;
        int remainder = length-total;
        if (read > remainder)
          read = remainder;
        System.arraycopy(buf, pos, array, start+total, read);
        pos += read;
        total += read;
      }
      return total;
    }
  }

  public String readLine() throws IOException {
    synchronized (lock) {
      StringBuffer sb = new StringBuffer();
      int shar;
      while ((shar = read()) != -1) {
        if (shar == '\n') {
          int length = sb.length();
          if (length > 0)
            if (sb.charAt(length-1) == '\r')
              sb.setLength(length-1);
          return sb.toString();
        }
        sb.append((char)shar);
      }
      if (sb.length() == 0)
        return null;
      return sb.toString();
    }
  }

  public long skip(long count) throws IOException {
    synchronized (lock) {
      long total = 0;
      while (total < count) {
        int read = readFill();
        if (read == -1)
          break;
        long remainder = count-total;
        if (read > remainder)
          read = (int)remainder;
        pos += read;
        total += read;
      }
      return total;
    }
  }

  public boolean ready() throws IOException {
    synchronized (lock) {
      return pos < count || super.ready();
    }
  }

  public void close() throws IOException {
    synchronized (lock) {
      pos = count;
      markpos = -1;
      super.close();
    }
  }

  public boolean markSupported() {
    return true;
  }

  public void mark(int limit) throws IOException {
    synchronized (lock) {
      markpos = pos;
      marklimit = limit;
    }
  }

  public void reset() throws IOException {
    synchronized (lock) {
      if (markpos == -1)
        throw new IOException("Reset without previous mark");
      pos = markpos;
    }
  }

}

