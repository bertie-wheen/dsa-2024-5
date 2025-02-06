package dsa.lab03.solutions;

import dsa.lab02.solutions.SinglyLinkedList;
import dsa.lab03.base.Queue;

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
    this.items.insertLast(item);
  }

  @Override
  public Item dequeue()
    throws NoSuchElementException
  {
    return this.items.removeFirst();
  }

  @Override
  public Item front()
    throws NoSuchElementException
  {
    return this.items.first();
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
