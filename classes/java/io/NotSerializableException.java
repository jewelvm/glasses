/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class NotSerializableException extends ObjectStreamException {

  public NotSerializableException() { }
  
  public NotSerializableException(String message) {
    super(message);
  }
    
}

