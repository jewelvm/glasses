/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.Serializable;

public class GuardedObject implements Serializable {

  private final Object object;
  private final Guard guard;

  public GuardedObject(Object object, Guard guard) {
    this.guard = guard;
    this.object = object;
  }

  public Object getObject() throws SecurityException {
    if (guard != null)
      guard.checkGuard(object);
    return object;
  }

}

