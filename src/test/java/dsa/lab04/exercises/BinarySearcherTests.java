package dsa.lab04.exercises;

import dsa.lab03.solutions.DynamicArray;
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
    "dsa.lib.examples.arrays.sorted_uniques.NonEmpty#AND_VALID_INDICES")
  <Item extends Comparable<Item>> void findsIfMember(
    Item[] nonEmptySortedUniqueArray,
    int validIndex)
  {
    DynamicArray<Item> sequence = new DynamicArray<>(nonEmptySortedUniqueArray);
    Item item = sequence.get(validIndex);
    assertEquals(validIndex, new BinarySearcher().search(sequence, item));
  }

  @ParameterizedTest
  @DisplayName("does not find if not member")
  @FieldSource(
    "dsa.lib.examples.arrays.sorted_uniques.NonEmpty#AND_VALID_INDICES")
  <Item extends Comparable<Item>> void doesNotFindIfNotMember(
    Item[] nonEmptySortedUniqueArray,
    int validIndex)
  {
    DynamicArray<Item> sequence = new DynamicArray<>(nonEmptySortedUniqueArray);
    Item item = sequence.remove(validIndex);
    assertEquals(-1, new BinarySearcher().search(sequence, item));
  }
}
