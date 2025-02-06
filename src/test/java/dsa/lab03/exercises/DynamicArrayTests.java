package dsa.lab03.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("DynamicArray")
public class DynamicArrayTests
{
  @Nested
  @DisplayName("insert")
  class Insert
  {
    @Nested
    @DisplayName("first")
    class First
    {
      @ParameterizedTest
      @DisplayName("inserts as first")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void insertsAsFirst(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.insertsAsFirst(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.doesNotChangeOthers(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.incrementsSize(
          new DynamicArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("into first half")
    class IntoFirstHalf
    {
      @ParameterizedTest
      @DisplayName("inserts into correct index")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void insertsIntoCorrectIndex(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.insertsIntoCorrectIndex(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.doesNotChangeOthers(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.incrementsSize(
          new DynamicArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("into middle")
    class IntoMiddle
    {
      @ParameterizedTest
      @DisplayName("inserts into correct index")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void insertsIntoCorrectIndex(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.insertsIntoCorrectIndex(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.doesNotChangeOthers(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.incrementsSize(
          new DynamicArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("into second half")
    class IntoSecondHalf
    {
      @ParameterizedTest
      @DisplayName("inserts into correct index")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void insertsIntoCorrectIndex(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.insertsIntoCorrectIndex(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.doesNotChangeOthers(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.incrementsSize(
          new DynamicArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("last")
    class Last
    {
      @ParameterizedTest
      @DisplayName("inserts as last")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void insertsAsLast(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.insertsAsLast(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.doesNotChangeOthers(
          new DynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.incrementsSize(
          new DynamicArray<>(array),
          item);
      }
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove
  {
    @ParameterizedTest
    @DisplayName("throws if empty")
    @MethodSource("dsa.lib.Examples#emptyArraysAndInts")
    <Item> void throwsIfEmpty(Item[] emptyArray, int index)
    {
      DynamicSequenceTests.Remove.throwsIfEmpty(
        new DynamicArray<>(emptyArray),
        index);
    }

    @Nested
    @DisplayName("first")
    class First
    {
      @ParameterizedTest
      @DisplayName("removes first")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void removesFirst(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.removesFirst(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.doesNotChangeOthers(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.decrementsSize(
          new DynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("from first half")
    class FromFirstHalf
    {
      @ParameterizedTest
      @DisplayName("removes from correct index")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void removesFromCorrectIndex(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.removesFromCorrectIndex(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.doesNotChangeOthers(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.decrementsSize(
          new DynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("from middle")
    class FromMiddle
    {
      @ParameterizedTest
      @DisplayName("removes from correct index")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void removesFromCorrectIndex(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.removesFromCorrectIndex(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.doesNotChangeOthers(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.decrementsSize(
          new DynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("from second half")
    class FromSecondHalf
    {
      @ParameterizedTest
      @DisplayName("removes from correct index")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void removesFromCorrectIndex(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.removesFromCorrectIndex(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.doesNotChangeOthers(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.decrementsSize(
          new DynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("last")
    class Last
    {
      @ParameterizedTest
      @DisplayName("removes last")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void removesLast(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.removesLast(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.doesNotChangeOthers(
          new DynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.decrementsSize(
          new DynamicArray<>(nonEmptyArray));
      }
    }
  }
}
