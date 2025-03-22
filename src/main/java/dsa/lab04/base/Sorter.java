package dsa.lab04.base;

import dsa.lab02.base.StaticSequence;

import java.util.Comparator;

/**
 * A sorting algorithm.
 */
public interface Sorter
{

  /**
   * Sorts the given sequence according to the given comparator.
   *
   * @param items      the items to sort
   * @param comparator how to compare the items
   * @param <Item>     the item type
   */
  <Item> void sort(StaticSequence<Item> items, Comparator<Item> comparator);


  /**
   * Sorts the given sequence.
   *
   * @param items  the items to sort
   * @param <Item> the item type
   */
  default <Item extends Comparable<Item>> void sort(StaticSequence<Item> items)
  {
    this.sort(items, Comparator.nullsFirst(Comparator.naturalOrder()));
  }

}
