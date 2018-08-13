/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

import java.lang.ref.SoftReference;
import java.util.WeakHashMap;

public class ObjectStreamClass implements Serializable {

  private static final long serialVersionUID = -6120832682080437368L;

  public static final ObjectStreamField[] NO_FIELDS = new ObjectStreamField[]{ };

  private static final WeakHashMap map = new WeakHashMap();

  private static long computeSerialVersionUID(Class clazz) {
    throw new InternalError("Unimplemented");
  }

  public static ObjectStreamClass lookup(Class clazz) {
    if (!Serializable.class.isAssignableFrom(clazz) && !Externalizable.class.isAssignableFrom(clazz))
      return null;
    ObjectStreamClass osclass = null;
    synchronized (map) {
      SoftReference ref = (SoftReference)map.get(clazz);
      if (ref != null)
        osclass = (ObjectStreamClass)ref.get();
      if (osclass == null) {
        osclass = new ObjectStreamClass(clazz);
        map.put(clazz, new SoftReference(osclass));
      }
    }
    return osclass;
  }

  private final Class clazz;
  private final long uid;
  private final ObjectStreamField[] fields;

  private ObjectStreamClass(Class clazz) {
    this.clazz = clazz;
    this.uid = computeSerialVersionUID(clazz);
    this.fields = NO_FIELDS;//update for serializable
  }

  public Class forClass() {
    return clazz;
  }

  public String getName() {
    return clazz.getName();
  }

  public long getSerialVersionUID() {
    return uid;
  }

  public ObjectStreamField getField(String name) {
    for (int i = 0; i < fields.length; i++) {
      ObjectStreamField field = fields[i];
      if (name.equals(field.getName()))
	return field;
    }
    return null;
  }

  public ObjectStreamField[] getFields() {
    return (ObjectStreamField[])fields.clone();
  }

  public String toString() {
    return getName()+": static final long serialVersionUID = "+getSerialVersionUID()+"L;";
  }

}

