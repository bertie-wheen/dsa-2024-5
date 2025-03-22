package dsa.lib.lab04;

import dsa.lab04.base.MapItem;
import dsa.lib.IntData;
import dsa.lib.Source;
import dsa.lib.StringData;

public class MapItemData
{

  public static final Source<MapItem<Integer, String>> INTS_TO_STRINGS =
    IntData.ALL.flatReplace((key) ->
      StringData.ALL.replace((value) ->
        new MapItem<>(key, value)));


  public static final Source<MapItem<String, Integer>> STRINGS_TO_INTS =
    StringData.NON_NULL.flatReplace((key) ->
      IntData.ALL.replace((value) ->
        new MapItem<>(key, value)));


  @SuppressWarnings("rawtypes")
  public static final Source<MapItem> ALL =
    Source.chain(INTS_TO_STRINGS.cast(), STRINGS_TO_INTS.cast());

}
