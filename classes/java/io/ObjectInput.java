/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public interface ObjectInput extends DataInput {

  public Object readObject() throws ClassNotFoundException, IOException;
  public int read() throws IOException;
  public int read(byte[] array) throws IOException;
  public int read(byte[] array, int start, int length) throws IOException;
  public long skip(long count) throws IOException;
  public int available() throws IOException;
  public void close() throws IOException;

}

