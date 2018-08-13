/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class InvalidClassException extends ObjectStreamException {

  public String classname;

  public InvalidClassException() { }

  public InvalidClassException(String message) {
    super(message);
  }

  public InvalidClassException(String classname, String message) {
    super(message);
    this.classname = classname;
  }

  public String getMessage() {
    String message = super.getMessage();
    if (classname != null)
      message += " (class "+classname+")";
    return message;
  }

}

