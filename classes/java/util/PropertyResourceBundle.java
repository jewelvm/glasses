/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.InputStream;
import java.io.IOException;

public class PropertyResourceBundle extends ResourceBundle {

  private final Properties properties = new Properties();

  public PropertyResourceBundle(InputStream in) throws IOException {
    properties.load(in);
  }

  public Object handleGetObject(String key) {
    return properties.get(key);
  }

  public Enumeration getKeys() {
    Vector vector = null;
    if (parent != null) {
      Enumeration e = parent.getKeys();
      if (properties.size() == 0)
        return e;
      while (e.hasMoreElements()) {
        Object key = e.nextElement();
        if (!properties.containsKey(key)) {
          if (vector == null)
            vector = new Vector();
          vector.add(key);
        }
      }
    }
    Enumeration e = properties.keys();
    if (vector == null)
      return e;
    while (e.hasMoreElements()) {
      Object key = e.nextElement();
      vector.add(key);
    }
    return vector.elements();
  }

}

