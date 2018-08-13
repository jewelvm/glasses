/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/** @deprecated */
public abstract class Identity implements Principal, Serializable {

  private static final long serialVersionUID = 3609922007826600659L;

  private String name;
  private IdentityScope scope;
  private PublicKey publicKey;
  private String info;
  private final ArrayList certificates = new ArrayList();
  
  protected Identity() { }

  public Identity(String name) {
    this.name = name;
  }
  
  public Identity(String name, IdentityScope scope) throws KeyManagementException {
    this.name = name;
    this.scope = scope;
    if (scope != null)
      scope.addIdentity(this);
  }
  
  public final String getName() {
    return name;
  }
  
  public final IdentityScope getScope() {
    return scope;
  }
  
  public PublicKey getPublicKey() {
    return publicKey;
  }
  
  public void setPublicKey(PublicKey publicKey) throws KeyManagementException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("setIdentityPublicKey");
    this.publicKey = publicKey;
  }
  
  public String getInfo() {
    return info;
  }
  
  public void setInfo(String info) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("setIdentityInfo");
    this.info = info;
  }
  
  public void addCertificate(Certificate certificate) throws KeyManagementException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("addIdentityCertificate");
    PublicKey publicKey = certificate.getPublicKey();
    if (this.publicKey != null)
      if (!this.publicKey.equals(publicKey))
        throw new KeyManagementException("Public key does not match certificate");
    this.publicKey = publicKey;
    certificates.add(certificate);
  }

  public void removeCertificate(Certificate certificate) throws KeyManagementException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("removeIdentityCertificate");
    certificates.remove(certificate);
  }
  
  public Certificate[] certificates() {
    return (Certificate[])certificates.toArray(new Certificate[certificates.size()]);
  }
  
  public int hashCode() {
    return name.hashCode();
  }
  
  public final boolean equals(Object object) {
    return object instanceof Identity
        && ((Identity)object).identityEquals(this);
  }
  
  protected boolean identityEquals(Identity identity) {
    return name.equalsIgnoreCase(identity.name)
        && (publicKey == identity.publicKey || (publicKey != null && publicKey.equals(identity.publicKey)));
  }
  
  public String toString() {
    return toString(false);
  }
  
  public String toString(boolean detailed) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSecurityAccess("printIdentity");
    StringBuffer sb = new StringBuffer();
    sb.append("Name: ");
    sb.append(name);
    if (scope != null) {
      sb.append(", Scope: ");
      sb.append(scope.getName());
    }
    if (detailed) {
      sb.append(", Public Key: ");
      if (publicKey == null)
        sb.append("not ");
      sb.append("available");
      if (certificates.size() > 0) {
        sb.append(", Certificates:");
        for (Iterator i = certificates.iterator(); i.hasNext(); ) {
          Certificate certificate = (Certificate)i.next();
          sb.append(" ");
          sb.append(certificate);
        }
      }
      if (info != null) {
        sb.append(", Info: ");
        sb.append(info);
      }
    }
    return sb.toString();
  }
  
}

