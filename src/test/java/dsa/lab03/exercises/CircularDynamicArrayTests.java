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
      <Item> void ifBelowBound(Item[] array, int negativeIndex)
      {
        StaticSequenceTests.Get.Throws.ifIndexBelowBound(
          new CircularDynamicArray<>(array),
          negativeIndex);
      }

      @ParameterizedTest
      @DisplayName("if above bound")
      @FieldSource("dsa.lib.examples.Arrays#AND_NON_NEGATIVE_INTS")
      <Item> void ifAboveBound(Item[] array, int nonNegativeOffset)
      {
        StaticSequenceTests.Get.Throws.ifIndexAboveBound(
          new CircularDynamicArray<>(array),
          nonNegativeOffset);
      }
    }

    @Nested
    @DisplayName("first")
    class First
    {
      @ParameterizedTest
      @DisplayName("gets first")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsFirst(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.First.getsFirst(
          new CircularDynamicArray<>(nonEmptyArray), nonEmptyArray[0]);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.First.doesNotChangeItems(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.First.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("first half")
    class InFirstHalf
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsCorrectIndex(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InFirstHalf.getsCorrectIndex(
          new CircularDynamicArray<>(nonEmptyArray),
          nonEmptyArray);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InFirstHalf.doesNotChangeItems(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InFirstHalf.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("middle")
    class InMiddle
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsCorrectIndex(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InMiddle.getsCorrectIndex(
          new CircularDynamicArray<>(nonEmptyArray),
          nonEmptyArray);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InMiddle.doesNotChangeItems(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InMiddle.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("second half")
    class InSecondHalf
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsCorrectIndex(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InSecondHalf.getsCorrectIndex(
          new CircularDynamicArray<>(nonEmptyArray),
          nonEmptyArray);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InSecondHalf.doesNotChangeItems(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.InSecondHalf.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("last")
    class Last
    {
      @ParameterizedTest
      @DisplayName("gets last")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsLast(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.Last.getsLast(
          new CircularDynamicArray<>(nonEmptyArray),
          nonEmptyArray[nonEmptyArray.length - 1]);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.Last.doesNotChangeItems(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray)
      {
        StaticSequenceTests.Get.Last.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray));
      }
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
      <Item> void ifBelowBound(
        Item[] array,
        int negativeIndex,
        Item item)
      {
        StaticSequenceTests.Set.Throws.setThrowsIfIndexBelowBound(
          new CircularDynamicArray<>(array),
          negativeIndex,
          item);
      }

      @ParameterizedTest
      @DisplayName("if above bound")
      @FieldSource("dsa.lib.examples.arrays.AndNonNegativeInts#AND_ITEMS")
      <Item> void ifAboveBound(
        Item[] array,
        int nonNegativeOffset,
        Item item)
      {
        StaticSequenceTests.Set.Throws.setThrowsIfIndexAboveBound(
          new CircularDynamicArray<>(array),
          nonNegativeOffset,
          item);
      }
    }

    @Nested
    @DisplayName("first")
    class First
    {
      @ParameterizedTest
      @DisplayName("sets first")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void setsFirst(Item[] nonEmptyArray, Item first)
      {
        StaticSequenceTests.Set.First.setsFirst(
          new CircularDynamicArray<>(nonEmptyArray), first);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray, Item first)
      {
        StaticSequenceTests.Set.First.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray),
          first);
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray, Item first)
      {
        StaticSequenceTests.Set.First.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray),
          first);
      }
    }

    @Nested
    @DisplayName("first half")
    class InFirstHalf
    {
      @ParameterizedTest
      @DisplayName("sets correct index")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void setsCorrectIndex(Item[] nonEmptyArray, Item item)
      {
        StaticSequenceTests.Set.InFirstHalf.setsCorrectIndex(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeOthers(
        Item[] nonEmptyArray,
        Item item)
      {
        StaticSequenceTests.Set.InFirstHalf.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray, Item item)
      {
        StaticSequenceTests.Set.InFirstHalf.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }
    }

    @Nested
    @DisplayName("middle")
    class InMiddle
    {
      @ParameterizedTest
      @DisplayName("sets correct index")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void setsCorrectIndex(Item[] nonEmptyArray, Item item)
      {
        StaticSequenceTests.Set.InMiddle.setsCorrectIndex(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray, Item item)
      {
        StaticSequenceTests.Set.InMiddle.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray, Item item)
      {
        StaticSequenceTests.Set.InMiddle.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }
    }

    @Nested
    @DisplayName("second half")
    class InSecondHalf
    {
      @ParameterizedTest
      @DisplayName("sets correct index")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void setsCorrectIndex(Item[] nonEmptyArray, Item item)
      {
        StaticSequenceTests.Set.InSecondHalf.setsCorrectIndex(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeOthers(
        Item[] nonEmptyArray,
        Item item)
      {
        StaticSequenceTests.Set.InSecondHalf.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray,
        Item item)
      {
        StaticSequenceTests.Set.InSecondHalf.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray),
          item);
      }
    }

    @Nested
    @DisplayName("last")
    class Last
    {
      @ParameterizedTest
      @DisplayName("sets last")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void setsLast(Item[] nonEmptyArray, Item last)
      {
        StaticSequenceTests.Set.Last.setsLast(
          new CircularDynamicArray<>(nonEmptyArray),
          last);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray, Item last)
      {
        StaticSequenceTests.Set.Last.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray),
          last);
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
      <Item> void doesNotChangeSize(Item[] nonEmptyArray, Item last)
      {
        StaticSequenceTests.Set.Last.doesNotChangeSize(
          new CircularDynamicArray<>(nonEmptyArray),
          last);
      }
    }
  }

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
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.doesNotChangeOthers(
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.incrementsSize(
          new CircularDynamicArray<>(array),
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
          new CircularDynamicArray<>(array),
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
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.incrementsSize(
          new CircularDynamicArray<>(array),
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
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.doesNotChangeOthers(
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.incrementsSize(
          new CircularDynamicArray<>(array),
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
          new CircularDynamicArray<>(array),
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
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.incrementsSize(
          new CircularDynamicArray<>(array),
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
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.doesNotChangeOthers(
          new CircularDynamicArray<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.incrementsSize(
          new CircularDynamicArray<>(array),
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
        new CircularDynamicArray<>(emptyArray),
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
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.decrementsSize(
          new CircularDynamicArray<>(nonEmptyArray));
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
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.decrementsSize(
          new CircularDynamicArray<>(nonEmptyArray));
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
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.decrementsSize(
          new CircularDynamicArray<>(nonEmptyArray));
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
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.decrementsSize(
          new CircularDynamicArray<>(nonEmptyArray));
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
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.doesNotChangeOthers(
          new CircularDynamicArray<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.decrementsSize(
          new CircularDynamicArray<>(nonEmptyArray));
      }
    }
  }
}
