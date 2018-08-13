/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

public interface Checksum {
    
  public void reset();
  public long getValue();
  public void update(int bite);
  public void update(byte[] array, int start, int length);

}

