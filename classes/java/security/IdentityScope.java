/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.util.Enumeration;

/** @deprecated */
public abstract class IdentityScope extends Identity {
  
  private static IdentityScope scope;
  
  protected static void setSystemScope(IdentityScope scope) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("setSystemScope");
    IdentityScope.scope = scope;
  }
  
  public static IdentityScope getSystemScope() {
    return scope;
  }
  
  protected IdentityScope() { }
  
  public IdentityScope(String name) {
    super(name);
  }
  
  public IdentityScope(String name, IdentityScope scope) throws KeyManagementException {
    super(name, scope);
  }
  
  public abstract int size();
  public abstract Identity getIdentity(String name);
  public abstract Identity getIdentity(PublicKey key);

  public Identity getIdentity(Principal principal) {
    return getIdentity(principal.getName());
  }
  
  public abstract void addIdentity(Identity identity) throws KeyManagementException;
  public abstract void removeIdentity(Identity identity) throws KeyManagementException;
  public abstract Enumeration identities();
  
  public String toString() {
    return super.toString()+", Size: "+size();
  }
  
}

