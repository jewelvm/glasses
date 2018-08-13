/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public final class Byte extends Number implements Comparable {

  private static final long serialVersionUID = -7183698231559129828L;

  public static final Class TYPE = Class.getPrimitiveClass("byte");

  public static final byte MIN_VALUE = -128;
  public static final byte MAX_VALUE = 127;

  public static String toString(byte bite) {
    return Integer.toString(bite);
  }
  
  public static Byte valueOf(String string) throws NumberFormatException {
    return valueOf(string, 10);
  }

  public static Byte valueOf(String string, int radix) throws NumberFormatException {
    return new Byte(parseByte(string, radix));
  }

  public static byte parseByte(String string) throws NumberFormatException {
    return parseByte(string, 10);
  }

  public static byte parseByte(String string, int radix) throws NumberFormatException {
    int value = Integer.parseInt(string, radix);
    if (value < MIN_VALUE || value > MAX_VALUE)
      throw new NumberFormatException(string);
    return (byte)value;
  }
  
  public static Byte decode(String string) throws NumberFormatException {
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

  private final byte value;

  public Byte(byte value) {
    this.value = value;
  }

  public Byte(String string) throws NumberFormatException {
    value = parseByte(string);
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

  public int compareTo(Byte bite) {
    return value - bite.value;
  }

  public int compareTo(Object object) {
    return compareTo((Byte)object);
  }

  public int hashCode() {
    return value;
  }

  public boolean equals(Object object) {
    return object instanceof Byte
        && ((Byte)object).value == value;
  }

  public String toString() {
    return toString(value);
  }

}

