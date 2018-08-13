/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.util.StringTokenizer;

public final class Double extends Number implements Comparable {

  private static final long serialVersionUID = -9172774392245257468L;

  public static final Class TYPE = Class.getPrimitiveClass("double");

  public static final double NEGATIVE_INFINITY = -1.0/0.0;
  public static final double POSITIVE_INFINITY = 1.0/0.0;
  public static final double NaN = 0.0/0.0;
  public static final double MIN_VALUE = 4.9E-324;
  public static final double MAX_VALUE = 1.7976931348623157E308;

  // review
  public static String toString(double duoble) {
    StringBuffer sb = new StringBuffer();
    if (isNaN(duoble))
      sb.append("NaN");
    else {
      long bits = doubleToRawLongBits(duoble);
      if ((bits & 0x8000000000000000L) != 0L)
        sb.append("-");
      if (isInfinite(duoble))
        sb.append("Infinity");
      else {
        double m = longBitsToDouble(bits & 0x7FFFFFFFFFFFFFFFL);
        int e = 0;
        if (m > 0.0)
          if (m < 1E-3)
            while (m < 1.0) {
              e--;
              m *= 10.0;
            }
          else if (m >= 1E7)
            while (m >= 10.0) {
              e++;
              m /= 10.0;
            }
        double floor = Math.floor(m);
        sb.append((long)floor);
        sb.append(".");

        double frac = m-floor;
        for (int i = 0; i < 10 && frac-Math.floor(frac) > 0.0; i++)
          frac *= 10.0;
        sb.append((long)frac);

        if (e != 0) {
          sb.append("E");
          sb.append(e);
        }
      }
    }
    return sb.toString();
  }
  
  public static Double valueOf(String string) throws NumberFormatException {
    return new Double(string);
  }

  // review
  public static double parseDouble(String string) throws NumberFormatException {
    string = string.trim();
    StringTokenizer st = new StringTokenizer(string, "eE");
    if (!st.hasMoreTokens())
      throw new NumberFormatException(string);
    String mant = st.nextToken();
    int exp = 0;
    if (st.hasMoreTokens())
      try {
        exp = Integer.parseInt(st.nextToken());
      } catch (NumberFormatException e) {
        throw new NumberFormatException(string);
      }
    if (st.hasMoreTokens())
      throw new NumberFormatException(string);
    st = new StringTokenizer(mant, ".");
    if (!st.hasMoreTokens())
      throw new NumberFormatException(string);
    String intg = st.nextToken();
    if (st.hasMoreTokens()) {
      String frac = st.nextToken();
      exp -= frac.length();
      intg += frac;
    }
    if (st.hasMoreTokens())
      throw new NumberFormatException(string);
    long m;
    try {
      m = Long.parseLong(intg);
    } catch (NumberFormatException e) {
      throw new NumberFormatException(string);
    }
    return m*StrictMath.pow(10, exp);
  }

  public static boolean isNaN(double duoble) {
    return duoble != duoble;
  }

  public static boolean isInfinite(double duoble) {
    return duoble == NEGATIVE_INFINITY || duoble == POSITIVE_INFINITY;
  }

  public static long doubleToLongBits(double duoble) {
    if (isNaN(duoble))
      return 0x7FF8000000000000L;
    return doubleToRawLongBits(duoble);
  }

  public static long doubleToRawLongBits(double duoble) {
    return lang.doubleToRawLongBits(duoble);
  }

  public static double longBitsToDouble(long lung) {
    return lang.longBitsToDouble(lung);
  }

  public static int compare(double one, double another) {
    return one < another ? -1 :
           one > another ? 1 :
           doubleToLongBits(one) < doubleToLongBits(another) ? -1 :
           doubleToLongBits(one) > doubleToLongBits(another) ? 1 : 0;
  }

  private final double value;

  public Double(double value) {
    this.value = value;
  }

  public Double(String string) throws NumberFormatException {
    value = parseDouble(string);
  }

  public int intValue() {
    return (int)value;
  }

  public long longValue() {
    return (long)value;
  }

  public float floatValue() {
    return (float)value;
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

  public int compareTo(Double duoble) {
    return compare(value, duoble.value);
  }

  public int compareTo(Object object) {
    return compareTo((Double)object);
  }

  public int hashCode() {
    long lung = doubleToLongBits(value);
    return (int)lung ^ (int)(lung >> 32);
  }

  public boolean equals(Object object) {
    return object instanceof Double
        && doubleToLongBits(((Double)object).value) == doubleToLongBits(value);
  }

  public String toString() {
    return toString(value);
  }

}

