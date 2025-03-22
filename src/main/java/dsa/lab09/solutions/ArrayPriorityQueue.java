package dsa.lab09.solutions;

import dsa.lab02.solutions.StaticArray;
import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.solutions.MergeSorter;
import dsa.lab09.base.PriorityQueueItem;
import dsa.lab09.base.PriorityQueue;

import java.util.Arrays;

/**
 * An (unsorted) array priority queue.
 *
 * @param <Priority> the priority type
 * @param <Item>     the item type
 */
public class ArrayPriorityQueue<Priority extends Comparable<Priority>, Item>
  implements PriorityQueue<Priority, Item>
{

  /** The DynamicArray we're using to implement the priority queue with. */
  private DynamicArray<PriorityQueueItem<Priority, Item>> items;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty array priority queue.
   */
  public ArrayPriorityQueue()
  {
    this.items = new DynamicArray<>();
  }


  /**
   * Construct an array priority queue containing the given items.
   *
   * @param items the items
   */
  public ArrayPriorityQueue(Iterable<PriorityQueueItem<Priority, Item>> items)
  {
    this.items = new DynamicArray<>(items);
  }


  /**
   * Construct an array priority queue containing the given items
   * more efficiently than {@link #ArrayPriorityQueue(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public ArrayPriorityQueue(
    Iterable<PriorityQueueItem<Priority, Item>> items,
    int size)
    throws IllegalArgumentException
  {
    this.items = new DynamicArray<>(items, size);
  }


  /**
   * Construct an array priority queue containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public ArrayPriorityQueue(PriorityQueueItem<Priority, Item>... items)
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

    PriorityQueueItem<Priority, Item> max = this.items.first();

    // NOTE: We start the loop from index 1 because max starts off as the index
    //       0 item, and there's no point comparing it with itself.
    for (int i = 1; i < this.size(); i++)
    {
      PriorityQueueItem<Priority, Item> item = this.items.get(i);
      if (item.compareTo(max) > 0)
      {
        max = item;
      }
    }

    return max;
  }


  @Override
  public void insert(PriorityQueueItem<Priority, Item> item)
  {
    this.items.insertLast(item);
  }


  @Override
  public PriorityQueueItem<Priority, Item> removeMax()
  {
    if (this.isEmpty())
    {
      return null;
    }

    // NOTE: Compare this to the (alternative) SelectionSorter.sort() solution.
    int max = 0;
    for (int i = 1; i < this.size(); i++)
    {
      if (this.items.get(i).compareTo(this.items.get(max)) > 0)
      {
        max = i;
      }
    }

    this.items.swap(max, this.size() - 1);

    return this.items.removeLast();
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  @Override
  public Iterable<PriorityQueueItem<Priority, Item>> items()
  {
    StaticArray<PriorityQueueItem<Priority, Item>> sortedItems =
      new StaticArray<>(this.items, this.items.size());
    new MergeSorter().sort(sortedItems);
    return sortedItems.reversed();
  }


  @Override
  public Iterable<PriorityQueueItem<Priority, Item>> reversed()
  {
    StaticArray<PriorityQueueItem<Priority, Item>> sortedItems =
      new StaticArray<>(this.items, this.items.size());
    new MergeSorter().sort(sortedItems);
    return sortedItems;
  }


  //</editor-fold>

}
