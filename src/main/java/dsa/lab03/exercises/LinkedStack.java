package dsa.lab03.exercises;

import dsa.lab02.solutions.SinglyLinkedList;
import dsa.lab03.base.Stack;
import dsa.lib.TODO;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A linked stack.
 * <p>
 * Implements the stack interface by using a singly-linked list.
 *
 * @param <Item> the item type
 */
public class LinkedStack<Item>
  implements Stack<Item>
{
  private SinglyLinkedList<Item> items;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty linked stack.
   */
  public LinkedStack()
  {
    this.items = new SinglyLinkedList<>();
  }

  /**
   * Construct a linked stack containing the given items.
   *
   * @param items the items
   */
  public LinkedStack(Iterable<Item> items)
  {
    this.items = new SinglyLinkedList<>(items);
  }

  /**
   * Construct a linked stack containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public LinkedStack(Item... items)
  {
    this(Arrays.asList(items));
  }

  //</editor-fold>

  @Override
  public void push(Item item)
  {
    // TODO: Implement LinkedStack.push(Item item)
    throw new TODO();
  }

  @Override
  public Item pop()
    throws NoSuchElementException
  {
    // TODO: Implement LinkedStack.pop()
    throw new TODO();
  }

  @Override
  public Item top()
    throws NoSuchElementException
  {
    // TODO: Implement LinkedStack.top()
    throw new TODO();
  }

  @Override
  public int size()
  {
    return this.items.size();
  }

  @Override
  public Iterable<Item> items()
  {
    return this.items.items();
  }
}
