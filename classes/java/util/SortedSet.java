/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public interface SortedSet extends Set {

  public Object first();
  public Object last();
  public Comparator comparator();
  public SortedSet subSet(Object start, Object end);
  public SortedSet headSet(Object end);
  public SortedSet tailSet(Object start);

}

