/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

import java.io.EOFException;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class ZipFile implements ZipConstants {

  public static final int OPEN_READ = 1;
  public static final int OPEN_DELETE = 4;

  private final File file;
  private final ZipEntry[] entries;
  private final int[] offsets;
  private final RandomAccessFile raf;
  private final boolean delete;
    
  public ZipFile(String name) throws IOException {
    this(new File(name));
  }

  public ZipFile(File file) throws IOException {
    this(file, OPEN_READ);
  }

  public ZipFile(File file, int mode) throws IOException {
    if (mode != OPEN_READ && mode != (OPEN_READ|OPEN_DELETE))
      throw new IllegalArgumentException("Invalid mode");
    this.file = file;
    raf = new RandomAccessFile(file, "r");
    delete = (mode & OPEN_DELETE) != 0;

    raf.seek(raf.length()-22);
    int ecdsign = readInt();
    if (ecdsign != ENDSIG)
      throw new ZipException("Missing end of central directory");
    if (raf.skipBytes(6) != 6)
      throw new EOFException();
    int count = readUnsignedShort();
    if (raf.skipBytes(4) != 4)
      throw new EOFException();
    int centralOffset = readInt();
    raf.seek(centralOffset);

    entries = new ZipEntry[count];
    offsets = new int[count];
    for (int i = 0; i < count; i++) {

      int cdfhsign = readInt();
      if (cdfhsign != CENSIG)
        throw new ZipException("Wrong central directory signature");
      if (raf.skipBytes(6) != 6)
        throw new EOFException();
      int method = readUnsignedShort();
      long time = readTime();
      int crc = readInt();
      int compressedSize = readInt();
      int size = readInt();
      int nameLength = readUnsignedShort();
      int extraLength = readUnsignedShort();
      int commentLength = readUnsignedShort();
      if (raf.skipBytes(8) != 8)
        throw new EOFException();
      int offset = readInt();
      byte[] nameBytes = new byte[nameLength];
      raf.readFully(nameBytes);
      String name = new String(nameBytes, "UTF8");
      byte[] extra = null;
      if (extraLength > 0) {
        extra = new byte[extraLength];
        raf.readFully(extra);
      }
      String comment = null;
      if (commentLength > 0) {
        byte[] commentBytes = new byte[commentLength];
        raf.readFully(commentBytes);
        comment = new String(commentBytes, "UTF8");
      }

      ZipEntry entry = new ZipEntry(name);
      entry.setMethod(method);
      entry.setTime(time);
      entry.setCrc(crc & 0xFFFFFFFFL);
      entry.setCompressedSize(compressedSize & 0xFFFFFFFFL);
      entry.setSize(size & 0xFFFFFFFFL);
      entry.setExtra(extra);
      entry.setComment(comment);

      entries[i] = entry;
      offsets[i] = offset;
    }
  }

  private int readUnsignedShort() throws IOException {
    int lowByte = raf.readUnsignedByte();
    int highByte = raf.readUnsignedByte();
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

  public String getName() {
    return file.getPath();
  }

  public int size() {
    return entries.length;
  }

  private int indexOf(String name) {
    for (int i = 0; i < entries.length; i++) {
      ZipEntry entry = entries[i];
      if (name.equals(entry.getName()))
        return i;
    }
    return -1;
  }

  public ZipEntry getEntry(String name) {
    int index = indexOf(name);
    if (index == -1)
      return null;
    ZipEntry entry = entries[index];
    return (ZipEntry)entry.clone();
  }

  public Enumeration entries() {
    return new Enumeration() {
      private int index;
      public boolean hasMoreElements() { return index < entries.length; }
      public Object nextElement() {
        if (index == entries.length)
          throw new NoSuchElementException();
        ZipEntry entry = entries[index++];
        return (ZipEntry)entry.clone();
      }
    };
  }

  public InputStream getInputStream(ZipEntry entry) throws IOException {
    int index = indexOf(entry.getName());
    if (index == -1)
      return null;
    entry = entries[index];
    int offset = offsets[index];
    int method;
    int headerLength;
    synchronized(raf) {
      raf.seek(offset);
      int lhsign = readInt();
      if (lhsign != LOCSIG)
        throw new ZipException("Wrong local header signature");
      if (raf.skipBytes(4) != 4)
        throw new EOFException();
      method = readUnsignedShort();
      if (raf.skipBytes(16) != 16)
        throw new EOFException();
      int nameLength = readUnsignedShort();
      if (entry.getName().length() != nameLength)
        throw new ZipException("File name length mismatch");
      int extraLength = readUnsignedShort();
      headerLength = 30+nameLength+extraLength;
    }
    final long begin = offset+headerLength;
    final long end = begin+entry.getCompressedSize();
    InputStream in = new InputStream() {
      private long pos = begin;
      public int read() throws IOException {
        if (pos == end)
          return -1;
        int bite;
        synchronized(raf) {
          raf.seek(pos);
          bite = raf.read();
        }
        if (bite != -1)
          pos++;
        return bite;
      }
      public int read(byte[] array, int start, int length) throws IOException {
        if (pos == end)
          return -1;
        if (pos+length > end)
          length = (int)(end-pos);
        int read;
        synchronized(raf) {
          raf.seek(pos);
          read = raf.read(array, start, length);
        }
        if (read > 0)
          pos += read;
        return read;
      }
      public long skip(long count) {
        if (count <= 0)
          return 0;
        if (pos+count > end)
          count = end-pos;
        pos += count;
        return count;
      }
      public int available() {
        return (int)(end-pos);
      }
    };
    switch (method) {
    case ZipEntry.STORED:
      return in;
    case ZipEntry.DEFLATED:
      return new InflaterInputStream(in, new Inflater(true));
    default:
      throw new ZipException("Unknown compression method");
    }
  }

  public void close() throws IOException {
    synchronized (raf) {
      raf.close();
    }
    if (delete)
      file.delete();
  }

  protected void finalize() throws IOException {
    close();
  }

}

