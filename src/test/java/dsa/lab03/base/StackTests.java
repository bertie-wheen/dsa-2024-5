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
      Item[] others = Iterators.toArray(stack);
      stack.push(item);
      assertArrayEquals(
        others,
        Iterators.toArray(Iterators.skipIndex(0, stack)));
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
      Item[] items = Iterators.toArray(nonEmptyStack);
      nonEmptyStack.top();
      assertArrayEquals(items, Iterators.toArray(nonEmptyStack));
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
      Item[] others = Iterators.toArray(Iterators.skipIndex(0, nonEmptyStack));
      nonEmptyStack.pop();
      assertArrayEquals(others, Iterators.toArray(nonEmptyStack));
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
