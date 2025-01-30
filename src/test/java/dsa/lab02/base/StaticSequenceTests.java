package dsa.lab02.base;

import static dsa.lib.Misc.addSaturating;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          others[i] = nonEmptyStaticSequence.get(i);
        }
        nonEmptyStaticSequence.first();
        for (int i = 0; i < size; i++)
        {
          assertEquals(others[i], nonEmptyStaticSequence.get(i));
        }
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

      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 4;
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          items[i] = nonEmptyStaticSequence.get(i);
        }
        nonEmptyStaticSequence.get(index);
        for (int i = 0; i < size; i++)
        {
          assertEquals(items[i], nonEmptyStaticSequence.get(i));
        }
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

      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 2;
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          others[i] = nonEmptyStaticSequence.get(i);
        }
        nonEmptyStaticSequence.get(index);
        for (int i = 0; i < size; i++)
        {
          assertEquals(others[i], nonEmptyStaticSequence.get(i));
        }
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

      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        int index = 3 * size / 4;
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          items[i] = nonEmptyStaticSequence.get(i);
        }
        nonEmptyStaticSequence.get(index);
        for (int i = 0; i < size; i++)
        {
          assertEquals(items[i], nonEmptyStaticSequence.get(i));
        }
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

      public static <Item> void doesNotChangeItems(
        StaticSequence<Item> nonEmptyStaticSequence)
      {
        int size = nonEmptyStaticSequence.size();
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          others[i] = nonEmptyStaticSequence.get(i);
        }
        nonEmptyStaticSequence.last();
        for (int i = 0; i < size; i++)
        {
          assertEquals(others[i], nonEmptyStaticSequence.get(i));
        }
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

      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item first)
      {
        int size = nonEmptyStaticSequence.size();
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size - 1];
        for (int i = 0; i < size - 1; i++)
        {
          others[i] = nonEmptyStaticSequence.get(i + 1);
        }
        nonEmptyStaticSequence.setFirst(first);
        for (int i = 0; i < size - 1; i++)
        {
          assertEquals(others[i], nonEmptyStaticSequence.get(i + 1));
        }
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

      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 4;
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[size - 1];
        for (int i = 0; i < size - 1; i++)
        {
          items[i] = nonEmptyStaticSequence.get(i < index ? i : i + 1);
        }
        nonEmptyStaticSequence.set(index, item);
        for (int i = 0; i < size - 1; i++)
        {
          assertEquals(
            items[i],
            nonEmptyStaticSequence.get(i < index ? i : i + 1));
        }
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

      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        int index = size / 2;
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size - 1];
        for (int i = 0; i < size - 1; i++)
        {
          others[i] = nonEmptyStaticSequence.get(i < index ? i : i + 1);
        }
        nonEmptyStaticSequence.set(index, item);
        for (int i = 0; i < size - 1; i++)
        {
          assertEquals(
            others[i],
            nonEmptyStaticSequence.get(i < index ? i : i + 1));
        }
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

      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item item)
      {
        int size = nonEmptyStaticSequence.size();
        int index = 3 * size / 4;
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[size - 1];
        for (int i = 0; i < size - 1; i++)
        {
          items[i] = nonEmptyStaticSequence.get(i < index ? i : i + 1);
        }
        nonEmptyStaticSequence.set(index, item);
        for (int i = 0; i < size - 1; i++)
        {
          assertEquals(
            items[i],
            nonEmptyStaticSequence.get(i < index ? i : i + 1));
        }
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

      public static <Item> void doesNotChangeOthers(
        StaticSequence<Item> nonEmptyStaticSequence,
        Item last)
      {
        int size = nonEmptyStaticSequence.size();
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size - 1];
        for (int i = 0; i < size - 1; i++)
        {
          others[i] = nonEmptyStaticSequence.get(i);
        }
        nonEmptyStaticSequence.setLast(last);
        for (int i = 0; i < size - 1; i++)
        {
          assertEquals(others[i], nonEmptyStaticSequence.get(i));
        }
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
