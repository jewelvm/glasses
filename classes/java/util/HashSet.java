/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.Serializable;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

public class HashSet extends AbstractSet implements Cloneable, Serializable {

  private transient HashMap map;

  public HashSet() {
    map = new HashMap();
  }

  public HashSet(int capacity) {
    map = new HashMap(capacity);
  }

  public HashSet(int capacity, float factor) {
    map = new HashMap(capacity, factor);
  }

  public HashSet(Collection collection) {
    this(2*collection.size()+5);
    addAll(collection);
  }

  public int size() {
    return map.size();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public boolean contains(Object object) {
    return map.containsKey(object);
  }

  public Iterator iterator() {
    return map.keySet().iterator();
  }

  public boolean add(Object object) {
    return map.put(object, "") == null;
  }

  public boolean remove(Object object) {
    return map.remove(object) != null;
  }

  public void clear() {
    map.clear();
  }

  public Object clone() {
    try { 
      HashSet set = (HashSet)super.clone();
      set.map = (HashMap)map.clone();
      return set;
    } catch (CloneNotSupportedException e) { 
      throw new InternalError(e.getMessage());
    }
  }

//  private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
//  }

//  private void writeObject(ObjectOutputStream out) throws IOException {
//  }

}

