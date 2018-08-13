/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

public abstract class MessageDigestSpi {

  protected MessageDigestSpi() { }

  protected int engineGetDigestLength() {
    return 0;
  }

  protected abstract void engineReset();    
  protected abstract void engineUpdate(byte bite);
  protected abstract void engineUpdate(byte[] array, int start, int length);
  protected abstract byte[] engineDigest();

  protected int engineDigest(byte[] array, int start, int length) throws DigestException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    byte[] digest = engineDigest();
    if (length < digest.length)
      throw new DigestException("Not enough room");
    System.arraycopy(digest, 0, array, start, digest.length);
    return digest.length;
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}

