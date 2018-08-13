/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;

public class UnknownHostException extends IOException {

  public UnknownHostException() { }

  public UnknownHostException(String message) {
    super(message);
  }

}

