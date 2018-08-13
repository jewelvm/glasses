/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

public final class Array {

  public static Object newInstance(Class elementType, int length) {
    if (elementType.getDimensions()+1 > 255)
      throw new IllegalArgumentException("Too many dimensions");
    if (length < 0)
      throw new NegativeArraySizeException();
    if (elementType == Void.TYPE)
      throw new IllegalArgumentException("Void is not a legal element type");
    if (elementType == Boolean.TYPE) { return new boolean[length]; }
    if (elementType == Character.TYPE) { return new char[length]; }
    if (elementType == Byte.TYPE) { return new byte[length]; }
    if (elementType == Short.TYPE) { return new short[length]; }
    if (elementType == Integer.TYPE) { return new int[length]; }
    if (elementType == Long.TYPE) { return new long[length]; }
    if (elementType == Float.TYPE) { return new float[length]; }
    if (elementType == Double.TYPE) { return new double[length]; }
    return reflect.newArray(elementType, length);
  }

  public static Object newInstance(Class elementType, int[] dimensions) {
    if (dimensions.length == 0)
      throw new IllegalArgumentException("Zero dimensions");
    if (elementType.getDimensions()+dimensions.length > 255)
      throw new IllegalArgumentException("Too many dimensions");
    for (int i = 0; i < dimensions.length; i++)
      if (dimensions[i] < 0)
        throw new NegativeArraySizeException();
    return newInstance(elementType, dimensions, 0);
  }
  
  private static Object newInstance(Class elementType, int[] dimensions, int start) {
    Object array = newInstance(elementType, dimensions[dimensions.length-1]);
    for (int i = dimensions.length-2; i >= start; i--) {
      Object[] container = (Object[])newInstance(array.getClass(), dimensions[i]);
      if (container.length > 0) {
        container[0] = array;
        for (int j = 1; j < container.length; j++)
          container[j] = newInstance(elementType, dimensions, i+1);
      }
      array = container;
    }
    return array;
  }

  public static int getLength(Object array) {
    if (array == null) throw new NullPointerException();
    if (array instanceof Object[]) return ((Object[])array).length;
    if (array instanceof boolean[]) return ((boolean[])array).length;
    if (array instanceof char[]) return ((char[])array).length;
    if (array instanceof byte[]) return ((byte[])array).length;
    if (array instanceof short[]) return ((short[])array).length;
    if (array instanceof int[]) return ((int[])array).length;
    if (array instanceof long[]) return ((long[])array).length;
    if (array instanceof float[]) return ((float[])array).length;
    if (array instanceof double[]) return ((double[])array).length;
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static Object get(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof Object[]) return ((Object[])array)[index];
    if (array instanceof boolean[]) return new Boolean(((boolean[])array)[index]);
    if (array instanceof char[]) return new Character(((char[])array)[index]);
    if (array instanceof byte[]) return new Byte(((byte[])array)[index]);
    if (array instanceof short[]) return new Short(((short[])array)[index]);
    if (array instanceof int[]) return new Integer(((int[])array)[index]);
    if (array instanceof long[]) return new Long(((long[])array)[index]);
    if (array instanceof float[]) return new Float(((float[])array)[index]);
    if (array instanceof double[]) return new Double(((double[])array)[index]);
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static boolean getBoolean(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof boolean[]) return ((boolean[])array)[index];
    if (array instanceof Object[] || array instanceof char[] ||
        array instanceof byte[] || array instanceof short[] ||
        array instanceof int[] || array instanceof long[] ||
        array instanceof float[] || array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static char getChar(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof char[]) return ((char[])array)[index];
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof byte[] || array instanceof short[] ||
        array instanceof int[] || array instanceof long[] ||
        array instanceof float[] || array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static byte getByte(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof byte[]) return ((byte[])array)[index];
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[] || array instanceof short[] ||
        array instanceof int[] || array instanceof long[] ||
        array instanceof float[] || array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static short getShort(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof byte[]) return ((byte[])array)[index];
    if (array instanceof short[]) return ((short[])array)[index];
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[] || array instanceof int[] ||
        array instanceof long[] || array instanceof float[] ||
        array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static int getInt(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof char[]) return ((char[])array)[index];
    if (array instanceof byte[]) return ((byte[])array)[index];
    if (array instanceof short[]) return ((short[])array)[index];
    if (array instanceof int[]) return ((int[])array)[index];
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof long[] || array instanceof float[] ||
        array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static long getLong(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof char[]) return ((char[])array)[index];
    if (array instanceof byte[]) return ((byte[])array)[index];
    if (array instanceof short[]) return ((short[])array)[index];
    if (array instanceof int[]) return ((int[])array)[index];
    if (array instanceof long[]) return ((long[])array)[index];
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof float[] || array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static float getFloat(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof char[]) return ((char[])array)[index];
    if (array instanceof byte[]) return ((byte[])array)[index];
    if (array instanceof short[]) return ((short[])array)[index];
    if (array instanceof int[]) return ((int[])array)[index];
    if (array instanceof long[]) return ((long[])array)[index];
    if (array instanceof float[]) return ((float[])array)[index];
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static double getDouble(Object array, int index) {
    if (array == null) throw new NullPointerException();
    if (array instanceof char[]) return ((char[])array)[index];
    if (array instanceof byte[]) return ((byte[])array)[index];
    if (array instanceof short[]) return ((short[])array)[index];
    if (array instanceof int[]) return ((int[])array)[index];
    if (array instanceof long[]) return ((long[])array)[index];
    if (array instanceof float[]) return ((float[])array)[index];
    if (array instanceof double[]) return ((double[])array)[index];
    if (array instanceof Object[] || array instanceof boolean[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void set(Object array, int index, Object value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof Object[]) { ((Object[])array)[index] = value; return; }
    if (array instanceof boolean[]) {
      if (value instanceof Boolean) { ((boolean[])array)[index] = ((Boolean)value).booleanValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    if (array instanceof char[]) {
      if (value instanceof Character) { ((char[])array)[index] = ((Character)value).charValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    if (array instanceof byte[]) {
      if (value instanceof Byte) { ((byte[])array)[index] = ((Byte)value).byteValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    if (array instanceof short[]) {
      if (value instanceof Byte) { ((short[])array)[index] = ((Byte)value).byteValue(); return; }
      if (value instanceof Short) { ((short[])array)[index] = ((Short)value).shortValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    if (array instanceof int[]) {
      if (value instanceof Character) { ((int[])array)[index] = ((Character)value).charValue(); return; }
      if (value instanceof Byte) { ((int[])array)[index] = ((Byte)value).byteValue(); return; }
      if (value instanceof Short) { ((int[])array)[index] = ((Short)value).shortValue(); return; }
      if (value instanceof Integer) { ((int[])array)[index] = ((Integer)value).intValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    if (array instanceof long[]) {
      if (value instanceof Character) { ((long[])array)[index] = ((Character)value).charValue(); return; }
      if (value instanceof Byte) { ((long[])array)[index] = ((Byte)value).byteValue(); return; }
      if (value instanceof Short) { ((long[])array)[index] = ((Short)value).shortValue(); return; }
      if (value instanceof Integer) { ((long[])array)[index] = ((Integer)value).intValue(); return; }
      if (value instanceof Long) { ((long[])array)[index] = ((Long)value).longValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    if (array instanceof float[]) {
      if (value instanceof Character) { ((float[])array)[index] = ((Character)value).charValue(); return; }
      if (value instanceof Byte) { ((float[])array)[index] = ((Byte)value).byteValue(); return; }
      if (value instanceof Short) { ((float[])array)[index] = ((Short)value).shortValue(); return; }
      if (value instanceof Integer) { ((float[])array)[index] = ((Integer)value).intValue(); return; }
      if (value instanceof Long) { ((float[])array)[index] = ((Long)value).longValue(); return; }
      if (value instanceof Float) { ((float[])array)[index] = ((Float)value).floatValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    if (array instanceof double[]) {
      if (value instanceof Character) { ((double[])array)[index] = ((Character)value).charValue(); return; }
      if (value instanceof Byte) { ((double[])array)[index] = ((Byte)value).byteValue(); return; }
      if (value instanceof Short) { ((double[])array)[index] = ((Short)value).shortValue(); return; }
      if (value instanceof Integer) { ((double[])array)[index] = ((Integer)value).intValue(); return; }
      if (value instanceof Long) { ((double[])array)[index] = ((Long)value).longValue(); return; }
      if (value instanceof Float) { ((double[])array)[index] = ((Float)value).floatValue(); return; }
      if (value instanceof Double) { ((double[])array)[index] = ((Double)value).doubleValue(); return; }
      throw new IllegalArgumentException("Unwrapping conversion failed");
    }
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setBoolean(Object array, int index, boolean value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof boolean[]) { ((boolean[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof char[] ||
        array instanceof byte[] || array instanceof short[] ||
        array instanceof int[] || array instanceof long[] ||
        array instanceof float[] || array instanceof double[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setChar(Object array, int index, char value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof char[]) { ((char[])array)[index] = value; return; }
    if (array instanceof int[]) { ((int[])array)[index] = value; return; }
    if (array instanceof long[]) { ((long[])array)[index] = value; return; }
    if (array instanceof float[]) { ((float[])array)[index] = value; return; }
    if (array instanceof double[]) { ((double[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof byte[] || array instanceof short[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setByte(Object array, int index, byte value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof byte[]) { ((byte[])array)[index] = value; return; }
    if (array instanceof short[]) { ((short[])array)[index] = value; return; }
    if (array instanceof int[]) { ((int[])array)[index] = value; return; }
    if (array instanceof long[]) { ((long[])array)[index] = value; return; }
    if (array instanceof float[]) { ((float[])array)[index] = value; return; }
    if (array instanceof double[]) { ((double[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setShort(Object array, int index, short value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof short[]) { ((short[])array)[index] = value; return; }
    if (array instanceof int[]) { ((int[])array)[index] = value; return; }
    if (array instanceof long[]) { ((long[])array)[index] = value; return; }
    if (array instanceof float[]) { ((float[])array)[index] = value; return; }
    if (array instanceof double[]) { ((double[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[] || array instanceof byte[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setInt(Object array, int index, int value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof int[]) { ((int[])array)[index] = value; return; }
    if (array instanceof long[]) { ((long[])array)[index] = value; return; }
    if (array instanceof float[]) { ((float[])array)[index] = value; return; }
    if (array instanceof double[]) { ((double[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[] || array instanceof byte[] ||
        array instanceof short[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setLong(Object array, int index, long value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof long[]) { ((long[])array)[index] = value; return; }
    if (array instanceof float[]) { ((float[])array)[index] = value; return; }
    if (array instanceof double[]) { ((double[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[] || array instanceof byte[] ||
        array instanceof short[] || array instanceof int[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setFloat(Object array, int index, float value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof float[]) { ((float[])array)[index] = value; return; }
    if (array instanceof double[]) { ((double[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[] || array instanceof byte[] ||
        array instanceof short[] || array instanceof int[] ||
        array instanceof long[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  public static void setDouble(Object array, int index, double value) {
    if (array == null) throw new NullPointerException();
    if (array instanceof double[]) { ((double[])array)[index] = value; return; }
    if (array instanceof Object[] || array instanceof boolean[] ||
        array instanceof char[] || array instanceof byte[] ||
        array instanceof short[] || array instanceof int[] ||
        array instanceof long[] || array instanceof float[])
      throw new IllegalArgumentException("Array element type mismatch");
    throw new IllegalArgumentException("Argument is not an array");
  }

  private Array() { }

}

