/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

public final class AccessController {

  private static final ThreadLocal local = new ThreadLocal();

  public static Object doPrivileged(PrivilegedAction action) {
    return doPrivileged(action, null);
  }

  public static Object doPrivileged(PrivilegedExceptionAction action) throws PrivilegedActionException {
    return doPrivileged(action, null);
  }

  public static Object doPrivileged(PrivilegedAction action, AccessControlContext context) {
    local.set(new PrivilegedFrame(context, (PrivilegedFrame)local.get()));
    try {
      return action.run();
    } finally {
      local.set(((PrivilegedFrame)local.get()).previous());
    }
  }

  public static Object doPrivileged(PrivilegedExceptionAction action, AccessControlContext context) throws PrivilegedActionException {
    local.set(new PrivilegedFrame(context, (PrivilegedFrame)local.get()));
    try {
      return action.run();
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new PrivilegedActionException(e);
    } finally {
      local.set(((PrivilegedFrame)local.get()).previous());
    }
  }

  public static AccessControlContext getContext() {
    PrivilegedFrame frame = (PrivilegedFrame)local.get();
    Class[] callStack = Thread.callStack();
    int privileged;
    AccessControlContext inherited;
    if (frame == null) {
      privileged = callStack.length;
      inherited = Thread.currentThread().inheritedAccessControlContext();
    } else {
      privileged = 0;
      for (int i = 1; i < callStack.length; i++)
        if (callStack[i] == AccessController.class) {
          privileged = i;
          break;
        }
      inherited = frame.context();
    }
    ProtectionDomain[] domains = new ProtectionDomain[privileged];
    for (int i = 0; i < domains.length; i++)
      domains[i] = callStack[i].getProtectionDomainNoCheck();
    if (inherited != null) {
      DomainCombiner combiner = inherited.getDomainCombinerNoCheck();
      if (combiner == null)
        combiner = new DomainCombiner() {
          public ProtectionDomain[] combine(ProtectionDomain[] current, ProtectionDomain[] assigned) {
            ProtectionDomain[] combined = new ProtectionDomain[current.length+assigned.length];
            System.arraycopy(current, 0, combined, 0, current.length);
            System.arraycopy(assigned, 0, combined, current.length, assigned.length);
            return combined;
          }
        };
      domains = combiner.combine(domains, inherited.getDomains());
    }
    return new AccessControlContext(domains);
  }

  public static void checkPermission(Permission permission) throws AccessControlException {
    AccessControlContext context = getContext();
    context.checkPermission(permission);
  }

  private AccessController() { }

  private static final class PrivilegedFrame {

    private final AccessControlContext context;
    private final PrivilegedFrame previous;

    public PrivilegedFrame(AccessControlContext context, PrivilegedFrame previous) {
      this.context = context;
      this.previous = previous;
    }

    public AccessControlContext context() {
      return context;
    }

    public PrivilegedFrame previous() {
      return previous;
    }

  }

}

