/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DigestOutputStream extends FilterOutputStream {

  private boolean on = true;
  protected MessageDigest digest;

  public DigestOutputStream(OutputStream out, MessageDigest digest) {
    super(out);
    this.digest = digest;
  }

  public void on(boolean on) {
    this.on = on;
  }

  public void setMessageDigest(MessageDigest digest) {
    this.digest = digest;
  }

  public MessageDigest getMessageDigest() {
    return digest;
  }

  public void write(int bite) throws IOException {
    if (on)
      digest.update((byte)bite);
    out.write(bite);
  }

  public void write(byte[] array, int start, int length) throws IOException {
    if (on)
      digest.update(array, start, length);
    out.write(array, start, length);
  }

}

