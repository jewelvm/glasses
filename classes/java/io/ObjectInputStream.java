/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class ObjectInputStream extends InputStream implements ObjectStreamConstants, ObjectInput {

  protected ObjectInputStream() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public ObjectInputStream(InputStream in) throws StreamCorruptedException, IOException {
    throw new InternalError("Unimplemented");
  }

  protected boolean enableResolveObject(boolean on) throws SecurityException {
    throw new InternalError("Unimplemented");
  }

  protected void readStreamHeader() throws StreamCorruptedException, IOException {
    throw new InternalError("Unimplemented");
  }

  protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
    throw new InternalError("Unimplemented");
  }

  public void defaultReadObject() throws IOException, ClassNotFoundException, NotActiveException {
    throw new InternalError("Unimplemented");
  }
    
  public GetField readFields() throws IOException, ClassNotFoundException, NotActiveException {
    throw new InternalError("Unimplemented");
  }

  public void registerValidation(ObjectInputValidation validation, int priority) throws NotActiveException, InvalidObjectException {
    throw new InternalError("Unimplemented");
  }

  public int read() throws IOException {
    throw new InternalError("Unimplemented");
  }
    
  public int read(byte[] b, int off, int len) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public int available() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void close() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public boolean readBoolean() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public byte readByte() throws IOException  {
    throw new InternalError("Unimplemented");
  }

  public int readUnsignedByte()  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public short readShort()  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public int readUnsignedShort() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public char readChar()  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public int readInt()  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public long readLong()  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public float readFloat() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public double readDouble() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public String readUTF() throws IOException {
    throw new InternalError("Unimplemented");
  }
    
  /** @deprecated */
  public String readLine() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void readFully(byte[] array) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void readFully(byte[] array, int start, int length) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public int skipBytes(int count) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public final Object readObject() throws OptionalDataException, ClassNotFoundException, IOException {
    throw new InternalError("Unimplemented");
  }

  public Object readUnshared() throws ClassNotFoundException, IOException {
    throw new InternalError("Unimplemented");
  }

  protected Class resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException {
    throw new InternalError("Unimplemented");
  }

  protected Class resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
    throw new InternalError("Unimplemented");
  }

  protected Object resolveObject(Object object) throws IOException {
    throw new InternalError("Unimplemented");
  }

  protected Object readObjectOverride() throws OptionalDataException, ClassNotFoundException, IOException {
    throw new InternalError("Unimplemented");
  }
 
  public static  abstract class GetField {

    public abstract ObjectStreamClass getObjectStreamClass();
    public abstract boolean defaulted(String name) throws IOException;
    public abstract boolean get(String name, boolean value) throws IOException;
    public abstract char get(String name, char value) throws IOException;
    public abstract byte get(String name, byte value) throws IOException;
    public abstract short get(String name, short value) throws IOException;
    public abstract int get(String name, int value) throws IOException;
    public abstract long get(String name, long value) throws IOException;
    public abstract float get(String name, float value) throws IOException;
    public abstract double get(String name, double value) throws IOException;
    public abstract Object get(String name, Object value) throws IOException;

  }
 
}

