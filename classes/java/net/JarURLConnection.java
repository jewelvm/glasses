/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public abstract class JarURLConnection extends URLConnection {

  private final URL jarFileURL;
  private final String entryName;

  protected URLConnection jarFileURLConnection;

  protected JarURLConnection(URL url) throws MalformedURLException {
    super(url);
    String file = url.getFile();
    int index = file.lastIndexOf('!');
    if (index == -1 || index+1 == file.length() || file.charAt(index+1) != '/')
      throw new MalformedURLException();
    jarFileURL = new URL(file.substring(0, index));
    String entryName = file.substring(index+2);
    if (entryName.equals(""))
      entryName = null;
    this.entryName = entryName;
  }	

  public URL getJarFileURL() {
    return jarFileURL;
  }

  public String getEntryName() {
    return entryName;
  }

  public abstract JarFile getJarFile() throws IOException;

  public Manifest getManifest() throws IOException {
    return getJarFile().getManifest();
  }
          
  public Attributes getMainAttributes() throws IOException { 
    Manifest manifest = getManifest();
    if (manifest == null)
      return null;
    return manifest.getMainAttributes();
  }
 
  public JarEntry getJarEntry() throws IOException {
    return getJarFile().getJarEntry(getEntryName());
  }

  public Attributes getAttributes() throws IOException {
    JarEntry entry = getJarEntry();
    if (entry == null)
      return null;
    return entry.getAttributes();
  }

  public Certificate[] getCertificates() throws IOException {
    JarEntry entry = getJarEntry();
    if (entry == null)
      return null;
    return entry.getCertificates();
  }

}

