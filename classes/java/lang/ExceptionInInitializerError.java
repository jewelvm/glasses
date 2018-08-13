/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ExceptionInInitializerError extends LinkageError {

  private static final long serialVersionUID = 1521711792217232256L;

  private final Throwable exception;

  public ExceptionInInitializerError() {
    exception = null;
  }

  public ExceptionInInitializerError(String message) {
    super(message);
    exception = null;
  }

  public ExceptionInInitializerError(Throwable exception) {
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

