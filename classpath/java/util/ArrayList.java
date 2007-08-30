package java.util;

public class ArrayList<T> implements List<T> {
  private static final int MinimumCapacity = 16;

  private Object[] array;
  private int size;

  public ArrayList(int capacity) {
    resize(capacity);
  }

  public ArrayList() {
    this(0);
  }
  
  private void grow() {
    if (array == null || size >= array.length) {
      resize(array == null ? MinimumCapacity : array.length * 2);
    }
  }

  private void shrink() {
    if (array.length / 2 >= MinimumCapacity && size <= array.length / 3) {
      resize(array.length / 2);
    } else if (size == 0) {
      resize(0);
    }
  }

  private void resize(int capacity) {
    Object[] newArray = null;
    if (capacity != 0) {
      if (array != null && array.length == capacity) {
        return;
      }

      newArray = new Object[capacity];
      if (array != null) {
        System.arraycopy(array, 0, newArray, 0, size);
      }
    }
    array = newArray;
  }

  private static boolean equal(Object a, Object b) {
    return (a == null && b == null) || (a != null && a.equals(b));
  }

  public int size() {
    return size;
  }

  public boolean contains(T element) {
    for (int i = 0; i < size; ++i) {
      if (equal(element, array[i])) {
        return true;
      }
    }
    return false;
  }

  public boolean add(T element) {
    ++ size;
    grow();
    array[size - 1] = element;
    return true;
  }

  public int indexOf(T element) {
    for (int i = 0; i < size; ++i) {
      if (equal(element, array[i])) {
        return i;
      }
    }
    return -1;
  }

  public int lastIndexOf(T element) {
    for (int i = size; i >= 0; --i) {
      if (equal(element, array[i])) {
        return i;
      }
    }
    return -1;
  }

  public T get(int index) {
    if (index >= 0 && index < size) {
      return (T) array[index];
    } else {
      throw new IndexOutOfBoundsException(index + " not in [0, " + size + ")");
    }    
  }

  public T remove(int index) {
    T v = get(index);

    if (index == size - 1) {
      array[index] = null;
    } else {
      System.arraycopy(array, index + 1, array, index, size - index);
    }

    -- size;
    shrink();

    return v;
  }

  public boolean remove(T element) {
    for (int i = 0; i < size; ++i) {
      if (equal(element, array[i])) {
        remove(i);
        return true;
      }
    }
    return false;
  }

  public void clear() {
    array = null;
    size = 0;
  }

  public Iterator<T> iterator() {
    return new Collections.ArrayListIterator(this);
  }
}
