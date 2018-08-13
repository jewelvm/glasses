/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;
import java.net.Socket.DefaultSocketImpl;

public class ServerSocket {

  private static SocketImplFactory factory;

  public static synchronized void setSocketFactory(SocketImplFactory factory) throws IOException {
    if (ServerSocket.factory != null)
      throw new IOException("Factory already set");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSetFactory();
    ServerSocket.factory = factory;
  }

  private final SocketImpl impl;

  private ServerSocket(SocketImpl impl) {
    this.impl = impl;
  }

  private ServerSocket(SocketImplFactory factory) {
    this(factory == null ? new DefaultSocketImpl() : factory.createSocketImpl());
  }

  public ServerSocket(int localPort) throws IOException {
    this(localPort, 50, null);
  }

  public ServerSocket(int localPort, int backlog) throws IOException {
    this(localPort, backlog, null);
  }

  public ServerSocket(int localPort, int backlog, InetAddress localAddr) throws IOException {
    this(factory);
    if (localPort < 0 || localPort > 65535)
      throw new IllegalArgumentException("Invalid port");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkListen(localPort);
    try {
      impl.create(true);
      if (localAddr == null)
        localAddr = new InetAddress(0); 
      impl.bind(localAddr, localPort);
      impl.listen(backlog);
    } catch(IOException e) {
      impl.close();
      throw e;
    }
  }

  protected final SocketImpl getSocketImpl() {
    return impl;
  }
  
  public synchronized InetAddress getInetAddress() {
    return impl.getInetAddress();
  }

  public synchronized int getLocalPort() {
    return impl.getLocalPort();
  }

  public synchronized void setSoTimeout(int value) throws SocketException {
    impl.setOption(SocketOptions.SO_TIMEOUT, new Integer(value));
  }

  public synchronized int getSoTimeout() throws SocketException {
    Object value = impl.getOption(SocketOptions.SO_TIMEOUT);
    if (value instanceof Integer) {
      Integer intg = (Integer)value;
      return intg.intValue();
    }
    return 0;
  }

  public Socket accept() throws IOException {
    Socket socket = new Socket();
    implAccept(socket);
    return socket;
  }

  protected final synchronized void implAccept(Socket socket) throws IOException {
    SocketImpl impl = socket.getSocketImpl();
    synchronized (socket) {
      this.impl.accept(impl);
      try {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null)
          sm.checkAccept(impl.getInetAddress().getHostAddress(), impl.getPort());
      } catch (SecurityException e) {
        impl.close();
        throw e;
      }
    }
  }

  public synchronized void close() throws IOException {
    impl.close();
  }

  public String toString() {
    return "ServerSocket[addr="+getInetAddress()+",localport="+getLocalPort()+"]";
  }

}

