package dsa.lab02.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import dsa.lab02.base.LinkedListTests;
import dsa.lab02.base.LinkedNodeTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("DoublyLinkedList")
class DoublyLinkedListTests
{
  @Nested
  @DisplayName("node")
  class Node
  {
    @Nested
    @DisplayName("throws")
    public class Throws
    {
      @ParameterizedTest
      @DisplayName("if index below bound")
      @FieldSource("dsa.lib.examples.Arrays#AND_NEGATIVE_INTS")
      <Item> void ifIndexBelowBound(
        Item[] array,
        int negativeIndex)
      {
        LinkedListTests.Node.Throws.ifIndexBelowBound(
          new DoublyLinkedList<>(array),
          negativeIndex);
      }

      @ParameterizedTest
      @DisplayName("if index above bound")
      @FieldSource("dsa.lib.examples.Arrays#AND_NON_NEGATIVE_INTS")
      <Item> void ifIndexAboveBound(
        Item[] array,
        int nonNegativeOffset)
      {
        LinkedListTests.Node.Throws.ifIndexAboveBound(
          new DoublyLinkedList<>(array),
          nonNegativeOffset);
      }
    }

    @Nested
    @DisplayName("on first")
    class OnFirst
    {
      @ParameterizedTest
      @DisplayName("gets first")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsFirst(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnFirst.getsFirst(
          new DoublyLinkedList<>(nonEmptyArray),
          nonEmptyArray[0]);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnFirst.doesNotChangeItems(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnFirst.doesNotChangeSize(
          new DoublyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("in first half")
    class InFirstHalf
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsCorrectIndex(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InFirstHalf.getsCorrectIndex(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InFirstHalf.doesNotChangeItems(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InFirstHalf.doesNotChangeSize(
          new DoublyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("in middle")
    class InMiddle
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsCorrectIndex(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InMiddle.getsCorrectIndex(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InMiddle.doesNotChangeItems(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InMiddle.doesNotChangeSize(
          new DoublyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("in second half")
    class InSecondHalf
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsCorrectIndex(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InSecondHalf.getsCorrectIndex(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InSecondHalf.doesNotChangeItems(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InSecondHalf.doesNotChangeSize(
          new DoublyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("on last")
    class OnLast
    {
      @ParameterizedTest
      @DisplayName("gets last")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void getsLast(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnLast.getsLast(
          new DoublyLinkedList<>(nonEmptyArray),
          nonEmptyArray[nonEmptyArray.length - 1]);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnLast.doesNotChangeItems(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnLast.doesNotChangeSize(
          new DoublyLinkedList<>(nonEmptyArray));
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
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.doesNotChangeOthers(
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.incrementsSize(
          new DoublyLinkedList<>(array),
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
          new DoublyLinkedList<>(array),
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
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.incrementsSize(
          new DoublyLinkedList<>(array),
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
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.doesNotChangeOthers(
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.incrementsSize(
          new DoublyLinkedList<>(array),
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
          new DoublyLinkedList<>(array),
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
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.incrementsSize(
          new DoublyLinkedList<>(array),
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
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.doesNotChangeOthers(
          new DoublyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.incrementsSize(
          new DoublyLinkedList<>(array),
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
        new DoublyLinkedList<>(emptyArray),
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
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.doesNotChangeOthers(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.decrementsSize(
          new DoublyLinkedList<>(nonEmptyArray));
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
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.doesNotChangeOthers(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.decrementsSize(
          new DoublyLinkedList<>(nonEmptyArray));
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
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.doesNotChangeOthers(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.decrementsSize(
          new DoublyLinkedList<>(nonEmptyArray));
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
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.doesNotChangeOthers(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.decrementsSize(
          new DoublyLinkedList<>(nonEmptyArray));
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
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.doesNotChangeOthers(
          new DoublyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.decrementsSize(
          new DoublyLinkedList<>(nonEmptyArray));
      }
    }
  }

  @Nested
  @DisplayName("Node")
  class LinkedNode
  {
    @Nested
    @DisplayName("insert previous")
    class InsertPrevious
    {
      @Nested
      @DisplayName("on first")
      class OnFirst
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.insertsAsPrevious(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesFirstNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.changesFirstNode(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.doesNotChangeLastNode(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("in first half")
      class InFirstHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.insertsAsPrevious(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.doesNotChangeLastNode(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("in middle")
      class InMiddle
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.insertsAsPrevious(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.doesNotChangeLastNode(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("in second half")
      class InSecondHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.insertsAsPrevious(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.doesNotChangeLastNode(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("on last")
      class OnLast
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.insertsAsPrevious(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.doesNotChangeLastNode(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            previous);
        }
      }
    }

    @Nested
    @DisplayName("insert next")
    class InsertNext
    {
      @Nested
      @DisplayName("on first")
      class OnFirst
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.insertsAsNext(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.doesNotChangeFirstNode(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("in first half")
      class InFirstHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.insertsAsNext(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.doesNotChangeFirstNode(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("in middle")
      class InMiddle
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.insertsAsNext(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.doesNotChangeFirstNode(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("in second half")
      class InSecondHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.insertsAsNext(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.doesNotChangeFirstNode(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("on last")
      class OnLast
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.insertsAsNext(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.doesNotChangeFirstNode(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void changesLastNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.changesLastNode(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.incrementsSize(
            new DoublyLinkedList<>(nonEmptyArray),
            next);
        }
      }
    }

    @Nested
    @DisplayName("remove")
    class Remove
    {
      @Nested
      @DisplayName("on first")
      class OnFirst
      {
        @ParameterizedTest
        @DisplayName("returns item")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void returnsItem(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnFirst.returnsItem(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnFirst.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnFirst.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnFirst.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnFirst.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void decrementsSize(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnFirst.decrementsSize(
            new DoublyLinkedList<>(nonEmptyArray));
        }
      }

      @Nested
      @DisplayName("in first half")
      class InFirstHalf
      {
        @ParameterizedTest
        @DisplayName("returns item")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void returnsItem(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InFirstHalf.returnsItem(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InFirstHalf.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InFirstHalf.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InFirstHalf.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InFirstHalf.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void decrementsSize(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InFirstHalf.decrementsSize(
            new DoublyLinkedList<>(nonEmptyArray));
        }
      }

      @Nested
      @DisplayName("in middle")
      class InMiddle
      {
        @ParameterizedTest
        @DisplayName("returns item")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void returnsItem(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InMiddle.returnsItem(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InMiddle.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InMiddle.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InMiddle.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InMiddle.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void decrementsSize(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InMiddle.decrementsSize(
            new DoublyLinkedList<>(nonEmptyArray));
        }
      }

      @Nested
      @DisplayName("in second half")
      class InSecondHalf
      {
        @ParameterizedTest
        @DisplayName("returns item")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void returnsItem(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InSecondHalf.returnsItem(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InSecondHalf.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InSecondHalf.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InSecondHalf.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InSecondHalf.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void decrementsSize(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.InSecondHalf.decrementsSize(
            new DoublyLinkedList<>(nonEmptyArray));
        }
      }

      @Nested
      @DisplayName("on last")
      class OnLast
      {
        @ParameterizedTest
        @DisplayName("returns item")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void returnsItem(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnLast.returnsItem(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnLast.changesFirstNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnLast.changesLastNodeOnlyIfShould(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnLast.doesNotChangeOtherItems(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnLast.doesNotChangeOtherNodes(
            new DoublyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void decrementsSize(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.Remove.OnLast.decrementsSize(
            new DoublyLinkedList<>(nonEmptyArray));
        }
      }
    }
  }
}
