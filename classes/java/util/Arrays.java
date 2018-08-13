/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

public class Arrays {

  public static int binarySearch(byte[] array, byte key) {
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      byte entry = array[middle];
      if (key < entry)
        end = middle;
      else if (entry < key)
        start = middle+1;
      else
        return middle;
    }
    return -start-1;
  }

  public static int binarySearch(char[] array, char key) {
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      char entry = array[middle];
      if (key < entry)
        end = middle;
      else if (entry < key)
        start = middle+1;
      else
        return middle;
    }
    return -start-1;
  }

  public static int binarySearch(short[] array, short key) {
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      short entry = array[middle];
      if (key < entry)
        end = middle;
      else if (entry < key)
        start = middle+1;
      else
        return middle;
    }
    return -start-1;
  }

  public static int binarySearch(int[] array, int key) {
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      int entry = array[middle];
      if (key < entry)
        end = middle;
      else if (entry < key)
        start = middle+1;
      else
        return middle;
    }
    return -start-1;
  }

  public static int binarySearch(long[] array, long key) {
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      long entry = array[middle];
      if (key < entry)
        end = middle;
      else if (entry < key)
        start = middle+1;
      else
        return middle;
    }
    return -start-1;
  }

  public static int binarySearch(float[] array, float key) {
    int keyBits = Float.floatToIntBits(key);
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      float entry = array[middle];
      if (key < entry)
        end = middle;
      else if (entry < key)
        start = middle+1;
      else {
        int entryBits = Float.floatToIntBits(entry);
        if (keyBits < entryBits)
          end = middle;
        else if (entryBits < keyBits)
          start = middle+1;
        else
          return middle;
      }
    }
    return -start-1;
  }

  public static int binarySearch(double[] array, double key) {
    long keyBits = Double.doubleToLongBits(key);
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      double entry = array[middle];
      if (key < entry)
        end = middle;
      else if (entry < key)
        start = middle+1;
      else {
        long entryBits = Double.doubleToLongBits(entry);
        if (keyBits < entryBits)
          end = middle;
        else if (entryBits < keyBits)
          start = middle+1;
        else
          return middle;
      }
    }
    return -start-1;
  }

  public static int binarySearch(Object[] array, Object key) {
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      int result = ((Comparable)array[middle]).compareTo(key);
      if (result > 0)
        end = middle;
      else if (result < 0)
        start = middle+1;
      else
        return middle;
    }
    return -start-1;
  }

  public static int binarySearch(Object[] array, Object key, Comparator comparator) {
    int start = 0;
    int end = array.length;
    while (start < end) {
      int middle = (start+end)/2;
      int result = comparator.compare(array[middle], key);
      if (result > 0)
        end = middle;
      else if (result < 0)
        start = middle+1;
      else
        return middle;
    }
    return -start-1;
  }

  public static void fill(boolean[] array, boolean value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(byte[] array, byte value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(char[] array, char value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(short[] array, short value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(int[] array, int value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(long[] array, long value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(float[] array, float value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(double[] array, double value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(Object[] array, Object value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(boolean[] array, int start, int end, boolean value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(byte[] array, int start, int end, byte value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(char[] array, int start, int end, char value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(short[] array, int start, int end, short value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(int[] array, int start, int end, int value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(long[] array, int start, int end, long value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(float[] array, int start, int end, float value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(double[] array, int start, int end, double value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void fill(Object[] array, int start, int end, Object value) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    for (int i = start; i < end; i++)
      array[i] = value;
  }

  public static void sort(byte[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(char[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(short[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(int[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(long[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(float[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(double[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(Object[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(Object[] array, Comparator comparator) {
    sort(array, 0, array.length, comparator);
  }

  public static void sort(byte[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      byte pivot = array[middle];
      int i = start, j = end-1;
      do {
        while (array[i] < pivot) i++;
        while (pivot < array[j]) j--;
        if (i <= j) {
          byte tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(char[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      char pivot = array[middle];
      int i = start, j = end-1;
      do {
        while (array[i] < pivot) i++;
        while (pivot < array[j]) j--;
        if (i <= j) {
          char tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(short[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      short pivot = array[middle];
      int i = start, j = end-1;
      do {
        while (array[i] < pivot) i++;
        while (pivot < array[j]) j--;
        if (i <= j) {
          short tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(int[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      int pivot = array[middle];
      int i = start, j = end-1;
      do {
        while (array[i] < pivot) i++;
        while (pivot < array[j]) j--;
        if (i <= j) {
          int tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(long[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      long pivot = array[middle];
      int i = start, j = end-1;
      do {
        while (array[i] < pivot) i++;
        while (pivot < array[j]) j--;
        if (i <= j) {
          long tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(float[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      float pivot = array[middle];
      int pivotBits = Float.floatToIntBits(pivot);
      int i = start, j = end-1;
      do {
        while (array[i] < pivot || (!(array[i] > pivot) && Float.floatToIntBits(array[i]) < pivotBits)) i++;
        while (pivot < array[j] || (!(pivot > array[j]) && pivotBits < Float.floatToIntBits(array[j]))) j--;
        if (i <= j) {
          float tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(double[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      double pivot = array[middle];
      long pivotBits = Double.doubleToLongBits(pivot);
      int i = start, j = end-1;
      do {
        while (array[i] < pivot || (!(array[i] > pivot) && Double.doubleToLongBits(array[i]) < pivotBits)) i++;
        while (pivot < array[j] || (!(pivot > array[j]) && pivotBits < Double.doubleToLongBits(array[j]))) j--;
        if (i <= j) {
          double tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(Object[] array, int start, int end) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      Comparable pivot = (Comparable)array[middle];
      int i = start, j = end-1;
      do {
        while (pivot.compareTo(array[i]) > 0) i++;
        while (pivot.compareTo(array[j]) < 0) j--;
        if (i <= j) {
          Object tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1);
      if (i < end-1) sort(array, i, end);
    }
  }

  public static void sort(Object[] array, int start, int end, Comparator comparator) {
    if (start < 0 || start > array.length)
      throw new ArrayIndexOutOfBoundsException(start);
    if (end < start || end > array.length)
      throw new ArrayIndexOutOfBoundsException(end);
    int length = end-start;
    if (length > 1) {
      int middle = (start+end)/2;
      Object pivot = array[middle];
      int i = start, j = end-1;
      do {
        while (comparator.compare(pivot, array[i]) > 0) i++;
        while (comparator.compare(pivot, array[j]) < 0) j--;
        if (i <= j) {
          Object tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++; j--;
        }
      } while (i <= j);
      if (start < j) sort(array, start, j+1, comparator);
      if (i < end-1) sort(array, i, end, comparator);
    }
  }

  public static boolean equals(boolean[] one, boolean[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (one[i] != another[i])
        return false;
    return true;
  }

  public static boolean equals(byte[] one, byte[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (one[i] != another[i])
        return false;
    return true;
  }

  public static boolean equals(char[] one, char[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (one[i] != another[i])
        return false;
    return true;
  }

  public static boolean equals(short[] one, short[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (one[i] != another[i])
        return false;
    return true;
  }

  public static boolean equals(int[] one, int[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (one[i] != another[i])
        return false;
    return true;
  }

  public static boolean equals(long[] one, long[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (one[i] != another[i])
        return false;
    return true;
  }

  public static boolean equals(float[] one, float[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (Float.floatToIntBits(one[i]) != Float.floatToIntBits(another[i]))
        return false;
    return true;
  }

  public static boolean equals(double[] one, double[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (Double.doubleToLongBits(one[i]) != Double.doubleToLongBits(another[i]))
        return false;
    return true;
  }

  public static boolean equals(Object[] one, Object[] another) {
    if (one == another)
      return true;
    if (one == null || another == null)
      return false;
    if (one.length != another.length)
      return false;
    for (int i = 0; i < one.length; i++)
      if (!HashMap.equals(one[i], another[i]))
        return false;
    return true;
  }

  public static List asList(Object[] array) {
    return new ArrayList(array);
  }

  private Arrays() { }

}

