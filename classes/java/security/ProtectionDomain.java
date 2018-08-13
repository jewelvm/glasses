/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

public class ProtectionDomain {

  private final CodeSource codeSource;
  private final PermissionCollection permissions;

  public ProtectionDomain(CodeSource codeSource, PermissionCollection permissions) {
    this.codeSource = codeSource;
    this.permissions = permissions;
    if (permissions != null)
      permissions.setReadOnly();
  }

  public final CodeSource getCodeSource() {
    return codeSource;
  }

  public final PermissionCollection getPermissions() {
    return permissions;
  }

  public boolean implies(Permission permission) {
    return permissions != null
        && permissions.implies(permission);
  }

  public String toString() {
    String eoln = System.getProperty("line.separator");
    return "ProtectionDomain "+codeSource+eoln+permissions+eoln;
  }

}

