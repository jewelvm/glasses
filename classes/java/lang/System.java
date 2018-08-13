/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Properties;

public final class System { 

  private static SecurityManager sm;
  private static Properties properties = new Properties();

  static {
    registerSystemProperties();
  }

  public static final InputStream in = new BufferedInputStream(new FileInputStream(FileDescriptor.in));
  public static final PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true);
  public static final PrintStream err = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.err)), true);

  private static void installSecurityManager() {
    String name = System.getProperty("java.security.manager");
    if (name != null) {
      SecurityManager sm;
      if (name.equals("") || name.equals("default"))
        sm = new SecurityManager();
      else {
        Class clazz;
        try {
          clazz = Class.forName(name, false, ClassLoader.getSystemClassLoader());
        } catch (ClassNotFoundException e) {
          return;
        }
        Object object;
        try {
          object = clazz.newInstance();
        } catch (InstantiationException e) {
          return;
        } catch (IllegalAccessException e) {
          return;
        }
        if (!(object instanceof SecurityManager))
          return;
        sm = (SecurityManager)object;
      }
      setSecurityManager(sm);
    }
  }

  public static synchronized void setSecurityManager(SecurityManager sm) {
    if (System.sm != null)
      sm.checkPermission(new RuntimePermission("setSecurityManager"));
    System.sm = sm;
  }
  
  public static SecurityManager getSecurityManager() {
    return sm;
  }

  public static void setIn(InputStream in) {
    if (sm != null)
      sm.checkPermission(new RuntimePermission("setIO"));
    try {
      Field field = System.class.getField("in");
      field.set(null, in);
    } catch (NoSuchFieldException e) {
      throw new NoSuchFieldError(e.getMessage());
    } catch (IllegalAccessException e) {
      throw new IllegalAccessError(e.getMessage());
    }
  }

  public static void setOut(PrintStream out) {
    if (sm != null)
      sm.checkPermission(new RuntimePermission("setIO"));
    try {
      Field field = System.class.getField("out");
      field.set(null, out);
    } catch (NoSuchFieldException e) {
      throw new NoSuchFieldError(e.getMessage());
    } catch (IllegalAccessException e) {
      throw new IllegalAccessError(e.getMessage());
    }
  }

  public static void setErr(PrintStream err) {
    if (sm != null)
      sm.checkPermission(new RuntimePermission("setIO"));
    try {
      Field field = System.class.getField("err");
      field.set(null, err);
    } catch (NoSuchFieldException e) {
      throw new NoSuchFieldError(e.getMessage());
    } catch (IllegalAccessException e) {
      throw new IllegalAccessError(e.getMessage());
    }
  }

  private static native void registerSystemProperties();

  public static Properties getProperties() {
    if (sm != null)
      sm.checkPropertiesAccess();
    return properties;
  }

  public static void setProperties(Properties properties) {
    if (sm != null)
      sm.checkPropertiesAccess();
    System.properties = properties;  
  }

  public static String getProperty(String name) {
    if (sm != null)
      sm.checkPropertyAccess(name);
    return properties.getProperty(name);
  }

  public static String getProperty(String name, String value) {
    if (sm != null)
      sm.checkPropertyAccess(name);
    return properties.getProperty(name, value);
  }

  public static String setProperty(String name, String value) {
    if (sm != null)
      sm.checkPropertyWriteAccess(name);
    return (String)properties.setProperty(name, value);
  }

  /** @deprecated */
  public static String getenv(String name) {
    return lang.getenv(name);
  }

  public static long currentTimeMillis() {
    return lang.currentTimeMillis();
  }

  public static int identityHashCode(Object object) {
    return lang.identityHashCode(object);
  }

  public static void arraycopy(Object source, int sourceStart, Object target, int targetStart, int length) {
    if (source == null || target == null)
      throw new NullPointerException();
    if (source instanceof Object[] && target instanceof Object[]) {
      Object[] sourceArray = (Object[])source;
      Object[] targetArray = (Object[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof boolean[] && target instanceof boolean[]) {
      boolean[] sourceArray = (boolean[])source;
      boolean[] targetArray = (boolean[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof char[] && target instanceof char[]) {
      char[] sourceArray = (char[])source;
      char[] targetArray = (char[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof float[] && target instanceof float[]) {
      float[] sourceArray = (float[])source;
      float[] targetArray = (float[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof double[] && target instanceof double[]) {
      double[] sourceArray = (double[])source;
      double[] targetArray = (double[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof byte[] && target instanceof byte[]) {
      byte[] sourceArray = (byte[])source;
      byte[] targetArray = (byte[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof short[] && target instanceof short[]) {
      short[] sourceArray = (short[])source;
      short[] targetArray = (short[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof int[] && target instanceof int[]) {
      int[] sourceArray = (int[])source;
      int[] targetArray = (int[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    if (source instanceof long[] && target instanceof long[]) {
      long[] sourceArray = (long[])source;
      long[] targetArray = (long[])target;
      if (sourceStart < 0 || sourceStart > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceStart);
      int sourceEnd = sourceStart+length;
      if (sourceEnd < sourceStart || sourceEnd > sourceArray.length)
        throw new ArrayIndexOutOfBoundsException(sourceEnd);
      if (targetStart < 0 || targetStart > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetStart);
      int targetEnd = targetStart+length;
      if (targetEnd < targetStart || targetEnd > targetArray.length)
        throw new ArrayIndexOutOfBoundsException(targetEnd);
      if (sourceArray == targetArray && sourceStart < targetStart)
        for (int i = length-1; i >= 0; i--)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      else
        for (int i = 0; i < length; i++)
          targetArray[targetStart+i] = sourceArray[sourceStart+i];
      return;
    }
    throw new ArrayStoreException();
  }

  public static void exit(int status) {
    Runtime.getRuntime().exit(status);
  }
  
  public static void gc() {
    Runtime.getRuntime().gc();
  }
  
  public static void runFinalization() {
    Runtime.getRuntime().runFinalization();
  }

  /** @deprecated */
  public static void runFinalizersOnExit(boolean on) {
    Runtime.runFinalizersOnExit(on);
  }

  public static void load(String libraryPath) {
    Class caller = Thread.callerClass();
    ClassLoader loader = caller == null ? Thread.currentThread().getContextClassLoader() : caller.getDefiningLoader();
    if (loader != null)
      loader.loadLibrary(libraryPath, true);
    else
      ClassLoader.loadSystemLibrary(libraryPath, true);
  }

  public static void loadLibrary(String libraryName) {
    Class caller = Thread.callerClass();
    ClassLoader loader = caller == null ? Thread.currentThread().getContextClassLoader() : caller.getDefiningLoader();
    if (loader != null)
      loader.loadLibrary(libraryName, false);
    else
      ClassLoader.loadSystemLibrary(libraryName, false);
  }

  public static String mapLibraryName(String name) {
    String prefix = getProperty("java.library.prefix");
    String suffix = getProperty("java.library.suffix");
    return prefix+name+suffix;
  }

  private System() { }

}

