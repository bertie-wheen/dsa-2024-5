package dsa.lab02.solutions;

import dsa.lab02.base.DynamicSequence;

import java.util.Arrays;

/**
 * A static array.
 * <p>
 * A dynamic sequence implemented using a full array
 * (i.e. with as many items as slots).
 * <p>
 * Dynamic operations always reallocate a new array and are all O({@code n})
 * (where {@code n} is the size).
 *
 * @param <Item> the item type
 */
public class StaticArray<Item>
  implements DynamicSequence<Item>
{

  /** The backing array - the Java array this wraps and is implemented using. */
  private Item[] items;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty static array.
   */
  @SuppressWarnings("unchecked")
  public StaticArray()
  {
    this.items = (Item[]) new Object[0];
  }


  /**
   * Construct a static array containing the given items.
   *
   * @param items the items
   */
  public StaticArray(Iterable<Item> items)
  {
    this();
    for (Item item : items)
    {
      this.insertLast(item);
    }
  }


  /**
   * Construct a static array containing the given items
   * more efficiently than {@link #StaticArray(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  @SuppressWarnings("unchecked")
  public StaticArray(Iterable<Item> items, int size)
    throws IllegalArgumentException
  {
    if (size < 0)
    {
      throw new IllegalArgumentException();
    }
    this.items = (Item[]) new Object[size];
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
   * Construct a static array containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public StaticArray(Item... items)
  {
    this(Arrays.asList(items), items.length);
  }


  //</editor-fold>


  @Override
  public int size()
  {
    // NOTE: Java arrays already store their size, so we don't have to.
    return this.items.length;
  }


  @Override
  public Item get(int index)
    throws IndexOutOfBoundsException
  {
    // NOTE: Java arrays have O(1) random access, so this will be O(1).
    return this.items[index];
  }


  @Override
  public void set(int index, Item item)
    throws IndexOutOfBoundsException
  {
    // NOTE: Similar comments to get().
    this.items[index] = item;
  }


  @Override
  @SuppressWarnings("unchecked")
  public void insert(int index, Item item)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index > this.size())
    {
      throw new IndexOutOfBoundsException();
    }

    // NOTE: Save a reference to the old backing array.
    Item[] oldItems = this.items;

    // NOTE: Allocate a new (larger) backing array.
    this.items = (Item[]) new Object[this.size() + 1];

    // NOTE: Copy the earlier items across.
    for (int i = 0; i < index; i++)
    {
      this.items[i] = oldItems[i];
    }

    // NOTE: Insert the new item.
    this.items[index] = item;

    // NOTE: Copy the later items across.
    for (int i = index + 1; i < this.size(); i++)
    {
      this.items[i] = oldItems[i - 1];
    }
  }


  @Override
  @SuppressWarnings("unchecked")
  public Item remove(int index)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size())
    {
      throw new IndexOutOfBoundsException();
    }

    // NOTE: Save a reference to the old backing array.
    Item[] oldArray = this.items;

    // NOTE: Allocate a new (smaller) backing array.
    this.items = (Item[]) new Object[this.size() - 1];

    // NOTE: Copy the earlier items across.
    for (int i = 0; i < index; i++)
    {
      this.items[i] = oldArray[i];
    }

    // NOTE: Copy the later items across.
    for (int i = index; i < this.size(); i++)
    {
      this.items[i] = oldArray[i + 1];
    }

    // NOTE: Return the removed item.
    return oldArray[index];
  }

}
