/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakHashMap extends AbstractMap {

  private final Map map;
  private final ReferenceQueue queue = new ReferenceQueue();

  private transient Set entrySet;

  public WeakHashMap() {
    map = new HashMap();
  }

  public WeakHashMap(int capacity) {
    map = new HashMap(capacity);
  }

  public WeakHashMap(int capacity, float factor) {
    map = new HashMap(capacity, factor);
  }

  public WeakHashMap(Map map) {
    this(Math.max(2*map.size(), 11));
    putAll(map);
  }

  private void processQueue() {
    KeyReference key;
    while ((key = (KeyReference)queue.poll()) != null)
      map.remove(key);
  }

  public boolean containsKey(Object key) {
    return map.containsKey(key == null ? null : new KeyReference(key));
  }

  public Object get(Object key) {
    return map.get(key == null ? null : new KeyReference(key));
  }

  public Object put(Object key, Object value) {
    processQueue();
    return map.put(key == null ? null : new KeyReference(key, queue), value);
  }

  public Object remove(Object key) {
    processQueue();
    return map.remove(key == null ? null : new KeyReference(key));
  }

  public void clear() {
    processQueue();
    map.clear();
  }

  // review
  public Set entrySet() {
    if (entrySet == null)
      entrySet = new AbstractSet() {
        private Set hashEntrySet = map.entrySet();
        public int size() {
          int size = 0;
          for (Iterator i = iterator(); i.hasNext(); size++)
            i.next();
          return size;
        }
        public Iterator iterator() {
          return new Iterator() {
            Iterator hashIterator = hashEntrySet.iterator();
            Entry next = null;
            public boolean hasNext() {
              while (hashIterator.hasNext()) {
                Map.Entry ent = (Map.Entry)hashIterator.next();
                KeyReference wk = (KeyReference)ent.getKey();
                Object k = null;
                if ((wk != null) && ((k = wk.get()) == null)) {
                  /* Weak key has been cleared by GC */
                  continue;
                }
                next = new WeakHashEntry(k, ent);
                return true;
              }
              return false;
            }
            public Object next() {
              if ((next == null) && !hasNext())
                throw new NoSuchElementException();
              Entry e = next;
              next = null;
              return e;
            }
            public void remove() {
              hashIterator.remove();
            }
          };
        }
        public boolean remove(Object o) {
            processQueue();
            if (!(o instanceof Map.Entry)) return false;
            Map.Entry e = (Map.Entry)o;
            Object ev = e.getValue();
            Object ekey = e.getKey();
            KeyReference wk = ekey == null ? null : new KeyReference(ekey);
            Object hv = map.get(wk);
            if ((hv == null)
                ? ((ev == null) && map.containsKey(wk)) : hv.equals(ev)) {
                map.remove(wk);
                return true;
            }
            return false;
        }
        public int hashCode() {
          int h = 0;
          for (Iterator i = hashEntrySet.iterator(); i.hasNext();) {
              Map.Entry ent = (Map.Entry)i.next();
              KeyReference wk = (KeyReference)ent.getKey();
              Object v;
              if (wk == null) continue;
              h += (wk.hashCode()
                    ^ (((v = ent.getValue()) == null) ? 0 : v.hashCode()));
          }
          return h;
        }
      };
    return entrySet;
  }

  private static class KeyReference extends WeakReference {

    private final int hashCode;

    private KeyReference(Object object) {
      super(object);
      hashCode = object.hashCode();
    }

    private KeyReference(Object object, ReferenceQueue queue) {
      super(object, queue);
      hashCode = object.hashCode();
    }

    public int hashCode() {
      return hashCode;
    }

    public boolean equals(Object object) {
      if (object == this)
        return true;
      Object ref = this.get();
      if (ref == null)
        return false;
      if (object instanceof KeyReference) {
        KeyReference key = (KeyReference)object;
        Object kref = key.get();
        if (kref == null)
          return false;
        if (!ref.equals(kref))
          return false;
        return true;
      }
      return false;
    }

  }

  private static final class WeakHashEntry implements Entry {

    private final Object key;
    private final Entry entry;

    WeakHashEntry(Object key, Entry entry) {
      this.key = key;
      this.entry = entry;
    }

    public Object getKey() {
      return key;
    }

    public Object getValue() {
      return entry.getValue();
    }

    public Object setValue(Object value) {
      return entry.setValue(value);
    }

    public int hashCode() {
      return HashMap.hashCode(key)
           ^ HashMap.hashCode(getValue());
    }

    public boolean equals(Object object) {
      return object instanceof Entry
          && HashMap.equals(key, ((Entry)object).getKey())
          && HashMap.equals(getValue(), ((Entry)object).getValue());
    }

    public String toString() {
      return key+"="+getValue();
    }

  }

}

