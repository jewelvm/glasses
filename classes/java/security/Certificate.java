/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

/** @deprecated */
public interface Certificate {

  public Principal getPrincipal();
  public Principal getGuarantor();
  public PublicKey getPublicKey();
  public String getFormat();
  public void encode(OutputStream out) throws KeyException, IOException;
  public void decode(InputStream in) throws KeyException, IOException;
  public String toString(boolean detailed);

}

