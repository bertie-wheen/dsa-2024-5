package dsa.lab02.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("StaticArray")
public class StaticArrayTests
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
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void insertsAsFirst(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.insertsAsFirst(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.doesNotChangeOthers(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.incrementsSize(
          new StaticArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("into first half")
    class IntoFirstHalf
    {
      @ParameterizedTest
      @DisplayName("inserts into correct index")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void insertsIntoCorrectIndex(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.insertsIntoCorrectIndex(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.doesNotChangeOthers(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.incrementsSize(
          new StaticArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("into middle")
    class IntoMiddle
    {
      @ParameterizedTest
      @DisplayName("inserts into correct index")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void insertsIntoCorrectIndex(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.insertsIntoCorrectIndex(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.doesNotChangeOthers(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.incrementsSize(
          new StaticArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("into second half")
    class IntoSecondHalf
    {
      @ParameterizedTest
      @DisplayName("inserts into correct index")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void insertsIntoCorrectIndex(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.insertsIntoCorrectIndex(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(
        Item[] array,
        Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.doesNotChangeOthers(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.incrementsSize(
          new StaticArray<>(array),
          item);
      }
    }

    @Nested
    @DisplayName("last")
    class Last
    {
      @ParameterizedTest
      @DisplayName("inserts as last")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void insertsAsLast(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.insertsAsLast(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.doesNotChangeOthers(
          new StaticArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.incrementsSize(
          new StaticArray<>(array),
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
    @FieldSource("dsa.lib.examples.arrays.Empty#AND_INTS")
    <Item> void throwsIfEmpty(Item[] emptyArray, int index)
    {
      DynamicSequenceTests.Remove.throwsIfEmpty(
        new StaticArray<>(emptyArray),
        index);
    }

    @Nested
    @DisplayName("first")
    class First
    {
      @ParameterizedTest
      @DisplayName("removes first")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void removesFirst(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.removesFirst(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.doesNotChangeOthers(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.decrementsSize(
          new StaticArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("from first half")
    class FromFirstHalf
    {
      @ParameterizedTest
      @DisplayName("removes from correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void removesFromCorrectIndex(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.removesFromCorrectIndex(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.doesNotChangeOthers(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.decrementsSize(
          new StaticArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("from middle")
    class FromMiddle
    {
      @ParameterizedTest
      @DisplayName("removes from correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void removesFromCorrectIndex(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.removesFromCorrectIndex(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.doesNotChangeOthers(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.decrementsSize(
          new StaticArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("from second half")
    class FromSecondHalf
    {
      @ParameterizedTest
      @DisplayName("removes from correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void removesFromCorrectIndex(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.removesFromCorrectIndex(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.doesNotChangeOthers(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.decrementsSize(
          new StaticArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("last")
    class Last
    {
      @ParameterizedTest
      @DisplayName("removes last")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void removesLast(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.removesLast(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.doesNotChangeOthers(
          new StaticArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.decrementsSize(
          new StaticArray<>(nonEmptyArray));
      }
    }
  }
}