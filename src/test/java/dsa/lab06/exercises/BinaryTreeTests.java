package dsa.lab06.exercises;

import dsa.lab03.base.Stack;
import dsa.lab03.solutions.ArrayStack;
import dsa.lib.DefaultMethodSource;
import dsa.lib.Source;
import dsa.lib.To;
import dsa.lib.lab06.BinaryTreeData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BinaryTree")
public class BinaryTreeTests
{

  @DisplayName("level")
  @ParameterizedTest
  @DefaultMethodSource
  <Item> void level(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    int solutionLevel = solutionNode.level();
    assertEquals(solutionLevel, node.level());
  }


  @DisplayName("calculate size")
  @ParameterizedTest
  @DefaultMethodSource
  <Item> void calculateSize(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
    throws
    NoSuchMethodException,
    InvocationTargetException,
    IllegalAccessException
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    //<editor-fold defaultstate="collapsed" desc="private hack">
    Method solutionCalculateSize =
      solutionNode.getClass().getDeclaredMethod("calculateSize");
    Method calculateSize = node.getClass().getDeclaredMethod("calculateSize");
    solutionCalculateSize.setAccessible(true);
    calculateSize.setAccessible(true);
    //</editor-fold>
    int solutionSize = (int) solutionCalculateSize.invoke(solutionNode);
    assertEquals(solutionSize, (int) calculateSize.invoke(node));
  }


  @DisplayName("calculate height")
  @ParameterizedTest
  @DefaultMethodSource
  <Item> void calculateHeight(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
    throws
    NoSuchMethodException,
    InvocationTargetException,
    IllegalAccessException
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    //<editor-fold defaultstate="collapsed" desc="private hack">
    Method solutionCalculateHeight =
      solutionNode.getClass().getDeclaredMethod("calculateHeight");
    Method calculateHeight =
      node.getClass().getDeclaredMethod("calculateHeight");
    solutionCalculateHeight.setAccessible(true);
    calculateHeight.setAccessible(true);
    //</editor-fold>
    int solutionHeight = (int) solutionCalculateHeight.invoke(solutionNode);
    assertEquals(solutionHeight, (int) calculateHeight.invoke(node));
  }


  @DisplayName("print pre order")
  @ParameterizedTest
  @DefaultMethodSource
  <Item> void printPreOrder(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    String solutionOutput = captureOutput(solutionNode::printPreOrder);
    assertEquals(solutionOutput, captureOutput(node::printPreOrder));
  }


  @DisplayName("print in order")
  @ParameterizedTest
  @DefaultMethodSource
  <Item> void printInOrder(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    String solutionOutput = captureOutput(solutionNode::printInOrder);
    assertEquals(solutionOutput, captureOutput(node::printInOrder));
  }


  @DisplayName("print post order")
  @ParameterizedTest
  @DefaultMethodSource
  <Item> void printPostOrder(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    String solutionOutput = captureOutput(solutionNode::printPostOrder);
    assertEquals(solutionOutput, captureOutput(node::printPostOrder));
  }


  //<editor-fold defaultstate="collapsed" desc="arguments">
  static Source<Arguments> arguments()
  {
    return BinaryTreeData.NON_EMPTY.replace((tree, index) ->
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
  }
  //</editor-fold>


  //<editor-fold defaultstate="collapsed" desc="helper functions">
  private static <Item> dsa.lab06.solutions.BinaryTree<Item> similarSolution(
    BinaryTree<Item> tree)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      new dsa.lab06.solutions.BinaryTree<>();
    solutionTree.insertRoot(similarSolution(solutionTree, tree.root()));
    return solutionTree;
  }


  private static <Item> dsa.lab06.solutions.BinaryTree.Node<Item> similarSolution(
    dsa.lab06.solutions.BinaryTree<Item> tree,
    BinaryTree.Node<Item> node)
  {
    if (node == null)
    {
      return null;
    }
    return new dsa.lab06.solutions.BinaryTree.Node<>(
      tree,
      similarSolution(tree, node.left()),
      node.item(),
      similarSolution(tree, node.right()));
  }


  private static <Item> Stack<Boolean> toPath(BinaryTree.Node<Item> node)
  {
    Stack<Boolean> path = new ArrayStack<>();
    BinaryTree.Node<Item> parent;
    while ((parent = node.parent()) != null)
    {
      path.push(parent.left() == node);
      node = parent;
    }
    return path;
  }


  private static <Item> dsa.lab06.solutions.BinaryTree.Node<Item> fromPath(
    dsa.lab06.solutions.BinaryTree<Item> tree,
    Stack<Boolean> path)
  {
    dsa.lab06.solutions.BinaryTree.Node<Item> node = tree.root();
    while (!path.isEmpty())
    {
      node = path.pop() ? node.left() : node.right();
    }
    return node;
  }


  private static String captureOutput(Runnable runnable)
  {
    PrintStream standardOut = System.out;
    OutputStream output = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(output);
    System.setOut(out);
    runnable.run();
    out.flush();
    System.setOut(standardOut);
    String[] lines = output.toString().split("\n");
    for (int i = 0; i < lines.length; i++)
    {
      lines[i] = To.string(lines[i]);
    }
    return String.join(",", lines);
  }
  //</editor-fold>

}
