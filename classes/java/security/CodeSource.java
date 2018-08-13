/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.Serializable;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.HashSet;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

public class CodeSource implements Serializable {

  private final URL location;
  private final transient HashSet certSet = new HashSet();

  public CodeSource(URL location, Certificate[] certificates) {
    this.location = location;
    if (certificates != null)
      for (int i = 0; i < certificates.length; i++)
        certSet.add(certificates[i]);
  }

  public final URL getLocation() {
    return location;
  }

  public final Certificate[] getCertificates() {
    return (Certificate[])certSet.toArray(new Certificate[certSet.size()]);
  }

  public boolean implies(CodeSource codeSource) {
    if (codeSource == null)
      return false;
    if (!codeSource.certSet.containsAll(certSet))
      return false;
    if (location == null)
      return true;
    if (codeSource.location == null)
      return false;
    String protocol = location.getProtocol();
    if (protocol != null && !protocol.equals(codeSource.location.getProtocol()))
      return false;
    String host = location.getHost();
    if (host != null) {
      String csHost = codeSource.location.getHost();
      if (csHost == null)
        return false;
      if (host.equals(""))
        host = "localhost";
      if (csHost.equals(""))
        csHost = "localhost";
      if (!host.equals(csHost)) {
//        SocketPermission permission = new SocketPermission(host, "resolve");
//        SocketPermission csPermission = new SocketPermission(csHost, "resolve");
//        if (!permission.implies(csPermission))
          return false;
      }
    }
    int port = location.getPort();
    if (port != -1 && port != codeSource.location.getPort())
      return false;
    String file = location.getFile();
    if (file != null) {
      String csFile = codeSource.location.getFile();
      if (csFile == null)
        return false;
      if (file.endsWith("/-")) {
        if (!csFile.startsWith(file.substring(0, file.length()-1)))
          return false;
      } else if (file.endsWith("/*")) {
        if (!csFile.substring(0, csFile.lastIndexOf('/')+1).equals(file.substring(0, file.length()-1)))
          return false;
      } else if (!csFile.equals(file) && !csFile.equals(file+"/"))
        return false;
    }
    String ref = location.getRef();
    if (ref != null && !ref.equals(codeSource.location.getRef()))
      return false;
    return true;
  }

  public int hashCode() {
    return location == null ? 0 : location.hashCode();
  }

  public boolean equals(Object object) {
    if (object instanceof CodeSource) {
      CodeSource codeSource = (CodeSource)object;
      if (location != codeSource.location && (location == null || !location.equals(codeSource.location)))
        return false;
      if (!certSet.equals(codeSource.certSet))
        return false;
      return true;
    }
    return false;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("(");
    sb.append(location);
    sb.append(" ");
    sb.append(certSet);
    sb.append(")");
    return sb.toString();
  }

//  private void writeObject(ObjectOutputStream oos) throws IOException {
//  }

//  private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
//  }

}

