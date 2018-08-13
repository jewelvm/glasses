/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class IndexOutOfBoundsException extends RuntimeException {

  public IndexOutOfBoundsException() { }

  public IndexOutOfBoundsException(String message) {
    super(message);
  }

  public IndexOutOfBoundsException(int index) {
    this(Integer.toString(index));
  }

}

