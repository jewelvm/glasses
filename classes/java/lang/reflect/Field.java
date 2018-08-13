/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

public final class Field extends AccessibleObject implements Member {

  private final Class declaringClass;
  private final char index;
  private final char modifiers;
  private final Class type;
  private final String name;

  private Field(Class declaringClass, char index, char modifiers, Class type, String name) {
    if (declaringClass == null)
      throw new NullPointerException();
    if (type == null)
      throw new NullPointerException();
    if (name == null)
      throw new NullPointerException();
    this.declaringClass = declaringClass;
    this.index = index;
    this.modifiers = modifiers;
    this.type = type;
    this.name = name;
  }

  public Class getDeclaringClass() {
    return declaringClass;
  }

  public int getIndex() {
    return index;
  }

  public int getModifiers() {
    return modifiers;
  }

  public Class getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public int hashCode() {
    return declaringClass.getName().hashCode() ^ name.hashCode();
  }

  public boolean equals(Object object) {
    if (object instanceof Field) {
      Field field = (Field)object;
      return declaringClass == field.declaringClass
          && type == field.type
          && name.equals(field.name);
    }
    return false;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer(60);
    if (modifiers != 0) {
      sb.append(Modifier.toString(modifiers));
      sb.append(' ');
    }
    sb.append(type.toTypeString());
    sb.append(' ');
    sb.append(declaringClass.toTypeString());
    sb.append('.');
    sb.append(name);
    return sb.toString();
  }

  private void checkReadAccess(Class caller) throws IllegalAccessException {
    if (isAccessible()) return;
    if (!Modifier.isPublic(declaringClass.getModifiers()))
      if (!caller.samePackageAs(declaringClass))
        throw new IllegalAccessException("Package private access denied");
    if (Modifier.isPublic(modifiers)) return;
    if (Modifier.isPrivate(modifiers)) {
      if (caller == declaringClass) return;
      throw new IllegalAccessException("Private access denied");
    }
    if (Modifier.isProtected(modifiers)) {
      if (declaringClass.isAssignableFrom(caller) || caller.samePackageAs(declaringClass)) return;
      throw new IllegalAccessException("Protected access denied");
    }
    if (caller.samePackageAs(declaringClass)) return;
    throw new IllegalAccessException("Package private access denied");
  }

  private void checkWriteAccess(Class caller) throws IllegalAccessException {
    if (isAccessible()) return;
    checkReadAccess(caller);
    if (Modifier.isFinal(modifiers)) {
      if (caller == declaringClass) return;
      throw new IllegalAccessException("Field is final");
    }
  }

  private Object prepareInstance(Object object) {
    if (Modifier.isStatic(modifiers)) {
      declaringClass.initialize();
      object = null;
    } else {
      if (object == null)
        throw new NullPointerException();
      if (!declaringClass.isInstance(object))
        throw new IllegalArgumentException("Object is not an instance of declaring class");
    }
    return object;
  }
  
  public Object get(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Boolean.TYPE) return new Boolean(reflect.getBoolean(declaringClass, this, object));
    if (type == Character.TYPE) return new Character(reflect.getChar(declaringClass, this, object));
    if (type == Byte.TYPE) return new Byte(reflect.getByte(declaringClass, this, object));
    if (type == Short.TYPE) return new Short(reflect.getShort(declaringClass, this, object));
    if (type == Integer.TYPE) return new Integer(reflect.getInt(declaringClass, this, object));
    if (type == Long.TYPE) return new Long(reflect.getLong(declaringClass, this, object));
    if (type == Float.TYPE) return new Float(reflect.getFloat(declaringClass, this, object));
    if (type == Double.TYPE) return new Double(reflect.getDouble(declaringClass, this, object));
    return reflect.getObject(declaringClass, this, object);
  }


  public boolean getBoolean(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Boolean.TYPE) return reflect.getBoolean(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public char getChar(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Character.TYPE) return reflect.getChar(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public byte getByte(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Byte.TYPE) return reflect.getByte(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public short getShort(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Byte.TYPE) return reflect.getByte(declaringClass, this, object);
    if (type == Short.TYPE) return reflect.getShort(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public int getInt(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Character.TYPE) return reflect.getChar(declaringClass, this, object);
    if (type == Byte.TYPE) return reflect.getByte(declaringClass, this, object);
    if (type == Short.TYPE) return reflect.getShort(declaringClass, this, object);
    if (type == Integer.TYPE) return reflect.getInt(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public long getLong(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Character.TYPE) return reflect.getChar(declaringClass, this, object);
    if (type == Byte.TYPE) return reflect.getByte(declaringClass, this, object);
    if (type == Short.TYPE) return reflect.getShort(declaringClass, this, object);
    if (type == Integer.TYPE) return reflect.getInt(declaringClass, this, object);
    if (type == Long.TYPE) return reflect.getLong(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public float getFloat(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Character.TYPE) return reflect.getChar(declaringClass, this, object);
    if (type == Byte.TYPE) return reflect.getByte(declaringClass, this, object);
    if (type == Short.TYPE) return reflect.getShort(declaringClass, this, object);
    if (type == Integer.TYPE) return reflect.getInt(declaringClass, this, object);
    if (type == Long.TYPE) return reflect.getLong(declaringClass, this, object);
    if (type == Float.TYPE) return reflect.getFloat(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public double getDouble(Object object) throws IllegalAccessException {
    checkReadAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Character.TYPE) return reflect.getChar(declaringClass, this, object);
    if (type == Byte.TYPE) return reflect.getByte(declaringClass, this, object);
    if (type == Short.TYPE) return reflect.getShort(declaringClass, this, object);
    if (type == Integer.TYPE) return reflect.getInt(declaringClass, this, object);
    if (type == Long.TYPE) return reflect.getLong(declaringClass, this, object);
    if (type == Float.TYPE) return reflect.getFloat(declaringClass, this, object);
    if (type == Double.TYPE) return reflect.getDouble(declaringClass, this, object);
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void set(Object object, Object value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Boolean.TYPE) {
      if (value instanceof Boolean) { reflect.setBoolean(declaringClass, this, object, ((Boolean)value).booleanValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (type == Character.TYPE) {
      if (value instanceof Character) { reflect.setChar(declaringClass, this, object, ((Character)value).charValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (type == Byte.TYPE) {
      if (value instanceof Byte) { reflect.setByte(declaringClass, this, object, ((Byte)value).byteValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (type == Short.TYPE) {
      if (value instanceof Byte) { reflect.setShort(declaringClass, this, object, ((Byte)value).byteValue()); return; }
      if (value instanceof Short) { reflect.setShort(declaringClass, this, object, ((Short)value).shortValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (type == Integer.TYPE) {
      if (value instanceof Byte) { reflect.setInt(declaringClass, this, object, ((Byte)value).byteValue()); return; }
      if (value instanceof Character) { reflect.setInt(declaringClass, this, object, ((Character)value).charValue()); return; }
      if (value instanceof Short) { reflect.setInt(declaringClass, this, object, ((Short)value).shortValue()); return; }
      if (value instanceof Integer) { reflect.setInt(declaringClass, this, object, ((Integer)value).intValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (type == Long.TYPE) {
      if (value instanceof Byte) { reflect.setLong(declaringClass, this, object, ((Byte)value).byteValue()); return; }
      if (value instanceof Character) { reflect.setLong(declaringClass, this, object, ((Character)value).charValue()); return; }
      if (value instanceof Short) { reflect.setLong(declaringClass, this, object, ((Short)value).shortValue()); return; }
      if (value instanceof Integer) { reflect.setLong(declaringClass, this, object, ((Integer)value).intValue()); return; }
      if (value instanceof Long) { reflect.setLong(declaringClass, this, object, ((Long)value).longValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (type == Float.TYPE) {
      if (value instanceof Byte) { reflect.setFloat(declaringClass, this, object, ((Byte)value).byteValue()); return; }
      if (value instanceof Character) { reflect.setFloat(declaringClass, this, object, ((Character)value).charValue()); return; }
      if (value instanceof Short) { reflect.setFloat(declaringClass, this, object, ((Short)value).shortValue()); return; }
      if (value instanceof Integer) { reflect.setFloat(declaringClass, this, object, ((Integer)value).intValue()); return; }
      if (value instanceof Long) { reflect.setFloat(declaringClass, this, object, ((Long)value).longValue()); return; }
      if (value instanceof Float) { reflect.setFloat(declaringClass, this, object, ((Float)value).floatValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (type == Double.TYPE) {
      if (value instanceof Byte) { reflect.setDouble(declaringClass, this, object, ((Byte)value).byteValue()); return; }
      if (value instanceof Character) { reflect.setDouble(declaringClass, this, object, ((Character)value).charValue()); return; }
      if (value instanceof Short) { reflect.setDouble(declaringClass, this, object, ((Short)value).shortValue()); return; }
      if (value instanceof Integer) { reflect.setDouble(declaringClass, this, object, ((Integer)value).intValue()); return; }
      if (value instanceof Long) { reflect.setDouble(declaringClass, this, object, ((Long)value).longValue()); return; }
      if (value instanceof Float) { reflect.setDouble(declaringClass, this, object, ((Float)value).floatValue()); return; }
      if (value instanceof Double) { reflect.setDouble(declaringClass, this, object, ((Double)value).doubleValue()); return; }
      throw new IllegalArgumentException("Field type mismatch");
    }
    if (value == null || type.isInstance(value)) { reflect.setObject(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setBoolean(Object object, boolean value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Boolean.TYPE) { reflect.setBoolean(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setChar(Object object, char value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Character.TYPE) { reflect.setChar(declaringClass, this, object, value); return; }
    if (type == Integer.TYPE) { reflect.setInt(declaringClass, this, object, value); return; }
    if (type == Long.TYPE) { reflect.setLong(declaringClass, this, object, value); return; }
    if (type == Float.TYPE) { reflect.setFloat(declaringClass, this, object, value); return; }
    if (type == Double.TYPE) { reflect.setDouble(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setByte(Object object, byte value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Byte.TYPE) { reflect.setByte(declaringClass, this, object, value); return; }
    if (type == Short.TYPE) { reflect.setShort(declaringClass, this, object, value); return; }
    if (type == Integer.TYPE) { reflect.setInt(declaringClass, this, object, value); return; }
    if (type == Long.TYPE) { reflect.setLong(declaringClass, this, object, value); return; }
    if (type == Float.TYPE) { reflect.setFloat(declaringClass, this, object, value); return; }
    if (type == Double.TYPE) { reflect.setDouble(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setShort(Object object, short value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Short.TYPE) { reflect.setShort(declaringClass, this, object, value); return; }
    if (type == Integer.TYPE) { reflect.setInt(declaringClass, this, object, value); return; }
    if (type == Long.TYPE) { reflect.setLong(declaringClass, this, object, value); return; }
    if (type == Float.TYPE) { reflect.setFloat(declaringClass, this, object, value); return; }
    if (type == Double.TYPE) { reflect.setDouble(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setInt(Object object, int value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Integer.TYPE) { reflect.setInt(declaringClass, this, object, value); return; }
    if (type == Long.TYPE) { reflect.setLong(declaringClass, this, object, value); return; }
    if (type == Float.TYPE) { reflect.setFloat(declaringClass, this, object, value); return; }
    if (type == Double.TYPE) { reflect.setDouble(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setLong(Object object, long value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Long.TYPE) { reflect.setLong(declaringClass, this, object, value); return; }
    if (type == Float.TYPE) { reflect.setFloat(declaringClass, this, object, value); return; }
    if (type == Double.TYPE) { reflect.setDouble(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setFloat(Object object, float value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Float.TYPE) { reflect.setFloat(declaringClass, this, object, value); return; }
    if (type == Double.TYPE) { reflect.setDouble(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

  public void setDouble(Object object, double value) throws IllegalAccessException {
    checkWriteAccess(Thread.callerClass());
    object = prepareInstance(object);
    if (type == Double.TYPE) { reflect.setDouble(declaringClass, this, object, value); return; }
    throw new IllegalArgumentException("Field type mismatch");
  }

}

