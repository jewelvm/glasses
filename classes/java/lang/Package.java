/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.net.URL;
import java.util.StringTokenizer;

public class Package {
  
  public static Package[] getPackages() {
    ClassLoader loader = Thread.callerClass().getDefiningLoader();
    if (loader != null)
      return loader.getPackages();
    return ClassLoader.getSystemPackages();
  }
  
  public static Package getPackage(String name) {
    ClassLoader loader = Thread.callerClass().getDefiningLoader();
    if (loader != null)
      return loader.getPackage(name);
    return ClassLoader.getSystemPackage(name);
  }
  
  private final String name;
  private final String specificationTitle;
  private final String specificationVersion;
  private final String specificationVendor;
  private final String implementationTitle;
  private final String implementationVersion;
  private final String implementationVendor;
  private final URL url;

  Package(String name, String specTitle, String specVersion, String specVendor, 
          String implTitle, String implVersion, String implVendor, URL url) {
    if (name == null)
      throw new NullPointerException();
    this.name = name;
    specificationTitle = specTitle;
    specificationVersion = specVersion;
    specificationVendor = specVendor;
    implementationTitle = implTitle;
    implementationVersion = implVersion;
    implementationVendor = implVendor;
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public String getSpecificationTitle() {
    return specificationTitle;
  }

  public String getSpecificationVersion() {
    return specificationVersion;
  }

  public String getSpecificationVendor() {
    return specificationVendor;
  }

  public String getImplementationTitle() {
    return implementationTitle;
  }

  public String getImplementationVersion() {
    return implementationVersion;
  }
  public String getImplementationVendor() {
    return implementationVendor;
  }

  public boolean isSealed() {
    return url != null;
  }

  public boolean isSealed(URL url) {
    return this.url != null
        && this.url.equals(url);
  }

  public boolean isCompatibleWith(String string) throws NumberFormatException {
    if (specificationVersion == null)
      throw new NumberFormatException("Specification version not available");
    StringTokenizer tokenizer = new StringTokenizer(specificationVersion, ".");
    StringTokenizer stringTokenizer = new StringTokenizer(string, ".");
    int value = 0;
    while (tokenizer.hasMoreTokens() || stringTokenizer.hasMoreTokens()) {
      int version = 0;
      if (tokenizer.hasMoreTokens()) {
        String token = tokenizer.nextToken();
        version = Integer.parseInt(token);
        if (version < 0)
          throw new NumberFormatException(token);
      }
      int stringVersion = 0;
      if (stringTokenizer.hasMoreTokens()) {
        String token = stringTokenizer.nextToken();
        stringVersion = Integer.parseInt(token);
        if (stringVersion < 0)
          throw new NumberFormatException(token);
      }
      if (value == 0)
        if (version < stringVersion)
          value = -1;
        else if (version > stringVersion)
          value = 1;
    }
    return value >= 0;
  }
  
  public int hashCode() {
    return name.hashCode();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer(64);
    sb.append("package");
    sb.append(' ');
    sb.append(name);
    if (specificationTitle != null) {
      sb.append(',');
      sb.append(' ');
      sb.append(specificationTitle);
    }
    if (specificationVersion != null) {
      sb.append(',');
      sb.append(' ');
      sb.append("version");
      sb.append(' ');
      sb.append(specificationVersion);
    }
    return sb.toString();
  }

}

