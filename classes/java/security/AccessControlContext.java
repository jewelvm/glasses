/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.util.HashSet;
import java.util.Iterator;

public final class AccessControlContext {

  private final HashSet domainSet;
  private final DomainCombiner combiner;

  public AccessControlContext(ProtectionDomain[] domains) {
    domainSet = new HashSet();
    for (int i = 0; i < domains.length; i++)
      if (domains[i] != null)
        domainSet.add(domains[i]);
    combiner = null;
  }

  public AccessControlContext(AccessControlContext context, DomainCombiner combiner) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("createAccessControlContext");
    domainSet = context.domainSet;
    if (combiner == null)
      throw new NullPointerException();
    this.combiner = combiner;
  }

  public void checkPermission(Permission permission) throws AccessControlException {
    if (permission == null)
      throw new NullPointerException();
    for (Iterator i = domainSet.iterator(); i.hasNext(); ) {
      ProtectionDomain domain = (ProtectionDomain)i.next();
      if (!domain.implies(permission))
        throw new AccessControlException("Access denied", permission);
    }
  }

  public ProtectionDomain[] getDomains() {
    return (ProtectionDomain[])domainSet.toArray(new ProtectionDomain[domainSet.size()]);
  }

  protected DomainCombiner getDomainCombinerNoCheck() {
    return combiner;
  }

  public DomainCombiner getDomainCombiner() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("getDomainCombiner");
    return combiner;
  }

  public int hashCode() {
    return domainSet.hashCode();
  }

  public boolean equals(Object object) {
    if (object instanceof AccessControlContext) {
      AccessControlContext context = (AccessControlContext)object;
      if (!domainSet.equals(context.domainSet))
        return false;
      if (combiner != context.combiner && (combiner == null || !combiner.equals(context.combiner)))
        return false;
      return true;
    }
    return false;
  }

}

