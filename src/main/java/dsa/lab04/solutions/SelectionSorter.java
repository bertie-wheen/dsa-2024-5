package dsa.lab04.solutions;

import dsa.lab02.base.StaticSequence;
import dsa.lab04.base.Sorter;

import java.util.Comparator;

/**
 * Selection sort.
 */
public class SelectionSorter
  implements Sorter
{

  @Override
  public <Item> void sort(
    StaticSequence<Item> items,
    Comparator<Item> comparator)
  {
    // NOTE: We build up a sorted prefix within the sequence.
    // NOTE: If items is empty, this for loop never executes (which is correct,
    //       because an empty sequence is already sorted).
    int size = items.size();
    for (int i = 0; i < size - 1; i++)
    {
      // NOTE: Find the minimum item amongst those right of the prefix.
      int min = i;
      for (int j = i + 1; j < size; j++)
      {
        if (comparator.compare(items.get(j), items.get(min)) < 0)
        {
          min = j;
        }
      }

      // NOTE: We then know that that item should be left of all the items right
      //       of the prefix, but we also know that it should be right of all
      //       the items already in the prefix (otherwise we would have
      //       selected it already), so move it to the index between the two
      //       regions (i.e. immediately right of the prefix) with a swap.
      items.swap(i, min);
    }

    // You could instead build up a sorted suffix, selecting each max:
    //   for (int i = size - 1; i >= 0; i--)
    //   {
    //     int max = 0;
    //     for (int j = 1; j < i; j++)
    //     {
    //       if (comparator.compare(items.get(j), items.get(max)) > 0)
    //       {
    //         max = j;
    //       }
    //     }
    //     items.swap(max, i);
    //   }
  }

}
