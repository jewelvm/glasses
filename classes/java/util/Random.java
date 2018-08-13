/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.Serializable;

public class Random implements Serializable {

  private static final long serialVersionUID = 3905348978240129619L;

  private long seed;
  private double nextNextGaussian;
  private boolean haveNextNextGaussian;

  public Random() {
    this(System.currentTimeMillis());
  }

  public Random(long seed) {
    setSeed(seed);
  }

  protected synchronized int next(int bits) {
    seed = (seed*0x5DEECE66DL+0xBL)&((1L<<48)-1);
    return (int)(seed>>>(48-bits));
  }

  public synchronized void setSeed(long seed) {
    this.seed = (seed^0x5DEECE66DL)&((1L<<48)-1);
    haveNextNextGaussian = false;
  }

  public int nextInt(int limit) {
    if (limit <= 0)
      throw new IllegalArgumentException("Invalid limit");
    if ((limit & -limit) == limit)
      return (int)((limit*(long)next(31))>>31);
    int bits, val;
    do {
      bits = next(31);
      val = bits % limit;
    } while(bits-val+(limit-1) < 0);
    return val;
  }

  public boolean nextBoolean() {
    return next(1) != 0;
  }

  public int nextInt() {
    return next(32);
  }

  public long nextLong() {
    return ((long)next(32)<<32)+next(32);
  }

  public float nextFloat() {
    return next(24)/(float)(1<<24);
  }

  public double nextDouble() {
    return (((long)next(26)<<27)+next(27))/(double)(1L<<53);
  }

  public void nextBytes(byte[] array) {
    int next = 0;
    for (int i = 0; i < array.length; i++) {
      if (i % 4 == 0)
        next = nextInt();
      array[i] = (byte)next;
      next >>= 8;
    }
  }

  public synchronized double nextGaussian() {
    if (haveNextNextGaussian) {
      haveNextNextGaussian = false;
      return nextNextGaussian;
    } else {
      double v1, v2, s;
      do { 
        v1 = 2*nextDouble()-1;
        v2 = 2*nextDouble()-1;
        s = v1*v1 + v2*v2;
      } while (s >= 1 || s == 0);
      double multiplier = Math.sqrt(-2*Math.log(s)/s);
      nextNextGaussian = v2*multiplier;
      haveNextNextGaussian = true;
      return v1*multiplier;
    }
  }

}

