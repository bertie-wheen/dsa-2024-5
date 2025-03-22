package dsa.lab09.exercises;

import dsa.lab03.solutions.DynamicArray;
import dsa.lab09.base.PriorityQueueItem;
import dsa.lab09.base.PriorityQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A binary heap priority queue.
 *
 * @param <Priority> the priority type
 * @param <Item>     the item type
 */
public class BinaryHeapPriorityQueue<Priority extends Comparable<Priority>, Item>
  implements PriorityQueue<Priority, Item>
{

  private DynamicArray<PriorityQueueItem<Priority, Item>> items;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty binary heap priority queue.
   */
  public BinaryHeapPriorityQueue()
  {
    this.items = new DynamicArray<>();
  }


  /**
   * Construct a binary heap priority queue containing the given items.
   *
   * @param items the items
   */
  public BinaryHeapPriorityQueue(Iterable<PriorityQueueItem<Priority, Item>> items)
  {
    this.items = new DynamicArray<>(items);

    // NOTE: Turn the unsorted array into a binary heap.
    // NOTE: We work backwards, right to left and bottom to top.
    // NOTE: We heapify each item down (to higher indices).
    // NOTE: We start from the parent of the rightmost item in the bottommost
    //       level because we don't need to heapify down any leaves.
    for (int i = this.parent(this.size() - 1); i >= 0; i--)
    {
      this.heapifyDown(i);
    }
  }


  /**
   * Construct a binary heap priority queue containing the given items
   * more efficiently than {@link #BinaryHeapPriorityQueue(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public BinaryHeapPriorityQueue(
    Iterable<PriorityQueueItem<Priority, Item>> items,
    int size)
    throws IllegalArgumentException
  {
    this.items = new DynamicArray<>(items, size);

    // NOTE: Turn the unsorted array into a binary heap.
    // NOTE: We work backwards, right to left and bottom to top.
    // NOTE: We heapify each item down (to higher indices).
    // NOTE: We start from the parent of the rightmost item in the bottommost
    //       level because we don't need to heapify down any leaves.
    for (int i = this.parent(this.size() - 1); i >= 0; i--)
    {
      this.heapifyDown(i);
    }
  }


  /**
   * Construct a binary heap priority queue containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public BinaryHeapPriorityQueue(PriorityQueueItem<Priority, Item>... items)
  {
    this(Arrays.asList(items), items.length);
  }


  //</editor-fold>


  @Override
  public int size()
  {
    return this.items.size();
  }


  /**
   * Get the index of the parent of the one given.
   * <p>
   * The index is assumed to be valid and have a parent.
   *
   * @param index the index
   * @return the parent index
   */
  private int parent(int index)
  {
    // 0
    // a
    //
    // 1       2
    // b       c
    //
    // 3   4   5   6
    // d   e   f   g
    //
    // 7 8 9 ...
    // h i j ...

    // TODO: Implement BinaryHeapPriorityQueue.parent(int index)
    return 0;
  }


  /**
   * Get the index of the left child of the one given.
   * <p>
   * The index is assumed to be valid and have a left child.
   *
   * @param index the index
   * @return the left child index
   */
  private int left(int index)
  {
    // 0
    // a
    //
    // 1       2
    // b       c
    //
    // 3   4   5   6
    // d   e   f   g
    //
    // 7 8 9 ...
    // h i j ...

    // TODO: Implement BinaryHeapPriorityQueue.left(int index)
    return 0;
  }


  /**
   * Get the index of the right child of the one given.
   * <p>
   * The index is assumed to be valid and have a right child.
   *
   * @param index the index
   * @return the right child index
   */
  private int right(int index)
  {
    // 0
    // a
    //
    // 1       2
    // b       c
    //
    // 3   4   5   6
    // d   e   f   g
    //
    // 7 8 9 ...
    // h i j ...

    // TODO: Implement BinaryHeapPriorityQueue.left(int index)
    return 0;
  }


  /**
   * Check whether the item at the first index given
   * has greater priority than that at the second.
   * <p>
   * The indices are assumed to be valid.
   *
   * @param indexA the index of the first item
   * @param indexB the index of the second item
   * @return whether the first item has greater priority than the second
   */
  private boolean greaterPriority(int indexA, int indexB)
  {
    return this.items.get(indexA).compareTo(this.items.get(indexB)) > 0;
  }


  /**
   * Heapify up from the given index.
   *
   * @param index the index
   */
  private void heapifyUp(int index)
  {
    // TODO: Implement BinaryHeapPriorityQueue.heapifyUp(int index)
  }


  /**
   * Heapify down from the given index.
   *
   * @param index the index
   */
  private void heapifyDown(int index)
  {
    // TODO: Implement BinaryHeapPriorityQueue.heapifyDown(int index)
  }


  @Override
  public PriorityQueueItem<Priority, Item> max()
  {
    if (this.isEmpty())
    {
      return null;
    }

    // TODO: Implement BinaryHeapPriorityQueue.max()
    return null;
  }


  @Override
  public void insert(PriorityQueueItem<Priority, Item> item)
  {
    // TODO: Implement BinaryHeapPriorityQueue.insert(PriorityQueueItem item)
  }


  @Override
  public PriorityQueueItem<Priority, Item> removeMax()
  {
    if (this.isEmpty())
    {
      return null;
    }

    // TODO: Implement BinaryHeapPriorityQueue.removeMax()
    return null;
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  @Override
  public Iterable<PriorityQueueItem<Priority, Item>> items()
  {
    return () -> new BinaryHeapIterator<>(this);
  }


  @Override
  public Iterable<PriorityQueueItem<Priority, Item>> reversed()
  {
    return new DynamicArray<>(this).reversed();
  }


  public static class BinaryHeapIterator<Priority extends Comparable<Priority>, Item>
    implements Iterator<PriorityQueueItem<Priority, Item>>
  {

    private BinaryHeapPriorityQueue<Priority, Item> items;


    public BinaryHeapIterator(BinaryHeapPriorityQueue<Priority, Item> items)
    {
      this.items = new BinaryHeapPriorityQueue<>(items.items);
    }


    @Override
    public boolean hasNext()
    {
      return !this.items.isEmpty();
    }


    @Override
    public PriorityQueueItem<Priority, Item> next()
      throws NoSuchElementException
    {
      if (!this.hasNext())
      {
        throw new NoSuchElementException();
      }
      return this.items.removeMax();
    }

  }


  //</editor-fold>

}
