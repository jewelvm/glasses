/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class DataInputStream extends FilterInputStream implements DataInput {
    
  public final static String readUTF(DataInput in) throws IOException {
    int index = 0;
    char[] buffer = new char[in.readUnsignedShort()];
    for (int i = 0; i < buffer.length; i++) {
      char shar;
      int bite = in.readUnsignedByte();
      if ((bite & 0x80) == 0x00) {
        if (bite == 0x00)
          throw new UTFDataFormatException();
        shar = (char)bite;
      } else if ((bite & 0xE0) == 0xC0) {
        i++;
        if (i == buffer.length)
          throw new UTFDataFormatException();
        int bite2 = in.readUnsignedByte();
        if ((bite2 & 0xC0) != 0x80)
          throw new UTFDataFormatException();
        shar = (char)(((bite & 0x1F) << 6)|(bite2 & 0x3F));
      } else if ((bite & 0xF0) == 0xE0) {
        i += 2;
        if (i >= buffer.length)
          throw new UTFDataFormatException();
        int bite2 = in.readUnsignedByte();
        if ((bite2 & 0xC0) != 0x80)
          throw new UTFDataFormatException();
        int bite3 = in.readUnsignedByte();
        if ((bite3 & 0xC0) != 0x80)
          throw new UTFDataFormatException();
        shar = (char)(((bite & 0x0F) << 12)|((bite2 & 0x3F) << 6)|(bite3 & 0x3F));
      } else
        throw new UTFDataFormatException();
      buffer[index++] = shar;
    }
    return new String(buffer, 0, index);
  }

  public DataInputStream(InputStream in) {
    super(in);
  }

  public final int read(byte[] array) throws IOException {
    return super.read(array);
  }

  public final int read(byte[] array, int start, int length) throws IOException {
    return super.read(array, start, length);
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
    return readUTF(this);
  }

  /** @deprecated */
  public final String readLine() throws IOException {
    StringBuffer sb = new StringBuffer();
    int bite;
    while ((bite = read()) != -1) {
      if (bite == '\n') {
        int length = sb.length();
        if (length > 0)
          if (sb.charAt(length-1) == '\r')
            sb.setLength(length-1);
        return sb.toString();
      }
      sb.append((char)bite);
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
  
  public final int skipBytes(int count) throws IOException {
    int total = 0;
    while (total < count) {
      int read = (int)skip(count-total);
      if (read == -1)
        break;
      total += read;
    }
    return total;
  }

}

