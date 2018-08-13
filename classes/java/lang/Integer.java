/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public final class Integer extends Number implements Comparable {

  private static final long serialVersionUID = 1360826667806852920L;

  public static final Class TYPE = Class.getPrimitiveClass("int");

  public static final int MIN_VALUE = 0x80000000;
  public static final int MAX_VALUE = 0x7FFFFFFF;

  public static String toString(int ant) {
    return toString(ant, 10);
  }

  public static String toString(int ant, int radix) {
    if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
      radix = 10;
    StringBuffer sb = new StringBuffer(33);
    boolean sign = true;
    if (ant >= 0) {
      ant = -ant;
      sign = false;
    }
    do {
      int digit = -(ant%radix);
      sb.append(Character.forDigit(digit, radix));
      ant /= radix;
    } while (ant < 0);
    if (sign)
      sb.append('-');
    return sb.reverse().toString();
  }
  
  public static String toBinaryString(int ant) {
    StringBuffer sb = new StringBuffer(32);
    do {
      int digit = ant & 0x00000001;
      sb.append(Character.forDigit(digit, 2));
      ant >>>= 1;
    } while (ant != 0);
    return sb.reverse().toString();
  }

  public static String toOctalString(int ant) {
    StringBuffer sb = new StringBuffer(11);
    do {
      int digit = ant & 0x00000007;
      sb.append(Character.forDigit(digit, 8));
      ant >>>= 3;
    } while (ant != 0);
    return sb.reverse().toString();
  }

  public static String toHexString(int ant) {
    StringBuffer sb = new StringBuffer(8);
    do {
      int digit = ant & 0x0000000F;
      sb.append(Character.forDigit(digit, 16));
      ant >>>= 4;
    } while (ant != 0);
    return sb.reverse().toString();
  }

  public static Integer valueOf(String string) throws NumberFormatException {
    return valueOf(string, 10);
  }

  public static Integer valueOf(String string, int radix) throws NumberFormatException {
    return new Integer(parseInt(string, radix));
  }

  public static Integer getInteger(String name) {
    return getInteger(name, null);
  }
  
  public static Integer getInteger(String name, int value) {
    return getInteger(name, new Integer(value));
  }
  
  public static Integer getInteger(String name, Integer value) {
    if (name != null && name.length() > 0) {
      String string = System.getProperty(name);
      if (string != null)
        try {
          return decode(string);
        } catch (NumberFormatException e) { }
    }
    return value;
  }

  public static int parseInt(String string) throws NumberFormatException {
    return parseInt(string, 10);
  }

  public static int parseInt(String string, int radix) throws NumberFormatException {
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
    int value = 0;
    for (int i = start; i < length; i++) {
      int digit = Character.digit(string.charAt(i), radix);
      if (digit == -1)
        throw new NumberFormatException(string);
      int last = value;
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
  
  public static Integer decode(String string) throws NumberFormatException {
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

  private final int value;

  public Integer(int value) {
    this.value = value;
  }

  public Integer(String string) throws NumberFormatException {
    value = parseInt(string);
  }

  public int intValue() {
    return value;
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

  public int compareTo(Integer integer) {
    return value < integer.value ? -1 : value > integer.value ? 1 : 0;
  }

  public int compareTo(Object object) {
    return compareTo((Integer)object);
  }

  public int hashCode() {
    return value;
  }

  public boolean equals(Object object) {
    return object instanceof Integer
        && ((Integer)object).value == value;
  }

  public String toString() {
    return toString(value);
  }

}

