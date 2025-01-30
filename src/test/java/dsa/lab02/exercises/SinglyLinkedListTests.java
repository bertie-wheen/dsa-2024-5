package dsa.lab02.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import dsa.lab02.base.LinkedListTests;
import dsa.lab02.base.LinkedNodeTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("SinglyLinkedList")
class SinglyLinkedListTests
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
      @MethodSource("dsa.lib.Examples#arraysAndNegativeInts")
      <Item> void ifIndexBelowBound(
        Item[] array,
        int negativeIndex)
      {
        LinkedListTests.Node.Throws.ifIndexBelowBound(
          new SinglyLinkedList<>(array),
          negativeIndex);
      }

      @ParameterizedTest
      @DisplayName("if index above bound")
      @MethodSource("dsa.lib.Examples#arraysAndNonNegativeInts")
      <Item> void ifIndexAboveBound(
        Item[] array,
        int nonNegativeOffset)
      {
        LinkedListTests.Node.Throws.ifIndexAboveBound(
          new SinglyLinkedList<>(array),
          nonNegativeOffset);
      }
    }

    @Nested
    @DisplayName("on first")
    class OnFirst
    {
      @ParameterizedTest
      @DisplayName("gets first")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void getsFirst(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnFirst.getsFirst(
          new SinglyLinkedList<>(nonEmptyArray),
          nonEmptyArray[0]);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnFirst.doesNotChangeItems(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnFirst.doesNotChangeSize(
          new SinglyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("in first half")
    class InFirstHalf
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void getsCorrectIndex(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InFirstHalf.getsCorrectIndex(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InFirstHalf.doesNotChangeItems(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InFirstHalf.doesNotChangeSize(
          new SinglyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("in middle")
    class InMiddle
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void getsCorrectIndex(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InMiddle.getsCorrectIndex(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InMiddle.doesNotChangeItems(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InMiddle.doesNotChangeSize(
          new SinglyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("in second half")
    class InSecondHalf
    {
      @ParameterizedTest
      @DisplayName("gets correct index")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void getsCorrectIndex(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InSecondHalf.getsCorrectIndex(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InSecondHalf.doesNotChangeItems(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.InSecondHalf.doesNotChangeSize(
          new SinglyLinkedList<>(nonEmptyArray));
      }
    }

    @Nested
    @DisplayName("on last")
    class OnLast
    {
      @ParameterizedTest
      @DisplayName("gets last")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void getsLast(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnLast.getsLast(
          new SinglyLinkedList<>(nonEmptyArray),
          nonEmptyArray[nonEmptyArray.length - 1]);
      }

      @ParameterizedTest
      @DisplayName("does not change items")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeItems(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnLast.doesNotChangeItems(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeSize(
        Item[] nonEmptyArray)
      {
        LinkedListTests.Node.OnLast.doesNotChangeSize(
          new SinglyLinkedList<>(nonEmptyArray));
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
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void insertsAsFirst(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.insertsAsFirst(
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.doesNotChangeOthers(
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.First.incrementsSize(
          new SinglyLinkedList<>(array),
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
          new SinglyLinkedList<>(array),
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
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoFirstHalf.incrementsSize(
          new SinglyLinkedList<>(array),
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
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.doesNotChangeOthers(
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoMiddle.incrementsSize(
          new SinglyLinkedList<>(array),
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
          new SinglyLinkedList<>(array),
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
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.IntoSecondHalf.incrementsSize(
          new SinglyLinkedList<>(array),
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
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void doesNotChangeOthers(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.doesNotChangeOthers(
          new SinglyLinkedList<>(array),
          item);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @MethodSource("dsa.lib.Examples#arraysAndItems")
      <Item> void incrementsSize(Item[] array, Item item)
      {
        DynamicSequenceTests.Insert.Last.incrementsSize(
          new SinglyLinkedList<>(array),
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
        new SinglyLinkedList<>(emptyArray),
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
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.doesNotChangeOthers(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.First.decrementsSize(
          new SinglyLinkedList<>(nonEmptyArray));
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
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.doesNotChangeOthers(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromFirstHalf.decrementsSize(
          new SinglyLinkedList<>(nonEmptyArray));
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
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.doesNotChangeOthers(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromMiddle.decrementsSize(
          new SinglyLinkedList<>(nonEmptyArray));
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
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.doesNotChangeOthers(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.FromSecondHalf.decrementsSize(
          new SinglyLinkedList<>(nonEmptyArray));
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
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("does not change others")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.doesNotChangeOthers(
          new SinglyLinkedList<>(nonEmptyArray));
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @MethodSource("dsa.lib.Examples#nonEmptyArrays")
      <Item> void decrementsSize(Item[] nonEmptyArray)
      {
        DynamicSequenceTests.Remove.Last.decrementsSize(
          new SinglyLinkedList<>(nonEmptyArray));
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
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.insertsAsPrevious(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesFirstNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.changesFirstNode(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.doesNotChangeLastNode(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnFirst.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("in first half")
      class InFirstHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.insertsAsPrevious(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.changesFirstNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.doesNotChangeLastNode(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InFirstHalf.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("in middle")
      class InMiddle
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.insertsAsPrevious(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.changesFirstNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.doesNotChangeLastNode(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InMiddle.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("in second half")
      class InSecondHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.insertsAsPrevious(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.changesFirstNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.doesNotChangeLastNode(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.InSecondHalf.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }
      }

      @Nested
      @DisplayName("on last")
      class OnLast
      {
        @ParameterizedTest
        @DisplayName("inserts as previous")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsPrevious(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.insertsAsPrevious(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesFirstNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.changesFirstNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.doesNotChangeLastNode(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            previous);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item previous)
        {
          LinkedNodeTests.InsertPrevious.OnLast.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
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
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.insertsAsNext(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.doesNotChangeFirstNode(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnFirst.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("in first half")
      class InFirstHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.insertsAsNext(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.doesNotChangeFirstNode(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InFirstHalf.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("in middle")
      class InMiddle
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.insertsAsNext(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.doesNotChangeFirstNode(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InMiddle.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("in second half")
      class InSecondHalf
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.insertsAsNext(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.doesNotChangeFirstNode(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.InSecondHalf.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }
      }

      @Nested
      @DisplayName("on last")
      class OnLast
      {
        @ParameterizedTest
        @DisplayName("inserts as next")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void insertsAsNext(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.insertsAsNext(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.doesNotChangeFirstNode(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("changes first node only if should")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void changesLastNode(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.changesLastNode(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherItems(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.doesNotChangeOtherItems(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void doesNotChangeOtherNodes(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }

        @ParameterizedTest
        @DisplayName("increments size")
        @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
        <Item> void incrementsSize(
          Item[] nonEmptyArray,
          Item next)
        {
          LinkedNodeTests.InsertNext.OnLast.incrementsSize(
            new SinglyLinkedList<>(nonEmptyArray),
            next);
        }
      }
    }

    @Nested
    @DisplayName("remove next")
    class RemoveNext
    {
      @Nested
      @DisplayName("on first")
      class OnFirst
      {
        @ParameterizedTest
        @DisplayName("removes next")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void returnsNext(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.OnFirst.returnsNext(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeFirstNode(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.OnFirst.doesNotChangeFirstNode(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.OnFirst.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherItems(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.OnFirst.doesNotChangeOtherItems(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherNodes(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.OnFirst.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void decrementsSize(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.OnFirst.decrementsSize(
            new SinglyLinkedList<>(multiItemArray));
        }
      }

      @Nested
      @DisplayName("in first half")
      class InFirstHalf
      {
        @ParameterizedTest
        @DisplayName("removes next")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void returnsNext(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.returnsNext(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeFirstNode(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.doesNotChangeFirstNode(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherItems(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherNodes(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void decrementsSize(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.decrementsSize(
            new SinglyLinkedList<>(multiItemArray));
        }
      }

      @Nested
      @DisplayName("in middle")
      class InMiddle
      {
        @ParameterizedTest
        @DisplayName("removes next")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void returnsNext(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InMiddle.returnsNext(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeFirstNode(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InMiddle.doesNotChangeFirstNode(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InMiddle.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherItems(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InMiddle.doesNotChangeOtherItems(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherNodes(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InMiddle.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void decrementsSize(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InMiddle.decrementsSize(
            new SinglyLinkedList<>(multiItemArray));
        }
      }

      @Nested
      @DisplayName("in second half")
      class InSecondHalf
      {
        @ParameterizedTest
        @DisplayName("removes next")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void returnsNext(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.returnsNext(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeFirstNode(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.doesNotChangeFirstNode(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void changesLastNodeOnlyIfShould(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherItems(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void doesNotChangeOtherNodes(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(multiItemArray));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @MethodSource("dsa.lib.Examples#multiItemArrays")
        <Item> void decrementsSize(
          Item[] multiItemArray)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.decrementsSize(
            new SinglyLinkedList<>(multiItemArray));
        }
      }

      @Nested
      @DisplayName("on last")
      class OnLast
      {
        @ParameterizedTest
        @DisplayName("throws")
        @MethodSource("dsa.lib.Examples#nonEmptyArrays")
        <Item> void throws_(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.RemoveNext.OnLast.throws_(
            new SinglyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @MethodSource("dsa.lib.Examples#nonEmptyArrays")
        <Item> void doesNotChangeFirstNode(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeFirstNode(
            new SinglyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @MethodSource("dsa.lib.Examples#nonEmptyArrays")
        <Item> void doesNotChangeLastNode(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeLastNode(
            new SinglyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change items")
        @MethodSource("dsa.lib.Examples#nonEmptyArrays")
        <Item> void doesNotChangeItems(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeItems(
            new SinglyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change nodes")
        @MethodSource("dsa.lib.Examples#nonEmptyArrays")
        <Item> void doesNotChangeNodes(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeNodes(
            new SinglyLinkedList<>(nonEmptyArray));
        }

        @ParameterizedTest
        @DisplayName("does not change size")
        @MethodSource("dsa.lib.Examples#nonEmptyArrays")
        <Item> void doesNotChangeSize(
          Item[] nonEmptyArray)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeSize(
            new SinglyLinkedList<>(nonEmptyArray));
        }
      }
    }
  }
}