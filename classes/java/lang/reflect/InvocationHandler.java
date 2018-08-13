/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang.reflect;

public interface InvocationHandler {

  public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable;

}

