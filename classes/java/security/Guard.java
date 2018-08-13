/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

public interface Guard {

  public void checkGuard(Object object) throws SecurityException;

}

