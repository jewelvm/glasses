/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;

public class MulticastSocket extends DatagramSocket {

  public MulticastSocket() throws IOException {
    this(0);
  }

  public MulticastSocket(int localPort) throws IOException {
    super(localPort, null);
  }

  public synchronized void setInterface(InetAddress address) throws SocketException {
    DatagramSocketImpl impl = getDatagramSocketImpl();
    impl.setOption(SocketOptions.IP_MULTICAST_IF, new Integer(address.getRawAddress()));
  }

  public synchronized InetAddress getInterface() throws SocketException {
    DatagramSocketImpl impl = getDatagramSocketImpl();
    Object value = impl.getOption(SocketOptions.IP_MULTICAST_IF);
    if (value instanceof Integer) {
      Integer intg = (Integer)value;
      return new InetAddress(intg.intValue());
    }
    return null;
  }

  public synchronized void setTimeToLive(int ttl) throws SocketException {
    DatagramSocketImpl impl = getDatagramSocketImpl();
    impl.setTimeToLive(ttl);
  }

  public synchronized int getTimeToLive() throws SocketException {
    DatagramSocketImpl impl = getDatagramSocketImpl();
    return impl.getTimeToLive();
  }

  /** @deprecated */
  public synchronized void setTTL(byte ttl) throws SocketException {
    DatagramSocketImpl impl = getDatagramSocketImpl();
    impl.setTTL(ttl);
  }

  /** @deprecated */
  public synchronized byte getTTL() throws SocketException {
    DatagramSocketImpl impl = getDatagramSocketImpl();
    return impl.getTTL();
  }

  public synchronized void joinGroup(InetAddress address) throws IOException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkMulticast(address);
    DatagramSocketImpl impl = getDatagramSocketImpl();
    impl.join(address);
  }

  public synchronized void leaveGroup(InetAddress address) throws IOException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkMulticast(address);
    DatagramSocketImpl impl = getDatagramSocketImpl();
    impl.leave(address);
  }

  public synchronized void send(DatagramPacket packet, byte ttl) throws IOException {
    DatagramSocketImpl impl = getDatagramSocketImpl();
    byte saved = getTTL();
    impl.setTTL(ttl);
    try {
      send(packet);
    } finally {
      impl.setTTL(saved);
    }
  }
  
  public String toString() {
    return "MulticastSocket[addr="+getInetAddress()+",port="+getPort()+",localport="+getLocalPort()+"]";
  }

}

