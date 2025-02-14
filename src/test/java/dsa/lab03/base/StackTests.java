package dsa.lab03.base;

import dsa.lib.Iterators;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTests
{
  public static class Push
  {
    public static <Item> void pushesAsTop(
      Stack<Item> stack,
      Item item)
    {
      stack.push(item);
      assertEquals(item, stack.top());
    }

    public static <Item> void doesNotChangeOthers(
      Stack<Item> stack,
      Item item)
    {
      int size = stack.size();
      Item[] others = Iterators.toArray(stack, size);
      stack.push(item);
      assertArrayEquals(
        others,
        Iterators.toArray(Iterators.skipIndex(0, stack), size));
    }

    public static <Item> void incrementsSize(
      Stack<Item> stack,
      Item item)
    {
      int size = stack.size();
      stack.push(item);
      assertEquals(size + 1, stack.size());
    }
  }

  public static class Top
  {
    public static <Item> void returnsTop(
      Stack<Item> nonEmptyStack,
      Item top)
    {
      assertEquals(top, nonEmptyStack.top());
    }

    public static <Item> void doesNotChangeTop(
      Stack<Item> nonEmptyStack)
    {
      Item top = nonEmptyStack.top();
      assertEquals(top, nonEmptyStack.top());
    }

    public static <Item> void doesNotChangeItems(
      Stack<Item> nonEmptyStack)
    {
      int size = nonEmptyStack.size();
      Item[] items = Iterators.toArray(nonEmptyStack, size);
      nonEmptyStack.top();
      assertArrayEquals(
        items,
        Iterators.toArray(nonEmptyStack, size));
    }

    public static <Item> void doesNotChangeSize(
      Stack<Item> nonEmptyStack)
    {
      int size = nonEmptyStack.size();
      nonEmptyStack.top();
      assertEquals(size, nonEmptyStack.size());
    }
  }

  public static class Pop
  {
    public static <Item> void returnsTop(
      Stack<Item> nonEmptyStack,
      Item top)
    {
      assertEquals(top, nonEmptyStack.pop());
    }

    public static <Item> void doesNotChangeOthers(
      Stack<Item> nonEmptyStack)
    {
      int size = nonEmptyStack.size();
      Item[] others = Iterators.toArray(
        Iterators.skipIndex(0, nonEmptyStack),
        size - 1);
      nonEmptyStack.pop();
      assertArrayEquals(
        others,
        Iterators.toArray(nonEmptyStack, size - 1));
    }

    public static <Item> void decrementsSize(
      Stack<Item> nonEmptyStack)
    {
      int size = nonEmptyStack.size();
      nonEmptyStack.pop();
      assertEquals(size - 1, nonEmptyStack.size());
    }
  }
}
