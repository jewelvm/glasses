/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;

public abstract class URLStreamHandler {

  protected URLStreamHandler() { }

  protected abstract URLConnection openConnection(URL url) throws IOException;

  protected void parseURL(URL url, String spec, int start, int end) {
    spec = spec.substring(start, end);

    String host = url.getHost();
    int port = url.getPort();
    if (spec.startsWith("//")) {
      spec = spec.substring(2);
      int slash = spec.indexOf('/');
      if (slash != -1)
        spec = spec.substring(slash);
      else {
        spec = "/";
        slash = spec.length();
      }
      host = spec.substring(0, slash);
      int colon = host.indexOf(':');
      if (colon == -1)
        port = getDefaultPort();
      else {
        host = host.substring(0, colon);
        try {
          port = Integer.parseInt(host.substring(colon+1));
        } catch (NumberFormatException e) { }
      }
    }

    String file = url.getFile();
    file = spec.startsWith("/") ? spec : file.substring(0, file.lastIndexOf('/')+1)+spec;

    setURL(url, url.getProtocol(), host, port, file, url.getRef());
  }

  protected int getDefaultPort() {
    return -1;
  }

  protected InetAddress getHostAddress(URL url) {
    String host = url.getHost();
    if (host != null)
      try {
        return InetAddress.getByName(host);
      } catch (UnknownHostException e) { }
    return null;
  }

  protected boolean hostsEqual(URL one, URL another) {
    InetAddress oneAddress = getHostAddress(one);
    InetAddress anotherAddress = getHostAddress(another);
    if (oneAddress != null && anotherAddress != null)
      return oneAddress.equals(anotherAddress);
    String oneHost = one.getHost();
    String anotherHost = another.getHost();
    return oneHost == anotherHost || (oneHost != null && oneHost.equalsIgnoreCase(anotherHost));
  }

  protected boolean sameFile(URL one, URL another) {
    String oneProtocol = one.getProtocol();
    String anotherProtocol = another.getProtocol();
    if (!(oneProtocol == anotherProtocol || (oneProtocol != null && oneProtocol.equalsIgnoreCase(anotherProtocol))))
      return false;
    if (!hostsEqual(one, another))
      return false;
    String oneFile = one.getFile();
    String anotherFile = another.getFile();
    if (!(oneFile == anotherFile || (oneFile != null && oneFile.equals(anotherFile))))
      return false;
    int onePort = one.getPort();
    int anotherPort = another.getPort();
    if (onePort != anotherPort)
      return false;
    return true;
  }

  /** @deprecated */
  protected void setURL(URL url, String protocol, String host, int port, String file, String ref) {
    url.set(protocol, host, port, file, ref);
  }

  protected void setURL(URL url, String protocol, String host, int port, String authority, String userInfo,
                        String path, String query, String ref) {
    url.set(protocol, host, port, authority, userInfo, path, query, ref);
  }

  protected int hashCode(URL url) {
    int hashCode = 0;
    String protocol = url.getProtocol();
    if (protocol != null)
        hashCode += protocol.hashCode();
    InetAddress address = getHostAddress(url);
    if (address != null)
      hashCode += address.hashCode();
    else {
      String host = url.getHost();
      if (host != null)
        hashCode += host.toLowerCase().hashCode();
    }
    String file = url.getFile();
    if (file != null)
      hashCode += file.hashCode();
    hashCode += url.getPort();
    String ref = url.getRef();
    if (ref != null)
      hashCode += ref.hashCode();
    return hashCode;
  }

  protected boolean equals(URL one, URL another) {
    String oneRef = one.getRef();
    String anotherRef = another.getRef();
    return sameFile(one, another)
        && (oneRef == anotherRef || (oneRef != null && oneRef.equals(anotherRef)));
  }
  
  protected String toExternalForm(URL url) {
    StringBuffer sb = new StringBuffer();
    sb.append(url.getProtocol());
    sb.append(":");
    String host = url.getHost();
    if (host != null)
      if (host.length() > 0) {
        sb.append("//");
        sb.append(host);
        int port = url.getPort();
        if (port != getDefaultPort()) {
          sb.append(":");
          sb.append(port);
        }
      }
    sb.append(url.getFile());
    String ref = url.getRef();
    if (ref != null) {
      sb.append("#");
      sb.append(ref);
    }
    return sb.toString();
  }

}

