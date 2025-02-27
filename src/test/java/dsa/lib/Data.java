package dsa.lib;

import dsa.lab04.base.MapItem;
import dsa.lab06.exercises.BinaryTree;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Random;

import static dsa.lib.Iterators.*;

public class Data
{
  public static class Ints
  {
    public static final Iterable<Integer>
      ZERO = singletonIterable(0),
      POSITIVE = iterable(1, 2, 4, 7, 10, 40, 100, Integer.MAX_VALUE),
      NEGATIVE = iterable(-1, -2, -4, -7, -10, -40, -100, Integer.MIN_VALUE),
      NON_ZERO = chain(POSITIVE, NEGATIVE),
      NON_NEGATIVE = chain(ZERO, POSITIVE),
      NON_POSITIVE = chain(ZERO, NEGATIVE),
      ALL = chain(NON_NEGATIVE, NEGATIVE);

    public static final int
      ZERO_SIZE = size(ZERO),
      POSITIVE_SIZE = size(POSITIVE),
      NEGATIVE_SIZE = size(NEGATIVE),
      NON_ZERO_SIZE = size(NON_ZERO),
      NON_NEGATIVE_SIZE = size(NON_NEGATIVE),
      NON_POSITIVE_SIZE = size(NON_POSITIVE),
      ALL_SIZE = size(ALL);
  }

  public static class Strings
  {
    public static final Iterable<String>
      NULL = singletonIterable(null),
      EMPTY = singletonIterable(""),
      NON_EMPTY =
        iterable(
          "A",
          "B",
          "CS",
          "foo",
          "bar",
          "testing testing 123",
          "*&^%$£!"),
      NON_NULL = chain(EMPTY, NON_EMPTY),
      ALL = chain(NON_NULL, NULL);

    public static final int
      NULL_SIZE = size(NULL),
      EMPTY_SIZE = size(EMPTY),
      NON_EMPTY_SIZE = size(NON_EMPTY),
      NON_NULL_SIZE = size(NON_NULL),
      ALL_SIZE = size(ALL);
  }

  public static class Lab04
  {
    @SuppressWarnings("rawtypes")
    public static class MapItems
    {
      // @formatter:off

      public static final Iterable<MapItem<Integer, String>>
        INTS_TO_STRINGS = onlyEvery(
          5,
          onProduct(
            Serializable.class,
            (array) -> new MapItem<>((Integer) array[0], (String) array[1]),
            Ints.ALL,
            Strings.ALL));
      public static final Iterable<MapItem<String, Integer>>
        STRINGS_TO_INTS = onlyEvery(
          5,
          onProduct(
            Serializable.class,
            (array) -> new MapItem<>((String) array[0], (Integer) array[1]),
            Strings.NON_NULL,
            Ints.ALL));
      public static final Iterable<MapItem>
        ALL = chain(INTS_TO_STRINGS, STRINGS_TO_INTS);

      // @formatter:on
    }
  }

  public static class Lab06
  {
    public static class BinaryTrees
    {
      private static <T> BinaryTree<T> make(T[] items, long seed)
      {
        Random random = new Random(seed);
        BinaryTree<T> tree = new BinaryTree<>();
        if (items.length != 0)
        {
          tree.insertRoot(new BinaryTree.Node<>(tree, items[0]));
          for (int i = 1; i < items.length; i++)
          {
            BinaryTree.Node<T> parent = tree.root();
            BinaryTree.Node<T> node =
              new BinaryTree.Node<>(tree, items[i]);
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
        public static final Iterable<BinaryTree<Object>>
          EMPTY = singletonIterable(new BinaryTree<>()),
          NON_EMPTY =
            uniques(
              applyEach(
                repeatCycled(
                  Integer.class,
                  6,
                  Data.Arrays.Uniques.Ints.NON_EMPTY),
                BinaryTrees::make),
              BinaryTrees::equals),
          ALL = chain(EMPTY, NON_EMPTY);
      }

      public static class Strings
      {
        public static final Iterable<BinaryTree<Object>>
          EMPTY = singletonIterable(new BinaryTree<>()),
          NON_EMPTY =
            uniques(
              applyEach(
                repeatCycled(
                  String.class,
                  6,
                  Data.Arrays.Uniques.Strings.NON_EMPTY),
                BinaryTrees::make),
              BinaryTrees::equals),
          ALL = chain(EMPTY, NON_EMPTY);
      }

      public static final Iterable<BinaryTree<Object>>
        EMPTY = singletonIterable(new BinaryTree<>()),
        NON_EMPTY = chain(Ints.NON_EMPTY, Strings.NON_EMPTY),
        ALL = chain(EMPTY, NON_EMPTY);
    }
  }

  public static class Arrays
  {
    public static class Ints
    {
      public static final Iterable<Integer[]>
        EMPTY = singletonIterable(new Integer[]{}),
        SINGLETON = singletonIterable(new Integer[]{2}),
        MULTI_ITEM = iterable(
          new Integer[]{1, 42, 1, 3},
          new Integer[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE, -1, -2},
          new Integer[]{0, 1, 2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29}),
        NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
        ALL = chain(EMPTY, NON_EMPTY);
    }

    public static class Strings
    {
      public static final Iterable<String[]>
        EMPTY = singletonIterable(new String[]{}),
        SINGLETON = singletonIterable(new String[]{
          "supercalifragilisticexpialidocious"}),
        MULTI_ITEM = iterable(
          new String[]{"foo", "bar", "quux", "quux"},
          new String[]{"Hello", null},
          new String[]{"LOREM", "IPSUM", "DOLOR", "SIT", "AMET!"},
          new String[]{
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog",
            "and",
            "the",
            "cow",
            "jumped",
            "over",
            "the",
            "moon"
          }),
        NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
        ALL = chain(EMPTY, NON_EMPTY);
    }

    @SuppressWarnings("rawtypes")
    public static class Comparables
    {
      public static final Iterable<Comparable[]>
        EMPTY = singletonIterable(new Comparable[]{}),
        SINGLETON = chain(Arrays.Ints.SINGLETON, Arrays.Strings.SINGLETON),
        MULTI_ITEM = chain(Arrays.Ints.MULTI_ITEM, Arrays.Strings.MULTI_ITEM),
        NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
        ALL = chain(EMPTY, NON_EMPTY);
    }

    public static class Uniques
    {
      public static class Ints
      {
        public static final Iterable<Integer[]>
          EMPTY = singletonIterable(new Integer[]{}),
          SINGLETON = uniquesEach(Integer.class, Arrays.Ints.SINGLETON),
          MULTI_ITEM = uniquesEach(Integer.class, Arrays.Ints.MULTI_ITEM),
          NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
          ALL = chain(EMPTY, NON_EMPTY);
      }

      public static class Strings
      {
        public static final Iterable<String[]>
          EMPTY = singletonIterable(new String[]{}),
          SINGLETON = uniquesEach(String.class, Arrays.Strings.SINGLETON),
          MULTI_ITEM = uniquesEach(String.class, Arrays.Strings.MULTI_ITEM),
          NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
          ALL = chain(EMPTY, NON_EMPTY);
      }

      /*
      public static final Iterable<Object[]>
        EMPTY = singletonIterable(new Object[]{}),
        SINGLETON =
          Iterators.<Object[]>chain(
            applyEach(
              Ints.SINGLETON,
              (array) -> toArray(Object.class, array)),
            applyEach(
              Strings.SINGLETON,
              (array) -> toArray(Object.class, array))),
        MULTI_ITEM =
          Iterators.<Object[]>chain(
            applyEach(
              Ints.MULTI_ITEM,
              (array) -> toArray(Object.class, array)),
            applyEach(
              Strings.MULTI_ITEM,
              (array) -> toArray(Object.class, array))),
        NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
        ALL = chain(EMPTY, NON_EMPTY);
      */
      public static final Iterable<Object[]>
        EMPTY = singletonIterable(new Object[]{}),
        SINGLETON =
          Iterators.<Object[]>chain(Ints.SINGLETON, Strings.SINGLETON),
        MULTI_ITEM =
          Iterators.<Object[]>chain(Ints.MULTI_ITEM, Strings.MULTI_ITEM),
        NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
        ALL = chain(EMPTY, NON_EMPTY);
    }

    @SuppressWarnings("rawtypes")
    public static class Lab04
    {
      @SuppressWarnings("unchecked")
      public static class UniqueMapItems
      {
        public static class IntsToStrings
        {
          public static final Iterable<MapItem<Integer, String>[]>
            EMPTY = singletonIterable(new MapItem[]{}),
            SINGLETON = onlyEvery(
              7,
              applyEach(
                Data.Lab04.MapItems.INTS_TO_STRINGS,
                (item) -> new MapItem[]{item})),
            MULTI_ITEM = applyEach(
              atMost(
                1000,
                onlyEvery(
                  1000,
                  product(
                    MapItem.class,
                    toArray(
                      Iterable.class,
                      group(
                        Data.Lab04.MapItems.INTS_TO_STRINGS,
                        MapItem::key))))),
              (items, index) -> onlyEvery(
                MapItem.class,
                index % 3,
                Data.Ints.ALL_SIZE - index % Data.Ints.ALL_SIZE,
                items)),
            NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
            ALL = chain(EMPTY, NON_EMPTY);
        }

        public static class StringsToInts
        {
          public static final Iterable<MapItem<String, Integer>[]>
            EMPTY = singletonIterable(new MapItem[]{}),
            SINGLETON = onlyEvery(
              7,
              applyEach(
                Data.Lab04.MapItems.STRINGS_TO_INTS,
                (item) -> new MapItem[]{item})),
            MULTI_ITEM = applyEach(
              atMost(
                1000,
                onlyEvery(
                  1000,
                  product(
                    MapItem.class,
                    toArray(
                      Iterable.class,
                      group(
                        Data.Lab04.MapItems.STRINGS_TO_INTS,
                        MapItem::key))))),
              (items, index) -> onlyEvery(
                MapItem.class,
                index % 3,
                Data.Strings.ALL_SIZE - index % Data.Strings.ALL_SIZE,
                items)),
            NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
            ALL = chain(EMPTY, NON_EMPTY);
        }

        public static final Iterable<MapItem[]>
          EMPTY = singletonIterable(new MapItem[]{}),
          SINGLETON = chain(IntsToStrings.SINGLETON, StringsToInts.SINGLETON),
          MULTI_ITEM = chain(
            IntsToStrings.MULTI_ITEM,
            StringsToInts.MULTI_ITEM),
          NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
          ALL = chain(EMPTY, NON_EMPTY);
      }

      public static class SortedUniqueMapItems
      {
        public static class IntsToStrings
        {
          public static final Iterable<MapItem<Integer, String>[]>
            EMPTY = UniqueMapItems.IntsToStrings.EMPTY,
            SINGLETON = UniqueMapItems.IntsToStrings.SINGLETON,
            MULTI_ITEM =
              sortedEach(
                UniqueMapItems.IntsToStrings.MULTI_ITEM,
                Comparator.comparing(MapItem::key)),
            NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
            ALL = chain(EMPTY, NON_EMPTY);
        }

        public static class StringsToInts
        {
          public static final Iterable<MapItem<String, Integer>[]>
            EMPTY = UniqueMapItems.StringsToInts.EMPTY,
            SINGLETON = UniqueMapItems.StringsToInts.SINGLETON,
            MULTI_ITEM =
              sortedEach(
                UniqueMapItems.StringsToInts.MULTI_ITEM,
                Comparator.comparing(MapItem::key)),
            NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
            ALL = chain(EMPTY, NON_EMPTY);
        }

        public static final Iterable<MapItem[]>
          EMPTY = UniqueMapItems.EMPTY,
          SINGLETON = UniqueMapItems.SINGLETON,
          MULTI_ITEM = chain(
            IntsToStrings.MULTI_ITEM,
            StringsToInts.MULTI_ITEM),
          NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
          ALL = chain(EMPTY, NON_EMPTY);
      }
    }

    @SuppressWarnings("rawtypes")
    public static class SortedUniques
    {
      public static final Iterable<Comparable[]>
        EMPTY = singletonIterable(new Comparable[]{}),
        SINGLETON = chain(
          sortedUniquesEach(Arrays.Ints.SINGLETON),
          sortedUniquesEach(Arrays.Strings.SINGLETON)),
        MULTI_ITEM = chain(
          sortedUniquesEach(Arrays.Ints.MULTI_ITEM),
          sortedUniquesEach(Arrays.Strings.MULTI_ITEM)),
        NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
        ALL = chain(EMPTY, NON_EMPTY);
    }

    public static final Iterable<Object[]>
      EMPTY = singletonIterable(new Object[]{}),
      SINGLETON = chain(Arrays.Ints.SINGLETON, Arrays.Strings.SINGLETON),
      MULTI_ITEM = chain(Arrays.Ints.MULTI_ITEM, Arrays.Strings.MULTI_ITEM),
      NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
      ALL = chain(EMPTY, NON_EMPTY);
  }

  public static class Objects
  {
    public static final Iterable<Object>
      NULL = singletonIterable(null),
      NON_NULL = chain(Ints.ALL, Strings.ALL, Arrays.ALL),
      ALL = chain(NON_NULL, NULL);
  }
}
