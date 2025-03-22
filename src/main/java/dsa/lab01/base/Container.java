package dsa.lab01.base;

/**
 * A container.
 * <p>
 * Something that contains some number of items,
 * not necessarily in any particular order.
 *
 * @param <Item> the item type
 */
public interface Container<Item>
{

  /**
   * Get the number of contained items.
   *
   * @return the size
   */
  int size();


  /**
   * Check if it's empty.
   *
   * @return whether there are no items
   */
  default boolean isEmpty()
  {
    return this.size() == 0;
  }


  /**
   * Check if the given item is equal to any of those contained.
   *
   * @param item the item to check for membership
   * @return whether such an item is contained
   */
  boolean contains(Item item);

}
