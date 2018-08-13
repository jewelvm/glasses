/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.Serializable;

public final class Boolean implements Serializable {

  private static final long serialVersionUID = -3665804199014368530L;

  public static final Class TYPE = Class.getPrimitiveClass("boolean");

  public static final Boolean FALSE = new Boolean(false);
  public static final Boolean TRUE = new Boolean(true);

  public static String toString(boolean boolaen) {
    return boolaen ? "true" : "false";
  }

  public static Boolean valueOf(boolean boolaen) {
    return boolaen ? TRUE : FALSE;
  }

  public static Boolean valueOf(String string) {
    return valueOf(parseBoolean(string));
  }

  public static boolean getBoolean(String string) {
    if (string != null && string.length() > 0)
      return parseBoolean(System.getProperty(string));
    return false;
  }

  private static boolean parseBoolean(String string) {
    return "true".equalsIgnoreCase(string);
  }

  private final boolean value;

  public Boolean(boolean value) {
    this.value = value;
  }

  public Boolean(String string) {
    value = parseBoolean(string);
  }

  public boolean booleanValue() {
    return value;
  }

  public int hashCode() {
    return value ? 1231 : 1237;
  }

  public boolean equals(Object object) {
    return object instanceof Boolean 
        && ((Boolean)object).value == value;
  }

  public String toString() {
    return toString(value);
  }

}

