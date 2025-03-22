package dsa.lab09.exercises;

import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.solutions.MergeSorter;
import dsa.lab09.base.PriorityQueueItem;
import dsa.lab09.base.PriorityQueue;
import dsa.lib.TODO;

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
    if (this.isEmpty())
    {
      return null;
    }

    // TODO: Implement SortedArrayPriorityQueue.max()
    return null;
  }


  @Override
  public void insert(PriorityQueueItem<Priority, Item> item)
  {
    // TODO: Implement SortedArrayPriorityQueue.insert(PrioritisedItem prioritisedItem)
  }


  @Override
  public PriorityQueueItem<Priority, Item> removeMax()
  {
    if (this.isEmpty())
    {
      return null;
    }

    // TODO: Implement SortedArrayPriorityQueue.removeMax()
    return null;
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
