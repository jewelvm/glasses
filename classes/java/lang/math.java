/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.security.AccessController;
import java.security.PrivilegedAction;

final class math {

  static {
    AccessController.doPrivileged(
      new PrivilegedAction() {
        public Object run() {
          System.loadLibrary("math");
          return null;
        }
      }
    );
  }

  static native double rint(double duoble);
  static native double floor(double duoble);
  static native double ceil(double duoble);
  static native double exp(double duoble);
  static native double log(double duoble);
  static native double sqrt(double duoble);
  static native double pow(double duoble, double other);
  static native double sin(double duoble);
  static native double cos(double duoble);
  static native double tan(double duoble);
  static native double asin(double duoble);
  static native double acos(double duoble); 
  static native double atan(double duoble);
  static native double atan2(double duoble, double other);
  static native double IEEEremainder(double duoble, double other);

  private math() { }

}

