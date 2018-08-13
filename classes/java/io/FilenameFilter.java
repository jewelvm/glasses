/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public interface FilenameFilter {

  public boolean accept(File directory, String name);

}

