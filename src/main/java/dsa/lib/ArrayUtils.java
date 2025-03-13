package dsa.lib;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class ArrayUtils
{
  @SuppressWarnings("unchecked")
  public static <T> T[] make(int size)
  {
    return (T[]) new Object[size];
  }

  @SuppressWarnings("unchecked")
  public static <Item> Item[] make(Class<?> itemClass, int size)
  {
    return (Item[]) Array.newInstance(itemClass, size);
  }


  public static <Item> IntFunction<Item[]> makeUntyped()
  {
    return ArrayUtils::make;
  }

  public static <Item> IntFunction<Item[]> makeTyped(Class<?> itemClass)
  {
    return size -> make(itemClass, size);
  }


  public static <Item> Item[] empty(IntFunction<Item[]> makeArray)
  {
    return makeArray.apply(0);
  }

  public static <Item> Item[] empty()
  {
    return make(0);
  }

  public static <Item> Item[] empty(Class<?> itemClass)
  {
    return make(itemClass, 0);
  }


  public static <Item> Item[] singleton(IntFunction<Item[]> makeArray, Item item)
  {
    Item[] items = makeArray.apply(1);
    items[0] = item;
    return items;
  }

  public static <Item> Item[] singleton(Item item)
  {
    return singleton(makeUntyped(), item);
  }

  public static <Item> Item[] singleton(Class<?> itemClass, Item item)
  {
    return singleton(makeTyped(itemClass), item);
  }


  public static <Item> Item[] repeat(
    IntFunction<Item[]> makeArray,
    int repetitions,
    Item item)
  {
    Item[] items = makeArray.apply(repetitions);
    for (int i = 0; i < repetitions; i++)
    {
      items[i] = item;
    }
    return items;
  }

  public static <Item> Item[] repeat(Class<?> itemClass, int repetitions, Item item)
  {
    return repeat(makeTyped(itemClass), repetitions, item);
  }

  public static <Item> Item[] repeat(int repetitions, Item item)
  {
    return repeat(makeUntyped(), repetitions, item);
  }


  public static <Item> Item[] from(
    IntFunction<Item[]> makeArray,
    Collection<Item> items)
  {
    Item[] itemsArray = makeArray.apply(items.size());
    return items.toArray(itemsArray);
  }

  public static <Item> Item[] from(Class<?> itemClass, Collection<Item> items)
  {
    return from(makeTyped(itemClass), items);
  }

  @SuppressWarnings("unchecked")
  public static <Item> Item[] from(Collection<Item> items)
  {
    return (Item[]) items.toArray();
  }


  public static <Item> Item[] from(IntFunction<Item[]> makeArray, Iterable<Item> items)
  {
    List<Item> itemsList = new ArrayList<>();
    items.forEach(itemsList::add);
    return itemsList.toArray(makeArray.apply(itemsList.size()));
  }

  public static <Item> Item[] from(Class<?> itemClass, Iterable<Item> items)
  {
    return from(makeTyped(itemClass), items);
  }

  @SuppressWarnings("unchecked")
  public static <Item> Item[] from(Iterable<Item> items)
  {
    ArrayList<Item> itemsList = new ArrayList<>();
    items.forEach(itemsList::add);
    return (Item[]) itemsList.toArray();
  }


  public static <Item> Item[] from(IntFunction<Item[]> makeArray, Iterator<Item> items)
  {
    return from(makeArray, () -> items);
  }

  public static <Item> Item[] from(Class<?> itemClass, Iterator<Item> items)
  {
    return from(makeTyped(itemClass), items);
  }

  public static <Item> Item[] from(Iterator<Item> items)
  {
    return from(makeUntyped(), items);
  }


  public static <Item> Item[] from(IntFunction<Item[]> makeArray, Stream<Item> items)
  {
    return items.toArray(makeArray);
  }

  public static <Item> Item[] from(Class<?> itemClass, Stream<Item> items)
  {
    return from(makeTyped(itemClass), items);
  }

  @SuppressWarnings("unchecked")
  public static <Item> Item[] from(Stream<Item> items)
  {
    return (Item[]) items.toArray();
  }


  public static <Item> Item[] from(
    IntFunction<Item[]> makeArray,
    int size,
    IntFunction<Item> item)
  {
    Item[] items = makeArray.apply(size);
    for (int i = 0; i < size; i++)
    {
      items[i] = item.apply(i);
    }
    return items;
  }

  public static <Item> Item[] from(
    Class<?> itemClass,
    int size,
    IntFunction<Item> item)
  {
    return from(makeTyped(itemClass), size, item);
  }

  public static <Item> Item[] from(int size, IntFunction<Item> item)
  {
    return from(makeUntyped(), size, item);
  }


  @SafeVarargs
  public static <Item> Item[] chain(IntFunction<Item[]> makeArray, Item[]... itemss)
  {
    int chainedSize = 0;
    for (Item[] items : itemss)
    {
      chainedSize += items.length;
    }
    Item[] chained = makeArray.apply(chainedSize);
    int offset = 0;
    for (Item[] items : itemss)
    {
      copy(items, chained, offset);
      offset += items.length;
    }
    return chained;
  }

  @SafeVarargs
  public static <Item> Item[] chain(Item[]... itemss)
  {
    return chain(makeUntyped(), itemss);
  }

  @SafeVarargs
  public static <Item> Item[] chain(Class<?> itemClass, Item[]... itemss)
  {
    return chain(makeTyped(itemClass), itemss);
  }


  public static <Item, Result> Result[] replace(
    IntFunction<Result[]> makeResultArray,
    Item[] items,
    BiFunction<Item, Integer, Result> function)
  {
    int size = items.length;
    Result[] results = makeResultArray.apply(size);
    for (int i = 0; i < size; i++)
    {
      results[i] = function.apply(items[i], i);
    }
    return results;
  }

  public static <Item, Result> Result[] replace(
    Item[] items,
    BiFunction<Item, Integer, Result> function)
  {
    return replace(makeUntyped(), items, function);
  }

  public static <Item, Result> Result[] replace(
    Class<?> resultClass,
    Item[] items,
    BiFunction<Item, Integer, Result> function)
  {
    return replace(makeTyped(resultClass), items, function);
  }

  public static <Item, Result> Result[] replace(
    IntFunction<Result[]> makeResultArray,
    Item[] items,
    Function<Item, Result> function)
  {
    return replace(makeResultArray, items, ignoringIndex(function));
  }

  public static <Item, Result> Result[] replace(
    Item[] items,
    Function<Item, Result> function)
  {
    return replace(items, ignoringIndex(function));
  }

  public static <Item, Result> Result[] replace(
    Class<?> resultClass,
    Item[] items,
    Function<Item, Result> function)
  {
    return replace(resultClass, items, ignoringIndex(function));
  }


  public static <Item> void copy(
    int size,
    Item[] from,
    int fromStart,
    Item[] to,
    int toStart)
  {
    System.arraycopy(from, fromStart, to, toStart, size);
  }

  public static <Item> void copy(
    int size,
    Item[] from,
    int fromStart,
    Item[] to)
  {
    copy(size, from, fromStart, to, 0);
  }

  public static <Item> void copy(
    int size,
    Item[] from,
    Item[] to,
    int toStart)
  {
    copy(size, from, 0, to, toStart);
  }

  public static <Item> void copy(int size, Item[] from, Item[] to)
  {
    copy(size, from, 0, to, 0);
  }

  public static <Item> void copy(Item[] from, int fromStart, Item[] to, int toStart)
  {
    copy(Math.min(from.length, to.length), from, fromStart, to, toStart);
  }

  public static <Item> void copy(Item[] from, int fromStart, Item[] to)
  {
    copy(from, fromStart, to, 0);
  }

  public static <Item> void copy(Item[] from, Item[] to, int toStart)
  {
    copy(from, 0, to, toStart);
  }

  public static <Item> void copy(Item[] from, Item[] to)
  {
    copy(from, 0, to, 0);
  }


  public static <Item> Item[] copy(IntFunction<Item[]> makeArray, Item[] items)
  {
    int size = items.length;
    Item[] copy = makeArray.apply(size);
    copy(items, copy);
    return copy;
  }

  public static <Item> Item[] copy(Item[] items)
  {
    return copy(makeUntyped(), items);
  }

  public static <Item> Item[] copy(Class<?> itemClass, Item[] items)
  {
    return copy(makeTyped(itemClass), items);
  }


  public static <Item> Item[] sorted(
    IntFunction<Item[]> makeArray,
    Item[] items)
  {
    int size = items.length;
    Item[] sorted = makeArray.apply(size);
    Arrays.sort(sorted);
    return sorted;
  }

  public static <Item> Item[] sorted(Item[] items)
  {
    return sorted(makeUntyped(), items);
  }

  public static <Item> Item[] sorted(Class<?> itemClass, Item[] items)
  {
    return sorted(makeTyped(itemClass), items);
  }

  public static <Item> Item[] sorted(
    IntFunction<Item[]> makeArray,
    Item[] items,
    Comparator<Item> comparator)
  {
    Item[] sorted = copy(makeArray, items);
    Arrays.sort(sorted, comparator);
    return sorted;
  }

  public static <Item> Item[] sorted(Item[] items, Comparator<Item> comparator)
  {
    return sorted(makeUntyped(), items, comparator);
  }

  public static <Item> Item[] sorted(
    Class<?> itemClass,
    Item[] items,
    Comparator<Item> comparator)
  {
    return sorted(makeTyped(itemClass), items, comparator);
  }


  public static <Item> Item[] reversed(IntFunction<Item[]> makeArray, Item[] items)
  {
    int size = items.length;
    Item[] reversed = makeArray.apply(size);
    for (int i = 0; i < size; i++)
    {
      reversed[i] = items[size - 1 - i];
    }
    return reversed;
  }

  public static <Item> Item[] reversed(Item[] items)
  {
    return reversed(makeUntyped(), items);
  }

  public static <Item> Item[] reversed(Class<?> itemClass, Item[] items)
  {
    return reversed(makeTyped(itemClass), items);
  }


  private static <Item, Result> BiFunction<Item, Integer, Result> ignoringIndex(
    Function<Item, Result> function)
  {
    return (item, index) -> function.apply(item);
  }
}
