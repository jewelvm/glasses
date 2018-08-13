/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

import java.net.Socket.SocketDescriptor;

public class FileDescriptor {

  private static final int STDIN_ID = 0;
  private static final int STDOUT_ID = 1;
  private static final int STDERR_ID = 2;

  public static final FileDescriptor in = new FileDescriptor(STDIN_ID);
  public static final FileDescriptor out = new FileDescriptor(STDOUT_ID);
  public static final FileDescriptor err = new FileDescriptor(STDERR_ID);

  private int id;

  public FileDescriptor() {
    this(-1);
  }
  
  private FileDescriptor(int id) {
    this.id = id;
  }

  protected final int getID() {
    if (this instanceof SocketDescriptor)
      return id;
    throw new UnsupportedOperationException();
  }

  protected final void setID(int id) {
    if (this instanceof SocketDescriptor) {
      this.id = id;
      return;
    }
    throw new UnsupportedOperationException();
  }
  
  public final boolean valid() {
    return id != -1;
  }
  
  public final void sync() throws SyncFailedException {
    io.sync(id);
  }

  void create(String name, boolean append) throws FileNotFoundException {
    id = io.create(name, append);
  }
  
  void open(String name, boolean write) throws FileNotFoundException {
    id = io.open(name, write);
  }

  int read(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length > 0)
      return io.read(id, array, start, length);
    return 0;
  }

  void write(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (length > 0)
      io.write(id, array, start, length);
  }

  void close() throws IOException {
    io.close(id);
    id = -1;
  }

  void seek(long offset) throws IOException {
    io.seek(id, offset);
  }

  long tell() throws IOException {
    return io.tell(id);
  }

  long length() throws IOException {
    return io.length(id);
  }

  void adjust(long offset) throws IOException {
    io.adjust(id, offset);
  }

}

