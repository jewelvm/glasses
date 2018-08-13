/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;

public class DatagramSocket {

  private static DatagramSocketImplFactory factory;

  public static synchronized void setDatagramSocketImplFactory(DatagramSocketImplFactory factory) throws IOException {
    if (DatagramSocket.factory != null)
      throw new IOException("Factory already set");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSetFactory();
    DatagramSocket.factory = factory;
  }
  
  private final DatagramSocketImpl impl;
  private boolean connected;
  private InetAddress address;
  private int port = -1;

  public DatagramSocket() throws IOException {
    this(0);
  }

  private DatagramSocket(DatagramSocketImpl impl) {
    this.impl = impl;
  }

  private DatagramSocket(DatagramSocketImplFactory factory) {
    this(factory == null ? null/*new DefaultDatagramSocketImpl()*/ : factory.createDatagramSocketImpl());
  }

  public DatagramSocket(int localPort) throws IOException {
    this(localPort, null);
  }

  public DatagramSocket(int localPort, InetAddress localAddr) throws IOException {
    this(factory);
    if (localPort < 0 || localPort > 65535)
      throw new IllegalArgumentException("Invalid port");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkListen(localPort);
    try {
      impl.create();
      if (localAddr == null)
        localAddr = new InetAddress(0);
      if (this instanceof MulticastSocket)
        impl.setOption(SocketOptions.SO_REUSEADDR, new Integer(-1));
      impl.bind(localPort, localAddr);
    } catch(IOException e) {
      impl.close();
      throw e;
    }
  }

  protected final DatagramSocketImpl getDatagramSocketImpl() {
    return impl;
  }
  
  public synchronized InetAddress getInetAddress() {
    return address;
  }

  public synchronized int getPort() {
    return port;
  }

  public synchronized InetAddress getLocalAddress() {
    try {
      InetAddress address = (InetAddress)impl.getOption(SocketOptions.SO_BINDADDR);
      SecurityManager sm = System.getSecurityManager();
      if (sm != null)
        sm.checkConnect(address.getHostAddress(), -1);
      return address;
    } catch (SocketException e) {
      return new InetAddress(0);
    }
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
  
  public synchronized void setReceiveBufferSize(int value) throws SocketException {
    impl.setOption(SocketOptions.SO_RCVBUF, new Integer(value));
  }

  public synchronized int getReceiveBufferSize() throws SocketException {
    Object value = impl.getOption(SocketOptions.SO_RCVBUF);
    if (value instanceof Integer) {
      Integer intg = (Integer)value;
      return intg.intValue();
    }
    return 0;
  }

  public synchronized void setSendBufferSize(int value) throws SocketException {
    impl.setOption(SocketOptions.SO_SNDBUF, new Integer(value));
  }

  public synchronized int getSendBufferSize() throws SocketException {
    Object value = impl.getOption(SocketOptions.SO_SNDBUF);
    if (value instanceof Integer) {
      Integer intg = (Integer)value;
      return intg.intValue();
    }
    return 0;
  }

  public synchronized void connect(InetAddress address, int port) {
    if (address == null)
      throw new NullPointerException();
    if (port < 0 || port > 65535)
      throw new IllegalArgumentException("Invalid port");
    connected = true;
    this.address = address;
    this.port = port;
  }

  public synchronized void send(DatagramPacket packet) throws IOException  {
    InetAddress address = packet.getAddress();
    int port = packet.getPort();
    if (connected) {
      if (address == null)
        address = this.address;
      if (port == -1)
        port = this.port;
      if (!this.address.equals(address) || this.port != port)
        throw new IllegalArgumentException("Packet addressee mismatch");
    }
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      if (address.isMulticastAddress())
        sm.checkMulticast(address);
      else
        sm.checkConnect(address.getHostAddress(), port);
    synchronized (packet) {
      packet.setAddress(address);
      packet.setPort(port);
      impl.send(packet);
    }
  }

  public synchronized void receive(DatagramPacket packet) throws IOException {
    DatagramPacket discarded = new DatagramPacket(new byte[1], 1);
    for (;;) {
      InetAddress address = new InetAddress(0);
      int port = impl.peek(address);
      if (connected)
        if (!this.address.equals(address) || this.port != port) {
          impl.receive(discarded);
          continue;
        }
      try {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null)
          sm.checkAccept(address.getHostAddress(), port);
      } catch (SecurityException e) {
        impl.receive(discarded);
        continue;
      }
      break;
    }
    synchronized (packet) {
      impl.receive(packet);
    }
  }

  public synchronized void disconnect() {
    connected = false;
    address = null;
    port = -1;
  }

  public synchronized void close() throws IOException {
    impl.close();
  }
 
  public String toString() {
    return "DatagramSocket[addr="+getInetAddress()+",port="+getPort()+",localport="+getLocalPort()+"]";
  }

}

