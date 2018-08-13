/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.PrintStream;

public class ThreadGroup {

  private static final ThreadGroup systemGroup = new ThreadGroup();

  static ThreadGroup getSystemGroup() {
    return systemGroup;
  }

  private final ThreadGroup parent;
  private final String name;
  private int maxPriority;
  boolean daemon;
  boolean destroyed;

  private int threadCount;
  private Thread threads;

  private int groupCount;
  private ThreadGroup groups;

  private ThreadGroup previous;
  private ThreadGroup next;
  
  private ThreadGroup() {
    parent = null;
    name = "system";
    maxPriority = Thread.MAX_PRIORITY;
  }
  
  public ThreadGroup(String name) {
    this(Thread.currentThread().getThreadGroup(), name);
  }
  
  public ThreadGroup(ThreadGroup parent, String name) {
    parent.checkAccess();
    this.parent = parent;
    this.name = name;
    this.maxPriority = parent.maxPriority;
    this.daemon = parent.daemon;
    parent.add(this);
  }

  private synchronized void add(ThreadGroup group) {
    if (destroyed)
      throw new IllegalThreadStateException("Thread group has been destroyed");
    if (groups != null)
      groups.previous = group;
    group.next = groups;
    groups = group;
    groupCount++;
  }

  private void remove(ThreadGroup group) {
    if (group.previous != null)
      group.previous.next = group.next;
    if (group.next != null)
      group.next.previous = group.previous;
    if (groups == group)
      groups = group.next;
    groupCount--;
  }

  synchronized void add(Thread thread) {
    if (destroyed)
      throw new IllegalThreadStateException("Thread group has been destroyed");
    if (threads != null)
      threads.previous = thread;
    thread.next = threads;
    threads = thread;
    threadCount++;
  }

  // should destroy empty daemon group
  synchronized void remove(Thread thread) {
    if (thread.previous != null)
      thread.previous.next = thread.next;
    if (thread.next != null)
      thread.next.previous = thread.previous;
    if (threads == thread)
      threads = thread.next;
    threadCount--;
  }

  public final boolean parentOf(ThreadGroup group) {
    while (group != null) {
      if (group == this)
        return true;
      group = group.parent;
    }
    return false;
  }

  public final ThreadGroup getParent() {
    if (parent != null)
      parent.checkAccess();
    return parent;
  }
  
  public final String getName() {
    return name;
  }

  public final int getMaxPriority() {
    return maxPriority;
  }

  public final synchronized void setMaxPriority(int priority) {
    checkAccess();
    if (priority < Thread.MIN_PRIORITY)
      maxPriority = Thread.MIN_PRIORITY;
    else if (priority < maxPriority)
      maxPriority = priority;
    for (ThreadGroup group = groups; group != null; group = group.next)
      group.setMaxPriority(priority);
  }
  
  public final boolean isDaemon() {
    return daemon;
  }

  public final void setDaemon(boolean daemon) {
    checkAccess();
    this.daemon = daemon;
  }

  public synchronized boolean isDestroyed() {
    return destroyed;
  }

  public final void destroy() {
    checkAccess();
    synchronized (parent != null ? parent : this) {
      synchronized (this) {
        if (destroyed)
          throw new IllegalThreadStateException("Thread group has been destroyed");
        if (threadCount > 0)
          throw new IllegalThreadStateException("Thread group has active threads");
        for (ThreadGroup group = groups; group != null; group = group.next)
          group.destroy();
        if (parent != null) {
          parent.remove(this);
          destroyed = true;
        }
      }
    }
    ThreadGroup group = parent;
    while (group != null) {
      synchronized (group.parent != null ? group.parent : group) {
        synchronized (group) {
          if (group.daemon && group.threadCount == 0 && group.groupCount == 0) {
            if (group.parent != null) {
              group.parent.remove(this);
              group.destroyed = true;
            }
          }
        }
      }
      if (!group.destroyed)
        break;
      group = group.parent;
    }
  }

  public final synchronized void interrupt() {
    checkAccess();
    for (Thread thread = threads; thread != null; thread = thread.next)
      thread.interrupt();
    for (ThreadGroup group = groups; group != null; group = group.next)
      group.interrupt();
  }

  public synchronized int activeCount() {
    int count = 0;
    if (!destroyed) {
      count = threadCount;
      for (ThreadGroup group = groups; group != null; group = group.next)
        count += group.activeCount();
    }
    return count;
  }

  public int enumerate(Thread[] array) {
    return enumerate(array, true, 0);
  }

  public int enumerate(Thread[] array, boolean recurse) {
    return enumerate(array, recurse, 0);
  }

  private synchronized int enumerate(Thread[] array, boolean recurse, int start) {
    checkAccess();
    int count = 0;
    if (!destroyed) {
      for (Thread thread = threads; thread != null; thread = thread.next) {
        if (start+count == array.length)
          return count;
        array[start+count] = thread;
        count++;
      }
      if (recurse)
        for (ThreadGroup group = groups; group != null; group = group.next) {
          if (start+count == array.length)
            return count;
          count += group.enumerate(array, true, start+count);
        }
    }
    return count;
  }

  public synchronized int activeGroupCount() {
    int count = 0;
    if (!destroyed) {
      count = groupCount;
      for (ThreadGroup group = groups; group != null; group = group.next)
        count += group.activeGroupCount();
    }
    return count;
  }

  public int enumerate(ThreadGroup[] array) {
    return enumerate(array, true, 0);
  }

  public int enumerate(ThreadGroup[] array, boolean recurse) {
    return enumerate(array, recurse, 0);
  }

  private synchronized int enumerate(ThreadGroup[] array, boolean recurse, int start) {
    checkAccess();
    int count = 0;
    if (!destroyed)
      for (ThreadGroup group = groups; group != null; group = group.next) {
        if (start+count == array.length)
          return count;
        array[start+count] = group;
        count++;
        if (recurse)
          count += group.enumerate(array, true, start+count);
      }
    return count;
  }

  public void list() {
    list(System.out, 0);
  }

  private synchronized void list(PrintStream out, int indent) {
    for (int i = 0; i < indent; i++)
      out.print(' ');
    out.println(this);
    indent += 4;
    for (Thread thread = threads; thread != null; thread = thread.next) {
      for (int i = 0; i < indent; i++)
        out.print(' ');
      out.println(thread);
    }
    for (ThreadGroup group = groups; group != null; group = group.next)
      group.list(out, indent);
  }
  
  public void uncaughtException(Thread thread, Throwable exception) {
    if (parent != null)
      parent.uncaughtException(thread, exception);
    else if (!(exception instanceof ThreadDeath))
      exception.printStackTrace();
  }

  public final void checkAccess() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkAccess(this);
  }

  public String toString() {
    return getClass().getName()+"[name="+name+",maxpri="+maxPriority+"]";
  }

  /** @deprecated */
  public boolean allowThreadSuspension(boolean allow) {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public final void resume() {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public final void suspend() {
    //implement
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public final void stop() {
    //implement
    throw new InternalError("Unimplemented");
  }

}

