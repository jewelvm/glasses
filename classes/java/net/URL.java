/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Permission;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

public final class URL implements Serializable {

  private static final long serialVersionUID = -7627629688361524110L;

  private static URLStreamHandlerFactory factory;
  private static final Hashtable handlers = new Hashtable();

  public static synchronized void setURLStreamHandlerFactory(URLStreamHandlerFactory factory) {
    if (URL.factory != null)
      throw new Error("Factory already set");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSetFactory();
    URL.factory = factory;
    handlers.clear();
  }

  private static synchronized URLStreamHandler getURLStreamHandler(String protocol) {
    protocol = protocol.trim();
    protocol = protocol.toLowerCase();

    URLStreamHandler handler = (URLStreamHandler)handlers.get(protocol);
    if (handler == null) {
      if (factory != null)
        handler = factory.createURLStreamHandler(protocol);
      if (handler == null)
        if (protocol.equals("file"))
          handler = new URLStreamHandler() {
            public URLConnection openConnection(URL url) {
              return new FileURLConnection(url);
            }
          };
        else if (protocol.equals("jar"))
          handler = new URLStreamHandler() {
            public URLConnection openConnection(URL url) throws IOException {
              try {
                return new JarEntryURLConnection(url);
              } catch (MalformedURLException e) {
                throw new IOException("Malformed jar url");
              }
            }
          };
      if (handler == null) {
        String pkgs = System.getProperty("java.protocol.handler.pkgs", "");
        pkgs += "|sun.net.www.protocol";
        StringTokenizer st = new StringTokenizer(pkgs, "|");
        while (st.hasMoreTokens()) {
          String pkg = st.nextToken();
          pkg = pkg.trim();
          try {
            Class clazz = Class.forName(pkg+"."+protocol+".Handler");
            handler = (URLStreamHandler)clazz.newInstance();
          } catch (Exception e) {
            continue;
          }
          break;
        }
      }
      if (handler != null)
        handlers.put(protocol, handler);
    }
    return handler;
  }

  private static final class FileURLConnection extends URLConnection {
    
    private final File file;
    private FileInputStream in;
    
    public FileURLConnection(URL url) {
      super(url);
      file = new File(url.getFile());
    }
    
    public Permission getPermission() {
      return null/*new FilePermission(file.getPath(), "read")*/;
    }
    
    public void connect() throws IOException {
      if (!connected) {
        in = new FileInputStream(file);
        connected = true;
      }
    }
    
    public String getHeaderFieldKey(int index) {
      switch (index) {
      case 0: return "content-type";
      case 1: return "content-length";
      }
      return null;
    }
    
    public String getHeaderField(String name) {
      try {
        connect();
        if (name.equals("content-type"))
          return guessContentTypeFromName(file.getPath());
        if (name.equals("content-length"))
          return Long.toString(file.length());
      } catch (IOException e) { }
      return null;
    }
    
    public InputStream getInputStream() throws IOException {
      connect();
      return in;
    }
    
  }

  private static final class JarEntryURLConnection extends JarURLConnection {

    private static final HashMap cache = new HashMap();

    private static synchronized JarFile getJarFile(File file) throws IOException {
      String path = file.getCanonicalPath();
      JarFile jar = (JarFile)cache.get(path);
      if (jar == null) {
        jar = new JarFile(file);
        cache.put(path, jar);
      }
      return jar;
    }

    private JarFile jar;
    private JarEntry entry;

    public JarEntryURLConnection(URL url) throws MalformedURLException {
      super(url);
    }

    public JarFile getJarFile() throws IOException {
      connect();
      return jar;
    }

    public Permission getPermission() throws IOException {
      return getJarFileURL().openConnection().getPermission();
    }
    
    public void connect() throws IOException {
      if (!connected) {
        URL url = getJarFileURL();
        jar = getJarFile(new File(url.getFile()));
        String name = getEntryName();
        if (name != null) {
          entry = jar.getJarEntry(name);
          if (entry == null)
            throw new IOException("Entry not found");
        }
        connected = true;
      }
    }

    public String getHeaderFieldKey(int index) {
      switch (index) {
      case 0: return "content-type";
      case 1: return "content-length";
      }
      return null;
    }
    
    public String getHeaderField(String name) {
      try {
        connect();
        if (name.equals("content-type"))
          return guessContentTypeFromName(getEntryName());
        if (name.equals("content-length"))
          if (entry != null)
            return Long.toString(entry.getSize());
      } catch (IOException e) { }
      return null;
    }
    
    public InputStream getInputStream() throws IOException {
      connect();
      if (entry == null)
        throw new IOException("Entry not specified");
      return jar.getInputStream(entry);
    }
    
  }
    
  private String protocol;
  private String host;
  private int port;
  private String file;
  private String ref;
  private transient URLStreamHandler handler;
  private transient String query;
  private transient String path;
  private transient String userInfo;
  private String authority;

  public URL(String protocol, String host, String file) throws MalformedURLException {
    this(protocol, host, -1, file);
  }

  public URL(String protocol, String host, int port, String file) throws MalformedURLException {
    this(protocol, host, port, file, null);
  }

  public URL(String protocol, String host, int port, String file, URLStreamHandler handler) throws MalformedURLException {
    if (handler != null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null)
        sm.checkPermission(new NetPermission("specifyStreamHandler"));
    }
    this.protocol = protocol;
    this.host = host;
    this.port = port;
    int index = file.indexOf('#');
    if (index == -1) {
      this.file = file;
      this.ref = null;
    } else {
      this.file = file.substring(0, index);
      this.ref = file.substring(index+1);
    }
    if (handler == null) {
      handler = getURLStreamHandler(protocol);
      if (handler == null)
        throw new MalformedURLException("Unknown protocol");
    }
    this.handler = handler;
  }

  public URL(String spec) throws MalformedURLException {
    this(null, spec);
  }

  public URL(URL context, String spec) throws MalformedURLException {
    this(context, spec, null);
  }

  public URL(URL context, String spec, URLStreamHandler handler) throws MalformedURLException {
    if (handler != null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null)
        sm.checkPermission(new NetPermission("specifyStreamHandler"));
    }
    spec = spec.trim();
    if (spec.startsWith("url:"))
      spec = spec.substring(4);
    int index = spec.indexOf(':');
    if (index != -1) {
      protocol = spec.substring(0, index);
      protocol = protocol.toLowerCase();
      for (int i = 0; i < protocol.length(); i++) {
        char chr = protocol.charAt(i);
        if (chr != '.' && chr != '+' && chr != '-' && !Character.isLetterOrDigit(chr))
          throw new MalformedURLException("Invalid protocol: "+protocol);
      }
      spec = spec.substring(index+1);
    }
    if (context != null) {
      if (protocol == null)
        protocol = context.protocol;
      if (protocol.equalsIgnoreCase(context.protocol)) {
        host = context.host;
        port = context.port;
        file = context.file;
        if (handler == null)
          handler = context.handler;
      }
    }
    if (protocol == null)
      throw new MalformedURLException("No protocol");
    index = spec.indexOf('#');
    if (index != -1) {
      ref = spec.substring(index+1);
      spec = spec.substring(0, index);
    }
    if (handler == null) {
      handler = getURLStreamHandler(protocol);
      if (handler == null)
        throw new MalformedURLException("Unknown protocol");
    }
    this.handler = handler;
    handler.parseURL(this, spec, 0, spec.length());
  }

  public String getProtocol() {
    return protocol;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port != -1 ? port : handler.getDefaultPort();
  }

  public String getFile() {
    return file;
  }

  public String getRef() {
    return ref;
  }

  public String getQuery() {
    return query;
  }

  public String getPath() {
    return path;
  }

  public String getUserInfo() {
    return userInfo;
  }

  public String getAuthority() {
    return authority;
  }

  protected synchronized void set(String protocol, String host, int port, String file, String ref) {
    this.protocol = protocol;
    this.host = host;
    this.port = port;
    this.file = file;
    this.ref = ref;
  }

  protected synchronized void set(String protocol, String host, int port, String authority, String userInfo,
                                  String path, String query, String ref) {
    this.protocol = protocol;
    this.host = host;
    this.port = port;
    this.authority = authority;
    this.userInfo = userInfo;
    this.path = path;
    this.query = query;
    this.ref = ref;
    this.file = query == null ? path : path+"?"+query;
  }

  public URLConnection openConnection() throws IOException {
    return handler.openConnection(this);
  }

  public InputStream openStream() throws IOException {
    return openConnection().getInputStream();
  }

  public Object getContent() throws IOException {
    return openConnection().getContent();
  }

  public Object getContent(Class[] classes) throws IOException {
    return openConnection().getContent(classes);
  }

  public boolean sameFile(URL url) {
    return handler.sameFile(this, url);
  }

  public int hashCode() {
    return handler.hashCode(this);
  }

  public boolean equals(Object object) {
    return object instanceof URL
        && handler.equals(this, (URL)object);
  }

  public String toString() {
    return toExternalForm();
  }

  public String toExternalForm() {
    return handler.toExternalForm(this);
  }

//  private void writeObject(ObjectOutputStream s) throws IOException {
//  }

//  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
//  }

}

