/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Proxy implements Serializable {

  private static long serial;
  private static final HashMap map = new HashMap();

  private static byte[] createProxyImage(String name, Class superclass, Class[] interfaces) {
    throw new InternalError("Unimplemented");
  }

  private static Class defineClass(ClassLoader loader, String name, byte[] array, int start, int length) {
    Class clazz = ClassLoader.class;
    Method method;
    try {
      method = clazz.getMethod("defineClass", new Class[]{ String.class, byte[].class, int.class, int.class });
    } catch (NoSuchMethodException e) {
      throw new NoSuchMethodError(e.getMessage());
    }
    method.setAccessible(true);
    Object object;
    try {
      object = method.invoke(loader, new Object[]{ name, array, new Integer(start), new Integer(length) });
    } catch (IllegalAccessException e) {
      throw new IllegalAccessError(e.getMessage());
    } catch (InvocationTargetException e) {
      Throwable target = e.getTargetException();
      if (target instanceof Error)
        throw (Error)target;
      if (target instanceof RuntimeException)
        throw (RuntimeException)target;
      throw new UndeclaredThrowableException(target);
    }
    return (Class)object;
  }

  public static synchronized boolean isProxyClass(Class clazz) {
    Class[] interfaces = clazz.getInterfaces();
    String packageName = clazz.getPackageName();
    String[] interfaceName = new String[interfaces.length];
    for (int i = 0; i < interfaces.length; i++)
      interfaceName[i] = interfaces[i].getName();
    Key key = new Key(packageName, interfaceName);
    ArrayList list = (ArrayList)map.get(key);
    if (list != null)
      for (Iterator i = list.iterator(); i.hasNext(); ) {
        WeakReference ref = (WeakReference)i.next();
        Class proxyClass = (Class)ref.get();
        if (proxyClass == null) {
          i.remove();
          continue;
        }
        if (clazz == proxyClass)
          return true;
      }
    return false;
  }
  
  public static synchronized Class getProxyClass(ClassLoader loader, Class[] interfaces) {
    String packageName = null;
    String[] interfaceName = new String[interfaces.length];
    for (int i = 0; i < interfaces.length; i++) {
      Class iface = interfaces[i];
      if (!iface.isInterface())
        throw new IllegalArgumentException("Expecting only interfaces");
      String name = iface.getName();
      interfaceName[i] = name;
      for (int j = 0; j < i; i++)
        if (name.equals(interfaceName[j]))
          throw new IllegalArgumentException("All interfaces must have unique names");
      Class clazz;
      try {
        clazz = Class.forName(name, false, loader);
      } catch (ClassNotFoundException e) {
        throw new IllegalArgumentException("Interface not available in this context");
      }
      if (clazz != iface)
        throw new IllegalArgumentException("Interface mismatch in this context");
      int modifiers = iface.getModifiers();
      if (!Modifier.isPublic(modifiers))
        if (packageName == null)
          packageName = iface.getPackageName();
        else {
          if (packageName.equals(iface.getPackageName()))
            throw new IllegalArgumentException("Non public interfaces of multiple packages");
        }
    }
    if (packageName == null)
      packageName = "";
    Key key = new Key(packageName, interfaceName);
    ArrayList list = (ArrayList)map.get(key);
    if (list == null) {
      list = new ArrayList();
      map.put(key, list);
    }
    for (Iterator i = list.iterator(); i.hasNext(); ) {
      WeakReference ref = (WeakReference)i.next();
      Class proxyClass = (Class)ref.get();
      if (proxyClass == null) {
        i.remove();
        continue;
      }
      if (loader == proxyClass.getClassLoaderNoCheck())
        return proxyClass;
    }
    String name = packageName+".$Proxy"+serial++;
    if (packageName.equals(""))
      name = name.substring(1);
    byte[] bytecode = createProxyImage(name, Proxy.class, interfaces);
    Class clazz = defineClass(loader, name, bytecode, 0, bytecode.length);
    list.add(new WeakReference(clazz));
    return clazz;
  }
  
  public static Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler h) {
    Class clazz = getProxyClass(loader, interfaces);
    Constructor constructor;
    try {
      constructor = clazz.getConstructor(new Class[]{ InvocationHandler.class });
    } catch (NoSuchMethodException e) {
      throw new NoSuchMethodError(e.getMessage());
    }
    Object object;
    try {
      object = constructor.newInstance(new Object[]{ h });
    } catch (IllegalAccessException e) {
      throw new IllegalAccessError(e.getMessage());
    } catch (InstantiationException e) {
      throw new InstantiationError(e.getMessage());
    } catch (InvocationTargetException e) {
      Throwable target = e.getTargetException();
      if (target instanceof Error)
        throw (Error)target;
      if (target instanceof RuntimeException)
        throw (RuntimeException)target;
      throw new UndeclaredThrowableException(target);
    }
    return object;
  }

  public static InvocationHandler getInvocationHandler(Object object) {
    Class clazz = object.getClass();
    if (!isProxyClass(clazz))
      throw new IllegalArgumentException("Object is not an instance of a proxy class");
    Proxy proxy = (Proxy)object;
    return proxy.h;
  }
  
  protected InvocationHandler h;

  protected Proxy(InvocationHandler h) {
    this.h = h;
  }

  private static final class Key {

    private final String packageName;
    private final String[] interfaceName;

    public Key(String packageName, String[] interfaceName) {
      this.packageName = packageName;
      this.interfaceName = interfaceName;
    }

    public int hashCode() {
      int hashCode = packageName.hashCode();
      for (int i = 0; i < interfaceName.length; i++) {
        hashCode *= i;
        hashCode += interfaceName.hashCode();
      }
      return hashCode;
    }

    public boolean equals(Object object) {
      return object instanceof Key
          && ((Key)object).packageName.equals(packageName)
          && Arrays.equals(((Key)object).interfaceName, interfaceName);
    }

  }

}

