package dsa.lib.lab04;

import dsa.lab04.base.MapItem;
import dsa.lib.IntData;
import dsa.lib.Source;
import dsa.lib.StringData;

public class MapItemData
{
  public static final Source<MapItem<Integer, String>> INTS_TO_STRINGS =
    IntData.ALL.flatReplace((i) ->
      StringData.ALL.replace((s) ->
        new MapItem<>(i, s)));

  public static final Source<MapItem<String, Integer>> STRINGS_TO_INTS =
    StringData.NON_NULL.flatReplace((s) ->
      IntData.ALL.replace((i) ->
        new MapItem<>(s, i)));

  @SuppressWarnings("rawtypes")
  public static final Source<MapItem> ALL =
    Source.chain(INTS_TO_STRINGS.cast(), STRINGS_TO_INTS.cast());
}
