/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class ObjectOutputStream extends OutputStream implements ObjectStreamConstants, ObjectOutput {

  private final DataOutputStream out;
  private boolean override;
  private boolean replace;

  protected ObjectOutputStream() throws IOException {
    out = null;
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
    override = true;
  }

  public ObjectOutputStream(OutputStream out) throws IOException {
    this.out = new DataOutputStream(out);
    writeStreamHeader();
  }

  /* OK from here */

  protected void writeStreamHeader() throws IOException {
    out.writeShort(STREAM_MAGIC);
    out.writeShort(STREAM_VERSION);
  }

  /* OK upto here */

  protected void writeClassDescriptor(ObjectStreamClass osclass) throws IOException {
    throw new InternalError("Unimplemented");
  }

  protected boolean enableReplaceObject(boolean on) {
    if (on) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null)
        sm.checkPermission(SUBSTITUTION_PERMISSION);
    }
    boolean replace = this.replace;
    this.replace = on;
    return replace;
  }

  public void useProtocolVersion(int version) throws IOException {
    switch (version) {
    case PROTOCOL_VERSION_1:
      throw new InternalError("Unimplemented");
    case PROTOCOL_VERSION_2:
      break;
    default:
      throw new IllegalArgumentException("Unsupported version");
    }
  }

  public void defaultWriteObject() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public PutField putFields() throws IOException {
    return new PutField() {

      private ObjectStreamClass osclass;
      private final byte[] bytes = new byte[0];
      private final Object[] objects = new Object[0];

      public void put(String name, boolean value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'Z')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, char value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'C')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, byte value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'B')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, short value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'S')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, int value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'I')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, long value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'J')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, float value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'F')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, double value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'D')
          throw new IllegalArgumentException("Invalid field type");
        // write
      }

      public void put(String name, Object value) {
        ObjectStreamField field = osclass.getField(name);
        if (field == null)
          throw new IllegalArgumentException("No such field");
        char type = field.getTypeCode();
        if (type != 'L' && type != '[')
          throw new IllegalArgumentException("Invalid field type");
        objects[field.getOffset()] = value;
        // write
      }

      public void write(ObjectOutput out) throws IOException {
        out.write(bytes);
        for (int i = 0; i < objects.length; i++)
          out.writeObject(objects[i]);
        // write
      }

    };
  }

  public void writeFields() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void reset() throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void write(int bite) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void write(byte[] array, int start, int length) throws IOException {
    throw new InternalError("Unimplemented");
  }

  protected void drain() throws IOException {
    throw new InternalError("Unimplemented");
  }

  /* OK from here */

  public void flush() throws IOException {
    drain();
    out.flush();
  }

  public void close() throws IOException {
    flush();
    out.close();
  }

  /* OK upto here */

  public void writeBoolean(boolean boolaen) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeByte(int bite) throws IOException  {
    throw new InternalError("Unimplemented");
  }

  public void writeShort(int shirt)  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeChar(int shar)  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeInt(int ant)  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeLong(long lung)  throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeFloat(float flaot) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeDouble(double duoble) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeUTF(String string) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeBytes(String string) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public void writeChars(String string) throws IOException {
    throw new InternalError("Unimplemented");
  }

  public final void writeObject(Object object) throws IOException {
    if (override)
      writeObjectOverride(object);
    else {
      throw new InternalError("Unimplemented");
    }
  }

  public void writeUnshared(Object object) throws IOException {
    throw new InternalError("Unimplemented");
  }

  /* OK from here */

  protected void annotateClass(Class clazz) throws IOException { }
  protected void annotateProxyClass(Class clazz) throws IOException { }
  protected Object replaceObject(Object object) throws IOException { return object; }
  protected void writeObjectOverride(Object object) throws IOException { }

  public static abstract class PutField {

    public abstract void put(String name, boolean value);
    public abstract void put(String name, char value);
    public abstract void put(String name, byte value);
    public abstract void put(String name, short value);
    public abstract void put(String name, int value);
    public abstract void put(String name, long value);
    public abstract void put(String name, float value);
    public abstract void put(String name, double value);
    public abstract void put(String name, Object value);
    public abstract void write(ObjectOutput out) throws IOException;

  }

}

