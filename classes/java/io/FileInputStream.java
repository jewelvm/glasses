/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class FileInputStream extends InputStream {
    
  private FileDescriptor descriptor;

  public FileInputStream(String name) throws FileNotFoundException {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(name);
    FileDescriptor descriptor = new FileDescriptor();
    descriptor.open(name, false);
    this.descriptor = descriptor;
  }

  public FileInputStream(File file) throws FileNotFoundException {
    this(file.getPath());
  }

  public FileInputStream(FileDescriptor descriptor) {
    if (descriptor == null)
      throw new NullPointerException();
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkRead(descriptor);
    this.descriptor = descriptor;
  }

  public final FileDescriptor getFD() throws IOException {
    if (descriptor == null)
      throw new IOException("Stream closed");
    return descriptor;
  }
  
  public int read() throws IOException {
    byte[] array = new byte[1];
    int read = read(array);
    if (read < 1)
      return -1;
    return array[0] & 0xFF;
  }

  public int read(byte[] array, int start, int length) throws IOException {
    if (descriptor == null)
      throw new IOException("Stream closed");
    return descriptor.read(array, start, length);
  }

  public long skip(long count) throws IOException {
    if (descriptor == null)
      throw new IOException("Stream closed");
    if (count <= 0)
      return 0;
    long offset = descriptor.tell();
    long length = descriptor.length();
    offset += count;
    if (offset > length) {
      count -= offset-length;
      offset = length;
    }
    descriptor.seek(offset);
    return count;
  }

  public int available() throws IOException {
    if (descriptor == null)
      throw new IOException("Stream closed");
    return (int)(descriptor.length()-descriptor.tell());
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

