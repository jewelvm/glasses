/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public abstract class ListResourceBundle extends ResourceBundle {

  private Hashtable hashtable;

  protected ListResourceBundle() { }
  
  protected final synchronized void init() {
    if (hashtable == null) {
      hashtable = new Hashtable();
      Object[][] contents = getContents();
      for (int i = 0; i < contents.length; i++) {
        Object[] pair = contents[i];
        hashtable.put(pair[0], pair[1]);
      }
    }
  }

  public final Object handleGetObject(String key) {
    init();
    return hashtable.get(key);
  }
  
  public Enumeration getKeys() {
    init();
    Vector vector = null;
    if (parent != null) {
      Enumeration e = parent.getKeys();
      if (hashtable.size() == 0)
        return e;
      while (e.hasMoreElements()) {
        Object key = e.nextElement();
        if (!hashtable.containsKey(key)) {
          if (vector == null)
            vector = new Vector();
          vector.add(key);
        }
      }
    }
    Enumeration e = hashtable.keys();
    if (vector == null)
      return e;
    while (e.hasMoreElements()) {
      Object key = e.nextElement();
      vector.add(key);
    }
    return vector.elements();
  }
  
  protected abstract Object[][] getContents();
  
}

