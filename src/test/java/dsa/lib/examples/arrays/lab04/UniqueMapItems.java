package dsa.lib.examples.arrays.lab04;

import dsa.lab04.base.MapItem;
import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.andOnValidIndices;
import static dsa.lib.Iterators.skipIndex;

public class UniqueMapItems
{
  public static final Iterable<Arguments> AND_CONTAINED_KEYS =
    andOnValidIndices(
      Data.Arrays.Lab04.UniqueMapItems.NON_EMPTY, (array, index) ->
        Arguments.of(array, array[index].key()));

  public static final Iterable<Arguments> AND_NOT_CONTAINED_KEYS =
    andOnValidIndices(
      Data.Arrays.Lab04.UniqueMapItems.NON_EMPTY,
      (array, index) -> Arguments.of(
        skipIndex(MapItem.class, index, array),
        array[index].key()));
}
