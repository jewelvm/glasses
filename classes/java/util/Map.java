/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public interface Map {

  public int size();
  public boolean isEmpty();
  public boolean containsKey(Object key);
  public boolean containsValue(Object value);
  public Object get(Object key);
  public Object put(Object key, Object value);
  public Object remove(Object key);
  public void putAll(Map map);
  public void clear();
  public Set entrySet();
  public Set keySet();
  public Collection values();

  public interface Entry {

    public Object getKey();
    public Object getValue();
    public Object setValue(Object value);

  }

}

