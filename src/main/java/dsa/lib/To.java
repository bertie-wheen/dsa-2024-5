package dsa.lib;

import dsa.lab02.base.Container;
import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lab06.solutions.BinaryTree;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * Abandon all coding standards ye who enter here!
 * This is not normally what you would ever want to do.
 * Here we're using reflection
 * https://docs.oracle.com/javase/tutorial/reflect/index.html
 * to essentially manually implement dynamic dispatch.
 * https://en.wikipedia.org/wiki/Dynamic_dispatch
 * Usually you can just let Java select the appropriate
 * overload of a given method (given its argument(s)),
 * but because Java implemented generics using type erasure
 * https://en.wikipedia.org/wiki/Type_erasure
 * https://docs.oracle.com/javase/tutorial/java/generics/erasure.html
 * that doesn't work well in this case.
 * (SinglyLinkedList<T> is erased to SinglyLinkedList<Object>,
 * so this overload is always chosen for the list's items
 * even if one of the others might be better.)
 * We're also using reflection to access private fields in some cases.
 * (In the lab 1 interfaces we don't include iteration,
 * Basically, this is the hackiest Java ever, and is not "good code".
 * Don't write code like this if you can help it.
 */
public class To
{
  public static String string(Object object)
  {
    return To.string(object, "");
  }

  public static String string(Object object, String indent)
  {
    if (Is.string(object))
    {
      return To.string((String) object, indent);
    }
    if (Is.javaArray(object))
    {
      return To.string((Object[]) object, indent);
    }
    if (Is.stringArrayExercise(object))
    {
      return To.string((dsa.lab01.exercises.StringArray) object, indent);
    }
    if (Is.stringArraySolution(object))
    {
      return To.string((dsa.lab01.solutions.StringArray) object, indent);
    }
    if (Is.arrayExercise(object))
    {
      return To.string((dsa.lab01.exercises.Array<?>) object, indent);
    }
    if (Is.arraySolution(object))
    {
      return To.string((dsa.lab01.solutions.Array<?>) object, indent);
    }
    if (Is.binaryTree(object))
    {
      return To.string((BinaryTree<?>) object, indent);
    }
    if (Is.map(object))
    {
      return To.string((Map<?, ?>) object, indent);
    }
    if (Is.mapItem(object))
    {
      return To.string((MapItem<?, ?>) object, indent);
    }
    if (Is.container(object))
    {
      return To.string((Container<?>) object, indent);
    }
    return object == null ? "null" : object.toString();
  }

  public static String string(String string)
  {
    return To.string(string, "");
  }

  public static String string(String string, String indent)
  {
    return "\"" + string.replaceAll("\"", Matcher.quoteReplacement("\\\""))
      .replaceAll("\n", Matcher.quoteReplacement("\\n")) + "\"";
  }

  public static String string(Object[] array)
  {
    return To.string(array, "");
  }

  public static String string(Object[] array, String indent)
  {
    return To.typedString(
      array,
      To.untypedString(
        Iterators.iterable(array),
        indent));
  }

  public static <Item> String string(Iterable<Item> iterable)
  {
    return To.string(iterable, "");
  }

  public static <Item> String string(Iterable<Item> iterable, String indent)
  {
    return To.typedString(iterable, To.untypedString(iterable, indent));
  }

  public static <Item> String untypedString(Iterable<Item> iterable)
  {
    return To.untypedString(iterable, "");
  }

  public static <Item> String untypedString(
    Iterable<Item> iterable,
    String indent)
  {
    StringBuilder sb = new StringBuilder();
    sb.append('(');
    Iterator<Item> iterator = iterable.iterator();
    if (iterator.hasNext())
    {
      sb.append('\n');
      while (iterator.hasNext())
      {
        Item item = iterator.next();
        String itemIndent = indent + "  ";
        sb.append(itemIndent);
        sb.append(To.string(item, itemIndent));
        sb.append('\n');
      }
      sb.append(indent);
    }
    sb.append(')');
    return sb.toString();
  }

  public static String string(dsa.lab01.exercises.StringArray stringArray)
  {
    return To.string(stringArray, "");
  }

  public static String string(
    dsa.lab01.exercises.StringArray stringArray,
    String indent)
  {
    return To.typedString(
      stringArray, To.untypedString(
        Arrays.asList(To.<String[]>field(
          stringArray,
          dsa.lab01.exercises.StringArray.class,
          "strings")), indent));
  }

  public static String string(dsa.lab01.solutions.StringArray stringArray)
  {
    return To.string(stringArray, "");
  }

  public static String string(
    dsa.lab01.solutions.StringArray stringArray,
    String indent)
  {
    return To.typedString(
      stringArray, To.untypedString(
        Arrays.asList(To.<String[]>field(
          stringArray,
          dsa.lab01.solutions.StringArray.class,
          "strings")), indent));
  }

  public static <Item> String string(dsa.lab01.exercises.Array<Item> array)
  {
    return To.string(array, "");
  }

  public static <Item> String string(
    dsa.lab01.exercises.Array<Item> array,
    String indent)
  {
    return To.typedString(
      array, To.untypedString(
        Arrays.asList(To.<Item[]>field(
          array,
          dsa.lab01.exercises.Array.class,
          "items")), indent));
  }

  public static <Item> String string(dsa.lab01.solutions.Array<Item> array)
  {
    return To.string(array, "");
  }

  public static <Item> String string(
    dsa.lab01.solutions.Array<Item> array,
    String indent)
  {
    return To.typedString(
      array, To.untypedString(
        Arrays.asList(To.<Item[]>field(
          array,
          dsa.lab01.solutions.Array.class,
          "items")), indent));
  }

  public static <Item> String string(Container<Item> container)
  {
    return To.string(container, "");
  }

  public static <Item> String string(Container<Item> container, String indent)
  {
    return To.string((Iterable<Item>) container, indent);
  }

  public static <Key, Value> String string(Map<Key, Value> map)
  {
    return To.string(map, "");
  }

  public static <Key, Value> String string(Map<Key, Value> map, String indent)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(map.getClass().getSimpleName());
    sb.append('(');
    if (!map.isEmpty())
    {
      sb.append('\n');
      for (MapItem<Key, Value> item : map)
      {
        String itemIndent = indent + "  ";
        sb.append(itemIndent);
        sb.append(To.string(item, itemIndent));
        sb.append('\n');
      }
      sb.append(indent);
    }
    sb.append(')');
    return sb.toString();
  }

  public static <Key, Value> String string(MapItem<Key, Value> item)
  {
    return To.string(item, "");
  }

  public static <Key, Value> String string(
    MapItem<Key, Value> item,
    String indent)
  {
    return To.string(item.key(), indent) + " => " + To.string(
      item.value(),
      indent);
  }

  public static <Item, Node> String string(BinaryTree<Item> tree)
  {
    return To.string(tree, "");
  }

  public static <Item, Node> String string(BinaryTree<Item> tree, String indent)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(tree.getClass().getSimpleName());
    sb.append('(');
    if (!tree.isEmpty())
    {
      sb.append('\n');
      String itemIndent = indent + "  ";
      sb.append(itemIndent);
      sb.append(To.string(tree, tree.root(), itemIndent));
      sb.append('\n');
      sb.append(indent);
    }
    sb.append(')');
    return sb.toString();
  }

  public static <Item> String string(
    BinaryTree<Item> tree,
    BinaryTree.Node<Item> node)
  {
    return To.string(tree, node, "");
  }

  public static <Item> String string(
    BinaryTree<Item> tree,
    BinaryTree.Node<Item> node,
    String indent)
  {
    if (node == null)
    {
      return "null";
    }
    StringBuilder sb = new StringBuilder();
    String item = To.string(node.item(), indent);
    String spaces = item.substring(item.lastIndexOf('\n') + 1)
      .replaceFirst("^ +", "")
      .replaceAll(".", " ");
    sb.append(item);
    if (!node.isLeaf())
    {
      sb.append(" <-P-+-R-> ");
      if (node.hasRight())
      {
        sb.append(To.string(
          tree,
          node.right(),
          indent + spaces + "     |     "));
      }
      sb.append('\n');
      sb.append(indent);
      sb.append(spaces);
      sb.append("     '-L-> ");
      if (node.hasLeft())
      {
        sb.append(To.string(
          tree,
          node.left(),
          indent + spaces + "           "));
      }
    }
    return sb.toString();
  }

  private static String typedString(Object object, String string)
  {
    return object.getClass().getSimpleName() + string;
  }

  @SuppressWarnings("unchecked")
  private static <FieldType> FieldType field(
    Object object,
    Class<?> class_,
    String fieldName)
  {
    try
    {
      Field field = class_.getDeclaredField(fieldName);
      field.setAccessible(true);
      return (FieldType) field.get(object);
    }
    catch (NoSuchFieldException | IllegalAccessException e)
    {
      throw new RuntimeException("ERROR: Please let the module staff know!", e);
    }
  }
}
