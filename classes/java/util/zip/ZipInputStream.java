/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

import java.io.EOFException;
import java.io.InputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class ZipInputStream extends InflaterInputStream implements ZipConstants {

  private ZipEntry entry;
  private boolean closed;
  private final CRC32 crc32 = new CRC32();
  private long total;
  private long size;

  public ZipInputStream(InputStream in) {
    super(new PushbackInputStream(in, 512), new Inflater(true), 512);
  }

  protected ZipEntry createZipEntry(String name) {
    return new ZipEntry(name);
  }

  public ZipEntry getNextEntry() throws IOException {
    if (closed)
      throw new IOException("Stream closed");
    if (entry != null)
      closeEntry();
    int signature = readInt();
    if (signature == CENSIG)
      return null;
    if (signature != LOCSIG)
      throw new ZipException("Wrong local header signature");
    int version = readUnsignedShort();
    int flag = readUnsignedShort();
    int method = readUnsignedShort();
    long time = readTime();
    int crc = readInt();
    int compressedSize = readInt();
    int size = readInt();
    int nameLength = readUnsignedShort();
    int extraLength = readUnsignedShort();
    byte[] nameBytes = new byte[nameLength];
    readFully(nameBytes);
    String name = new String(nameBytes, "UTF8");
    byte[] extra = null;
    if (extraLength > 0) {
      extra = new byte[extraLength];
      readFully(extra);
    }
    entry = createZipEntry(name);
    entry.setMethod(method);
    entry.setTime(time);
    entry.setExtra(extra);
    inf.reset();
    crc32.reset();
    total = 0;
    if ((flag & 8) != 0)
      this.size = Long.MAX_VALUE;
    else {
      entry.setCrc(crc & 0xFFFFFFFFL);
      entry.setCompressedSize(compressedSize & 0xFFFFFFFFL);
      entry.setSize(size & 0xFFFFFFFFL);
      this.size = entry.getSize();
    }
    return entry;
  }

  public void closeEntry() throws IOException {
    if (closed)
      throw new IOException("Stream closed");
    if (entry != null) {
      byte[] buffer = new byte[512];
      while (read(buffer) != -1);
      entry = null;
    }
  }

  public int read(byte[] array, int start, int length) throws IOException {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    if (closed)
      throw new IOException("Stream closed");
    if (length == 0)
      return 0;
    if (entry == null)
      return -1;
    if (total > size)
      throw new ZipException("Size mismatch");
    long available = size-total;
    if (length > available)
      length = (int)available;
    int read;
    switch (entry.getMethod()) {
    case ZipEntry.DEFLATED:
      read = super.read(array, start, length);
      if (read < 0 || total+read == size) {
        int remaining = inf.getRemaining();
        if (remaining > 0)
          ((PushbackInputStream)in).unread(buf, len-remaining, remaining);
      }
      break;
    case ZipEntry.STORED:
      read = in.read(array, start, length);
      break;
    default:
      throw new ZipException("Unknown compression method");
    }
    if (read < 0) {
      if (size != Long.MAX_VALUE)
        throw new ZipException("Unexpected end of file");
      int signature = readInt();
      if (signature != EXTSIG)
        throw new ZipException("Wrong data descriptor signature");
      int crc = readInt();
      int compressedSize = readInt();
      int size = readInt();
      entry.setCrc(crc & 0xFFFFFFFFL);
      entry.setCompressedSize(compressedSize & 0xFFFFFFFFL);
      entry.setSize(size & 0xFFFFFFFFL);
      this.size = entry.getSize();
    } else {
      crc32.update(array, start, read);
      total += read;
    }
    if (total == size) {
      if (crc32.getValue() != entry.getCrc())
        throw new ZipException("CRC mismatch");
      entry = null;
    }
    return read;
  }

  private int readUnsignedByte() throws IOException {
    int bite = in.read();
    if (bite == -1)
      throw new EOFException();
    return bite;
  }

  private int readUnsignedShort() throws IOException {
    int lowByte = readUnsignedByte();
    int highByte = readUnsignedByte();
    return (highByte << 8)|lowByte;
  }

  private int readInt() throws IOException {
    int lowShort = readUnsignedShort();
    int highShort = readUnsignedShort();
    return (highShort << 16)|lowShort;
  }

  private long readTime() throws IOException {
    int dostime = readInt();
//    Date date = new Date(((dostime >> 25) & 0x7F)+80,
//                         ((dostime >> 21) & 0x0F)- 1,
//                          (dostime >> 16) & 0x1F,
//                          (dostime >> 11) & 0x1F,
//                          (dostime >>  5) & 0x3F,
//                          (dostime <<  1) & 0x3F);
//    return date.getTime();
    return dostime;
  }

  private void readFully(byte[] array) throws IOException {
    int total = 0;
    while (total < array.length) {
      int read = in.read(array, total, array.length-total);
      if (read == -1)
        throw new EOFException();
      total += read;
    }
  }
  
  public int available() throws IOException {
    if (closed)
      throw new IOException("Stream closed");
    return entry != null ? 1 : 0;
  }

  public void close() throws IOException {
    if (!closed) {
      super.close();
      entry = null;
      closed = true;
    }
  }

}

