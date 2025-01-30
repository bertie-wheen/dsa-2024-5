package dsa.lab02.base;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedNodeTests
{
  public static class InsertPrevious
  {
    private static <Item> void insertsAsPrevious(
      LinkedNode<Item> linkedNode,
      Item previous)
    {
      linkedNode.insertPrevious(previous);
      assertEquals(previous, linkedNode.previous().item());
    }

    private static <Item> void changesFirstNodeOnlyIfShould(
      LinkedNode<Item> linkedNode,
      Item previous)
    {
      LinkedNode<Item> firstNode = linkedNode.list().firstNode();
      linkedNode.insertPrevious(previous);
      if (linkedNode == firstNode)
      {
        assertSame(linkedNode.previous(), linkedNode.list().firstNode());
      }
      else
      {
        assertSame(firstNode, linkedNode.list().firstNode());
      }
    }

    private static <Item> void doesNotChangeLastNode(
      LinkedNode<Item> linkedNode,
      Item previous)
    {
      LinkedNode<Item> lastNode = linkedNode.list().lastNode();
      linkedNode.insertPrevious(previous);
      assertSame(lastNode, linkedNode.list().lastNode());
    }

    private static <Item> void doesNotChangeOtherItems(
      LinkedNode<Item> linkedNode,
      Item previous)
    {
      @SuppressWarnings("unchecked")
      Item[] items = (Item[]) new Object[linkedNode.list().size()];
      {
        int i = 0;
        for (Item item : linkedNode.list())
        {
          items[i++] = item;
        }
      }
      linkedNode.insertPrevious(previous);
      {
        LinkedNode<Item> previousNode = linkedNode.previous();
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          if (node != previousNode)
          {
            assertEquals(items[i++], node.item());
          }
        }
      }
    }

    private static <Item> void doesNotChangeOtherNodes(
      LinkedNode<Item> linkedNode,
      Item previous)
    {
      @SuppressWarnings("unchecked")
      LinkedNode<Item>[] nodes =
        (LinkedNode<Item>[]) new LinkedNode[linkedNode.list().size()];
      {
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          nodes[i++] = node;
        }
      }
      linkedNode.insertPrevious(previous);
      {
        LinkedNode<Item> previousNode = linkedNode.previous();
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          if (node != previousNode)
          {
            assertSame(nodes[i++], node);
          }
        }
      }
    }

    private static <Item> void incrementsSize(
      LinkedNode<Item> linkedNode,
      Item previous)
    {
      int size = linkedNode.list().size();
      linkedNode.insertPrevious(previous);
      assertEquals(size + 1, linkedNode.list().size());
    }

    public static class OnFirst
    {
      public static <Item> void insertsAsPrevious(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.insertsAsPrevious(
          nonEmptyLinkedList.firstNode(),
          previous);
      }

      public static <Item> void changesFirstNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        LinkedNode<Item> node = nonEmptyLinkedList.firstNode();
        node.insertPrevious(previous);
        assertSame(node.previous(), nonEmptyLinkedList.firstNode());
      }

      public static <Item> void doesNotChangeLastNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeLastNode(
          nonEmptyLinkedList.firstNode(),
          previous);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherItems(
          nonEmptyLinkedList.firstNode(),
          previous);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherNodes(
          nonEmptyLinkedList.firstNode(),
          previous);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.incrementsSize(
          nonEmptyLinkedList.firstNode(),
          previous);
      }
    }

    public static class InFirstHalf
    {
      public static <Item> void insertsAsPrevious(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.insertsAsPrevious(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void doesNotChangeLastNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeLastNode(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.incrementsSize(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          previous);
      }
    }

    public static class InMiddle
    {
      public static <Item> void insertsAsPrevious(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.insertsAsPrevious(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          previous);
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          previous);
      }

      public static <Item> void doesNotChangeLastNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeLastNode(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          previous);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherItems(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          previous);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          previous);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.incrementsSize(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          previous);
      }
    }

    public static class InSecondHalf
    {
      public static <Item> void insertsAsPrevious(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.insertsAsPrevious(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void doesNotChangeLastNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeLastNode(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherItems(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          previous);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.incrementsSize(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          previous);
      }
    }

    public static class OnLast
    {
      public static <Item> void insertsAsPrevious(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.insertsAsPrevious(
          nonEmptyLinkedList.lastNode(),
          previous);
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.lastNode(),
          previous);
      }

      public static <Item> void doesNotChangeLastNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeLastNode(
          nonEmptyLinkedList.lastNode(),
          previous);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherItems(
          nonEmptyLinkedList.lastNode(),
          previous);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.doesNotChangeOtherNodes(
          nonEmptyLinkedList.lastNode(),
          previous);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item previous)
      {
        InsertPrevious.incrementsSize(
          nonEmptyLinkedList.lastNode(),
          previous);
      }
    }
  }

  public static class InsertNext
  {
    private static <Item> void insertsAsNext(
      LinkedNode<Item> linkedNode,
      Item next)
    {
      linkedNode.insertNext(next);
      assertEquals(next, linkedNode.next().item());
    }

    private static <Item> void doesNotChangeFirstNode(
      LinkedNode<Item> linkedNode,
      Item next)
    {
      LinkedNode<Item> firstNode = linkedNode.list().firstNode();
      linkedNode.insertNext(next);
      assertSame(firstNode, linkedNode.list().firstNode());
    }

    private static <Item> void changesLastNodeOnlyIfShould(
      LinkedNode<Item> linkedNode,
      Item next)
    {
      LinkedNode<Item> lastNode = linkedNode.list().lastNode();
      linkedNode.insertNext(next);
      if (linkedNode == lastNode)
      {
        assertSame(linkedNode.next(), linkedNode.list().lastNode());
      }
      else
      {
        assertSame(lastNode, linkedNode.list().lastNode());
      }
    }

    private static <Item> void doesNotChangeOtherItems(
      LinkedNode<Item> linkedNode,
      Item next)
    {
      @SuppressWarnings("unchecked")
      Item[] items = (Item[]) new Object[linkedNode.list().size()];
      {
        int i = 0;
        for (Item item : linkedNode.list())
        {
          items[i++] = item;
        }
      }
      linkedNode.insertNext(next);
      {
        LinkedNode<Item> nextNode = linkedNode.next();
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          if (node != nextNode)
          {
            assertEquals(items[i++], node.item());
          }
        }
      }
    }

    private static <Item> void doesNotChangeOtherNodes(
      LinkedNode<Item> linkedNode,
      Item next)
    {
      @SuppressWarnings("unchecked")
      LinkedNode<Item>[] nodes =
        (LinkedNode<Item>[]) new LinkedNode[linkedNode.list().size()];
      {
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          nodes[i++] = node;
        }
      }
      linkedNode.insertNext(next);
      {
        LinkedNode<Item> nextNode = linkedNode.next();
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          if (node != nextNode)
          {
            assertSame(nodes[i++], node);
          }
        }
      }
    }

    private static <Item> void incrementsSize(
      LinkedNode<Item> linkedNode,
      Item next)
    {
      int size = linkedNode.list().size();
      linkedNode.insertNext(next);
      assertEquals(size + 1, linkedNode.list().size());
    }

    public static class OnFirst
    {
      public static <Item> void insertsAsNext(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.insertsAsNext(
          nonEmptyLinkedList.firstNode(),
          next);
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeFirstNode(
          nonEmptyLinkedList.firstNode(),
          next);
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.firstNode(),
          next);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherItems(
          nonEmptyLinkedList.firstNode(),
          next);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherNodes(
          nonEmptyLinkedList.firstNode(),
          next);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.incrementsSize(
          nonEmptyLinkedList.firstNode(),
          next);
      }
    }

    public static class InFirstHalf
    {
      public static <Item> void insertsAsNext(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.insertsAsNext(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeFirstNode(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.incrementsSize(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4),
          next);
      }
    }

    public static class InMiddle
    {
      public static <Item> void insertsAsNext(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.insertsAsNext(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          next);
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeFirstNode(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          next);
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          next);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherItems(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          next);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          next);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.incrementsSize(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2),
          next);
      }
    }

    public static class InSecondHalf
    {
      public static <Item> void insertsAsNext(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.insertsAsNext(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeFirstNode(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherItems(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          next);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.incrementsSize(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4),
          next);
      }
    }

    public static class OnLast
    {
      public static <Item> void insertsAsNext(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.insertsAsNext(
          nonEmptyLinkedList.lastNode(),
          next);
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeFirstNode(
          nonEmptyLinkedList.lastNode(),
          next);
      }

      public static <Item> void changesLastNode(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        LinkedNode<Item> node = nonEmptyLinkedList.lastNode();
        node.insertNext(next);
        assertSame(node.next(), nonEmptyLinkedList.lastNode());
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherItems(
          nonEmptyLinkedList.lastNode(),
          next);
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.doesNotChangeOtherNodes(
          nonEmptyLinkedList.lastNode(),
          next);
      }

      public static <Item> void incrementsSize(
        LinkedList<Item> nonEmptyLinkedList,
        Item next)
      {
        InsertNext.incrementsSize(
          nonEmptyLinkedList.lastNode(),
          next);
      }
    }
  }

  public static class Remove
  {
    private static <Item> void returnsItem(
      LinkedNode<Item> linkedNode)
    {
      Item next = linkedNode.item();
      assertEquals(next, linkedNode.remove());
    }

    private static <Item> void changesFirstNodeOnlyIfShould(
      LinkedNode<Item> linkedNode)
    {
      LinkedNode<Item> firstNode = linkedNode.list().firstNode();
      LinkedNode<Item> nextNode = linkedNode.next();
      linkedNode.remove();
      if (linkedNode == firstNode)
      {
        assertSame(
          nextNode,
          linkedNode.list().firstNode());
      }
      else
      {
        assertSame(
          firstNode,
          linkedNode.list().firstNode());
      }
    }

    private static <Item> void changesLastNodeOnlyIfShould(
      LinkedNode<Item> linkedNode)
    {
      LinkedNode<Item> lastNode = linkedNode.list().lastNode();
      LinkedNode<Item> previousNode = linkedNode.previous();
      linkedNode.remove();
      if (linkedNode == lastNode)
      {
        assertSame(
          previousNode,
          linkedNode.list().lastNode());
      }
      else
      {
        assertSame(
          lastNode,
          linkedNode.list().lastNode());
      }
    }

    private static <Item> void doesNotChangeOtherItems(
      LinkedNode<Item> linkedNode)
    {
      @SuppressWarnings("unchecked")
      Item[] items = (Item[]) new Object[linkedNode.list().size() - 1];
      {
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          if (node != linkedNode)
          {
            items[i++] = node.item();
          }
        }
      }
      linkedNode.remove();
      {
        int i = 0;
        for (Item item : linkedNode.list())
        {
          assertEquals(items[i++], item);
        }
      }
    }

    private static <Item> void doesNotChangeOtherNodes(
      LinkedNode<Item> linkedNode)
    {
      @SuppressWarnings("unchecked")
      LinkedNode<Item>[] nodes =
        (LinkedNode<Item>[]) new LinkedNode[linkedNode.list().size()];
      {
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          if (node != linkedNode)
          {
            nodes[i++] = node;
          }
        }
      }
      linkedNode.remove();
      {
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          assertSame(nodes[i++], node);
        }
      }
    }

    private static <Item> void decrementsSize(
      LinkedNode<Item> linkedNode)
    {
      int size = linkedNode.list().size();
      linkedNode.remove();
      assertEquals(size - 1, linkedNode.list().size());
    }

    public static class OnFirst
    {
      public static <Item> void returnsItem(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.returnsItem(
          nonEmptyLinkedList.firstNode());
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.firstNode());
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.firstNode());
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherItems(
          nonEmptyLinkedList.firstNode());
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherNodes(
          nonEmptyLinkedList.firstNode());
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.decrementsSize(
          nonEmptyLinkedList.firstNode());
      }
    }

    public static class InFirstHalf
    {
      public static <Item> void returnsItem(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.returnsItem(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.decrementsSize(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 4));
      }
    }

    public static class InMiddle
    {
      public static <Item> void returnsItem(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.returnsItem(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2));
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2));
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2));
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherItems(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2));
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2));
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.decrementsSize(
          nonEmptyLinkedList.node(nonEmptyLinkedList.size() / 2));
      }
    }

    public static class InSecondHalf
    {
      public static <Item> void returnsItem(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.returnsItem(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherItems(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherNodes(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4));
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.decrementsSize(
          nonEmptyLinkedList.node(3 * nonEmptyLinkedList.size() / 4));
      }
    }

    public static class OnLast
    {
      public static <Item> void returnsItem(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.returnsItem(
          nonEmptyLinkedList.lastNode());
      }

      public static <Item> void changesFirstNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesFirstNodeOnlyIfShould(
          nonEmptyLinkedList.lastNode());
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.changesLastNodeOnlyIfShould(
          nonEmptyLinkedList.lastNode());
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherItems(
          nonEmptyLinkedList.lastNode());
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.doesNotChangeOtherNodes(
          nonEmptyLinkedList.lastNode());
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        Remove.decrementsSize(
          nonEmptyLinkedList.lastNode());
      }
    }
  }

  public static class RemoveNext
  {
    private static <Item> void returnsNext(
      LinkedNode<Item> linkedNodeWithNext)
    {
      Item next = linkedNodeWithNext.next().item();
      assertEquals(next, linkedNodeWithNext.removeNext());
    }

    private static <Item> void doesNotChangeFirstNode(
      LinkedNode<Item> linkedNodeWithNext)
    {
      LinkedNode<Item> firstNode = linkedNodeWithNext.list().firstNode();
      linkedNodeWithNext.removeNext();
      assertSame(firstNode, linkedNodeWithNext.list().firstNode());
    }

    private static <Item> void changesLastNodeOnlyIfShould(
      LinkedNode<Item> linkedNodeWithNext)
    {
      LinkedNode<Item> lastNode = linkedNodeWithNext.list().lastNode();
      LinkedNode<Item> nextNode = linkedNodeWithNext.next();
      linkedNodeWithNext.removeNext();
      if (nextNode == lastNode)
      {
        assertSame(
          linkedNodeWithNext,
          linkedNodeWithNext.list().lastNode());
      }
      else
      {
        assertSame(
          lastNode,
          linkedNodeWithNext.list().lastNode());
      }
    }

    private static <Item> void doesNotChangeOtherItems(
      LinkedNode<Item> linkedNodeWithNext)
    {
      @SuppressWarnings("unchecked")
      Item[] items = (Item[]) new Object[linkedNodeWithNext.list().size() - 1];
      {
        LinkedNode<Item> nextNode = linkedNodeWithNext.next();
        int i = 0;
        for (LinkedNode<Item> node : linkedNodeWithNext.list().nodes())
        {
          if (node != nextNode)
          {
            items[i++] = node.item();
          }
        }
      }
      linkedNodeWithNext.removeNext();
      {
        int i = 0;
        for (Item item : linkedNodeWithNext.list())
        {
          assertEquals(items[i++], item);
        }
      }
    }

    private static <Item> void doesNotChangeOtherNodes(
      LinkedNode<Item> linkedNode)
    {
      @SuppressWarnings("unchecked")
      LinkedNode<Item>[] nodes =
        (LinkedNode<Item>[]) new LinkedNode[linkedNode.list().size()];
      {
        LinkedNode<Item> nextNode = linkedNode.next();
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          if (node != nextNode)
          {
            nodes[i++] = node;
          }
        }
      }
      linkedNode.removeNext();
      {
        int i = 0;
        for (LinkedNode<Item> node : linkedNode.list().nodes())
        {
          assertSame(nodes[i++], node);
        }
      }
    }

    private static <Item> void decrementsSize(
      LinkedNode<Item> linkedNodeWithNext)
    {
      int size = linkedNodeWithNext.list().size();
      linkedNodeWithNext.removeNext();
      assertEquals(size - 1, linkedNodeWithNext.list().size());
    }

    public static class OnFirst
    {
      public static <Item> void returnsNext(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.returnsNext(
          multiItemLinkedList.firstNode());
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeFirstNode(
          multiItemLinkedList.firstNode());
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.changesLastNodeOnlyIfShould(
          multiItemLinkedList.firstNode());
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherItems(
          multiItemLinkedList.firstNode());
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherNodes(
          multiItemLinkedList.firstNode());
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.decrementsSize(
          multiItemLinkedList.firstNode());
      }
    }

    public static class InFirstHalf
    {
      public static <Item> void returnsNext(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.returnsNext(
          multiItemLinkedList.node(multiItemLinkedList.size() / 4));
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeFirstNode(
          multiItemLinkedList.node(multiItemLinkedList.size() / 4));
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.changesLastNodeOnlyIfShould(
          multiItemLinkedList.node(multiItemLinkedList.size() / 4));
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherNodes(
          multiItemLinkedList.node(multiItemLinkedList.size() / 4));
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherNodes(
          multiItemLinkedList.node(multiItemLinkedList.size() / 4));
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.decrementsSize(
          multiItemLinkedList.node(multiItemLinkedList.size() / 4));
      }
    }

    public static class InMiddle
    {
      private static <Item> LinkedNode<Item> linkedNodeWithNext(
        LinkedList<Item> multiItemLinkedList)
      {
        int size = multiItemLinkedList.size();
        return multiItemLinkedList.node((size / 2) % (size - 1));
      }

      public static <Item> void returnsNext(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.returnsNext(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeFirstNode(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.changesLastNodeOnlyIfShould(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherItems(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherNodes(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.decrementsSize(
          linkedNodeWithNext(multiItemLinkedList));
      }
    }

    public static class InSecondHalf
    {
      private static <Item> LinkedNode<Item> linkedNodeWithNext(
        LinkedList<Item> multiItemLinkedList)
      {
        int size = multiItemLinkedList.size();
        return multiItemLinkedList.node((3 * size / 4) % (size - 1));
      }

      public static <Item> void returnsNext(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.returnsNext(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeFirstNode(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void changesLastNodeOnlyIfShould(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.changesLastNodeOnlyIfShould(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void doesNotChangeOtherItems(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherItems(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void doesNotChangeOtherNodes(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.doesNotChangeOtherNodes(
          linkedNodeWithNext(multiItemLinkedList));
      }

      public static <Item> void decrementsSize(
        LinkedList<Item> multiItemLinkedList)
      {
        RemoveNext.decrementsSize(
          linkedNodeWithNext(multiItemLinkedList));
      }
    }

    public static class OnLast
    {
      public static <Item> void throws_(
        LinkedList<Item> nonEmptyLinkedList)
      {
        assertThrows(
          NoSuchElementException.class,
          () -> nonEmptyLinkedList.lastNode().removeNext());
      }

      public static <Item> void doesNotChangeFirstNode(
        LinkedList<Item> nonEmptyLinkedList)
      {
        LinkedNode<Item> firstNode = nonEmptyLinkedList.firstNode();
        try
        {
          nonEmptyLinkedList.lastNode().removeNext();
        }
        catch (NoSuchElementException ignored)
        {
        }
        assertSame(firstNode, nonEmptyLinkedList.firstNode());
      }

      public static <Item> void doesNotChangeLastNode(
        LinkedList<Item> nonEmptyLinkedList)
      {
        LinkedNode<Item> lastNode = nonEmptyLinkedList.lastNode();
        try
        {
          lastNode.removeNext();
        }
        catch (NoSuchElementException ignored)
        {
        }
        assertSame(lastNode, nonEmptyLinkedList.lastNode());
      }

      public static <Item> void doesNotChangeItems(
        LinkedList<Item> nonEmptyLinkedList)
      {
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[nonEmptyLinkedList.size()];
        {
          int i = 0;
          for (Item item : nonEmptyLinkedList)
          {
            items[i++] = item;
          }
        }
        try
        {
          nonEmptyLinkedList.lastNode().removeNext();
        }
        catch (NoSuchElementException ignored)
        {
        }
        {
          int i = 0;
          for (Item item : nonEmptyLinkedList)
          {
            assertSame(items[i++], item);
          }
        }
      }

      public static <Item> void doesNotChangeNodes(
        LinkedList<Item> nonEmptyLinkedList)
      {
        @SuppressWarnings("unchecked")
        LinkedNode<Item>[] nodes =
          (LinkedNode<Item>[]) new LinkedNode[nonEmptyLinkedList.size()];
        {
          int i = 0;
          for (LinkedNode<Item> node : nonEmptyLinkedList.nodes())
          {
            nodes[i++] = node;
          }
        }
        try
        {
          nonEmptyLinkedList.lastNode().removeNext();
        }
        catch (NoSuchElementException ignored)
        {
        }
        {
          int i = 0;
          for (LinkedNode<Item> node : nonEmptyLinkedList.nodes())
          {
            assertSame(nodes[i++], node);
          }
        }
      }

      public static <Item> void doesNotChangeSize(
        LinkedList<Item> nonEmptyLinkedList)
      {
        int size = nonEmptyLinkedList.size();
        try
        {
          nonEmptyLinkedList.lastNode().removeNext();
        }
        catch (NoSuchElementException ignored)
        {
        }
        assertEquals(size, nonEmptyLinkedList.size());
      }
    }
  }
}
