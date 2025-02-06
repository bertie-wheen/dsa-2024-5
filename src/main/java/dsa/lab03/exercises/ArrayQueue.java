package dsa.lab03.exercises;

import dsa.lab03.base.Queue;
import dsa.lib.TODO;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * An array queue.
 * <p>
 * Implements the queue interface by using a circular dynamic array.
 *
 * @param <Item> the item type
 */
public class ArrayQueue<Item>
  implements Queue<Item>
{
  private CircularDynamicArray<Item> items;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty array queue.
   */
  public ArrayQueue()
  {
    this.items = new CircularDynamicArray<>();
  }

  /**
   * Construct an array queue containing the given items.
   *
   * @param items the items
   */
  public ArrayQueue(Iterable<Item> items)
  {
    this.items = new CircularDynamicArray<>(items);
  }

  /**
   * Construct an array queue containing the given items
   * more efficiently than {@link #ArrayQueue(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public ArrayQueue(Iterable<Item> items, int size)
    throws IllegalArgumentException
  {
    this.items = new CircularDynamicArray<>(items, size);
  }

  /**
   * Construct an array queue containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public ArrayQueue(Item... items)
  {
    this(Arrays.asList(items), items.length);
  }

  //</editor-fold>

  @Override
  public void enqueue(Item item)
  {
    // TODO: Implement ArrayQueue.enqueue(Item item)
    throw new TODO();
  }

  @Override
  public Item dequeue()
    throws NoSuchElementException
  {
    // TODO: Implement ArrayQueue.dequeue()
    throw new TODO();
  }

  @Override
  public Item front()
    throws NoSuchElementException
  {
    // TODO: Implement ArrayQueue.front()
    throw new TODO();
  }

  @Override
  public int size()
  {
    return this.items.size();
  }

  @Override
  public Iterable<Item> items()
  {
    return this.items.items();
  }
}
