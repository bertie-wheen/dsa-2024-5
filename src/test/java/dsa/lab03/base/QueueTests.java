package dsa.lab03.base;

import dsa.lib.Iterators;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTests
{
  public static class Enqueue
  {
    public static <Item> void enqueuesAsBack(
      Queue<Item> queue,
      Item item)
    {
      queue.enqueue(item);
      boolean empty = true;
      Item backItem = null;
      for (Item item_ : queue)
      {
        empty = false;
        backItem = item_;
      }
      if (empty)
      {
        fail();
      }
      else
      {
        assertEquals(item, backItem);
      }
    }

    public static <Item> void doesNotChangeFront(
      Queue<Item> nonEmptyQueue,
      Item item)
    {
      Item front = nonEmptyQueue.front();
      nonEmptyQueue.enqueue(item);
      assertEquals(front, nonEmptyQueue.front());
    }

    public static <Item> void doesNotChangeOthers(
      Queue<Item> queue,
      Item item)
    {
      int size = queue.size();
      Item[] others = Iterators.toArray(queue);
      queue.enqueue(item);
      assertArrayEquals(
        others,
        Iterators.toArray(Iterators.skipIndex(size, queue)));
    }

    public static <Item> void incrementsSize(
      Queue<Item> queue,
      Item item)
    {
      int size = queue.size();
      queue.enqueue(item);
      assertEquals(size + 1, queue.size());
    }
  }

  public static class Front
  {
    public static <Item> void returnsFront(
      Queue<Item> nonEmptyQueue,
      Item front)
    {
      assertEquals(front, nonEmptyQueue.front());
    }

    public static <Item> void doesNotChangeFront(
      Queue<Item> nonEmptyQueue)
    {
      Item front = nonEmptyQueue.front();
      assertEquals(front, nonEmptyQueue.front());
    }

    public static <Item> void doesNotChangeItems(
      Queue<Item> nonEmptyQueue)
    {
      Item[] items = Iterators.toArray(nonEmptyQueue);
      nonEmptyQueue.front();
      assertArrayEquals(items, Iterators.toArray(nonEmptyQueue));
    }

    public static <Item> void doesNotChangeSize(
      Queue<Item> nonEmptyQueue)
    {
      int size = nonEmptyQueue.size();
      nonEmptyQueue.front();
      assertEquals(size, nonEmptyQueue.size());
    }
  }

  public static class Dequeue
  {
    public static <Item> void returnsFront(
      Queue<Item> nonEmptyQueue,
      Item front)
    {
      assertEquals(front, nonEmptyQueue.dequeue());
    }

    public static <Item> void doesNotChangeOthers(
      Queue<Item> nonEmptyQueue)
    {
      Item[] others = Iterators.toArray(Iterators.skipIndex(0, nonEmptyQueue));
      nonEmptyQueue.dequeue();
      assertArrayEquals(others, Iterators.toArray(nonEmptyQueue));
    }

    public static <Item> void decrementsSize(
      Queue<Item> nonEmptyQueue)
    {
      int size = nonEmptyQueue.size();
      nonEmptyQueue.dequeue();
      assertEquals(size - 1, nonEmptyQueue.size());
    }
  }
}
