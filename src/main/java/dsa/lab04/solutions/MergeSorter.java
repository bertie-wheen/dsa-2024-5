package dsa.lab04.solutions;

import dsa.lab02.base.StaticSequence;
import dsa.lab02.solutions.StaticArray;
import dsa.lab04.base.Sorter;

import java.util.Comparator;

/**
 * Merge sort.
 */
public class MergeSorter
  implements Sorter
{

  @Override
  @SuppressWarnings("unchecked")
  public <Item> void sort(
    StaticSequence<Item> items,
    Comparator<Item> comparator)
  {
    int size = items.size();

    // NOTE: If it's an empty or singleton sequence, it's already sorted, so
    //       nothing to do.
    if (size <= 1)
    {
      return;
    }

    // NOTE: Divide the sequence into two halves (not necessarily of exactly
    //       equal size, if size is odd - in this case we take the convention
    //       that the right "half" will be one item bigger, but you could
    //       choose otherwise).
    int sizeL = size / 2;
    int sizeR = size - sizeL;
    StaticArray<Item> itemsL = new StaticArray<>((Item[]) new Object[sizeL]);
    StaticArray<Item> itemsR = new StaticArray<>((Item[]) new Object[sizeR]);
    for (int l = 0; l < sizeL; l++)
    {
      itemsL.set(l, items.get(l));
    }
    for (int r = 0; r < sizeR; r++)
    {
      itemsR.set(r, items.get(sizeL + r));
    }

    // NOTE: Sort each half independently.
    // NOTE: This could happen in parallel, if the overheads of parallelization
    //       and the size of the sequence are such that that'd be worthwhile.
    // NOTE: Sometimes people will create a hybrid sort where the halves aren't
    //       necessarily sorted using merge sort. This is also true with
    //       quicksort, and other divide-and-conquer sorts. For example, you
    //       might recursively merge sort until you get to some small number of
    //       items, and then switch to using e.g. insertion sort to sort
    //       subsequences of less than that size.
    this.sort(itemsL, comparator);
    this.sort(itemsR, comparator);

    // NOTE: Merge the two sorted halves into one sorted whole.
    int l = 0;
    int r = 0;
    for (int i = 0; i < size; i++)
    {
      if (r >= sizeR
        || (l < sizeL && comparator.compare(itemsL.get(l), itemsR.get(r)) <= 0))
      {
        items.set(i, itemsL.get(l++));
      }
      else
      {
        items.set(i, itemsR.get(r++));
      }
    }
  }

}
