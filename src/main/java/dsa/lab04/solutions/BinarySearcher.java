package dsa.lab04.solutions;

import dsa.lab02.base.StaticSequence;

import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearcher
{
  /**
   * Finds the index of the given item in the given sequence using binary search.
   * <p>
   * If the item isn't contained in the sequence, returns -1.
   * <p>
   * The sequence must be sorted for the search to work properly.
   *
   * @param items  the sorted sequence
   * @param item   the searched-for item
   * @param <Item> the item type
   * @return the index of {@code item} in {@code items}, or -1 if it's not in it
   */
  public <Item extends Comparable<Item>> int search(
    StaticSequence<Item> items,
    Item item)
  {
    return this.search(
      items,
      Comparator.nullsFirst(Comparator.naturalOrder()),
      item);
  }

  /**
   * Finds the index of the given item in the given sequence using binary search.
   * <p>
   * If the item isn't contained in the sequence, returns -1.
   * <p>
   * The sequence must be sorted for the search to work properly.
   *
   * @param items      the sorted sequence
   * @param comparator the comparator
   * @param item       the searched-for item
   * @param <Item>     the item type
   * @return the index of {@code item} in {@code items}, or -1 if it's not in it
   */
  public <Item> int search(
    StaticSequence<Item> items,
    Comparator<Item> comparator,
    Item item)
  {
    return this.search(items, comparator, item, 0, items.size());
  }

  /**
   * A recursive function used to implement
   * {@link #search(StaticSequence, Comparator, Item)}.
   *
   * @param items      the sorted sequence
   * @param comparator the comparator
   * @param item       the searched-for item
   * @param start      the (inclusive) start index of
   *                   the subsequence to be searched
   * @param stop       the (exclusive) stop index of
   *                   the subsequence to be searched
   * @param <Item>     the item type
   * @return the index of {@code item} in {@code items}, or -1 if it's not in it
   */
  private <Item> int search(
    StaticSequence<Item> items,
    Comparator<Item> comparator,
    Item item,
    int start,
    int stop)
  {
    if (start >= stop)
    {
      return -1;
    }
    int middleIndex = (start + stop) / 2;
    Item middleItem = items.get(middleIndex);
    int comparison = comparator.compare(item, middleItem);
    if (comparison < 0)
    {
      return this.search(items, comparator, item, start, middleIndex);
    }
    if (comparison > 0)
    {
      return this.search(items, comparator, item, middleIndex + 1, stop);
    }
    return middleIndex;
  }
}
