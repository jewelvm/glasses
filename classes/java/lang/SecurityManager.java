/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.FileDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.InetAddress;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.AllPermission;
import java.security.Permission;
import java.security.Security;
import java.security.SecurityPermission;
import java.util.StringTokenizer;

//import java.io.FilePermission;
//import java.net.SocketPermission;
//import java.util.PropertyPermission;

public class SecurityManager {
  
  private static Permission newAWTPermission(String name) {
    try {
      Class clazz = Class.forName("java.awt.AWTPermission");
      Constructor constructor = clazz.getConstructor(new Class[]{ String.class });
      return (Permission)constructor.newInstance(new Object[]{ name });
    } catch (ClassNotFoundException e) {
      throw new NoClassDefFoundError(e.getMessage());
    } catch (NoSuchMethodException e) {
      throw new NoSuchMethodError(e.getMessage());
    } catch (IllegalAccessException e) {
      throw new IllegalAccessError(e.getMessage());
    } catch (InstantiationException e) {
      throw new InstantiationError(e.getMessage());
    } catch (InvocationTargetException e) {
      Throwable t = e.getTargetException();
      if (t instanceof Error)
        throw (Error)t;
      if (t instanceof RuntimeException)
        throw (RuntimeException)t;
      throw new UndeclaredThrowableException(t);
    }
  }

  /** @deprecated */
  protected boolean inCheck;

  public SecurityManager() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("createSecurityManager"));
  }

  /** @deprecated */
  protected int classDepth(String name) {
    Class[] context = getClassContext();
    for (int i = 1; i < context.length; i++)
      if (name.equals(context[i].getName()))
        return i-1;
    return -1;
  }

  /** @deprecated */
  protected int classLoaderDepth() {
    Class[] context = getClassContext();
    for (int i = 1; i < context.length; i++)
      if (!ClassLoader.isSystemClassLoader(context[i].getClassLoaderNoCheck())) {
        try {
          checkPermission(new AllPermission());
        } catch (SecurityException e) {
          return i-1;
        }
        break;
      }
    return -1;
  }

  /** @deprecated */
  protected ClassLoader currentClassLoader() {
    Class clazz = currentLoadedClass();
    if (clazz != null)
      return clazz.getClassLoaderNoCheck();
    return null;
  }

  /** @deprecated */
  protected Class currentLoadedClass() {
    int depth = classLoaderDepth();
    if (depth != -1) {
      Class[] context = getClassContext();
      return context[depth+1];
    }
    return null;
  }

  /** @deprecated */
  public boolean getInCheck() {
    return inCheck;
  }

  /** @deprecated */
  protected boolean inClass(String name) {
    return classDepth(name) != -1;
  }

  /** @deprecated */
  protected boolean inClassLoader() {
    return currentClassLoader() != null;
  }

  protected Class[] getClassContext() {
    return Thread.callStack();
  }

  public Object getSecurityContext() {
    return AccessController.getContext();
  }

  public void checkPermission(Permission permission) {
    AccessController.checkPermission(permission);
  }

  public void checkPermission(Permission permission, Object context) {
    if (!(context instanceof AccessControlContext))
      throw new SecurityException("Unknown context");
    ((AccessControlContext)context).checkPermission(permission);
  }

  public void checkCreateClassLoader() {
    checkPermission(new RuntimePermission("createClassLoader"));
  }

  public void checkAccess(Thread thread) {
    if (thread.getThreadGroup() == ThreadGroup.getSystemGroup())
      checkPermission(new RuntimePermission("modifyThread"));
  }

  public void checkAccess(ThreadGroup group) {
    if (group == ThreadGroup.getSystemGroup())
      checkPermission(new RuntimePermission("modifyThreadGroup"));
  }

  public void checkExit(int status) {
    checkPermission(new RuntimePermission("exitVM"));
  }

  public void checkExec(String name) {
//    checkPermission(new FilePermission(name, "execute"));
  }
  
  public void checkLink(String name) {
    checkPermission(new RuntimePermission("loadLibrary."+name));
  }
  
  public void checkRead(FileDescriptor descriptor) {
    checkPermission(new RuntimePermission("readFileDescriptor"));  
  }

  public void checkRead(String name) {
//    checkPermission(new FilePermission(name, "read"));
  }

  public void checkRead(String name, Object context) {
//    checkPermission(new FilePermission(name, "read"), context);
  }
  
  public void checkWrite(FileDescriptor descriptor) {
    checkPermission(new RuntimePermission("writeFileDescriptor"));
  }

  public void checkWrite(String name) {
//    checkPermission(new FilePermission(name, "write"));
  }

  public void checkDelete(String name) {
//    checkPermission(new FilePermission(name, "delete"));
  }

  public void checkConnect(String host, int port) {
/*    SocketPermission permission = port == -1
                              ? new SocketPermission(host, "resolve")
                              : new SocketPermission(host+":"+port, "connect");
    checkPermission(permission);*/
  }
  
  public void checkConnect(String host, int port, Object context) {
/*    SocketPermission permission = port == - 1
                              ? new SocketPermission(host, "resolve")
                              : new SocketPermission(host+":"+port, "connect");
    checkPermission(permission, context);*/
  }
  
  public void checkListen(int port) {
/*    SocketPermission permission = port == 0
                              ? new SocketPermission("localhost:1024-", "listen")
                              : new SocketPermission("localhost:"+port, "listen");
    checkPermission(permission);*/
  }
  
  public void checkAccept(String host, int port) {
//    checkPermission(new SocketPermission(host+":"+port, "accept"));
  }
  
  public void checkMulticast(InetAddress address) {
//    checkPermission(new SocketPermission(address.getHostAddress(), "accept,connect"));
  }
  
  public void checkMulticast(InetAddress address, byte ttl) {
//    checkPermission(new SocketPermission(address.getHostAddress(), "accept,connect"));
  }

  public void checkPropertiesAccess() {
//    checkPermission(new PropertyPermission("*", "read,write"));
  }

  public void checkPropertyAccess(String name) {
//    checkPermission(new PropertyPermission(name, "read"));
  }

  public void checkPropertyWriteAccess(String name) { 
//    checkPermission(new PropertyPermission(name, "write"));
  }

  public boolean checkTopLevelWindow(Object window) {
    try {
      checkPermission(newAWTPermission("showWindowWithoutWarningBanner"));
      return true;
    } catch (SecurityException e) {
      return false;
    }
  }

  public void checkSystemClipboardAccess() {
    checkPermission(newAWTPermission("accessClipboard"));
  }

  public void checkAwtEventQueueAccess() {
    checkPermission(newAWTPermission("accessEventQueue"));
  }
  
  public void checkPrintJobAccess() {
    checkPermission(new RuntimePermission("queuePrintJob"));
  }

  public void checkPackageAccess(String name) {
    String packages = Security.getProperty("package.access");
    if (packages != null) {
      StringTokenizer st = new StringTokenizer(packages, ",");
      while (st.hasMoreTokens())
        if (name.startsWith(st.nextToken()))
          checkPermission(new RuntimePermission("accessClassInPackage."+name));
    }
  }

  public void checkPackageDefinition(String name) {
    String packages = Security.getProperty("package.definition");
    if (packages != null) {
      StringTokenizer st = new StringTokenizer(packages, ",");
      while (st.hasMoreTokens())
        if (name.startsWith(st.nextToken()))
          checkPermission(new RuntimePermission("defineClassInPackage."+name));
    }
  }
  
  public void checkSetFactory() {
    checkPermission(new RuntimePermission("setFactory"));
  }

  public void checkMemberAccess(Class target, int type) {
    Class[] context = getClassContext();
    if (context.length > 3)
      checkMemberAccess(context[3], target, type);
  }
  
  public void checkMemberAccess(Class source, Class target, int type) {
    if (type != Member.PUBLIC)
      if (source.getClassLoader() != target.getClassLoader())
        checkPermission(new RuntimePermission("accessDeclaredMembers"));
  }
  
  public void checkSecurityAccess(String target) {
    checkPermission(new SecurityPermission(target));
  }
  
  public ThreadGroup getThreadGroup() {
    return Thread.currentThread().getThreadGroup();
  }

}

