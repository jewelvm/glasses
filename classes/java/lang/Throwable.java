/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class Throwable implements Serializable {

  private static final long serialVersionUID = -3042686055658047285L;
  
  private final String message;
  private Throwable cause;
  private StackTraceElement[] elements;

  public Throwable() {
    this(null, null);
  }
  
  public Throwable(String message) {
    this(message, null);
  }

  public Throwable(Throwable cause) {
    this(null, cause);
  }

  public Throwable(String message, Throwable cause) {
    this.message = message;
    this.cause = cause;
    fillInStackTrace();
  }

  public String getMessage() {
    return message;
  }

  public String getLocalizedMessage() {
    return getMessage();
  }

  public Throwable initCause(Throwable cause) {
    if (this.cause != null)
      throw new IllegalStateException("Cause already initialized");
    for (Throwable t = cause; t != null; t = t.cause)
      if (t == this)
        throw new IllegalArgumentException("Cause circularity");
    this.cause = cause;
    return this;
  }

  public Throwable getCause() {
    return cause;
  }

  public Throwable fillInStackTrace() {
    elements = lang.getStackTrace();
    return this;
  }

  public void setStackTrace(StackTraceElement[] elements) {
    this.elements = (StackTraceElement[])elements.clone();
  }

  public StackTraceElement[] getStackTrace() {
    return elements == null ? null : (StackTraceElement[])elements.clone();
  }

  public void printStackTrace() {
    printStackTrace(System.err);
  }

  public void printStackTrace(PrintStream out) {
    out.println(this);
    if (elements != null)
      for (int i = 0; i < elements.length; i++) {
        out.print("\tat ");
        out.println(elements[i]);
      }
    if (cause != null) {
      out.print("Caused by: ");
      cause.printStackTrace(out);
    }
  }

  public void printStackTrace(PrintWriter out) {
    out.println(this);
    if (elements != null)
      for (int i = 0; i < elements.length; i++) {
        out.print("\tat ");
        out.println(elements[i]);
      }
    if (cause != null) {
      out.print("Caused by: ");
      cause.printStackTrace(out);
    }
  }

  public String toString() {
    String className = getClass().getName();
    String message = getLocalizedMessage();
    return message == null ? className : className+": "+message;
  }

}

