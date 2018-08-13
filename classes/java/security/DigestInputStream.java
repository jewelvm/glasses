/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.security;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;

public class DigestInputStream extends FilterInputStream {

  private boolean on = true;
  protected MessageDigest digest;
  
  public DigestInputStream(InputStream in, MessageDigest digest) {
    super(in);
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
  
  public int read() throws IOException {
    int bite = super.read();
    if (bite != -1)
      if (on)
        digest.update((byte)bite);
    return bite;
  }
  
  public int read(byte[] array, int start, int length) throws IOException {
    int read = in.read(array, start, length);
    if (read != -1)
      if (on)
        digest.update(array, start, read);
    return read;
  }
  
}

