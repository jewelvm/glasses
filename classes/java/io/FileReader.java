/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class FileReader extends InputStreamReader {

  public FileReader(String name) throws FileNotFoundException {
    super(new FileInputStream(name));
  }

  public FileReader(File file) throws FileNotFoundException {
    super(new FileInputStream(file));
  }

  public FileReader(FileDescriptor descriptor) {
    super(new FileInputStream(descriptor));
  }

}

