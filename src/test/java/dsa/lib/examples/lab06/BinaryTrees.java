package dsa.lib.examples.lab06;

import dsa.lab06.exercises.BinaryTree;
import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Random;

import static dsa.lib.Examples.arguments;
import static dsa.lib.Iterators.applyEach;

public class BinaryTrees
{
  public static final Iterable<Arguments> AND_NODES =
    applyEach(
      Data.Lab06.BinaryTrees.NON_EMPTY,
      (tree, index) ->
      {
        Random random = new Random(index);
        BinaryTree.Node<Object> node = tree.root();
        while (!node.isLeaf() && random.nextBoolean())
        {
          node = node.hasLeft() && node.hasRight()
            ? random.nextBoolean() ? node.left() : node.right()
            : node.hasLeft() ? node.left() : node.right();
        }
        return Arguments.of(tree, node);
      });

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(Data.Lab06.BinaryTrees.NON_EMPTY);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Lab06.BinaryTrees.ALL);
}
