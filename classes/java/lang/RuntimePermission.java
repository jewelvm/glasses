/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.security.BasicPermission;

public final class RuntimePermission extends BasicPermission {

  public RuntimePermission(String name) {
    super(name);
  }

  public RuntimePermission(String name, String actions) {
    super(name, actions);
  }

}

