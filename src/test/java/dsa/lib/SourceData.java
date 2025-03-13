package dsa.lib;

public class SourceData
{
  public static class Ints
  {
    public static final Source<Source<Integer>>
      EMPTY = Source.singleton(Source.empty()),
      SINGLETON = Source.singleton(Source.singleton(2)),
      MULTI_ITEM = Source.from(
        Source.from(1, 42, 1, 3),
        Source.from(Integer.MAX_VALUE, 0, Integer.MIN_VALUE, -1, -2),
        Source.from(0, 1, 2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29)),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);
  }

  public static class Strings
  {
    public static class NonNull
    {
      public static final Source<Source<String>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = Source.singleton(Source.singleton(
          "supercalifragilisticexpialidocious")),
        MULTI_ITEM = Source.from(
          Source.from("foo", "bar", "quux", "quux"),
          Source.from("LOREM", "IPSUM", "DOLOR", "SIT", "AMET!"),
          Source.from(
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
          )),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);
    }

    public static final Source<Source<String>>
      EMPTY = NonNull.EMPTY,
      SINGLETON = NonNull.SINGLETON,
      MULTI_ITEM = Source.chain(
        NonNull.MULTI_ITEM,
        Source.singleton(Source.from("Hello", null))),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);
  }

  public static final Source<Source<Object>>
    EMPTY = Source.singleton(Source.empty()),
    SINGLETON = Source.chain(Ints.SINGLETON.cast(), Strings.SINGLETON.cast()),
    MULTI_ITEM = Source.from(
      Source.from("foo", "bar", "quux", "quux"),
      Source.from("Hello", null),
      Source.from("LOREM", "IPSUM", "DOLOR", "SIT", "AMET!"),
      Source.from(
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
      )),
    NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
    ALL = Source.chain(EMPTY, NON_EMPTY);

  public static class Uniques
  {
    public static class Ints
    {
      public static final Source<Source<Integer>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = Source.uniquesEach(SourceData.Ints.SINGLETON),
        MULTI_ITEM = Source.uniquesEach(SourceData.Ints.MULTI_ITEM),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);
    }

    public static class Strings
    {
      public static final Source<Source<String>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = Source.uniquesEach(SourceData.Strings.NonNull.SINGLETON),
        MULTI_ITEM = Source.uniquesEach(SourceData.Strings.NonNull.MULTI_ITEM),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);
    }

    @SuppressWarnings({"rawtypes", "RedundantSuppression"})
    public static final Source<Source<Object>>
      EMPTY = Source.singleton(Source.empty()),
      SINGLETON = Source.chain(
        Ints.SINGLETON.cast(),
        Strings.SINGLETON.cast()),
      MULTI_ITEM = Source.chain(
        Ints.MULTI_ITEM.cast(),
        Strings.MULTI_ITEM.cast()),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);
  }

  public static class Sorted
  {
    public static class Ints
    {
      public static final Source<Source<Integer>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = Source.sortedEach(SourceData.Ints.SINGLETON),
        MULTI_ITEM = Source.sortedEach(SourceData.Ints.MULTI_ITEM),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);
    }

    public static class Strings
    {
      public static final Source<Source<String>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = Source.sortedEach(SourceData.Strings.NonNull.SINGLETON),
        MULTI_ITEM = Source.sortedEach(SourceData.Strings.NonNull.MULTI_ITEM),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);
    }

    @SuppressWarnings({"rawtypes", "RedundantSuppression"})
    public static final Source<Source<Object>>
      EMPTY = Source.singleton(Source.empty()),
      SINGLETON = Source.chain(
        Ints.SINGLETON.cast(),
        Strings.SINGLETON.cast()),
      MULTI_ITEM = Source.chain(
        Ints.MULTI_ITEM.cast(),
        Strings.MULTI_ITEM.cast()),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);
  }
}
