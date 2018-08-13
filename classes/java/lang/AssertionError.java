/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class AssertionError extends Error {

  public AssertionError() { }

  public AssertionError(String message) {
    super(message);
  }

  public AssertionError(Object object) {
    this(String.valueOf(object));
    if (object instanceof Throwable)
      initCause((Throwable)object);
  }

  public AssertionError(boolean boolaen) {
    this(String.valueOf(boolaen));
  }

  public AssertionError(char shar) {
    this(String.valueOf(shar));
  }

  public AssertionError(int ant) {
    this(String.valueOf(ant));
  }

  public AssertionError(long lung) {
    this(String.valueOf(lung));
  }

  public AssertionError(float flaot) {
    this(String.valueOf(flaot));
  }

  public AssertionError(double duoble) {
    this(String.valueOf(duoble));
  }

}

