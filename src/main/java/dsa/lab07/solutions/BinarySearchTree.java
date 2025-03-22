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

  /** The root of the BST. Null if empty. */
  protected Node<Key, Value> root = null;
  // NOTE: This (and various things in this class) are protected rather than
  //       private because in the next exercise we'll be making a subclass that
  //       will want access to them, which it wouldn't have it they were
  //       private. There are downsides to doing it this way, particularly that
  //       protected is more visibility than we want, in that it means not only
  //       that subclasses can access them, but that so too can other classes in
  //       the same package. In this case however, there aren't any other
  //       classes in the same exercises package that we need to worry about.
  //       This is, incidentally, one of the reasons why you should very rarely
  //       have classes in the default package (which is when you don't put them
  //       in any package at all / they're directly within the source folder).


  /** The number of nodes in the BST. */
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

    // NOTE: For this, and most methods in BinarySearchTree, we implement them
    //       in terms of recursive methods on BinarySearchTree.Node.
    return this.root.findNode(key);

    // NOTE: We implement this findNode method as it's useful both in find() and
    //       in remove(), so this reduces duplication and keeps the code DRY!
    //       (Don't Repeat Yourself is a good thing to have in mind when
    //       refactoring - when initially written, code may have many very
    //       similar sections, which can often be extracted into a method.)
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

    /** The parent node - possibly null (if root). */
    public Node<Key, Value> parent;


    /** The containing BST. */
    public BinarySearchTree<Key, Value> tree;


    /** The left child - possible null. */
    public Node<Key, Value> left;


    /** The contained item. */
    public MapItem<Key, Value> item;


    /** The left child - possible null. */
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
      // NOTE: Compare this to BinarySearcher.search, or SortedArrayMap methods.

      int comparison = key.compareTo(this.item.key());

      // NOTE: If the key we're looking for is less than this node's, then - if
      //       the key is anywhere in the tree - it must be in the left subtree.
      if (comparison < 0)
      {
        // NOTE: If there isn't a left subtree, then the key can't be in it, and
        //       since that's the only place it could be, it's not in the BST.
        if (this.left == null)
        {
          throw new NoSuchElementException();
        }

        // NOTE: If there is, then recursively search it.
        return this.left.findNode(key);
      }

      // NOTE: If the key we're looking for is more than this node's, then we
      //       should look in the right subtree.
      if (comparison > 0)
      {
        if (this.right == null)
        {
          throw new NoSuchElementException();
        }

        return this.right.findNode(key);
      }

      // NOTE: If the key we're looking for is neither less nor more than this
      //       node's item's key, then it must be equal, i.e. this node is the
      //       node that contains an item with that key, so return it.
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
      // NOTE: Has the same binary-search structure as findNode(), but with
      //       different actions in each of the base cases.

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
      // NOTE: remove() works very differently to findNode() and insert().

      // NOTE: If we're a leaf node, it's "simple".
      // NOTE: All we have to do is just directly remove this node.
      if (this.left == null && this.right == null)
      {
        // NOTE: If we were the root, the tree will become empty.
        if (this.parent == null)
        {
          this.tree.root = null;
        }

        // NOTE: If we were our parent's left child, it will then not have one.
        else if (this.parent.left == this)
        {
          this.parent.left = null;
        }

        // NOTE: Similarly if we were our parent's right child.
        else
        {
          this.parent.right = null;
        }

        // NOTE: We're tracking the size, so decrement that.
        this.tree.size--;

        // NOTE: We removed _this_ node, so return it.
        return this;
      }

      // NOTE: If we're _not_ a leaf node, i.e. we have at least one child,
      //       then things are more complex.

      // NOTE: Find either our in-order predecessor or successor from amongst
      //       our descendants.
      // NOTE: (It doesn't especially matter which. If we have both amongst our
      //       descendants, then pick either - here we choose the predecessor.)
      // NOTE: The point of them being our predecessor/successor is so that we
      //       don't mess up the BST condition of in-order sortedness.
      // NOTE: The point of it being amongst our descendants is so that we're
      //       moving "down" in the tree, towards (eventually) a leaf, as
      //       otherwise we might recurse indefinitely.
      Node<Key, Value> node =
        this.left != null ? this.left.maxNode() : this.right.minNode();

      // NOTE: Copy its item across to this node.
      // NOTE: We want to remove the item this node contains from the tree, so
      //       this - by overwriting it - does that.
      this.item = node.item;

      // NOTE: At this point, we will have duplicated that item, i.e. it will
      //       appear twice in the tree, but the next line takes care of that.

      // NOTE: Remove that item from the descendant predecessor/successor's
      //       subtree, and return the node removed (which, if it was a leaf,
      //       will be node, but it may have children itself).
      return node.remove();
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
      // NOTE: If the key we're looking for what the predecessor of would be is
      //       less than or equal to this node's (item's) key, then it must be
      //       in the left subtree - if there isn't one, then this is the
      //       minimum node within the subtree, but is more than such a
      //       predecessor, and therefore there cannot be one in the subtree.
      if (key.compareTo(this.item.key()) <= 0)
      {
        return this.left != null ? this.left.previous(key) : null;
      }

      // NOTE: Otherwise, if the key is more than this node's...

      // NOTE: If we've got a right subtree, then look in it, and if we find one
      //       in it, then return that.
      if (this.right != null)
      {
        MapItem<Key, Value> item = this.right.previous(key);
        if (item != null)
        {
          return item;
        }
      }

      // NOTE: Otherwise, if we don't have a right subtree, or it doesn't
      //       contain such a predecessor, then return this.
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
      // NOTE: Works in the same way as previous() (just swaps left for right).

      if (key.compareTo(this.item.key()) >= 0)
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
      // NOTE: Start at this node.
      Node<Key, Value> node = this;

      // NOTE: Descend as far leftwards as we can.
      while (node.left != null)
      {
        node = node.left;
      }

      // NOTE: Return the leftmost descendant, which we've reached.
      return node;

      // NOTE: Could also be implemented recursively as:
      //         return this.left == null ? this : this.left.minNode();
    }


    /**
     * Get the node in this subtree with the greatest key.
     *
     * @return the maximum node (the rightmost descendant)
     */
    public Node<Key, Value> maxNode()
    {
      // NOTE: Similar comments as minNode().

      Node<Key, Value> node = this;

      while (node.right != null)
      {
        node = node.right;
      }

      return node;

      // NOTE: A recursive implementation could be:
      //         return this.right == null ? this : this.right.maxNode();
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


  //<editor-fold defaultstate="collapsed" desc="toString()">


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


  //</editor-fold>


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
