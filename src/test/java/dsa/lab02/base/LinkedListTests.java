package dsa.lab02.base;

import static dsa.lib.Misc.addSaturating;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinkedListTests
{
  public static class Node
  {
    private static <Item> int indexOf(LinkedNode<Item> node)
    {
      int index = 0;
      for (LinkedNode<Item> n : node.list().nodes())
      {
        if (node == n)
        {
          return index;
        }
        index++;
      }
      return -1;
    }

    public static class Throws
    {
      public static <Item> void ifIndexBelowBound(
        LinkedList<Item> linkedList,
        int negativeIndex)
      {
        assertThrows(
          IndexOutOfBoundsException.class,
          () -> linkedList.node(negativeIndex));
      }

      public static <Item> void ifIndexAboveBound(
        LinkedList<Item> linkedList,
        int nonNegativeOffset)
      {
        int index = addSaturating(linkedList.size(), nonNegativeOffset);
        assertThrows(
          IndexOutOfBoundsException.class,
          () -> linkedList.node(index));
      }
    }

    public static class OnFirst
    {
      public static <Item> void getsFirst(
        LinkedList<Item> nonEmptyLinkedList,
        Item first)
      {
        assertEquals(first, nonEmptyLinkedList.first());
      }

      public static <Item> void doesNotChangeItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          others[i] = nonEmptyLinkedList.get(i);
        }
        nonEmptyLinkedList.first();
        for (int i = 0; i < size; i++)
        {
          assertEquals(others[i], nonEmptyLinkedList.get(i));
        }
      }

      public static <Item> void doesNotChangeSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        nonEmptyLinkedList.first();
        assertEquals(size, nonEmptyLinkedList.size());
      }
    }

    public static class InFirstHalf
    {
      public static <Item> void getsCorrectIndex(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int index = nonEmptyLinkedList.size() / 4;
        assertEquals(index, indexOf(nonEmptyLinkedList.node(index)));
      }

      public static <Item> void doesNotChangeItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        int index = size / 4;
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          items[i] = nonEmptyLinkedList.get(i);
        }
        nonEmptyLinkedList.node(index);
        for (int i = 0; i < size; i++)
        {
          assertEquals(items[i], nonEmptyLinkedList.get(i));
        }
      }

      public static <Item> void doesNotChangeSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        nonEmptyLinkedList.node(size / 4);
        assertEquals(size, nonEmptyLinkedList.size());
      }
    }

    public static class InMiddle
    {
      public static <Item> void getsCorrectIndex(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int index = nonEmptyLinkedList.size() / 2;
        assertEquals(index, indexOf(nonEmptyLinkedList.node(index)));
      }

      public static <Item> void doesNotChangeItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        int index = size / 2;
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          others[i] = nonEmptyLinkedList.get(i);
        }
        nonEmptyLinkedList.node(index);
        for (int i = 0; i < size; i++)
        {
          assertEquals(others[i], nonEmptyLinkedList.get(i));
        }
      }

      public static <Item> void doesNotChangeSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        nonEmptyLinkedList.node(size / 2);
        assertEquals(size, nonEmptyLinkedList.size());
      }
    }

    public static class InSecondHalf
    {
      public static <Item> void getsCorrectIndex(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int index = 3 * nonEmptyLinkedList.size() / 4;
        assertEquals(index, indexOf(nonEmptyLinkedList.node(index)));
      }

      public static <Item> void doesNotChangeItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        int index = 3 * size / 4;
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          items[i] = nonEmptyLinkedList.get(i);
        }
        nonEmptyLinkedList.node(index);
        for (int i = 0; i < size; i++)
        {
          assertEquals(items[i], nonEmptyLinkedList.get(i));
        }
      }

      public static <Item> void doesNotChangeSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        nonEmptyLinkedList.node(3 * size / 4);
        assertEquals(size, nonEmptyLinkedList.size());
      }
    }

    public static class OnLast
    {
      public static <Item> void getsLast(
        LinkedList<Item> nonEmptyLinkedList,
        Item last)
      {
        assertEquals(last, nonEmptyLinkedList.last());
      }

      public static <Item> void doesNotChangeItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        @SuppressWarnings("unchecked")
        Item[] others = (Item[]) new Object[size];
        for (int i = 0; i < size; i++)
        {
          others[i] = nonEmptyLinkedList.get(i);
        }
        nonEmptyLinkedList.last();
        for (int i = 0; i < size; i++)
        {
          assertEquals(others[i], nonEmptyLinkedList.get(i));
        }
      }

      public static <Item> void doesNotChangeSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        nonEmptyLinkedList.last();
        assertEquals(size, nonEmptyLinkedList.size());
      }
    }
  }
}
