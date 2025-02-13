package dsa.lib;

import static dsa.lib.Iterators.*;

public class Data
{
  public static class Ints
  {
    public static final Iterable<Integer>
      ZERO = singletonIterable(0),
      POSITIVE = iterable(1, 100, Integer.MAX_VALUE),
      NEGATIVE = iterable(-1, -40, Integer.MIN_VALUE),
      NON_ZERO = chain(POSITIVE, NEGATIVE),
      NON_NEGATIVE = chain(ZERO, POSITIVE),
      NON_POSITIVE = chain(ZERO, NEGATIVE),
      ALL = chain(NON_NEGATIVE, NEGATIVE);
  }

  public static class Strings
  {
    public static final Iterable<String>
      NULL = singletonIterable(null),
      EMPTY = singletonIterable(""),
      NON_EMPTY = iterable("CS", ")(*&^%$£\"!"),
      NON_NULL = chain(EMPTY, NON_EMPTY),
      ALL = chain(NULL, NON_NULL);
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
          new Integer[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE, -1, -2}),
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
          new String[]{"LOREM", "IPSUM", "DOLOR", "SIT", "AMET!"}),
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
      public static final Iterable<Object[]>
        EMPTY = singletonIterable(new Object[]{}),
        SINGLETON = chain(
          uniquesEach(Arrays.Ints.SINGLETON),
          uniquesEach(Arrays.Strings.SINGLETON)),
        MULTI_ITEM = chain(
          uniquesEach(Arrays.Ints.MULTI_ITEM),
          uniquesEach(Arrays.Strings.MULTI_ITEM)),
        NON_EMPTY = chain(SINGLETON, MULTI_ITEM),
        ALL = chain(EMPTY, NON_EMPTY);
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
}
