/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;

public final class Class implements Serializable {

  static Class derive(ClassLoader loader, String name, byte[] array, int start, int length) {
    return lang.derive(loader, name, array, start, length);
  }

  public static Class getPrimitiveClass(String name) {
    Class clazz = ClassLoader.getClass(null, "<"+name+">");
    if (clazz == null)
      throw new InternalError();
    return clazz;
  }

  public static Class forName(String name) throws ClassNotFoundException {
    return forName(name, true, Thread.callerClass().getDefiningLoader());
  }

  public static Class forName(String name, boolean initialize, ClassLoader loader) throws ClassNotFoundException {
    if (loader == null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        ClassLoader caller = Thread.callerClass().getDefiningLoader();
        if (caller != null)
          sm.checkPermission(new RuntimePermission("getClassLoader"));
      }
    }
    Class clazz = ClassLoader.findClass(loader, name);
    if (initialize)
      clazz.initialize();
    return clazz;
  }

  private Object[] signers;
  private ProtectionDomain domain;

  private Class() { }

  public void link() {
    lang.link(this);
  }

  public void initialize() {
    lang.initialize(this);
  }

  public boolean hasStaticInitializer() {
    return lang.hasStaticInitializer(this);
  }

  public int getModifiers() {
    return lang.getModifiers(this);
  }

  public String getName() {
    return lang.getName(this);
  }

  public String getPackageName() {
    String name = getName();
    int begin = name.lastIndexOf('[');
    begin = begin == -1 ? 0 : begin+2;
    int end = name.lastIndexOf('.');
    end = end == -1 ? begin : end;
    return name.substring(begin, end);
  }

  public Class getSuperclass() {
    return lang.getSuperclass(this);
  }

  public Class[] getInterfaces() {
    return lang.getInterfaces(this);
  }

  ClassLoader getDefiningLoader() {
    return lang.getDefiningLoader(this);
  }

  public ClassLoader getClassLoaderNoCheck() {
    return getDefiningLoader();
  }

  public ClassLoader getClassLoader() {
    ClassLoader loader = getDefiningLoader();
    if (loader != null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        ClassLoader caller = Thread.callerClass().getDefiningLoader();
        if (caller != null && !caller.parentOf(loader))
          sm.checkPermission(new RuntimePermission("getClassLoader"));
      }
    }
    return loader;
  }

  public int getDimensions() {
    return lang.getDimensions(this);
  }

  public Class getElementType() {
    return lang.getElementType(this);
  }

  public Class getComponentType() {
    if (isArray()) {
      if (getDimensions() == 1)
        return getElementType();
      try {
        return forName(getName().substring(1), false, getDefiningLoader());
      } catch (ClassNotFoundException e) {
        throw new InternalError("Array component should be found");
      }
    }
    return null;
  }

  public Package getPackage() {
    String packageName = getPackageName();
    if (packageName.length() == 0)
      return null;
    ClassLoader loader = getDefiningLoader();
    if (loader != null)
      return loader.getPackage(packageName);
    return ClassLoader.getSystemPackage(packageName);
  }

  public boolean isInterface() {
    return Modifier.isInterface(getModifiers());
  }

  public boolean isPrimitive() {
    return this == Void.TYPE  || this == Boolean.TYPE
        || this == Byte.TYPE  || this == Character.TYPE
        || this == Short.TYPE || this == Integer.TYPE
        || this == Long.TYPE  || this == Float.TYPE
        || this == Double.TYPE;
  }

  public boolean isArray() {
    return getDimensions() > 0;
  }

  public boolean isInstance(Object object) {
    return object != null
        && isAssignableFrom(object.getClass());
  }

  public boolean isAssignableFrom(Class clazz) {
    if (clazz == null)
      throw new NullPointerException();
    return lang.isAssignableFrom(this, clazz);
  }

  public Object newInstance() throws InstantiationException, IllegalAccessException {
    return lang.newInstance(this);
  }

  public Class getDeclaringClass() {
    return lang.getDeclaringClass(this);
  }

  public Class[] getDeclaredClasses() {
    return lang.getDeclaredClasses(this);
  }

  public Field[] getDeclaredFields() {
    return lang.getDeclaredFields(this);
  }

  public Constructor[] getDeclaredConstructors() {
    return lang.getDeclaredConstructors(this);
  }

  public Method[] getDeclaredMethods() {
    return lang.getDeclaredMethods(this);
  }

  public Class[] getClasses() {
    ArrayList classes = new ArrayList();
    Class[] cls = getDeclaredClasses();
    for (int i = 0; i < cls.length; i++)
      if (Modifier.isPublic(cls[i].getModifiers()))
        classes.add(cls[i]);
    Class[] interfaces = getInterfaces();
    for (int j = 0; j < interfaces.length; j++) {
      cls = interfaces[j].getClasses();
      for (int i = 0; i < cls.length; i++)
        classes.add(cls[i]);
    }
    Class superclass = getSuperclass();
    if (superclass != null) {
      cls = superclass.getClasses();
      for (int i = 0; i < cls.length; i++)
        classes.add(cls[i]);
    }
    return (Class[])classes.toArray(new Class[classes.size()]);
  }
  
  public Field[] getFields() {
    ArrayList fields = new ArrayList();
    Field[] fds = getDeclaredFields();
    for (int i = 0; i < fds.length; i++)
      if (Modifier.isPublic(fds[i].getModifiers()))
        fields.add(fds[i]);
    Class[] interfaces = getInterfaces();
    for (int j = 0; j < interfaces.length; j++) {
      fds = interfaces[j].getFields();
      for (int i = 0; i < fds.length; i++)
        fields.add(fds[i]);
    }
    Class superclass = getSuperclass();
    if (superclass != null) {
      fds = superclass.getFields();
      for (int i = 0; i < fds.length; i++)
        fields.add(fds[i]);
    }
    return (Field[])fields.toArray(new Field[fields.size()]);
  }
  
  public Constructor[] getConstructors() {
    ArrayList constructors = new ArrayList();
    Constructor[] cts = getDeclaredConstructors();
    for (int i = 0; i < cts.length; i++)
      if (Modifier.isPublic(cts[i].getModifiers()))
        constructors.add(cts[i]);
    return (Constructor[])constructors.toArray(new Constructor[constructors.size()]);
  }

  public Method[] getMethods() {
    ArrayList methods = new ArrayList();
    Method[] mds = getDeclaredMethods();
    for (int i = 0; i < mds.length; i++)
      if (Modifier.isPublic(mds[i].getModifiers()))
        methods.add(mds[i]);
    Class[] interfaces = getInterfaces();
    for (int j = 0; j < interfaces.length; j++) {
      mds = interfaces[j].getMethods();
      for (int i = 0; i < mds.length; i++)
        methods.add(mds[i]);
    }
    Class superclass = getSuperclass();
    if (superclass != null) {
      mds = superclass.getMethods();
      for (int i = 0; i < mds.length; i++)
        methods.add(mds[i]);
    }
    return (Method[])methods.toArray(new Method[methods.size()]);
  }
  
  public Field getDeclaredField(String name) throws NoSuchFieldException {
    if (name == null)
      throw new NullPointerException();
    Field[] fields = getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      if (name.equals(field.getName()))
        return field;
    }
    throw new NoSuchFieldException(name);
  }


  public Constructor getDeclaredConstructor(Class[] array) throws NoSuchMethodException {
    if (array == null)
      array = new Class[]{ };
    Constructor[] constructors = getDeclaredConstructors();
    for (int i = 0; i < constructors.length; i++) {
      Constructor constructor = constructors[i];
      Class[] types = constructor.getParameterTypes();
      if (types.length != array.length)
        continue;
      boolean found = true;
      for (int j = 0; j < types.length; j++)
        if (types[j] != array[j]) {
          found = false;
          break;
        }
      if (!found)
        continue;
      return constructor;
    }
    throw new NoSuchMethodException();
  }

  public Method getDeclaredMethod(String name, Class[] array) throws NoSuchMethodException {
    if (name == null)
      throw new NullPointerException();
    if (array == null)
      array = new Class[]{ };
    Method[] methods = getDeclaredMethods();
    for (int i = 0; i < methods.length; i++) {
      Method method = methods[i];
      if (!name.equals(method.getName()))
        continue;
      Class[] types = method.getParameterTypes();
      if (types.length != array.length)
        continue;
      boolean found = true;
      for (int j = 0; j < types.length; j++)
        if (types[j] != array[j]) {
          found = false;
          break;
        }
      if (!found)
        continue;
      return method;
    }
    throw new NoSuchMethodException(name);
  }

  public Field getField(String name) throws NoSuchFieldException {
    if (name == null)
      throw new NullPointerException();
    Field[] fields = getFields();
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      if (name.equals(field.getName()))
        return field;
    }
    throw new NoSuchFieldException(name);
  }

  public Constructor getConstructor(Class[] array) throws NoSuchMethodException {
    if (array == null)
      array = new Class[]{ };
    Constructor[] constructors = getConstructors();
    for (int i = 0; i < constructors.length; i++) {
      Constructor constructor = constructors[i];
      Class[] types = constructor.getParameterTypes();
      if (types.length != array.length)
        continue;
      boolean found = true;
      for (int j = 0; j < types.length; j++)
        if (types[j] != array[j]) {
          found = false;
          break;
        }
      if (!found)
        continue;
      return constructor;
    }
    throw new NoSuchMethodException();
  }

  public Method getMethod(String name, Class[] array) throws NoSuchMethodException {
    if (name == null)
      throw new NullPointerException();
    if (array == null)
      array = new Class[]{ };
    Method[] methods = getMethods();
    for (int i = 0; i < methods.length; i++) {
      Method method = methods[i];
      if (!name.equals(method.getName()))
        continue;
      Class[] types = method.getParameterTypes();
      if (types.length != array.length)
        continue;
      boolean found = true;
      for (int j = 0; j < types.length; j++)
        if (types[j] != array[j]) {
          found = false;
          break;
        }
      if (!found)
        continue;
      return method;
    }
    throw new NoSuchMethodException(name);
  }
  
  public URL getResource(String name) {
    if (name.startsWith("/")) 
      name = name.substring(1);
    else {
      String pckgName = getPackageName();
      if (pckgName.length() > 0) {
        pckgName = pckgName.replace('.', '/');
        name = pckgName+"/"+name;
      }
    }
    ClassLoader loader = getDefiningLoader();
    if (loader != null)
      return loader.getResource(name);
    return ClassLoader.getSystemResource(name);
  }

  public InputStream getResourceAsStream(String name) {
    URL resource = getResource(name);
    if (resource != null)
      try {
        return resource.openStream();
      } catch (IOException e) { }
    return null;
  }

  public Object[] getSigners() {
    return (Object[])signers.clone();
  }

  protected void setSigners(Object[] signers) {
    this.signers = signers;
  }

  public ProtectionDomain getProtectionDomainNoCheck() {
    return domain;
  }

  public ProtectionDomain getProtectionDomain() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("getProtectionDomain"));
    return domain;
  }

  protected void setProtectionDomain(ProtectionDomain domain) {
    this.domain = domain;
  }

  public boolean samePackageAs(Class clazz) {
    return getDefiningLoader() == clazz.getDefiningLoader()
        && getPackageName().equals(clazz.getPackageName());
  }

  public boolean desiredAssertionStatus() {
    return false;//implement
  }

  public String toString() {
    String name = getName();
    if (isPrimitive())
      return name;
    StringBuffer sb = new StringBuffer(10+name.length());
    sb.append(isInterface() ? "interface" : "class");
    sb.append(' ');
    sb.append(name);
    return sb.toString();
  }

  public String toTypeString() {
    String name = getName();
    if (!isArray()) 
      return name;
    String elementName = getElementType().getName();
    StringBuffer sb = new StringBuffer(elementName.length()+2*getDimensions());
    sb.append(elementName);
    int dimensions = getDimensions();
    for (int i = 0; i < dimensions; i++)
      sb.append("[]");
    return sb.toString();
  }

  protected void finalize() throws Throwable {
    try {
      Method finalizer = getDeclaredMethod("classFinalize", new Class[]{ });
      int modifiers = finalizer.getModifiers();
      Class returnType = finalizer.getReturnType();
      if (Modifier.isStatic(modifiers) || returnType == Void.TYPE)
        finalizer.invoke(null, new Object[]{ });
    } catch(NoSuchMethodException e) {
      throw new NoSuchMethodError(e.getMessage());
    } catch (IllegalAccessException e) {
      throw new IllegalAccessError(e.getMessage());
    } catch(InvocationTargetException e) {
      throw e.getTargetException();
    }
  }

}

