/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public final class Long extends Number implements Comparable {
  
  private static final long serialVersionUID = 4290774380558885855L;

  public static final Class TYPE  = Class.getPrimitiveClass("long");

  public static final long MIN_VALUE = 0x8000000000000000L;
  public static final long MAX_VALUE = 0x7FFFFFFFFFFFFFFFL;

  public static String toString(long lung) {
    return toString(lung, 10);
  }

  public static String toString(long lung, int radix) {
    if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
      radix = 10;
    StringBuffer sb = new StringBuffer(65);
    boolean sign = true;
    if (lung >= 0) {
      lung = -lung;
      sign = false;
    }
    do {
      int digit = -(int)(lung%radix);
      sb.append(Character.forDigit(digit, radix));
      lung /= radix;
    } while (lung < 0);
    if (sign)
      sb.append('-');
    return sb.reverse().toString();
  }
  
  public static String toBinaryString(long lung) {
    StringBuffer sb = new StringBuffer(64);
    do {
      int digit = (int)lung & 0x00000001;
      sb.append(Character.forDigit(digit, 2));
      lung >>>= 1;
    } while (lung != 0);
    return sb.reverse().toString();
  }

  public static String toOctalString(long lung) {
    StringBuffer sb = new StringBuffer(22);
    do {
      int digit = (int)lung & 0x00000007;
      sb.append(Character.forDigit(digit, 8));
      lung >>>= 3;
    } while (lung != 0);
    return sb.reverse().toString();
  }

  public static String toHexString(long lung) {
    StringBuffer sb = new StringBuffer(16);
    do {
      int digit = (int)lung & 0x0000000F;
      sb.append(Character.forDigit(digit, 16));
      lung >>>= 4;
    } while (lung != 0);
    return sb.reverse().toString();
  }

  public static Long valueOf(String string) throws NumberFormatException {
    return valueOf(string, 10);
  }

  public static Long valueOf(String string, int radix) throws NumberFormatException {
    return new Long(parseLong(string, radix));
  }

  public static Long getLong(String name) {
    return getLong(name, null);
  }

  public static Long getLong(String name, long value) {
    return getLong(name, new Long(value));
  }

  public static Long getLong(String name, Long value) {
    if (name != null && name.length() > 0) {
      String string = System.getProperty(name);
      if (string != null)
        try {
          return decode(string);
        } catch (NumberFormatException e) { }
    }
    return value;
  }
  
  public static long parseLong(String string) throws NumberFormatException {
    return parseLong(string, 10);
  }

  public static long parseLong(String string, int radix) throws NumberFormatException {
    if (string == null)
      throw new NumberFormatException("Null string");
    if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
      throw new NumberFormatException("Invalid radix");
    int length = string.length();
    if (length == 0)
      throw new NumberFormatException("Zero length");
    int start = 0;
    if (string.charAt(0) == '-') {
      start = 1;
      if (length == 1)
        throw new NumberFormatException(string);
    }
    long value = 0;
    for (int i = start; i < length; i++) {
      int digit = Character.digit(string.charAt(i), radix);
      if (digit == -1)
        throw new NumberFormatException(string);
      long last = value;
      value = radix*last-digit;
      if (value > last)
        throw new NumberFormatException(string);
    }
    if (start == 0) {
      value = -value;
      if (value < 0)
        throw new NumberFormatException(string);
    }
    return value;
  }

  public static Long decode(String string) throws NumberFormatException {
    if (string.startsWith("#-"))
      throw new NumberFormatException(string);
    if (string.startsWith("#"))
      return valueOf(string.substring(1), 16);
    if (string.startsWith("-#"))
      return valueOf("-"+string.substring(2), 16);
    if (string.startsWith("0x-") || string.startsWith("0X-"))
      throw new NumberFormatException(string);
    if (string.startsWith("0x") || string.startsWith("0X"))
      return valueOf(string.substring(2), 16);
    if (string.startsWith("-0x") || string.startsWith("-0X"))
      return valueOf("-"+string.substring(3), 16);
    if (string.startsWith("0") || string.startsWith("-0"))
      return valueOf(string, 8);
    return valueOf(string);
  }

  private final long value;

  public Long(long value) {
    this.value = value;
  }

  public Long(String string) throws NumberFormatException {
    value = parseLong(string);
  }

  public int intValue() {
    return (int)value;
  }

  public long longValue() {
    return value;
  }

  public float floatValue() {
    return value;
  }

  public double doubleValue() {
    return value;
  }

  public int compareTo(Long lung) {
    return value < lung.value ? -1 : value > lung.value ? 1 : 0;
  }

  public int compareTo(Object object) {
    return compareTo((Long)object);
  }

  public int hashCode() {
    return (int)value ^ (int)(value >> 32);
  }

  public boolean equals(Object object) {
    return object instanceof Long
        && ((Long)object).value == value;
  }

  public String toString() {
    return toString(value);
  }

}

