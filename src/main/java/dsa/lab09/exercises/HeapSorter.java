package dsa.lab09.exercises;

import dsa.lab02.base.StaticSequence;
import dsa.lab04.base.Sorter;
import dsa.lib.TODO;

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
    // TODO: Implement HeapSort.parent(int index)
    // NOTE: You can copy across your solution in BinaryHeapPriorityQueue.
    return 0;
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
    // TODO: Implement HeapSort.left(int index)
    // NOTE: You can copy across your solution in BinaryHeapPriorityQueue.
    return 0;
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
    // TODO: Implement HeapSort.right(int index)
    // NOTE: You can copy across your solution in BinaryHeapPriorityQueue.
    return 0;
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
    // TODO: Implement HeapSort.heapifyUp(StaticSequence items, Comparator comparator, int index)
    // NOTE: This is similar to in BinaryHeapPriorityQueue, but you will need to
    //       adapt your solution to use the given Comparator and to work on the
    //       StaticSequence parameter rather than the DynamicArray field.
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
    // TODO: Implement HeapSort.heapifyDown(StaticSequence items, Comparator comparator, int index, int size)
    // NOTE: Similar comments as with heapifyUp.
    // NOTE: It's the same as in BinaryHeapPriorityQueue, but different.
  }


  @Override
  public <Item> void sort(
    StaticSequence<Item> items,
    Comparator<Item> comparator)
  {
    // TODO: Implement HeapSort.sort(StaticSequence items, Comparator comparator)
    // NOTE: This is the interesting bit of this exercise!
  }

}
