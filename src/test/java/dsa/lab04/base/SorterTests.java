package dsa.lab04.base;

import dsa.lab02.base.StaticSequence;
import dsa.lab02.solutions.StaticArray;
import dsa.lib.Iterators;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SorterTests
{
  public static <Item> void sorts(
    Sorter sorter,
    Item[] items,
    Comparator<Item> comparator)
  {
    Item[] sortedItems = Arrays.copyOf(items, items.length);
    Arrays.sort(sortedItems, comparator);
    StaticSequence<Item> sequence = new StaticArray<>(items);
    sorter.sort(sequence, comparator);
    assertArrayEquals(sortedItems, Iterators.toArray(sequence));
  }

  public static <Item extends Comparable<Item>> void sortsInNaturalOrder(
    Sorter sorter,
    Item[] items)
  {
    sorts(sorter, items, Comparator.nullsFirst(Comparator.naturalOrder()));
  }

  public static <Item extends Comparable<Item>> void sortsInReverseOrder(
    Sorter sorter,
    Item[] items)
  {
    sorts(sorter, items, Comparator.nullsLast(Comparator.reverseOrder()));
  }
}
