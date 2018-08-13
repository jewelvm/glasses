/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.Serializable;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

public class Hashtable extends Dictionary implements Cloneable, Map, Serializable {

  private static final long serialVersionUID = 1421746759512286392L;

  private transient HashMap map;

  private transient Set entrySet;
  private transient Set keySet;
  private transient Collection values;

  public Hashtable() {
    this.map = new HashMap();
  }

  public Hashtable(int capacity) {
    this.map = new HashMap(capacity);
  }

  public Hashtable(int capacity, float factor) {
    this.map = new HashMap(capacity, factor);
  }

  public Hashtable(Map map) {
    this.map = new HashMap(map);
  }

  public int size() {
    return map.size();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public synchronized Enumeration keys() {
    return new Enumeration() {
      private Iterator i = Hashtable.this.keySet().iterator();
      public boolean hasMoreElements() {
        for (;;)
          try {
            return i.hasNext();
          } catch (ConcurrentModificationException e) {
            i = Hashtable.this.keySet().iterator();
          }
      }
      public Object nextElement() {
        for (;;)
          try {
            return i.next();
          } catch (ConcurrentModificationException e) {
            i = Hashtable.this.keySet().iterator();
          }
      }
    };
  }

  public synchronized Enumeration elements() {
    return new Enumeration() {
      private Iterator i = Hashtable.this.values().iterator();
      public boolean hasMoreElements() {
        for (;;)
          try {
            return i.hasNext();
          } catch (ConcurrentModificationException e) {
            i = Hashtable.this.values().iterator();
          }
      }
      public Object nextElement() {
        for (;;)
          try {
            return i.next();
          } catch (ConcurrentModificationException e) {
            i = Hashtable.this.values().iterator();
          }
      }
    };
  }

  public synchronized boolean containsKey(Object key) {
    if (key == null)
      throw new NullPointerException();
    return map.containsKey(key);
  }

  public synchronized boolean containsValue(Object value) {
    if (value == null)
      throw new NullPointerException();
    return map.containsValue(value);
  }

  public boolean contains(Object value) {
    return containsValue(value);
  }

  public synchronized Object get(Object key) {
    if (key == null)
      throw new NullPointerException();
    return map.get(key);
  }

  public synchronized Object put(Object key, Object value) {
    if (key == null)
      throw new NullPointerException();
    if (value == null)
      throw new NullPointerException();
    return map.put(key, value);
  }

  public synchronized Object remove(Object key) {
    if (key == null)
      throw new NullPointerException();
    return map.remove(key);
  }

  protected void rehash() {
    map.rehash();
  }

  public synchronized void putAll(Map map) {
    this.map.putAll(map);
  }

  public synchronized void clear() {
    map.clear();
  }

  // Entry may setValue(null)
  public Set entrySet() {
    if (entrySet == null)
      entrySet = /*Collections.synchronizedSet(*/map.entrySet()/*, this)*/;
    return entrySet;
  }

  public Set keySet() {
    if (keySet == null)
      keySet = /*Collections.synchronizedSet(*/map.keySet()/*, this)*/;
    return keySet;
  }

  public Collection values() {
    if (values == null)
      values = /*Collections.synchronizedCollection(*/map.values()/*, this)*/;
    return values;
  }

  public synchronized Object clone() {
    try { 
      Hashtable t = (Hashtable)super.clone();
      t.map = (HashMap)map.clone();
      t.entrySet = null;
      t.keySet = null;
      t.values = null;
      return t;
    } catch (CloneNotSupportedException e) { 
      throw new InternalError(e.getMessage());
    }
  }

  public synchronized int hashCode() {
    return map.hashCode();
  }

  public synchronized boolean equals(Object object) {
    if (object == this)
      return true;
    return map.equals(object);
  }

  public synchronized String toString() {
    return map.toString();
  }

//  private synchronized void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
//  }

//  private synchronized void writeObject(ObjectOutputStream s) throws IOException {
//  }

}

