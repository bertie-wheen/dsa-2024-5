package dsa.lab02.solutions;

import dsa.lab02.base.LinkedList;
import dsa.lab02.base.LinkedNode;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A doubly-linked list.
 * <p>
 * Nodes are directly-linked to both their predecessors and successors.
 * Holds references to both the first and last nodes (if non-empty).
 *
 * @param <Item> the item type
 */
public class DoublyLinkedList<Item>
  implements LinkedList<Item>
{
  private int size = 0;
  private Node<Item> first = null;
  private Node<Item> last = null;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty doubly-linked list.
   */
  public DoublyLinkedList()
  {
  }

  /**
   * Construct a doubly-linked list containing the given items.
   *
   * @param items the items
   */
  public DoublyLinkedList(Iterable<Item> items)
  {
    for (Item item : items)
    {
      this.insertLast(item);
    }
  }

  /**
   * Construct a doubly-linked list containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public DoublyLinkedList(Item... items)
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
    Node<Item> node;
    if (index < this.size / 2)
    {
      node = this.first;
      for (int i = 0; i < index; i++)
      {
        node = node.next();
      }
    }
    else
    {
      node = this.last;
      int reverseIndex = this.size - index - 1;
      for (int i = 0; i < reverseIndex; i++)
      {
        node = node.previous();
      }
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
    else if (index == this.size)
    {
      this.last = new Node<>(this, this.last, item);
      this.last.previous.next = this.last;
      this.size++;
    }
    else
    {
      this.node(index).insertPrevious(item);
    }
  }

  @Override
  public Item remove(int index)
    throws IndexOutOfBoundsException
  {
    return this.node(index).remove();
  }

  /**
   * A node in a doubly-linked list.
   * <p>
   * Holds direct links to both the previous and next nodes.
   *
   * @param <Item> the item type
   */
  public static class Node<Item>
    implements LinkedNode<Item>
  {
    private DoublyLinkedList<Item> list;
    private Node<Item> previous;
    private Item item;
    private Node<Item> next;

    /**
     * Construct a node with the given item.
     *
     * @param list the containing linked list
     * @param item the contained item
     */
    public Node(DoublyLinkedList<Item> list, Item item)
    {
      this(list, null, item, null);
    }

    /**
     * Construct a node with the given predecessor and item.
     *
     * @param list     the containing linked list
     * @param previous the previous node
     * @param item     the contained item
     */
    public Node(DoublyLinkedList<Item> list, Node<Item> previous, Item item)
    {
      this(list, previous, item, null);
    }

    /**
     * Construct a node with the given item and successor.
     *
     * @param list the containing linked list
     * @param item the contained item
     * @param next the next node
     */
    public Node(DoublyLinkedList<Item> list, Item item, Node<Item> next)
    {
      this(list, null, item, next);
    }

    /**
     * Construct a node with the given predecessor, item and successor.
     *
     * @param list     the containing linked list
     * @param previous the previous node
     * @param item     the contained item
     * @param next     the next node
     */
    public Node(
      DoublyLinkedList<Item> list,
      Node<Item> previous,
      Item item,
      Node<Item> next)
    {
      this.list = list;
      this.previous = previous;
      this.item = item;
      this.next = next;
    }

    @Override
    public DoublyLinkedList<Item> list()
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
      return this.previous;
    }

    @Override
    public Node<Item> next()
    {
      return this.next;
    }

    @Override
    public void insertPrevious(Item item)
    {
      this.previous = new Node<>(this.list, this.previous, item, this);
      if (this.isFirst())
      {
        this.list.first = this.previous;
      }
      else
      {
        this.previous.previous.next = this.previous;
      }
      this.list.size++;
    }

    @Override
    public void insertNext(Item item)
    {
      this.next = new Node<>(this.list, this, item, this.next);
      if (this.isLast())
      {
        this.list.last = this.next;
      }
      else
      {
        this.next.next.previous = this.next;
      }
      this.list.size++;
    }

    @Override
    public Item remove()
    {
      if (this.isFirst())
      {
        this.list.first = this.next;
      }
      else
      {
        this.previous.next = this.next;
      }
      if (this.isLast())
      {
        this.list.last = this.previous;
      }
      else
      {
        this.next.previous = this.previous;
      }
      this.list.size--;
      return this.item;
    }

    @Override
    public Item removePrevious()
      throws NoSuchElementException
    {
      if (this.isFirst())
      {
        throw new NoSuchElementException();
      }
      return this.previous.remove();
    }

    @Override
    public Item removeNext()
      throws NoSuchElementException
    {
      if (this.isLast())
      {
        throw new NoSuchElementException();
      }
      return this.next.remove();
    }
  }
}
