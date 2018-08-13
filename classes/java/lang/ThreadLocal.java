/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class ThreadLocal {

  private static final Object NULL = new Object();

  public ThreadLocal() { }

  protected Object initialValue() {
    return null;
  }

  public Object get() {
    Thread current = Thread.currentThread();
    Object value = current.getLocal(this);
    if (value == null) {
      value = initialValue();
      set(value);
    }
    if (value == NULL)
      value = null;
    return value;
  }

  public void set(Object value) {
    Thread current = Thread.currentThread();
    if (value == null)
      value = NULL;
    current.setLocal(this, value);
  }

}

