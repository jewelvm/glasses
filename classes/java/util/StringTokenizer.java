/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public class StringTokenizer implements Enumeration {

  private final String string;
  private String delimiters;
  private final boolean retdelims;
  private int index;

  public StringTokenizer(String string) {
    this(string, " \t\n\r\f");
  }

  public StringTokenizer(String string, String delimiters) {
    this(string, delimiters, false);
  }

  public StringTokenizer(String string, String delimiters, boolean retdelims) {
    if (string == null)
      throw new NullPointerException();
    if (delimiters == null)
      throw new NullPointerException();
    this.string = string;
    this.delimiters = delimiters;
    this.retdelims = retdelims;
  }

  private boolean isDelimiter(char shar) {
    return delimiters.indexOf(shar) != -1;
  }

  public boolean hasMoreTokens() {
    if (!retdelims)
      for ( ; index < string.length(); index++)
        if (!isDelimiter(string.charAt(index)))
          break;
    return index < string.length();
  }

  public String nextToken() {
    if (!hasMoreTokens())
      throw new NoSuchElementException();
    if (retdelims) {
      char shar = string.charAt(index);
      if (isDelimiter(shar)) {
        index++;
        return String.valueOf(shar);
      }
    }
    int start = index;
    for ( ; index < string.length(); index++)
      if (isDelimiter(string.charAt(index)))
        break;
    int end = index;
    return string.substring(start, end);
  }

  public String nextToken(String delimiters) {
    if (delimiters == null)
      throw new NullPointerException();
    this.delimiters = delimiters;
    return nextToken();
  }

  public boolean hasMoreElements() {
    return hasMoreTokens();
  }

  public Object nextElement() {
    return nextToken();
  }

  public int countTokens() {
    int backup = index;
    try {
      int count = 0;
      while (hasMoreTokens()) {
        nextToken();
        count++;
      }
      return count;
    } finally {
      index = backup;
    }
  }

}

