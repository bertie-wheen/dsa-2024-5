package dsa.lab09.solutions;

import dsa.lab02.base.StaticSequence;
import dsa.lab04.base.Sorter;

import java.util.Comparator;

/**
 * Heap sort.
 */
public class HeapSorter
  implements Sorter
{

  /**
   * Get the index of the parent of the one given.
   * <p>
   * The index is assumed to be valid and have a parent.
   *
   * @param index the index
   * @return the parent index
   */
  private int parent(int index)
  {
    return (index - 1) / 2;
  }


  /**
   * Get the index of the left child of the one given.
   * <p>
   * The index is assumed to be valid and have a left child.
   *
   * @param index the index
   * @return the left child index
   */
  private int left(int index)
  {
    return index * 2 + 1;
  }


  /**
   * Get the index of the right child of the one given.
   * <p>
   * The index is assumed to be valid and have a right child.
   *
   * @param index the index
   * @return the right child index
   */
  private int right(int index)
  {
    return index * 2 + 2;
  }


  /**
   * Heapify up from the given index.
   *
   * @param items      the sequence
   * @param comparator the comparator
   * @param index      the index
   * @param <Item>     the item type
   */
  private <Item> void heapifyUp(
    StaticSequence<Item> items,
    Comparator<Item> comparator,
    int index)
  {
    // NOTE: This is much the same as BinaryHeapPriorityQueue.heapifyUp().
    if (index > 0)
    {
      int parent = this.parent(index);
      if (comparator.compare(items.get(index), items.get(parent)) > 0)
      {
        items.swap(index, parent);
        this.heapifyUp(items, comparator, parent);
      }
    }
  }


  /**
   * Heapify down from the given index within the prefix.
   *
   * @param items      the sequence
   * @param comparator the comparator
   * @param index      the index
   * @param size       the prefix size
   * @param <Item>     the item type
   */
  private <Item> void heapifyDown(
    StaticSequence<Item> items,
    Comparator<Item> comparator,
    int index,
    int size)
  {
    // NOTE: Like heapifyUp(), basically BinaryHeapPriorityQueue.heapifyDown().
    int left = this.left(index);
    if (left < size)
    {
      int right = this.right(index);
      int child =
        right >= size
          || comparator.compare(items.get(left), items.get(right)) > 0
          ? left
          : right;
      if (comparator.compare(items.get(child), items.get(index)) > 0)
      {
        items.swap(index, child);
        this.heapifyDown(items, comparator, child, size);
      }
    }
  }


  @Override
  public <Item> void sort(
    StaticSequence<Item> items,
    Comparator<Item> comparator)
  {
    // NOTE: At this point, the whole sequence is unsorted.

    // NOTE: We insert each item in turn into a heap that builds up as a prefix
    //       within the sequence.
    // NOTE: Compare this to BinaryHeapPriorityQueue.insert().
    for (int i = 1; i < items.size(); i++)
    {
      this.heapifyUp(items, comparator, i);
    }

    // NOTE: At this point, the whole sequence is a heap.

    // NOTE: We then remove each item in decreasing order, building up a sorted
    //       suffix within the sequence.
    // NOTE: Compare this to BinaryHeapPriorityQueue.removeMax().
    for (int i = items.size() - 1; i >= 1; i--)
    {
      items.swap(0, i);
      this.heapifyDown(items, comparator, 0, i);
    }

    // NOTE: At this point, the whole sequence is sorted!
  }

}
