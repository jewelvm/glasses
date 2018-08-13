/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

public interface CharSequence {

  public int length();
  public char charAt(int index);
  public CharSequence subSequence(int start, int end);

}

