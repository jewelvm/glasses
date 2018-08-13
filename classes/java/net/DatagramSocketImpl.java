/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.FileDescriptor;
import java.io.IOException;

public abstract class DatagramSocketImpl implements SocketOptions {

  protected FileDescriptor fd;
  protected int localPort;

  protected DatagramSocketImpl() { }

  protected FileDescriptor getFileDescriptor() {
    return fd;
  }

  protected int getLocalPort() {
    return localPort;
  }

  protected abstract void create() throws IOException;
  protected abstract void bind(int port, InetAddress address) throws IOException;
  protected abstract void send(DatagramPacket packet) throws IOException;
  protected abstract void receive(DatagramPacket packet) throws IOException;
  protected abstract int peek(InetAddress address) throws IOException;
  protected abstract void join(InetAddress address) throws IOException;
  protected abstract void leave(InetAddress address) throws IOException;
  protected abstract void close() throws IOException;

  protected abstract void setTimeToLive(int ttl) throws SocketException;
  protected abstract int getTimeToLive() throws SocketException;

  /** @deprecated */
  protected abstract void setTTL(byte ttl) throws SocketException;

  /** @deprecated */
  protected abstract byte getTTL() throws SocketException;

}

