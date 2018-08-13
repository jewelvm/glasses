/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.lang.reflect.Array;

public abstract class AbstractCollection implements Collection {

  protected AbstractCollection() { }

  public abstract int size();

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(Object object) {
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object current = i.next();
      if (HashMap.equals(current, object))
        return true;
    }
    return false;
  }

  public abstract Iterator iterator();

  public Object[] toArray() {
    return toArray(new Object[size()]);
  }

  public Object[] toArray(Object[] array) {
    int size = size();
    if (array.length < size) {
      Class clazz = array.getClass();
      Class componentType = clazz.getComponentType();
      array = (Object[])Array.newInstance(componentType, size);
    }
    Iterator i = iterator();
    for (int j = 0; j < size; j++)
      array[j] = i.next();
    if (array.length > size)
      array[size] = null;
    return array;
  }

  public boolean add(Object object) {
    throw new UnsupportedOperationException();
  }

  public boolean remove(Object object) {
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object current = i.next();
      if (HashMap.equals(current, object)) {
        i.remove();
        return true;
      }
    }
    return false;
  }

  public boolean containsAll(Collection collection) {
    for (Iterator i = collection.iterator(); i.hasNext(); ) {
      Object current = i.next();
      if (!contains(current))
        return false;
    }
    return true;
  }

  public boolean addAll(Collection collection) {
    boolean changed = false;
    for (Iterator i = collection.iterator(); i.hasNext(); ) {
      Object current = i.next();
      if (add(current))
        changed = true;
    }
    return changed;
  }

  public boolean removeAll(Collection collection) {
    boolean changed = false;
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object current = i.next();
      if (collection.contains(current)) {
        i.remove();
        changed = true;
      }
    }
    return changed;
  }

  public boolean retainAll(Collection collection) {
    boolean changed = false;
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object current = i.next();
      if (!collection.contains(current)) {
        i.remove();
        changed = true;
      }
    }
    return changed;
  }

  public void clear() {
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object current = i.next();
      i.remove();
    }
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append('[');
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object current = i.next();
      sb.append(current);
      sb.append(", ");
    }
    int length = sb.length();
    if (length > 1)
      sb.setLength(length-2);
    sb.append(']');
    return sb.toString();
  }

}

