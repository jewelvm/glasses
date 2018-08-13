/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ClassNotFoundException extends Exception {

  private static final long serialVersionUID = 9176873029745254542L;

  private final Throwable exception;

  public ClassNotFoundException() {
    exception = null;
  }

  public ClassNotFoundException(String message) {
    super(message);
    exception = null;
  }

  public ClassNotFoundException(String message, Throwable exception) {
    super(message);
    this.exception = exception;
  }

  public Throwable getException() {
    return exception;
  }

  public void printStackTrace(PrintStream out) {
    if (exception == null)
      super.printStackTrace(out);
    else {
      out.print(getClass().getName());
      out.print(": ");
      exception.printStackTrace(out);
    }
  }

  public void printStackTrace(PrintWriter out) {
    if (exception == null)
      super.printStackTrace(out);
    else {
      out.print(getClass().getName());
      out.print(": ");
      exception.printStackTrace(out);
    }
  }

}

