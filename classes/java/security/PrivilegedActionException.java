/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.PrintStream;
import java.io.PrintWriter;

public class PrivilegedActionException extends Exception {

  private static final long serialVersionUID = 4724086851538908602L;

  private final Exception exception;

  public PrivilegedActionException(Exception exception) {
    this.exception = exception;
  }

  public Exception getException() {
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

