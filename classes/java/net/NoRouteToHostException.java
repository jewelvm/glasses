/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

public class NoRouteToHostException extends SocketException {

  public NoRouteToHostException() { }
    
  public NoRouteToHostException(String message) {
    super(message);
  }

}

