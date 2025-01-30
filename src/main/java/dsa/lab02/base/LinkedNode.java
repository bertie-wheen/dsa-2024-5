package dsa.lab02.base;

import java.util.NoSuchElementException;

/**
 * A node in a linked list.
 * <p>
 * Depending on the specific type, many operations may be O(1) or O({@code n})
 * (where {@code n} is the size of the containing list).
 * Nodes are only valid as long as they remain in a list -
 * if removed from that list, they should no longer be used.
 *
 * @param <Item> the item type
 */
public interface LinkedNode<Item>
{
  /**
   * Get the containing list.
   *
   * @return the list
   */
  LinkedList<Item> list();

  /**
   * Get the contained item.
   *
   * @return the item
   */
  Item item();

  /**
   * Set the contained item.
   * <p>
   * Replaces whatever was contained before.
   *
   * @param item the new item
   */
  void setItem(Item item);

  /**
   * Get the previous node. or {@code null} if this is the first.
   *
   * @return the predecessor
   */
  LinkedNode<Item> previous();

  /**
   * Get the next node. or {@code null} if this is the last.
   *
   * @return the successor
   */
  LinkedNode<Item> next();

  /**
   * Insert a node containing the given item
   * immediately before this one in the list.
   *
   * @param item the new previous item
   */
  void insertPrevious(Item item);

  /**
   * Insert a node containing the given item
   * immediately after this one in the list.
   *
   * @param item the new next item
   */
  void insertNext(Item item);

  /**
   * Remove the node from the list and return its item.
   *
   * @return the item
   */
  Item remove();

  /**
   * Remove the previous node from the list and return its item.
   *
   * @return the old previous item
   * @throws NoSuchElementException if there is no previous node
   *                                (i.e. this is the first)
   */
  Item removePrevious()
    throws NoSuchElementException;

  /**
   * Remove the next node from the list and return its item.
   *
   * @return the old next item
   * @throws NoSuchElementException if there is no next node
   *                                (i.e. this is the last)
   */
  Item removeNext()
    throws NoSuchElementException;

  /**
   * Check if it's the first in the list.
   *
   * @return whether there are no predecessors
   */
  default boolean isFirst()
  {
    return this.list().firstNode() == this;
  }

  /**
   * Check if it's the last in the list.
   *
   * @return whether there are no successors
   */
  default boolean isLast()
  {
    return this.list().lastNode() == this;
  }

  /**
   * Check if it has a previous node.
   *
   * @return whether it's not the first
   */
  default boolean hasPrevious()
  {
    return !this.isFirst();
  }

  /**
   * Check if it has a next node.
   *
   * @return whether it's not the last
   */
  default boolean hasNext()
  {
    return !this.isLast();
  }
}
