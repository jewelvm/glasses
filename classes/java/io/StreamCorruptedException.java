/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class StreamCorruptedException extends ObjectStreamException {

  public StreamCorruptedException() { }

  public StreamCorruptedException(String message) {
    super(message);
  }

}

