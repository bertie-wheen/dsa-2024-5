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
      <Item> void ifIndexBelowBound(Item[] items, int negativeIndex)
      {
        LinkedListTests.Node.Throws.ifIndexBelowBound(
          new DoublyLinkedList<>(items),
          negativeIndex);
      }

      @ParameterizedTest
      @DisplayName("if index above bound")
      @FieldSource("dsa.lib.examples.Arrays#AND_NON_NEGATIVE_INTS")
      <Item> void ifIndexAboveBound(Item[] items, int nonNegativeOffset)
      {
        LinkedListTests.Node.Throws.ifIndexAboveBound(
          new DoublyLinkedList<>(items),
          nonNegativeOffset);
      }
    }

    @ParameterizedTest
    @DisplayName("gets correct index")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void getsCorrectIndex(Item[] items, int index)
    {
      LinkedListTests.Node.getsCorrectIndex(
        new DoublyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change items")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeItems(Item[] items, int index)
    {
      LinkedListTests.Node.doesNotChangeItems(
        new DoublyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change size")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeSize(Item[] items, int index)
    {
      LinkedListTests.Node.doesNotChangeSize(
        new DoublyLinkedList<>(items),
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
        new DoublyLinkedList<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void doesNotChangeOthers(Item[] items, int index, Item item)
    {
      DynamicSequenceTests.Insert.doesNotChangeOthers(
        new DoublyLinkedList<>(items),
        index,
        item);
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.arrays.AndValidInsertIndices#AND_ITEMS")
    <Item> void incrementsSize(Item[] items, int index, Item item)
    {
      DynamicSequenceTests.Insert.incrementsSize(
        new DoublyLinkedList<>(items),
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
        new DoublyLinkedList<>(emptyArray),
        index);
    }

    @ParameterizedTest
    @DisplayName("removes from correct index")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void removesFromCorrectIndex(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.removesFromCorrectIndex(
        new DoublyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void doesNotChangeOthers(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.doesNotChangeOthers(
        new DoublyLinkedList<>(items),
        index);
    }

    @ParameterizedTest
    @DisplayName("decrements size")
    @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
    <Item> void decrementsSize(Item[] items, int index)
    {
      DynamicSequenceTests.Remove.decrementsSize(
        new DoublyLinkedList<>(items),
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
          new DoublyLinkedList<>(items),
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
          new DoublyLinkedList<>(items),
          index,
          previous);
      }

      @ParameterizedTest
      @DisplayName("does not change last node")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeLastNode(Item[] items, int index, Item previous)
      {
        LinkedNodeTests.InsertPrevious.doesNotChangeLastNode(
          new DoublyLinkedList<>(items),
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
          new DoublyLinkedList<>(items),
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
          new DoublyLinkedList<>(items),
          index,
          previous);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void incrementsSize(Item[] items, int index, Item previous)
      {
        LinkedNodeTests.InsertPrevious.incrementsSize(
          new DoublyLinkedList<>(items),
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
          new DoublyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("does not change last node")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeFirstNode(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.doesNotChangeFirstNode(
          new DoublyLinkedList<>(items),
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
          new DoublyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("does not change other items")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeOtherItems(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.doesNotChangeOtherItems(
          new DoublyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("does not change other nodes")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void doesNotChangeOtherNodes(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.doesNotChangeOtherNodes(
          new DoublyLinkedList<>(items),
          index,
          next);
      }

      @ParameterizedTest
      @DisplayName("increments size")
      @FieldSource("dsa.lib.examples.arrays.AndValidIndices#AND_ITEMS")
      <Item> void incrementsSize(Item[] items, int index, Item next)
      {
        LinkedNodeTests.InsertNext.incrementsSize(
          new DoublyLinkedList<>(items),
          index,
          next);
      }
    }

    @Nested
    @DisplayName("remove")
    class Remove
    {
      @ParameterizedTest
      @DisplayName("returns item")
      @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
      <Item> void returnsItem(Item[] items, int index)
      {
        LinkedNodeTests.Remove.returnsItem(
          new DoublyLinkedList<>(items),
          index);
      }

      @ParameterizedTest
      @DisplayName("changes first node only if should")
      @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
      <Item> void changesFirstNodeOnlyIfShould(Item[] items, int index)
      {
        LinkedNodeTests.Remove.changesFirstNodeOnlyIfShould(
          new DoublyLinkedList<>(items),
          index);
      }

      @ParameterizedTest
      @DisplayName("changes last node only if should")
      @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
      <Item> void changesLastNodeOnlyIfShould(Item[] items, int index)
      {
        LinkedNodeTests.Remove.changesLastNodeOnlyIfShould(
          new DoublyLinkedList<>(items),
          index);
      }

      @ParameterizedTest
      @DisplayName("does not change other items")
      @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
      <Item> void doesNotChangeOtherItems(Item[] items, int index)
      {
        LinkedNodeTests.Remove.doesNotChangeOtherItems(
          new DoublyLinkedList<>(items),
          index);
      }

      @ParameterizedTest
      @DisplayName("does not change other nodes")
      @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
      <Item> void doesNotChangeOtherNodes(Item[] items, int index)
      {
        LinkedNodeTests.Remove.doesNotChangeOtherNodes(
          new DoublyLinkedList<>(items),
          index);
      }

      @ParameterizedTest
      @DisplayName("decrements size")
      @FieldSource("dsa.lib.examples.Arrays#AND_VALID_INDICES")
      <Item> void decrementsSize(Item[] items, int index)
      {
        LinkedNodeTests.Remove.decrementsSize(
          new DoublyLinkedList<>(items),
          index);
      }
    }
  }
}
