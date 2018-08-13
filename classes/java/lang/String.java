/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Comparator;
import java.util.Locale;
import java.util.WeakHashMap;

public class String implements CharSequence, Comparable, Serializable {

  private static final long serialVersionUID = -6849794470754667710L;

  private static final WeakHashMap interns = new WeakHashMap();

  public static final Comparator CASE_INSENSITIVE_ORDER = new Comparator() {
    
    public int compare(Object object, Object other) {
      return ((String)object).compareToIgnoreCase((String)other);
    }

  };
      
  public static String valueOf(Object object) {
    return object == null ? "null" : object.toString();
  }
  
  public static String valueOf(char[] array) {
    return copyValueOf(array);
  }

  public static String valueOf(char[] array, int start, int length) {
    return copyValueOf(array, start, length);
  }

  public static String copyValueOf(char[] array) {
    return new String(array);
  }

  public static String copyValueOf(char[] array, int start, int length) {
    return new String(array, start, length);
  }

  public static String valueOf(boolean bool) {
    return Boolean.toString(bool);
  }

  public static String valueOf(char shar) {
    return Character.toString(shar);
  }

  public static String valueOf(int ant) {
    return Integer.toString(ant);
  }

  public static String valueOf(long lung) {
    return Long.toString(lung);
  }
  
  public static String valueOf(float flaot) {
    return Float.toString(flaot);
  }

  public static String valueOf(double duoble) {
    return Double.toString(duoble);
  }

  static final class Substring extends String {

    private final int start;
    private final int length;

    Substring(char[] buffer, int start, int length) {
      super(length > 0 ? (Object)buffer : null);
      this.start = start;
      this.length = length;
    }

  }

  final char[] buffer;

  String(Object array) {
    buffer = (char[])array;
  }

  public String() {
    buffer = null;
  }
  
  public String(String string) {
    buffer = string.length() > 0 ? string.toCharArray() : null;
  }

  /** @deprecated */
  public String(byte[] array, int hibyte) {
    this(array, hibyte, 0, array.length);
  }

  /** @deprecated */
  public String(byte[] array, int hibyte, int start, int length) {
    if (start < 0 || start > array.length)
      throw new StringIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new StringIndexOutOfBoundsException(end);
    if (length == 0)
      buffer = null;
    else {
      buffer = new char[length];
      for (int i = start; i < end; i++)
        buffer[i-start] = (char)(hibyte << 8 | array[i] & 0xFF);
    }
  }

  public String(char[] array) {
    this(array, 0, array.length);
  }

  public String(char[] array, int start, int length) {
    if (start < 0 || start > array.length)
      throw new StringIndexOutOfBoundsException(start);
    int end = start+length;
    if (end < start || end > array.length)
      throw new StringIndexOutOfBoundsException(end);
    if (length == 0)
      buffer = null;
    else {
      buffer = new char[length];
      for (int i = start; i < end; i++)
        buffer[i-start] = array[i];
    }
  }

  public String(byte[] array) {
    this(array, 0, array.length);
  }
  
  public String(byte[] array, String encoding) throws UnsupportedEncodingException {
    this(array, 0, array.length, encoding);
  }
  
  public String(byte[] array, int start, int length) {
    ByteArrayInputStream in = new ByteArrayInputStream(array, start, length);
    int size = 0;
    char[] buffer = new char[length];
    try {
      InputStreamReader reader = new InputStreamReader(in);
      int read;
      while ((read = reader.read(buffer, size, buffer.length-size)) != -1) {
        size += read;
        if (size == buffer.length) {
          int shar = reader.read();
          if (shar == -1)
            break;
          char[] tmp = buffer;
          buffer = new char[2*buffer.length+1];
          System.arraycopy(tmp, 0, buffer, 0, tmp.length);
          buffer[size++] = (char)shar;
        }
      }
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
    if (buffer.length > size) {
      char[] tmp = buffer;
      buffer = new char[size];
      System.arraycopy(tmp, 0, buffer, 0, buffer.length);
    }
    if (buffer.length == 0)
      buffer = null;
    this.buffer = buffer;
  }

  public String(byte[] array, int start, int length, String encoding) throws UnsupportedEncodingException {
    ByteArrayInputStream in = new ByteArrayInputStream(array, start, length);
    int size = 0;
    char[] buffer = new char[length];
    try {
      InputStreamReader reader = new InputStreamReader(in, encoding);
      int read;
      while ((read = reader.read(buffer, size, buffer.length-size)) != -1) {
        size += read;
        if (size == buffer.length) {
          int shar = reader.read();
          if (shar == -1)
            break;
          char[] tmp = buffer;
          buffer = new char[2*buffer.length+1];
          System.arraycopy(tmp, 0, buffer, 0, tmp.length);
          buffer[size++] = (char)shar;
        }
      }
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
    if (buffer.length > size) {
      char[] tmp = buffer;
      buffer = new char[size];
      System.arraycopy(tmp, 0, buffer, 0, buffer.length);
    }
    if (buffer.length == 0)
      buffer = null;
    this.buffer = buffer;
  }

  public String(StringBuffer sb) {
    this(sb.toString());
  }

  public final int length() {
    if (this instanceof Substring) {
      Substring substring = (Substring)this;
      return substring.length;
    }
    return buffer != null ? buffer.length : 0;
  }

  public final char charAt(int index) {
    if (index < 0 || index >= length())
      throw new StringIndexOutOfBoundsException(index);
    if (this instanceof Substring) {
      Substring substring = (Substring)this;
      return substring.buffer[substring.start+index];
    }
    return buffer[index];
  }

  public final CharSequence subSequence(int start, int end) {
    return substring(start, end);
  }

  public final void getChars(int start, int end, char[] array, int offset) {
    int length = length();
    if (start < 0 || start > length)
      throw new StringIndexOutOfBoundsException(start);
    if (end < start || end > length)
      throw new StringIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[offset+i-start] = charAt(i);
  }

  public final byte[] getBytes() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      OutputStreamWriter writer = new OutputStreamWriter(out);
      writer.write(this);
      writer.flush();
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
    return out.toByteArray();
  }

  public final byte[] getBytes(String encoding) throws UnsupportedEncodingException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      OutputStreamWriter writer = new OutputStreamWriter(out, encoding);
      writer.write(this);
      writer.flush();
    } catch (IOException e) {
      throw new InternalError(e.getMessage());
    }
    return out.toByteArray();
  }

  /** @deprecated */
  public void getBytes(int start, int end, byte[] array, int offset) {
    int length = length();
    if (start < 0 || start > length)
      throw new StringIndexOutOfBoundsException(start);
    if (end < start || end > length)
      throw new StringIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[offset+i-start] = (byte)charAt(i);
  }

  public final int hashCode() {
    int value = 0;
    int length = length();
    for (int i = 0; i < length; i++)
      value = 31*value + charAt(i);
    return value;
  }
  
  public final boolean equals(Object object) {
    if (this == object)
      return true;
    if (object instanceof String) {
      String string = (String)object;
      int length = length();
      if (length != string.length())
        return false;
      for (int i = 0; i < length; i++)
        if (charAt(i) != string.charAt(i))
          return false;
      return true;
    }
    return false;
  }

  public final boolean equalsIgnoreCase(String string) {
    if (this == string)
      return true;
    if (string != null) {
      int length = length();
      if (length != string.length())
        return false;
      for (int i = 0; i < length; i++) {
        char chr = charAt(i);
        char stringChr = string.charAt(i);
        if (chr != stringChr &&
            Character.toLowerCase(chr) != Character.toLowerCase(stringChr) && 
            Character.toUpperCase(chr) != Character.toUpperCase(stringChr))
          return false;
      }
      return true;
    }
    return false;
  }

  public final boolean contentEquals(StringBuffer sb) {
    int length = length();
    if (length != sb.length())
      return false;
    char[] chars = new char[length];
    sb.getChars(0, length, chars, 0);
    for (int i = 0; i < length; i++)
      if (charAt(i) != chars[i])
        return false;
    return true;
  }

  public final int compareTo(String string) {
    int length = length();
    int stringLength = string.length();
    for (int i = 0; i < length && i < stringLength; i++) {
      char chr = charAt(i);
      char stringChr = string.charAt(i);
      if (chr != stringChr)
        return chr-stringChr;
    }
    return length-stringLength;
  }

  public final int compareTo(Object object) {
    return compareTo((String)object);
  }

  public final int compareToIgnoreCase(String string) {
    int length = length();
    int stringLength = string.length();
    for (int i = 0; i < length && i < stringLength; i++) {
      char chr = charAt(i);
      char stringChr = string.charAt(i);
      if (chr != stringChr && 
          Character.toLowerCase(chr) != Character.toLowerCase(stringChr) &&
          Character.toUpperCase(chr) != Character.toUpperCase(stringChr))
        return chr-stringChr;
    }
    return length-stringLength;
  }

  public final boolean regionMatches(int start, String string, int stringStart, int length) {
    return regionMatches(false, start, string, stringStart, length);
  }
  
  public final boolean regionMatches(boolean ignoreCase, int start, String string, int stringStart, int regionLength) {
    int length = length();
    int stringLength = string.length();
    if (start < 0 || start > length)
      return false;
    if (stringStart < 0 || stringStart > stringLength)
      return false;
    int end = start+regionLength;
    if (end < start || end > length)
      return false;
    int stringEnd = stringStart+regionLength;
    if (stringEnd < stringStart || stringEnd > stringLength)
      return false;
    for (int i = 0; i < regionLength; i++) {
      char chr = charAt(start+i);
      char stringChr = string.charAt(stringStart+i);
      if (chr != stringChr && (!ignoreCase || (
          Character.toLowerCase(chr) != Character.toLowerCase(stringChr) && 
          Character.toUpperCase(chr) != Character.toUpperCase(stringChr))))
        return false;
    }
    return true;
  }
  
  public final boolean startsWith(String string) {
    return startsWith(string, 0);
  }

  public final boolean startsWith(String string, int start) {
    int length = length();
    int stringLength = string.length();
    if (start < 0 || start > length-stringLength)
      return false;
    for (int i = start; i-start < stringLength && i < length; i++)
      if (string.charAt(i-start) != charAt(i))
        return false;
    return true;
  }
  
  public final boolean endsWith(String string) {
    return startsWith(string, length()-string.length());
  }

  public final int indexOf(int chr) {
    return indexOf(chr, 0);
  }

  public final int indexOf(int chr, int start) {
    if (start < 0)
      start = 0;
    int length = length();
    for (int i = start; i < length; i++)
      if (charAt(i) == chr)
        return i;
    return -1;
  }

  public final int lastIndexOf(int chr) {
    return lastIndexOf(chr, length()-1);
  }

  public final int lastIndexOf(int chr, int end) {
    int length = length();
    if (end >= length)
      end = length-1;
    for (int i = end; i >= 0; i--)
      if (charAt(i) == chr)
        return i;
    return -1;
  }

  public final int indexOf(String string) {
    return indexOf(string, 0);
  }

  public final int indexOf(String string, int start) {
    int length = length();
    int stringLength = string.length();
    if (start < 0)
      start = 0;
    else if (start > length)
      start = length;
    if (stringLength == 0)
      return start;
    int end = start+stringLength;
    while (end <= length) {
      boolean flag = false;
      for (int i = 1; i <= stringLength; i++)
        if (string.charAt(stringLength-i) != charAt(end-i)) {
          flag = true;
          break;
        }
      if (!flag)
        return end-stringLength;
      end += stringLength-(string.lastIndexOf(charAt(end-1), stringLength-2)+1);
    }
    return -1;
  }
  
  public final int lastIndexOf(String string) {
    return lastIndexOf(string, length()-1);
  }

  public final int lastIndexOf(String string, int end) {
    int length = length();
    int stringLength = string.length();
    end++;
    if (end < 0)
      end = 0;
    else if (end > length)
      end = length;
    if (stringLength == 0)
      return end;
    int start = end-stringLength;
    while (start >= 0) {
      boolean flag = false;
      for (int i = 0; i < stringLength; i++)
        if (string.charAt(i) != charAt(start+i)) {
          flag = true;
          break;
        }
      if (!flag)
        return start;
      int last = string.indexOf(charAt(start), 1);
      start -= last == -1 ? stringLength : last;
    }
    return -1;
  }
  
  public final String substring(int start) {
    return substring(start, length());
  }

  public final String substring(int start, int end) {
    int length = length();
    if (start < 0 || start > length)
      throw new StringIndexOutOfBoundsException(start);
    if (end < start || end > length)
      throw new StringIndexOutOfBoundsException(end);
    if (start == 0 && end == length)
      return this;
    if (this instanceof Substring) {
      Substring substring = (Substring)this;
      return new Substring(substring.buffer, substring.start+start, end-start);
    }
    return new Substring(buffer, start, end-start);
  }

  public final String concat(String string) {
    int stringLength = string.length();
    if (stringLength == 0)
      return this;
    int length = length();
    char[] buffer = new char[length+stringLength];
    for (int i = 0; i < length; i++)
      buffer[i] = charAt(i);
    for (int i = 0; i < stringLength; i++)
      buffer[length+i] = string.charAt(i);
    return new String((Object)buffer);
  }
  
  public final String replace(char oldChr, char newChr) {
    if (oldChr != newChr) {
      boolean flag = false;
      int length = length();
      for (int i = 0; i < length; i++)
        if (charAt(i) == oldChr) {
          flag = true;
          break;
        }
      if (flag) {
        char[] buffer = new char[length];
        for (int i = 0; i < length; i++) {
          char chr = charAt(i);
          buffer[i] = chr == oldChr ? newChr : chr;
        }
        return new String((Object)buffer);
      }
    }
    return this;
  }

  public final String toLowerCase() {
    return toLowerCase(Locale.getDefault());
  }

  public final String toLowerCase(Locale locale) {
    boolean flag = false;
    int length = length();
    for (int i = 0; i < length; i++) {
      char shar = charAt(i);
      if (shar != Character.toLowerCase(shar)) {
        flag = true;
        break;
      }
    }
    if (flag) {
      char[] buffer = new char[length];
      for (int i = 0; i < length; i++) {
        char shar = charAt(i);
        buffer[i] = Character.toLowerCase(shar);
      }
      return new String((Object)buffer);
    }
    return this;
  }

  public final String toUpperCase() {
    return toUpperCase(Locale.getDefault());
  }
  
  public final String toUpperCase(Locale locale) {
    boolean flag = false;
    int length = length();
    for (int i = 0; i < length; i++) {
      char shar = charAt(i);
      if (shar != Character.toUpperCase(shar)) {
        flag = true;
        break;
      }
    }
    if (flag) {
      char[] buffer = new char[length];
      for (int i = 0; i < length; i++) {
        char shar = charAt(i);
        buffer[i] = Character.toUpperCase(shar);
      }
      return new String((Object)buffer);
    }
    return this;
  }

  public final String trim() {
    int start = 0;
    int end = length();
    while (start < end && charAt(start) <= '\u0020')
      start++;
    while (end > start && charAt(end-1) <= '\u0020')
      end--;
    return substring(start, end);
  }
  
  public final char[] toCharArray() {
    int length = length();
    char[] array = new char[length];
    getChars(0, length, array, 0);
    return array;
  }
  
  public final String toString() {
    return this;
  }
  
  public final String intern() {
    synchronized (interns) {
      WeakReference ref = (WeakReference)interns.get(this);
      if (ref != null) {
        String intern = (String)ref.get();
        if (intern != null)
          return intern;
      }
      interns.put(this, new WeakReference(this));
    }
    return this;
  }

}

