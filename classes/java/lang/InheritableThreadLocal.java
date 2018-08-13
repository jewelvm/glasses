/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class InheritableThreadLocal extends ThreadLocal {

  public InheritableThreadLocal() { }

  protected Object childValue(Object value) {
    return value;
  }

}

