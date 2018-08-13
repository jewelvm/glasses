/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.AccessControlContext;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.Manifest;

//import java.io.FilePermission;
//import java.net.SocketPermission;

public class URLClassLoader extends SecureClassLoader {

  public static URLClassLoader newInstance(final URL[] urls) {
    URLClassLoader loader = (URLClassLoader)AccessController.doPrivileged(
      new PrivilegedAction() {
        public Object run() {
          return new URLClassLoader(urls);
        }
      }
    );
    loader.checkPackageAccess = true;
    loader.context = AccessController.getContext();
    return loader;
  }

  public static URLClassLoader newInstance(final URL[] urls, final ClassLoader parent) {
    URLClassLoader loader = (URLClassLoader)AccessController.doPrivileged(
      new PrivilegedAction() {
        public Object run() {
          return new URLClassLoader(urls, parent);
        }
      }
    );
    loader.checkPackageAccess = true;
    loader.context = AccessController.getContext();
    return loader;
  }

  private boolean checkPackageAccess;
  private AccessControlContext context;
  private final URLClassPath classPath;

  public URLClassLoader(URL[] urls) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkCreateClassLoader();
    context = AccessController.getContext();
    classPath = new URLClassPath(urls);
  }

  public URLClassLoader(URL[] urls, ClassLoader parent) {
    super(parent);
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkCreateClassLoader();
    context = AccessController.getContext();
    classPath = new URLClassPath(urls);
  }

  public URLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
    super(parent);
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkCreateClassLoader();
    context = AccessController.getContext();
    classPath = new URLClassPath(urls, factory.createURLStreamHandler("jar"));
  }

  protected void addURL(URL url) {
    classPath.addURL(url);
  }

  public URL[] getURLs() {
    return classPath.getURLs();
  }

  protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
    if (checkPackageAccess) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        int start = name.startsWith("[") ? name.indexOf('L')+1 : 0;
        int end = name.lastIndexOf('.');
        if (end != -1) {
          String packageName = name.substring(start, end);
          sm.checkPackageAccess(packageName);
        }
      }
    }
    return super.loadClass(name, resolve);
  }

  protected Class findClass(final String name) throws ClassNotFoundException {
    try {
      return (Class)AccessController.doPrivileged(
        new PrivilegedExceptionAction(){
          public Object run() throws ClassNotFoundException {
            byte[] buffer = classPath.getResourceBytes(name.replace('.', '/').concat(".class"));
            URL url = null/* source (original) baseURL */;
            Certificate[] certificates = null/* jarEntry.getCertificates() */;
            if (buffer == null)
              throw new ClassNotFoundException(name);
            int index = name.lastIndexOf('.');
            if (index != -1) {
              String packageName = name.substring(0, index);
              Package pakcage = getPackage(packageName);
              if (pakcage == null) {
                Manifest manifest = null/*jar.getManifest()*/;
                if (manifest != null)
                  definePackage(packageName, manifest, url);
                else
                  definePackage(packageName, null, null, null, null, null, null, null);
              }
//              if (!pakcage.isSealed(url) || !((man != null) && isSealed(pkgname, man)))
//                throw new SecurityException("Sealing violation");
            }
            return defineClass(name, buffer, 0, buffer.length, new CodeSource(url, certificates));
          }
        }, context
      );
    } catch (PrivilegedActionException t) {
      Exception e = t.getException();
      if (e instanceof ClassNotFoundException)
        throw (ClassNotFoundException)e;
      if (e instanceof RuntimeException)
        throw (RuntimeException)e;
      throw new UndeclaredThrowableException(e);
    }
  }

  public Enumeration findResources(String name) throws IOException {
    return classPath.getResources(name);
  }

  public URL findResource(String name) {
    return classPath.getResource(name);
  }

  protected Package definePackage(String name, Manifest manifest, URL sealBase) {
    String specificationTitle = null;
    String specificationVersion = null;
    String specificationVendor = null;
    String implementationTitle = null;
    String implementationVersion = null;
    String implementationVendor = null;
    String sealed = null;
    Attributes attributes = manifest.getAttributes(name.replace('.', '/').concat("/"));
    if (attributes != null) {
      specificationTitle = attributes.getValue(Name.SPECIFICATION_TITLE);
      specificationVersion = attributes.getValue(Name.SPECIFICATION_VERSION);
      specificationVendor = attributes.getValue(Name.SPECIFICATION_VENDOR);
      implementationTitle = attributes.getValue(Name.IMPLEMENTATION_TITLE);
      implementationVersion = attributes.getValue(Name.IMPLEMENTATION_VERSION);
      implementationVendor = attributes.getValue(Name.IMPLEMENTATION_VENDOR);
      sealed = attributes.getValue(Name.SEALED);
    }
    attributes = manifest.getMainAttributes();
    if (attributes != null) {
      if (specificationTitle == null)
        specificationTitle = attributes.getValue(Name.SPECIFICATION_TITLE);
      if (specificationVersion == null)
        specificationVersion = attributes.getValue(Name.SPECIFICATION_VERSION);
      if (specificationVendor == null)
        specificationVendor = attributes.getValue(Name.SPECIFICATION_VENDOR);
      if (implementationTitle == null)
        implementationTitle = attributes.getValue(Name.IMPLEMENTATION_TITLE);
      if (implementationVersion == null)
        implementationVersion = attributes.getValue(Name.IMPLEMENTATION_VERSION);
      if (implementationVendor == null)
        implementationVendor = attributes.getValue(Name.IMPLEMENTATION_VENDOR);
      if (sealed == null)
        sealed = attributes.getValue(Name.SEALED);
    }
    if (!"true".equalsIgnoreCase(sealed))
      sealBase = null;
    return definePackage(name, specificationTitle, specificationVersion, specificationVendor,
                         implementationTitle, implementationVersion, implementationVendor, sealBase);
  }

  protected PermissionCollection getPermissions(CodeSource codeSource) {
    PermissionCollection permissions = super.getPermissions(codeSource);
//    URL url = codeSource.getLocation();
//    String protocol = url.getProtocol();
//    String host = url.getHost();
//    if (host == null)
//      host = "localhost";
//    String file = url.getFile();
//    file = file.replace('/', File.separatorChar);
//    if (file.endsWith(File.separator))
//      file += "-";
//    Permission permission = protocol.equals("file")
//                          ? new FilePermission(file, "read")
//                          : new SocketPermission(host, "connect,accept");
//    SecurityManager sm = System.getSecurityManager();
//    if (sm != null)
//      sm.checkPermission(permission);
//    permissions.add(permission);
    return permissions;
  }

  public static final class URLClassPath {

    private final ArrayList list;
    private final URLStreamHandler handler;

    public URLClassPath(URL[] urls) {
      this(urls, null);
    }

    public URLClassPath(URL[] urls, URLStreamHandler handler) {
      list = new ArrayList(urls.length);
      for (int i = 0; i < urls.length; i++)
        list.add(urls[i]);
      this.handler = handler;
    }

    public void addURL(URL url) {
      list.add(url);
    }

    public URL[] getURLs() {
      return (URL[])list.toArray(new URL[list.size()]);
    }

    private void listResources(URL sourceURL, String name, Vector resources, int limit) {
      if (resources.size() < limit) {

        URL baseURL = sourceURL;

        if (!baseURL.getFile().endsWith("/"))
          try {
            baseURL = new URL("jar", "", -1, baseURL+"!/", handler);
          } catch (MalformedURLException e) { }

        try {
          URL url = new URL(baseURL, name);
          URLConnection connection = url.openConnection();
          SecurityManager sm = System.getSecurityManager();
          if (sm != null) {
            Permission permission = connection.getPermission();
            if (permission != null)
              sm.checkPermission(permission);
          }
          InputStream in = connection.getInputStream();
          in.close();
          resources.add(url);
        } catch (Exception e) { }

        if (baseURL.getProtocol().equals("jar"))
          try {
            URLConnection connection = baseURL.openConnection();
            if (connection instanceof JarURLConnection) {
              JarURLConnection jarConnection = (JarURLConnection)connection;
              Attributes attributes = jarConnection.getMainAttributes();
              if (attributes != null) {
                String value = attributes.getValue(Name.CLASS_PATH);
                if (value != null) {
                  StringTokenizer st = new StringTokenizer(value);
                  while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    URL subURL;
                    try {
                      subURL = new URL(sourceURL, token);
                    } catch (MalformedURLException e) {
                      continue;
                    }
                    listResources(subURL, name, resources, limit);
                  }
                }
              }
            }
          } catch (Exception e) { }

      }
    }

    public Enumeration getResources(String name) {
      Vector resources = new Vector();
      for (Iterator i = list.iterator(); i.hasNext(); ) {
        URL sourceURL = (URL)i.next();
        listResources(sourceURL, name, resources, Integer.MAX_VALUE);
      }
      return resources.elements();
    }

    public URL getResource(String name) {
      Vector resources = new Vector(1);
      for (Iterator i = list.iterator(); i.hasNext(); ) {
        URL sourceURL = (URL)i.next();
        listResources(sourceURL, name, resources, 1);
        if (resources.size() > 0)
          return (URL)resources.get(0);
      }
      return null;
    }

    // improve interface
    public byte[] getResourceBytes(String name) {
      URL url = getResource(name);
      if (url == null)
        return null;
      try {
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        try {
          int length = connection.getContentLength();
          if (length <= 0)
            length = 1024;
          int total = 0;
          byte[] buffer = new byte[length];
          int read;
          while ((read = in.read(buffer, total, buffer.length-total)) != -1) {
            total += read;
            if (total == buffer.length) {
              byte[] tmp = buffer;
              buffer = new byte[2*total];
              System.arraycopy(tmp, 0, buffer, 0, tmp.length);
            }
          }
          if (total < buffer.length) {
            byte[] tmp = buffer;
            buffer = new byte[total];
            System.arraycopy(tmp, 0, buffer, 0, buffer.length);
          }
          return buffer;
        } finally {
          in.close();
        }
      } catch (IOException e) {
        return null;
      }
    }

  }

}

