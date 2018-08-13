/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public class InternalError extends VirtualMachineError {

  public InternalError() { }

  public InternalError(String message) {
    super(message);
  }

}

