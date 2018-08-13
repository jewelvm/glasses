/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

public class AccessibleObject {

  public static void setAccessible(AccessibleObject[] array, boolean accessible) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) 
      sm.checkPermission(new ReflectPermission("suppressAccessChecks"));
    for (int i = 0; i < array.length; i++)
      array[i].accessible = accessible;
  }

  private boolean accessible;

  protected AccessibleObject() { }

  public boolean isAccessible() {
    return accessible;
  }

  public void setAccessible(boolean accessible) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) 
      sm.checkPermission(new ReflectPermission("suppressAccessChecks"));
    this.accessible = accessible;
  }

}

