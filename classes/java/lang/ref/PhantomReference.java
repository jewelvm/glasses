/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.ref;

public class PhantomReference extends Reference {

  public PhantomReference(Object referent, ReferenceQueue queue) {
    super(referent, queue); 
  }

  public Object get() {
    return null;
  }

}

