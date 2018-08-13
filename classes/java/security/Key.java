/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.Serializable;

public interface Key extends Serializable {

  public String getAlgorithm();
  public String getFormat();
  public byte[] getEncoded();

}

