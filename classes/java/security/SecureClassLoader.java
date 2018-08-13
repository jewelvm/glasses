/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.util.HashMap;

public class SecureClassLoader extends ClassLoader {

  private final HashMap domains = new HashMap();

  protected SecureClassLoader() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkCreateClassLoader();
  }

  protected SecureClassLoader(ClassLoader parent) {
    super(parent);
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkCreateClassLoader();
  }

  protected final Class defineClass(String name, byte[] array, int start, int length, CodeSource codeSource) {
    ProtectionDomain domain = null;
    if (codeSource != null)
      synchronized (domains) {
        domain = (ProtectionDomain)domains.get(codeSource);
        if (domain == null) {
          PermissionCollection permissions = getPermissions(codeSource);
          domain = new ProtectionDomain(codeSource, permissions);
          domains.put(codeSource, domain);
        }
      }
    return defineClass(name, array, start, length, domain);
  }

  protected PermissionCollection getPermissions(CodeSource codeSource) {
    Policy policy = Policy.getPolicy();
    if (policy != null)
      return policy.getPermissions(codeSource);
    return null;
  }

}

