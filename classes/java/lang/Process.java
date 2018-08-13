/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Process {

  protected Process() { }

  public abstract InputStream getInputStream();
  public abstract InputStream getErrorStream();
  public abstract OutputStream getOutputStream();
  public abstract int waitFor() throws InterruptedException;
  public abstract int exitValue();
  public abstract void destroy();

}

