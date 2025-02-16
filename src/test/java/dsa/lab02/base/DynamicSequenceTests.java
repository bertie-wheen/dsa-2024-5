package dsa.lab02.base;

import dsa.lib.Iterators;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicSequenceTests
{
  public static class Insert
  {
    public static class First
    {
      public static <Item> void insertsAsFirst(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        dynamicSequence.insertFirst(item);
        assertEquals(item, dynamicSequence.first());
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        Item[] others = Iterators.toArray(dynamicSequence, size);
        dynamicSequence.insertFirst(item);
        assertArrayEquals(
          others,
          Iterators.toArray(Iterators.skipIndex(0, dynamicSequence), size));
      }

      public static <Item> void incrementsSize(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        dynamicSequence.insertFirst(item);
        assertEquals(size + 1, dynamicSequence.size());
      }
    }

    public static class IntoFirstHalf
    {
      public static <Item> void insertsIntoCorrectIndex(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int index = dynamicSequence.size() / 4;
        dynamicSequence.insert(index, item);
        assertEquals(item, dynamicSequence.get(index));
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        int index = size / 4;
        Item[] others = Iterators.toArray(dynamicSequence, size);
        dynamicSequence.insert(index, item);
        assertArrayEquals(
          others,
          Iterators.toArray(Iterators.skipIndex(index, dynamicSequence), size));
      }

      public static <Item> void incrementsSize(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        dynamicSequence.insert(size / 4, item);
        assertEquals(size + 1, dynamicSequence.size());
      }
    }

    public static class IntoMiddle
    {
      public static <Item> void insertsIntoCorrectIndex(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int index = dynamicSequence.size() / 2;
        dynamicSequence.insert(index, item);
        assertEquals(item, dynamicSequence.get(index));
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        int index = size / 2;
        Item[] others = Iterators.toArray(dynamicSequence, size);
        dynamicSequence.insert(index, item);
        assertArrayEquals(
          others,
          Iterators.toArray(Iterators.skipIndex(index, dynamicSequence), size));
      }

      public static <Item> void incrementsSize(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        dynamicSequence.insert(size / 2, item);
        assertEquals(size + 1, dynamicSequence.size());
      }
    }

    public static class IntoSecondHalf
    {
      public static <Item> void insertsIntoCorrectIndex(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int index = 3 * dynamicSequence.size() / 4;
        dynamicSequence.insert(index, item);
        assertEquals(item, dynamicSequence.get(index));
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        int index = 3 * size / 4;
        Item[] others = Iterators.toArray(dynamicSequence, size);
        dynamicSequence.insert(index, item);
        assertArrayEquals(
          others,
          Iterators.toArray(Iterators.skipIndex(index, dynamicSequence), size));
      }

      public static <Item> void incrementsSize(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        dynamicSequence.insert(3 * size / 4, item);
        assertEquals(size + 1, dynamicSequence.size());
      }
    }

    public static class Last
    {
      public static <Item> void insertsAsLast(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        dynamicSequence.insertLast(item);
        assertEquals(item, dynamicSequence.last());
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        Item[] others = Iterators.toArray(dynamicSequence, size);
        dynamicSequence.insertLast(item);
        assertArrayEquals(
          others,
          Iterators.toArray(Iterators.skipIndex(size, dynamicSequence), size));
      }

      public static <Item> void incrementsSize(
        DynamicSequence<Item> dynamicSequence,
        Item item)
      {
        int size = dynamicSequence.size();
        dynamicSequence.insertLast(item);
        assertEquals(size + 1, dynamicSequence.size());
      }
    }
  }

  public static class Remove
  {
    public static <Item> void throwsIfEmpty(
      DynamicSequence<Item> emptyDynamicSequence,
      int index)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> emptyDynamicSequence.remove(index));
    }

    public static class First
    {
      public static <Item> void removesFirst(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        Item first = nonEmptyDynamicSequence.first();
        assertEquals(first, nonEmptyDynamicSequence.removeFirst());
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        Item[] others = Iterators.toArray(
          Iterators.skipIndex(0, nonEmptyDynamicSequence),
          size - 1);
        nonEmptyDynamicSequence.removeFirst();
        assertArrayEquals(
          others,
          Iterators.toArray(nonEmptyDynamicSequence, size - 1));
      }

      public static <Item> void decrementsSize(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        nonEmptyDynamicSequence.removeFirst();
        assertEquals(size - 1, nonEmptyDynamicSequence.size());
      }
    }

    public static class FromFirstHalf
    {
      public static <Item> void removesFromCorrectIndex(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int index = nonEmptyDynamicSequence.size() / 4;
        Item item = nonEmptyDynamicSequence.get(index);
        assertEquals(item, nonEmptyDynamicSequence.remove(index));
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        int index = size / 4;
        Item[] others = Iterators.toArray(
          Iterators.skipIndex(index, nonEmptyDynamicSequence),
          size - 1);
        nonEmptyDynamicSequence.remove(index);
        assertArrayEquals(
          others,
          Iterators.toArray(nonEmptyDynamicSequence, size - 1));
      }

      public static <Item> void decrementsSize(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        nonEmptyDynamicSequence.remove(size / 4);
        assertEquals(size - 1, nonEmptyDynamicSequence.size());
      }
    }

    public static class FromMiddle
    {
      public static <Item> void removesFromCorrectIndex(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int index = nonEmptyDynamicSequence.size() / 2;
        Item item = nonEmptyDynamicSequence.get(index);
        assertEquals(item, nonEmptyDynamicSequence.remove(index));
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        int index = size / 2;
        Item[] others = Iterators.toArray(
          Iterators.skipIndex(index, nonEmptyDynamicSequence),
          size - 1);
        nonEmptyDynamicSequence.remove(index);
        assertArrayEquals(
          others,
          Iterators.toArray(nonEmptyDynamicSequence, size - 1));
      }

      public static <Item> void decrementsSize(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        nonEmptyDynamicSequence.remove(size / 2);
        assertEquals(size - 1, nonEmptyDynamicSequence.size());
      }
    }

    public static class FromSecondHalf
    {
      public static <Item> void removesFromCorrectIndex(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int index = nonEmptyDynamicSequence.size() / 4;
        Item item = nonEmptyDynamicSequence.get(index);
        assertEquals(item, nonEmptyDynamicSequence.remove(index));
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        int index = size / 4;
        Item[] others = Iterators.toArray(
          Iterators.skipIndex(index, nonEmptyDynamicSequence),
          size - 1);
        nonEmptyDynamicSequence.remove(index);
        assertArrayEquals(
          others,
          Iterators.toArray(nonEmptyDynamicSequence, size - 1));
      }

      public static <Item> void decrementsSize(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        nonEmptyDynamicSequence.remove(size / 4);
        assertEquals(size - 1, nonEmptyDynamicSequence.size());
      }
    }

    public static class Last
    {
      public static <Item> void removesLast(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        Item last = nonEmptyDynamicSequence.last();
        assertEquals(last, nonEmptyDynamicSequence.removeLast());
      }

      public static <Item> void doesNotChangeOthers(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        Item[] others = Iterators.toArray(
          Iterators.skipIndex(size - 1, nonEmptyDynamicSequence),
          size - 1);
        nonEmptyDynamicSequence.removeLast();
        assertArrayEquals(
          others,
          Iterators.toArray(nonEmptyDynamicSequence, size - 1));
      }

      public static <Item> void decrementsSize(
        DynamicSequence<Item> nonEmptyDynamicSequence)
      {
        int size = nonEmptyDynamicSequence.size();
        nonEmptyDynamicSequence.removeLast();
        assertEquals(size - 1, nonEmptyDynamicSequence.size());
      }
    }
  }
}
