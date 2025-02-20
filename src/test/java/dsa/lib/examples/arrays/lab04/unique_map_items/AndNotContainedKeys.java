package dsa.lib.examples.arrays.lab04.unique_map_items;

import dsa.lab04.base.MapItem;
import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.*;
import static dsa.lib.Iterators.*;

public class AndNotContainedKeys
{
  public static final Iterable<Arguments> AND_VALUES = onlyEvery(
    7,
    chain(
      flatProduct(
        andOnValidIndices(
          Data.Arrays.Lab04.UniqueMapItems.IntsToStrings.NON_EMPTY,
          (array, index) ->
            Arguments.of(
              skipIndex(MapItem.class, index, array),
              array[index].key())),
        applyEach(Data.Strings.ALL, (i) -> Arguments.of(i))),
      flatProduct(
        andOnValidIndices(
          Data.Arrays.Lab04.UniqueMapItems.StringsToInts.NON_EMPTY,
          (array, index) ->
            Arguments.of(
              skipIndex(MapItem.class, index, array),
              array[index].key())),
        applyEach(Data.Ints.ALL, (i) -> Arguments.of(i)))));
}
