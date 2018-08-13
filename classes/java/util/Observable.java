/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public class Observable {

  private boolean changed;
  private final ArrayList observers = new ArrayList();
 
  public Observable() { }

  public synchronized boolean hasChanged() {
    return changed;
  }

  protected synchronized void clearChanged() {
    changed = false;
  }

  protected synchronized void setChanged() {
    changed = true;
  }

  public synchronized int countObservers() {
    return observers.size();
  }

  public synchronized void addObserver(Observer observer) {
    if (observer == null)
      throw new NullPointerException();
    if (!observers.contains(observer))
      observers.add(observer);
  }

  public synchronized void deleteObserver(Observer observer) {
    if (observer == null)
      throw new NullPointerException();
    observers.remove(observer);
  }

  public synchronized void deleteObservers() {
    observers.clear();
  }

  public void notifyObservers() {
    notifyObservers(null);
  }

  public void notifyObservers(Object argument) {
    Object[] array;
    synchronized (this) {
      if (!changed)
        return;
      changed = false;
      array = observers.toArray();
    }
    for (int i = 0; i < array.length; i++) {
      Observer observer = (Observer)array[i];
      observer.update(this, argument);
    }
  }

}

