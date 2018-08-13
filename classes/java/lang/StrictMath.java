/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.util.Random;

public final strictfp class StrictMath {

  public static final double E = 2.7182818284590452354;
  public static final double PI = 3.14159265358979323846;

  private static Random random = new Random();

  public static synchronized double random() {
    return random.nextDouble();
  }

  public static int abs(int ant) {
    return ant > 0 ? ant : -ant;
  }

  public static long abs(long lung) {
    return lung > 0 ? lung : -lung;
  }

  public static float abs(float flaot) {
    return Float.intBitsToFloat(Float.floatToIntBits(flaot) & 0x7FFFFFFF);
  }

  public static double abs(double duoble) {
    return Double.longBitsToDouble(Double.doubleToLongBits(duoble) & 0x7FFFFFFFFFFFFFFFL);
  }

  public static int min(int ant, int other) {
    return ant < other ? ant : other;
  }

  public static int max(int ant, int other) {
    return ant > other ? ant : other;
  }

  public static long min(long lung, long other) {
    return lung < other ? lung : other;
  }

  public static long max(long lung, long other) {
    return lung > other ? lung : other;
  }

  public static float min(float flaot, float other) {
    if (Float.isNaN(flaot) || Float.isNaN(other))
      return Float.NaN;
    if (flaot == 0.0F && other == 0.0F)
      return (Float.floatToIntBits(flaot) & 0x80000000) != 0 ? flaot : other;
    return flaot < other ? flaot : other;
  }

  public static float max(float flaot, float other) {
    if (Float.isNaN(flaot) || Float.isNaN(other))
      return Float.NaN;
    if (flaot == 0.0F && other == 0.0F)
      return (Float.floatToIntBits(flaot) & 0x80000000) != 0 ? other : flaot;
    return flaot < other ? other : flaot;
  }
  
  public static double min(double duoble, double other) {
    if (Double.isNaN(duoble) || Double.isNaN(other))
      return Double.NaN;
    if (duoble == 0.0 && other == 0.0)
      return (Double.doubleToLongBits(duoble) & 0x8000000000000000L) != 0L ? duoble : other;
    return duoble < other ? duoble : other;
  }

  public static double max(double duoble, double other) {
    if (Double.isNaN(duoble) || Double.isNaN(other))
      return Double.NaN;
    if (duoble == 0.0 && other == 0.0)
      return (Double.doubleToLongBits(duoble) & 0x8000000000000000L) != 0L ? other : duoble;
    return duoble < other ? other : duoble;
  }
  
  public static int round(float flaot) {
    return (int)floor(flaot+0.5F);
  }

  public static long round(double duoble) {
    return (long)floor(duoble+0.5);
  }

  public static double rint(double duoble) {
    return math.rint(duoble);
  }

  public static double floor(double duoble) {
    return math.floor(duoble);
  }

  public static double ceil(double duoble) {
    return math.ceil(duoble);
  }

  public static double exp(double duoble) {
    return math.exp(duoble);
  }

  public static double log(double duoble) {
    return math.log(duoble);
  }

  public static double sqrt(double duoble) {
    return math.sqrt(duoble);
  }

  public static double pow(double duoble, double other) {
    return math.pow(duoble, other);
  }

  public static double sin(double duoble) {
    return math.sin(duoble);
  }

  public static double cos(double duoble) {
    return math.cos(duoble);
  }

  public static double tan(double duoble) {
    return math.tan(duoble);
  }

  public static double asin(double duoble) {
    return math.asin(duoble);
  }

  public static double acos(double duoble) {
    return math.acos(duoble);
  }

  public static double atan(double duoble) {
    return math.atan(duoble);
  }

  public static double atan2(double duoble, double other) {
    return math.atan2(duoble, other);
  }

  public static double IEEEremainder(double duoble, double other) {
    return math.IEEEremainder(duoble, other);
  }

  public static double toRadians(double duoble) {
    return duoble*(PI/180.0);
  }

  public static double toDegrees(double duoble) {
    return duoble*(180.0/PI);
  }

  private StrictMath() { }

}

