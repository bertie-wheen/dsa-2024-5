package dsa.lab02.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

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


  /**
   * Get the first item.
   *
   * @return the item at index 0
   * @throws NoSuchElementException if there's no first item to retrieve
   *                                (i.e. this is empty)
   */
  default Item first()
    throws NoSuchElementException
  {
    // NOTE: This is likely about as good as is possible. If a better
    //       implementation is possible, get(0) should be implemented that way,
    //       as then both that and this will improve (and it saves overriding).
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    return this.get(0);
  }


  /**
   * Get the last item.
   *
   * @return the item at index {@code n}-1 (where {@code n} is the size)
   * @throws NoSuchElementException if there's no last item to retrieve
   *                                (i.e. this is empty)
   */
  default Item last()
    throws NoSuchElementException
  {
    // NOTE: Similar comments apply as with first(), but with get(size() - 1).
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    return this.get(this.size() - 1);
  }


  /**
   * Set the first item.
   * <p>
   * Replaces whatever item was there before.
   *
   * @param item the new first item
   * @throws NoSuchElementException if there's no first item to replace
   *                                (i.e. this is empty)
   */
  default void setFirst(Item item)
    throws NoSuchElementException
  {
    // NOTE: Similar comments as with first(), but with set(0, item).
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    this.set(0, item);
  }


  /**
   * Set the last item.
   * <p>
   * Replaces whatever item was there before.
   *
   * @param item the new last item
   * @throws NoSuchElementException if there's no last item to replace
   *                                (i.e. this is empty)
   */
  default void setLast(Item item)
    throws NoSuchElementException
  {
    // NOTE: Similar comments as with last(), setFirst()...
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    this.set(this.size() - 1, item);
  }


  /**
   * Swaps the two items at the given indices.
   *
   * @param indexA the first index
   * @param indexB the second index
   * @throws IndexOutOfBoundsException if either of {@code indexA} or
   *                                   {@code indexB} aren't valid indices
   */
  default void swap(int indexA, int indexB)
    throws IndexOutOfBoundsException
  {
    // NOTE: Useful for implementing swap-based sorting algorithms in terms of.
    // NOTE: Unlikely to be improvable by an override.
    Item temp = this.get(indexA);
    this.set(indexA, this.get(indexB));
    this.set(indexB, temp);
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  /**
   * Get a forward iterable that yields each item once.
   * <p>
   * The items are iterated over in first-to-last order (by index).
   *
   * @return an iterable over the items
   */
  @Override
  default Iterable<Item> items()
  {
    return () -> new ForwardIterator<>(this);
  }


  /**
   * Get a reverse iterable that yields each item once.
   * <p>
   * The items are iterated over in last-to-first order (by index).
   *
   * @return an iterable over the items
   */
  default Iterable<Item> reversed()
  {
    return () -> new ReverseIterator<>(this);
  }


  /**
   * A forward iterator over the items in a sequence.
   *
   * @param <Item> the item type
   */
  class ForwardIterator<Item>
    implements Iterator<Item>
  {

    private StaticSequence<Item> sequence;


    private int index;


    /**
     * Construct a forward iterator over the items in the given sequence.
     *
     * @param sequence the sequence
     */
    public ForwardIterator(StaticSequence<Item> sequence)
    {
      this.sequence = sequence;
      this.index = 0;
    }


    @Override
    public boolean hasNext()
    {
      return this.index < this.sequence.size();
    }


    @Override
    public Item next()
      throws NoSuchElementException
    {
      return this.sequence.get(this.index++);
    }

  }


  /**
   * A reverse iterator over the items in a sequence.
   *
   * @param <Item> the item type
   */
  class ReverseIterator<Item>
    implements Iterator<Item>
  {

    private StaticSequence<Item> sequence;


    private int index;


    /**
     * Construct a reverse iterator over the items in the given sequence.
     *
     * @param sequence the sequence
     */
    public ReverseIterator(StaticSequence<Item> sequence)
    {
      this.sequence = sequence;
      this.index = sequence.size() - 1;
    }


    @Override
    public boolean hasNext()
    {
      return this.index >= 0;
    }


    @Override
    public Item next()
      throws NoSuchElementException
    {
      return this.sequence.get(this.index--);
    }

  }


  //</editor-fold>

}
