/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.Serializable;

public class EventObject implements Serializable {

  protected transient Object source;

  public EventObject(Object source) {
    this.source = source;
  }

  public Object getSource() {
    return source;
  }

  public String toString() {
    return getClass().getName()+"[source="+source+"]";
  }

}

