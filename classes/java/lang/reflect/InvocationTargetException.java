/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

import java.io.PrintStream;
import java.io.PrintWriter;

public class InvocationTargetException extends Exception {

  private static final long serialVersionUID = 4085088731926701167L;

  private final Throwable target;

  protected InvocationTargetException() {
    target = null;
  }

  public InvocationTargetException(Throwable target) {
    this.target = target;
  }

  public InvocationTargetException(Throwable target, String message) {
    super(message);
    this.target = target;
  }

  public Throwable getTargetException() {
    return target;
  }

  public void printStackTrace(PrintStream out) {
    if (target == null)
      super.printStackTrace(out);
    else {
      out.print(getClass().getName());
      out.print(": ");
      target.printStackTrace(out);
    }
  }

  public void printStackTrace(PrintWriter out) {
    if (target == null)
      super.printStackTrace(out);
    else {
      out.print(getClass().getName());
      out.print(": ");
      target.printStackTrace(out);
    }
  }

}

