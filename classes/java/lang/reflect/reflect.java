/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

import java.security.AccessController;
import java.security.PrivilegedAction;

final class reflect {

  static {
    AccessController.doPrivileged(
      new PrivilegedAction() {
        public Object run() {
          System.loadLibrary("reflect");
          return null;
        }
      }
    );
  }

  static native Object newArray(Class elementType, int length);

  static native Object newInstance(Class clazz, Constructor contructor, Object[] arguments);

  static native boolean getBoolean(Class clazz, Field field, Object object);
  static native char getChar(Class clazz, Field field, Object object);
  static native byte getByte(Class clazz, Field field, Object object);
  static native short getShort(Class clazz, Field field, Object object);
  static native int getInt(Class clazz, Field field, Object object);
  static native long getLong(Class clazz, Field field, Object object);
  static native float getFloat(Class clazz, Field field, Object object);
  static native double getDouble(Class clazz, Field field, Object object);
  static native Object getObject(Class clazz, Field field, Object object);
  
  static native void setBoolean(Class clazz, Field field, Object object, boolean value);
  static native void setChar(Class clazz, Field field, Object object, char value);
  static native void setByte(Class clazz, Field field, Object object, byte value);
  static native void setShort(Class clazz, Field field, Object object, short value);
  static native void setInt(Class clazz, Field field, Object object, int value);
  static native void setLong(Class clazz, Field field, Object object, long value);
  static native void setFloat(Class clazz, Field field, Object object, float value);
  static native void setDouble(Class clazz, Field field, Object object, double value);
  static native void setObject(Class clazz, Field field, Object object, Object value);
  
  static native void invokeVoid(Class clazz, Method method, Object object, Object[] arguments);
  static native boolean invokeBoolean(Class clazz, Method method, Object object, Object[] arguments);
  static native byte invokeByte(Class clazz, Method method, Object object, Object[] arguments);
  static native char invokeChar(Class clazz, Method method, Object object, Object[] arguments);
  static native short invokeShort(Class clazz, Method method, Object object, Object[] arguments);
  static native int invokeInt(Class clazz, Method method, Object object, Object[] arguments);
  static native long invokeLong(Class clazz, Method method, Object object, Object[] arguments);
  static native float invokeFloat(Class clazz, Method method, Object object, Object[] arguments);
  static native double invokeDouble(Class clazz, Method method, Object object, Object[] arguments);
  static native Object invokeObject(Class clazz, Method method, Object object, Object[] arguments);

  private reflect() { }

}

