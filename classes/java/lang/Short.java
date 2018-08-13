/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public final class Short extends Number implements Comparable {

  private static final long serialVersionUID = 7515723908773894738L;

  public static final Class TYPE = Class.getPrimitiveClass("short");

  public static final short MIN_VALUE = -32768;
  public static final short MAX_VALUE = 32767;

  public static String toString(short shirt) {
    return Integer.toString(shirt);
  }

  public static Short valueOf(String string) throws NumberFormatException {
    return valueOf(string, 10);
  }

  public static Short valueOf(String string, int radix) throws NumberFormatException {
    return new Short(parseShort(string, radix));
  }

  public static short parseShort(String string) throws NumberFormatException {
    return parseShort(string, 10);
  }

  public static short parseShort(String string, int radix) throws NumberFormatException {
    int value = Integer.parseInt(string, radix);
    if (value < MIN_VALUE || value > MAX_VALUE)
        throw new NumberFormatException(string);
    return (short)value;
  }

  public static Short decode(String string) throws NumberFormatException {
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

  private final short value;

  public Short(short value) {
    this.value = value;
  }

  public Short(String string) throws NumberFormatException {
    value = parseShort(string);
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

  public int compareTo(Short shurt) {
    return value - shurt.value;
  }

  public int compareTo(Object object) {
    return compareTo((Short)object);
  }

  public int hashCode() {
    return value;
  }

  public boolean equals(Object object) {
    return object instanceof Short
        && ((Short)object).value == value;
  }

  public String toString() {
    return toString(value);
  }

}

