package dsa.lab06.exercises;

import dsa.lab03.base.Stack;
import dsa.lab03.solutions.ArrayStack;
import dsa.lib.To;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BinaryTree")
public class BinaryTreeTests
{
  @ParameterizedTest
  @DisplayName("level")
  @FieldSource("dsa.lib.examples.lab06.BinaryTrees#AND_NODES")
  <Item> void level(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    int correctLevel = solutionNode.level();
    assertEquals(correctLevel, node.level());
  }

  @ParameterizedTest
  @DisplayName("calculate size")
  @FieldSource("dsa.lib.examples.lab06.BinaryTrees#AND_NODES")
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
    int correctSize = (int) solutionCalculateSize.invoke(solutionNode);
    assertEquals(correctSize, (int) calculateSize.invoke(node));
  }

  @ParameterizedTest
  @DisplayName("calculate height")
  @FieldSource("dsa.lib.examples.lab06.BinaryTrees#AND_NODES")
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
    int correctHeight = (int) solutionCalculateHeight.invoke(solutionNode);
    assertEquals(correctHeight, (int) calculateHeight.invoke(node));
  }

  @ParameterizedTest
  @DisplayName("print pre order")
  @FieldSource("dsa.lib.examples.lab06.BinaryTrees#AND_NODES")
  <Item> void printPreOrder(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    String correctOutput = captureOutput(solutionNode::printPreOrder);
    assertEquals(correctOutput, captureOutput(node::printPreOrder));
  }

  @ParameterizedTest
  @DisplayName("print in order")
  @FieldSource("dsa.lib.examples.lab06.BinaryTrees#AND_NODES")
  <Item> void printInOrder(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    String correctOutput = captureOutput(solutionNode::printInOrder);
    assertEquals(correctOutput, captureOutput(node::printInOrder));
  }

  @ParameterizedTest
  @DisplayName("print post order")
  @FieldSource("dsa.lib.examples.lab06.BinaryTrees#AND_NODES")
  <Item> void printPostOrder(BinaryTree<Item> tree, BinaryTree.Node<Item> node)
  {
    dsa.lab06.solutions.BinaryTree<Item> solutionTree =
      similarSolution(tree);
    dsa.lab06.solutions.BinaryTree.Node<Item> solutionNode =
      fromPath(solutionTree, toPath(node));
    String correctOutput = captureOutput(solutionNode::printPostOrder);
    assertEquals(correctOutput, captureOutput(node::printPostOrder));
  }

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
}
