/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.Serializable;

public final class StringBuffer implements CharSequence, Serializable {

  private int length;
  private char[] buffer;
  private int sharedLimit;

  public StringBuffer() {
    this(16);
  }
  
  public StringBuffer(int capacity) {
    if (capacity < 0)
      throw new IllegalArgumentException("Negative capacity");
    buffer = new char[capacity];
  }

  public StringBuffer(String string) {
    this(16+string.length());
    append(string);
  }

  public int capacity() {
    return buffer.length;
  }

  public int length() {
    return length;
  }

  public synchronized char charAt(int index) {
    if (index < 0 || index >= length)
      throw new StringIndexOutOfBoundsException(index);
    return buffer[index];
  }

  public CharSequence subSequence(int start, int end) {
    return substring(start, end);
  }

  public synchronized void getChars(int start, int end, char[] array, int offset) {
    if (start < 0 || start > length)
      throw new StringIndexOutOfBoundsException(start);
    if (end < start || end > length)
      throw new StringIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[offset+i-start] = buffer[i];
  }
  
  public synchronized void setLength(int length) {
    if (length < 0)
      throw new StringIndexOutOfBoundsException(length);
    if (length > this.length)
      ensureCapacity(length);
    else if (length < this.length) {
      ensureNotShared(length);
      for (int i = length; i < this.length; i++)
        buffer[i] = '\u0000';
    }
    this.length = length;
  }

  public synchronized void ensureCapacity(int capacity) {
    if (capacity > buffer.length) {
      int minimum = 2*buffer.length+2;
      if (capacity < minimum)
        capacity = minimum;
      char[] buffer = new char[capacity];
      for (int i = 0; i < length; i++)
        buffer[i] = this.buffer[i];
      this.buffer = buffer;
      sharedLimit = 0;
    }
  }

  private void ensureNotShared(int index) {
    if (index < sharedLimit) {
      buffer = (char[])buffer.clone();
      sharedLimit = 0;
    }
  }

  public synchronized void setCharAt(int index, char chr) {
    if (index < 0 || index >= length)
      throw new StringIndexOutOfBoundsException(index);
    ensureNotShared(index);
    buffer[index] = chr;
  }
  
  public StringBuffer append(Object object) {
    return append(String.valueOf(object));
  }

  public synchronized StringBuffer append(String string) {
    if (string == null)
      string = "null";
    int stringLength = string.length();
    int newLength = length+stringLength;
    ensureCapacity(newLength);
    string.getChars(0, stringLength, buffer, length);
    length = newLength;
    return this;
  }

  public synchronized StringBuffer append(StringBuffer sb) {
    if (sb == null)
      sb = new StringBuffer("null");
    int newLength = length+sb.length;
    ensureCapacity(newLength);
    System.arraycopy(sb.buffer, 0, buffer, length, sb.length);
    length = newLength;
    return this;
  }

  public StringBuffer append(char[] array) {
    return append(array, 0, array.length);
  }

  public synchronized StringBuffer append(char[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new StringIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new StringIndexOutOfBoundsException(end);
    int newLength = this.length+length; 
    ensureCapacity(newLength);
    for (int i = start; i < end; i++)
      buffer[this.length+i-start] = array[i];
    this.length = newLength;
    return this;
  }
  
  public StringBuffer append(boolean bool) {
    return append(String.valueOf(bool));
  }

  public synchronized StringBuffer append(char chr) {
    int newLength = length+1;
    ensureCapacity(newLength);
    buffer[length] = chr;
    length = newLength;
    return this;
  }
  
  public StringBuffer append(int ant) {
    return append(String.valueOf(ant));
  }
  
  public StringBuffer append(long lung) {
    return append(String.valueOf(lung));
  }
  
  public StringBuffer append(float flaot) {
    return append(String.valueOf(flaot));
  }
  
  public StringBuffer append(double duoble) {
    return append(String.valueOf(duoble));
  }
  
  public StringBuffer insert(int index, Object object) {
    return insert(index, String.valueOf(object));
  }

  public synchronized StringBuffer insert(int index, String string) {
    if (index < 0 || index > length)
      throw new StringIndexOutOfBoundsException(index);
    if (string == null)
      string = "null";
    int stringLength = string.length();
    ensureNotShared(index);
    int newLength = length+stringLength; 
    ensureCapacity(newLength);
    for (int i = length-1; i >= index; i--)
      buffer[i+stringLength] = buffer[i];
    string.getChars(0, stringLength, buffer, index);
    length = newLength;
    return this;
  }

  public StringBuffer insert(int index, char[] array) {
    return insert(index, array, 0, array.length);
  }

  public synchronized StringBuffer insert(int index, char[] array, int start, int length) {
    if (index < 0 || index > this.length)
      throw new StringIndexOutOfBoundsException(index);
    if (start < 0 || start > array.length)
      throw new StringIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new StringIndexOutOfBoundsException(end);
    ensureNotShared(index);
    int newLength = this.length+length;
    ensureCapacity(newLength);
    for (int i = this.length-1; i >= index; i--)
      buffer[i+length] = buffer[i];
    for (int i = start; i < end; i++)
      buffer[index+i-start] = array[i];
    this.length = newLength;
    return this;
  }

  public StringBuffer insert(int index, boolean bool) {
    return insert(index, String.valueOf(bool));
  }

  public synchronized StringBuffer insert(int index, char chr) {
    if (index < 0 || index > length)
      throw new StringIndexOutOfBoundsException(index);
    ensureNotShared(index);
    int newLength = length+1;
    ensureCapacity(newLength);
    for (int i = length-1; i >= index; i--)
      buffer[i+1] = buffer[i];
    buffer[index] = chr;
    length = newLength;
    return this;
  }

  public StringBuffer insert(int index, int ant) {
    return insert(index, String.valueOf(ant));
  }

  public StringBuffer insert(int index, long lung) {
    return insert(index, String.valueOf(lung));
  }

  public StringBuffer insert(int index, float flaot) {
    return insert(index, String.valueOf(flaot));
  }

  public StringBuffer insert(int index, double duoble) {
    return insert(index, String.valueOf(duoble));
  }
  
  public synchronized StringBuffer delete(int start, int end) {
    if (start < 0 || start > length)
      throw new StringIndexOutOfBoundsException(start);
    if (end > length)
      end = length;
    if (end < start)
      throw new StringIndexOutOfBoundsException(end);
    if (end > start) {
      ensureNotShared(start);
      int shift = start-end;
      int newLength = length+shift;
      for (int i = end; i < length; i++)
        buffer[i+shift] = buffer[i];
      for (int i = newLength; i < length; i++)
        buffer[i] = '\u0000';
      length = newLength;
    }                 
    return this;
  }
  
  public StringBuffer deleteCharAt(int index) {
    return delete(index, index+1);
  }
  
  public synchronized StringBuffer replace(int start, int end, String string) {
    if (start < 0 || start > length)
      throw new StringIndexOutOfBoundsException(start);
    if (end > length)
      end = length;
    if (end < start)
      throw new StringIndexOutOfBoundsException(end);
    ensureNotShared(start);
    int stringLength = string.length();
    int shift = stringLength-(end-start);
    int newLength = length+shift;
    if (shift < 0) {
      for (int i = end; i < length; i++)
        buffer[i+shift] = buffer[i];
      for (int i = newLength; i < length; i++)
        buffer[i] = '\u0000';
    } else if (shift > 0) {
      ensureCapacity(newLength);
      for (int i = length-1; i >= end; i--)
        buffer[i+shift] = buffer[i];
    }
    string.getChars(0, stringLength, buffer, start);
    length = newLength;
    return this;
  }

  public synchronized StringBuffer reverse() {
    ensureNotShared(0);
    for (int i = 1; i <= length/2; i++) {
      char tmp = buffer[i-1];
      buffer[i-1] = buffer[length-i];
      buffer[length-i] = tmp;
    }
    return this;
  }
  
  public int indexOf(String string) {
    return indexOf(string, 0);
  }

  public synchronized int indexOf(String string, int start) {
    return new String.Substring(buffer, 0, length).indexOf(string, start);
  }
  
  public int lastIndexOf(String string) {
    return lastIndexOf(string, length-1);
  }

  public synchronized int lastIndexOf(String string, int end) {
    return new String.Substring(buffer, 0, length).lastIndexOf(string, end);
  }
  
  public String substring(int start) {
    return substring(start, length);
  }

  public synchronized String substring(int start, int end) {
    if (start < 0 || start > length)
      throw new StringIndexOutOfBoundsException(start);
    if (end < start || end > length)
      throw new StringIndexOutOfBoundsException(end);
    if (sharedLimit < end)
      sharedLimit = end;
    return new String.Substring(buffer, start, end);
  }
  
  public String toString() {
    return substring(0, length);
  }

}

