package dsa.lab03.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("DynamicArray")
public class DynamicArrayTests
{
  @Nested
  @DisplayName("insert")
  class Insert
  {
    @ParameterizedTest
    @DisplayName("inserts into correct index")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void insertsIntoCorrectIndex(Item[] array, int index, Item item)
    {
      DynamicSequenceTests.Insert.insertsIntoCorrectIndex(
        new DynamicArray<>(array),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void doesNotChangeOthers(Item[] array, int index, Item item)
    {
      DynamicSequenceTests.Insert.doesNotChangeOthers(
        new DynamicArray<>(array),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void incrementsSize(Item[] array, int index, Item item)
    {
      DynamicSequenceTests.Insert.incrementsSize(
        new DynamicArray<>(array),
        index,
        item);
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove
  {
    @ParameterizedTest
    @DisplayName("throws if empty")
    @FieldSource("dsa.lib.examples.Ints#ALL")
    void throwsIfEmpty(int index)
    {
      DynamicSequenceTests.Remove.throwsIfEmpty(
        new DynamicArray<>(),
        index);
    }

    @ParameterizedTest
    @DisplayName("removes from correct index")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void removesFromCorrectIndex(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.removesFromCorrectIndex(
        new DynamicArray<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeOthers(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.doesNotChangeOthers(
        new DynamicArray<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("decrements size")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void decrementsSize(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.decrementsSize(
        new DynamicArray<>(items),
        index);
    }
  }
}
