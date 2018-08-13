/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

final class net {

  static {
    AccessController.doPrivileged(
      new PrivilegedAction() {
        public Object run() {
          System.loadLibrary("net");
          return null;
        }
      }
    );
  }

  static native String getLocalHostName();
  static native String getHostNameByAddress(int address);
  static native int[] getHostAddressesByName(String name);

  static native int create(boolean stream) throws IOException;
  static native int connect(int id, int address, int port) throws IOException;
  static native int bind(int id, int address, int port) throws IOException;
  static native void listen(int id, int backlog) throws IOException;
  static native int accept(int id, int timeout, int[] address, int[] port) throws IOException;
  static native int recv(int id, byte[] array, int start, int length, int timeout) throws IOException;
  static native void send(int id, byte[] array, int start, int length) throws IOException;
  static native int available(int id) throws IOException;
  static native void shutdownInput(int id) throws IOException;
  static native void shutdownOutput(int id) throws IOException;
  static native void closesocket(int id) throws IOException;

  private net() { }

}

