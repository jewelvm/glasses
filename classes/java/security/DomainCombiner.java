/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

public interface DomainCombiner {
    
  public ProtectionDomain[] combine(ProtectionDomain[] current, ProtectionDomain[] assigned);

}

