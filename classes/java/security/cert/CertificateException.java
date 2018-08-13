/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security.cert;

import java.security.GeneralSecurityException;

public class CertificateException extends GeneralSecurityException {

  public CertificateException() { }

  public CertificateException(String message) {
    super(message);
  }

}

