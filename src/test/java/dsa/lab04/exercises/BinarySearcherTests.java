package dsa.lab04.exercises;

import dsa.lab03.solutions.DynamicArray;
import dsa.lib.Iterators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BinarySearcher")
public class BinarySearcherTests
{
  @ParameterizedTest
  @DisplayName("finds if member")
  @FieldSource(
    "dsa.lib.examples.arrays.SortedUniques#AND_CONTAINED_ITEMS")
  <Item extends Comparable<Item>> void findsIfMember(
    Item[] nonEmptySortedUniqueArray,
    Item containedItem)
  {
    assertEquals(
      Iterators.asList(nonEmptySortedUniqueArray).indexOf(containedItem),
      new BinarySearcher().search(
        new DynamicArray<>(nonEmptySortedUniqueArray),
        containedItem));
  }

  @ParameterizedTest
  @DisplayName("does not find if not member")
  @FieldSource(
    "dsa.lib.examples.arrays.SortedUniques#AND_NOT_CONTAINED_ITEMS")
  <Item extends Comparable<Item>> void doesNotFindIfNotMember(
    Item[] sortedUniqueArray,
    Item notContainedItem)
  {
    assertEquals(
      -1,
      new BinarySearcher().search(
        new DynamicArray<>(sortedUniqueArray),
        notContainedItem));
  }
}
