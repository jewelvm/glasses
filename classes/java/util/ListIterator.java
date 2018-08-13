/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public interface ListIterator extends Iterator {

  public boolean hasPrevious();
  public Object previous();
  public int nextIndex();
  public int previousIndex();
  public void set(Object object);
  public void add(Object object);

}

