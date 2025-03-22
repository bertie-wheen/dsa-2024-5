package dsa.lab03.base;

import dsa.lab02.base.Container;

import java.util.NoSuchElementException;

/**
 * A queue.
 * <p>
 * A FIFO (First-In, First-Out) structure where items are
 * added (enqueued) to the back and removed (dequeued) from the front.
 *
 * @param <Item> the item type
 */
public interface Queue<Item>
  extends Container<Item>
{

  // NOTE: Some people will include additional methods/operations, but here we
  //       include only the core operations that define the essence of a queue.


  /**
   * Enqueue (insert) the given item to the back.
   *
   * @param item the new back item
   */
  void enqueue(Item item);


  /**
   * Dequeue (remove and return) the given item from the front.
   *
   * @return the old front item
   * @throws NoSuchElementException if there's no first item to remove
   *                                (i.e. if this is empty)
   */
  Item dequeue()
    throws NoSuchElementException;


  /**
   * Get the item at the front.
   *
   * @return the front item
   * @throws NoSuchElementException if there's no first item to retrieve
   *                                (i.e. if this is empty)
   */
  Item front()
    throws NoSuchElementException;

}
