/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AllPermission;
import java.security.Permission;
import java.util.Hashtable;
import java.util.StringTokenizer;

//import java.util.Date;

public abstract class URLConnection {

  private static FileNameMap fileNameMap;
  private static ContentHandlerFactory factory;
  private static final Hashtable handlers = new Hashtable();

  private static boolean defaultAllowUserInteraction = false;
  private static boolean defaultUseCaches = true;

  public static void setFileNameMap(FileNameMap fileNameMap) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSetFactory();
    URLConnection.fileNameMap = fileNameMap;
  }

  public static FileNameMap getFileNameMap() {
    return fileNameMap;
  }

  protected static String guessContentTypeFromName(String name) {
    if (fileNameMap != null)
      return fileNameMap.getContentTypeFor(name);
    return null;
  }

  public static String guessContentTypeFromStream(InputStream in) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public static synchronized void setContentHandlerFactory(ContentHandlerFactory factory) {
    if (URLConnection.factory != null)
      throw new Error("factory already defined");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSetFactory();
    URLConnection.factory = factory;
    handlers.clear();
  }

  private static synchronized ContentHandler getContentHandler(String contentType) {
    contentType = contentType.trim();
    contentType = contentType.toLowerCase();
    contentType = contentType.replace('/', '.');
    StringBuffer sb = new StringBuffer(contentType);
    for (int i = 0; i < sb.length(); i++) {
      char shar = sb.charAt(i);
      if (!Character.isJavaIdentifierPart(shar))
        if (shar != '.')
          sb.setCharAt(i, '_');
    }
    contentType = sb.toString();

    ContentHandler handler = (ContentHandler)handlers.get(contentType);
    if (handler == null) {
      if (factory != null)
        handler = factory.createContentHandler(contentType);
      if (handler == null) {
        String pkgs = System.getProperty("java.content.handler.pkgs", "");
        pkgs += "|sun.net.www.content";
        StringTokenizer st = new StringTokenizer(pkgs, "|");
        while (st.hasMoreTokens()) {
          String pkg = st.nextToken();
          pkg = pkg.trim();
          try {
            Class clazz = Class.forName(pkg+"."+contentType);
            handler = (ContentHandler)clazz.newInstance();
          } catch (Exception e) {
            continue;
          }
          break;
        }
      }
      if (handler != null)
        handlers.put(contentType, handler);
    }
    return handler;
  }

  public static void setDefaultAllowUserInteraction(boolean defaultAllowUserInteraction) {
    URLConnection.defaultAllowUserInteraction = defaultAllowUserInteraction;
  }

  public static boolean getDefaultAllowUserInteraction() {
    return defaultAllowUserInteraction;
  }

  public void setDefaultUseCaches(boolean defaultUseCaches) {
    URLConnection.defaultUseCaches = defaultUseCaches;
  }

  public boolean getDefaultUseCaches() {
    return defaultUseCaches;
  }

  /** @deprecated */
  public static void setDefaultRequestProperty(String key, String value) {
    throw new InternalError("Unimplemented");
  }

  /** @deprecated */
  public static String getDefaultRequestProperty(String key) {
    throw new InternalError("Unimplemented");
  }

  protected URL url;
  protected boolean connected = false;
  protected boolean doInput = true;
  protected boolean doOutput = false;
  protected boolean allowUserInteraction = defaultAllowUserInteraction;
  protected boolean useCaches = defaultUseCaches;
  protected long ifModifiedSince;

  protected URLConnection(URL url) {
    this.url = url;
  }

  public URL getURL() {
    return url;
  }

  public Permission getPermission() throws IOException {
    return new AllPermission();
  }

  public abstract void connect() throws IOException;

  public String getHeaderFieldKey(int index) {
    return null;
  }

  public String getHeaderField(int index) {
    String key = getHeaderFieldKey(index);
    if (key == null)
      return null;
    return getHeaderField(key);
  }

  public String getHeaderField(String name) {
    return null;
  }

  public int getHeaderFieldInt(String name, int value) {
    try {
      return Integer.parseInt(getHeaderField(name));
    } catch(NumberFormatException e) {
      return value;
    }
  }

  public long getHeaderFieldDate(String name, long value) {
//    try {
//      return Date.parse(getHeaderField(name));
//    } catch(Throwable t) { }
    return value;
  }

  public String getContentType() {
    return getHeaderField("content-type");
  }

  public String getContentEncoding() {
    return getHeaderField("content-encoding");
  }

  public int getContentLength() {
    return getHeaderFieldInt("content-length", -1);
  }

  public Object getContent() throws IOException {
    String contentType = getContentType();
    if (contentType == null)
      throw new UnknownServiceException("No content-type");
    ContentHandler handler = getContentHandler(contentType);
    if (handler == null)
      return getInputStream();
    return handler.getContent(this);
  }

  public Object getContent(Class[] classes) throws IOException {
    String contentType = getContentType();
    if (contentType == null)
      throw new UnknownServiceException("No content-type");
    ContentHandler handler = getContentHandler(contentType);
    if (handler == null) {
      Object object = getInputStream();
      for (int i = 0; i < classes.length; i++)
        if (classes[i].isInstance(object))
          return object;
      return null;
    }
    return handler.getContent(this, classes);
  }

  public long getDate() {
    return getHeaderFieldDate("date", 0);
  }

  public long getExpiration() {
    return getHeaderFieldDate("expires", 0);
  }

  public long getLastModified() {
    return getHeaderFieldDate("last-modified", 0);
  }

  public void setDoInput(boolean doinput) {
    if (connected)
      throw new IllegalStateException("Already connected");
    doInput = doinput;
  }

  public boolean getDoInput() {
    return doInput;
  }

  public InputStream getInputStream() throws IOException {
    throw new UnknownServiceException("Protocol does not support input");
  }

  public void setDoOutput(boolean dooutput) {
    if (connected)
      throw new IllegalStateException("Already connected");
    doOutput = dooutput;
  }

  public boolean getDoOutput() {
    return doOutput;
  }

  public OutputStream getOutputStream() throws IOException {
    throw new UnknownServiceException("Protocol does not support output");
  }

  public void setAllowUserInteraction(boolean allowUserInteraction) {
    if (connected)
      throw new IllegalStateException("Already connected");
    this.allowUserInteraction = allowUserInteraction;
  }

  public boolean getAllowUserInteraction() {
    return allowUserInteraction;
  }

  public void setUseCaches(boolean useCaches) {
    if (connected)
      throw new IllegalStateException("Already connected");
    this.useCaches = useCaches;
  }

  public boolean getUseCaches() {
    return useCaches;
  }

  public void setIfModifiedSince(long ifModifiedSince) {
    if (connected)
      throw new IllegalStateException("Already connected");
    this.ifModifiedSince = ifModifiedSince;
  }

  public long getIfModifiedSince() {
    return ifModifiedSince;
  }

  public void setRequestProperty(String key, String value) {
    if (connected)
      throw new IllegalStateException("Already connected");
  }

  public String getRequestProperty(String key) {
    if (connected)
      throw new IllegalStateException("Already connected");
    return null;
  }

  public String toString() {
    return getClass().getName()+":"+url;
  }

}

