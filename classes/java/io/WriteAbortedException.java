/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class WriteAbortedException extends ObjectStreamException {

  public Exception detail;

  public WriteAbortedException() { }

  public WriteAbortedException(String message) {
    super(message);
  }

  public WriteAbortedException(String message, Exception detail) {
    super(message);
    this.detail = detail;
  }

  public String getMessage() {
    String message = super.getMessage();
    if (detail != null)
      message += "; "+detail;
    return message;
  }

}

