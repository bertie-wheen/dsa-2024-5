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
    if (size <= 1)
    {
      return;
    }
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
    this.sort(itemsL, comparator);
    this.sort(itemsR, comparator);
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
