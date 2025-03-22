package dsa.lab02.base;

import java.util.Iterator;
import java.util.Objects;

/**
 * A container.
 * <p>
 * Something that contains some number of items,
 * not necessarily in any particular order.
 *
 * @param <Item> the item type
 */
public interface Container<Item>
  extends Iterable<Item>
{

  /**
   * Get the number of contained items.
   *
   * @return the size
   */
  int size();
  // NOTE: size() could have a default implementation in terms of iteration,
  //       though we choose not to include that here.


  /**
   * Check if it's empty.
   *
   * @return whether there are no items
   */
  default boolean isEmpty()
  {
    // NOTE: We can implement this in terms of size(), though if size() isn't
    //       O(1) and is instead e.g. O(n), this may want to be overridden if an
    //       O(1) implementation is nonetheless possible.
    return this.size() == 0;
  }


  /**
   * Check if the given item is equal to any of those contained.
   *
   * @param item the item to check for membership
   * @return whether such an item is contained
   */
  default boolean contains(Item item)
  {
    // NOTE: This O(n) implementation is often about as good as is possible,
    //       though if items are stored sorted, may be overridden with an
    //       O(log(n)) implementation using binary search, and if items are
    //       stored by hashes, may be overridden with an O(1) expected
    //       implementation.
    for (Item containedItem : this)
    {
      if (Objects.equals(item, containedItem))
      {
        return true;
      }
    }
    return false;
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  /**
   * Get an iterable that yields each item once.
   *
   * @return an iterable over the items
   */
  Iterable<Item> items();


  @Override
  default Iterator<Item> iterator()
  {
    // NOTE: This implements the Iterable<Item> interface, and so we can write:
    //         for (Item item : this) { /* ... */ }
    return this.items().iterator();
  }


  //</editor-fold>

}
