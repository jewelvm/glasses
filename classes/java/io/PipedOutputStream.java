/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PipedOutputStream extends OutputStream {

  private boolean closed;
  private boolean connected;
  private PipedInputStream in;
    
  public PipedOutputStream() { }

  public PipedOutputStream(PipedInputStream in)  throws IOException {
    connect(in);
  }

  public synchronized void connect(PipedInputStream in) throws IOException {
    if (connected)
      throw new IOException("Already connected");
    in.connect();
    connected = true;
    this.in = in;
  }

  public synchronized void write(int bite)  throws IOException {
    if (closed)
      throw new IOException("Stream closed");
    if (!connected)
      throw new IOException("Not connected");
    in.receive(bite);
  }

  public synchronized void write(byte[] array, int start, int length) throws IOException {
    if (closed)
      throw new IOException("Stream closed");
    if (!connected)
      throw new IOException("Not connected");
    in.receive(array, start, length);
  }

  protected synchronized void disconnect() {
    if (connected) {
      in.disconnect();
      in = null;
      connected = false;
    }
  }

  public synchronized void close() {
    if (!closed) {
      disconnect();
      closed = true;
    }
  }

}

