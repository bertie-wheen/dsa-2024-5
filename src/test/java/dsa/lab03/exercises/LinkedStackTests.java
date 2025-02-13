package dsa.lab03.exercises;

import dsa.lab03.base.StackTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("LinkedStack")
public class LinkedStackTests
{
  @Nested
  @DisplayName("push")
  class Push
  {
    @ParameterizedTest
    @DisplayName("pushes as top")
    @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
    <Item> void pushesAsTop(Item[] array, Item item)
    {
      StackTests.Push.pushesAsTop(
        new LinkedStack<>(array),
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
    <Item> void doesNotChangeOthers(Item[] array, Item item)
    {
      StackTests.Push.doesNotChangeOthers(
        new LinkedStack<>(array),
        item);
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.Arrays#AND_ITEMS")
    <Item> void incrementsSize(Item[] array, Item item)
    {
      StackTests.Push.incrementsSize(
        new LinkedStack<>(array),
        item);
    }
  }

  @Nested
  @DisplayName("top")
  class Top
  {
    @ParameterizedTest
    @DisplayName("returns top")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void returnsTop(Item[] nonEmptyArray)
    {
      StackTests.Top.returnsTop(
        new LinkedStack<>(nonEmptyArray),
        nonEmptyArray[0]);
    }

    @ParameterizedTest
    @DisplayName("does not change top")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeTop(Item[] nonEmptyArray)
    {
      StackTests.Top.doesNotChangeTop(
        new LinkedStack<>(nonEmptyArray));
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeItems(Item[] nonEmptyArray)
    {
      StackTests.Top.doesNotChangeItems(
        new LinkedStack<>(nonEmptyArray));
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeSize(Item[] nonEmptyArray)
    {
      StackTests.Top.doesNotChangeSize(
        new LinkedStack<>(nonEmptyArray));
    }
  }

  @Nested
  @DisplayName("pop")
  class Pop
  {
    @ParameterizedTest
    @DisplayName("returns top")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void returnsTop(Item[] nonEmptyArray)
    {
      StackTests.Pop.returnsTop(
        new LinkedStack<>(nonEmptyArray),
        nonEmptyArray[0]);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void doesNotChangeOthers(Item[] nonEmptyArray)
    {
      StackTests.Pop.doesNotChangeOthers(
        new LinkedStack<>(nonEmptyArray));
    }

    @ParameterizedTest
    @DisplayName("increments size")
    @FieldSource("dsa.lib.examples.Arrays#NON_EMPTY")
    <Item> void decrementsSize(Item[] nonEmptyArray)
    {
      StackTests.Pop.decrementsSize(
        new LinkedStack<>(nonEmptyArray));
    }
  }
}
