/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public interface ObjectOutput extends DataOutput {

  public void writeObject(Object object) throws IOException;
  public void flush() throws IOException;
  public void close() throws IOException;

}

