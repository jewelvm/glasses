/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.ref;

public abstract class Reference {

  private ReferenceQueue queue;
  private Object referent;

  Reference next;

  Reference(Object referent) {
    this(referent, null);
  }

  Reference(Object referent, ReferenceQueue queue) {
    set(referent);
    this.queue = queue;
    if (queue == null)
      next = this;
  }

  public Object get() {
    return referent;
  }

  private void set(Object referent) {
    this.referent = referent;
  }

  public void clear() {
    set(null);
  }

  public synchronized boolean isEnqueued() {
    return queue == null
        && next != this;
  }
  
  public synchronized boolean enqueue() {
    ReferenceQueue queue = this.queue;
    if (queue == null)
      return false;
    this.queue = null;
    queue.enqueue(this);
    return true;
  }

}

