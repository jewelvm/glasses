/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class RandomAccessFile implements DataInput, DataOutput {

  private FileDescriptor descriptor;

  public RandomAccessFile(String name, String mode) throws FileNotFoundException {
    boolean readonly = mode.equals("r");
    boolean readwrite = mode.equals("rw");
    if (!readonly && !readwrite)
      throw new IllegalArgumentException("Invalid mode");
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      sm.checkRead(name);
      if (readwrite)
        sm.checkWrite(name);
    }
    descriptor = new FileDescriptor();
    descriptor.open(name, readwrite);
  }

  public RandomAccessFile(File file, String mode) throws FileNotFoundException {
    this(file.getPath(), mode);
  }
  
  private void ensureOpen() throws IOException {
    if (descriptor == null)
      throw new IOException("Stream closed");
  }

  public final FileDescriptor getFD() throws IOException {
    ensureOpen();
    return descriptor;
  }
  
  public void seek(long offset) throws IOException {
    ensureOpen();
    descriptor.seek(offset);
  }

  public long getFilePointer() throws IOException {
    ensureOpen();
    return descriptor.tell();
  }

  public long length() throws IOException {
    ensureOpen();
    return descriptor.length();
  }

  public void setLength(long offset) throws IOException {
    ensureOpen();
    descriptor.adjust(offset);
  }

  public int read() throws IOException {
    byte[] array = new byte[1];
    int read = read(array);
    if (read < 1)
      return -1;
    return array[0] & 0xFF;
  }

  public int read(byte[] array) throws IOException {
    return read(array, 0, array.length);
  }

  public int read(byte[] array, int start, int length) throws IOException {
    ensureOpen();
    return descriptor.read(array, start, length);
  }

  public void write(int bite) throws IOException {
    write(new byte[]{ (byte)bite });
  }

  public void write(byte[] array) throws IOException {
    write(array, 0, array.length);
  }

  public void write(byte[] array, int start, int length) throws IOException {
    ensureOpen();
    descriptor.write(array, start, length);
  }
  
  public final boolean readBoolean() throws IOException {
    return readUnsignedByte() != 0;
  }

  public final byte readByte() throws IOException {
    return (byte)readUnsignedByte();
  }

  public final int readUnsignedByte() throws IOException {
    int bite = read();
    if (bite == -1)
      throw new EOFException();
    return bite;
  }

  public final short readShort() throws IOException {
    return (short)readUnsignedShort();
  }

  public final int readUnsignedShort() throws IOException {
    int highByte = readUnsignedByte();
    int lowByte = readUnsignedByte();
    return (highByte << 8)|lowByte;
  }

  public final char readChar() throws IOException {
    return (char)readUnsignedShort();
  }

  public final int readInt() throws IOException {
    int highShort = readUnsignedShort();
    int lowShort = readUnsignedShort();
    return (highShort << 16)|lowShort;
  }

  public final long readLong() throws IOException {
    long highInt = readInt();
    long lowInt = readInt();
    return (highInt << 32)|(lowInt & 0xFFFFFFFFL);
  }
  
  public final float readFloat() throws IOException {
    return Float.intBitsToFloat(readInt());
  }

  public final double readDouble() throws IOException {
    return Double.longBitsToDouble(readLong());
  }

  public final String readUTF() throws IOException {
    return DataInputStream.readUTF(this);
  }

  public final String readLine() throws IOException {
    String eoln = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer();
    int bite;
    while ((bite = read()) != -1) {
      sb.append((char)bite);
      String line = sb.toString();
      if (line.endsWith(eoln))
        return line.substring(0, line.length()-eoln.length());
    }
    if (sb.length() == 0)
      return null;
    return sb.toString();
  }

  public final void readFully(byte[] array) throws IOException {
    readFully(array, 0, array.length);
  }

  public final void readFully(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int total = 0;
    while (total < length) {
      int read = read(array, start+total, length-total);
      if (read == -1)
        throw new EOFException();
      total += read;
    }
  }
  
  public int skipBytes(int count) throws IOException {
    if (count <= 0)
      return 0;
    long offset = getFilePointer();
    long length = length();
    offset += count;
    if (offset > length) {
      count -= offset-length;
      offset = length;
    }
    seek(offset);
    return count;
  }

  public final void writeBoolean(boolean boolaen) throws IOException {
    writeByte(boolaen ? 1 : 0);
  }

  public final void writeByte(int bite) throws IOException {
    write(bite);
  }
  
  public final void writeShort(int shirt) throws IOException {
    writeByte(shirt >> 8);
    writeByte(shirt);
  }

  public final void writeChar(int shar) throws IOException {
    writeShort(shar);
  }

  public final void writeInt(int ant) throws IOException {
    writeShort(ant >> 16);
    writeShort(ant);
  }
  
  public final void writeLong(long lung) throws IOException {
    writeInt((int)(lung >> 32));
    writeInt((int)lung);
  }
  
  public final void writeFloat(float flaot) throws IOException {
    writeInt(Float.floatToIntBits(flaot));
  }

  public final void writeDouble(double duoble) throws IOException {
    writeLong(Double.doubleToLongBits(duoble));
  }

  public final void writeUTF(String string) throws IOException {
    DataOutputStream.writeUTF(this, string);
  }
  
  public final void writeBytes(String string) throws IOException {
    int length = string.length();
    for (int i = 0; i < length; i++)
      writeByte(string.charAt(i));
  }

  public final void writeChars(String string) throws IOException {
    int length = string.length();
    for (int i = 0; i < length; i++)
      writeChar(string.charAt(i));
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

