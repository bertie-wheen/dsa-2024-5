package dsa.lab07.solutions;

import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.base.MapItem;
import dsa.lab04.solutions.MergeSorter;
import dsa.lab05.base.OrderedMap;
import dsa.lib.To;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A binary search tree.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value>
  implements OrderedMap<Key, Value>
{
  protected Node<Key, Value> root = null;
  protected int size = 0;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty binary search tree.
   */
  public BinarySearchTree()
  {
  }

  /**
   * Construct a binary search tree containing the given items.
   *
   * @param items the items
   */
  public BinarySearchTree(Iterable<MapItem<Key, Value>> items)
  {
    this(new DynamicArray<>(items));
  }

  /**
   * Construct a binary search tree containing the given items
   * more efficiently than {@link #BinarySearchTree(Iterable)}.
   *
   * @param items the items
   */
  public BinarySearchTree(
    Iterable<MapItem<Key, Value>> items,
    int size)
  {
    this(new DynamicArray<>(items, size));
  }

  /**
   * Construct a binary search tree containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public BinarySearchTree(MapItem<Key, Value>... items)
  {
    this(Arrays.asList(items), items.length);
  }

  /**
   * Construct a binary search tree containing the given items.
   *
   * @param items the items
   */
  public BinarySearchTree(DynamicArray<MapItem<Key, Value>> items)
  {
    new MergeSorter().sort(items, Comparator.comparing(MapItem::key));
    this.root = this.buildNode(items, 0, items.size());
    this.size = items.size();
  }

  private Node<Key, Value> buildNode(
    DynamicArray<MapItem<Key, Value>> items,
    int start,
    int stop)
  {
    if (start >= stop)
    {
      return null;
    }
    int index = (start + stop) / 2;
    Node<Key, Value> left = this.buildNode(items, start, index);
    Node<Key, Value> right = this.buildNode(items, index + 1, stop);
    Node<Key, Value> node =
      new Node<>(null, this, left, items.get(index), right);
    if (left != null)
    {
      left.parent = node;
    }
    if (right != null)
    {
      right.parent = node;
    }
    return node;
  }

  //</editor-fold>

  @Override
  public int size()
  {
    return this.size;
  }

  /**
   * Find the node containing the item with the given key.
   *
   * @param key the key
   * @return the node
   * @throws NoSuchElementException if no node has key {@code key}
   */
  protected Node<Key, Value> findNode(Key key)
    throws NoSuchElementException
  {
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    return this.root.findNode(key);
  }

  @Override
  public MapItem<Key, Value> find(Key key)
    throws NoSuchElementException
  {
    return this.findNode(key).item;
  }

  @Override
  public void insert(MapItem<Key, Value> item)
  {
    if (this.isEmpty())
    {
      this.root = new Node<>(null, this, null, item, null);
      this.size = 1;
    }
    else
    {
      this.root.insert(item);
    }
  }

  @Override
  public MapItem<Key, Value> remove(Key key)
    throws NoSuchElementException
  {
    Node<Key, Value> node = this.findNode(key);
    MapItem<Key, Value> item = node.item;
    node.remove();
    return item;
  }

  @Override
  public MapItem<Key, Value> previous(Key key)
  {
    return this.root != null ? this.root.previous(key) : null;
  }

  @Override
  public MapItem<Key, Value> next(Key key)
  {
    return this.root != null ? this.root.next(key) : null;
  }

  @Override
  public MapItem<Key, Value> min()
  {
    return this.isEmpty() ? null : this.root.min();
  }

  @Override
  public MapItem<Key, Value> max()
  {
    return this.isEmpty() ? null : this.root.max();
  }

  /**
   * A node in a binary search tree.
   *
   * @param <Key>   the key type
   * @param <Value> the value type
   */
  protected static class Node<Key extends Comparable<Key>, Value>
  {
    public Node<Key, Value> parent;
    public BinarySearchTree<Key, Value> tree;
    public Node<Key, Value> left;
    public MapItem<Key, Value> item;
    public Node<Key, Value> right;

    public Node(
      Node<Key, Value> parent,
      BinarySearchTree<Key, Value> tree,
      Node<Key, Value> left,
      MapItem<Key, Value> item,
      Node<Key, Value> right)
    {
      this.parent = parent;
      this.tree = tree;
      this.left = left;
      this.item = item;
      this.right = right;
    }

    /**
     * Find the node containing the item with the given key within this subtree.
     *
     * @param key the key
     * @return the node
     * @throws NoSuchElementException if no node has key {@code key}
     */
    public Node<Key, Value> findNode(Key key)
      throws NoSuchElementException
    {
      int comparison = key.compareTo(this.item.key());
      if (comparison < 0)
      {
        if (this.left == null)
        {
          throw new NoSuchElementException();
        }
        return this.left.findNode(key);
      }
      if (comparison > 0)
      {
        if (this.right == null)
        {
          throw new NoSuchElementException();
        }
        return this.right.findNode(key);
      }
      return this;
    }

    /**
     * Insert the given item within this subtree.
     * <p>
     * If there's already an item with the same key,
     * that item is replaced with this one,
     * and that node is returned.
     * <p>
     * If instead there isn't already an item with the same key,
     * a new node is inserted and returned.
     *
     * @param item the item
     * @return the node containing the item, once inserted
     */
    public Node<Key, Value> insert(MapItem<Key, Value> item)
    {
      int comparison = item.key().compareTo(this.item.key());
      if (comparison < 0)
      {
        if (this.left != null)
        {
          return this.left.insert(item);
        }
        this.tree.size++;
        return this.left = new Node<>(this, this.tree, null, item, null);
      }
      if (comparison > 0)
      {
        if (this.right != null)
        {
          return this.right.insert(item);
        }
        this.tree.size++;
        return this.right = new Node<>(this, this.tree, null, item, null);
      }
      this.item = item;
      return this;
    }

    /**
     * Remove the item that this node contains from the tree,
     * and return the node removed.
     * <p>
     * The removed node is only this node if this is a leaf.
     * <p>
     * If this node isn't a leaf, find a suitable leaf node
     * (if this has a left subtree, then its maximum node,
     * otherwise the right subtree's minimum),
     * copy its item into this node, and remove and return that node.
     *
     * @return the removed node
     */
    public Node<Key, Value> remove()
    {
      if (this.left != null || this.right != null)
      {
        Node<Key, Value> node =
          this.left != null ? this.left.maxNode() : this.right.minNode();
        this.item = node.item;
        return node.remove();
      }
      if (this.parent == null)
      {
        this.tree.root = null;
      }
      else if (this.parent.left == this)
      {
        this.parent.left = null;
      }
      else
      {
        this.parent.right = null;
      }
      this.tree.size--;
      return this;
    }

    /**
     * Get the item within this subtree that would be
     * the predecessor of one with the given key.
     * <p>
     * There may or may not be an item with the given key.
     *
     * @param key a key
     * @return the previous item (by key), or {@code null} if there is none
     */
    public MapItem<Key, Value> previous(Key key)
    {
      if (this.item.key().compareTo(key) >= 0)
      {
        return this.left != null ? this.left.previous(key) : null;
      }
      if (this.right != null)
      {
        MapItem<Key, Value> item = this.right.previous(key);
        if (item != null)
        {
          return item;
        }
      }
      return this.item;
    }

    /**
     * Get the item within this subtree that would be
     * the successor of one with the given key.
     * <p>
     * There may or may not be an item with the given key.
     *
     * @param key a key
     * @return the next item (by key), or {@code null} if there is none
     */
    public MapItem<Key, Value> next(Key key)
    {
      if (this.item.key().compareTo(key) <= 0)
      {
        return this.right != null ? this.right.next(key) : null;
      }
      if (this.left != null)
      {
        MapItem<Key, Value> item = this.left.next(key);
        if (item != null)
        {
          return item;
        }
      }
      return this.item;
    }

    /**
     * Get the node in this subtree with the least key.
     *
     * @return the minimum node (the leftmost descendant)
     */
    public Node<Key, Value> minNode()
    {
      Node<Key, Value> node = this;
      while (node.left != null)
      {
        node = node.left;
      }
      return node;
    }

    /**
     * Get the node in this subtree with the greatest key.
     *
     * @return the maximum node (the rightmost descendant)
     */
    public Node<Key, Value> maxNode()
    {
      Node<Key, Value> node = this;
      while (node.right != null)
      {
        node = node.right;
      }
      return node;
    }

    /**
     * Get the item in this subtree with the least key.
     *
     * @return the minimum item (by key)
     */
    public MapItem<Key, Value> min()
    {
      return this.minNode().item;
    }

    /**
     * Get the item in this subtree with the greatest key.
     *
     * @return the maximum item (by key)
     */
    public MapItem<Key, Value> max()
    {
      return this.maxNode().item;
    }
  }

  @Override
  public String toString()
  {
    return this.toString(this.root);
  }

  private String toString(Node<Key, Value> node)
  {
    if (node == null)
    {
      return "";
    }
    String l = this.toString(node.left);
    String r = this.toString(node.right);
    return "[" + l + " (" + To.string(node.item) + ") " + r + "]";
  }

  //<editor-fold defaultstate="collapsed" desc="Iteration">

  @Override
  public Iterable<MapItem<Key, Value>> items()
  {
    return () -> new ItemIterator<>(this, true);
  }

  @Override
  public Iterable<MapItem<Key, Value>> reversed()
  {
    return () -> new ItemIterator<>(this, false);
  }

  public static class ItemIterator<Key extends Comparable<Key>, Value>
    implements Iterator<MapItem<Key, Value>>
  {
    private boolean forward;
    private Node<Key, Value> node;
    private ItemIterator<Key, Value> left;
    private ItemIterator<Key, Value> right;

    public ItemIterator(BinarySearchTree<Key, Value> tree, boolean forward)
    {
      this(tree.root, forward);
    }

    private ItemIterator(Node<Key, Value> node, boolean forward)
    {
      this.forward = forward;
      this.node = node;
      if (node != null)
      {
        if (node.left != null)
        {
          this.left = new ItemIterator<>(node.left, forward);
        }
        if (node.right != null)
        {
          this.right = new ItemIterator<>(node.right, forward);
        }
      }
    }

    @Override
    public boolean hasNext()
    {
      return this.node != null ||
        (this.left != null && this.left.hasNext()) ||
        (this.right != null && this.right.hasNext());
    }

    @Override
    public MapItem<Key, Value> next()
      throws NoSuchElementException
    {
      ItemIterator<Key, Value> left = this.forward ? this.left : this.right;
      ItemIterator<Key, Value> right = this.forward ? this.right : this.left;
      if (left != null && left.hasNext())
      {
        return left.next();
      }
      if (this.node != null)
      {
        MapItem<Key, Value> item = this.node.item;
        this.node = null;
        return item;
      }
      if (right != null && right.hasNext())
      {
        return right.next();
      }
      throw new NoSuchElementException();
    }
  }

  //</editor-fold>
}
