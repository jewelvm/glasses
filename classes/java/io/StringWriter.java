/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class StringWriter extends Writer {

  private final StringBuffer sb;

  private StringWriter(StringBuffer sb) {
    super(sb);
    this.sb = sb;
  }

  public StringWriter() {
    this(new StringBuffer());
  }

  public StringWriter(int size) {
    this(new StringBuffer(size));
  }

  public StringBuffer getBuffer() {
    return sb;
  }

  public void write(int shar) {
    sb.append((char)shar);
  }

  public void write(char []array, int start, int length) {
    sb.append(array, start, length);
  }

  public void write(String string, int start, int length)  {
    sb.append(string.substring(start, start+length));
  }

  public void flush() { }

  public void close() { }

  public String toString() {
    return sb.toString();
  }

}

