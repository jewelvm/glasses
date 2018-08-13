/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PipedWriter extends Writer {

  private boolean closed;
  private boolean connected;
  private PipedReader in;
    
  public PipedWriter() { }

  public PipedWriter(PipedReader in)  throws IOException {
    connect(in);
  }

  public void connect(PipedReader in) throws IOException {
    synchronized (lock) {
      if (connected)
        throw new IOException("Already connected");
      in.connect();
      connected = true;
      this.in = in;
    }
  }

  public void write(int shar)  throws IOException {
    synchronized (lock) {
      if (closed)
        throw new IOException("Stream closed");
      if (!connected)
        throw new IOException("Not connected");
      in.receive(shar);
    }
  }

  public void write(char[] array, int start, int length) throws IOException {
    synchronized (lock) {
      if (closed)
        throw new IOException("Stream closed");
      if (!connected)
        throw new IOException("Not connected");
      in.receive(array, start, length);
    }
  }

  protected void disconnect() {
    synchronized (lock) {
      if (connected) {
        in.disconnect();
        in = null;
        connected = false;
      }
    }
  }

  public void flush() { }

  public void close() {
    synchronized (lock) {
      if (!closed) {
        disconnect();
        closed = true;
      }
    }
  }

}

