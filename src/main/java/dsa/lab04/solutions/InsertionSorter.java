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
    for (int i = 1; i < items.size(); i++)
    {
      for (
        int j = i;
        j > 0 && comparator.compare(items.get(j), items.get(j - 1)) < 0;
        j--)
      {
        items.swap(j - 1, j);
      }
    }
  }
}
