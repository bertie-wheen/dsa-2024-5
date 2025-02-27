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

    public static <Item> void getsCorrectIndex(
      StaticSequence<Item> nonEmptyStaticSequence,
      Item[] items,
      int index)
    {
      assertEquals(items[index], nonEmptyStaticSequence.get(index));
    }

    public static <Item> void doesNotChangeItems(
      StaticSequence<Item> nonEmptyStaticSequence,
      int index)
    {
      Item[] items = Iterators.toArray(nonEmptyStaticSequence);
      nonEmptyStaticSequence.get(index);
      assertArrayEquals(items, Iterators.toArray(nonEmptyStaticSequence));
    }

    public static <Item> void doesNotChangeSize(
      StaticSequence<Item> nonEmptyStaticSequence,
      int index)
    {
      int size = nonEmptyStaticSequence.size();
      nonEmptyStaticSequence.get(index);
      assertEquals(size, nonEmptyStaticSequence.size());
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

    public static <Item> void setsCorrectIndex(
      StaticSequence<Item> nonEmptyStaticSequence,
      int index,
      Item item)
    {
      nonEmptyStaticSequence.set(index, item);
      assertEquals(item, nonEmptyStaticSequence.get(index));
    }

    public static <Item> void doesNotChangeOthers(
      StaticSequence<Item> nonEmptyStaticSequence,
      int index,
      Item item)
    {
      Item[] others =
        Iterators.toArray(Iterators.skipIndex(index, nonEmptyStaticSequence));
      nonEmptyStaticSequence.set(index, item);
      assertArrayEquals(
        others,
        Iterators.toArray(Iterators.skipIndex(index, nonEmptyStaticSequence)));
    }

    public static <Item> void doesNotChangeSize(
      StaticSequence<Item> nonEmptyStaticSequence,
      int index,
      Item item)
    {
      int size = nonEmptyStaticSequence.size();
      nonEmptyStaticSequence.set(index, item);
      assertEquals(size, nonEmptyStaticSequence.size());
    }
  }
}
