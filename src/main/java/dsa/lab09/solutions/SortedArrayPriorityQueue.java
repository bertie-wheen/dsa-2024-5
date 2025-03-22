package dsa.lab09.solutions;

import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.solutions.MergeSorter;
import dsa.lab09.base.PriorityQueueItem;
import dsa.lab09.base.PriorityQueue;

import java.util.Arrays;

/**
 * A sorted array priority queue.
 *
 * @param <Priority> the priority type
 * @param <Item>     the item type
 */
public class SortedArrayPriorityQueue<Priority extends Comparable<Priority>, Item>
  implements PriorityQueue<Priority, Item>
{

  private DynamicArray<PriorityQueueItem<Priority, Item>> items;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty sorted array priority queue.
   */
  public SortedArrayPriorityQueue()
  {
    this.items = new DynamicArray<>();
  }


  /**
   * Construct a sorted array priority queue containing the given items.
   *
   * @param items the items
   */
  public SortedArrayPriorityQueue(Iterable<PriorityQueueItem<Priority, Item>> items)
  {
    this.items = new DynamicArray<>(items);
    new MergeSorter().sort(this.items);
  }


  /**
   * Construct a sorted array priority queue containing the given items
   * more efficiently than {@link #SortedArrayPriorityQueue(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public SortedArrayPriorityQueue(
    Iterable<PriorityQueueItem<Priority, Item>> items,
    int size)
    throws IllegalArgumentException
  {
    this.items = new DynamicArray<>(items, size);
    new MergeSorter().sort(this.items);
  }


  /**
   * Construct a sorted array priority queue containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public SortedArrayPriorityQueue(PriorityQueueItem<Priority, Item>... items)
  {
    this(Arrays.asList(items), items.length);
  }


  //</editor-fold>


  @Override
  public int size()
  {
    return this.items.size();
  }


  @Override
  public PriorityQueueItem<Priority, Item> max()
  {
    // NOTE: We could flip the logic in insert() such that items are stored
    //       in reverse order, and then max() would of course be items.first().
    return this.isEmpty() ? null : this.items.last();
  }


  @Override
  public void insert(PriorityQueueItem<Priority, Item> item)
  {
    // NOTE: Compare this to the InsertionSorter.sort solution.
    this.items.insertLast(item);
    for (
      int i = this.size() - 1;
      i > 0 && this.items.get(i - 1).compareTo(item) >= 0;
      i--)
    {
      this.items.swap(i - 1, i);
    }
  }


  @Override
  public PriorityQueueItem<Priority, Item> removeMax()
  {
    return this.isEmpty() ? null : this.items.removeLast();
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  @Override
  public Iterable<PriorityQueueItem<Priority, Item>> items()
  {
    return this.items.reversed();
  }


  @Override
  public Iterable<PriorityQueueItem<Priority, Item>> reversed()
  {
    return this.items;
  }


  //</editor-fold>

}
