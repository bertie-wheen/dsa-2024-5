package dsa.lab06.solutions;

import dsa.lab02.base.Container;
import dsa.lab03.solutions.DynamicArray;
import dsa.lib.Iterators;
import dsa.lib.To;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A binary tree.
 * <p>
 * This implementation is designed for binary trees that are built bottom-up.
 * It will work in other cases, just less efficiently.
 *
 * @param <Item> the item type
 */
public class BinaryTree<Item>
  implements Container<Item>
{
  private Node<Item> root;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty binary tree.
   */
  public BinaryTree()
  {
    this.root = null;
  }

  /**
   * Construct a binary tree containing the given items.
   *
   * @param items the items
   */
  public BinaryTree(Iterable<Item> items)
  {
    DynamicArray<Item> itemsArray = new DynamicArray<>(items);
    this.root = BinaryTree.buildNode(this, itemsArray, 0);
  }

  /**
   * Construct a binary tree containing the given items
   * more efficiently than {@link #BinaryTree(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public BinaryTree(Iterable<Item> items, int size)
    throws IllegalArgumentException
  {
    DynamicArray<Item> itemsArray = new DynamicArray<>(items, size);
    this.root = BinaryTree.buildNode(this, itemsArray, 0);
  }

  /**
   * Construct a binary tree containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public BinaryTree(Item... items)
  {
    this(Arrays.asList(items), items.length);
  }

  private static <Item> Node<Item> buildNode(
    BinaryTree<Item> tree,
    DynamicArray<Item> items,
    int index)
  {
    if (index >= items.size())
    {
      return null;
    }
    Node<Item> left = BinaryTree.buildNode(tree, items, index * 2 + 1);
    Node<Item> right = BinaryTree.buildNode(tree, items, index * 2 + 2);
    return new Node<>(tree, left, items.get(index), right);
  }

  public static <Item> BinaryTree<Item> buildInOrder(Iterable<Item> items)
  {
    return BinaryTree.buildInOrder(new DynamicArray<>(items));
  }

  public static <Item> BinaryTree<Item> buildInOrder(
    Iterable<Item> items,
    int size)
  {
    return BinaryTree.buildInOrder(new DynamicArray<>(items, size));
  }

  @SafeVarargs
  public static <Item> BinaryTree<Item> buildInOrder(Item... items)
  {
    return BinaryTree.buildInOrder(Arrays.asList(items), items.length);
  }

  private static <Item> BinaryTree<Item> buildInOrder(DynamicArray<Item> items)
  {
    BinaryTree<Item> tree = new BinaryTree<>();
    tree.root = BinaryTree.buildNodeInOrder(tree, items, 0, items.size());
    return tree;
  }

  private static <Item> Node<Item> buildNodeInOrder(
    BinaryTree<Item> tree,
    DynamicArray<Item> items,
    int start,
    int stop)
  {
    if (start >= stop)
    {
      return null;
    }
    int index = (start + stop) / 2;
    Node<Item> left = BinaryTree.buildNodeInOrder(tree, items, start, index);
    Node<Item> right =
      BinaryTree.buildNodeInOrder(tree, items, index + 1, stop);
    return new Node<>(tree, left, items.get(index), right);
  }

  //</editor-fold>

  /**
   * Get the root node.
   * <p>
   * If there is none (i.e. it is empty), returns {@code null}.
   *
   * @return the root node
   */
  public Node<Item> root()
  {
    return this.root;
  }

  /**
   * Insert the given node as the root.
   *
   * @param root the new root
   * @throws IllegalStateException    if there's already a root, or
   * @throws IllegalArgumentException if the one given already has a parent
   *                                  or is in a different tree
   */
  public void insertRoot(Node<Item> root)
    throws IllegalStateException, IllegalArgumentException
  {
    if (!this.isEmpty())
    {
      throw new IllegalStateException();
    }
    if (root.hasParent() || root.tree != this)
    {
      throw new IllegalStateException();
    }
    this.root = root;
  }

  /**
   * Remove and return the root.
   *
   * @return the old root
   * @throws NoSuchElementException if there is no root
   */
  public Node<Item> removeRoot()
    throws NoSuchElementException
  {
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    Node<Item> root = this.root;
    this.root = null;
    return root;
  }

  /**
   * Get the number of levels below the root of the tree.
   * <p>
   * If the tree is empty, returns {@code -1},
   * otherwise returns a non-negative integer.
   *
   * @return the height
   */
  public int height()
  {
    return this.isEmpty() ? -1 : this.root.height();
  }

  @Override
  public int size()
  {
    return this.isEmpty() ? 0 : this.root.size();
  }

  @Override
  public boolean isEmpty()
  {
    return this.root == null;
  }

  public void printPreOrder()
  {
    if (!this.isEmpty())
    {
      this.root.printPreOrder();
    }
  }

  public void printInOrder()
  {
    if (!this.isEmpty())
    {
      this.root.printInOrder();
    }
  }

  public void printPostOrder()
  {
    if (!this.isEmpty())
    {
      this.root.printPostOrder();
    }
  }

  /**
   * A node in a binary tree.
   *
   * @param <Item> the item type
   */
  public static class Node<Item>
  {
    private Node<Item> parent = null;
    private BinaryTree<Item> tree;
    private Node<Item> left;
    private Item item;
    private Node<Item> right;
    private int height;
    private int size;

    /**
     * Construct a binary node with no parent nor children.
     *
     * @param tree the containing tree
     * @param item the contained item
     */
    public Node(
      BinaryTree<Item> tree,
      Item item)
    {
      this(tree, null, item, null);
    }

    /**
     * Construct a binary node with no parent and only a left child.
     *
     * @param tree the containing tree
     * @param left the left node
     * @param item the contained item
     */
    public Node(
      BinaryTree<Item> tree,
      Node<Item> left,
      Item item)
    {
      this(tree, left, item, null);
    }

    /**
     * Construct a binary node with no parent and only a right child.
     *
     * @param tree  the containing tree
     * @param item  the contained item
     * @param right the right node
     */
    public Node(
      BinaryTree<Item> tree,
      Item item,
      Node<Item> right)
    {
      this(tree, null, item, right);
    }

    /**
     * Construct a binary node with no parent and both children.
     *
     * @param tree  the containing tree
     * @param left  the left node
     * @param item  the contained item
     * @param right the right node
     */
    public Node(
      BinaryTree<Item> tree,
      Node<Item> left,
      Item item,
      Node<Item> right)
    {
      if (left != null)
      {
        if (left.parent != null)
        {
          throw new IllegalArgumentException();
        }
        left.parent = this;
      }
      if (right != null)
      {
        if (right.parent != null)
        {
          throw new IllegalArgumentException();
        }
        right.parent = this;
      }
      this.tree = tree;
      this.left = left;
      this.item = item;
      this.right = right;
      this.recalculateSizeAndHeight();
    }

    /**
     * Get the parent node.
     *
     * @return the parent
     */
    public Node<Item> parent()
    {
      return this.parent;
    }

    /**
     * Get the containing binary tree.
     *
     * @return the tree
     */
    public BinaryTree<Item> tree()
    {
      return this.tree;
    }

    /**
     * Get the left child node.
     *
     * @return the left node
     */
    public Node<Item> left()
    {
      return this.left;
    }

    /**
     * Get the contained item.
     *
     * @return the item
     */
    public Item item()
    {
      return this.item;
    }

    /**
     * Get the right child node.
     *
     * @return the right node
     */
    public Node<Item> right()
    {
      return this.right;
    }

    /**
     * Set the contained item.
     *
     * @param item the new item
     */
    public void setItem(Item item)
    {
      this.item = item;
    }

    /**
     * Check if it has a parent (i.e. isn't the root).
     *
     * @return whether it has a parent
     */
    public boolean hasParent()
    {
      return this.parent != null;
    }

    /**
     * Check if it has a left child.
     *
     * @return whether it has a left child
     */
    public boolean hasLeft()
    {
      return this.left != null;
    }

    /**
     * Check if it has a right child.
     *
     * @return whether it has a right child
     */
    public boolean hasRight()
    {
      return this.right != null;
    }

    /**
     * Check if it is the root (i.e. doesn't have a parent).
     *
     * @return whether it's the root
     */
    public boolean isRoot()
    {
      return !this.hasParent();
    }

    /**
     * Check if it is a leaf (i.e. doesn't have either child).
     *
     * @return whether it's a leaf
     */
    public boolean isLeaf()
    {
      return !this.hasLeft() && !this.hasRight();
    }

    /**
     * Check if it is a left child (i.e. has a parent and is its left child).
     *
     * @return whether it's a left child
     */
    public boolean isLeft()
    {
      return this.hasParent() && this.parent.left == this;
    }

    /**
     * Check if it is a right child (i.e. has a parent and is its right child).
     *
     * @return whether it's a right child
     */
    public boolean isRight()
    {
      return this.hasParent() && this.parent.right == this;
    }

    /**
     * Get the number of contained items.
     *
     * @return the size
     */
    public int size()
    {
      return this.size;
    }

    /**
     * Get the number of levels below.
     *
     * @return the height
     */
    public int height()
    {
      return this.height;
    }

    /**
     * Get the number of levels above.
     *
     * @return the level
     */
    public int level()
    {
      return this.hasParent() ? this.parent.level() + 1 : 0;
    }

    /**
     * Calculate the number of contained items.
     * <p>
     * Don't use {@code this.size}, but if the node has children
     * you can use their {@code .size} field(s).
     *
     * @return the size
     */
    private int calculateSize()
    {
      int leftSize = this.hasLeft() ? this.left.size : 0;
      int rightSize = this.hasRight() ? this.right.size : 0;
      return leftSize + rightSize + 1;
    }

    /**
     * Calculate the number of levels below.
     * <p>
     * Don't use {@code this.height}, but if the node has children
     * you can use their {@code .height} field(s).
     *
     * @return the height
     */
    private int calculateHeight()
    {
      int leftHeight = this.hasLeft() ? this.left.height : -1;
      int rightHeight = this.hasRight() ? this.right.height : -1;
      return Math.max(leftHeight, rightHeight) + 1;
    }

    private void recalculateSizeAndHeight()
    {
      this.size = this.calculateSize();
      this.height = this.calculateHeight();
      if (this.hasParent())
      {
        this.parent.recalculateSizeAndHeight();
      }
    }

    /**
     * Print out the items in this subtree, "pre-order".
     * <p>
     * Prints items using {@code System.out.println(item)},
     * in the order "node-left-right".
     */
    public void printPreOrder()
    {
      System.out.println(this.item);
      if (this.hasLeft())
      {
        this.left.printPreOrder();
      }
      if (this.hasRight())
      {
        this.right.printPreOrder();
      }
    }

    /**
     * Print out the items in this subtree, "in-order".
     * <p>
     * Prints items using {@code System.out.println(item)},
     * in the order "left-node-right".
     */
    public void printInOrder()
    {
      if (this.hasLeft())
      {
        this.left.printInOrder();
      }
      System.out.println(this.item);
      if (this.hasRight())
      {
        this.right.printInOrder();
      }
    }

    /**
     * Print out the items in this subtree, "post-order".
     * <p>
     * Prints items using {@code System.out.println(item)},
     * in the order "left-right-node".
     */
    public void printPostOrder()
    {
      if (this.hasLeft())
      {
        this.left.printPostOrder();
      }
      if (this.hasRight())
      {
        this.right.printPostOrder();
      }
      System.out.println(this.item);
    }

    /**
     * Insert the given node as the left child.
     *
     * @param left the new left child
     * @throws IllegalStateException    if there's already a left child
     * @throws IllegalArgumentException if the one given already has a parent
     *                                  or is in a different tree
     */
    public void insertLeft(Node<Item> left)
      throws IllegalStateException, IllegalArgumentException
    {
      if (this.hasLeft())
      {
        throw new IllegalStateException();
      }
      if (left.hasParent() || left.tree != this.tree)
      {
        throw new IllegalStateException();
      }
      this.left = left;
      left.parent = this;
      this.recalculateSizeAndHeight();
    }

    /**
     * Insert the given node as the right child.
     *
     * @param right the new right child
     * @throws IllegalStateException    if there's already a right child
     * @throws IllegalArgumentException if the one given already has a parent
     *                                  or is in a different tree
     */
    public void insertRight(Node<Item> right)
      throws IllegalStateException, IllegalArgumentException
    {
      if (this.hasRight())
      {
        throw new IllegalStateException();
      }
      if (right.hasParent() || right.tree != this.tree)
      {
        throw new IllegalStateException();
      }
      this.right = right;
      right.parent = this;
      this.recalculateSizeAndHeight();
    }

    /**
     * Remove and return the left child.
     *
     * @return the old left child
     * @throws NoSuchElementException if there is no left child
     */
    public Node<Item> removeLeft()
      throws NoSuchElementException
    {
      if (!this.hasLeft())
      {
        throw new NoSuchElementException();
      }
      Node<Item> left = this.left;
      this.left = left.parent = null;
      this.recalculateSizeAndHeight();
      return left;
    }

    /**
     * Remove and return the right child.
     *
     * @return the old right child
     * @throws NoSuchElementException if there is no right child
     */
    public Node<Item> removeRight()
      throws NoSuchElementException
    {
      if (!this.hasRight())
      {
        throw new NoSuchElementException();
      }
      Node<Item> right = this.right;
      this.right = right.parent = null;
      this.recalculateSizeAndHeight();
      return right;
    }

    @Override
    public String toString()
    {
      return To.string(this.item);
    }
  }

  @Override
  public String toString()
  {
    return this.toString(root);
  }

  private String toString(Node<Item> node)
  {
    if (node == null)
    {
      return "";
    }
    String l = this.toString(node.left);
    String r = this.toString(node.right);
    return "[" + l + ' ' + node + ' ' + r + "]";
  }

  //<editor-fold defaultstate="collapsed" desc="Iteration">

  public Iterable<Node<Item>> preOrderNodes()
  {
    return () -> new NodeIterator<>(this, IterationOrder.PRE);
  }

  public Iterable<Node<Item>> inOrderNodes()
  {
    return () -> new NodeIterator<>(this, IterationOrder.IN);
  }

  public Iterable<Node<Item>> postOrderNodes()
  {
    return () -> new NodeIterator<>(this, IterationOrder.POST);
  }

  public Iterable<Node<Item>> reversePreOrderNodes()
  {
    return () -> new NodeIterator<>(this, IterationOrder.REVERSE_PRE);
  }

  public Iterable<Node<Item>> reverseInOrderNodes()
  {
    return () -> new NodeIterator<>(this, IterationOrder.REVERSE_IN);
  }

  public Iterable<Node<Item>> reversePostOrderNodes()
  {
    return () -> new NodeIterator<>(this, IterationOrder.REVERSE_POST);
  }

  public Iterable<Item> preOrder()
  {
    return Iterators.applyEach(this.preOrderNodes(), Node::item);
  }

  public Iterable<Item> inOrder()
  {
    return Iterators.applyEach(this.inOrderNodes(), Node::item);
  }

  public Iterable<Item> postOrder()
  {
    return Iterators.applyEach(this.postOrderNodes(), Node::item);
  }

  public Iterable<Item> reversePreOrder()
  {
    return Iterators.applyEach(this.reversePreOrderNodes(), Node::item);
  }

  public Iterable<Item> reverseInOrder()
  {
    return Iterators.applyEach(this.reverseInOrderNodes(), Node::item);
  }

  public Iterable<Item> reversePostOrder()
  {
    return Iterators.applyEach(this.reversePostOrderNodes(), Node::item);
  }

  @Override
  public Iterable<Item> items()
  {
    return this.preOrder();
  }

  public enum IterationOrder
  {
    PRE,
    IN,
    POST,
    REVERSE_PRE,
    REVERSE_IN,
    REVERSE_POST,
  }

  public static class NodeIterator<Item>
    implements Iterator<Node<Item>>
  {
    private Node<Item> node;
    private NodeIterator<Item> left;
    private NodeIterator<Item> right;
    private IterationOrder order;

    /**
     * Construct an iterator over the nodes in a binary tree.
     *
     * @param tree  the binary tree
     * @param order the order nodes should be iterated in
     */
    public NodeIterator(BinaryTree<Item> tree, IterationOrder order)
    {
      this(tree.root(), order);
    }

    private NodeIterator(Node<Item> node, IterationOrder order)
    {
      this.order = order;
      this.node = node;
      if (node.hasLeft())
      {
        this.left = new NodeIterator<>(node.left(), order);
      }
      if (node.hasRight())
      {
        this.right = new NodeIterator<>(node.right(), order);
      }
    }

    private boolean hasNode()
    {
      return this.node != null;
    }

    private boolean hasLeft()
    {
      return this.left != null && this.left.hasNext();
    }

    private boolean hasRight()
    {
      return this.right != null && this.right.hasNext();
    }

    private Node<Item> node()
    {
      Node<Item> node = this.node;
      this.node = null;
      return node;
    }

    private Node<Item> left()
    {
      return this.left.next();
    }

    private Node<Item> right()
    {
      return this.right.next();
    }

    @Override
    public boolean hasNext()
    {
      return this.hasNode() || this.hasLeft() || this.hasRight();
    }

    @Override
    public Node<Item> next()
      throws NoSuchElementException
    {
      switch (this.order)
      {
        case PRE:
          if (this.hasNode())
          {
            return this.node();
          }
          if (this.hasLeft())
          {
            return this.left();
          }
          if (this.hasRight())
          {
            return this.right();
          }
          break;
        case IN:
          if (this.hasLeft())
          {
            return this.left();
          }
          if (this.hasNode())
          {
            return this.node();
          }
          if (this.hasRight())
          {
            return this.right();
          }
          break;
        case POST:
          if (this.hasLeft())
          {
            return this.left();
          }
          if (this.hasRight())
          {
            return this.right();
          }
          if (this.hasNode())
          {
            return this.node();
          }
          break;
        case REVERSE_PRE:
          if (this.hasNode())
          {
            return this.node();
          }
          if (this.hasRight())
          {
            return this.right();
          }
          if (this.hasLeft())
          {
            return this.left();
          }
          break;
        case REVERSE_IN:
          if (this.hasRight())
          {
            return this.right();
          }
          if (this.hasNode())
          {
            return this.node();
          }
          if (this.hasLeft())
          {
            return this.left();
          }
          break;
        case REVERSE_POST:
          if (this.hasRight())
          {
            return this.right();
          }
          if (this.hasLeft())
          {
            return this.left();
          }
          if (this.hasNode())
          {
            return this.node();
          }
          break;
      }
      throw new NoSuchElementException();
    }
  }

  //</editor-fold>
}
