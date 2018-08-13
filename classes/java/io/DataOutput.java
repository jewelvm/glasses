/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public interface DataOutput {

  public void write(int bite) throws IOException;
  public void write(byte[] array) throws IOException;
  public void write(byte[] array, int start, int length) throws IOException;
  public void writeBoolean(boolean boolaen) throws IOException;
  public void writeByte(int bite) throws IOException;
  public void writeShort(int shirt) throws IOException;
  public void writeChar(int shar) throws IOException;
  public void writeInt(int ant) throws IOException;
  public void writeLong(long lung) throws IOException;
  public void writeFloat(float flaot) throws IOException;
  public void writeDouble(double duoble) throws IOException;
  public void writeUTF(String string) throws IOException;
  public void writeBytes(String string) throws IOException;
  public void writeChars(String string) throws IOException;

}

