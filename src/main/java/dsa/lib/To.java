package dsa.lib;

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
    return object.toString();
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

  private static String typedString(Object object, String string)
  {
    return object.getClass().getSimpleName() + string;
  }
}
