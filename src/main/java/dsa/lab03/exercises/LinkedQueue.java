package dsa.lab03.exercises;

import dsa.lab02.solutions.SinglyLinkedList;
import dsa.lab03.base.Queue;
import dsa.lib.TODO;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A linked queue.
 * <p>
 * Implements the queue interface by using a singly-linked list.
 *
 * @param <Item> the item type
 */
public class LinkedQueue<Item>
  implements Queue<Item>
{
  private SinglyLinkedList<Item> items;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty linked queue.
   */
  public LinkedQueue()
  {
    this.items = new SinglyLinkedList<>();
  }

  /**
   * Construct a linked queue containing the given items.
   *
   * @param items the items
   */
  public LinkedQueue(Iterable<Item> items)
  {
    this.items = new SinglyLinkedList<>(items);
  }

  /**
   * Construct a linked queue containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public LinkedQueue(Item... items)
  {
    this(Arrays.asList(items));
  }

  //</editor-fold>

  @Override
  public void enqueue(Item item)
  {
    // TODO: Implement LinkedQueue.enqueue(Item item)
    throw new TODO();
  }

  @Override
  public Item dequeue()
    throws NoSuchElementException
  {
    // TODO: Implement LinkedQueue.dequeue()
    throw new TODO();
  }

  @Override
  public Item front()
    throws NoSuchElementException
  {
    // TODO: Implement LinkedQueue.front()
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
