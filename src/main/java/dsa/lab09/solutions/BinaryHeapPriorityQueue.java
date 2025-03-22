package dsa.lab09.solutions;

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
    //
    // so
    //
    // 1 -> 0
    // 2 -> 0
    // 3 -> 1
    // 4 -> 1
    // 5 -> 2
    // 6 -> 2
    // 7 -> 3
    // 8 -> 3
    // 9 -> 4
    // ...
    return (index - 1) / 2;
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
    //
    // so
    //
    // 0 -> 1
    // 1 -> 3
    // 2 -> 5
    // 3 -> 7
    // 4 -> 9
    // ...
    return index * 2 + 1;
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
    //
    // so
    //
    // 0 -> 2
    // 1 -> 4
    // 2 -> 6
    // 3 -> 8
    // ...
    return index * 2 + 2;
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
    // NOTE: If index == 0, we're the root, so we can't heapify any further up.
    if (index > 0)
    {
      // NOTE: Compare the priorities of this item and its parent, and if they
      //       are the wrong way round (which, because we're implementing a
      //       max-heap would be if our priority is greater than our parent's)
      //       then swap them and continue heapifying up.
      int parent = this.parent(index);
      if (this.greaterPriority(index, parent))
      {
        this.items.swap(index, parent);
        this.heapifyUp(parent);
      }
    }
  }


  /**
   * Heapify down from the given index.
   *
   * @param index the index
   */
  private void heapifyDown(int index)
  {
    // NOTE: If we don't have a left child, we will (because heaps are complete)
    //       be a leaf, and so we can't heapify any further down.
    int left = this.left(index);
    if (left < this.size())
    {
      // NOTE: We choose the relevant child to compare against.
      // NOTE: If we only have a left child, then that.
      // NOTE: If we have both children, then the one with greater priority.
      // NOTE: If both have the same priority, we arbitrarily choose the left.
      // NOTE: We can't have only a right child because heaps are complete.
      int right = this.right(index);
      int child =
        right >= this.size() || this.greaterPriority(left, right)
          ? left
          : right;

      // NOTE: Similarly to heapifying up (except here with the chosen child
      //       rather than the parent), we compare our priority with theirs,
      //       and if they're the wrong way around (which, given we're a
      //       max-heap, is if the child's priority is greater than ours), then
      //       we swap and continue heapifying down.
      if (this.greaterPriority(child, index))
      {
        this.items.swap(index, child);
        this.heapifyDown(child);
      }
    }
  }


  @Override
  public PriorityQueueItem<Priority, Item> max()
  {
    // NOTE: We're implementing a max-PQ using a max-heap, so this is easy:
    //       The item at the root of a max-heap (which is the item at index 0)
    //       is maximal, so we can just return that.
    return this.isEmpty() ? null : this.items.first();
  }


  @Override
  public void insert(PriorityQueueItem<Priority, Item> item)
  {
    // NOTE: This is quite like SortedArrayPriorityQueue.insert().
    // NOTE: We initially insert it at the end, and then repeatedly swap it with
    //       its parents (using heapifyUp) until it's in an appropriate
    //       position (for this to still be a heap).
    this.items.insertLast(item);
    this.heapifyUp(this.size() - 1);
  }


  @Override
  public PriorityQueueItem<Priority, Item> removeMax()
  {
    if (this.isEmpty())
    {
      return null;
    }

    // NOTE: Similarly to ArrayPriorityQueue.removeMax(), we swap the max with
    //       the last item, and then remove it from there. This is both for
    //       efficiency and to better match the corresponding PQ sorts. (We will
    //       see an example of this in a moment in HeapSorter.)
    this.items.swap(0, this.size() - 1);
    PriorityQueueItem<Priority, Item> max = this.items.removeLast();

    // NOTE: The item that was last and is now first is very likely in the wrong
    //       position for this to still be a valid heap, so we repeatedly swap
    //       it downwards in the heap (using heapify down) until it is.
    this.heapifyDown(0);

    return max;
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
