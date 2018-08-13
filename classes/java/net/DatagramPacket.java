/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

public final class DatagramPacket {

  private byte[] data;
  private int offset;
  private int length;
  private InetAddress address;
  private int port;

  public DatagramPacket(byte[] data, int length) {
    this(data, 0, length);
  }
  
  public DatagramPacket(byte[] data, int offset, int length) {
    setData(data, offset, length);
    this.address = null;
    this.port = -1;
  }

  public DatagramPacket(byte[] data, int length, InetAddress address, int port) {
    this(data, 0, length, address, port);
  }
  
  public DatagramPacket(byte[] data, int offset, int length, InetAddress address, int port) {
    setData(data, offset, length);
    setAddress(address);
    setPort(port);
  }

  public synchronized void setData(byte[] data) {
    setData(data, 0, data.length);
  }

  public synchronized void setData(byte[] data, int offset, int length) {
    if (offset < 0 || offset > data.length)
      throw new IllegalArgumentException("Invalid offset");
    int end = offset+length;
    if (end < offset || end > data.length)
      throw new IllegalArgumentException("Invalid length");
    this.data = data;
    this.length = length;
    this.offset = offset;
  }

  public synchronized byte[] getData() {
    return data;
  }
  
  public synchronized void setOffset(int offset) {
    int end = offset+length;
    if (offset < 0 || end > data.length)
      throw new IllegalArgumentException("Invalid offset");
    this.offset = offset;
  }

  public synchronized int getOffset() {
    return offset;
  }

  public synchronized void setLength(int length) {
    int end = offset+length;
    if (end < offset || end > data.length)
      throw new IllegalArgumentException("Invalid length");
    this.length = length;
  }

  public synchronized int getLength() {
    return length;
  }

  public synchronized void setAddress(InetAddress address) {
    if (address == null)
      throw new NullPointerException();
    this.address = address;
  }

  public synchronized InetAddress getAddress() {
    return address;
  }
  
  public synchronized void setPort(int port) {
    if (port < 0 || port > 655535)
      throw new IllegalArgumentException("Invalid port");
    this.port = port;
  }

  public synchronized int getPort() {
    return port;
  }
  
}

