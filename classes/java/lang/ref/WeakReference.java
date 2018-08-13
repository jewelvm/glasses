/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.ref;

public class WeakReference extends Reference {

  public WeakReference(Object referent) { 
    super(referent);
  }

  public WeakReference(Object referent, ReferenceQueue queue) {
    super(referent, queue);
  }

}

