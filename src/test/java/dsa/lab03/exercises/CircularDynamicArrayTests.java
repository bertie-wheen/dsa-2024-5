package dsa.lab03.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import dsa.lab02.base.StaticSequenceTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("CircularDynamicArray")
public class CircularDynamicArrayTests
{
  @Nested
  @DisplayName("get")
  class Get
  {
    @Nested
    @DisplayName("throws")
    class Throws
    {
      @ParameterizedTest
      @DisplayName("if below bound")
      @FieldSource("dsa.lib.examples.Arrays#AND_NEGATIVE_INTS")
      <Item> void ifBelowBound(Item[] items, int negativeIndex)
      {
        StaticSequenceTests.Get.Throws.ifIndexBelowBound(
          new CircularDynamicArray<>(items),
          negativeIndex);
      }

      @ParameterizedTest
      @DisplayName("if above bound")
      @FieldSource("dsa.lib.examples.Arrays#AND_NON_NEGATIVE_INTS")
      <Item> void ifAboveBound(Item[] items, int nonNegativeOffset)
      {
        StaticSequenceTests.Get.Throws.ifIndexAboveBound(
          new CircularDynamicArray<>(items),
          nonNegativeOffset);
      }
    }

    @ParameterizedTest
    @DisplayName("gets correct index")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void getsCorrectIndex(Item[] items, int index)
    {
      StaticSequenceTests.Get.getsCorrectIndex(
        new CircularDynamicArray<>(items),
        items,
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change items")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeItems(Item[] items, int index)
    {
      StaticSequenceTests.Get.doesNotChangeItems(
        new CircularDynamicArray<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change size")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeSize(Item[] items, int index)
    {
      StaticSequenceTests.Get.doesNotChangeSize(
        new CircularDynamicArray<>(items),
        index);
    }
  }

  @Nested
  @DisplayName("set")
  class Set
  {
    @Nested
    @DisplayName("throws")
    class Throws
    {
      @ParameterizedTest
      @DisplayName("if below bound")
      @FieldSource("dsa.lib.examples.arrays.AndNegativeInts#AND_ITEMS")
      <Item> void ifBelowBound(Item[] items, int negativeIndex, Item item)
      {
        StaticSequenceTests.Set.Throws.setThrowsIfIndexBelowBound(
          new CircularDynamicArray<>(items),
          negativeIndex,
          item);
      }

      @ParameterizedTest
      @DisplayName("if above bound")
      @FieldSource("dsa.lib.examples.arrays.AndNonNegativeInts#AND_ITEMS")
      <Item> void ifAboveBound(Item[] items, int nonNegativeOffset, Item item)
      {
        StaticSequenceTests.Set.Throws.setThrowsIfIndexAboveBound(
          new CircularDynamicArray<>(items),
          nonNegativeOffset,
          item);
      }
    }

    @ParameterizedTest
    @DisplayName("sets correct index")
    @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
    <Item> void setsCorrectIndex(Item[] items, int index, Item item)
    {
      StaticSequenceTests.Set.setsCorrectIndex(
        new CircularDynamicArray<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
    <Item> void doesNotChangeOthers(Item[] items, int index, Item item)
    {
      StaticSequenceTests.Set.doesNotChangeOthers(
        new CircularDynamicArray<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change size")
    @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
    <Item> void doesNotChangeSize(Item[] items, int index, Item item)
    {
      StaticSequenceTests.Set.doesNotChangeSize(
        new CircularDynamicArray<>(items),
        index,
        item);
    }
  }

  @Nested
  @DisplayName("insert")
  class Insert
  {
    @ParameterizedTest
    @DisplayName("inserts into correct index")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void insertsIntoCorrectIndex(Item[] items, int index, Item item)
    {
      DynamicSequenceTests.Insert.insertsIntoCorrectIndex(
        new CircularDynamicArray<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void doesNotChangeOthers(Item[] items, int index, Item item)
    {
      DynamicSequenceTests.Insert.doesNotChangeOthers(
        new CircularDynamicArray<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void incrementsSize(Item[] items, int index, Item item)
    {
      DynamicSequenceTests.Insert.incrementsSize(
        new CircularDynamicArray<>(items),
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
        new CircularDynamicArray<>(),
        index);
    }

    @ParameterizedTest
    @DisplayName("removes from correct index")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void removesFromCorrectIndex(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.removesFromCorrectIndex(
        new CircularDynamicArray<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeOthers(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.doesNotChangeOthers(
        new CircularDynamicArray<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("decrements size")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void decrementsSize(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.decrementsSize(
        new CircularDynamicArray<>(items),
        index);
    }
  }
}
