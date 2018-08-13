/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public final class Compiler {

  public static void enable() {
    lang.compilerEnable();
  }

  public static void disable() {
    lang.compilerDisable();
  }

  public static Object command(Object command) {
    return lang.compilerCommand(command);
  }

  public static boolean compileClass(Class clazz) {
    return lang.compileClass(clazz);
  }
  public static boolean compileClasses(String classes) {
    return lang.compileClasses(classes);
  }

  private Compiler() { }

}

