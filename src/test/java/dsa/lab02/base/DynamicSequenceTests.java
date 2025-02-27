package dsa.lab02.base;

import dsa.lib.Iterators;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicSequenceTests
{
  public static class Insert
  {
    public static <Item> void insertsIntoCorrectIndex(
      DynamicSequence<Item> dynamicSequence,
      int validIndex,
      Item item)
    {
      dynamicSequence.insert(validIndex, item);
      assertEquals(item, dynamicSequence.get(validIndex));
    }

    public static <Item> void doesNotChangeOthers(
      DynamicSequence<Item> dynamicSequence,
      int validIndex,
      Item item)
    {
      Item[] others = Iterators.toArray(dynamicSequence);
      dynamicSequence.insert(validIndex, item);
      assertArrayEquals(
        others,
        Iterators.toArray(Iterators.skipIndex(validIndex, dynamicSequence)));
    }

    public static <Item> void incrementsSize(
      DynamicSequence<Item> dynamicSequence,
      int validIndex,
      Item item)
    {
      int size = dynamicSequence.size();
      dynamicSequence.insert(validIndex, item);
      assertEquals(size + 1, dynamicSequence.size());
    }
  }

  public static class Remove
  {
    // TODO: generalise to OOB
    public static <Item> void throwsIfEmpty(
      DynamicSequence<Item> emptyDynamicSequence,
      int index)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> emptyDynamicSequence.remove(index));
    }

    public static <Item> void removesFromCorrectIndex(
      DynamicSequence<Item> nonEmptyDynamicSequence,
      int validIndex)
    {
      Item item = nonEmptyDynamicSequence.get(validIndex);
      assertEquals(item, nonEmptyDynamicSequence.remove(validIndex));
    }

    public static <Item> void doesNotChangeOthers(
      DynamicSequence<Item> nonEmptyDynamicSequence,
      int validIndex)
    {
      Item[] others = Iterators.toArray(
        Iterators.skipIndex(validIndex, nonEmptyDynamicSequence));
      nonEmptyDynamicSequence.remove(validIndex);
      assertArrayEquals(others, Iterators.toArray(nonEmptyDynamicSequence));
    }

    public static <Item> void decrementsSize(
      DynamicSequence<Item> nonEmptyDynamicSequence,
      int validIndex)
    {
      int size = nonEmptyDynamicSequence.size();
      nonEmptyDynamicSequence.remove(validIndex);
      assertEquals(size - 1, nonEmptyDynamicSequence.size());
    }
  }
}
