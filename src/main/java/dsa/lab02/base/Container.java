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
  default boolean contains(Item item)
  {
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
    return this.items().iterator();
  }

  //</editor-fold>
}
