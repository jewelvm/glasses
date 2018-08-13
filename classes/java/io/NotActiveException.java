/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class NotActiveException extends ObjectStreamException {

  public NotActiveException() { }

  public NotActiveException(String message) {
    super(message);
  }

}

