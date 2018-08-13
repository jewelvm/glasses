/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public interface Collection {

  public int size();
  public boolean isEmpty();
  public boolean contains(Object object);
  public Iterator iterator();
  public Object[] toArray();
  public Object[] toArray(Object[] array);
  public boolean add(Object object);
  public boolean remove(Object object);
  public boolean containsAll(Collection collection);
  public boolean addAll(Collection collection);
  public boolean removeAll(Collection collection);
  public boolean retainAll(Collection collection);
  public void clear();

}

