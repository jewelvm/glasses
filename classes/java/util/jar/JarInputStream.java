/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.jar;

import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarInputStream extends ZipInputStream {

  private Manifest manifest;
  private final boolean verify;

  public JarInputStream(InputStream in) throws IOException {
    this(in, true);
  }

  public JarInputStream(InputStream in, boolean verify) throws IOException {
    super(in);
    this.verify = verify;
  }

  public Manifest getManifest() {
    return manifest;
  }

  protected ZipEntry createZipEntry(String name) {
    return new JarEntry(name, this);
  }

  public ZipEntry getNextEntry() throws IOException {
    JarEntry entry = (JarEntry)super.getNextEntry();
    if (entry != null)
      if (JarFile.MANIFEST_NAME.equalsIgnoreCase(entry.getName())) {
        manifest = new Manifest(this);
        entry = (JarEntry)super.getNextEntry();
      }
    if (entry != null)
      if (verify) {
        //implement
      }
    return entry;
  }

  public JarEntry getNextJarEntry() throws IOException {
    return (JarEntry)getNextEntry();
  }

  public int read(byte[] array, int start, int length) throws IOException {
    int read = super.read(array, start, length);
    if (verify) {
      //implement
    }
    return read;
  }

}

