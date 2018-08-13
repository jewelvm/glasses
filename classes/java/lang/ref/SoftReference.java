/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.ref;

public class SoftReference extends Reference {

  public SoftReference(Object referent) { 
    super(referent);
  }

  public SoftReference(Object referent, ReferenceQueue queue) { 
    super(referent, queue);
  }

}

