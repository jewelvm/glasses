/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

public abstract class Policy {

  static {
    String provider = Security.getProperty("policy.provider");
    if (provider != null)
      try {
        Class clazz = Class.forName(provider);
        Object object = clazz.newInstance();
        if (object instanceof Policy)
          setPolicy((Policy)object);
      } catch (ClassNotFoundException e) {
      } catch (IllegalAccessException e) {
      } catch (InstantiationException e) {
      }
  }

  private static Policy policy;

  public static void setPolicy(Policy policy) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("setPolicy");
    Policy.policy = policy;
  }

  public static Policy getPolicy() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("getPolicy");
    return policy;
  }

  protected Policy() { }

  public abstract PermissionCollection getPermissions(CodeSource codeSource);
  public abstract void refresh();

}

