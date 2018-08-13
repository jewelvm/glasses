/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public final class AllPermission extends Permission {

  public AllPermission() {
    super("<all permissions>");
  }

  public AllPermission(String name, String actions) {
    this();
  }

  public boolean implies(Permission permission) {
    return true;
  }

  public PermissionCollection newPermissionCollection() {
    return new PermissionCollection() {

      private boolean empty = true;

      public void add(Permission permission) {
        if (!(permission instanceof AllPermission))
          throw new IllegalArgumentException("Invalid permission");
        empty = false;
      }

      public boolean implies(Permission permission) {
        return !empty;
      }

      public Enumeration elements() {
        return new Enumeration() {
          private boolean over = empty;
          public boolean hasMoreElements() { return !over; }
          public Object nextElement() {
            if (over)
              throw new NoSuchElementException();
            over = true;
            return AllPermission.this;
          }
        };
      }

    };
  }

  public String getActions() {
    return "<all actions>";
  }

  public int hashCode() {
    return 1;
  }

  public boolean equals(Object object) {
    return object instanceof AllPermission;
  }

}

