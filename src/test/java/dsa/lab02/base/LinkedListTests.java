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

    public static <Item> void getsCorrectIndex(
      LinkedList<Item> nonEmptyLinkedList,
      int validIndex)
    {
      assertEquals(validIndex, indexOf(nonEmptyLinkedList.node(validIndex)));
    }

    public static <Item> void doesNotChangeItems(
      LinkedList<Item> nonEmptyLinkedList,
      int validIndex)
    {
      int size = nonEmptyLinkedList.size();
      @SuppressWarnings("unchecked")
      Item[] items = (Item[]) new Object[size];
      for (int i = 0; i < size; i++)
      {
        items[i] = nonEmptyLinkedList.get(i);
      }
      nonEmptyLinkedList.node(validIndex);
      for (int i = 0; i < size; i++)
      {
        assertEquals(items[i], nonEmptyLinkedList.get(i));
      }
    }

    public static <Item> void doesNotChangeSize(
      LinkedList<Item> nonEmptyLinkedList,
      int validIndex)
    {
      int size = nonEmptyLinkedList.size();
      nonEmptyLinkedList.node(validIndex);
      assertEquals(size, nonEmptyLinkedList.size());
    }
  }
}
