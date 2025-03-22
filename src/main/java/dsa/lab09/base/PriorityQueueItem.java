package dsa.lab09.base;

import dsa.lib.To;

import java.util.Objects;

/**
 * An item in a priority queue.
 *
 * @param <Priority> the priority type
 * @param <Item>     the item type
 * @see PriorityQueue
 */
public class PriorityQueueItem<Priority extends Comparable<Priority>, Item>
  implements Comparable<PriorityQueueItem<Priority, Item>>
{

  /** The item's priority. */
  private Priority priority;


  /** The item. */
  private Item item;


  /**
   * Construct a priority queue item.
   */
  public PriorityQueueItem(Priority priority, Item item)
  {
    this.priority = priority;
    this.item = item;
  }


  /**
   * Get the priority.
   *
   * @return the priority
   */
  public Priority priority()
  {
    return this.priority;
  }


  /**
   * Get the item.
   *
   * @return the item
   */
  public Item item()
  {
    return this.item;
  }


  @Override
  public int compareTo(PriorityQueueItem<Priority, Item> that)
  {
    return this.priority.compareTo(that.priority);
  }


  //<editor-fold defaultstate="collapsed" desc="equals()+hashCode()+toString()">


  @Override
  public boolean equals(Object that)
  {
    if (that == null || this.getClass() != that.getClass())
    {
      return false;
    }
    PriorityQueueItem<?, ?> that_ = (PriorityQueueItem<?, ?>) that;
    return Objects.equals(this.priority, that_.priority) &&
      Objects.equals(this.item, that_.item);
  }


  @Override
  public int hashCode()
  {
    return Objects.hash(this.priority, this.item);
  }


  @Override
  public String toString()
  {
    return To.string(this);
  }


  //</editor-fold>

}
