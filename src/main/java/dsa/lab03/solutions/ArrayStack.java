package dsa.lab03.solutions;

import dsa.lab03.base.Stack;
import dsa.lib.Iterators;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * An array stack.
 * <p>
 * Implements the stack interface by using a dynamic array.
 *
 * @param <Item> the item type
 */
public class ArrayStack<Item>
  implements Stack<Item>
{
  private DynamicArray<Item> items;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty array stack.
   */
  public ArrayStack()
  {
    this.items = new DynamicArray<>();
  }

  /**
   * Construct an array stack containing the given items.
   *
   * @param items the items
   */
  public ArrayStack(Iterable<Item> items)
  {
    this.items = new DynamicArray<>(Iterators.reversed(items));
  }

  /**
   * Construct an array stack containing the given items
   * more efficiently than {@link #ArrayStack(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public ArrayStack(Iterable<Item> items, int size)
    throws IllegalArgumentException
  {
    this.items = new DynamicArray<>(Iterators.reversed(items, size), size);
  }

  /**
   * Construct an array stack containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public ArrayStack(Item... items)
  {
    this(Arrays.asList(items), items.length);
  }

  //</editor-fold>

  @Override
  public void push(Item item)
  {
    this.items.insertLast(item);
  }

  @Override
  public Item pop()
    throws NoSuchElementException
  {
    return this.items.removeLast();
  }

  @Override
  public Item top()
    throws NoSuchElementException
  {
    return this.items.last();
  }

  @Override
  public int size()
  {
    return this.items.size();
  }

  @Override
  public Iterable<Item> items()
  {
    return this.items.reversed();
  }
}
