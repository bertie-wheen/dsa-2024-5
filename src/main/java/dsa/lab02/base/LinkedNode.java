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
  // NOTE: Some linked list implementations don't have any methods (at least not
  //       any insert/remove methods) on nodes, and instead only have
  //       (insert/remove) methods on the list(s). Such implementations usually
  //       don't store a pointer to the containing list on each of the nodes,
  //       and would thus not be able to implement this method. However, we're
  //       not programming an implementation like that here. (If you're taking
  //       a less object-oriented approach than we are here, perhaps because
  //       you're using a less object-oriented language than Java, it does have
  //       the advantage of being more memory efficient - not storing the list
  //       pointers reduces the space each node requires by 1/4 to 1/3 (if the
  //       nodes are doubly- or singly-linked, respectively), and since for
  //       large lists (i.e. with lots of nodes) the nodes dominate the list's
  //       overall space requirement, it can cut the overall space required by
  //       the list(s) by nearly 1/4 to 1/3.) (It's the same difference in space
  //       requirements as with singly- vs doubly-linked nodes/lists.)


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
  // NOTE: For doubly-linked nodes will be O(1).
  // NOTE: For singly-linked nodes that don't store a previous pointer, O(n),
  //       as we'll have to find the previous node by starting at the first node
  //       and repeatedly going to the next node until we do.


  /**
   * Get the next node. or {@code null} if this is the last.
   *
   * @return the successor
   */
  LinkedNode<Item> next();
  // NOTE: For doubly-linked nodes will be O(1).
  // NOTE: For the singly-linked nodes we'll implement, also O(1).
  // NOTE: A singly-linked variant we don't cover (because it's essentially
  //       equivalent to the one we do) instead stores a link to the previous
  //       node, but not to the next node, in which case this would be O(n).
  // NOTE: When we talk about singly-linked nodes/lists, we will always mean
  //       ones with a next link but no previous link, and will not consider the
  //       other variety.


  /**
   * Insert a node containing the given item
   * immediately before this one in the list.
   *
   * @param item the new previous item
   */
  void insertPrevious(Item item);
  // NOTE: For singly-linked, O(n); doubly-linked, O(1).


  /**
   * Insert a node containing the given item
   * immediately after this one in the list.
   *
   * @param item the new next item
   */
  void insertNext(Item item);
  // NOTE: For singly- and doubly-linked, O(1).


  /**
   * Remove the node from the list and return its item.
   *
   * @return the item
   */
  Item remove();
  // NOTE: For singly-linked, O(n); doubly-linked, O(1).


  /**
   * Remove the previous node from the list and return its item.
   *
   * @return the old previous item
   * @throws NoSuchElementException if there is no previous node
   *                                (i.e. this is the first)
   */
  Item removePrevious()
    throws NoSuchElementException;
  // NOTE: For singly-linked, O(n); doubly-linked, O(1).


  /**
   * Remove the next node from the list and return its item.
   *
   * @return the old next item
   * @throws NoSuchElementException if there is no next node
   *                                (i.e. this is the last)
   */
  Item removeNext()
    throws NoSuchElementException;
  // NOTE: For singly- and doubly-linked, O(1).


  /**
   * Check if it's the first in the list.
   *
   * @return whether there are no predecessors
   */
  default boolean isFirst()
  {
    // NOTE: Could alternatively implement in terms of previous(), but that
    //       may be O(n) and this is likely to be O(1).
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
    // NOTE: Similar comments to isFirst().
    // NOTE: Could instead implement isFirst() in terms of this, and this in
    //       terms of list().firstNode(), which would be equivalently good.
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
