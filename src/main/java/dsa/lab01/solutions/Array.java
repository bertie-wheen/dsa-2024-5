package dsa.lab01.solutions;

import dsa.lab01.base.Container;

import java.util.Objects;

/**
 * An array-based container.
 * <p>
 * Contains some number of items using an array.
 *
 * @param <Item> the item type
 */
public class Array<Item>
  implements Container<Item>
{
  private Item[] items;

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Construct an empty array.
   */
  @SuppressWarnings("unchecked")
  public Array()
  {
    this.items = (Item[]) new Object[0];
  }

  /**
   * Construct an array containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  @SuppressWarnings("unchecked")
  public Array(Item... items)
  {
    this.items = (Item[]) new Object[items.length];
    for (int i = 0; i < items.length; i++)
    {
      this.items[i] = items[i];
    }
  }
  //</editor-fold>

  @Override
  public int size()
  {
    return this.items.length;
  }

  @Override
  public boolean contains(Item item)
  {
    for (Item containedItem : this.items)
    {
      if (Objects.equals(item, containedItem))
      {
        return true;
      }
    }
    return false;
  }
}
