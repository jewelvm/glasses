/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.Iterator;
import java.util.WeakHashMap;

public class Thread implements Runnable {

  public static final int MIN_PRIORITY = 1;
  public static final int NORM_PRIORITY = 5;
  public static final int MAX_PRIORITY = 10;

  private static int serial;
  
  private static synchronized int nextSerial() {
    return serial++;
  }

  public static native Thread currentThread();

  public static boolean holdsLock(Object object) {
    return lang.holdsLock(object);
  }

  public static void yield() {
    lang.yield();
  }

  public static void sleep(long millis) throws InterruptedException {
    if (millis < 0)
      throw new IllegalArgumentException("Milliseconds out of range");
    if (millis == 0)
      yield();
    else {
      Object object = new Object();
      synchronized (object) {
        object.wait(millis);
      }
    }
  }

  public static void sleep(long millis, int nanos) throws InterruptedException {
    if (millis < 0)
      throw new IllegalArgumentException("Milliseconds out of range");
    if (nanos < 0 || nanos > 999999)
      throw new IllegalArgumentException("Nanoseconds out of range");
    if (nanos >= 500000 || (millis == 0 && nanos > 0))
      millis++;
    sleep(millis);
  }
  
  public static boolean interrupted() {
    Thread current = currentThread();
    boolean interrupted = current.interrupted;
    current.interrupted = false;
    return interrupted;
  }

  public static int activeCount() {
    Thread current = currentThread();
    return current.group.activeCount();
  }

  public static int enumerate(Thread[] array) {
    Thread current = currentThread();
    return current.group.enumerate(array);
  }

  public static void dumpStack() {
    new Throwable("Stack trace").printStackTrace();
  }

  public static Class callerClass() {
    return lang.callerClass();
  }

  public static Class[] callStack() {
    return lang.callStack();
  }

  private final ThreadGroup group;
  private final Runnable target;
  private final long stackSize;
  
  private String name;
  private int priority;
  private boolean daemon;
  private boolean alive;
  private boolean destroyed;
  private volatile boolean interrupted;

  Thread previous;
  Thread next;

  Thread nextHook;

  private ClassLoader contextLoader;
  private AccessControlContext inheritedContext;

  private final WeakHashMap locals = new WeakHashMap();

  public Thread() {
    this("Thread-"+nextSerial());
  }

  public Thread(Runnable target) {
    this(target, "Thread-"+nextSerial());
  }

  public Thread(ThreadGroup group, Runnable target) {
    this(group, target, "Thread-"+nextSerial());
  }

  public Thread(String name) { 
    this(null, null, name, 0);
  }

  public Thread(ThreadGroup group, String name) {
    this(group, null, name, 0);
  }

  public Thread(Runnable target, String name) {
    this(null, target, name, 0);
  }

  public Thread(ThreadGroup group, Runnable target, String name) {
    this(group, target, name, 0);
  }

  public Thread(ThreadGroup group, Runnable target, String name, long stackSize) {
    if (name == null)
      throw new NullPointerException();
    if (stackSize < 0)
      throw new IllegalArgumentException("Negative stack size");
    Thread current = currentThread();
    if (group == null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null)
        group = sm.getThreadGroup();
      if (group == null)
        group = current == this ? ThreadGroup.getSystemGroup() : current.group;
    }
    group.checkAccess();
    this.group = group;
    this.target = target;
    this.name = name;
    this.stackSize = stackSize;
    if (current == this) {
      priority = NORM_PRIORITY;
      daemon = false;
      alive = true;
    } else {
      priority = current.priority;
      daemon = current.daemon;
      contextLoader = current.contextLoader;
      inheritedContext = AccessController.getContext();
      inheriteLocals(current);
    }
    group.add(this);
  }

  public AccessControlContext inheritedAccessControlContext() {
    return inheritedContext;
  }

  public void run() {
    if (target != null)
      target.run();
  }

  public final boolean isAlive() {
    return alive;
  }

  public synchronized void start() {
    if (alive)
      throw new IllegalThreadStateException("Thread already started");
    if (destroyed)
      throw new IllegalThreadStateException("Attempt to restart thread");
    alive = true;
    lang.start(this, priority, daemon, stackSize);
  }

  private synchronized void destroy(Throwable exception) {
    try {
      if (exception != null)
        group.uncaughtException(this, exception);
    } finally {
      group.remove(this);
      alive = false;
      destroyed = true;
      interrupted = false;
      notifyAll();
    }
  }
  
  public final void join() throws InterruptedException {
    join(0);
  }

  public final synchronized void join(long millis) throws InterruptedException {
    if (millis < 0)
      throw new IllegalArgumentException("Milliseconds out of range");
    while (alive) {
      long start = System.currentTimeMillis();
      wait(millis);
      long end = System.currentTimeMillis();
      if (millis != 0) {
        millis -= end-start;
        if (millis <= 0)
          break;
      }
    }
  }
  
  public final void join(long millis, int nanos) throws InterruptedException {
    if (millis < 0)
      throw new IllegalArgumentException("Milliseconds out of range");
    if (nanos < 0 || nanos > 999999)
      throw new IllegalArgumentException("Nanoseconds out of range");
    if (nanos >= 500000 || (millis == 0 && nanos > 0))
      millis++;
    join(millis);
  }

  public void interrupt() {
    checkAccess();
    if (alive) {
      interrupted = true;
      lang.unblock(this);
    }
  }

  public boolean isInterrupted() {
    return interrupted;
  }

  public final ThreadGroup getThreadGroup() {
    return destroyed ? null : group;
  }

  public final void setName(String name) {
    checkAccess();
    this.name = name;
  }

  public final String getName() {
    return name;
  }

  public final void setPriority(int priority) {
    checkAccess();
    if (priority < MIN_PRIORITY || priority > MAX_PRIORITY)
      throw new IllegalArgumentException("Illegal priority value");
    int maxPriority = group.getMaxPriority();
    if (priority > maxPriority)
      priority = maxPriority;
    this.priority = priority;
    if (alive)
      lang.changePriority(this, priority);
  }
  
  public final int getPriority() {
    return priority;
  }

  public final void setDaemon(boolean daemon) {
    checkAccess();
    if (alive)
      throw new IllegalThreadStateException("Attempt to make daemon a live thread");
    this.daemon = daemon;
  }
  
  public final boolean isDaemon() {
    return daemon;
  }

  public final void checkAccess() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkAccess(this);
  }

  public ClassLoader getContextClassLoader() {
    if (contextLoader != null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        Class clazz = callerClass();
        if (clazz != null) {
          ClassLoader caller = clazz.getDefiningLoader();
          if (caller != null && !caller.parentOf(contextLoader))
            sm.checkPermission(new RuntimePermission("getClassLoader"));
        }
      }
    }
    return contextLoader;
  }

  public void setContextClassLoader(ClassLoader loader) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("setContextClassLoader"));
    contextLoader = loader;
  }

  private void inheriteLocals(Thread thread) {
    Iterator iterator = thread.locals.keySet().iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      if (object instanceof InheritableThreadLocal) {
        InheritableThreadLocal local = (InheritableThreadLocal)object;
        setLocal(local, local.childValue(thread.getLocal(local)));
      }
    }
  }

  Object getLocal(ThreadLocal local) {
    return locals.get(local);
  }

  void setLocal(ThreadLocal local, Object value) {
    locals.put(local, value);
  }

  public String toString() {
    StringBuffer sb = new StringBuffer(32);
    sb.append("Thread");
    sb.append('[');
    sb.append(name);
    sb.append(',');
    sb.append(priority);
    sb.append(',');
    if (!destroyed)
      sb.append(group.getName());
    sb.append(']');
    return sb.toString();
  }

  public void destroy() {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public int countStackFrames() {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public void resume() {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public void suspend() {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public void stop() {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public void stop(Throwable throwable) {
    //implement
    throw new InternalError("Unimplemented");
  }

}

