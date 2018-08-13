/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public interface ObjectStreamConstants {

  public static final int PROTOCOL_VERSION_1 = 1;
  public static final int PROTOCOL_VERSION_2 = 2;

  public static final short STREAM_MAGIC = (short)0xACED;
  public static final short STREAM_VERSION = 5;

  public static final byte SC_WRITE_METHOD = 0x01;
  public static final byte SC_SERIALIZABLE = 0x02;
  public static final byte SC_EXTERNALIZABLE = 0x04;
  public static final byte SC_BLOCK_DATA = 0x08;  

  public static final byte TC_NULL = 0x70;
  public static final byte TC_REFERENCE = 0x71;
  public static final byte TC_CLASSDESC = 0x72;
  public static final byte TC_OBJECT = 0x73;
  public static final byte TC_STRING = 0x74;
  public static final byte TC_ARRAY = 0x75;
  public static final byte TC_CLASS = 0x76;
  public static final byte TC_BLOCKDATA = 0x77;
  public static final byte TC_ENDBLOCKDATA = 0x78;
  public static final byte TC_RESET = 0x79;
  public static final byte TC_BLOCKDATALONG = 0x7A;
  public static final byte TC_EXCEPTION = 0x7B;
  public static final byte TC_LONGSTRING = 0x7C;
  public static final byte TC_PROXYCLASSDESC = 0x7D;

  public static final byte TC_BASE = TC_NULL;
  public static final byte TC_MAX = TC_PROXYCLASSDESC;

  public static final int baseWireHandle = 0x007E0000;

  public static final SerializablePermission SUBSTITUTION_PERMISSION = new SerializablePermission("enableSubstitution");
  public static final SerializablePermission SUBCLASS_IMPLEMENTATION_PERMISSION = new SerializablePermission("enableSubclassImplementation");

}

