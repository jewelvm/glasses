/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public interface Externalizable extends Serializable {

  public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException;
  public void writeExternal(ObjectOutput out) throws IOException;

}

