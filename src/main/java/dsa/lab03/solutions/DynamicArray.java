package dsa.lab03.solutions;

import dsa.lab02.base.DynamicSequence;

import java.util.Arrays;

/**
 * A dynamic array.
 * <p>
 * A dynamic sequence implemented using an array with spare capacity.
 * <p>
 * Dynamic operations only rarely reallocate a new array.
 * <p>
 * Improves on dynamic arrays' efficiencies with
 * {@link #insertLast(Item)} and {@link #removeLast()}
 * having (amortised) asymptotic complexity O(1).
 *
 * @param <Item> the item type
 */
public class DynamicArray<Item>
  implements DynamicSequence<Item>
{

  /** The backing array. */
  private Item[] items;


  /** The number of contained items. */
  private int size;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty dynamic array.
   */
  @SuppressWarnings("unchecked")
  public DynamicArray()
  {
    this.items = (Item[]) new Object[0];
    this.size = 0;
  }


  /**
   * Construct a dynamic array containing the given items.
   *
   * @param items the items
   */
  public DynamicArray(Iterable<Item> items)
  {
    this();
    for (Item item : items)
    {
      this.insertLast(item);
    }
  }


  /**
   * Construct a dynamic array containing the given items
   * more efficiently than {@link #DynamicArray(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  @SuppressWarnings("unchecked")
  public DynamicArray(Iterable<Item> items, int size)
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
   * Construct a dynamic array containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public DynamicArray(Item... items)
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


  @Override
  public Item get(int index)
    throws IndexOutOfBoundsException
  {
    if (index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }

    return this.items[index];
  }


  @Override
  public void set(int index, Item item)
    throws IndexOutOfBoundsException
  {
    if (index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }

    this.items[index] = item;
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
    // NOTE: Save a reference to the old backing array.
    Item[] oldItems = this.items;

    // NOTE: Allocate a new array with the desired capacity.
    this.items = (Item[]) new Object[capacity];

    // NOTE: Copy the items across.
    for (int i = 0; i < this.size; i++)
    {
      this.items[i] = oldItems[i];
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

    // NOTE: If the backing array is full, increase the capacity.
    if (this.size == this.capacity())
    {
      // NOTE: Usually we double it, but if it was 0, increase it to 1.
      this.resize(Math.max(1, 2 * this.capacity()));
    }

    // NOTE: Move the later items right one space.
    // NOTE: Iterate backwards to avoid overwriting them.
    for (int i = this.size; i > index; i--)
    {
      this.items[i] = this.items[i - 1];
    }

    // NOTE: Insert the new item.
    this.items[index] = item;

    // NOTE: Increment the size.
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

    // NOTE: Store a reference to the item we're removing, as we'll want to
    //       return it but will lose our reference to it.
    Item item = this.items[index];

    // NOTE: Decrement the size.
    this.size--;

    // NOTE: Move later items left one space.
    // NOTE: Removes this item.
    for (int i = index; i < this.size; i++)
    {
      this.items[i] = this.items[i + 1];
    }
    this.items[this.size] = null;

    // NOTE: If the backing array is under-full, decrease its capacity.
    if (this.size <= this.capacity() / 4)
    {
      // NOTE: Integer division rounds towards zero, so if the capacity was 1,
      //       will be 1 / 2 == 0.
      this.resize(this.capacity() / 2);
    }

    // NOTE: Return the removed item.
    return item;
  }

}
