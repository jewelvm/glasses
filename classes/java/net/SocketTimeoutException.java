/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.InterruptedIOException;

public class SocketTimeoutException extends InterruptedIOException {

  public SocketTimeoutException() { }

  public SocketTimeoutException(String message) {
    super(message);
  }

}

