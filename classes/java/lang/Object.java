/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class Object {

  public Object() { }

  public final Class getClass() {
    return lang.getClass(this);
  }
  
  protected Object clone() throws CloneNotSupportedException {
    if (this instanceof Cloneable)
      return lang.clone(this);
    throw new CloneNotSupportedException();
  }

  public final void wait() throws InterruptedException {
    wait(0);
  }
  
  public final void wait(long millis) throws InterruptedException {
    lang.wait(this, millis);
  }

  public final void wait(long millis, int nanos) throws InterruptedException {
    if (millis < 0)
      throw new IllegalArgumentException("Milliseconds out of range");
    if (nanos < 0 || nanos > 999999)
      throw new IllegalArgumentException("Nanoseconds out of range");
    if (nanos >= 500000 || (millis == 0 && nanos > 0))
      millis++;
    wait(millis);
  }

  public final void notify() {
    lang.notify(this);
  }

  public final void notifyAll() {
    lang.notifyAll(this);
  }

  public int hashCode() {
    return System.identityHashCode(this);
  }

  public boolean equals(Object object) {
    return this == object;
  }

  public String toString() {
    return getClass().getName()+"@"+Integer.toHexString(hashCode());
  }

  protected void finalize() throws Throwable { }

}

