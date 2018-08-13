/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public class Stack extends Vector {
    
  private static final long serialVersionUID = 1224463164541339165L;

  public Stack() { }

  public boolean empty() {
    return size() == 0;
  }

  public Object push(Object object) {
    addElement(object);
    return object;
  }

  public synchronized Object peek() {
    int	size = size();
    if (size == 0)
      throw new EmptyStackException();
    return lastElement();
  }

  public synchronized Object pop() {
    int	size = size();
    if (size == 0)
      throw new EmptyStackException();
    Object object = lastElement();
    removeElementAt(size-1);
    return object;
  }

  public synchronized int search(Object object) {
    int index = lastIndexOf(object);
    if (index != -1)
      index = size()-index;
    return index;
  }

}

