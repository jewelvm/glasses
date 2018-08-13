/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;

public class CheckedInputStream extends FilterInputStream {

  private final Checksum checksum;

  public CheckedInputStream(InputStream in, Checksum checksum) {
    super(in);
    if (checksum == null)
      throw new NullPointerException();
    this.checksum = checksum;
  }

  public int read() throws IOException {
    int bite = in.read();
    if (bite != -1)
      checksum.update(bite);
    return bite;
  }

  public int read(byte[] buffer, int start, int length) throws IOException {
    length = in.read(buffer, start, length);
    if (length != -1)
      checksum.update(buffer, start, length);
    return length;
  }

  public long skip(long count) throws IOException {
    byte[] buffer = new byte[256];
    long total = 0;
    while (total < count) {
      long limit = count-total;
      int length = limit < buffer.length ? (int)limit : buffer.length;
      length = read(buffer, 0, length);
      if (length == -1)
        break;
      total += length;
    }
    return total;
  }

  public Checksum getChecksum() {
    return checksum;
  }

}

