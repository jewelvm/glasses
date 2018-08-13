/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.jar;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.zip.ZipEntry;

public class JarEntry extends ZipEntry {

  private Object creator;

  public JarEntry(String name) {
    super(name);
  }

  public JarEntry(ZipEntry entry) {
    super(entry);
  }

  public JarEntry(JarEntry entry) {
    super(entry);
    creator = entry.creator;
  }

  protected JarEntry(ZipEntry entry, JarFile jar) {
    super(entry);
    creator = jar;
  }

  protected JarEntry(String name, JarInputStream in) {
    super(name);
    creator = in;
  }

  public Attributes getAttributes() throws IOException {
    Manifest manifest = null;
    if (creator instanceof JarFile) {
      JarFile jar = (JarFile)creator;
      manifest = jar.getManifest();
    }
    if (creator instanceof JarInputStream) {
      JarInputStream in = (JarInputStream)creator;
      manifest = in.getManifest();
    }
    if (manifest != null)
      return manifest.getAttributes(getName());
    return null;
  }

  public Certificate[] getCertificates() {
    if (creator instanceof JarFile) {
      JarFile jar = (JarFile)creator;
      return jar.getCertificates(this);
    }
    return null;
  }

}

