/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;

public class UnknownServiceException extends IOException {

  public UnknownServiceException() { }

  public UnknownServiceException(String message) {
    super(message);
  }

}

