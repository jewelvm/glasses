/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class DataOutputStream extends FilterOutputStream implements DataOutput {
    
  public final static void writeUTF(DataOutput out, String string) throws IOException {
    int count = 0;
    int length = string.length();
    for (int i = 0; i < length; i++) {
      int shar = string.charAt(i);
      if ('\u0001' <= shar && shar <= '\u007F')
        count++;
      else if (shar <= '\u07FF')
        count += 2;
      else
        count += 3;
    }
    if (count > 65535)
      throw new UTFDataFormatException("String length exceeds UTF representation limit");
    out.writeChar(count);
    for (int i = 0; i < length; i++) {
      int shar = string.charAt(i);
      if ('\u0001' <= shar && shar <= '\u007F')
        out.writeByte(shar);
      else {
        if (shar <= '\u07FF')
          out.writeByte(((shar >>  6)&0x1F)|0xC0);
        else {
          out.writeByte(((shar >> 12)&0x0F)|0xE0);
          out.writeByte(((shar >>  6)&0x3F)|0x80);
        }
        out.writeByte((shar&0x3F)|0x80);
      }
    }
  }

  protected int written;

  public DataOutputStream(OutputStream out) {
    super(out);
  }

  public final int size() {
    return written;
  }

  public void write(int bite) throws IOException {
    out.write(bite);
    if (written < Integer.MAX_VALUE)
      written++;
  }

  public void write(byte[] array, int start, int length) throws IOException {
    out.write(array, start, length);
    written = length > Integer.MAX_VALUE-written ? Integer.MAX_VALUE : written+length;
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
    writeUTF(this, string);
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
  
}

