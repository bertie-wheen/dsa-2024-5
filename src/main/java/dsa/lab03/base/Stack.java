package dsa.lab03.base;

import dsa.lab02.base.Container;

import java.util.NoSuchElementException;

/**
 * A stack.
 * <p>
 * A LIFO (Last-In, First-Out) structure where items are
 * added (pushed) to and removed (popped) from the top.
 *
 * @param <Item> the item type
 */
public interface Stack<Item>
  extends Container<Item>
{

  // NOTE: Some people will include additional methods/operations, but here we
  //       include only the core operations that define the essence of a stack.


  /**
   * Push (insert) the given item onto the top.
   *
   * @param item the new top item
   */
  void push(Item item);


  /**
   * Pop (remove and return) the given item from the top.
   *
   * @return the old top item
   * @throws NoSuchElementException if there's no top item to remove
   *                                (i.e. if this is empty)
   */
  Item pop()
    throws NoSuchElementException;


  /**
   * Get the item on top.
   *
   * @return the top item
   * @throws NoSuchElementException if there's no top item to retrieve
   *                                (i.e. if this is empty)
   */
  Item top()
    throws NoSuchElementException;
  // NOTE: Also called peek.

}
