/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public abstract class VirtualMachineError extends Error {

  protected VirtualMachineError() { }

  protected VirtualMachineError(String message) {
    super(message);
  }

}

