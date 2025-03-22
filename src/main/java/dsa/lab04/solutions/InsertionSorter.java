package dsa.lab04.solutions;

import dsa.lab02.base.StaticSequence;
import dsa.lab04.base.Sorter;

import java.util.Comparator;

/**
 * Insertion sort.
 */
public class InsertionSorter
  implements Sorter
{

  @Override
  public <Item> void sort(
    StaticSequence<Item> items,
    Comparator<Item> comparator)
  {
    // NOTE: We build up a sorted prefix within the sequence.
    // NOTE: A sequence of length 1 is always sorted, so our prefix is initially
    //       the leftmost item.
    // NOTE: If items is empty (or a singleton list), i.e. if size <= 1, then
    //       this for loop never executes (which is correct, because in those
    //       cases the sequence must already be sorted - for a sequence to be
    //       not sorted, there must be two items in the wrong order, and so
    //       there must be at least two items).
    for (int i = 1; i < items.size(); i++)
    {
      // NOTE: Get the next item immediately right of the prefix, and insert it
      //       into the correct position within the sorted prefix.
      // NOTE: Keep swapping it left until it's in the right position.
      for (
        int j = i;
        j > 0 && comparator.compare(items.get(j - 1), items.get(j)) >= 0;
        j--)
      {
        items.swap(j - 1, j);
      }
    }
  }

}
