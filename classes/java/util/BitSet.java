/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.Serializable;

public class BitSet implements Cloneable, Serializable {

  private static final long serialVersionUID = 7997698588986878753L;

  private int[] bits;

  public BitSet() {
    this(64);
  }

  public BitSet(int size) {
    if (size < 0)
      throw new IllegalArgumentException("Invalid size");
    bits = new int[(size+31)/32];
  }

  public int size() {
    return 32*bits.length;
  }

  public boolean isEmpty() {
    return length() == 0;
  }

  public int length() {
    if (bits.length == 0)
      return 0;
    int lastint = bits.length-1;
    while (bits[lastint] == 0) {
      if (lastint == 0)
        return 0;
      lastint--;
    }
    int nonzero = bits[lastint];
    int lastbit = 31;
    while ((nonzero & (1 << lastbit)) == 0)
      lastbit--;
    return 32*lastint+lastbit+1;
  }

  public int cardinality() {
    int cardinality = 0;
    for (int i = 0; i < bits.length; i++) {
      int value = bits[i];
      for (int j = 0; j < 32; j++)
        if ((value & (1 << j)) != 0)
          cardinality++;
    }
    return cardinality;
  }

  public void clear() {
    for (int i = 0; i < bits.length; i++)
      bits[i] = 0;
  }

  public boolean get(int index) {
    if (index < 0)
      throw new IndexOutOfBoundsException(index);
    if (index >= 32*bits.length)
      return false;
    return (bits[index/32] & (1 << (index%32))) != 0;
  }

  public void set(int index) {
    if (index < 0)
      throw new IndexOutOfBoundsException(index);
    if (index >= 32*bits.length) {
      int[] tmp = bits;
      bits = new int[(index/32)+1];
      System.arraycopy(tmp, 0, bits, 0, tmp.length);
    }
    bits[index/32] |= (1 << (index%32));
  }

  public void set(int index, boolean value) {
    if (value)
      set(index);
    else
      clear(index);
  }

  public void clear(int index) {
    if (index < 0)
      throw new IndexOutOfBoundsException(index);
    if (index < 32*bits.length)
      bits[index/32] &= ~(1 << (index%32));
  }

  public void flip(int index) {
    if (index < 0)
      throw new IndexOutOfBoundsException(index);
    if (index >= 32*bits.length) {
      int[] tmp = bits;
      bits = new int[(index/32)+1];
      System.arraycopy(tmp, 0, bits, 0, tmp.length);
    }
    bits[index/32] ^= (1 << (index%32));
  }

  public BitSet get(int start, int end) {
    if (start < 0)
      throw new IndexOutOfBoundsException(start);
    if (end < start)
      throw new IndexOutOfBoundsException(end);
    if (end > 32*bits.length)
      end = 32*bits.length;
    int size = end-start;
    BitSet set = new BitSet(size);
    if (start%32 == 0)
      for (int i = 0; i < set.bits.length; i++)
        set.bits[i] = bits[start/32+i];
    else
      for (int i = 0; i < set.bits.length; i++)
        set.bits[i] = bits[start/32+i] >>> (start%32) | bits[start/32+i+1] << (32-(start%32));
    if (size%32 != 0)
      set.bits[set.bits.length-1] &= 0xFFFFFFFF >>> (32-(size%32));
    return set;
  }

  public void set(int start, int end) {
    if (start < 0)
      throw new IndexOutOfBoundsException(start);
    if (end < start)
      throw new IndexOutOfBoundsException(end);
    if (end > 32*bits.length) {
      int[] tmp = bits;
      bits = new int[(end+31)/32];
      System.arraycopy(tmp, 0, bits, 0, tmp.length);
    }
    int smask = 0xFFFFFFFF << (start%32);
    int emask = 0xFFFFFFFF >>> (31-((end-1)%32));
    if (start/32 == (end-1)/32)
      bits[start/32] |= smask & emask;
    else {
      bits[start/32] |= smask;
      for (int i = (start/32)+1; i < (end-1)/32; i++)
        bits[i] = 0xFFFFFFFF;
      bits[(end-1)/32] |= emask;
    }
  }

  public void set(int start, int end, boolean value) {
    if (value)
      set(start, end);
    else
      clear(start, end);
  }

  public void clear(int start, int end) {
    if (start < 0)
      throw new IndexOutOfBoundsException(start);
    if (end < start)
      throw new IndexOutOfBoundsException(end);
    if (end > 32*bits.length)
      end = 32*bits.length;
    int smask = 0xFFFFFFFF << (start%32);
    int emask = 0xFFFFFFFF >>> (31-((end-1)%32));
    if (start/32 == (end-1)/32)
      bits[start/32] &= ~(smask & emask);
    else {
      bits[start/32] &= ~smask;
      for (int i = (start/32)+1; i < (end-1)/32; i++)
        bits[i] = 0;
      bits[(end-1)/32] &= ~emask;
    }
  }

  public void flip(int start, int end) {
    if (start < 0)
      throw new IndexOutOfBoundsException(start);
    if (end < start)
      throw new IndexOutOfBoundsException(end);
    if (end > 32*bits.length) {
      int[] tmp = bits;
      bits = new int[(end+31)/32];
      System.arraycopy(tmp, 0, bits, 0, tmp.length);
    }
    int smask = 0xFFFFFFFF << (start%32);
    int emask = 0xFFFFFFFF >>> (31-((end-1)%32));
    if (start/32 == (end-1)/32)
      bits[start/32] ^= smask & emask;
    else {
      bits[start/32] ^= smask;
      for (int i = (start/32)+1; i < (end-1)/32; i++)
        bits[i] ^= 0xFFFFFFFF;
      bits[(end-1)/32] ^= emask;
    }
  }

  public int nextSetBit(int index) {
    if (index < 0)
      throw new IndexOutOfBoundsException(index);
    for (int i = index/32; i < bits.length; i++) {
      int value = bits[i];
      if (value != 0)
        for (int j = i == index/32 ? index%32 : 0; j < 32; j++)
          if ((value & (1 << j)) != 0)
            return 32*i+j;
    }
    return -1;
  }

  public int nextClearBit(int index) {
    if (index < 0)
      throw new IndexOutOfBoundsException(index);
    for (int i = index/32; i < bits.length; i++) {
      int value = bits[i];
      if (value != 0xFFFFFFFF)
        for (int j = i == index/32 ? index%32 : 0; j < 32; j++)
          if ((value & (1 << j)) == 0)
            return 32*i+j;
    }
    return 32*bits.length;
  }

  public boolean intersects(BitSet set) {
    int minlen = bits.length;
    if (set.bits.length < minlen)
      minlen = set.bits.length;
    for (int i = 0; i < minlen; i++)
      if ((bits[i] & set.bits[i]) != 0)
        return true;
    return false;
  }

  public void and(BitSet set) {
    int minlen = bits.length;
    if (set.bits.length < minlen)
      minlen = set.bits.length;
    for (int i = 0; i < minlen; i++)
      bits[i] &= set.bits[i];
    for (int i = minlen; i < bits.length; i++)
      bits[i] = 0;
  }

  public void andNot(BitSet set) {
    int minlen = bits.length;
    if (set.bits.length < minlen)
      minlen = set.bits.length;
    for (int i = 0; i < minlen; i++)
      bits[i] &= ~set.bits[i];
  }

  public void or(BitSet set) {
    if (bits.length < set.bits.length) {
      int[] tmp = bits;
      bits = new int[set.bits.length];
      System.arraycopy(tmp, 0, bits, 0, tmp.length);
    }
    for (int i = 0; i < set.bits.length; i++)
      bits[i] |= set.bits[i];
  }

  public void xor(BitSet set) {
    if (bits.length < set.bits.length) {
      int[] tmp = bits;
      bits = new int[set.bits.length];
      System.arraycopy(tmp, 0, bits, 0, tmp.length);
    }
    for (int i = 0; i < set.bits.length; i++)
      bits[i] ^= set.bits[i];
  }

  public Object clone() {
    try {
      BitSet set = (BitSet)super.clone();
      set.bits = (int[])bits.clone();
      return set;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }

  public int hashCode() {
    int hashCode = 0;
    for (int i = 0; i < bits.length; i++)
      hashCode += bits[i];
    return hashCode;
  }

  public boolean equals(Object object) {
    if (object instanceof BitSet) {
      BitSet set = (BitSet)object;
      int minlen = bits.length;
      if (set.bits.length < minlen)
        minlen = set.bits.length;
      for (int i = 0; i < minlen; i++)
        if (bits[i] != set.bits[i])
          return false;
      for (int i = minlen; i < bits.length; i++)
        if (bits[i] != 0)
          return false;
      for (int i = minlen; i < set.bits.length; i++)
        if (set.bits[i] != 0)
          return false;
      return true;
    }
    return false;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append('{');
    for (int i = nextSetBit(0); i != -1; i = nextSetBit(i+1)) {
      sb.append(i);
      sb.append(", ");
    }
    int length = sb.length();
    if (length > 1)
      sb.setLength(length-2);
    sb.append('}');
    return sb.toString();
  }

}

