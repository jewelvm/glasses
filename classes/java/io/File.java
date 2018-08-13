/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class File implements Comparable, Serializable {

  private static final long serialVersionUID = 301077366599181567L;

  public static final String separator = System.getProperty("file.separator");
  public static final char separatorChar = separator.charAt(0);

  public static final String pathSeparator = System.getProperty("path.separator");
  public static final char pathSeparatorChar = pathSeparator.charAt(0);

  private static final int PLAF = io.plaf();
  private static final int UNIX = 1;
  private static final int WIN32 = 2;

  private static String unixNormalize(String path) {
    int length = path.length();
    StringBuffer sb = new StringBuffer(length);
    char last = '\0';
    for (int i = 0; i < length; i++) {
      char curr = path.charAt(i);
      if (last == '/' && curr == '/')
        continue;
      sb.append(curr);
      last = curr;
    }
    if (last == '/') {
      length = sb.length();
      if (length > 1)
        sb.setLength(length-1);
    }
    return sb.toString();
  }

  private static String unixResolve(String parent, String child) {
    if (child.equals(""))
      return parent;
    if (child.charAt(0) == '/') {
      if (parent.equals("/"))
        return child;
      return parent+child;
    }
    if (parent.equals("/"))
      return parent+child;
    return parent+"/"+child;
  }
      
  private static byte unixPrefix(String path) {
    int length = path.length();
    if (length == 0)
      return 0;
    if (path.charAt(0) == '/')
      return 1;
    return 0;
  }

  private static boolean unixIsAbsolute(String path) {
    return path.startsWith("/");
  }

  private static String unixGetAbsolutePath(String path) {
    if (path.startsWith("/"))
      return path;
    return resolve(normalize(System.getProperty("user.dir")), path);
  }
      
  private static String win32Normalize(String path) {
    if (path.startsWith("/"))
      path = path.substring(1);
    path = path.replace('/', '\\');
    int length = path.length();
    StringBuffer sb = new StringBuffer(length);
    char last = '\0';
    for (int i = 0; i < length; i++) {
      char curr = path.charAt(i);
      if (last == '\\' && curr == '\\' && i != 1)
        continue;
      sb.append(curr);
      last = curr;
    }
    if (last == '\\') {
      length = sb.length();
      if (length == 1)
        ;
      else if (length == 2 && sb.charAt(0) == '\\')
        ;
      else if (length == 3 && sb.charAt(1) == ':')
        ;
      else
        sb.setLength(length-1);
    }
    return sb.toString();
  }

  private static String win32Resolve(String parent, String child) {
    int pn = parent.length();
    if (pn == 0)
      return child;
    int cn = child.length();
    if (cn == 0)
      return parent;
    if (child.startsWith("\\\\"))
      child = child.substring(2);
    else if (child.startsWith("\\"))
      child = child.substring(1);
    if (parent.charAt(pn-1) == '\\')
      parent = parent.substring(0, pn-1);
    return parent + (child.length() > 0 && child.charAt(0) != '\\' ? "\\" : "") + child;
  }

  private static byte win32Prefix(String path) {
    if (path.length() > 1) {
      if (path.substring(1).startsWith(":\\"))
        return 3;
      if (path.substring(1).startsWith(":"))
        return 2;
    }
    if (path.startsWith("\\\\"))
      return 2;
    if (path.startsWith("\\"))
      return 1;
    return 0;
  }

  private static boolean win32IsAbsolute(String path) {
    return path.startsWith("\\\\") 
        || (path.length() > 0 && path.substring(1).startsWith(":\\"));
  }

  private static String win32GetAbsolutePath(String path) {
    if (path.startsWith("\\\\"))
      return path;
    if (path.length() > 0 && path.substring(1).startsWith(":\\"))
      return path;
    if (path.length() > 1 && path.charAt(1) == ':') {
      //implement
      throw new InternalError("Unimplemented");
    }
    if (path.startsWith("\\")) {
      String user_dir = normalize(System.getProperty("user.dir"));
      if (user_dir.length() > 1 && user_dir.charAt(1) == ':')
        user_dir = user_dir.substring(0, 2);
      return resolve(user_dir, path);
    }
    return resolve(normalize(System.getProperty("user.dir")), path);
  }

  private static String getDefaultParent() {
    switch (PLAF) {
    case UNIX:
      return "/";
    case WIN32:
      return "\\";
    default:
      throw new InternalError("Unimplemented");
    }
  }
  private static String normalize(String path) {
    switch (PLAF) {
    case UNIX:
      return unixNormalize(path);
    case WIN32:
      return win32Normalize(path);
    default:
      throw new InternalError("Unimplemented");
    }
  }
  private static String resolve(String parent, String child) {
    switch (PLAF) {
    case UNIX:
      return unixResolve(parent, child);
    case WIN32:
      return win32Resolve(parent, child);
    default:
      throw new InternalError("Unimplemented");
    }
  }
  private static byte prefix(String path) {
    switch (PLAF) {
    case UNIX:
      return unixPrefix(path);
    case WIN32:
      return win32Prefix(path);
    default:
      throw new InternalError("Unimplemented");
    }
  }
  private static boolean isAbsolute(String path) {
    switch (PLAF) {
    case UNIX:
      return unixIsAbsolute(path);
    case WIN32:
      return win32IsAbsolute(path);
    default:
      throw new InternalError("Unimplemented");
    }
  }
  private static String getAbsolutePath(String path) {
    switch (PLAF) {
    case UNIX:
      return unixGetAbsolutePath(path);
    case WIN32:
      return win32GetAbsolutePath(path);
    default:
      throw new InternalError("Unimplemented");
    }
  }

  public static File createTempFile(String prefix, String suffix) throws IOException {
    return createTempFile(prefix, suffix, null);
  }

  public static File createTempFile(String prefix, String suffix, File directory) throws IOException {
    if (prefix.length() < 3)
      throw new IllegalArgumentException("Invalid prefix");
    if (suffix == null)
      suffix = ".tmp";
    if (directory == null)
      directory = new File(System.getProperty("java.io.tmpdir"));
    Random random = new Random();
    for (;;) {
      int index = random.nextInt();
      if (index < 0)
        index = -index;
      File file = new File(directory, prefix+index+suffix);
      if (file.createNewFile())
        return file;
    }
  }

  public static File[] listRoots() {
    String[] roots = io.roots();
    File[] files = new File[roots.length];
    for (int i = 0; i < files.length; i++)
      files[i] = new File(roots[i]);
    return files;
  }

  private String path;
  private byte prefix;

  public File(String path) {
    this((String)null, path);
  }

  public File(String parent, String child) {
    if (parent == null)
      path = normalize(child);
    else {
      if (parent.equals(""))
        parent = getDefaultParent();
      path = resolve(normalize(parent), normalize(child));
    }
    prefix = prefix(path);
  }

  public File(File parent, String child) {
    this(parent == null ? null : parent.getPath(), child);
  }

  public String getPath() {
    return path;
  }

  public String getName() {
    int index = path.lastIndexOf(separatorChar);
    if (index < prefix)
      return path.substring(prefix);
    return path.substring(index+1);
  }

  public String getParent() {
    int index = path.lastIndexOf(separatorChar);
    if (index < prefix) {
      if (0 < prefix && prefix < path.length())
        return path.substring(0, prefix);
      return null;
    }
    return path.substring(0, index);
  }

  public File getParentFile() {
    String parent = getParent();
    if (parent == null)
      return null;
    return new File(parent);
  }

  public boolean isAbsolute() {
    return isAbsolute(path);
  }

  public String getAbsolutePath() {
    return getAbsolutePath(path);
  }

  public File getAbsoluteFile() {
    return new File(getAbsolutePath());
  }

  public String getCanonicalPath() throws IOException {
    return io.canonicalize(getAbsolutePath());
  }

  public File getCanonicalFile() throws IOException {
    return new File(getCanonicalPath());
  }

  public boolean exists() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    return io.exists(path);
  }

  public boolean isDirectory() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    return io.isDirectory(path);
  }

  public boolean renameTo(File file) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      sm.checkWrite(path);
      sm.checkWrite(file.path);
    }
    return io.renameTo(path, file.path);
  }

  public boolean setReadOnly() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkWrite(path);
    return io.setReadOnly(path);
  }

  public boolean delete() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkDelete(path);
    return io.delete(path);
  }

  public boolean mkdir() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkWrite(path);
    return io.mkdir(path);
  }

  public boolean mkdirs() {
    if (exists())
      return false;
    if (mkdir())
      return true;
    File parent = getParentFile();
    if (parent == null)
      return false;
    if (!parent.mkdirs())
      return false;
    return mkdir();
  }

  public boolean canRead() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    return io.canRead(path);
  }

  public boolean canWrite() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkWrite(path);
    return io.canWrite(path);
  }

  public boolean isFile() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    return io.isFile(path);
  }

  public boolean isHidden() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    return io.isHidden(path);
  }

  public long length() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    return io.length(path);
  }

  public long lastModified() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    return io.lastModified(path);
  }

  public boolean setLastModified(long value) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkWrite(path);
    return io.setLastModified(path, value);
  }

  public boolean createNewFile() throws IOException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkWrite(path);
    return io.createNewFile(path);
  }

  public void deleteOnExit() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkDelete(path);
    DeleteOnExit.add(this);
  }

  public String[] list() {
    return list((FilenameFilter)null);
  }

  public String[] list(FilenameFilter filter) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(path);
    String[] list = io.list(path);
    if (list == null)
      return null;
    if (filter == null)
      return list;
    int length = 0;
    boolean[] accepted = new boolean[list.length];
    for (int i = 0 ; i < list.length ; i++)
      if (filter.accept(this, list[i])) {
        accepted[i] = true;
        length++;
      }
    String[] filtered = new String[length];
    int index = 0;
    for (int i = 0 ; i < list.length ; i++)
      if (accepted[i])
        filtered[index++] = list[i];
    return filtered;
  }

  public File[] listFiles() {
    return listFiles((FilenameFilter)null);
  }

  public File[] listFiles(FilenameFilter filter) {
    String[] list = list(filter);
    if (list == null)
      return null;
    File[] files = new File[list.length];
    for (int i = 0; i < files.length; i++)
      files[i] = new File(path, list[i]);
    return files;
  }

  public File[] listFiles(final FileFilter filter) {
    return listFiles(
      new FilenameFilter() {
        public boolean accept(File directory, String name) {
          return filter.accept(new File(directory, name));
        }
      }
    );
  }

  public int compareTo(File file) {
    switch (PLAF) {
    case UNIX:
      return path.compareTo(file.path);
    case WIN32:
      return path.compareToIgnoreCase(file.path);
    default:
      throw new InternalError("Unimplemented");
    }
  }

  public int compareTo(Object object) {
    return compareTo((File)object);
  }

  public int hashCode() {
    switch (PLAF) {
    case UNIX:
      return path.hashCode();
    case WIN32:
      return path.toLowerCase().hashCode();
    default:
      throw new InternalError("Unimplemented");
    }
  }

  public boolean equals(Object object) {
    switch (PLAF) {
    case UNIX:
      return object instanceof File
          && ((File)object).path.equals(path);
    case WIN32:
      return object instanceof File
          && ((File)object).path.equalsIgnoreCase(path);
    default:
      throw new InternalError("Unimplemented");
    }
  }

  public String toString() {
    return path;
  }

  public URL toURL() throws MalformedURLException {
    String path = getAbsolutePath();
    path = path.replace(File.separatorChar, '/');
    if (!path.startsWith("/"))
      path = "/"+path;
    if (isDirectory())
      if (!path.endsWith("/"))
        path = path+"/";
    return new URL("file", "", path);
  }

  private static final class DeleteOnExit extends Thread {

    private static final HashSet files = new HashSet();

    static {
      Runtime.getRuntime().addShutdownHook(new DeleteOnExit());
    }

    public static void add(File file) {
      synchronized (files) {
        files.add(file);
      }
    }

    private DeleteOnExit() { }

    public void run() {
      synchronized (files) {
        for (Iterator i = files.iterator(); i.hasNext(); ) {
          File file = (File)i.next();
          file.delete();
        }
      }
    }

  }

}

