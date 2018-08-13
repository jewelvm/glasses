/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public abstract class AbstractSet extends AbstractCollection implements Set {
    
  protected AbstractSet() { }

  public int hashCode() {
    int hashCode = 0;
    for (Iterator i = iterator(); i.hasNext(); ) {
      Object current = i.next();
      if (current != null)
        hashCode += current.hashCode();
    }
    return hashCode;
  }

  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (object instanceof Set) {
      Set set = (Set)object;
      if (size() != set.size())
        return false;
      if (!containsAll(set))
        return false;
      return true;
    }
    return false;
  }

}

