/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.jar;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.Attributes.Name;

public class Manifest implements Cloneable {

  private final Attributes attributes = new Attributes();
  private final HashMap entries = new HashMap();

  public Manifest() { }

  public Manifest(Manifest manifest) {
    attributes.putAll(manifest.attributes);
    entries.putAll(manifest.entries);
  }

  public Manifest(InputStream in) throws IOException {
    read(in);
  }

  public Attributes getMainAttributes() {
    return attributes;
  }

  public Map getEntries() {
    return entries;
  }

  public Attributes getAttributes(String name) {
    return (Attributes)getEntries().get(name);
  }

  public void clear() {
    attributes.clear();
    entries.clear();
  }

  public Object clone() {
    return new Manifest(this);
  }

  public int hashCode() {
    return attributes.hashCode()+entries.hashCode();
  }

  public boolean equals(Object object) {
    return object instanceof Manifest
        && ((Manifest)object).attributes.equals(attributes)
        && ((Manifest)object).entries.equals(entries);
  }

  public void read(InputStream in) throws IOException {
    Attributes attrs = readAttrs(in);
    if (attrs != null) {
      Attributes attributes = getMainAttributes();
      attributes.putAll(attrs);
      while ((attrs = readAttrs(in)) != null) {
        String name = attrs.getValue("Name");
        if (name == null)
          throw new IOException("Invalid attributes");
        attrs.remove(new Name("Name"));
        attributes = getAttributes(name);
        if (attributes == null) {
          attributes = new Attributes(attrs.size());
          entries.put(name, attributes);
        }
        attributes.putAll(attrs);
      }
    }
  }

  private Attributes readAttrs(InputStream in) throws IOException {
    Attributes attributes = null;
    StringBuffer sb = null;
    String line;
    while ((line = readLine(in)) != null) {
      int length = line.length();
      if (length == 0) {
        if (sb == null)
          continue;
        break;
      }
      if (line.charAt(0) == ' ') {
        if (sb == null)
          throw new IOException("Invalid attribute");
        sb.append(line.substring(1));
      } else {
        if (sb != null) {
          String attribute = sb.toString();
          int index = attribute.indexOf(": ");
          if (index == -1)
            throw new IOException("Invalid attribute");
          String name = attribute.substring(0, index);
          String value = attribute.substring(index+2);
          if (attributes == null)
            attributes = new Attributes();
          try {
            attributes.putValue(name, value);
          } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
          }
        }
        sb = new StringBuffer(line);
      }
    }
    if (sb != null) {
      String attribute = sb.toString();
      int index = attribute.indexOf(": ");
      if (index == -1)
        throw new IOException("Invalid attribute");
      String name = attribute.substring(0, index);
      String value = attribute.substring(index+2);
      if (attributes == null)
        attributes = new Attributes();
      try {
        attributes.putValue(name, value);
      } catch (IllegalArgumentException e) {
        throw new IOException(e.getMessage());
      }
    }
    return attributes;
  }

  private String readLine(InputStream in) throws IOException {
    StringBuffer sb = new StringBuffer();
    int bite;
    while ((bite = in.read()) != -1) {
      char shar = (char)(bite & 0xFF);
      if (shar == '\n') {
        int length = sb.length();
        if (length > 0)
          if (sb.charAt(length-1) == '\r')
            sb.setLength(length-1);
        return sb.toString();
      }
      sb.append(shar);
    }
    int length = sb.length();
    if (length == 0)
      return null;
    return sb.toString();
  }

  public void write(OutputStream out) throws IOException {
    write(out, getMainAttributes());
    Map entries = getEntries();
    Set set = entries.entrySet();
    for (Iterator i = set.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      write(out, "Name", entry.getKey());
      write(out, (Attributes)entry.getValue());
    }
  }

  private void write(OutputStream out, Attributes attributes) throws IOException {
    Name name = Name.MANIFEST_VERSION;
    String version = attributes.getValue(name);
    if (version == null) {
      name = Name.SIGNATURE_VERSION;
      version = attributes.getValue(name);
    }
    if (version != null)
      write(out, name, version);
    Set set = attributes.entrySet();
    for (Iterator i = set.iterator(); i.hasNext(); ) {
      Entry entry = (Entry)i.next();
      Object key = entry.getKey();
      if (!name.equals(key))
        write(out, key, entry.getValue());
    }
    out.write('\r');
    out.write('\n');
  }

  private void write(OutputStream out, Object key, Object value) throws IOException {
    String string = key+": "+value;
    int column = 0;
    int length = string.length();
    for (int i = 0; i < length; i++) {
      if (column == 70) {
        out.write('\r');
        out.write('\n');
        out.write(' ');
        column = 1;
      }
      out.write(string.charAt(i));
      column++;
    }
    out.write('\r');
    out.write('\n');
  }

}

