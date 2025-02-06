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
  private Item[] items;
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
    Item[] items = (Item[]) new Object[capacity];
    for (int i = 0; i < this.size; i++)
    {
      items[i] = this.items[i];
    }
    this.items = items;
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
    for (int i = this.size; i > index; i--)
    {
      this.items[i] = this.items[i - 1];
    }
    this.items[index] = item;
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
    this.size--;
    for (int i = index; i < this.size; i++)
    {
      this.items[i] = this.items[i + 1];
    }
    if (this.size <= this.capacity() / 4)
    {
      this.resize(this.capacity() / 2);
    }
    return item;
  }
}
