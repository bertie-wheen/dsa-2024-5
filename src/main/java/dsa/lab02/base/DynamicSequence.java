package dsa.lab02.base;

import java.util.NoSuchElementException;

/**
 * A dynamic sequence.
 * <p>
 * A sequence that can have items inserted into or removed from it;
 * these operations change (increment or decrement) the size of the
 * dynamic sequence.
 *
 * @param <Item> the item type
 */
public interface DynamicSequence<Item>
  extends StaticSequence<Item>
{

  /**
   * Insert the given item at the given index.
   * <p>
   * Increments the size,
   * as well as the indices of all items that had the same index or higher.
   *
   * @param index the index
   * @param item  the new item
   * @throws IndexOutOfBoundsException if {@code index} {@literal <} 0 or
   *                                   {@code index} {@literal >} {@code n}
   *                                   (where {@code n} is the size)
   */
  void insert(int index, Item item)
    throws IndexOutOfBoundsException;


  /**
   * Remove and return the item at the given index.
   * <p>
   * Decrements the size,
   * as well as the indices of all items that had the same index or higher.
   *
   * @param index the index
   * @return the item that was at that index
   * @throws IndexOutOfBoundsException if {@code index} {@literal <} 0 or
   *                                   {@code index} {@literal >=} {@code n}
   *                                   (where {@code n} is the size)
   */
  Item remove(int index)
    throws IndexOutOfBoundsException;


  // NOTE: We could provide default implementations of get and set in terms of
  //       insert and remove, but they'd be so awful we'd realistically always
  //       want to override them. (Partly because of the relative efficiencies,
  //       but particularly simply because of our sensibilities.)


  /**
   * Insert the given item as the first.
   * <p>
   * Increments the size, as well as the indices of all the other items.
   *
   * @param item the new item that should now be at index 0
   */
  default void insertFirst(Item item)
  {
    // NOTE: Similar comments to StaticSequence.setFirst(item).
    this.insert(0, item);
  }


  /**
   * Insert the given item as the last.
   * <p>
   * Increments the size.
   *
   * @param item the new item that should now be at index {@code n}
   *             (where {@code n} is the old size)
   */
  default void insertLast(Item item)
  {
    // NOTE: Similar comments to StaticSequence.setLast(item).
    this.insert(this.size(), item);
  }


  /**
   * Remove and return the first item.
   * <p>
   * Decrements the size, as well as the indices of all the other items.
   *
   * @return the old item that was at index 0
   * @throws NoSuchElementException if there's no first item to remove
   *                                (i.e. this is empty)
   */
  default Item removeFirst()
    throws NoSuchElementException
  {
    // NOTE: Similar comments to StaticSequence.first().
    try
    {
      return this.remove(0);
    }
    catch (IndexOutOfBoundsException e)
    {
      throw new NoSuchElementException();
    }
  }


  /**
   * Remove and return the last item.
   * <p>
   * Decrements the size.
   *
   * @return the old item that was at index {@code n}-1
   *         (where {@code n} is the old size)
   * @throws NoSuchElementException if there's no last item to remove
   *                                (i.e. this is empty)
   */
  default Item removeLast()
    throws NoSuchElementException
  {
    // NOTE: Similar comments to StaticSequence.last().
    try
    {
      return this.remove(this.size() - 1);
    }
    catch (IndexOutOfBoundsException e)
    {
      throw new NoSuchElementException();
    }
  }

}
