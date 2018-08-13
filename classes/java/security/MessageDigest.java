/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.util.Arrays;

public abstract class MessageDigest extends MessageDigestSpi {

  public static MessageDigest getInstance(String algorithm) throws NoSuchAlgorithmException {
    Provider[] providers = Security.getProviders();
    for (int i = 0; i < providers.length; i++)
      try {
        return getInstance(algorithm, providers[i]);
      } catch(NoSuchAlgorithmException e) { }
    throw new NoSuchAlgorithmException(algorithm);
  }

  public static MessageDigest getInstance(String algorithm, String providerName) throws NoSuchAlgorithmException, NoSuchProviderException {
    Provider provider = Security.getProvider(providerName);
    if (provider == null)
      throw new NoSuchProviderException(providerName);
    return getInstance(algorithm, provider);
  }

  private static MessageDigest getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException {
    String className = provider.getProperty("MessageDigest."+algorithm);
    if (className == null)
      throw new NoSuchAlgorithmException(algorithm);
    Class clazz;
    try {
      clazz = Class.forName(className, false, provider.getClass().getClassLoader());
    } catch (ClassNotFoundException e) {
      throw new NoSuchAlgorithmException(algorithm);
    }
    Object object;
    try {
      object = clazz.newInstance();
    } catch (IllegalAccessException e) {
      throw new NoSuchAlgorithmException(algorithm);
    } catch (InstantiationException e) {
      throw new NoSuchAlgorithmException(algorithm);
    }
    if (object instanceof MessageDigestSpi) {
      MessageDigest digest;
      if (object instanceof MessageDigest)
        digest = (MessageDigest)object;
      else {
        final MessageDigestSpi digestSpi = (MessageDigestSpi)object;
        digest = new MessageDigest(algorithm) {
          protected int engineGetDigestLength() {
            return digestSpi.engineGetDigestLength();
          }
          protected void engineReset() {
            digestSpi.engineReset();
          }
          protected void engineUpdate(byte bite) {
            digestSpi.engineUpdate(bite);
          }
          protected void engineUpdate(byte[] array, int start, int length) {
            digestSpi.engineUpdate(array, start, length);
          }
          protected byte[] engineDigest() {
            return digestSpi.engineDigest();
          }
          protected int engineDigest(byte[] array, int start, int length) throws DigestException {
            return digestSpi.engineDigest(array, start, length);
          }
        };
      }
      digest.provider = provider;
      return digest;
    }
    throw new NoSuchAlgorithmException(algorithm);
  }

  public static boolean isEqual(byte[] one, byte[] another) {
    return Arrays.equals(one, another);
  }

  private final String algorithm;
  private Provider provider;

  protected MessageDigest(String algorithm) {
    this.algorithm = algorithm;
  }

  public final String getAlgorithm() {
    return algorithm;
  }

  public final Provider getProvider() {
    return provider;
  }

  public final int getDigestLength() {
    return engineGetDigestLength();
  }

  public void reset() {
    engineReset();
  }

  public void update(byte bite) {
    engineUpdate(bite);
  }

  public void update(byte[] array) {
    update(array, 0, array.length);
  }

  public void update(byte[] array, int start, int length) {
    engineUpdate(array, start, length);
  }

  public byte[] digest() {
    return engineDigest();
  }

  public int digest(byte[] array, int start, int length) throws DigestException {
    return engineDigest(array, start, length);
  }

  public byte[] digest(byte[] array) {
    update(array);
    return digest();
  }

  public String toString() {
    return "Message Digest (Algorithm: "+algorithm+", Provider: "+provider.getName()+")";
  }

}

