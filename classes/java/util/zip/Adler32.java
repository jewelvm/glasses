/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

public class Adler32 implements Checksum {

  // NMAX = k+1 | max(65520+k*255,sum(i,0,k,65520+i*255)) < 2^31
  private static final int NMAX = 3855; 
  private static final int BASE = 65521;

  private int value = 1;

  public Adler32() { }
    
  public void reset() {
    value = 1;
  }

  public long getValue() {
    return (long)value & 0xFFFFFFFFL;
  }

  public void update(int bite) {
    int loword = value & 0xFFFF;
    int hiword = value >>> 16;
    loword += bite & 0xFF;
    hiword += loword;
    loword %= BASE;
    hiword %= BASE;
    value = hiword << 16 | loword;
  }

  public void update(byte[] buffer) {
    update(buffer, 0, buffer.length);
  }

  public void update(byte[] buffer, int start, int length) {
    if (start < 0 || start > buffer.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > buffer.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int loword = value & 0xFFFF;
    int hiword = value >>> 16;
    for (int i = start; i < end; ) {
      int limit = end-i < NMAX ? end : i+NMAX;
      for ( ; i < limit; i++) {
        loword += buffer[i] & 0xFF;
        hiword += loword;
      }
      loword %= BASE;
      hiword %= BASE;
    }
    value = hiword << 16 | loword;
  }

}

