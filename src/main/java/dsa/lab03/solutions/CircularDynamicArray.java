package dsa.lab03.solutions;

import dsa.lab02.base.DynamicSequence;

import java.util.Arrays;

/**
 * A circular dynamic array.
 * <p>
 * A dynamic sequence implemented using an array
 * with spare capacity and variable start index.
 * <p>
 * Dynamic operations only rarely reallocate a new array.
 * <p>
 * Improves on non-circular dynamic arrays' efficiencies with
 * {@link #insertFirst(Item)} and {@link #removeFirst()}
 * having (amortised) asymptotic complexity O(1).
 *
 * @param <Item> the item type
 */
public class CircularDynamicArray<Item>
  implements DynamicSequence<Item>
{

  /** The backing array. */
  private Item[] items;


  /** The index of the first item (or where it will be if currently empty). */
  private int start = 0;


  /** The number of contained items. */
  private int size;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty circular dynamic array.
   */
  @SuppressWarnings("unchecked")
  public CircularDynamicArray()
  {
    this.items = (Item[]) new Object[0];
    this.size = 0;
  }


  /**
   * Construct a circular dynamic array containing the given items.
   *
   * @param items the items
   */
  public CircularDynamicArray(Iterable<Item> items)
  {
    this();
    for (Item item : items)
    {
      this.insertLast(item);
    }
  }


  /**
   * Construct a circular dynamic array containing the given items
   * more efficiently than {@link #CircularDynamicArray(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  @SuppressWarnings("unchecked")
  public CircularDynamicArray(Iterable<Item> items, int size)
    throws IllegalArgumentException
  {
    if (size < 0)
    {
      throw new IllegalArgumentException();
    }
    this.items = (Item[]) new Object[size];
    this.size = size;
    int index = 0;
    for (Item item : items)
    {
      this.items[index++] = item;
    }
    if (index != size)
    {
      throw new IllegalArgumentException();
    }
  }


  /**
   * Construct a circular dynamic array containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public CircularDynamicArray(Item... items)
  {
    this(Arrays.asList(items), items.length);
  }


  //</editor-fold>


  @Override
  public int size()
  {
    return this.size;
  }


  /**
   * Get the maximum number of items that can be contained without reallocation.
   *
   * @return the capacity
   */
  public int capacity()
  {
    return this.items.length;
  }


  /**
   * Return the backing array index of the given logical index.
   * <p>
   * {@code index} is the external index users might call {@link #get} with.
   * The returned index is used internally in this class's implementation,
   * and would be used to look up the corresponding item in {@code this.items}.
   *
   * @param index the logical index
   * @return the backing array index
   */
  private int index(int index)
  {
    // NOTE: You could also write
    //         return Math.floorMod(this.start + index, this.capacity());
    // NOTE: In some languages negative arguments are handled differently,
    //       so in e.g. Python you could write
    //         (start + index) % capacity
    //       rather than having to add capacity to make sure it's non-negative.
    int capacity = this.capacity();
    return (capacity + this.start + index) % capacity;
  }


  @Override
  public Item get(int index)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }

    return this.items[this.index(index)];
  }


  @Override
  public void set(int index, Item item)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }

    this.items[this.index(index)] = item;
  }


  /**
   * Resize the backing array.
   * <p>
   * Allocates a new array with the given capacity, copies the items over to it,
   * and sets that as the backing array.
   * <p>
   * Assumes that {@code capacity} is at least {@code size()}.
   *
   * @param capacity the new capacity
   */
  @SuppressWarnings("unchecked")
  private void resize(int capacity)
  {
    // NOTE: Save a reference to the old backing array and start index.
    Item[] oldItems = this.items;
    int oldStart = this.start;

    // NOTE: Allocate a new array with the desired capacity and reset start.
    this.items = (Item[]) new Object[capacity];
    this.start = 0;

    // NOTE: Copy the items across.
    int oldCapacity = oldItems.length;
    for (int i = 0; i < this.size; i++)
    {
      this.items[i] = oldItems[(oldCapacity + oldStart + i) % oldCapacity];
    }
  }


  @Override
  public void insert(int index, Item item)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index > this.size)
    {
      throw new IndexOutOfBoundsException();
    }

    if (this.size == this.capacity())
    {
      this.resize(Math.max(1, 2 * this.capacity()));
    }

    // NOTE: We can do better than DynamicArray.insert if inserting near the
    //       start of the sequence, as - with the start index not necessarily
    //       being 0 - we can shift earlier items left instead of later items
    //       right. (This is a similar improvement to that in DoublyLinkedList.)
    if (index < this.size / 2)
    {
      for (int i = -1; i < index - 1; i++)
      {
        this.items[this.index(i)] = this.items[this.index(i + 1)];
      }
      this.start = this.index(-1);
    }
    else
    {
      for (int i = this.size; i > index; i--)
      {
        this.items[this.index(i)] = this.items[this.index(i - 1)];
      }
    }

    this.items[this.index(index)] = item;

    this.size++;
  }


  @Override
  public Item remove(int index)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }

    Item item = this.items[index];

    // NOTE: Similarly to insert(), we can do better than DynamicArray for low
    //       indices by shifting the earlier rather than later items.
    if (index < this.size / 2)
    {
      for (int i = index; i > 0; i--)
      {
        this.items[this.index(i)] = this.items[this.index(i - 1)];
      }
      this.items[this.index(0)] = null;
      this.start = this.index(+1);
    }
    else
    {
      for (int i = index; i < this.size - 1; i++)
      {
        this.items[this.index(i)] = this.items[this.index(i + 1)];
      }
      this.items[this.index(this.size - 1)] = null;
    }

    this.size--;

    if (this.size <= this.capacity() / 4)
    {
      this.resize(this.capacity() / 2);
    }

    return item;
  }

}
