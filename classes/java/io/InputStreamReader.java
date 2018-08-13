/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

// review
public class InputStreamReader extends Reader {

  private final InputStream in;

  public InputStreamReader(InputStream in) {
    super(in);
    this.in = in;
  }

  public InputStreamReader(InputStream in, String encoding) throws UnsupportedEncodingException {
    this(in);
  }

  public String getEncoding() {
    return null;
  }

  public int read(char[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = 0; i < length; i++) {
      int bite = in.read();
      if (bite == -1) {
        if (i == 0)
          return -1;
        return i;
      }
      array[start+i] = (char)bite;
    }
    return length;
  }

  public boolean ready() throws IOException {
    return in.available() > 0;
  }

  public void close() throws IOException {
    in.close();
  }

}

