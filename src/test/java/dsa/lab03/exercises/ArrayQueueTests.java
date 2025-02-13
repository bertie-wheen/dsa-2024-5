package dsa.lab03.exercises;

import dsa.lab03.base.QueueTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("ArrayQueue")
public class ArrayQueueTests
{
  @Nested
  @DisplayName("enqueue")
  class Enqueue
  {
    @ParameterizedTest
    @DisplayName("enqueues as front")
    @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
    <Item> void enqueuesAsBack(Item[] array, Item item)
    {
      QueueTests.Enqueue.enqueuesAsBack(
        new ArrayQueue<>(array),
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change front")
    @FieldSource("dsa.lib.examples.arrays.NonEmpty#AND_ITEMS")
    <Item> void doesNotChangeFront(Item[] nonEmptyArray, Item item)
    {
      QueueTests.Enqueue.doesNotChangeFront(
        new ArrayQueue<>(nonEmptyArray),
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
    <Item> void doesNotChangeOthers(Item[] array, Item item)
    {
      QueueTests.Enqueue.doesNotChangeOthers(
        new ArrayQueue<>(array),
        item);
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
    <Item> void incrementsSize(Item[] array, Item item)
    {
      QueueTests.Enqueue.incrementsSize(
        new ArrayQueue<>(array),
        item);
    }
  }

  @Nested
  @DisplayName("front")
  class Front
  {
    @ParameterizedTest
    @DisplayName("returns front")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void returnsFront(Item[] nonEmptyArray)
    {
      QueueTests.Front.returnsFront(
        new ArrayQueue<>(nonEmptyArray),
        nonEmptyArray[0]);
    }

    @ParameterizedTest
    @DisplayName("does not change front")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeFront(Item[] array)
    {
      QueueTests.Front.doesNotChangeFront(
        new ArrayQueue<>(array));
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeItems(Item[] nonEmptyArray)
    {
      QueueTests.Front.doesNotChangeItems(
        new ArrayQueue<>(nonEmptyArray));
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeSize(Item[] nonEmptyArray)
    {
      QueueTests.Front.doesNotChangeSize(
        new ArrayQueue<>(nonEmptyArray));
    }
  }

  @Nested
  @DisplayName("dequeue")
  class Dequeue
  {
    @ParameterizedTest
    @DisplayName("returns front")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void returnsFront(Item[] nonEmptyArray)
    {
      QueueTests.Dequeue.returnsFront(
        new ArrayQueue<>(nonEmptyArray),
        nonEmptyArray[0]);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
    {
      QueueTests.Dequeue.doesNotChangeOthers(
        new ArrayQueue<>(nonEmptyArray));
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void decrementsSize(Item[] nonEmptyArray)
    {
      QueueTests.Dequeue.decrementsSize(
        new ArrayQueue<>(nonEmptyArray));
    }
  }
}
