/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.jar;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Attributes implements Cloneable, Map {

  protected Map map;

  public Attributes() {
    map = new HashMap();
  }

  public Attributes(int size) {
    map = new HashMap(size);
  }

  public Attributes(Attributes attributes) {
    map = new HashMap(attributes.map);
  }

  public int size() {
    return map.size();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public boolean containsKey(Object key) {
    return map.containsKey(key);
  }

  public boolean containsValue(Object value) {
    return map.containsValue(value);
  }

  public Object get(Object key) {
    return map.get(key);
  }

  public String getValue(Name key) {
    return (String)get(key);
  }

  public String getValue(String key) {
    return (String)get(new Name(key));
  }

  public Object put(Object key, Object value) {
    return map.put((Name)key, (String)value);
  }

  public String putValue(String key, String value) {
    return (String)put(new Name(key), value);
  }

  public Object remove(Object key) {
    return map.remove(key);
  }

  public void putAll(Map map) {
    this.map.putAll(((Attributes)map).map);
  }

  public void clear() {
    map.clear();
  }

  public Set entrySet() {
    return map.entrySet();
  }

  public Set keySet() {
    return map.keySet();
  }

  public Collection values() {
    return map.values();
  }

  public Object clone() {
    return new Attributes(this);
  }

  public int hashCode() {
    return map.hashCode();
  }

  public boolean equals(Object object) {
    return object instanceof Attributes
        && ((Attributes)object).map.equals(map);
  }

  public static class Name {

    public static final Name CLASS_PATH = new Name("Class-Path");
    public static final Name CONTENT_TYPE = new Name("Content-Type");
    public static final Name EXTENSION_INSTALLATION = new Name("Extension-Instalation");
    public static final Name EXTENSION_LIST = new Name("Extension-List");
    public static final Name EXTENSION_NAME = new Name("Extension-Name");
    public static final Name IMPLEMENTATION_TITLE = new Name("Implementation-Title");
    public static final Name IMPLEMENTATION_URL = new Name("Implementation-URL");
    public static final Name IMPLEMENTATION_VENDOR = new Name("Implementation-Vendor");
    public static final Name IMPLEMENTATION_VENDOR_ID = new Name("Implementation-Vendor-Id");
    public static final Name IMPLEMENTATION_VERSION = new Name("Implementation-Version");
    public static final Name MAIN_CLASS = new Name("Main-Class");
    public static final Name MANIFEST_VERSION = new Name("Manifest-Version");
    public static final Name SEALED = new Name("Sealed");
    public static final Name SIGNATURE_VERSION = new Name("Signature-Version");
    public static final Name SPECIFICATION_TITLE = new Name("Specification-Title");
    public static final Name SPECIFICATION_VERSION = new Name("Specification-Version");
    public static final Name SPECIFICATION_VENDOR = new Name("Specification-Vendor");

    private final String name;

    public Name(String name) {
      int length = name.length();
      if (length > 70)
        throw new IllegalArgumentException("Invalid name");
      for (int i = 0; i < length; i++) {
        char shar = name.charAt(i);
        if ('a' <= shar && shar <= 'z') continue;
        if ('A' <= shar && shar <= 'Z') continue;
        if ('0' <= shar && shar <= '9') continue;
        if (shar == '_' || shar == '-') continue;
        throw new IllegalArgumentException("Invalid name");
      }
      this.name = name;
    }

    public int hashCode() {
      return name.toLowerCase().hashCode();
    }

    public boolean equals(Object object) {
      return object instanceof Name
          && ((Name)object).name.equalsIgnoreCase(name);
    }
  
    public String toString() {
      return name;
    }

  }

}

