/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

import java.io.IOException;

public class ZipException extends IOException {
    
  public ZipException() { }

  public ZipException(String message) {
    super(message);
  }

}

