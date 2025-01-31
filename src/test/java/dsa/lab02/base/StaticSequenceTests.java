package dsa.lab02.base;

import dsa.lib.Iterators;

import static dsa.lib.Misc.addSaturating;
import static org.junit.jupiter.api.Assertions.*;

public class StaticSequenceTests
{
  public static class Get
  {
    public static class Throws
    {
      public static <Item> void ifIndexBelowBound(
        StaticSequence<Item> staticSequence,
        int negativeIndex)
      {
        assertThrows(
          IndexOutOfBoundsException.class,
          () -> staticSequence.get(negativeIndex));
      }

      public static <Item> void ifIndexAboveBound(
        StaticSequence<Item> staticSequence,
        int nonNegativeOffset)
      {
        int index =
          addSaturating(staticSequence.size(), nonNegativeOffset);
        assertThrows(
          IndexOutOfBoundsException.class,
          () -> staticSequence.get(index));
      }
    }

    public static class First
    {
      public static <Item> void getsFirst(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item first)
      {
        assertEquals(first, nonEmptyStaticSequence.first());
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        Item[] items =
          (Item[]) Iterators.toArray(nonEmptyStaticSequence, size);
        nonEmptyStaticSequence.first();
        assertArrayEquals(
          items,
          Iterators.toArray(nonEmptyStaticSequence, size));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.first();
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class InFirstHalf
    {
      public static <Item> void getsCorrectIndex(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item[] items)
      {
        int index = nonEmptyStaticSequence.size() / 4;
        assertEquals(items[index], nonEmptyStaticSequence.get(index));
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 4;
        Item[] items =
          (Item[]) Iterators.toArray(nonEmptyStaticSequence, size);
        nonEmptyStaticSequence.get(index);
        assertArrayEquals(
          items,
          Iterators.toArray(nonEmptyStaticSequence, size));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.get(size / 4);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class InMiddle
    {
      public static <Item> void getsCorrectIndex(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item[] items)
      {
        int index = nonEmptyStaticSequence.size() / 2;
        assertEquals(items[index], nonEmptyStaticSequence.get(index));
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 2;
        Item[] items =
          (Item[]) Iterators.toArray(nonEmptyStaticSequence, size);
        nonEmptyStaticSequence.get(index);
        assertArrayEquals(
          items,
          Iterators.toArray(nonEmptyStaticSequence, size));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.get(size / 2);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class InSecondHalf
    {
      public static <Item> void getsCorrectIndex(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item[] items)
      {
        int index = 3 * nonEmptyStaticSequence.size() / 4;
        assertEquals(items[index], nonEmptyStaticSequence.get(index));
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        int index = 3 * size / 4;
        Item[] items =
          (Item[]) Iterators.toArray(nonEmptyStaticSequence, size);
        nonEmptyStaticSequence.get(index);
        assertArrayEquals(
          items,
          Iterators.toArray(nonEmptyStaticSequence, size));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.get(3 * size / 4);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class Last
    {
      public static <Item> void getsLast(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item last)
      {
        assertEquals(last, nonEmptyStaticSequence.last());
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        Item[] items =
          (Item[]) Iterators.toArray(nonEmptyStaticSequence, size);
        nonEmptyStaticSequence.last();
        assertArrayEquals(
          items,
          Iterators.toArray(nonEmptyStaticSequence, size));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.last();
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }
  }

  public static class Set
  {
    public static class Throws
    {
      public static <Item> void setThrowsIfIndexBelowBound(
        StaticSequence<Item> staticSequence,
        int negativeIndex,
        Item item)
      {
        assertThrows(
          IndexOutOfBoundsException.class,
          () -> staticSequence.set(negativeIndex, item));
      }

      public static <Item> void setThrowsIfIndexAboveBound(
        StaticSequence<Item> staticSequence,
        int nonNegativeOffset,
        Item item)
      {
        int index =
          addSaturating(staticSequence.size(), nonNegativeOffset);
        assertThrows(
          IndexOutOfBoundsException.class,
          () -> staticSequence.set(index, item));
      }
    }

    public static class First
    {
      public static <Item> void setsFirst(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item first)
      {
        nonEmptyStaticSequence.setFirst(first);
        assertEquals(first, nonEmptyStaticSequence.first());
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item first)
      {
        int size = nonEmptyStaticSequence.size();
        Item[] others =
          (Item[]) Iterators.toArray(
            Iterators.skipIndex(0, nonEmptyStaticSequence),
            size - 1);
        nonEmptyStaticSequence.setFirst(first);
        assertArrayEquals(
          others,
          Iterators.toArray(
            Iterators.skipIndex(0, nonEmptyStaticSequence),
            size - 1));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item first)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.setFirst(first);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class InFirstHalf
    {
      public static <Item> void setsCorrectIndex(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int index = nonEmptyStaticSequence.size() / 4;
        nonEmptyStaticSequence.set(index, item);
        assertEquals(item, nonEmptyStaticSequence.get(index));
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 4;
        Item[] others =
          (Item[]) Iterators.toArray(
            Iterators.skipIndex(index, nonEmptyStaticSequence),
            size - 1);
        nonEmptyStaticSequence.set(index, item);
        assertArrayEquals(
          others,
          Iterators.toArray(
            Iterators.skipIndex(index, nonEmptyStaticSequence),
            size - 1));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.set(size / 4, item);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class InMiddle
    {
      public static <Item> void setsCorrectIndex(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int index = nonEmptyStaticSequence.size() / 2;
        nonEmptyStaticSequence.set(index, item);
        assertEquals(item, nonEmptyStaticSequence.get(index));
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 2;
        Item[] others =
          (Item[]) Iterators.toArray(
            Iterators.skipIndex(index, nonEmptyStaticSequence),
            size - 1);
        nonEmptyStaticSequence.set(index, item);
        assertArrayEquals(
          others,
          Iterators.toArray(
            Iterators.skipIndex(index, nonEmptyStaticSequence),
            size - 1));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.set(size / 2, item);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class InSecondHalf
    {
      public static <Item> void setsCorrectIndex(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int index = 3 * nonEmptyStaticSequence.size() / 4;
        nonEmptyStaticSequence.set(index, item);
        assertEquals(item, nonEmptyStaticSequence.get(index));
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        int index = 3 * size / 4;
        Item[] others =
          (Item[]) Iterators.toArray(
            Iterators.skipIndex(index, nonEmptyStaticSequence),
            size - 1);
        nonEmptyStaticSequence.set(index, item);
        assertArrayEquals(
          others,
          Iterators.toArray(
            Iterators.skipIndex(index, nonEmptyStaticSequence),
            size - 1));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.set(3 * size / 4, item);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }

    public static class Last
    {
      public static <Item> void setsLast(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item last)
      {
        nonEmptyStaticSequence.setLast(last);
        assertEquals(last, nonEmptyStaticSequence.last());
      }

      @SuppressWarnings("unchecked")
      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item last)
      {
        int size = nonEmptyStaticSequence.size();
        Item[] others =
          (Item[]) Iterators.toArray(
            Iterators.skipIndex(size - 1, nonEmptyStaticSequence),
            size - 1);
        nonEmptyStaticSequence.setLast(last);
        assertArrayEquals(
          others,
          Iterators.toArray(
            Iterators.skipIndex(size - 1, nonEmptyStaticSequence),
            size - 1));
      }

      public static <Item> void doesNotChangeSize(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item last)
      {
        int size = nonEmptyStaticSequence.size();
        nonEmptyStaticSequence.setLast(last);
        assertEquals(size, nonEmptyStaticSequence.size());
      }
    }
  }
}
