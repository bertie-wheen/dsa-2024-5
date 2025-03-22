package dsa.lab01.base;

/**
 * A static sequence.
 * <p>
 * A container whose {@code n} items are in an order given by their indices,
 * where the first item is at index 0, the second at index 1, and so on, the
 * {@code n}th being at index {@code n}-1.
 *
 * @param <Item> the item type
 */
public interface StaticSequence<Item>
  extends Container<Item>
{

  /**
   * Get the item at the given index.
   *
   * @param index the index
   * @return the item that is at that index
   * @throws IndexOutOfBoundsException if {@code index} {@literal <} 0 or
   *                                   {@code index} {@literal >=} {@code n}
   *                                   (where {@code n} is the size)
   */
  Item get(int index)
    throws IndexOutOfBoundsException;


  /**
   * Set the item at the given index.
   * <p>
   * Replaces whatever item was there before.
   *
   * @param index the index
   * @param item  the new item that should now be at that index
   * @throws IndexOutOfBoundsException if {@code index} {@literal <} 0 or
   *                                   {@code index} {@literal >=} {@code n}
   *                                   (where {@code n} is the size)
   */
  void set(int index, Item item)
    throws IndexOutOfBoundsException;

}
