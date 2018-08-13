/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Properties extends Hashtable {

  private static final long serialVersionUID = 4112578634029874840L;

  protected Properties defaults;

  public Properties() { }

  public Properties(Properties defaults) {
    this.defaults = defaults;
  }

  public String getProperty(String name) {
    String value = null;
    Object object = super.get(name);
    if (object instanceof String)
      value = (String)object;
    if (value == null)
      if (defaults != null)
        value = defaults.getProperty(name);
    return value;
  }

  public String getProperty(String name, String defvalue) {
    String value = getProperty(name);
    if (value == null)
      value = defvalue;
    return value;
  }

  public Object setProperty(String name, String value) {
    return put(name, value);
  }

  public synchronized Enumeration propertyNames() {
    Hashtable table = new Hashtable();
    if (defaults != null)
      for (Enumeration e = defaults.propertyNames(); e.hasMoreElements(); ) {
        String name = (String)e.nextElement();
        table.put(name, name);
      }
    for (Enumeration e = keys(); e.hasMoreElements(); ) {
      Object key = e.nextElement();
      if (key instanceof String) {
        String name = (String)key;
        table.put(name, name);
      }
    }
    return table.keys();
  }

  public synchronized void list(PrintStream out) {
    out.println("-- listing properties --");
    for (Enumeration e = propertyNames(); e.hasMoreElements(); ) {
      String name = (String)e.nextElement();
      String value = getProperty(name);
      out.print(name);
      out.print("=");
      out.println(value);
    }
  }

  public synchronized void list(PrintWriter out) {
    out.println("-- listing properties --");
    for (Enumeration e = propertyNames(); e.hasMoreElements(); ) {
      String name = (String)e.nextElement();
      String value = getProperty(name);
      out.print(name);
      out.print("=");
      out.println(value);
    }
  }

  /** @deprecated */
  public void save(OutputStream out, String header)  {
    try {
      store(out, header);
    } catch (IOException e) { }
  }

  public synchronized void load(InputStream is) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(is, "8859_1"));
    String line;
    while ((line = in.readLine()) != null) {
      if (line.length() == 0)
        continue;
      if (line.charAt(0) == '#' || line.charAt(0) == '!')
        continue;
      while (linespans(line)) {
        line = line.substring(0, line.length()-1);
        String nextline = in.readLine();
        if (nextline != null)
          for (int i = 0; i < nextline.length(); i++)
            if (!Character.isSpace(nextline.charAt(i))) {
              line += nextline.substring(i);
              break;
            }
      }
      int length = line.length();
      int start = 0;
      while (start < length) {
        if (!Character.isSpace(line.charAt(start)))
          break;
        start++;
      }
      if (start == length)
        continue;
      int end = start;
      while (end < length) {
        char shar = line.charAt(end);
        if (shar == '=' || shar == ':' || Character.isSpace(shar))
          break;
        if (shar == '\\')
          end++;
        end++;
      }
      String key = line.substring(start, end);
      start = end;
      while (start < length) {
        if (!Character.isSpace(line.charAt(start)))
          break;
        start++;
      }
      if (start < length) {
        char shar = line.charAt(start);
        if (shar == '=' || shar == ':')
          start++;
      }
      while (start < length) {
        if (!Character.isSpace(line.charAt(start)))
          break;
        start++;
      }
      end = line.length();
      String value = line.substring(start, end);
      key = stringdec(key);
      value = stringdec(value);
      put(key, value);

    }
  }

  private boolean linespans(String line) {
    boolean linespans = false;
    for (int i = line.length()-1; i >= 0; i--) {
      if (line.charAt(i) != '\\')
        break;
      linespans = !linespans;
    }
    return linespans;
  }

  private String stringdec(String string) {
    int length = string.length();
    StringBuffer sb = new StringBuffer(length);
    for (int i = 0; i < length; i++) {
      char shar = string.charAt(i);
      if (shar == '\\') {
        shar = string.charAt(++i);
        switch (shar) {
        case 't':
          shar = '\t';
          break;
        case 'f':
          shar = '\f';
          break;
        case 'n':
          shar = '\n';
          break;
        case 'r':
          shar = '\r';
          break;
        case 'u':
          if (i+4 < length) {
            int value = 0;
            for (int j = 1; j <= 4; j++) {
              char hex = string.charAt(i+j);
              int digit = Character.digit(hex, 16);
              if (hex > '\u007F')
                digit = -1;
              value = value << 4 | digit;
            }
            if (value >= 0) {
              shar = (char)value;
              i += 4;
            }
          }
          break;
        }
      }
      sb.append(shar);
    }
    return sb.toString();
  }

  public synchronized void store(OutputStream os, String header) throws IOException {
    PrintWriter out = new PrintWriter(new OutputStreamWriter(os, "8859_1"));
    if (header != null) {
      out.print("#");
      out.println(header);
    }
    out.print("#");
    out.println(/*new Date()*/);
    for (Enumeration e = keys(); e.hasMoreElements(); ) {
      String key = (String)e.nextElement();
      String value = (String)get(key);
      printenc(out, true, key);
      out.print("=");
      printenc(out, false, value);
      out.println();
    }
    out.flush();
    if (out.checkError())
      throw new IOException();
  }

  private void printenc(PrintWriter out, boolean isKey, String string) {
    int length = string.length();
    for (int i = 0; i < length; i++) {
      char shar = string.charAt(i);
      switch (shar) {
      case ' ': 
        if (i == 0 || isKey)
          out.print("\\");
        out.print(shar);
        break;
      case '#':
      case '!':
      case ':':
      case '=':
      case '\\':
        out.print("\\");
        out.print(shar);
        break;
      case '\t':
        out.print("\\t");
        break;
      case '\f':
        out.print("\\f");
        break;
      case '\n':
        out.print("\\n");
        break;
      case '\r':
        out.print("\\r");
        break;
      default:
        if ('\u0020' <= shar && shar <= '\u007E')
          out.print(shar);
        else {
          out.print("\\u");
          out.print(Character.forDigit((shar >> 12) & 0xF, 16));
          out.print(Character.forDigit((shar >>  8) & 0xF, 16));
          out.print(Character.forDigit((shar >>  4) & 0xF, 16));
          out.print(Character.forDigit((shar >>  0) & 0xF, 16));
        }
      }
    }
  }

}

