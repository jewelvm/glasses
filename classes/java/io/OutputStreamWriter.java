/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

// review
public class OutputStreamWriter extends Writer {

  private final OutputStream out;

  public OutputStreamWriter(OutputStream out) {
    super(out);
    this.out = out;
  }

  public OutputStreamWriter(OutputStream out, String encoding) throws UnsupportedEncodingException {
    this(out);
  }

  public String getEncoding() {
    return null;
  }

  public void write(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      out.write(array[i]);
  }

  public void flush() throws IOException {
    out.flush();
  }

  public void close() throws IOException {
    flush();
    out.close();
  }

}

