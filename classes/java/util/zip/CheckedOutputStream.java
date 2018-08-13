/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.io.IOException;

public class CheckedOutputStream extends FilterOutputStream {

  private final Checksum checksum;

  public CheckedOutputStream(OutputStream out, Checksum checksum) {
    super(out);
    if (checksum == null)
      throw new NullPointerException();
    this.checksum = checksum;
  }

  public void write(int bite) throws IOException {
    out.write(bite);
    checksum.update(bite);
  }

  public void write(byte[] buffer, int start, int length) throws IOException {
    out.write(buffer, start, length);
    checksum.update(buffer, start, length);
  }

  public Checksum getChecksum() {
    return checksum;
  }

}

