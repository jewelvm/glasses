/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

public final class PasswordAuthentication {

  private final String userName;
  private final char[] password;

  public PasswordAuthentication(String userName, char[] password) {
    this.userName = userName;
    this.password = (char[])password.clone();
  }

  public String getUserName() {
    return userName;
  }

  public char[] getPassword() {
    return password;
  }

}

