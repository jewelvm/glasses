/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;

public class InflaterInputStream extends FilterInputStream {

  protected Inflater inf;
  protected int len;
  protected byte[] buf;

  public InflaterInputStream(InputStream in) {
    this(in, new Inflater());
  }

  public InflaterInputStream(InputStream in, Inflater inf) {
    this(in, inf, 512);
  }

  public InflaterInputStream(InputStream in, Inflater inf, int size) {
    super(in);
    if (inf == null)
      throw new NullPointerException();
    if (size <= 0)
      throw new IllegalArgumentException("Invalid buffer size");
    this.inf = inf;
    buf = new byte[size];
  }

  protected void fill() throws IOException {
    len = in.read(buf, 0, buf.length);
    if (len == -1)
      throw new EOFException();
    inf.setInput(buf, 0, len);
  }

  public int read() throws IOException {
    byte[] array = new byte[1];
    int read = read(array);
    if (read < 1)
      return -1;
    return array[0] & 0xFF;
  }
      
  public int read(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length == 0)
      return 0;
    for (;;) {
      int total;
      try {
        total = inf.inflate(array, start, length);
      } catch (DataFormatException e) {
        throw new ZipException(e.getMessage());
      }
      if (total != 0)
        return total;
      if (inf.finished() || inf.needsDictionary())
        return -1;
      if (inf.needsInput())
        fill();
    }
  }

  public long skip(long count) throws IOException {
    if (count <= 0)
      return 0;
    byte[] array = new byte[256];
    long total = 0;
    while (total < count) {
      int limit = array.length;
      long remaining = count-total;
      if (limit > remaining)
        limit = (int)remaining;
      int read = read(array, 0, limit);
      if (read == -1)
        break;
      total += read;
    }
    return total;
  }

  public int available() throws IOException {
    return inf.finished() ? 0 : 1;
  }
      
  public void close() throws IOException {
    in.close();
    inf.end();
  }
  
}

