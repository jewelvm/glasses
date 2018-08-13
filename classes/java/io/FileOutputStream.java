/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class FileOutputStream extends OutputStream {

  private FileDescriptor descriptor;
    
  public FileOutputStream(String name) throws FileNotFoundException {
    this(name, false);
  }

  public FileOutputStream(String name, boolean append) throws FileNotFoundException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkWrite(name);
    FileDescriptor descriptor = new FileDescriptor();
    descriptor.create(name, append);
    this.descriptor = descriptor;
  }

  public FileOutputStream(File file) throws FileNotFoundException {
    this(file.getPath());
  }

  public FileOutputStream(File file, boolean append) throws FileNotFoundException {
    this(file.getPath(), append);
  }

  public FileOutputStream(FileDescriptor descriptor) {
    if (descriptor == null)
      throw new NullPointerException();
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkWrite(descriptor);
    this.descriptor = descriptor;
  }
  
  public final FileDescriptor getFD() throws IOException {
    if (descriptor == null)
      throw new IOException("Stream closed");
    return descriptor;
  }
  
  public void write(int bite) throws IOException {
    write(new byte[]{ (byte)bite });
  }
  
  public void write(byte[] array, int start, int length) throws IOException {
    if (descriptor == null)
      throw new IOException("Stream closed");
    descriptor.write(array, start, length);
  }
  
  public void close() throws IOException {
    if (descriptor != null) {
      descriptor.close();
      descriptor = null;
    }
  }
  
  protected void finalize() throws IOException {
    close();
  }

}

