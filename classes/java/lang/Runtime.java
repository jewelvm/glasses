/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

public class Runtime {

  private static final Runtime runtime = new Runtime();

  public static Runtime getRuntime() {
    return runtime;
  }

  private static void enqueueFinalizable(Object object) {
    FinalizationQueue.enqueue(object);
  }

  /** @deprecated */
  public static void runFinalizersOnExit(boolean on) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkExit(0); 
    synchronized (runtime) {
      runtime.runFinalizersOnExit = on;
    }
  }

  private static void runShutdownHooks() {
    runtime.shutdown();
  }

  private static final class FinalizationQueue {
  
    private static FinalizationQueue head;

    private static synchronized void enqueue(Object finalizee) {
      if (finalizee == null)
        throw new InternalError("Attempt to enqueue a null reference for finalization");
      FinalizationQueue head = finalizee instanceof FinalizationQueue
                             ? (FinalizationQueue)finalizee : new FinalizationQueue();
      head.finalizee = finalizee;
      head.next = FinalizationQueue.head;
      FinalizationQueue.head = head;
    }

    private static void process() {
      Object finalizee = poll();
      while (finalizee != null) {
        try {
          finalizee.finalize();
        } catch (Throwable e) {
          e.printStackTrace();
        }
        finalizee = poll();
      }
    }
  
    private static synchronized Object poll() {
      FinalizationQueue head = FinalizationQueue.head;
      if (head == null)
        return null;
      else {
        FinalizationQueue.head = head.next;
        return head.finalizee;
      }
    }
  
    private Object finalizee;
    private FinalizationQueue next;

    private FinalizationQueue() { }

  }

  private boolean runFinalizersOnExit;
  private Thread hooks;
  private Thread shutdownThread;

  private Runtime() { }
  
  public void gc() {
    lang.gc();
  }

  public long freeMemory() {
    return lang.freeMemory();
  }

  public long totalMemory() {
    return lang.totalMemory();
  }

  public long maxMemory() {
    return lang.maxMemory();
  }

  public int availableProcessors() {
    return lang.availableProcessors();
  }
  
  public void traceMethodCalls(boolean on) {
    lang.traceMethodCalls(on);
  }

  public void traceInstructions(boolean on) {
    lang.traceInstructions(on);
  }

  public void runFinalization() {
    Thread finalizer = new Thread() {
      public void run() {
        FinalizationQueue.process();
      }
    };
    finalizer.start();
    try {
      finalizer.join();
    } catch (InterruptedException e) { }
  }

  public synchronized void addShutdownHook(Thread hook) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("shutdownHooks"));
    if (shutdownThread != null)
      throw new IllegalStateException("Shutdown in progress");
    if (hook.nextHook != null)
      throw new IllegalArgumentException("Hook already registered");
    hook.nextHook = hooks == null ? hook : hooks;
    hooks = hook;
  }

  public synchronized boolean removeShutdownHook(Thread hook) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("shutdownHooks"));
    if (shutdownThread != null)
      throw new IllegalStateException("Shutdown in progress");
    if (hook.nextHook == null)
      return false;
    Thread previousHook = null;
    for (Thread currentHook = hooks; currentHook != hook; currentHook = currentHook.nextHook)
      previousHook = currentHook;
    Thread nextHook = hook.nextHook == hook ? previousHook : hook.nextHook;
    if (previousHook == null)
      hooks = nextHook;
    else
      previousHook.nextHook = nextHook;
    hook.nextHook = null;
    return true;
  }

  public void exit(int status) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkExit(status);
    shutdown();
    lang.exit(status);
  }

  public void halt(int status) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkExit(status);
    lang.exit(status);
  }

  private void shutdown() {
    Thread shutdownThread;
    Thread currentThread = Thread.currentThread();
    synchronized (this) {
      shutdownThread = this.shutdownThread;
      if (shutdownThread == null) {
        shutdownThread = currentThread;
        this.shutdownThread = shutdownThread;
      }
    }
    if (shutdownThread != currentThread)
      for (;;)
        try {
          shutdownThread.join();
          return;
        } catch (InterruptedException e) {
          continue;
        }
    for (Thread previousHook = null, currentHook = hooks; currentHook != previousHook; previousHook = currentHook, currentHook = currentHook.nextHook)
      try {
        currentHook.start();
      } catch (Throwable e) {
        e.printStackTrace();
      }
    for (Thread previousHook = null, currentHook = hooks; currentHook != previousHook; previousHook = currentHook, currentHook = currentHook.nextHook)
      for (;;)
        try {
          currentHook.join();
          break;
        } catch (InterruptedException e) {
          continue;
        }
    if (runFinalizersOnExit)
      runFinalization();
  }

  public Process exec(String command) throws IOException {
    return exec(command, null);
  }

  public Process exec(String[] command) throws IOException {
    return exec(command, null);
  }

  public Process exec(String command, String[] environment) throws IOException {
    return exec(command, environment, null);
  }

  public Process exec(String[] command, String[] environment) throws IOException {
    return exec(command, environment, null);
  }
  
  public Process exec(String command, String[] environment, File directory) throws IOException {
    StringTokenizer tokenizer = new StringTokenizer(command);
    String[] array = new String[tokenizer.countTokens()];
    for (int i = 0; tokenizer.hasMoreTokens(); i++)
      array[i] = tokenizer.nextToken();
    return exec(array, environment, directory);
  }

  public Process exec(String[] command, String[] environment, File directory) throws IOException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkExec(command[0]);
    return lang.exec(command, environment, directory.getPath());
  }
  
  public void load(String libraryPath) {
    Class caller = Thread.callerClass();
    ClassLoader loader = caller == null ? Thread.currentThread().getContextClassLoader() : caller.getDefiningLoader();
    if (loader != null)
      loader.loadLibrary(libraryPath, true);
    else
      ClassLoader.loadSystemLibrary(libraryPath, true);
  }

  public void loadLibrary(String libraryName) {
    Class caller = Thread.callerClass();
    ClassLoader loader = caller == null ? Thread.currentThread().getContextClassLoader() : caller.getDefiningLoader();
    if (loader != null)
      loader.loadLibrary(libraryName, false);
    else
      ClassLoader.loadSystemLibrary(libraryName, false);
  }

  /** @deprecated */
  public InputStream getLocalizedInputStream(InputStream in) {
    return in;
  }

  /** @deprecated */
  public OutputStream getLocalizedOutputStream(OutputStream out) {
    return out;
  }

}

