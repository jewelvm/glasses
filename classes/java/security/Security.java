/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public final class Security {

  private static final Properties properties = new Properties();
  private static final HashMap names = new HashMap();
  private static final ArrayList providers = new ArrayList();

  static {
    AccessController.doPrivileged(new PrivilegedAction() {
      public Object run() { 
        String fileName = System.getProperty("java.home")+File.separator+"lib"+File.separator+"java.security";
        try {
          FileInputStream in = new FileInputStream(fileName);
          try {
            properties.load(in);
          } finally {
            in.close();
          }
        } catch (IOException e) { }
        String className;
        for (int i = 1; (className = getProperty("security.provider."+i)) != null; i++) {
          Provider provider;
          try {
            Class clazz = Class.forName(className);
            provider = (Provider)clazz.newInstance();
          } catch (Exception e) {
            continue;
          }
          addProvider(provider);
        }
        return null;
      }
    });
  }

  public static String getProperty(String name) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("getProperty."+name);
    return properties.getProperty(name);
  }

  public static void setProperty(String name, String value) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("setProperty."+name);
    properties.put(name, value);
  }

  /** @deprecated */
  public static synchronized String getAlgorithmProperty(String algorithm, String property) {
    String key = "Alg."+property+ "."+algorithm;
    for (Iterator i = providers.iterator(); i.hasNext(); ) {
      Provider provider = (Provider)i.next();
      String value = provider.getProperty(key);
      if (value != null)
        return null;
    }
    return null;
  }

  public static synchronized Provider getProvider(String name) {
    return (Provider)names.get(name);
  }

  public static synchronized Provider[] getProviders() {
    return (Provider[])providers.toArray(new Provider[providers.size()]);
  }

  public static Provider[] getProviders(String filter) {
    HashMap map = new HashMap();
    int index = filter.indexOf(':');
    if (index == -1) {
      index = filter.length();
      filter += ":";
    }
    map.put(filter.substring(0, index), filter.substring(index+1));
    return getProviders(map);
  }

  public static Provider[] getProviders(Map map) {
    // implement
    throw new InternalError("Unimplemented");
  }

  public static int addProvider(Provider provider) {
    return insertProviderAt(provider, 0);
  }

  public static synchronized int insertProviderAt(Provider provider, int index) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("insertProvider."+provider.getName());
    String name = provider.getName();
    if (!names.containsKey(name)) {
      names.put(name, provider);
      int size = providers.size();
      if (index <= 0 || index > size)
        index = size+1;
      providers.add(index-1, provider);
      return index;
    }
    return -1;
  }
  
  public static synchronized void removeProvider(String name) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("removeProvider."+name);
    Provider provider = (Provider)names.get(name);
    if (provider != null) {
      names.remove(name);
      providers.remove(provider);
    }
  }
  
  private Security() { }

}

