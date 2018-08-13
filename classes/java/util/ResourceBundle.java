/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.InputStream;
import java.io.IOException;

public abstract class ResourceBundle {

  private static final WeakHashMap loaderMap = new WeakHashMap();

  public static ResourceBundle getBundle(String baseName) throws MissingResourceException {
    Class caller = Thread.callerClass();
    ClassLoader loader = caller == null ? Thread.currentThread().getContextClassLoader() : caller.getClassLoader();
    return getBundle(baseName, Locale.getDefault(), loader);
  }
  
  public static ResourceBundle getBundle(String baseName, Locale locale) throws MissingResourceException {
    Class caller = Thread.callerClass();
    ClassLoader loader = caller == null ? Thread.currentThread().getContextClassLoader() : caller.getClassLoader();
    return getBundle(baseName, locale, loader);
  }
  
  public static ResourceBundle getBundle(String baseName, Locale locale, ClassLoader loader) throws MissingResourceException {
    ResourceBundle bundle = findBundle(loader, baseName, locale);
    if (bundle != null)
      if (!bundle.locale.equals(new Locale("", "")))
        return bundle;
    bundle = findBundle(loader, baseName, Locale.getDefault());
    if (bundle != null)
      return bundle;
    throw new MissingResourceException("Resource bundle not found: "+baseName, bundleName(baseName, locale), null);
  }

  private static ResourceBundle findBundle(ClassLoader loader, String baseName, Locale locale) {
    HashMap map;
    synchronized (loaderMap) {
      map = (HashMap)loaderMap.get(loader);
      if (map == null)
        map = new HashMap();
      loaderMap.put(loader, map);
    }
    synchronized (map) {
      String name = baseName+"-"+locale;
      ResourceBundle bundle = (ResourceBundle)map.get(name);
      if (bundle == null) {
        ResourceBundle parent = findParentBundle(loader, baseName, locale);
        bundle = loadBundle(loader, baseName, locale);
        if (bundle == null)
          bundle = parent;
        else
          bundle.setParent(parent);
        if (bundle != null)
          map.put(name, bundle);
      }
      return bundle;
    }
  }

  private static ResourceBundle findParentBundle(ClassLoader loader, String baseName, Locale locale) {
    String language = locale.getLanguage();
    String country = locale.getCountry();
    String variant = locale.getVariant();
    if (variant.length() > 0)
      return findBundle(loader, baseName, new Locale(language, country));
    if (country.length() > 0)
      return findBundle(loader, baseName, new Locale(language, ""));
    if (language.length() > 0)
      return findBundle(loader, baseName, new Locale("", ""));
    return null;
  }

  private static ResourceBundle loadBundle(ClassLoader loader, String baseName, Locale locale) {
    String name = bundleName(baseName, locale);
    ResourceBundle bundle = loadClassBundle(loader, name);
    if (bundle == null)
      bundle = loadPropertyBundle(loader, name.replace('.', '/')+".properties");
    if (bundle != null)
      bundle.locale = locale;
    return bundle;
  }

  private static ResourceBundle loadClassBundle(ClassLoader loader, String name) {
    Class clazz;
    try {
      clazz = Class.forName(name, false, loader);
    } catch (ClassNotFoundException e) {
      return null;
    }
    Object object;
    try {
      object = clazz.newInstance();
    } catch (IllegalAccessException e) {
      return null;
    } catch (InstantiationException e) {
      return null;
    }
    if (object instanceof ResourceBundle)
      return (ResourceBundle)object;
    return null;
  }

  private static ResourceBundle loadPropertyBundle(ClassLoader loader, String name) {
    InputStream in = loader == null ? ClassLoader.getSystemResourceAsStream(name) : loader.getResourceAsStream(name);
    if (in != null)
      try {
        try {
          return new PropertyResourceBundle(in);
        } finally {
          in.close();
        }
      } catch (IOException e) { }
    return null;
  }

  private static String bundleName(String baseName, Locale locale) {
    if (baseName == null)
      throw new NullPointerException();
    String name = baseName;
    String triplet = locale.toString();
    if (triplet.length() > 0)
      name += "_"+triplet;
    return name;
  }

  private Locale locale;

  protected ResourceBundle parent;
  
  protected ResourceBundle() { }
  
  public Locale getLocale() {
    return locale;
  }

  protected void setParent(ResourceBundle parent) {
    this.parent = parent;
  }
  
  protected abstract Object handleGetObject(String key) throws MissingResourceException;
  
  public final Object getObject(String key) throws MissingResourceException {
    Object object = handleGetObject(key);
    if (object != null)
      return object;
    if (parent != null) {
      object = parent.getObject(key);
      if (object != null)
        return object;
    }
    throw new MissingResourceException("Resource not found: "+key, this.getClass().getName(), key);
  }

  public final String getString(String key) throws MissingResourceException {
    return (String)getObject(key);
  }
  
  public final String[] getStringArray(String key) throws MissingResourceException {
    return (String[])getObject(key);
  }
  
  public abstract Enumeration getKeys();
  
}

