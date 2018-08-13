/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.jar;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarFile extends ZipFile {

  private Manifest manifest;
  private final boolean verify;

  public static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";

  public JarFile(String name) throws IOException {
    this(name, true);
  }

  public JarFile(String name, boolean verify) throws IOException {
    this(new File(name), verify);
  }

  public JarFile(File file) throws IOException {
    this(file, true);
  }

  public JarFile(File file, boolean verify) throws IOException {
    this(file, verify, OPEN_READ);
  }

  public JarFile(File file, boolean verify, int mode) throws IOException {
    super(file, mode);
    this.verify = verify;
  }

  public Manifest getManifest() throws IOException {
    if (manifest == null) {
      ZipEntry entry = super.getEntry(MANIFEST_NAME);
      if (entry != null)
        manifest = new Manifest(super.getInputStream(entry));
    }
    return manifest;
  }

  public ZipEntry getEntry(String name) {
    return getJarEntry(name);
  }

  public JarEntry getJarEntry(String name) {
    ZipEntry entry = super.getEntry(name);
    if (entry != null)
      return new JarEntry(entry, this);
    return null;
  }

  public Enumeration entries() {
    return new Enumeration() {
      private final Enumeration e = JarFile.super.entries();
      public boolean hasMoreElements() { return e.hasMoreElements(); }
      public Object nextElement() {
        ZipEntry entry = (ZipEntry)e.nextElement();
        return new JarEntry(entry, JarFile.this);
      }
    };
  }

  public InputStream getInputStream(ZipEntry entry) throws IOException {
    InputStream in = super.getInputStream(entry);
    if (verify) {
      //implement
    }
    return in;
  }
  
  protected Certificate[] getCertificates(JarEntry entry) {
    if (verify) {
      //implement
    }
    return null;
  }

}

