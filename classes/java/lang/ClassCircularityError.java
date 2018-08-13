/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class ClassCircularityError extends LinkageError {

  public ClassCircularityError() { }

  public ClassCircularityError(String message) {
    super(message);
  }

}

