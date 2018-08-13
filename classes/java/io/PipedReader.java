/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PipedReader extends Reader {

  private static final int PIPE_SIZE = 1024;

  private boolean connected;
  private boolean closed;

  private int in = -1;
  private int out;
  private char[] buffer = new char[PIPE_SIZE];

  public PipedReader() { }

  public PipedReader(PipedWriter out) throws IOException {
    connect(out);
  }

  protected void connect() throws IOException {
    synchronized (lock) {
      if (connected)
        throw new IOException("Already connected");
      connected = true;
    }
  }

  public void connect(PipedWriter out) throws IOException {
    out.connect(this);
  }

  protected void receive(int shar) throws IOException {
    synchronized (lock) {
      for (;;) {
        if (closed)
          throw new IOException("Pipe closed");
        if (!connected)
          throw new IOException("Disconnected");
        if (in != out)
          break;
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new InterruptedIOException(e.getMessage());
        }
      }
      if (in < 0)
        in = out;
      buffer[in] = (char)shar;
      in = (in+1)%buffer.length;
      lock.notifyAll();
    }
  }

  protected void receive(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > length)
      throw new ArrayIndexOutOfBoundsException(end);
    synchronized (lock) {
      for (int i = start; i < end; i++)
        receive(array[i]);
    }
  }

  public int read() throws IOException {
    synchronized (lock) {
      for (;;) {
        if (closed)
          throw new IOException("Pipe closed");
        if (in >= 0)
          break;
        if (!connected)
          return -1;
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new InterruptedIOException(e.getMessage());
        }
      }
      int shar = buffer[out];
      out = (out+1)%buffer.length;
      if (out == in)
        in = -1;
      lock.notifyAll();
      return shar;
    }
  }

  public int read(char[] array, int start, int length)  throws IOException {
    if (start < 0 || start > length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length == 0)
      return 0;
    synchronized (lock) {
      for (;;) {
        if (closed)
          throw new IOException("Pipe closed");
        if (in >= 0)
          break;
        if (!connected)
          return -1;
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new InterruptedIOException(e.getMessage());
        }
      }
      int read = out < in ? in-out : buffer.length-(out-in);
      if (read > length)
        read = length;
      for (int i = 0; i < read; i++) {
        array[start+i] = buffer[out];
        out = (out+1)%buffer.length;
      }
      if (out == in)
        in = -1;
      lock.notifyAll();
      return read;
    }
  }

  public boolean ready() throws IOException {
    synchronized (lock) {
      if (closed)
        throw new IOException("Stream closed");
      if (in < 0)
        return false;
      return true;
    }
  }

  protected void disconnect() {
    synchronized (lock) {
      if (connected) {
        connected = false;
        lock.notifyAll();
      }
    }
  }

  public void close() {
    synchronized (lock) {
      if (!closed) {
        in = -1;
        buffer = null;
        closed = true;
        lock.notifyAll();
      }
    }
  }

}

