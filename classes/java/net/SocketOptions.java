/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

public interface SocketOptions {

  public static final int TCP_NODELAY = 0x00000001;
  
  public static final int IP_TOS = 0x00000003;
  public static final int IP_MULTICAST_IF = 0x00000010;
  public static final int IP_MULTICAST_LOOP = 0x00000012;
  public static final int IP_MULTICAST_IF2 = 0x0000001F;

  public static final int SO_REUSEADDR = 0x00000004;
  public static final int SO_KEEPALIVE = 0x00000008;
  public static final int SO_BINDADDR = 0x0000000F;
  public static final int SO_BROADCAST = 0x00000020;
  public static final int SO_LINGER = 0x00000080;
  public static final int SO_SNDBUF = 0x00001001;
  public static final int SO_RCVBUF = 0x000001002;
  public static final int SO_OOBINLINE = 0x00001003;
  public static final int SO_TIMEOUT = 0x00001006;

  public void setOption(int option, Object value) throws SocketException;
  public Object getOption(int option) throws SocketException;
  
}

