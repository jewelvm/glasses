/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.lang.reflect.Array;
import java.io.Serializable;

public class Vector extends AbstractList implements Cloneable, RandomAccess, Serializable {

  private static final long serialVersionUID = -2767605614048989439L;

  protected int elementCount;
  protected Object[] elementData;
  protected int capacityIncrement;

  public Vector() {
    this(10);
  }

  public Vector(int capacity) {
    this(capacity, 0);
  }

  public Vector(int capacity, int capacityIncrement) {
    if (capacity < 0)
      throw new IllegalArgumentException("Invalid capacity");
    this.elementData = new Object[capacity];
    this.capacityIncrement = capacityIncrement;
  }

  public Vector(Collection collection) {
    this((collection.size()*11)/10);
    addAll(collection);
  }

  public int size() {
    return elementCount;
  }

  public int capacity() {
    return elementData.length;
  }

  public synchronized void setSize(int size) {
    if (size > elementCount)
      ensureCapacity(size);
    else {
      modCount++;
      for (int i = size; i < elementCount; i++)
        elementData[i] = null;
    }
    elementCount = size;
  }

  public synchronized void trimToSize() {
    modCount++;
    if (elementCount < elementData.length) {
      Object[] tmp = elementData;
      elementData = new Object[elementCount];
      System.arraycopy(tmp, 0, elementData, 0, elementCount);
    }
  }

  public synchronized void ensureCapacity(int capacity) {
    modCount++;
    if (capacity > elementData.length) {
      if (elementData.length+capacityIncrement > capacity)
        capacity = elementData.length+capacityIncrement;
      Object[] tmp = elementData;
      elementData = new Object[capacity];
      System.arraycopy(tmp, 0, elementData, 0, elementCount);
    }
  }
  
  public boolean contains(Object object) {
    return indexOf(object) != -1;
  }

  public synchronized boolean containsAll(Collection collection) {
    return super.containsAll(collection);
  }

  public Object get(int index) {
    return elementAt(index);
  }

  public synchronized Object set(int index, Object object) {
    if (index < 0 || index >= elementCount)
      throw new ArrayIndexOutOfBoundsException(index);
    Object oldvalue = elementData[index];
    elementData[index] = object;
    return oldvalue;
  }

  public boolean add(Object object) {
    ensureCapacity(elementCount+1);
    elementData[elementCount++] = object;
    return true;
  }

  public void add(int index, Object object) {
    insertElementAt(object, index);
  }

  public boolean addAll(Collection collection) {
    return addAll(elementCount, collection);
  }

  public synchronized boolean addAll(int index, Collection collection) {
    if (index < 0 || index > elementCount)
      throw new ArrayIndexOutOfBoundsException(index);	    
    int inc = collection.size();
    ensureCapacity(elementCount+inc);
    System.arraycopy(elementData, index, elementData, index+inc, elementCount-index);
    for (Iterator i = collection.iterator(); i.hasNext(); ) {
      Object object = i.next();
      elementData[index++] = object;
    }
    elementCount += inc;
    return inc != 0;
  }

  public synchronized Object remove(int index) {
    if (index < 0 || index >= elementCount)
      throw new ArrayIndexOutOfBoundsException(index);
    modCount++;
    Object oldvalue = elementData[index];
    System.arraycopy(elementData, index+1, elementData, index, elementCount-index-1);
    elementData[--elementCount] = null;
    return oldvalue;
  }

  public boolean remove(Object object) {
    return removeElement(object);
  }

  public synchronized boolean removeAll(Collection collection) {
    return super.removeAll(collection);
  }

  protected void removeRange(int start, int end) {
    if (start < 0 || start > elementCount)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > elementCount)
      throw new ArrayIndexOutOfBoundsException(end);
    modCount++;
    System.arraycopy(elementData, end, elementData, start, elementCount-end);
    int length = end-start;
    for (int i = 0; i < length; i++)
      elementData[--elementCount] = null;
  }

  public synchronized boolean retainAll(Collection collection)  {
    return super.retainAll(collection);
  }

  public void clear() {
    removeAllElements();
  }

  public synchronized Object elementAt(int index) {
    if (index < 0 || index >= elementCount)
      throw new ArrayIndexOutOfBoundsException(index);
    return elementData[index];
  }

  public synchronized void setElementAt(Object object, int index) {
    if (index < 0 || index >= elementCount)
      throw new ArrayIndexOutOfBoundsException(index);
    elementData[index] = object;
  }

  public synchronized void addElement(Object object) {
    ensureCapacity(elementCount+1);
    elementData[elementCount++] = object;
  }

  public synchronized void insertElementAt(Object object, int index) {
    if (index < 0 || index > elementCount)
      throw new ArrayIndexOutOfBoundsException(index);
    ensureCapacity(elementCount+1);
    System.arraycopy(elementData, index, elementData, index+1, elementCount-index);
    elementData[index] = object;
    elementCount++;
  }

  public synchronized void removeElementAt(int index) {
    if (index < 0 || index >= elementCount)
      throw new ArrayIndexOutOfBoundsException(index);
    modCount++;
    System.arraycopy(elementData, index+1, elementData, index, elementCount-index-1);
    elementData[--elementCount] = null;
  }

  public synchronized boolean removeElement(Object object) {
    int index = indexOf(object);
    if (index == -1)
      return false;
    removeElementAt(index);
    return true;
  }

  public synchronized void removeAllElements() {
    modCount++;
    for (int i = 0; i < elementCount; i++)
      elementData[i] = null;
    elementCount = 0;
  }

  public synchronized Object firstElement() {
    if (elementCount == 0)
      throw new NoSuchElementException();
    return elementData[0];
  }

  public synchronized Object lastElement() {
    if (elementCount == 0)
      throw new NoSuchElementException();
    return elementData[elementCount-1];
  }

  public int indexOf(Object object) {
    return indexOf(object, 0);
  }

  public synchronized int indexOf(Object object, int index) {
    if (index < 0)
      throw new ArrayIndexOutOfBoundsException(index);
    for (int i = index; i < elementCount; i++)
      if (HashMap.equals(elementData[i], object))
        return i;
    return -1;
  }

  public int lastIndexOf(Object object) {
    return lastIndexOf(object, Integer.MAX_VALUE);
  }

  public synchronized int lastIndexOf(Object object, int index) {
    if (index < 0)
      throw new ArrayIndexOutOfBoundsException(index);
    if (index >= elementCount)
      index = elementCount-1;
    for (int i = index; i >= 0; i--)
      if (HashMap.equals(elementData[i], object))
        return i;
    return -1;
  }

  public Enumeration elements() {
    return new Enumeration() {
      private int index;
      public boolean hasMoreElements() { return index < elementCount; }
      public Object nextElement() {
        synchronized (Vector.this) {
          if (index >= elementCount)
            throw new NoSuchElementException();
          return elementData[index++];
        }
      }
    };
  }

  public synchronized void copyInto(Object[] array) {
    System.arraycopy(elementData, 0, array, 0, elementCount);
  }

  public List subList(int start, int end) {
    return /*Collections.synchronizedList(*/super.subList(start, end)/*, this)*/;
  }

  public synchronized Object clone() {
    try { 
      Vector vector = (Vector)super.clone();
      vector.elementData = (Object[])elementData.clone();
      vector.modCount = 0;
      return vector;
    } catch (CloneNotSupportedException e) { 
      throw new InternalError(e.getMessage());
    }
  }

  public synchronized int hashCode() {
    return super.hashCode();
  }

  public synchronized boolean equals(Object object) {
    return super.equals(object);
  }

  public synchronized String toString() {
    return super.toString();
  }

  public Object[] toArray() {
    return toArray(new Object[elementCount]);
  }

  public synchronized Object[] toArray(Object[] array) {
    if (array.length < elementCount)
      array = (Object[])Array.newInstance(array.getClass().getComponentType(), elementCount);
    System.arraycopy(elementData, 0, array, 0, elementCount);
    if (array.length > elementCount)
      array[elementCount] = null;
    return array;
  }

}

