/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class InvalidObjectException extends ObjectStreamException {

  public InvalidObjectException() { }

  public InvalidObjectException(String message) {
    super(message);
  }

}

