/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class BasicPermission extends Permission {

  protected BasicPermission(String name) {
    super(name);
  }

  protected BasicPermission(String name, String actions) {
    super(name);
  }

  public boolean implies(Permission permission) {
    if (permission == null)
      return false;
    if (permission.getClass() != getClass())
      return false;
    String name = getName();
    if (name.equals("*"))
      return true;
    String iname = ((BasicPermission)permission).getName();
    if (!name.endsWith(".*"))
      return iname.equals(name);
    name = name.substring(0, name.length()-1);
    return iname.startsWith(name);
  }

  public PermissionCollection newPermissionCollection() {
    return new PermissionCollection() {

      private final Hashtable permissions = new Hashtable();

      public void add(Permission permission) {
        if (!(permission instanceof BasicPermission))
          throw new IllegalArgumentException("Invalid permission");
        String name = ((BasicPermission)permission).getName();
        permissions.put(name, permission);
      }

      public boolean implies(Permission permission) {
        if (permission instanceof BasicPermission) {
          String name = ((BasicPermission)permission).getName();
          for (;;) {
            BasicPermission basicPermission = (BasicPermission)permissions.get(name);
            if (basicPermission != null)
              return basicPermission.implies(permission);
            if (name.equals("*"))
              break;
            int length = name.length();
            if (name.endsWith(".*"))
              length -= 2;
            int index = name.lastIndexOf('.', length-1);
            name = index == -1 ? "*" : name.substring(0, index)+".*";
          }
        }
        return false;
      }

      public Enumeration elements() {
        return permissions.elements();
      }

    };
  }

  public String getActions() {
    return "";
  }

  public int hashCode() {
    return getName().hashCode();
  }

  public boolean equals(Object object) {
    return object instanceof BasicPermission
        && getClass() == ((BasicPermission)object).getClass()
        && getName().equals(((BasicPermission)object).getName());
  }

}

