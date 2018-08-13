/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileDescriptor;

public abstract class SocketImpl implements SocketOptions {

  protected FileDescriptor fd;
  protected InetAddress address;
  protected int port;
  protected int localport;   

  protected SocketImpl() { }

  protected FileDescriptor getFileDescriptor() {
    return fd;
  }

  protected InetAddress getInetAddress() {
    return address;
  }

  protected int getPort() {
    return port;
  }

  protected int getLocalPort() {
    return localport;
  }
  
  protected abstract void create(boolean stream) throws IOException;
  protected abstract void connect(String host, int port) throws IOException;
  protected abstract void connect(InetAddress address, int port) throws IOException;
  protected abstract void bind(InetAddress address, int port) throws IOException;
  protected abstract void listen(int backlog) throws IOException;
  protected abstract void accept(SocketImpl impl) throws IOException;
  protected abstract InputStream getInputStream() throws IOException;
  protected abstract OutputStream getOutputStream() throws IOException;
  protected abstract int available() throws IOException;
  protected abstract void close() throws IOException;

  protected void shutdownInput() throws IOException {
    throw new IOException("Operation not available");
  }
  
  protected void shutdownOutput() throws IOException {
    throw new IOException("Operation not available");
  }  

  public String toString() {
    return "Socket[addr="+getInetAddress()+",port="+getPort()+",localport="+getLocalPort()+"]";
  }

}

