package dsa.lab09.base;

import dsa.lab02.base.Container;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A priority queue.
 *
 * @param <Priority> the priority type
 * @param <Item>     the item type
 */
public interface PriorityQueue<Priority extends Comparable<Priority>, Item>
  extends Container<PriorityQueueItem<Priority, Item>>
{

  /**
   * Get the item with the greatest priority.
   *
   * @return the maximum priority item, or {@code null} if empty
   */
  PriorityQueueItem<Priority, Item> max();


  /**
   * Insert the given prioritised item.
   *
   * @param item the prioritised item
   */
  void insert(PriorityQueueItem<Priority, Item> item);


  /**
   * Insert the given prioritised item.
   *
   * @param priority the priority
   * @param item     the item
   */
  default void insert(Priority priority, Item item)
  {
    this.insert(new PriorityQueueItem<>(priority, item));
  }


  /**
   * Remove the item with the greatest priority.
   *
   * @return the maximum priority item, or {@code null} if empty
   */
  PriorityQueueItem<Priority, Item> removeMax();


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  /**
   * Get a forward iterable that yields each prioritised item once.
   * <p>
   * The items are iterated over in max-to-min order (by priority).
   *
   * @return an iterable over the prioritised items
   */
  @Override
  Iterable<PriorityQueueItem<Priority, Item>> items();


  /**
   * Get a reverse iterable that yields each prioritised item once.
   * <p>
   * The items are iterated over in min-to-max order (by priority).
   *
   * @return an iterable over the prioritised items
   */
  Iterable<PriorityQueueItem<Priority, Item>> reversed();


  /**
   * Get a reverse iterable that yields each item once.
   * <p>
   * The items are iterated over in max-to-min order (by priority).
   *
   * @return an iterable over just the items (not the priorities)
   */
  default Iterable<Item> itemsOnly()
  {
    return () -> new ItemIterator<>(this.iterator());
  }


  /**
   * Get a reverse iterable that yields each item once.
   * <p>
   * The items are iterated over in min-to-max order (by priority).
   *
   * @return an iterable over just the items (not the priorities)
   */
  default Iterable<Item> reversedItemsOnly()
  {
    return () -> new ItemIterator<>(this.reversed().iterator());
  }


  /**
   * An iterator over the items in a priority queue.
   * <p>
   * Iterates over the items of a given prioritised item iterator.
   *
   * @param <Priority> the item type
   * @param <Item>     the item type
   */
  class ItemIterator<Priority extends Comparable<Priority>, Item>
    implements Iterator<Item>
  {

    private Iterator<PriorityQueueItem<Priority, Item>> prioritisedItemIterator;


    /**
     * Construct an iterator over the items
     * in the given prioritised item iterator.
     *
     * @param prioritisedItemIterator the iterator over prioritised items
     */
    public ItemIterator(Iterator<PriorityQueueItem<Priority, Item>> prioritisedItemIterator)
    {
      this.prioritisedItemIterator = prioritisedItemIterator;
    }


    /**
     * Check if there are more items to be iterated over.
     * <p>
     * (Equivalently, checks if {@link #next()} would return an item
     * rather than throwing a {@link NoSuchElementException}.)
     *
     * @return whether {@link #next()} can be successfully called
     */
    @Override
    public boolean hasNext()
    {
      return this.prioritisedItemIterator.hasNext();
    }


    /**
     * Get the next item in the iteration.
     *
     * @return the next item
     * @throws NoSuchElementException if there is no next item
     */
    @Override
    public Item next()
      throws NoSuchElementException
    {
      return this.prioritisedItemIterator.next().item();
    }


    /**
     * Remove the previous item returned from {@link #next()}.
     *
     * @throws IllegalStateException if {@link #next()} hasn't yet been called,
     *                               or if {@link #remove()} has already been
     *                               called since {@link #next()} was last called
     */
    @Override
    public void remove()
      throws IllegalStateException
    {
      this.prioritisedItemIterator.remove();
    }

  }


  //</editor-fold>

}
