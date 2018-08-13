/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util.zip;

public class ZipEntry implements Cloneable, ZipConstants {

  public static final int STORED = 0;
  public static final int DEFLATED = 8;

  private static final byte ZEF_METHOD = 1;
  private static final byte ZEF_TIME = 2;
  private static final byte ZEF_CRC = 4;
  private static final byte ZEF_COMPRESSEDSIZE = 8;
  private static final byte ZEF_SIZE = 16;

  private final String name;
  private byte flags;
  private long time;
  private int crc;
  private int compressedSize;
  private int size;
  private byte[] extra;
  private String comment;

  public ZipEntry(String name) {
    if (name.length() > 65535)
      throw new IllegalArgumentException();
    this.name = name;
  }

  public ZipEntry(ZipEntry entry) {
    name = entry.name;
    flags = entry.flags;
    time = entry.time;
    crc = entry.crc;
    compressedSize = entry.compressedSize;
    size = entry.size;
    extra = entry.extra;
    comment = entry.comment;
  }

  public String getName() {
    return name;
  }

  public boolean isDirectory() {
    return name.endsWith("/"); 
  }

  public void setMethod(int method) {
    switch (method) {
    case STORED:
      flags &= ~ZEF_METHOD;
      break;
    case DEFLATED:
      flags |= ZEF_METHOD;
      break;
    default:
      throw new IllegalArgumentException();
    }
  }

  public int getMethod() {
    return (flags & ZEF_METHOD) != 0 ? DEFLATED : STORED;
  }

  public void setTime(long time) {
    this.flags |= ZEF_TIME;
    this.time = time;
  }

  public long getTime() {
    return (flags & ZEF_TIME) != 0 ? time : -1L;
  }

  public void setCrc(long crc) {
    if ((crc & 0xFFFFFFFF00000000L) != 0)
	throw new IllegalArgumentException();
    this.flags |= ZEF_CRC;
    this.crc = (int)crc;
  }

  public long getCrc() {
    return (flags & ZEF_CRC) != 0 ? crc & 0xFFFFFFFFL : -1L;
  }

  public void setCompressedSize(long compressedSize) {
    if ((compressedSize & 0xFFFFFFFF00000000L) != 0)
	throw new IllegalArgumentException();
    this.flags |= ZEF_COMPRESSEDSIZE;
    this.compressedSize = (int)compressedSize;
  }

  public long getCompressedSize() {
    return (flags & ZEF_COMPRESSEDSIZE) != 0 ? compressedSize & 0xFFFFFFFFL : -1L;
  }

  public void setSize(long size) {
    if ((size & 0xFFFFFFFF00000000L) != 0)
	throw new IllegalArgumentException();
    this.flags |= ZEF_SIZE;
    this.size = (int)size;
  }

  public long getSize() {
    return (flags & ZEF_SIZE) != 0 ? size & 0xFFFFFFFFL : -1L;
  }

  public void setExtra(byte[] extra) {
    if (extra != null)
      if (extra.length > 65535)
        throw new IllegalArgumentException();
    this.extra = extra;
  }

  public byte[] getExtra() {
    return extra;
  }

  public void setComment(String comment) {
    if (comment != null)
      if (comment.length() > 65535)
        throw new IllegalArgumentException();
    this.comment = comment;
  }

  public String getComment() {
    return comment;
  }

  public Object clone() {
    try {
      ZipEntry entry = (ZipEntry)super.clone();
      if (extra != null)
        entry.extra = (byte[])extra.clone();
      return entry;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }

  public int hashCode() {
    return name.hashCode();
  }

  public String toString() {
    return name;
  }

}

