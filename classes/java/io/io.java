/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

import java.security.AccessController;
import java.security.PrivilegedAction;

final class io {

  static {
    AccessController.doPrivileged(
      new PrivilegedAction() {
        public Object run() {
          System.loadLibrary("io");
          return null;
        }
      }
    );
  }

  static native int plaf();
  static native String[] roots();
  static native String[] list(String path);
  static native String canonicalize(String path) throws IOException;
  static native boolean exists(String path);
  static native boolean isDirectory(String path);
  static native boolean renameTo(String path, String dest);
  static native boolean setReadOnly(String path);
  static native boolean delete(String path);
  static native boolean mkdir(String path);
  static native boolean canRead(String path);
  static native boolean canWrite(String path);
  static native boolean isFile(String path);
  static native boolean isHidden(String path);
  static native long length(String path);
  static native long lastModified(String path);
  static native boolean setLastModified(String path, long value);
  static native boolean createNewFile(String path);

  static native int create(String name, boolean append) throws FileNotFoundException;
  static native int open(String name, boolean write) throws FileNotFoundException;
  static native int read(int id, byte[] array, int start, int length) throws IOException;
  static native void write(int id, byte[] array, int start, int length) throws IOException;
  static native void close(int id) throws IOException;
  static native void seek(int id, long offset) throws IOException;
  static native long tell(int id) throws IOException;
  static native long length(int id) throws IOException;
  static native void adjust(int id, long offset) throws IOException;
  static native void sync(int id) throws SyncFailedException;

  private io() { }

}

