/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Socket {

  private static SocketImplFactory factory;

  public static synchronized void setSocketImplFactory(SocketImplFactory factory) throws IOException {
    if (Socket.factory != null)
      throw new IOException("Factory already set");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSetFactory();
    Socket.factory = factory;
  }

  private final SocketImpl impl;

  protected Socket() {
    this(factory);
  }
           
  protected Socket(SocketImpl impl) {
    this.impl = impl;
  }

  private Socket(SocketImplFactory factory) {
    this(factory == null ? new DefaultSocketImpl() : factory.createSocketImpl());
  }

  public Socket(String host, int port) throws IOException {
    this(InetAddress.getByName(host), port);
  }
           
  /** @deprecated */
  public Socket(String host, int port, boolean stream) throws IOException {
    this(InetAddress.getByName(host), port, stream);
  }
           
  public Socket(String host, int port, InetAddress localAddr, int localPort) throws IOException {
    this(InetAddress.getByName(host), port, localAddr, localPort);
  }
    
  public Socket(InetAddress address, int port) throws IOException {
    this(address, port, true);
  }
           
  /** @deprecated */
  public Socket(InetAddress address, int port, boolean stream) throws IOException {
    this(address, port, null, 0, stream);
  }
           
  public Socket(InetAddress address, int port, InetAddress localAddr, int localPort) throws IOException {
    this(address, port, localAddr, localPort, true);
  }
  
  private Socket(InetAddress address, int port, InetAddress localAddr, int localPort, boolean stream) throws IOException {
    this(factory);
    if (port < 0 || port > 65535)
      throw new IllegalArgumentException("Invalid port");
    if (localPort < 0 || localPort > 65535)
      throw new IllegalArgumentException("Invalid local port");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkConnect(address.getHostAddress(), port);
    try {
      impl.create(stream);
      if (localAddr != null || localPort != 0) {
        if (localAddr == null)
          localAddr = new InetAddress(0);
        impl.bind(localAddr, localPort);
      }
      impl.connect(address, port);
    } catch (IOException e) {
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
  
  public synchronized int getPort() {
    return impl.getPort();
  }
  
  public synchronized InetAddress getLocalAddress() {
    try {
      return (InetAddress)impl.getOption(SocketOptions.SO_BINDADDR);
    } catch (SocketException e) {
      return new InetAddress(0);
    }
  }
  
  public synchronized int getLocalPort() {
    return impl.getLocalPort();
  }
  
  public synchronized InputStream getInputStream() throws IOException {
    return impl.getInputStream();
  }
  
  public synchronized OutputStream getOutputStream() throws IOException {
    return impl.getOutputStream();
  }
  
  public synchronized void setKeepAlive(boolean value) throws SocketException {
    impl.setOption(SocketOptions.SO_KEEPALIVE, new Boolean(value));
  }

  public synchronized boolean getKeepAlive() throws SocketException {
    Object value = impl.getOption(SocketOptions.SO_KEEPALIVE);
    if (value instanceof Boolean) {
      Boolean bool = (Boolean)value;
      return bool.booleanValue();
    }
    return false;
  }
      
  public synchronized void setTcpNoDelay(boolean value) throws SocketException {
    impl.setOption(SocketOptions.TCP_NODELAY, new Boolean(value));
  }
      
  public synchronized boolean getTcpNoDelay() throws SocketException {
    Object value = impl.getOption(SocketOptions.TCP_NODELAY);
    if (value instanceof Boolean) {
      Boolean bool = (Boolean)value;
      return bool.booleanValue();
    }
    return false;
  }
  
  public synchronized void setSoLinger(boolean enable, int value) throws SocketException {
    impl.setOption(SocketOptions.SO_LINGER, new Integer(enable ? value : -1));
  }
      
  public synchronized int getSoLinger() throws SocketException {
    Object value = impl.getOption(SocketOptions.SO_LINGER);
    if (value instanceof Integer) {
      Integer intg = (Integer)value;
      return intg.intValue();
    }
    return -1;
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

  public synchronized void shutdownInput() throws IOException {
    impl.shutdownInput();
  }
  
  public synchronized void shutdownOutput() throws IOException {
    impl.shutdownOutput();
  }
  
  public synchronized void close() throws IOException {
    impl.close();
  }
  
  public String toString() {
    return "Socket[addr="+getInetAddress()+",port="+getPort()+",localport="+getLocalPort()+"]";
  }

  protected static final class DefaultSocketImpl extends SocketImpl {

    private int timeout;

    private InputStream in = new InputStream() {
      public int read() throws IOException {
        byte[] array = new byte[1];
        int read = read(array);
        if (read < 1)
          return -1;
        return array[0] & 0xFF;
      }
      public int read(byte[] array, int start, int length) throws IOException {
        if (fd == null)
          throw new IOException("Stream closed");
        if (in == null)
          return -1;
        return ((SocketDescriptor)fd).recv(array, start, length, timeout);
      }
      public int available() throws IOException { return DefaultSocketImpl.this.available(); }
      public void close() throws IOException { DefaultSocketImpl.this.close(); }
    };

    private OutputStream out = new OutputStream() {
      public void write(int bite) throws IOException { write(new byte[]{ (byte)bite }); }
      public void write(byte[] array, int start, int length) throws IOException {
        if (fd == null)
          throw new IOException("Stream closed");
        if (out == null)
          throw new IOException("Output shutdown");
        ((SocketDescriptor)fd).send(array, start, length);
      }
      public void close() throws IOException { DefaultSocketImpl.this.close(); }
    };

    protected DefaultSocketImpl() { }

    protected void create(boolean stream) throws IOException {
      SocketDescriptor sd = new SocketDescriptor();
      sd.create(stream);
      fd = sd;
    }

    protected void connect(String host, int port) throws IOException {
      connect(InetAddress.getByName(host), port);
    }

    protected void connect(InetAddress address, int port) throws IOException {
      if (fd == null)
        throw new SocketException("Socket closed");
      int localport = ((SocketDescriptor)fd).connect(address.getRawAddress(), port);
      this.address = address;
      this.port = port;
      this.localport = localport;
    }

    protected void bind(InetAddress address, int port) throws IOException {
      if (fd == null)
        throw new SocketException("Socket closed");
      int localport = ((SocketDescriptor)fd).bind(address.getRawAddress(), port);
      this.address = address;
      this.localport = localport;
    }

    protected void listen(int backlog) throws IOException {
      if (fd == null)
        throw new SocketException("Socket closed");
      ((SocketDescriptor)fd).listen(backlog);
    }
    
    protected void accept(SocketImpl impl) throws IOException {
      if (fd == null)
        throw new SocketException("Socket closed");
      int[] address = new int[1];
      int[] port = new int[1];
      impl.fd = ((SocketDescriptor)fd).accept(timeout, address, port);
      impl.address = new InetAddress(address[0]);
      impl.port = port[0];
      impl.localport = this.port;
    }

    protected InputStream getInputStream() throws IOException {
      if (fd == null)
        throw new IOException("Socket closed");
      if (in == null)
        throw new IOException("Input shutdown");
      return in;
    }

    protected OutputStream getOutputStream() throws IOException {
      if (fd == null)
        throw new IOException("Socket closed");
      if (out == null)
        throw new IOException("Output shutdown");
      return out;
    }

    protected int available() throws IOException {
      if (fd == null)
        throw new SocketException("Socket closed");
      return ((SocketDescriptor)fd).available();
    }

    protected void shutdownInput() throws IOException {
      if (fd == null)
        throw new SocketException("Socket closed");
      if (in != null) {
        ((SocketDescriptor)fd).shutdownInput();
        in = null;
      }
    }

    protected void shutdownOutput() throws IOException {
      if (fd == null)
        throw new SocketException("Socket closed");
      if (out != null) {
        ((SocketDescriptor)fd).shutdownOutput();
        out = null;
      }
    }  

    protected void close() throws IOException {
      if (fd != null) {
        ((SocketDescriptor)fd).closesocket();
        fd = null;
        in = null;
        out = null;
      }
    }

    public void setOption(int option, Object value) throws SocketException {
      switch (option) {
      case SO_TIMEOUT:
        if (!(value instanceof Integer))
          throw new IllegalArgumentException();
        int timeout = ((Integer)value).intValue();
        if (timeout < 0)
          throw new IllegalArgumentException();
        this.timeout = timeout;
        break;
      default:
        throw new InternalError("Unimplemented");
      }
    }

    public Object getOption(int option) throws SocketException {
      switch (option) {
      case SO_TIMEOUT:
        return new Integer(timeout);
      default:
        throw new InternalError("Unimplemented");
      }
    }

    protected void finalize() throws IOException {
      close();
    }

  }

  public static final class SocketDescriptor extends FileDescriptor {

    public SocketDescriptor() { }

    void create(boolean stream) throws IOException {
      setID(net.create(stream));
    }

    int connect(int address, int port) throws IOException {
      return net.connect(getID(), address, port);
    }

    int bind(int address, int port) throws IOException {
      return net.bind(getID(), address, port);
    }

    void listen(int backlog) throws IOException {
      net.listen(getID(), backlog);
    }

    SocketDescriptor accept(int timeout, int[] address, int[] port) throws IOException {
      SocketDescriptor sd = new SocketDescriptor();
      sd.setID(net.accept(getID(), timeout, address, port));
      return sd;
    }

    int recv(byte[] array, int start, int length, int timeout) throws IOException {
      return net.recv(getID(), array, start, length, timeout);
    }

    void send(byte[] array, int start, int length) throws IOException {
      net.send(getID(), array, start, length);
    }

    int available() throws IOException {
      return net.available(getID());
    }

    void shutdownInput() throws IOException {
      net.shutdownInput(getID());
    }

    void shutdownOutput() throws IOException {
      net.shutdownOutput(getID());
    }

    void closesocket() throws IOException {
      net.closesocket(getID());
      setID(-1);
    }

  }
    
}

