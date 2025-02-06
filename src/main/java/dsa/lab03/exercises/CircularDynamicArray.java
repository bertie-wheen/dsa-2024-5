package dsa.lab03.exercises;

import dsa.lab02.base.DynamicSequence;
import dsa.lib.TODO;

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
  private Item[] items;
  private int start = 0;
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
    // TODO: Implement CircularDynamicArray.index(int index)
    // NOTE: You don't _have_ to implement this method, but you'll likely find
    //       it very useful for implementing the other methods in this class.
    throw new TODO();
  }

  @Override
  public Item get(int index)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }
    // TODO: Implement CircularDynamicArray.get(int index)
    throw new TODO();
  }

  @Override
  public void set(int index, Item item)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }
    // TODO: Implement CircularDynamicArray.set(int index, Item item)
    throw new TODO();
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
    int oldCapacity = this.capacity();
    Item[] items = (Item[]) new Object[capacity];
    for (int i = 0; i < this.size; i++)
    {
      items[i] = this.items[(oldCapacity + this.start + i) % oldCapacity];
    }
    this.items = items;
    this.start = 0;
  }

  @Override
  public void insert(int index, Item item)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index > this.size)
    {
      throw new IndexOutOfBoundsException();
    }
    // TODO: Implement CircularDynamicArray.insert(int index, Item item)
    throw new TODO();
  }

  @Override
  public Item remove(int index)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }
    // TODO: Implement CircularDynamicArray.remove(int index)
    throw new TODO();
  }
}
