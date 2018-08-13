/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.util.Map.Entry;

public abstract class AbstractMap implements Map {

  /*private*/ transient Set keySet;
  /*private*/ transient Collection values;

  protected AbstractMap() { }

  public int size() {
    Set entries = entrySet();
    return entries.size();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean containsKey(Object key) {
    Set entries = entrySet();
    for (Iterator i = entries.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      if (HashMap.equals(key, entry.getKey()))
        return true;
    }
    return false;
  }

  public boolean containsValue(Object value) {
    Set entries = entrySet();
    for (Iterator i = entries.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      if (HashMap.equals(value, entry.getValue()))
        return true;
    }
    return false;
  }

  public Object get(Object key) {
    Set entries = entrySet();
    for (Iterator i = entries.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      if (HashMap.equals(key, entry.getKey()))
        return entry.getValue();
    }
    return null;
  }

  public Object put(Object key, Object value) {
    throw new UnsupportedOperationException();
  }

  public Object remove(Object key) {
    Set entries = entrySet();
    for (Iterator i = entries.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      if (HashMap.equals(key, entry.getKey())) {
        Object value = entry.getValue();
        i.remove();
        return value;
      }
    }
    return null;
  }

  public void putAll(Map map) {
    Set entries = map.entrySet();
    for (Iterator i = entries.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      put(entry.getKey(), entry.getValue());
    }
  }

  public void clear() {
    Set entries = entrySet();
    entries.clear();
  }

  public abstract Set entrySet();

  public Set keySet() {
    if (keySet == null)
      keySet = new AbstractSet() {
        public int size() { return AbstractMap.this.size(); }
        public boolean contains(Object object) { return AbstractMap.this.containsKey(object); }
        public boolean remove(Object object) {
          boolean result = AbstractMap.this.containsKey(object);
          AbstractMap.this.remove(object);
          return result;
        }
        public void clear() { AbstractMap.this.clear(); }
        public Iterator iterator() {
          return new Iterator() {
            private final Iterator i = AbstractMap.this.entrySet().iterator();
            public boolean hasNext() { return i.hasNext(); }
            public Object next() { return ((Entry)i.next()).getKey(); }
            public void remove() { i.remove(); }
          };
        }
      };
    return keySet;
  }

  public Collection values() {
    if (values == null)
      values = new AbstractCollection() {
        public int size() { return AbstractMap.this.size(); }
        public boolean contains(Object object) { return AbstractMap.this.containsValue(object); }
        public void clear() { AbstractMap.this.clear(); }
        public Iterator iterator() {
          return new Iterator() {
            private final Iterator i = AbstractMap.this.entrySet().iterator();
            public boolean hasNext() { return i.hasNext(); }
            public Object next() { return ((Entry)i.next()).getValue(); }
            public void remove() { i.remove(); }
          };
        }
      };
    return values;
  }

  protected Object clone() throws CloneNotSupportedException {
    AbstractMap map = (AbstractMap)super.clone();
    map.keySet = null;
    map.values = null;
    return map;
  }

  public int hashCode() {
    int hashCode = 0;
    Set entries = entrySet();
    for (Iterator i = entries.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      hashCode += entry.hashCode();
    }
    return hashCode;
  }

  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (object instanceof Map) {
      Map map = (Map)object;
      if (size() != map.size())
        return false;
      Set entries = entrySet();
      for (Iterator i = entries.iterator(); i.hasNext(); ) {
        Entry entry = (Entry)i.next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (value == null && !map.containsKey(key))
          return false;
        if (!HashMap.equals(value, map.get(key)))
          return false;
      }
      return true;
    }
    return false;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append('{');
    Set entries = entrySet();
    for (Iterator i = entries.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      sb.append(entry);
      sb.append(", ");
    }
    int length = sb.length();
    if (length > 1)
      sb.setLength(length-2);
    sb.append('}');
    return sb.toString();
  }

}

