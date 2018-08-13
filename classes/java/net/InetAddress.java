/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.StringTokenizer;

public final class InetAddress implements Serializable {

  private static final long serialVersionUID = 3286316764910316507L;

  private static final HashMap cache = new HashMap();

  private static int parseAddress(String hostName) throws NumberFormatException {
    StringTokenizer st = new StringTokenizer(hostName, ".");
    if (st.countTokens() != 4)
      throw new NumberFormatException();
    int address = 0;
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      int bite = Integer.parseInt(token);
      if (bite < 0 || bite > 255)
        throw new NumberFormatException();
      address = address<<8 | bite;
    }
    return address;
  }

  public static InetAddress getLocalHost() throws UnknownHostException {
    return getByName(net.getLocalHostName());
  }

  public static InetAddress getByName(String hostName) throws UnknownHostException {
    return getAllByName(hostName)[0];
  }

  public static InetAddress[] getAllByName(String hostName) throws UnknownHostException {
    String key = hostName.toLowerCase();
    InetAddress[] all;
    synchronized (cache) {
      all = (InetAddress[])cache.get(key);
      if (all == null) {
        try {
          all = new InetAddress[]{ new InetAddress(parseAddress(hostName)) };
        } catch (NumberFormatException e) {
          SecurityManager sm = System.getSecurityManager();
          if (sm != null)
            sm.checkConnect(hostName, -1);
          int[] addresses = net.getHostAddressesByName(hostName);
          if (addresses == null)
            throw new UnknownHostException(hostName);
          all = new InetAddress[addresses.length];
          for (int i = 0; i < all.length; i++)
            all[i] = new InetAddress(addresses[i]);
        }
        cache.put(key, all);
      }
    }
    return (InetAddress[])all.clone();
  }

  private String hostName;
  private final int address;

  protected InetAddress(int address) {
    this.address = address;
  }

  protected int getRawAddress() {
    return address;
  }

  public boolean isMulticastAddress() {
    return (address & 0xF0000000) == 0xE0000000;
  }

  public byte[] getAddress() {
    return new byte[]{ 
      (byte)(address>>24),
      (byte)(address>>16),
      (byte)(address>> 8),
      (byte) address
    };
  }

  public String getHostName() {
    if (this.hostName == null) {
      String hostName = net.getHostNameByAddress(address);
      if (hostName == null)
        hostName = getHostAddress();
      else {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null)
          sm.checkConnect(hostName, -1);
      }
      this.hostName = hostName;
    }
    return this.hostName;
  }

  public String getHostAddress() {
    return ((address>>24) & 0xFF)+"."+
           ((address>>16) & 0xFF)+"."+
           ((address>> 8) & 0xFF)+"."+
            (address      & 0xFF);
  }

  public int hashCode() {
    return address;
  }

  public boolean equals(Object object) {
    return object instanceof InetAddress
        && ((InetAddress)object).address == address;
  }

  public String toString() {
    return getHostName()+"/"+getHostAddress();
  }

}

