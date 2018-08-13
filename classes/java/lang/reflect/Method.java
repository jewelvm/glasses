/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

public final class Method extends AccessibleObject implements Member {

  private final Class declaringClass;
  private final char index;
  private final char modifiers;
  private final Class returnType;
  private final String name;
  private final Class[] parameterTypes;
  private final Class[] exceptionTypes;

  private Method(Class declaringClass, char index, char modifiers, Class returnType, String name, Class[] parameterTypes, Class[] exceptionTypes) {
    if (declaringClass == null)
      throw new NullPointerException();
    if (returnType == null)
      throw new NullPointerException();
    if (name == null)
      throw new NullPointerException();
    if (parameterTypes == null)
      throw new NullPointerException();
    for (int i = 0; i < parameterTypes.length; i++)
      if (parameterTypes[i] == null)
        throw new NullPointerException();
    if (exceptionTypes == null)
      throw new NullPointerException();
    for (int i = 0; i < exceptionTypes.length; i++)
      if (exceptionTypes[i] == null)
        throw new NullPointerException();
    this.declaringClass = declaringClass;
    this.index = index;
    this.modifiers = modifiers;
    this.returnType = returnType;
    this.name = name;
    this.parameterTypes = parameterTypes;
    this.exceptionTypes = exceptionTypes;
  }

  public Class getDeclaringClass() {
    return declaringClass;
  }

  public int getIndex() {
    return index;
  }

  public Class getReturnType() {
    return returnType;
  }

  public int getModifiers() {
    return modifiers;
  }

  public String getName() {
    return name;
  }

  public Class[] getParameterTypes() {
    return (Class[])parameterTypes.clone();
  }

  public Class[] getExceptionTypes() {
    return (Class[])exceptionTypes.clone();
  }
  
  public int hashCode() {
    return declaringClass.getName().hashCode() ^ name.hashCode();
  }

  public boolean equals(Object object) {
    if (object instanceof Method) {
      Method method = (Method)object;
      if (declaringClass != method.declaringClass)
        return false;
      if (returnType != method.returnType)
        return false;
      if (!name.equals(method.name))
        return false;
      if (parameterTypes.length != method.parameterTypes.length)
        return false;
      for (int i = 0; i < parameterTypes.length; i++) 
        if (parameterTypes[i] != method.parameterTypes[i])
          return false;
      return true;
    }
    return false;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer(100);
    if (modifiers != 0) {
      sb.append(Modifier.toString(modifiers));
      sb.append(' ');
    }
    sb.append(returnType.toTypeString());
    sb.append(' ');
    sb.append(declaringClass.toTypeString());
    sb.append('.');
    sb.append(name);
    sb.append('(');
    for (int i = 0; i < parameterTypes.length; i++) {
      sb.append(parameterTypes[i].toTypeString());
      if (i+1 < parameterTypes.length)
        sb.append(',');
    }
    sb.append(')');
    if (exceptionTypes.length > 0) {
      sb.append(" throws ");
      for (int i = 0; i < exceptionTypes.length; i++) {
        sb.append(exceptionTypes[i].toTypeString());
        if (i+1 < exceptionTypes.length)
          sb.append(',');
      }
    }
    return sb.toString();
  }

  private void checkAccess(Class caller) throws IllegalAccessException {
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
  
  private Object checkArgument(Class type, Object value) {
    if (type == Boolean.TYPE) {
      if (value instanceof Boolean) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (type == Character.TYPE) {
      if (value instanceof Character) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (type == Byte.TYPE) {
      if (value instanceof Byte) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (type == Short.TYPE) {
      if (value instanceof Byte) return new Short(((Byte)value).byteValue());
      if (value instanceof Short) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (type == Integer.TYPE) {
      if (value instanceof Byte) return new Integer(((Byte)value).byteValue());
      if (value instanceof Character) return new Integer(((Character)value).charValue());
      if (value instanceof Short) return new Integer(((Short)value).shortValue());
      if (value instanceof Integer) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (type == Long.TYPE) {
      if (value instanceof Byte) return new Long(((Byte)value).byteValue());
      if (value instanceof Character) return new Long(((Character)value).charValue());
      if (value instanceof Short) return new Long(((Short)value).shortValue());
      if (value instanceof Integer) return new Long(((Integer)value).intValue());
      if (value instanceof Long) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (type == Float.TYPE) {
      if (value instanceof Byte)  return new Float(((Byte)value).byteValue());
      if (value instanceof Character)  return new Float(((Character)value).charValue());
      if (value instanceof Short)  return new Float(((Short)value).shortValue());
      if (value instanceof Integer) return new Float(((Integer)value).intValue());
      if (value instanceof Long) return new Float(((Long)value).longValue());
      if (value instanceof Float) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (type == Double.TYPE) {
      if (value instanceof Byte) return new Double(((Byte)value).byteValue());
      if (value instanceof Character) return new Double(((Character)value).charValue());
      if (value instanceof Short) return new Double(((Short)value).shortValue());
      if (value instanceof Integer) return new Double(((Integer)value).intValue());
      if (value instanceof Long) return new Double(((Long)value).longValue());
      if (value instanceof Float) return new Double(((Float)value).floatValue());
      if (value instanceof Double) return value;
      throw new IllegalArgumentException("Argument type mismatch");
    }
    if (value == null || type.isInstance(value)) return new Object[]{ value };
    throw new IllegalArgumentException("Argument type mismatch");
  }

  public Object invoke(Object object, Object[] arguments) throws IllegalAccessException, InvocationTargetException {
    checkAccess(Thread.callerClass());
    if (arguments == null)
      arguments = new Object[]{ };
    if (arguments.length != parameterTypes.length)
      throw new IllegalArgumentException("Wrong number of arguments");
    Object[] parameters = new Object[parameterTypes.length];
    for (int i = 0; i < parameterTypes.length; i++)
      parameters[i] = checkArgument(parameterTypes[i], arguments[i]);
    object = prepareInstance(object);
    try {
      if (returnType == Void.TYPE) { reflect.invokeVoid(declaringClass, this, object, parameters); return null; }
      if (returnType == Boolean.TYPE) return new Boolean(reflect.invokeBoolean(declaringClass, this, object, parameters));
      if (returnType == Byte.TYPE) return new Byte(reflect.invokeByte(declaringClass, this, object, parameters));
      if (returnType == Character.TYPE) return new Character(reflect.invokeChar(declaringClass, this, object, parameters));
      if (returnType == Short.TYPE) return new Short(reflect.invokeShort(declaringClass, this, object, parameters));
      if (returnType == Integer.TYPE) return new Integer(reflect.invokeInt(declaringClass, this, object, parameters));
      if (returnType == Long.TYPE) return new Long(reflect.invokeLong(declaringClass, this, object, parameters));
      if (returnType == Float.TYPE) return new Float(reflect.invokeFloat(declaringClass, this, object, parameters));
      if (returnType == Double.TYPE) return new Double(reflect.invokeDouble(declaringClass, this, object, parameters));
      return reflect.invokeObject(declaringClass, this, object, parameters);
    } catch (Throwable e) {
      if (e instanceof Error)
        throw (Error)e;
      throw new InvocationTargetException(e);
    }
  }

}

