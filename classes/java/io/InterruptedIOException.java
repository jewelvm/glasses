/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class InterruptedIOException extends IOException {

  public int bytesTransferred;

  public InterruptedIOException() { }
  
  public InterruptedIOException(String message) {
    super(message);
  }

}

