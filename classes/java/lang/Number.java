/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.Serializable;

public abstract class Number implements Serializable {

  private static final long serialVersionUID = -8742448824652078965L;

  protected Number() { }

  public byte byteValue() {
    return (byte)intValue();
  }

  public short shortValue() {
    return (short)intValue();
  }

  public abstract int intValue();
  public abstract long longValue();
  public abstract float floatValue();
  public abstract double doubleValue();

}

