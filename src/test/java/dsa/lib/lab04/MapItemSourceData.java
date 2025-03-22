package dsa.lib.lab04;

import dsa.lab04.base.MapItem;
import dsa.lib.IntData;
import dsa.lib.Source;
import dsa.lib.StringData;

import java.util.Comparator;

public class MapItemSourceData
{

  public static class Uniques
  {

    public static class IntsToStrings
    {

      public static final Source<Source<MapItem<Integer, String>>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = MapItemData.INTS_TO_STRINGS.replace(Source::singleton),
        MULTI_ITEM =
          Source.productSourceQuadraticLimit(
              MapItemData.INTS_TO_STRINGS.group(MapItem::key))
            .replace((source, index) -> source.limit(
              1 + index % IntData.ALL.size())),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);

    }


    public static class StringsToInts
    {

      public static final Source<Source<MapItem<String, Integer>>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = MapItemData.STRINGS_TO_INTS.replace(Source::singleton),
        MULTI_ITEM =
          Source.productSourceQuadraticLimit(
              MapItemData.STRINGS_TO_INTS.group(MapItem::key))
            .replace((source, index) -> source.limit(
              1 + index % StringData.NON_NULL.size())),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);

    }


    @SuppressWarnings({"rawtypes", "RedundantSuppression"})
    public static final Source<Source<MapItem>>
      EMPTY = Source.singleton(Source.empty()),
      SINGLETON = Source.chain(
        IntsToStrings.SINGLETON.cast(),
        StringsToInts.SINGLETON.cast()),
      MULTI_ITEM = Source.chain(
        IntsToStrings.MULTI_ITEM.cast(),
        StringsToInts.MULTI_ITEM.cast()),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);

  }


  public static class SortedUniques
  {

    public static class IntsToStrings
    {

      public static final Source<Source<MapItem<Integer, String>>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = Source.sortedEach(
          Uniques.IntsToStrings.SINGLETON,
          Comparator.comparing(MapItem::key)),
        MULTI_ITEM = Source.sortedEach(
          Uniques.IntsToStrings.MULTI_ITEM,
          Comparator.comparing(MapItem::key)),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);

    }


    public static class StringsToInts
    {

      public static final Source<Source<MapItem<String, Integer>>>
        EMPTY = Source.singleton(Source.empty()),
        SINGLETON = Source.sortedEach(
          Uniques.StringsToInts.SINGLETON,
          Comparator.comparing(MapItem::key)),
        MULTI_ITEM = Source.sortedEach(
          Uniques.StringsToInts.MULTI_ITEM,
          Comparator.comparing(MapItem::key)),
        NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
        ALL = Source.chain(EMPTY, NON_EMPTY);

    }


    @SuppressWarnings({"rawtypes", "RedundantSuppression"})
    public static final Source<Source<MapItem>>
      EMPTY = Source.singleton(Source.empty()),
      SINGLETON = Source.chain(
        IntsToStrings.SINGLETON.cast(),
        StringsToInts.SINGLETON.cast()),
      MULTI_ITEM = Source.chain(
        IntsToStrings.MULTI_ITEM.cast(),
        StringsToInts.MULTI_ITEM.cast()),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);

  }

}
