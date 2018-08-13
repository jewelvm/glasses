/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public abstract class AbstractList extends AbstractCollection implements List {

  protected transient int modCount;

  protected AbstractList() { }

  public abstract Object get(int index);

  public Object set(int index, Object object) {
    throw new UnsupportedOperationException();
  }

  public boolean add(Object object) {
    add(size(), object);
    return true;
  }

  public void add(int index, Object object) {
    throw new UnsupportedOperationException();
  }

  public boolean addAll(int index, Collection collection) {
    boolean changed = false;
    for (Iterator i = collection.iterator(); i.hasNext(); index++) {
      Object object = i.next();
      add(index, object);
      changed = true;
    }
    return changed;
  }

  public Object remove(int index) {
    throw new UnsupportedOperationException();
  }

  public void clear() {
    removeRange(0, size());
  }

  protected void removeRange(int start, int end) {
    ListIterator i = listIterator(start);
    for (int j = start; j < end; j++) {
      i.next();
      i.remove();
    }
  }

  public int indexOf(Object object) {
    for (ListIterator i = listIterator(); i.hasNext(); )
      if (HashMap.equals(object, i.next()))
        return i.previousIndex();
    return -1;
  }

  public int lastIndexOf(Object object) {
    for (ListIterator i = listIterator(size()); i.hasPrevious(); )
      if (HashMap.equals(object, i.previous()))
        return i.nextIndex();
    return -1;
  }

  public Iterator iterator() {
    return listIterator();
  }

  public ListIterator listIterator() {
    return listIterator(0);
  }

  public ListIterator listIterator(int index) {
    if (index < 0 || index > size())
      throw new IndexOutOfBoundsException(index);
    return new ListItr(index);
  }

  public List subList(int start, int end) {
    return new SubList(this, start, end);
  }

  public int hashCode() {
    int hashCode = 1;
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object object = i.next();
      hashCode = 31*hashCode+HashMap.hashCode(object);
    }
    return hashCode;
  }

  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (object instanceof List) {
      List list = (List)object;
      if (size() != list.size())
        return false;
      ListIterator j = list.listIterator();
      for (ListIterator i = listIterator(); i.hasNext(); )
        if (!HashMap.equals(i.next(), j.next()))
          return false;
      return true;
    }
    return false;
  }

  // review from here
  private class ListItr implements ListIterator {
    int cursor = 0;
    int lastRet = -1;
    int expectedModCount = modCount;

    ListItr(int index) {
      cursor = index;
    }

    public boolean hasNext() {
      return cursor != size();
    }

    public Object next() {
      try {
        Object next = get(cursor);
        checkForComodification();
        lastRet = cursor++;
        return next;
      } catch(IndexOutOfBoundsException e) {
        checkForComodification();
        throw new NoSuchElementException();
      }
    }

    public void remove() {
      if (lastRet == -1)
        throw new IllegalStateException();

      try {
        AbstractList.this.remove(lastRet);
        if (lastRet < cursor)
          cursor--;
        lastRet = -1;

        int newModCount = modCount;
        if (newModCount - expectedModCount > 1)
          throw new ConcurrentModificationException();
        expectedModCount = newModCount;
      } catch(IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

    final void checkForComodification() {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
    }

    public boolean hasPrevious() {
      return cursor != 0;
    }

    public Object previous() {
      try {
        Object previous = get(--cursor);
        checkForComodification();
        lastRet = cursor;
        return previous;
      } catch(IndexOutOfBoundsException e) {
        checkForComodification();
        throw new NoSuchElementException();
      }
    }

    public int nextIndex() {
      return cursor;
    }

    public int previousIndex() {
      return cursor-1;
    }

    public void set(Object o) {
      if (lastRet == -1)
        throw new IllegalStateException();
      try {
        AbstractList.this.set(lastRet, o);

        int newModCount = modCount;
        if (newModCount - expectedModCount > 1)
          throw new ConcurrentModificationException();
        expectedModCount = newModCount;
      } catch(IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

    public void add(Object o) {
      try {
        AbstractList.this.add(cursor++, o);
        lastRet = -1;

        int newModCount = modCount;
        if (newModCount - expectedModCount > 1)
          throw new ConcurrentModificationException();
        expectedModCount = newModCount;
      } catch(IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

  }

  private static class SubList extends AbstractList {
  
    private AbstractList l;
    private int offset;
    private int size;
    private int expectedModCount;
  
    SubList(AbstractList list, int fromIndex, int toIndex) {
      if (fromIndex < 0)
        throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
      if (toIndex > list.size())
        throw new IndexOutOfBoundsException("toIndex = " + toIndex);
      if (fromIndex > toIndex)
        throw new IllegalArgumentException("fromIndex(" + fromIndex +") > toIndex(" + toIndex + ")");
      l = list;
      offset = fromIndex;
      size = toIndex - fromIndex;
      expectedModCount = l.modCount;
    }
  
    public Object set(int index, Object element) {
      rangeCheck(index);
      checkForComodification();
      return l.set(index+offset, element);
    }
  
    public Object get(int index) {
      rangeCheck(index);
      checkForComodification();
      return l.get(index+offset);
    }
  
    public int size() {
      checkForComodification();
      return size;
    }
  
    public void add(int index, Object element) {
      if (index<0 || index>size)
        throw new IndexOutOfBoundsException();
      checkForComodification();
      l.add(index+offset, element);
      expectedModCount = l.modCount;
      size++;
      modCount++;
    }
  
    public Object remove(int index) {
      rangeCheck(index);
      checkForComodification();
      Object result = l.remove(index+offset);
      expectedModCount = l.modCount;
      size--;
      modCount++;
      return result;
    }
  
    protected void removeRange(int fromIndex, int toIndex) {
      checkForComodification();
      l.removeRange(fromIndex+offset, toIndex+offset);
      expectedModCount = l.modCount;
      size -= (toIndex-fromIndex);
      modCount++;
    }
  
    public boolean addAll(Collection c) {
      return addAll(size, c);
    }
  
    public boolean addAll(int index, Collection c) {
      if (index<0 || index>size)
        throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
      int cSize = c.size();
      if (cSize==0)
        return false;
      checkForComodification();
      l.addAll(offset+index, c);
      expectedModCount = l.modCount;
      size += cSize;
      modCount++;
      return true;
    }
  
    public Iterator iterator() {
      return listIterator();
    }
  
    public ListIterator listIterator(final int index) {
      checkForComodification();
      if (index<0 || index>size)
        throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
      return new ListIterator() {
          private ListIterator i = l.listIterator(index+offset);
  
          public boolean hasNext() {
              return nextIndex() < size;
          }
  
          public Object next() {
              if (hasNext())
                  return i.next();
              else
                  throw new NoSuchElementException();
          }
  
          public boolean hasPrevious() {
              return previousIndex() >= 0;
          }
  
          public Object previous() {
              if (hasPrevious())
                  return i.previous();
              else
                  throw new NoSuchElementException();
          }
  
          public int nextIndex() {
              return i.nextIndex() - offset;
          }
  
          public int previousIndex() {
              return i.previousIndex() - offset;
          }
  
          public void remove() {
              i.remove();
              expectedModCount = l.modCount;
              size--;
              modCount++;
          }
  
          public void set(Object o) {
              i.set(o);
          }
  
          public void add(Object o) {
              i.add(o);
              expectedModCount = l.modCount;
              size++;
              modCount++;
          }
      };
    }
  
    public List subList(int fromIndex, int toIndex) {
      return new SubList(this, fromIndex, toIndex);
    }
  
    private void rangeCheck(int index) {
      if (index<0 || index>=size)
        throw new IndexOutOfBoundsException("Index: "+index+",Size: "+size);
    }
  
    private void checkForComodification() {
      if (l.modCount != expectedModCount)
        throw new ConcurrentModificationException();
    }
  
  }

}

