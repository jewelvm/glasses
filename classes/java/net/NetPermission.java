/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.security.BasicPermission;

public final class NetPermission extends BasicPermission {

  public NetPermission(String name) {
    super(name);
  }

  public NetPermission(String name, String actions) {
    super(name, actions);
  }

}

