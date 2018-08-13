/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLClassLoader.URLClassPath;
import java.security.CodeSource;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class ClassLoader {

  private static ClassLoader systemLoader;
  private static final HashMap systemPackages = new HashMap();
  private static final HashMap bootstrapClasses = new HashMap();
  private static final HashMap bootstrapConstraints = new HashMap();
  private static final ArrayList systemLibraries = new ArrayList();
  private static final ThreadLocal loadingStack = new ThreadLocal();

  private static URLClassPath classPath;

  /*private*/ static Class getClass(ClassLoader loader, String name) {
    HashMap classes = loader == null ? bootstrapClasses : loader.classes;
    synchronized (classes) {
      return (Class)classes.get(name);
    }
  }

  private static void putClass(ClassLoader loader1, String name, Class clazz) {
    ClassLoader loader2;
    HashMap classes;
    HashMap constraints;
    if (loader1 == null) {
      classes = bootstrapClasses;
      constraints = bootstrapConstraints;
    } else {
      classes = loader1.classes;
      constraints = loader1.constraints;
    }
    for (int index = 0; ; index++) {
      synchronized (classes) {
        ArrayList loaders = (ArrayList)constraints.get(name);
        if (loaders == null || index >= loaders.size()) {
          if (classes.get(name) != null)
            throw new LinkageError(name);
          constraints.remove(name);
          classes.put(name, clazz);
          return;
        }
        loader2 = (ClassLoader)loaders.get(index);
      }
      Class far = getClass(loader2, name);
      if (far != null && clazz != far)
        throw new LinkageError(name);
    }
  }

  private static void imposeLoadingConstraint(ClassLoader loader1, ClassLoader loader2, String name) {
    HashMap classes;
    HashMap constraints;
    if (loader1 == null) {
      classes = bootstrapClasses;
      constraints = bootstrapConstraints;
    } else {
      classes = loader1.classes;
      constraints = loader1.constraints;
    }
    Class clazz;
    synchronized (classes) {
      clazz = (Class)classes.get(name);
      if (clazz == null) {
        ArrayList loaders = (ArrayList)constraints.get(name);
        if (loaders == null) {
          loaders = new ArrayList();
          constraints.put(name, loaders);
        }
        loaders.add(loader2);
        return;
      }
    }
    Class far = getClass(loader2, name);
    if (far != null && clazz != far)
      throw new LinkageError();
  }

  static Class findClass(ClassLoader loader, String name) throws ClassNotFoundException {
    Class clazz = getClass(loader, name);
    if (clazz == null) {
      int dims = name.lastIndexOf('[')+1;
      if (dims == 0) {
        if (loader != null)
          clazz = loader.loadClass(name);
        else {
          byte[] buffer = null;
          if (classPath != null)
            buffer = classPath.getResourceBytes(name.replace('.', '/').concat(".class"));
          if (buffer == null)
            throw new ClassNotFoundException(name.replace('.', '/'));
          clazz = defineClass(null, name, buffer, 0, buffer.length);
        }
      } else {
        Class elem;
        switch (name.charAt(dims)) {
        case 'Z': elem = Boolean.TYPE; break;
        case 'B': elem = Byte.TYPE; break;
        case 'C': elem = Character.TYPE; break;
        case 'S': elem = Short.TYPE; break;
        case 'I': elem = Integer.TYPE; break;
        case 'J': elem = Long.TYPE; break;
        case 'F': elem = Float.TYPE; break;
        case 'D': elem = Double.TYPE; break;
        default: 
          try {
            elem = findClass(loader, name.substring(dims+1, name.length()-1));
          } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
          }
          break;
        }
        if (elem.getDefiningLoader() != loader)
          clazz = findClass(elem.getDefiningLoader(), name);
        else {
          byte[] buffer = createArrayImage(name.replace('.', '/'), Modifier.isPublic(elem.getModifiers()));
          clazz = defineClass(elem.getDefiningLoader(), name, buffer, 0, buffer.length);
        }
      }
      if (clazz == null)
        throw new ClassNotFoundException(name.replace('.', '/'));
      if (clazz.getDefiningLoader() != loader)
        putClass(loader, name, clazz);
    }
    return clazz;
  }

  private static byte[] createArrayImage(String name, boolean isPublic) {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bout);
    try {
      out.writeInt(0xCAFEBABE);
      out.writeChar(3);
      out.writeChar(45);
      out.writeChar(9);
      out.writeByte(7);
      out.writeChar(5);
      out.writeByte(7);
      out.writeChar(6);
      out.writeByte(7);
      out.writeChar(7);
      out.writeByte(7);
      out.writeChar(8);
      out.writeByte(1);
      out.writeUTF(name);
      out.writeByte(1);
      out.writeUTF("java/lang/Object");
      out.writeByte(1);
      out.writeUTF("java/lang/Cloneable");
      out.writeByte(1);
      out.writeUTF("java/io/Serializable");
      out.writeChar(Modifier.FINAL|Modifier.ABSTRACT|Modifier.SYNCHRONIZED|(isPublic ? Modifier.PUBLIC : 0));
      out.writeChar(1);
      out.writeChar(2);
      out.writeChar(2);
      out.writeChar(3);
      out.writeChar(4);
      out.writeChar(0);
      out.writeChar(0);
      out.writeChar(0);
      out.flush();
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
    return bout.toByteArray();
  }

  private static Class defineClass(ClassLoader loader, String name, byte[] array, int start, int length) {
    Class clazz = getClass(loader, name);
    if (clazz != null)
      throw new LinkageError(name);

    Object[] st = (Object[])loadingStack.get();
    for (Object[] i = st; i != null; i = (Object[])i[2])
      if (loader == i[1] && name.equals(i[0]))
        throw new ClassCircularityError(name);
    st = new Object[] { name, loader, st };
    loadingStack.set(st);

    try {
      clazz = Class.derive(loader, name, array, start, length);
    } finally {
      st = (Object[])loadingStack.get();
      st = (Object[])st[2];
      loadingStack.set(st);
    }
    putClass(loader, name, clazz);
    return clazz;
  }

  private static Enumeration getBootstrapResources(String name) {
    if (classPath != null)
      return classPath.getResources(name);
    return new Vector(0).elements();
  }

  private static URL getBootstrapResource(String name) {
    if (classPath != null)
      return classPath.getResource(name);
    return null;
  }

  private static void installBootstrapLoader() {
    String path = System.getProperty("java.vm.class.path");
    StringTokenizer st = new StringTokenizer(path, File.pathSeparator);
    URL[] urls = new URL[st.countTokens()];
    for (int i = 0; i < urls.length; i++) {
      String fileName = st.nextToken();
      File file = new File(fileName);
      try {
        urls[i] = file.toURL();
      } catch (MalformedURLException e) {
        throw new InternalError(e.getMessage());
      }
    }
    classPath = new URLClassPath(urls);
  }

  private static void installSystemLoader() {
    String exts = System.getProperty("java.ext.dirs");
    StringTokenizer est = new StringTokenizer(exts, File.pathSeparator);
    final File[] extdirs = new File[est.countTokens()];
    ArrayList extlist = new ArrayList();
    for (int i = 0; i < extdirs.length; i++) {
      String dirName = est.nextToken();
      File dir = new File(dirName);
      extdirs[i] = dir;
      File[] list = dir.listFiles();
      if (list != null)
        for (int j = 0; j < list.length; j++)
          try {
            extlist.add(list[j].toURL());
          } catch (MalformedURLException e) {
            throw new InternalError(e.getMessage());
          }
    }
    URL[] exturls = (URL[])extlist.toArray(new URL[extlist.size()]);
    ClassLoader extLoader = new URLClassLoader(exturls, null) {
      protected String findLibrary(String name) {
        for (int i = 0; i < extdirs.length; i++) {
          File file = new File(extdirs[i], System.mapLibraryName(name));
          if (file.exists())
            return file.getAbsolutePath();
        }
        return null;
      }
    };
    String path = System.getProperty("java.class.path");
    StringTokenizer pst = new StringTokenizer(path, File.pathSeparator);
    URL[] sysurls = new URL[pst.countTokens()];
    for (int i = 0; i < sysurls.length; i++) {
      String fileName = pst.nextToken();
      File file = new File(fileName);
      try {
        sysurls[i] = file.toURL();
      } catch (MalformedURLException e) {
        throw new InternalError(e.getMessage());
      }
    }
    ClassLoader sysLoader = new URLClassLoader(sysurls, extLoader);
    setSystemClassLoader(sysLoader);
  }

  private static void setSystemClassLoader(ClassLoader loader) {
    systemLoader = loader;
  }

  public static ClassLoader getSystemClassLoader() {
    if (systemLoader != null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        Class clazz = Thread.callerClass();
        if (clazz != null) {
          ClassLoader caller = clazz.getDefiningLoader();
          if (caller != null && !caller.parentOf(systemLoader))
            sm.checkPermission(new RuntimePermission("getClassLoader"));
        }
      }
    }
    return systemLoader;
  }

  public static boolean isSystemClassLoader(ClassLoader loader) {
    return loader == null
        || loader.parentOf(systemLoader);
  }

  public static Enumeration getSystemResources(String name) throws IOException {
    ClassLoader loader = getSystemClassLoader();
    if (loader != null) 
      return loader.getResources(name);
    return getBootstrapResources(name);
  }
  
  public static URL getSystemResource(String name) {
    ClassLoader loader = getSystemClassLoader();
    if (loader != null) 
      return loader.getResource(name);
    return getBootstrapResource(name);
  }

  public static InputStream getSystemResourceAsStream(String name) {
    URL resource = getSystemResource(name);
    if (resource != null)
      try {
        return resource.openStream();
      } catch (IOException e) { }
    return null;
  }

  static Package defineSystemPackage(String name, String specTitle, String specVersion, String specVendor, 
                                     String implTitle, String implVersion, String implVendor, URL url) {
    synchronized (systemPackages) {
      Package pakcage = getSystemPackage(name);
      if (pakcage != null)
        throw new IllegalArgumentException(name);
      pakcage = new Package(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, url);
      systemPackages.put(name, pakcage);
      return pakcage;
    }
  }

  static Package getSystemPackage(String name) {
    synchronized (systemPackages) {
      return (Package)systemPackages.get(name);
    }
  }

  static Package[] getSystemPackages() {
    synchronized (systemPackages) {
      return (Package[])systemPackages.values().toArray(new Package[systemPackages.size()]);
    }
  }

  static void loadSystemLibrary(String libraryName, boolean absolute) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkLink(libraryName);
    if (absolute) {
      if (getLibrary(null, libraryName))
        return;
    } else {
      String libraryPath = System.getProperty("java.vm.library.path");
      if (libraryPath != null) {
        StringTokenizer st = new StringTokenizer(libraryPath, File.pathSeparator);
        while (st.hasMoreTokens()) {
          String directory = st.nextToken();
          String actualName = directory+File.separator+System.mapLibraryName(libraryName);
          if (getLibrary(null, actualName))
            return;
        }
      }
    }
    throw new UnsatisfiedLinkError("Could not load library \""+libraryName+"\"");
  }

  private final ClassLoader parent;
  private final HashMap classes = new HashMap();
  private final HashMap constraints = new HashMap();
  private final HashMap packages = new HashMap();
  private final ArrayList libraries = new ArrayList();
  private final ProtectionDomain defaultDomain;

  protected ClassLoader() {
    this(getSystemClassLoader());
  }

  protected ClassLoader(ClassLoader parent) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkCreateClassLoader();
    this.parent = parent;
    ProtectionDomain domain = null;
    Policy policy = Policy.getPolicy();
    if (policy != null) {
      CodeSource codeSource = new CodeSource(null, null);
      domain = new ProtectionDomain(codeSource, policy.getPermissions(codeSource));
    }
    this.defaultDomain = domain;
  }

  public final ClassLoader getParent() {
    if (parent != null) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        ClassLoader caller = Thread.callerClass().getDefiningLoader();
        if (caller != null && !caller.parentOf(parent))
          sm.checkPermission(new RuntimePermission("getClassLoader"));
      }
    }
    return parent;
  }

  final boolean parentOf(ClassLoader loader) {
    while (loader != null) {
      if (loader == this)
        return true;
      loader = loader.parent;
    }
    return false;
  }

  public Class loadClass(String name) throws ClassNotFoundException {
    return loadClass(name, false);
  }
  
  protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
    Class clazz = findLoadedClass(name);
    if (clazz == null)
      try {
        if (parent != null)
          return parent.loadClass(name, resolve);
        clazz = findBootstrapClass(name);
      } catch (ClassNotFoundException e) {
        clazz = findClass(name);
      }
    if (resolve)
      resolveClass(clazz);
    return clazz;
  }

  protected final void resolveClass(Class clazz) {
    clazz.link();
  }

  /** @deprecated */
  protected final Class defineClass(byte[] array, int start, int length) {
    return defineClass(null, array, start, length);
  }

  protected final Class defineClass(String name, byte[] array, int start, int length) {
    return defineClass(name, array, start, length, null);
  }

  protected final Class defineClass(String name, byte[] array, int start, int length, ProtectionDomain domain) {
    if (name != null)
      if (name.startsWith("["))
        throw new NoClassDefFoundError("Wrong name: "+name);
    if (domain == null)
      domain = defaultDomain;
    Object[] signers = null;
    if (domain != null) {
      CodeSource codeSource = domain.getCodeSource();
      if (codeSource != null)
        signers = codeSource.getCertificates();
    }
    if (name != null) {
      // check signers, signers from package must equals signers from class
      // if pass share global copy
    }
    Class clazz = defineClass(this, name, array, start, length);
    clazz.setSigners(signers);
    clazz.setProtectionDomain(domain);
    return clazz;
  }

  protected final void setSigners(Class clazz, Object[] signers) {
    clazz.setSigners(signers);
  }
  
  public final Enumeration getResources(String name) throws IOException {
    Enumeration enumeration = parent != null ? parent.getResources(name) : getBootstrapResources(name);
    Vector vector = new Vector();
    while (enumeration.hasMoreElements())
      vector.addElement(enumeration.nextElement());
    enumeration = findResources(name);
    while (enumeration.hasMoreElements())
      vector.addElement(enumeration.nextElement());
    return vector.elements();
  }
  
  public URL getResource(String name) {
    URL resource = parent != null ? parent.getResource(name) : getBootstrapResource(name);
    if (resource == null)
      resource = findResource(name);
    return resource;
  }

  public InputStream getResourceAsStream(String name) {
    URL resource = getResource(name);
    if (resource != null)
      try {
        return resource.openStream();
      } catch (IOException e) { }
    return null;
  }
  
  protected final Class findLoadedClass(String name) {
    return getClass(this, name);
  }

  protected final Class findBootstrapClass(String name) throws ClassNotFoundException {
    return findClass(null, name);
  }

  protected final Class findSystemClass(String name) throws ClassNotFoundException {
    ClassLoader loader = getSystemClassLoader();
    if (loader != null) 
      return loader.loadClass(name);
    return findBootstrapClass(name);
  }

  protected Class findClass(String name) throws ClassNotFoundException {
    throw new ClassNotFoundException(name);
  }
  
  protected Enumeration findResources(String name) throws IOException {
    return new Vector(0).elements();
  }

  protected URL findResource(String name) {
    return null;
  }

  protected String findLibrary(String name){
    return null;
  }

  protected Package definePackage(String name, String specTitle, String specVersion, String specVendor, 
                                               String implTitle, String implVersion, String implVendor, URL url) {
    synchronized (packages) {
      Package pakcage = getPackage(name);
      if (pakcage != null)
        throw new IllegalArgumentException(name);
      pakcage = new Package(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, url);
      packages.put(name, pakcage);
      return pakcage;
    }
  }

  protected Package getPackage(String name) {
    synchronized (packages) {
      Package pakcage = (Package)packages.get(name);
      if (pakcage == null) {
        pakcage = parent != null ? parent.getPackage(name) : getSystemPackage(name);
        if (pakcage != null)
          packages.put(name, pakcage);
      }
      return pakcage;
    }
  }

  protected Package[] getPackages() {
    synchronized (packages) {
      HashMap map = (HashMap)packages.clone();
      Package[] pkgs = parent != null ? parent.getPackages() : getSystemPackages();
      if (pkgs != null)
        for (int i = 0; i < pkgs.length; i++)
          map.put(pkgs[i].getName(), pkgs[i]);
      return (Package[])map.values().toArray(new Package[map.size()]);
    }
  }

  final void loadLibrary(String libraryName, boolean absolute) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkLink(libraryName);
    if (absolute) {
      if (getLibrary(this, libraryName))
        return;
    } else {
      String actualName = findLibrary(libraryName);
      if (actualName != null) {
        if (getLibrary(this, actualName))
          return;
      } else {
        String libraryPath = System.getProperty("java.library.path");
        if (libraryPath != null) {
          StringTokenizer st = new StringTokenizer(libraryPath, File.pathSeparator);
          while (st.hasMoreTokens()) {
            String directory = st.nextToken();
            actualName = directory+File.separator+System.mapLibraryName(libraryName);
            if (getLibrary(this, actualName))
              return;
          }
        }
      }
    }
    throw new UnsatisfiedLinkError("Could not load library \""+libraryName+"\"");
  }

  private static boolean getLibrary(ClassLoader loader, String name) {
    ArrayList libraries = loader == null ? systemLibraries : loader.libraries;
    synchronized (libraries) {
      for (Iterator i = libraries.iterator(); i.hasNext(); ) {
        Library library = (Library)i.next();
        if (name.equals(library.getName()))
          return true;
      }
      Library library = Library.forName(name);
      if (library != null) {
        libraries.add(library);
        return true;
      }
    }
    return false;
  }

  private static long findNativeMethod(ClassLoader loader, String className, String name, String descriptor) {
    className = className.replace('.', '/');
    StringBuffer sb = new StringBuffer();
    sb.append("Java_");
    for (int i = 0; i < className.length(); i++) {
      char shar = className.charAt(i);
      if (shar == '/')
        sb.append('_');
      else if ('0' <= shar && shar <= '9' || 'A' <= shar && shar <= 'Z' || 'a' <= shar && shar <= 'z')
        sb.append(shar);
      else {
        sb.append('_');
        if (shar == '_')
          sb.append('1');
        else if (shar == ';')
          sb.append('2');
        else if (shar == '[')
          sb.append('3');
        else {
          sb.append('0');
          sb.append(Character.forDigit(shar >> 12 & 0xF, 16));
          sb.append(Character.forDigit(shar >>  8 & 0xF, 16));
          sb.append(Character.forDigit(shar >>  4 & 0xF, 16));
          sb.append(Character.forDigit(shar >>  0 & 0xF, 16));
        }
      }
    }
    sb.append('_');
    for (int i = 0; i < name.length(); i++) {
      char shar = name.charAt(i);
      if (shar == '/')
        sb.append('_');
      else if ('0' <= shar && shar <= '9' || 'A' <= shar && shar <= 'Z' || 'a' <= shar && shar <= 'z')
        sb.append(shar);
      else {
        sb.append('_');
        if (shar == '_')
          sb.append('1');
        else if (shar == ';')
          sb.append('2');
        else if (shar == '[')
          sb.append('3');
        else {
          sb.append('0');
          sb.append(Character.forDigit(shar >> 12 & 0xF, 16));
          sb.append(Character.forDigit(shar >>  8 & 0xF, 16));
          sb.append(Character.forDigit(shar >>  4 & 0xF, 16));
          sb.append(Character.forDigit(shar >>  0 & 0xF, 16));
        }
      }
    }
    int jwords = 0;
    for (int i = 1; descriptor.charAt(i) != ')'; i++)
      switch (descriptor.charAt(i)) {
      case '[':
        i++;
        while (descriptor.charAt(i) == '[')
          i++;
        if (descriptor.charAt(i) == 'L')
          i = descriptor.indexOf(';', i+1);
        jwords++;
        break;
      case 'L':
        i = descriptor.indexOf(';', i+1);
        jwords++;
        break;
      case 'J':
      case 'D':
        jwords += 2;
        break;
      default:
        jwords++;
      }
    long handle = findSymbol(loader, sb.toString(), jwords);
    if (handle == 0) {
      sb.append('_');
      sb.append('_');
      for (int i = 1; ; i++) {
        char shar = descriptor.charAt(i);
        if (shar == ')')
          break;
        else if (shar == '/')
          sb.append('_');
        else if ('0' <= shar && shar <= '9' || 'A' <= shar && shar <= 'Z' || 'a' <= shar && shar <= 'z')
          sb.append(shar);
        else {
          sb.append('_');
          if (shar == '_')
            sb.append('1');
          else if (shar == ';')
            sb.append('2');
          else if (shar == '[')
            sb.append('3');
          else {
            sb.append('0');
            sb.append(Character.forDigit(shar >> 12 & 0xF, 16));
            sb.append(Character.forDigit(shar >>  8 & 0xF, 16));
            sb.append(Character.forDigit(shar >>  4 & 0xF, 16));
            sb.append(Character.forDigit(shar >>  0 & 0xF, 16));
          }
        }
      }
      handle = findSymbol(loader, sb.toString(), jwords);
    }
    return handle;
  }

  private static long findSymbol(ClassLoader loader, String symbol, int jwords) {
    ArrayList libraries = loader == null ? systemLibraries : loader.libraries;
    synchronized (libraries) {
      for (Iterator i = libraries.iterator(); i.hasNext(); ) {
        Library library = (Library)i.next();
        long handle = library.findSymbol(symbol, jwords);
        if (handle != 0)
          return handle;
      }
    }
    return 0;
  }

  private static final class Library {

    private static final HashMap libraries = new HashMap();

    private static long load(String name) {
      return lang.load(name);
    }

    private static long findSymbol(long handle, String symbol, int jwords) {
      return lang.findSymbol(handle, symbol, jwords);
    }

    private static void unload(long handle) {
      lang.unload(handle);
    }

    public static synchronized Library forName(String name) {
      Library library = null;
      WeakReference weak = (WeakReference)libraries.get(name);
      if (weak != null) {
        library = (Library)weak.get();
        if (library == null)
          return null;
      }
      if (library == null) {
        long handle = load(name);
        if (handle == 0)
          return null;
        library = new Library(name, handle);
        libraries.put(name, new WeakReference(library));
      }
      return library;
    }

    private static synchronized void remove(String name) {
      libraries.remove(name);
    }

    private final String name;
    private final long handle;

    private Library(String name, long handle) {
      this.name = name;
      this.handle = handle;
    }

    public String getName() {
      return name;
    }

    public long findSymbol(String symbol, int jwords) {
      return findSymbol(handle, symbol, jwords);
    }

    protected void finalize() {
      unload(handle);
      remove(name);
    }

  }

}

