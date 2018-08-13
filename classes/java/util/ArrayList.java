/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.lang.reflect.Array;
import java.io.Serializable;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

public class ArrayList extends AbstractList implements Cloneable, RandomAccess, Serializable {
    
  private int size;
  private transient Object[] elements;

  public ArrayList() {
    this(10);
  }

  public ArrayList(int capacity) {
    if (capacity < 0)
      throw new IllegalArgumentException("Invalid capacity");
    this.elements = new Object[capacity];
  }

  public ArrayList(Collection collection) {
    this((collection.size()*11)/10);
    addAll(collection);
  }

  protected ArrayList(Object[] array) {
    this.size = array.length;
    this.elements = array;
  }

  public int size() {
    return size;
  }

  public void trimToSize() {
    modCount++;
    if (size < elements.length) {
      Object[] tmp = elements;
      elements = new Object[size];
      System.arraycopy(tmp, 0, elements, 0, size);
    }
  }

  public void ensureCapacity(int capacity) {
    modCount++;
    if (capacity > elements.length) {
      Object[] tmp = elements;
      elements = new Object[capacity];
      System.arraycopy(tmp, 0, elements, 0, size);
    }
  }

  public boolean contains(Object object) {
    return indexOf(object) != -1;
  }

  public Object get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(index);
    return elements[index];
  }

  public Object set(int index, Object object) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(index);
    Object oldvalue = elements[index];
    elements[index] = object;
    return oldvalue;
  }

  public boolean add(Object object) {
    add(size, object);
    return true;
  }

  public void add(int index, Object element) {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException(index);
    ensureCapacity(size+1);
    System.arraycopy(elements, index, elements, index+1, size-index);
    elements[index] = element;
    size++;
  }

  public boolean addAll(Collection collection) {
    return addAll(size, collection);
  }

  public boolean addAll(int index, Collection collection) {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException(index);
    int inc = collection.size();
    ensureCapacity(size+inc);
    System.arraycopy(elements, index, elements, index+inc, size-index);
    for (Iterator i = collection.iterator(); i.hasNext(); ) {
      Object object = i.next();
      elements[index++] = object;
    }
    size += inc;
    return inc != 0;
  }

  public Object remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(index);
    modCount++;
    Object oldvalue = elements[index];
    System.arraycopy(elements, index+1, elements, index, size-index-1);
    elements[--size] = null;
    return oldvalue;
  }

  public boolean remove(Object object) {
    int index = indexOf(object);
    if (index == -1)
      return false;
    remove(index);
    return true;
  }

  protected void removeRange(int start, int end) {
    if (start < 0 || start > size)
      throw new IndexOutOfBoundsException(start);
    if (end < start || end > size)
      throw new IndexOutOfBoundsException(end);
    modCount++;
    System.arraycopy(elements, end, elements, start, size-end);
    int length = end-start;
    for (int i = 0; i < length; i++)
      elements[--size] = null;
  }

  public void clear() {
    modCount++;
    for (int i = 0; i < size; i++)
      elements[i] = null;
    size = 0;
  }

  public int indexOf(Object object) {
    for (int i = 0; i < size; i++)
      if (HashMap.equals(elements[i], object))
        return i;
    return -1;
  }

  public int lastIndexOf(Object object) {
    for (int i = size-1; i >= 0; i--)
      if (HashMap.equals(elements[i], object))
        return i;
    return -1;
  }

  public Object clone() {
    try { 
      ArrayList list = (ArrayList)super.clone();
      list.elements = (Object[])elements.clone();
      list.modCount = 0;
      return list;
    } catch (CloneNotSupportedException e) { 
      throw new InternalError(e.getMessage());
    }
  }

  public Object[] toArray() {
    return toArray(new Object[size]);
  }

  public Object[] toArray(Object[] array) {
    if (array.length < size)
      array = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
    System.arraycopy(elements, 0, array, 0, size);
    if (array.length > size)
      array[size] = null;
    return array;
  }

//  private synchronized void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
//  }

//  private synchronized void writeObject(ObjectOutputStream s) throws IOException{
//  }

}

