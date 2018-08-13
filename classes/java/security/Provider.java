/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.InputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

//import java.util.Collections;

public abstract class Provider extends Properties {

  private static final long serialVersionUID = -4298000515446427739L;

  private final String name;
  private final double version;
  private final String info;

  protected Provider(String name, double version, String info) {
    if (name == null)
      throw new NullPointerException();
    this.name = name;
    this.version = version;
    this.info = info;
  }

  public String getName() {
    return name;
  }

  public double getVersion() {
    return version;
  }

  public String getInfo() {
    return info;
  }

  public Object put(Object key, Object value) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("putProviderProperty."+name);
    return super.put(key, value);
  }

  public Object remove(Object key) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("removeProviderProperty."+name);
    return super.remove(key);
  }

  public void putAll(Map map) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("putAllProviderProperties."+name);
    super.putAll(map);
  }

  public void clear() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("clearProviderProperties."+name);
    super.clear();
  }

  public void load(InputStream in) throws IOException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("loadProviderProperties."+name);
    super.load(in);
  }

  public Set entrySet() {
    return /*Collections.unmodifiableMap(*/this/*)*/.entrySet();
  }

  public Set keySet() {
    return /*Collections.unmodifiableMap(*/this/*)*/.keySet();
  }

  public Collection values() {
    return /*Collections.unmodifiableMap(*/this/*)*/.values();
  }

  public String toString() {
    return name+" version "+version;
  }

}

