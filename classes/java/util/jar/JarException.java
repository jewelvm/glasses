/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.jar;

import java.util.zip.ZipException;

public class JarException extends ZipException {

  public JarException() { }

  public JarException(String message) {
    super(message);
  }

}

