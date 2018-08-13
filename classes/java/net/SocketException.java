/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;

public class SocketException extends IOException {

  public SocketException() { }
    
  public SocketException(String message) {
    super(message);
  }

}

