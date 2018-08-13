/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.ref;

public class ReferenceQueue {

  private Reference head;

  public ReferenceQueue() { }

  synchronized void enqueue(Reference reference) {
    reference.next = head;
    head = reference;
    notifyAll();
  }

  public synchronized Reference poll() {
    Reference reference = head;
    if (reference != null) {
      head = reference.next;
      reference.next = reference;
    }
    return reference;
  }
  
  public Reference remove() throws InterruptedException {
    return remove(0);
  }

  public Reference remove(long millis) throws InterruptedException {
    if (millis < 0)
      throw new IllegalArgumentException("Milliseconds out of range");
    Reference reference = poll();
    while (reference == null) {
      long start = System.currentTimeMillis();
      synchronized (this) {
        wait(millis);
      }
      long end = System.currentTimeMillis();
      reference = poll();
      if (millis != 0) {
        millis -= end-start;
        if (millis <= 0)
          break;
      }
    }
    return reference;
  }

}

