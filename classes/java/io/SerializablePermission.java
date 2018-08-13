/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

import java.security.BasicPermission;

public final class SerializablePermission extends BasicPermission {

  public SerializablePermission(String name) {
    super(name);
  }

  public SerializablePermission(String name, String actions) {
    super(name, actions);
  }
    
}

