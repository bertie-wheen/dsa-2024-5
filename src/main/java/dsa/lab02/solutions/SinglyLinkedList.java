package dsa.lab02.solutions;

import dsa.lab02.base.LinkedList;
import dsa.lab02.base.LinkedNode;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A singly-linked list.
 * <p>
 * Nodes are only directly linked to their successors.
 * Holds references to both the first and last nodes (if non-empty).
 *
 * @param <Item> the item type
 */
public class SinglyLinkedList<Item>
  implements LinkedList<Item>
{
  private int size = 0;
  private Node<Item> first = null;
  private Node<Item> last = null;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty singly-linked list.
   */
  public SinglyLinkedList()
  {
  }

  /**
   * Construct a singly-linked list containing the given items.
   *
   * @param items the items
   */
  public SinglyLinkedList(Iterable<Item> items)
  {
    for (Item item : items)
    {
      this.insertLast(item);
    }
  }

  /**
   * Construct a singly-linked list containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public SinglyLinkedList(Item... items)
  {
    this(Arrays.asList(items));
  }

  //</editor-fold>

  @Override
  public int size()
  {
    return this.size;
  }

  @Override
  public Node<Item> node(int index)
    throws IndexOutOfBoundsException
  {
    if (index < 0 || index >= this.size)
    {
      throw new IndexOutOfBoundsException();
    }
    if (index == this.size - 1)
    {
      return this.last;
    }
    Node<Item> node = this.first;
    for (int i = 0; i < index; i++)
    {
      node = node.next();
    }
    return node;
  }

  @Override
  public void insert(int index, Item item)
    throws IndexOutOfBoundsException
  {
    if (this.isEmpty())
    {
      this.first = this.last = new Node<>(this, item);
      this.size = 1;
    }
    else if (index == 0)
    {
      this.first = new Node<>(this, item, this.first);
      this.size++;
    }
    else
    {
      this.node(index - 1).insertNext(item);
    }
  }

  @Override
  public Item remove(int index)
    throws IndexOutOfBoundsException
  {
    if (this.isEmpty())
    {
      throw new IndexOutOfBoundsException();
    }
    return index == 0
      ? this.first.remove()
      : this.node(index - 1).removeNext();
  }

  /**
   * A node in a singly-linked list.
   * <p>
   * Only holds a direct link to the next node.
   *
   * @param <Item> the item type
   */
  public static class Node<Item>
    implements LinkedNode<Item>
  {
    private SinglyLinkedList<Item> list;
    private Item item;
    private Node<Item> next;

    /**
     * Construct a node with the given item.
     *
     * @param list the containing linked list
     * @param item the contained item
     */
    public Node(SinglyLinkedList<Item> list, Item item)
    {
      this(list, item, null);
    }

    /**
     * Construct a node with the given item and successor.
     *
     * @param list the containing linked list
     * @param item the contained item
     * @param next the next node
     */
    public Node(SinglyLinkedList<Item> list, Item item, Node<Item> next)
    {
      this.list = list;
      this.item = item;
      this.next = next;
    }

    @Override
    public SinglyLinkedList<Item> list()
    {
      return this.list;
    }

    @Override
    public Item item()
    {
      return this.item;
    }

    @Override
    public void setItem(Item item)
    {
      this.item = item;
    }

    @Override
    public Node<Item> previous()
    {
      if (this.isFirst())
      {
        return null;
      }
      Node<Item> node = this.list.first;
      while (node.next != this)
      {
        node = node.next;
      }
      return node;
    }

    @Override
    public Node<Item> next()
    {
      return this.next;
    }

    @Override
    public void insertPrevious(Item item)
    {
      Node<Item> node = new Node<>(this.list, item, this);
      if (this.isFirst())
      {
        this.list.first = node;
      }
      else
      {
        this.previous().next = node;
      }
      this.list.size++;
    }

    @Override
    public void insertNext(Item item)
    {
      this.next = new Node<>(this.list, item, this.next);
      if (this.isLast())
      {
        this.list.last = this.next;
      }
      this.list.size++;
    }

    @Override
    public Item remove()
    {
      if (this.isFirst())
      {
        this.list.first = this.next;
        this.list.size--;
        return this.item;
      }
      return this.previous().removeNext();
    }

    @Override
    public Item removePrevious()
      throws NoSuchElementException
    {
      if (this.isFirst())
      {
        throw new NoSuchElementException();
      }
      Node<Item> node = this.list.first;
      if (node.next == this)
      {
        this.list.first = this;
        this.list.size--;
        return node.item;
      }
      while (node.next.next != this)
      {
        node = node.next;
      }
      return node.removeNext();
    }

    @Override
    public Item removeNext()
      throws NoSuchElementException
    {
      if (this.isLast())
      {
        throw new NoSuchElementException();
      }
      if (this.next.isLast())
      {
        this.list.last = this;
      }
      Item item = this.next.item;
      this.next = this.next.next;
      this.list.size--;
      return item;
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Iteration">

  // NOTE: SinglyLinkedList.reversed() will use the LinkedList.reversed()
  //       implementation which starts from the last node and repeatedly calls
  //       previous(), which for singly-linked lists is O(n), making it O(n^2)
  //       overall. We don't implement it here, but FYI it's possible to
  //       implement O(n) reverse-iteration over singly-linked lists if you
  //       first create a reversed copy of the list by doing essentially:
  //         SinglyLinkedList<Item> copy = new SinglyLinkedList();
  //         for (Item item : list)
  //         {
  //           copy.insertFirst(item);
  //         }
  //       which is O(n), and then simply iterating over that reversed copy,
  //       which is also O(n). This has two disadvantages, the first being that
  //       it requires O(n) additional storage for the copy, and the second
  //       being that you're iterating over a copy rather than the list itself,
  //       though that doesn't matter if you're not making any changes to the
  //       list or copy during the iteration. It also only really works for
  //       reversed(), not reversedNodes(), as the nodes yielded would be
  //       different nodes in the temporary copy rather than in the actual list.

  //</editor-fold>
}
