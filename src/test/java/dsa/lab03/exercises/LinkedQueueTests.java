package dsa.lab03.exercises;

import dsa.lab03.base.QueueTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("LinkedQueue")
public class LinkedQueueTests
{
  @Nested
  @DisplayName("enqueue")
  class Enqueue
  {
    @ParameterizedTest
    @DisplayName("enqueues as front")
    @MethodSource("dsa.lib.Examples#arraysAndItems")
    <Item> void enqueuesAsBack(Item[] array, Item item)
    {
      QueueTests.Enqueue.enqueuesAsBack(
        new LinkedQueue<>(array),
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change front")
    @MethodSource("dsa.lib.Examples#nonEmptyArraysAndItems")
    <Item> void doesNotChangeFront(Item[] nonEmptyArray, Item item)
    {
      QueueTests.Enqueue.doesNotChangeFront(
        new LinkedQueue<>(nonEmptyArray),
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @MethodSource("dsa.lib.Examples#arraysAndItems")
    <Item> void doesNotChangeOthers(Item[] array, Item item)
    {
      QueueTests.Enqueue.doesNotChangeOthers(
        new LinkedQueue<>(array),
        item);
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @MethodSource("dsa.lib.Examples#arraysAndItems")
    <Item> void incrementsSize(Item[] array, Item item)
    {
      QueueTests.Enqueue.incrementsSize(
        new LinkedQueue<>(array),
        item);
    }
  }

  @Nested
  @DisplayName("front")
  class Front
  {
    @ParameterizedTest
    @DisplayName("returns front")
    @MethodSource("dsa.lib.Examples#nonEmptyArrays")
    <Item> void returnsFront(Item[] nonEmptyArray)
    {
      QueueTests.Front.returnsFront(
        new LinkedQueue<>(nonEmptyArray),
        nonEmptyArray[0]);
    }

    @ParameterizedTest
    @DisplayName("does not change front")
    @MethodSource("dsa.lib.Examples#nonEmptyArrays")
    <Item> void doesNotChangeFront(Item[] array)
    {
      QueueTests.Front.doesNotChangeFront(
        new LinkedQueue<>(array));
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @MethodSource("dsa.lib.Examples#nonEmptyArrays")
    <Item> void doesNotChangeItems(Item[] nonEmptyArray)
    {
      QueueTests.Front.doesNotChangeItems(
        new LinkedQueue<>(nonEmptyArray));
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @MethodSource("dsa.lib.Examples#nonEmptyArrays")
    <Item> void doesNotChangeSize(Item[] nonEmptyArray)
    {
      QueueTests.Front.doesNotChangeSize(
        new LinkedQueue<>(nonEmptyArray));
    }
  }

  @Nested
  @DisplayName("dequeue")
  class Dequeue
  {
    @ParameterizedTest
    @DisplayName("returns front")
    @MethodSource("dsa.lib.Examples#nonEmptyArrays")
    <Item> void returnsFront(Item[] nonEmptyArray)
    {
      QueueTests.Dequeue.returnsFront(
        new LinkedQueue<>(nonEmptyArray),
        nonEmptyArray[0]);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @MethodSource("dsa.lib.Examples#nonEmptyArrays")
    <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
    {
      QueueTests.Dequeue.doesNotChangeOthers(
        new LinkedQueue<>(nonEmptyArray));
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @MethodSource("dsa.lib.Examples#nonEmptyArrays")
    <Item> void decrementsSize(Item[] nonEmptyArray)
    {
      QueueTests.Dequeue.decrementsSize(
        new LinkedQueue<>(nonEmptyArray));
    }
  }
}
