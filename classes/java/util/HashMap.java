/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.Serializable;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

public class HashMap extends AbstractMap implements Cloneable, Serializable {

  /* review location */
  static int hashCode(Object object) {
    return object == null ? 0 : object.hashCode();
  }

  /* review location */
  static boolean equals(Object one, Object another) {
    return one == another
        || (one != null && one.equals(another));
  }

  private int size;
  private HashEntry[] entries;
  private float factor;
  private int modifications;

  private transient Set entrySet;

  public HashMap() {
    this(37);
  }

  public HashMap(int capacity) {
    this(capacity, 0.75F);
  }

  public HashMap(int capacity, float factor) {
    if (capacity < 0)
      throw new IllegalArgumentException("Invalid capacity");
    if (factor <= 0.0F || factor > 1.0F)
      throw new IllegalArgumentException("Invalid factor");
    if (capacity == 0) 
      capacity++;
    this.entries = new HashEntry[capacity];
    this.factor = factor;
  }

  public HashMap(Map map) {
    this(Math.max(2*map.size(), 11));
    putAll(map);
  }

  public int size() {
    return size;
  }

  public boolean containsKey(Object key) {
    int hashCode = HashMap.hashCode(key);
    if (hashCode < 0)
      hashCode = -hashCode;
    int index = hashCode % entries.length;
    for (HashEntry entry = entries[index]; entry != null; entry = entry.next)
      if (HashMap.equals(key, entry.getKey()))
        return true;
    return false;
  }

  public Object get(Object key) {
    int hashCode = HashMap.hashCode(key);
    if (hashCode < 0)
      hashCode = -hashCode;
    int index = hashCode % entries.length;
    for (HashEntry entry = entries[index]; entry != null; entry = entry.next)
      if (HashMap.equals(key, entry.getKey()))
        return entry.getValue();
    return null;
  }

  public Object put(Object key, Object value) {
    int hashCode = HashMap.hashCode(key);
    if (hashCode < 0)
      hashCode = -hashCode;
    int index = hashCode % entries.length;
    for (HashEntry entry = entries[index]; entry != null; entry = entry.next)
      if (HashMap.equals(key, entry.getKey())) {
        Object oldvalue = entry.getValue();
        entry.setValue(value);
        return oldvalue;
      }
    HashEntry newentry = new HashEntry(key, value);
    newentry.next = entries[index];
    entries[index] = newentry;
    size++;
    modifications++;
    if (size >= factor*entries.length)
      rehash();
    return null;
  }

  public Object remove(Object key) {
    int hashCode = HashMap.hashCode(key);
    if (hashCode < 0)
      hashCode = -hashCode;
    int index = hashCode % entries.length;
    HashEntry previous = null;
    for (HashEntry entry = entries[index]; entry != null; entry = entry.next) {
      if (HashMap.equals(key, entry.getKey())) {
        modifications++;
        size--;
        if (previous != null)
          previous.next = entry.next;
        else
          entries[index] = entry.next;
        return entry.getValue();
      }
      previous = entry;
    }
    return null;
  }

  protected void rehash() {
    HashEntry[] oldentries = entries;
    entries = new HashEntry[2*oldentries.length];
    for (int i = 0; i < oldentries.length; i++)
      for (HashEntry entry = oldentries[i]; entry != null; ) {
          HashEntry newentry = entry;
          entry = entry.next;
          int hashCode = HashMap.hashCode(newentry.getKey());
          if (hashCode < 0)
            hashCode = -hashCode;
          int index = hashCode % entries.length;
          newentry.next = entries[index];
          entries[index] = newentry;
      }
  }

  public void clear() {
    if (size > 0) {
      modifications++;
      size = 0;
      for (int i = 0; i < entries.length; i++)
        entries[i] = null;
    }
  }

  public Set entrySet() {
    if (entrySet == null)
      entrySet = new AbstractSet() {
        public int size() { return HashMap.this.size(); }
        public Iterator iterator() {
          return new Iterator() {
            private int modifications = HashMap.this.modifications;
            private int index;
            private HashEntry entry;
            private HashEntry previous;
            public boolean hasNext() {
              if (modifications != HashMap.this.modifications)
                throw new ConcurrentModificationException();
              if (entry != null)
                return true;
              while (index < HashMap.this.entries.length) {
                entry = HashMap.this.entries[index++];
                if (entry != null)
                  return true;
              }
              return false;
            }
            public Object next() {
              if (!hasNext())
                throw new NoSuchElementException();
              previous = entry;
              entry = entry.next;
              return previous;
            }
            public void remove() {
              if (modifications != HashMap.this.modifications)
                throw new ConcurrentModificationException();
              if (previous == null)
                throw new IllegalStateException();
              HashMap.this.remove(previous.getKey());
              modifications = HashMap.this.modifications;
              previous = null;
            }
          };
        }
        public boolean contains(Object object) {
          if (object instanceof Entry) {
            Entry entry = (Entry)object;
            return HashMap.this.containsKey(entry.getKey());
          }
          return false;
        }
        public boolean remove(Object object) {
          if (object instanceof Entry) {
            Entry entry = (Entry)object;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (!HashMap.equals(value, HashMap.this.get(key)))
              return false;
            if (value == null)
              if (!HashMap.this.containsKey(key))
                return false;
            HashMap.this.remove(key);
            return true;
          }
          return false;
        }
        public void clear() { HashMap.this.clear(); }
      };
    return entrySet;
  }

  public Object clone() {
    try { 
      HashMap map = (HashMap)super.clone();
      map.entries = (HashEntry[])entries.clone();
      for (int i = 0 ; i < entries.length; i++)
        if (entries[i] != null)
          map.entries[i] = (HashEntry)entries[i].clone();
      map.entrySet = null;
      return map;
    } catch (CloneNotSupportedException e) { 
      throw new InternalError(e.getMessage());
    }
  }

  private static final class HashEntry implements Cloneable, Entry {

    private final Object key;
    private Object value;

    HashEntry next;

    HashEntry(Object key, Object value) {
      this.key = key;
      this.value = value;
    }

    public Object getKey() {
      return key;
    }

    public Object getValue() {
      return value;
    }

    public Object setValue(Object value) {
      Object oldvalue = this.value;
      this.value = value;
      return oldvalue;
    }

    public Object clone() {
      try {
        HashEntry entry = (HashEntry)super.clone();
        if (next != null)
          entry.next = (HashEntry)next.clone();
        return entry;
      } catch (CloneNotSupportedException e) {
        throw new InternalError();
      }
    }

    public int hashCode() {
      return HashMap.hashCode(key)
           ^ HashMap.hashCode(value);
    }

    public boolean equals(Object object) {
      return object instanceof Entry
          && HashMap.equals(key, ((Entry)object).getKey())
          && HashMap.equals(value, ((Entry)object).getValue());
    }

    public String toString() {
      return key+"="+value;
    }

  }

//  private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
//  }

//  private void writeObject(ObjectOutputStream out) throws IOException {
//  }

}

