/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public class ConcurrentModificationException extends RuntimeException {
  
  public ConcurrentModificationException() { }

  public ConcurrentModificationException(String message) {
    super(message);
  }

}

