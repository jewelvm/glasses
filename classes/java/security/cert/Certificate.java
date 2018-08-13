/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security.cert;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Arrays;

public abstract class Certificate implements Serializable {

  private final String type;

  protected Certificate(String type) {
    this.type = type;
  }

  public final String getType() {
    return type;
  }

  public abstract byte[] getEncoded() throws CertificateEncodingException;

  public abstract PublicKey getPublicKey();

  public abstract void verify(PublicKey key) throws CertificateException, InvalidKeyException,
                                                    NoSuchAlgorithmException, NoSuchProviderException,
                                                    SignatureException;

  public abstract void verify(PublicKey key, String provider) throws CertificateException, InvalidKeyException,
                                                                     NoSuchAlgorithmException, NoSuchProviderException,
                                                                     SignatureException;

  public int hashCode() {
    try {
      byte[] array = getEncoded();
      if (array == null)
        return 0;
      int hashCode = 0;
      for (int i = 0; i < array.length; i++)
        hashCode += array[i] << (i%25);
      return hashCode;
    } catch (CertificateEncodingException e) {
      return 0;
    }
  }

  public boolean equals(Object object) {
    try {
      return object instanceof Certificate
          && Arrays.equals(getEncoded(), ((Certificate)object).getEncoded());
    } catch (CertificateEncodingException e) {
      return false;
    }
  }

  public abstract String toString();

}

