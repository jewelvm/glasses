/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

final class lang {

  static {
    AccessController.doPrivileged(
      new PrivilegedAction() {
        public Object run() {
          System.loadLibrary("lang");
          return null;
        }
      }
    );
  }

  static native Class derive(ClassLoader loader, String name, byte[] array, int start, int length);
  static native void link(Class clazz);
  static native void initialize(Class clazz);
  static native boolean hasStaticInitializer(Class clazz);
  static native int getModifiers(Class clazz);
  static native Class getSuperclass(Class clazz);
  static native Class[] getInterfaces(Class clazz);
  static native int getDimensions(Class clazz);
  static native Class getElementType(Class clazz);
  static native boolean isAssignableFrom(Class clazz, Class from);
  static native Object newInstance(Class clazz) throws InstantiationException, IllegalAccessException;
  static native Class getDeclaringClass(Class clazz);
  static native Class[] getDeclaredClasses(Class clazz);
  static native Field[] getDeclaredFields(Class clazz);
  static native Constructor[] getDeclaredConstructors(Class clazz);
  static native Method[] getDeclaredMethods(Class clazz);
  static native String getName(Class clazz);
  static native ClassLoader getDefiningLoader(Class clazz);

  static native long load(String name);
  static native long findSymbol(long handle, String symbol, int jwords);
  static native void unload(long handle);

  static native void compilerEnable();
  static native void compilerDisable();
  static native Object compilerCommand(Object command);
  static native boolean compileClass(Class clazz);
  static native boolean compileClasses(String classes);

  static native long doubleToRawLongBits(double duoble);
  static native double longBitsToDouble(long lung);
  
  static native int floatToRawIntBits(float flaot);
  static native float intBitsToFloat(int ant);

  static native Class getClass(Object object);
  static native Object clone(Object object);
  static native void wait(Object object, long millis) throws InterruptedException;
  static native void notify(Object object);
  static native void notifyAll(Object object);

  static native void gc();
  static native long freeMemory();
  static native long totalMemory();
  static native long maxMemory();
  static native int availableProcessors();
  static native void traceMethodCalls(boolean on);
  static native void traceInstructions(boolean on);
  static native void exit(int status);
  static native Process exec(String[] command, String[] environment, String directory) throws IOException;

  static native Class callerClass();
  static native Class[] callStack();
  static native void yield();
  static native void unblock(Thread thread);
  static native void changePriority(Thread thread, int priority);
  static native void start(Thread thread, int priority, boolean daemon, long stackSize);
  static native boolean holdsLock(Object object);

  static native int identityHashCode(Object object);
  static native String getenv(String name);
  static native long currentTimeMillis();

  static native StackTraceElement[] getStackTrace();

  private lang() { }

}

