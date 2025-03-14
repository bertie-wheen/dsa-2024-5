package dsa.lab08.exercises;

import dsa.lab04.base.MapItem;
import dsa.lab05.solutions.ChainingHashMap;
import dsa.lab07.solutions.BinarySearchTree;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * An AVL tree.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class AVLTree<Key extends Comparable<Key>, Value>
  extends BinarySearchTree<Key, Value>
{
  private ChainingHashMap<Node<Key, Value>, Integer> heights =
    new ChainingHashMap<>();

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty AVL tree.
   */
  public AVLTree()
  {
  }

  /**
   * Construct an AVL tree containing the given items.
   *
   * @param items the items
   */
  public AVLTree(Iterable<MapItem<Key, Value>> items)
  {
    for (MapItem<Key, Value> item : items)
    {
      this.insert(item);
    }
  }

  /**
   * Construct an AVL tree containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public AVLTree(MapItem<Key, Value>... items)
  {
    this(Arrays.asList(items));
  }

  //</editor-fold>

  @Override
  public void insert(MapItem<Key, Value> item)
  {
    if (this.isEmpty())
    {
      this.root = new Node<>(null, this, null, item, null);
      this.size = 1;
      this.heights.insert(this.root, 0);
    }
    else
    {
      Node<Key, Value> inserted = this.root.insert(item);
      this.updateAncestors(inserted, true);
    }
  }

  @Override
  public MapItem<Key, Value> remove(Key key)
    throws NoSuchElementException
  {
    Node<Key, Value> node = this.findNode(key);
    MapItem<Key, Value> item = node.item;
    Node<Key, Value> removed = node.remove();
    this.heights.remove(removed);
    this.updateAncestors(removed.parent, false);
    return item;
  }

  /**
   * Rotate the given node clockwise.
   *
   * @param node the node to rotate
   */
  private void rotateC(Node<Key, Value> node)
  {
    Node<Key, Value> left = node.left;
    if (left == null)
    {
      throw new IllegalStateException();
    }

    // TODO: Implement AVLTree.rotateC(Node node)
    // NOTE: You will need to do a LOT of link juggling!
    // NOTE: Expect to write approx 10+ lines.

    this.recalculateHeight(node);
    this.recalculateHeight(left);
  }

  /**
   * Rotate the given node anticlockwise.
   *
   * @param node the node to rotate
   */
  private void rotateA(Node<Key, Value> node)
  {
    Node<Key, Value> right = node.right;
    if (right == null)
    {
      throw new IllegalStateException();
    }

    // TODO: Implement AVLTree.rotateA(Node node)
    // NOTE: This is very similar to rotateC.
    //       (Just swap left for right, and right for left.)

    this.recalculateHeight(node);
    this.recalculateHeight(right);
  }

  /**
   * Rebalance the given node (if necessary) after
   * an insertion or removal within its descendants.
   * <p>
   * If the node is still balanced, do nothing.
   * <p>
   * At most two rotations should be performed.
   *
   * @param node the node to rebalance
   * @return whether the node needed rebalancing
   */
  private boolean rebalance(Node<Key, Value> node)
  {
    int balanceFactor = this.balanceFactor(node);
    if (balanceFactor == -2)
    {
      // TODO: Implement AVLTree.rebalance(Node node)
      // NOTE: There are two cases to consider here!

      return true;
    }
    if (balanceFactor == 2)
    {
      // TODO: Implement AVLTree.rebalance(Node node)
      // NOTE: There are also two more cases to consider here!
      // NOTE: Remember, there are four total cases.

      return true;
    }
    return false;
  }

  private void updateAncestors(
    Node<Key, Value> node,
    boolean canStopAfterRebalance)
  {
    if (node != null)
    {
      this.recalculateHeight(node);
      boolean rebalanced = this.rebalance(node);
      if (!canStopAfterRebalance || !rebalanced)
      {
        this.updateAncestors(node.parent, canStopAfterRebalance);
      }
    }
  }

  private int calculateHeight(Node<Key, Value> node)
  {
    int leftHeight = this.cachedHeight(node.left);
    int rightHeight = this.cachedHeight(node.right);
    return 1 + Math.max(leftHeight, rightHeight);
  }

  private void recalculateHeight(Node<Key, Value> node)
  {
    this.heights.insert(node, this.calculateHeight(node));
  }

  private int balanceFactor(Node<Key, Value> node)
  {
    int leftHeight = this.cachedHeight(node.left);
    int rightHeight = this.cachedHeight(node.right);
    return rightHeight - leftHeight;
  }

  private int cachedHeight(Node<Key, Value> node)
  {
    return node == null ? -1 : this.heights.get(node);
  }

  //<editor-fold defaultstate="collapsed" desc="Methods for testing">

  public boolean _isHeightCacheCorrect()
  {
    for (MapItem<Node<Key, Value>, Integer> cacheItem : heights)
    {
      Node<Key, Value> node = cacheItem.key();
      int height = cacheItem.value();
      if (this.calculateHeight(node) != height)
      {
        return false;
      }
    }
    return true;
  }

  public boolean _isAVLConditionSatisfied()
  {
    for (Node<Key, Value> node : heights.keys())
    {
      int balanceFactor = this.balanceFactor(node);
      if (Math.abs(balanceFactor) >= 2)
      {
        return false;
      }
    }
    return true;
  }

  //</editor-fold>
}
