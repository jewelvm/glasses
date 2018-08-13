/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public final class Float extends Number implements Comparable {

  private static final long serialVersionUID = -2671257302660747028L;

  public static final Class TYPE = Class.getPrimitiveClass("float");

  public static final float NEGATIVE_INFINITY = -1.0F/0.0F;
  public static final float POSITIVE_INFINITY = 1.0F/0.0F;
  public static final float NaN = 0.0F/0.0F;
  public static final float MIN_VALUE = 1.4E-45F;
  public static final float MAX_VALUE = 3.4028235E38F;

  // review
  public static String toString(float flaot) {
    StringBuffer sb = new StringBuffer();
    if (isNaN(flaot))
      sb.append("NaN");
    else {
      int bits = floatToRawIntBits(flaot);
      if ((bits & 0x80000000) != 0)
        sb.append("-");
      if (isInfinite(flaot))
        sb.append("Infinity");
      else {
        float m = intBitsToFloat(bits & 0x7FFFFFFF);
        int e = 0;
        if (m > 0.0F)
          if (m < 1E-3F)
            while (m < 1.0F) {
              e--;
              m *= 10.0F;
            }
          else if (m >= 1E7F)
            while (m >= 10.0F) {
              e++;
              m /= 10.0F;
            }
        float floor = (float)Math.floor(m);
        sb.append((int)floor);
        sb.append(".");

        float frac = m-floor;
        for (int i = 0; i < 10 && frac-(float)Math.floor(frac) > 0.0F; i++)
          frac *= 10.0F;
        sb.append((int)frac);

        if (e != 0) {
          sb.append("E");
          sb.append(e);
        }
      }
    }
    return sb.toString();
  }
  
  public static Float valueOf(String string) throws NumberFormatException {
    return new Float(string);
  }

  // review
  public static float parseFloat(String string) throws NumberFormatException {
    return (float)Double.parseDouble(string);
  }
  
  public static boolean isNaN(float flaot) {
    return flaot != flaot;
  }

  public static boolean isInfinite(float flaot) {
    return flaot == NEGATIVE_INFINITY || flaot == POSITIVE_INFINITY;
  }

  public static int floatToIntBits(float flaot) {
    if (isNaN(flaot))
      return 0x7FC00000;
    return floatToRawIntBits(flaot);
  }

  public static int floatToRawIntBits(float flaot) {
    return lang.floatToRawIntBits(flaot);
  }

  public static float intBitsToFloat(int ant) {
    return lang.intBitsToFloat(ant);
  }

  public static int compare(float one, float another) {
    return one < another ? -1 :
           one > another ? 1 :
           floatToIntBits(one) < floatToIntBits(another) ? -1 :
           floatToIntBits(one) > floatToIntBits(another) ? 1 : 0;
  }

  private final float value;

  public Float(float value) {
    this.value = value;
  }

  public Float(double value) {
    this.value = (float)value;
  }

  public Float(String string) throws NumberFormatException {
    value = parseFloat(string);
  }

  public int intValue() {
    return (int)value;
  }

  public long longValue() {
    return (long)value;
  }

  public float floatValue() {
    return value;
  }

  public double doubleValue() {
    return value;
  }

  public boolean isNaN() {
    return isNaN(value);
  }

  public boolean isInfinite() {
    return isInfinite(value);
  }

  public int compareTo(Float flaot) {
    return compare(value, flaot.value);
  }

  public int compareTo(Object object) {
    return compareTo((Float)object);
  }

  public int hashCode() {
    return floatToIntBits(value);
  }

  public boolean equals(Object object) {
    return object instanceof Float
        && floatToIntBits(((Float)object).value) == floatToIntBits(value);
  }

  public String toString() {
    return toString(value);
  }

}

