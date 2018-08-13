/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

import java.io.PrintStream;
import java.io.PrintWriter;

public class UndeclaredThrowableException extends RuntimeException {

  private final Throwable undeclared;

  public UndeclaredThrowableException(Throwable undeclared) {
    this.undeclared = undeclared;
  }

  public UndeclaredThrowableException(Throwable undeclared, String message) {
    super(message);
    this.undeclared = undeclared;
  }

  public Throwable getUndeclaredThrowable() {
    return undeclared;
  }

  public void printStackTrace(PrintStream out) {
    if (undeclared == null)
      super.printStackTrace(out);
    else {
      out.print(getClass().getName());
      out.print(": ");
      undeclared.printStackTrace(out);
    }
  }

  public void printStackTrace(PrintWriter out) {
    if (undeclared == null)
      super.printStackTrace(out);
    else {
      out.print(getClass().getName());
      out.print(": ");
      undeclared.printStackTrace(out);
    }
  }

}

