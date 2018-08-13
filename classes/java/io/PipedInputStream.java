/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PipedInputStream extends InputStream {

  protected static final int PIPE_SIZE = 1024;

  private boolean connected;
  private boolean closed;

  protected int in = -1;
  protected int out;
  protected byte[] buffer = new byte[PIPE_SIZE];

  public PipedInputStream() { }

  public PipedInputStream(PipedOutputStream out) throws IOException {
    connect(out);
  }

  protected synchronized void connect() throws IOException {
    if (connected)
      throw new IOException("Already connected");
    connected = true;
  }

  public void connect(PipedOutputStream out) throws IOException {
    out.connect(this);
  }

  protected synchronized void receive(int bite) throws IOException {
    for (;;) {
      if (closed)
        throw new IOException("Pipe closed");
      if (!connected)
        throw new IOException("Disconnected");
      if (in != out)
        break;
      try {
        wait();
      } catch (InterruptedException e) {
        throw new InterruptedIOException(e.getMessage());
      }
    }
    if (in < 0)
      in = out;
    buffer[in] = (byte)bite;
    in = (in+1)%buffer.length;
    notifyAll();
  }

  protected synchronized void receive(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      receive(array[i]);
  }

  public synchronized int read() throws IOException {
    for (;;) {
      if (closed)
        throw new IOException("Pipe closed");
      if (in >= 0)
        break;
      if (!connected)
        return -1;
      try {
        wait();
      } catch (InterruptedException e) {
        throw new InterruptedIOException(e.getMessage());
      }
    }
    int bite = buffer[out] & 0xFF;
    out = (out+1)%buffer.length;
    if (out == in)
      in = -1;
    notifyAll();
    return bite;
  }

  public synchronized int read(byte[] array, int start, int length)  throws IOException {
    if (start < 0 || start > length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length == 0)
      return 0;
    for (;;) {
      if (closed)
        throw new IOException("Pipe closed");
      if (in >= 0)
        break;
      if (!connected)
        return -1;
      try {
        wait();
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
    notifyAll();
    return read;
  }

  public synchronized int available() throws IOException {
    if (closed)
      throw new IOException("Stream closed");
    if (in < 0)
      return 0;
    return out < in ? in-out : buffer.length-(out-in);
  }

  protected synchronized void disconnect() {
    if (connected) {
      connected = false;
      notifyAll();
    }
  }

  public synchronized void close() {
    if (!closed) {
      in = -1;
      buffer = null;
      closed = true;
      notifyAll();
    }
  }

}

