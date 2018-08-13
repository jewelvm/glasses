/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

import java.util.Enumeration;
import java.util.Vector;

public class SequenceInputStream extends InputStream {

  private static Enumeration enumeration(Object[] elements) {
    Vector v = new Vector(elements.length);
    for (int i = 0; i < elements.length; i++)
      v.add(elements[i]);
    return v.elements();
  }

  private final Enumeration e;
  private InputStream in;

  public SequenceInputStream(Enumeration e) {
    this.e = e;
    in = nextInputStream();
  }

  public SequenceInputStream(InputStream in1, InputStream in2) {
    this(enumeration(new Object[]{ in1, in2 }));
  }

  private InputStream nextInputStream() {
    if (e.hasMoreElements())
      return (InputStream)e.nextElement();
    return null;
  }

  public int read() throws IOException {
    while (in != null) {
      int bite = in.read();
      if (bite != -1)
        return bite;
      in.close();
      in = nextInputStream();
    }
    return -1;
  }

  public int read(byte[] array, int start, int length) throws IOException {
    while (in != null) {
      int read = in.read(array, start, length);
      if (read != -1)
        return read;
      in.close();
      in = nextInputStream();
    }
    return -1;
  }
  
  public int available() throws IOException {
    if (in != null)
      in.available();
    return 0;    
  }

  public void close() throws IOException {
    while (in != null) {
      in.close();
      in = nextInputStream();
    }
  }

}

