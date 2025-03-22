package dsa.lib.lab06;

import dsa.lab06.exercises.BinaryTree;
import dsa.lib.Source;
import dsa.lib.SourceData;

import java.util.Random;

public class BinaryTreeData
{

  private static <T> BinaryTree<T> make(Source<T> items, long seed)
  {
    Random random = new Random(seed);
    BinaryTree<T> tree = new BinaryTree<>();
    if (items.size() != 0)
    {
      tree.insertRoot(new BinaryTree.Node<>(tree, items.getAt(0)));
      for (int i = 1; i < items.size(); i++)
      {
        BinaryTree.Node<T> parent = tree.root();
        BinaryTree.Node<T> node =
          new BinaryTree.Node<>(tree, items.getAt(i));
        if (random.nextBoolean())
        {
          while (parent.hasLeft())
          {
            parent = parent.hasRight() && random.nextBoolean()
              ? parent.right() : parent.left();
          }
          parent.insertLeft(node);
        }
        else
        {
          while (parent.hasRight())
          {
            parent = parent.hasLeft() && random.nextBoolean()
              ? parent.left() : parent.right();
          }
          parent.insertRight(node);
        }
      }
    }
    return tree;
  }


  private static <T> boolean equals(BinaryTree<T> a, BinaryTree<T> b)
  {
    return a.isEmpty() ? b.isEmpty() : equals(a.root(), b.root());
  }


  private static <T> boolean equals(
    BinaryTree.Node<T> a,
    BinaryTree.Node<T> b)
  {
    return (a == null && b == null) ||
      (a != null && b != null &&
        java.util.Objects.equals(a.item(), b.item()) &&
        (a.hasLeft() ? a.left().equals(b.left()) : !b.hasLeft()) &&
        (a.hasRight() ? a.right().equals(b.right()) : !b.hasRight()));
  }


  public static class Ints
  {

    public static final Source<BinaryTree<Integer>>
      EMPTY = Source.singleton(new BinaryTree<>()),
      NON_EMPTY =
        Source.repeatCycled(SourceData.Uniques.Ints.NON_EMPTY, 6)
          .replace(BinaryTreeData::make)
          .uniques(BinaryTreeData::equals),
      ALL = Source.chain(EMPTY, NON_EMPTY);

  }

  public static class Strings
  {

    public static final Source<BinaryTree<String>>
      EMPTY = Source.singleton(new BinaryTree<>()),
      NON_EMPTY =
        Source.repeatCycled(SourceData.Uniques.Strings.NON_EMPTY, 6)
          .replace(BinaryTreeData::make)
          .uniques(BinaryTreeData::equals),
      ALL = Source.chain(EMPTY, NON_EMPTY);

  }

  public static final Source<BinaryTree<Object>>
    EMPTY = Source.singleton(new BinaryTree<>()),
    NON_EMPTY = Source.chain(Ints.NON_EMPTY.cast(), Strings.NON_EMPTY.cast()),
    ALL = Source.chain(EMPTY, NON_EMPTY);

}
