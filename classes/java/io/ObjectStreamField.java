/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class ObjectStreamField implements Comparable {

  private final String name;
  private final Class type;
  private final boolean unshared;
  private int offset;

  public ObjectStreamField(String name, Class type) {
    this(name, type, false);
  }

  public ObjectStreamField(String name, Class type, boolean unshared) {
    if (name == null)
      throw new NullPointerException();
    if (type == null)
      throw new NullPointerException();
    if (type == Void.TYPE)
      throw new IllegalArgumentException("Void is not a legal type");
    this.name = name;
    this.type = type;
    this.unshared = unshared;
  }

  public String getName() {
    return name;
  }

  public Class getType() {
    return type;
  }

  public boolean isUnshared() {
    return unshared;
  }

  public boolean isPrimitive() {
    return type.isPrimitive();
  }

  public char getTypeCode() {
    if (type == Boolean.TYPE)
      return 'Z';
    if (type == Character.TYPE)
      return 'C';
    if (type == Byte.TYPE)
      return 'B';
    if (type == Short.TYPE)
      return 'S';
    if (type == Integer.TYPE)
      return 'I';
    if (type == Long.TYPE)
      return 'J';
    if (type == Float.TYPE)
      return 'F';
    if (type == Double.TYPE)
      return 'D';
    if (type.isArray())
      return '[';
    return 'L';
  }

  public String getTypeString() {
    if (type.isPrimitive())
      return null;
    String name = type.getName();
    name = name.replace('.', '/');
    if (!type.isArray())
      name = "L"+name+";";
    return name;
  }

  protected void setOffset(int offset) {
    this.offset = offset;
  }

  public int getOffset() {
    return offset;
  }

  public int compareTo(ObjectStreamField osfield) {
    if (isPrimitive()) {
      if (!osfield.isPrimitive())
        return -1;
    } else {
      if (osfield.isPrimitive())
        return 1;
    }
    return name.compareTo(osfield.name);
  }

  public int compareTo(Object object) {
    return compareTo((ObjectStreamField)object);
  }

  public String toString() {
    String type = getTypeString();
    if (type == null)
      type = String.valueOf(getTypeCode());
    return type+" "+name;
  }

}

