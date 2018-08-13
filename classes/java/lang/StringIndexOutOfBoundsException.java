/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class StringIndexOutOfBoundsException extends IndexOutOfBoundsException {

  public StringIndexOutOfBoundsException() { }

  public StringIndexOutOfBoundsException(String message) {
    super(message);
  }

  public StringIndexOutOfBoundsException(int index) {
    super(index);
  }

}

