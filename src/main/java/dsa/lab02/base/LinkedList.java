package dsa.lab02.base;

import dsa.lib.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A linked list.
 * <p>
 * A dynamic sequence implemented using linked nodes,
 * with O({@code n}) iteration in at least one direction
 * (first-to-last / last-to-first) (where {@code n} is the size)
 * and O(1) insertion/removal at at least one end (first/last)
 *
 * @param <Item> the item type
 */
public interface LinkedList<Item>
  extends DynamicSequence<Item>
{

  /**
   * Get the node at the given index.
   *
   * @param index the index
   * @return the node that is at that index
   * @throws IndexOutOfBoundsException if {@code index} {@literal <} 0 or
   *                                   {@code index} {@literal >=} {@code n}
   *                                   (where {@code n} is the size)
   */
  LinkedNode<Item> node(int index)
    throws IndexOutOfBoundsException;


  /**
   * Get the first node, or {@code null} if empty.
   *
   * @return the node at index 0
   */
  default LinkedNode<Item> firstNode()
  {
    // NOTE: This is called the ternary operator, and is basically an
    //       if _expression_ (as opposed to an if/else _statement_).
    // NOTE: Equivalent to:
    //         if (this.isEmpty()) return null; else return this.node(0);
    return this.isEmpty() ? null : this.node(0);
  }


  /**
   * Get the last node, or {@code null} if empty.
   *
   * @return the node at index {@code n}-1 (where {@code n} is the size)
   */
  default LinkedNode<Item> lastNode()
  {
    return this.isEmpty() ? null : this.node(this.size() - 1);
  }


  @Override
  default Item get(int index)
    throws IndexOutOfBoundsException
  {
    // NOTE: Similar comments as with some of the {Static,Dynamic}Sequence
    //       default implementations, in that this is unlikely to be overridden,
    //       because any way of improving get(index) should instead improve
    //       node(index), so we needn't override and they both benefit.
    return this.node(index).item();
  }


  @Override
  default void set(int index, Item item)
    throws IndexOutOfBoundsException
  {
    // NOTE: Similar comments to get().
    this.node(index).setItem(item);
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  /**
   * Get a forward iterable that yields each node once.
   * <p>
   * The nodes are iterated over in first-to-last order (by index).
   *
   * @return an iterable over the nodes
   */
  default Iterable<LinkedNode<Item>> nodes()
  {
    return () -> new ForwardNodeIterator<>(this);
  }


  /**
   * Get a reverse iterable that yields each node once.
   * <p>
   * The nodes are iterated over in last-to-first order (by index).
   *
   * @return an iterable over the nodes
   */
  default Iterable<LinkedNode<Item>> reversedNodes()
  {
    return () -> new ReverseNodeIterator<>(this);
  }


  @Override
  default Iterable<Item> items()
  {
    return Iterators.applyEach(
      () -> new ForwardNodeIterator<>(this),
      LinkedNode::item);
  }


  @Override
  default Iterable<Item> reversed()
  {
    return Iterators.applyEach(
      () -> new ReverseNodeIterator<>(this),
      LinkedNode::item);
  }


  /**
   * An iterator over the nodes in a linked list.
   *
   * @param <Item> the item type
   */
  abstract class NodeIterator<Item>
    implements Iterator<LinkedNode<Item>>
  {

    private LinkedNode<Item> next;


    /**
     * Construct an iterator over the nodes in a linked list.
     *
     * @param firstNode the first node in the iteration
     *                  (not necessarily in the linked list)
     */
    public NodeIterator(LinkedNode<Item> firstNode)
    {
      this.next = firstNode;
    }


    protected abstract LinkedNode<Item> nextNode(LinkedNode<Item> node);


    @Override
    public boolean hasNext()
    {
      return this.next != null;
    }


    @Override
    public LinkedNode<Item> next()
      throws NoSuchElementException
    {
      LinkedNode<Item> node = this.next;
      this.next = this.nextNode(this.next);
      return node;
    }

  }


  /**
   * A forward iterator over the nodes in a linked list.
   *
   * @param <Item> the item type
   */
  class ForwardNodeIterator<Item>
    extends NodeIterator<Item>
  {

    /**
     * Construct a forward iterator over the nodes in the given linked list.
     *
     * @param list the linked list
     */
    public ForwardNodeIterator(LinkedList<Item> list)
    {
      super(list.firstNode());
    }


    @Override
    protected LinkedNode<Item> nextNode(LinkedNode<Item> node)
    {
      return node.next();
    }

  }

  /**
   * A reverse iterator over the nodes in a linked list.
   *
   * @param <Item> the item type
   */
  class ReverseNodeIterator<Item>
    extends NodeIterator<Item>
  {

    /**
     * Construct a reverse iterator over the nodes in the given linked list.
     *
     * @param list the linked list
     */
    public ReverseNodeIterator(LinkedList<Item> list)
    {
      super(list.lastNode());
    }


    @Override
    protected LinkedNode<Item> nextNode(LinkedNode<Item> node)
    {
      return node.previous();
    }

  }


  //</editor-fold>

}
