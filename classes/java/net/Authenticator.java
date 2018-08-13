/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

public abstract class Authenticator {

  private static Authenticator authenticator;

  public static synchronized void setDefault(Authenticator authenticator) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new NetPermission("setDefaultAuthenticator"));
    if (Authenticator.authenticator == null)
      Authenticator.authenticator = authenticator;
  }

  public static synchronized PasswordAuthentication requestPasswordAuthentication(InetAddress site, int port, String protocol, String prompt, String scheme) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new NetPermission("requestPasswordAuthentication"));
    if (authenticator != null) {
      authenticator.site = site;
      authenticator.port = port;
      authenticator.protocol = protocol;
      authenticator.prompt = prompt;
      authenticator.scheme = scheme;
      return authenticator.getPasswordAuthentication();
    }
    return null;
  }

  private InetAddress site;
  private int port;
  private String protocol;
  private String prompt;
  private String scheme;

  protected Authenticator() { }

  protected final InetAddress getRequestingSite() {
    return site;
  }

  protected final int getRequestingPort() {
    return port;
  }

  protected final String getRequestingProtocol() {
    return protocol;
  }

  protected final String getRequestingPrompt() {
    return prompt;
  }

  protected final String getRequestingScheme() {
    return scheme;
  }

  protected PasswordAuthentication getPasswordAuthentication() {
    return null;
  }

}

