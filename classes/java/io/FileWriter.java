/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class FileWriter extends OutputStreamWriter {

  public FileWriter(String name) throws FileNotFoundException {
    super(new FileOutputStream(name));
  }

  public FileWriter(String name, boolean append) throws FileNotFoundException {
    super(new FileOutputStream(name, append));
  }

  public FileWriter(File file) throws FileNotFoundException {
    super(new FileOutputStream(file));
  }

  public FileWriter(File file, boolean append) throws FileNotFoundException {
    super(new FileOutputStream(file, append));
  }

  public FileWriter(FileDescriptor descriptor) {
    super(new FileOutputStream(descriptor));
  }

}

