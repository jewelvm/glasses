/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class OptionalDataException extends ObjectStreamException {

  public int length;
  public boolean eof;

  OptionalDataException(int length, boolean eof) {
    this.length = length;
    this.eof = eof;
  }

}

