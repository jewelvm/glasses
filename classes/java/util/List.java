/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public interface List extends Collection {

  public Object get(int index);
  public Object set(int index, Object object);
  public void add(int index, Object object);
  public boolean addAll(int index, Collection collection);
  public Object remove(int index);
  public int indexOf(Object object);
  public int lastIndexOf(Object object);
  public ListIterator listIterator();
  public ListIterator listIterator(int index);
  public List subList(int start, int end);

}

