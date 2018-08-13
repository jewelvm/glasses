/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.Serializable;
import java.util.Enumeration;

public abstract class PermissionCollection implements Serializable {

  private boolean readOnly;

  protected PermissionCollection() { }

  public boolean isReadOnly() {
    return readOnly;
  }

  public void setReadOnly() {
    readOnly = true;
  }

  public abstract boolean implies(Permission permission);
  public abstract void add(Permission permission);
  public abstract Enumeration elements();
  
  public String toString() {
    String eoln = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer();
    sb.append(super.toString());
    sb.append(" (");
    sb.append(eoln);
    for (Enumeration e = elements(); e.hasMoreElements(); ) {
      Permission permission = (Permission)e.nextElement();
      sb.append(" ");
      sb.append(permission);
      sb.append(eoln);
    }
    sb.append(")");
    sb.append(eoln);
    return sb.toString();
  }

}

