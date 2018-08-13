/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public interface DataInput {

  public boolean readBoolean() throws IOException;
  public byte readByte() throws IOException;
  public int readUnsignedByte() throws IOException;
  public short readShort() throws IOException;
  public int readUnsignedShort() throws IOException;
  public char readChar() throws IOException;
  public int readInt() throws IOException;
  public long readLong() throws IOException;
  public float readFloat() throws IOException;
  public double readDouble() throws IOException;
  public String readUTF() throws IOException;
  public String readLine() throws IOException;
  public void readFully(byte[] array) throws IOException;
  public void readFully(byte[] array, int start, int length) throws IOException;
  public int skipBytes(int count) throws IOException;

}

