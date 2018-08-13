/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.Serializable;

public abstract class Permission implements Guard, Serializable {

  private final String name;

  protected Permission(String name) {
    if (name == null)
      throw new NullPointerException();
    this.name = name;
  }

  public final String getName() {
    return name;
  }

  public void checkGuard(Object object) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(this);
  }

  public abstract boolean implies(Permission permission);
  
  public PermissionCollection newPermissionCollection() {
    return null;
  }

  public abstract String getActions();
  
  public abstract int hashCode();
  public abstract boolean equals(Object object);
    
  public String toString() {
    return "("+getClass().getName()+" "+name+" "+getActions()+")";
  }

}

