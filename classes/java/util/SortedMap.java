/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public interface SortedMap extends Map {

  public Object firstKey();
  public Object lastKey();
  public Comparator comparator();
  public SortedMap subMap(Object start, Object end);
  public SortedMap headMap(Object end);
  public SortedMap tailMap(Object start);

}

