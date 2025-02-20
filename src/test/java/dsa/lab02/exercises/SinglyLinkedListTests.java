package dsa.lab02.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import dsa.lab02.base.LinkedListTests;
import dsa.lab02.base.LinkedNodeTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

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
      @FieldSource("dsa.lib.examples.Arrays#AND_NEGATIVE_INTS")
      <Item> void ifIndexBelowBound(Item[] items, int negativeIndex)
      {
        LinkedListTests.Node.Throws.ifIndexBelowBound(
          new SinglyLinkedList<>(items),
          negativeIndex);
      }

      @ParameterizedTest
      @DisplayName("if index above bound")
      @FieldSource("dsa.lib.examples.Arrays#AND_NON_NEGATIVE_INTS")
      <Item> void ifIndexAboveBound(Item[] items, int nonNegativeOffset)
      {
        LinkedListTests.Node.Throws.ifIndexAboveBound(
          new SinglyLinkedList<>(items),
          nonNegativeOffset);
      }
    }

    @ParameterizedTest
    @DisplayName("gets correct index")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void getsCorrectIndex(Item[] items, int index)
    {
      LinkedListTests.Node.getsCorrectIndex(
        new SinglyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change items")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeItems(Item[] items, int index)
    {
      LinkedListTests.Node.doesNotChangeItems(
        new SinglyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change size")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeSize(Item[] items, int index)
    {
      LinkedListTests.Node.doesNotChangeSize(
        new SinglyLinkedList<>(items),
        index);
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
        new SinglyLinkedList<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void doesNotChangeOthers(Item[] items, int index, Item item)
    {
      DynamicSequenceTests.Insert.doesNotChangeOthers(
        new SinglyLinkedList<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void incrementsSize(Item[] items, int index, Item item)
    {
      DynamicSequenceTests.Insert.incrementsSize(
        new SinglyLinkedList<>(items),
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
    @FieldSource("dsa.lib.examples.arrays.Empty#AND_INTS")
    <Item> void throwsIfEmpty(Item[] emptyArray, int index)
    {
      DynamicSequenceTests.Remove.throwsIfEmpty(
        new SinglyLinkedList<>(emptyArray),
        index);
    }

    @ParameterizedTest
    @DisplayName("removes from correct index")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void removesFromCorrectIndex(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.removesFromCorrectIndex(
        new SinglyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeOthers(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.doesNotChangeOthers(
        new SinglyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("decrements size")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void decrementsSize(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.decrementsSize(
        new SinglyLinkedList<>(items),
        index);
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
      @ParameterizedTest
      @DisplayName("inserts as previous")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void insertsAsPrevious(Item[] items, int index, Item previous)
      {
        LinkedNodeTests.InsertPrevious.insertsAsPrevious(
          new SinglyLinkedList<>(items),
          index,
          previous);
      }

      @ParameterizedTest
      @DisplayName("changes first node only if should")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void changesFirstNodeOnlyIfShould(
        Item[] items,
        int index,
        Item previous)
      {
        LinkedNodeTests.InsertPrevious.changesFirstNodeOnlyIfShould(
          new SinglyLinkedList<>(items),
          index,
          previous);
      }

      @ParameterizedTest
      @DisplayName("does not change last node")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeLastNode(Item[] items, int index, Item previous)
      {
        LinkedNodeTests.InsertPrevious.doesNotChangeLastNode(
          new SinglyLinkedList<>(items),
          index,
          previous);
      }

      @ParameterizedTest
      @DisplayName("does not change other items")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeOtherItems(
        Item[] items,
        int index,
        Item previous)
      {
        LinkedNodeTests.InsertPrevious.doesNotChangeOtherItems(
          new SinglyLinkedList<>(items),
          index,
          previous);
      }

      @ParameterizedTest
      @DisplayName("does not change other nodes")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeOtherNodes(
        Item[] items,
        int index,
        Item previous)
      {
        LinkedNodeTests.InsertPrevious.doesNotChangeOtherNodes(
          new SinglyLinkedList<>(items),
          index,
          previous);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void incrementsSize(Item[] items, int index, Item previous)
      {
        LinkedNodeTests.InsertPrevious.incrementsSize(
          new SinglyLinkedList<>(items),
          index,
          previous);
      }
    }

    @Nested
    @DisplayName("insert next")
    class InsertNext
    {
      @ParameterizedTest
      @DisplayName("inserts as next")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void insertsAsNext(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.insertsAsNext(
          new SinglyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("does not change last node")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeFirstNode(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.doesNotChangeFirstNode(
          new SinglyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("changes first node only if should")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void changesLastNodeOnlyIfShould(
        Item[] items,
        int index,
        Item next)
      {
        LinkedNodeTests.InsertNext.changesLastNodeOnlyIfShould(
          new SinglyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("does not change other items")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeOtherItems(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.doesNotChangeOtherItems(
          new SinglyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("does not change other nodes")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeOtherNodes(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.doesNotChangeOtherNodes(
          new SinglyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void incrementsSize(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.incrementsSize(
          new SinglyLinkedList<>(items),
          index,
          next);
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
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void returnsNext(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnFirst.returnsNext(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeFirstNode(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnFirst.doesNotChangeFirstNode(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void changesLastNodeOnlyIfShould(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnFirst.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherItems(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnFirst.doesNotChangeOtherItems(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherNodes(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnFirst.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void decrementsSize(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnFirst.decrementsSize(
            new SinglyLinkedList<>(items));
        }
      }

      @Nested
      @DisplayName("in first half")
      class InFirstHalf
      {
        @ParameterizedTest
        @DisplayName("removes next")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void returnsNext(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.returnsNext(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeFirstNode(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.doesNotChangeFirstNode(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void changesLastNodeOnlyIfShould(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherItems(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherNodes(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void decrementsSize(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InFirstHalf.decrementsSize(
            new SinglyLinkedList<>(items));
        }
      }

      @Nested
      @DisplayName("in middle")
      class InMiddle
      {
        @ParameterizedTest
        @DisplayName("removes next")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void returnsNext(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InMiddle.returnsNext(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeFirstNode(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InMiddle.doesNotChangeFirstNode(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void changesLastNodeOnlyIfShould(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InMiddle.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherItems(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InMiddle.doesNotChangeOtherItems(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherNodes(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InMiddle.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void decrementsSize(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InMiddle.decrementsSize(
            new SinglyLinkedList<>(items));
        }
      }

      @Nested
      @DisplayName("in second half")
      class InSecondHalf
      {
        @ParameterizedTest
        @DisplayName("removes next")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void returnsNext(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.returnsNext(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeFirstNode(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.doesNotChangeFirstNode(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("changes last node only if should")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void changesLastNodeOnlyIfShould(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.changesLastNodeOnlyIfShould(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other items")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherItems(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.doesNotChangeOtherItems(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change other nodes")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void doesNotChangeOtherNodes(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.doesNotChangeOtherNodes(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("decrements size")
        @FieldSource("dsa.lib.examples.Arrays#MULTI_ITEM")
        <Item> void decrementsSize(Item[] items)
        {
          LinkedNodeTests.RemoveNext.InSecondHalf.decrementsSize(
            new SinglyLinkedList<>(items));
        }
      }

      @Nested
      @DisplayName("on last")
      class OnLast
      {
        @ParameterizedTest
        @DisplayName("throws")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void throws_(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnLast.throws_(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change first node")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeFirstNode(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeFirstNode(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change last node")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeLastNode(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeLastNode(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change items")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeItems(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeItems(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change nodes")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeNodes(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeNodes(
            new SinglyLinkedList<>(items));
        }

        @ParameterizedTest
        @DisplayName("does not change size")
        @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
        <Item> void doesNotChangeSize(Item[] items)
        {
          LinkedNodeTests.RemoveNext.OnLast.doesNotChangeSize(
            new SinglyLinkedList<>(items));
        }
      }
    }
  }
}