package dsa.lib.examples.arrays.lab04;

import dsa.lab04.base.MapItem;
import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.andContainedItems;
import static dsa.lib.Examples.andOnValidIndices;
import static dsa.lib.Iterators.*;

public class UniqueMapItems
{
  public static final Iterable<Arguments> AND_CONTAINED_KEYS =
    onlyEvery(
      3,
      andOnValidIndices(
        Data.Arrays.Lab04.UniqueMapItems.NON_EMPTY, (array, index) ->
          Arguments.of(array, array[index].key())));

  public static final Iterable<Arguments> AND_NOT_CONTAINED_KEYS =
    onlyEvery(
      3,
      andOnValidIndices(
        Data.Arrays.Lab04.UniqueMapItems.NON_EMPTY,
        (array, index) -> Arguments.of(
          skipIndex(MapItem.class, index, array),
          array[index].key())));

  public static final Iterable<Arguments> AND_KEYS =
    onlyEvery(3, chain(AND_CONTAINED_KEYS, AND_NOT_CONTAINED_KEYS));

  public static final Iterable<Arguments> AND_CONTAINED_ITEMS =
    onlyEvery(3, andContainedItems(Data.Arrays.Lab04.UniqueMapItems.NON_EMPTY));

  public static final Iterable<Arguments> AND_NOT_CONTAINED_ITEMS =
    onlyEvery(
      3,
      andOnValidIndices(
        Data.Arrays.Lab04.UniqueMapItems.NON_EMPTY,
        (array, index) -> Arguments.of(
          skipIndex(MapItem.class, index, array),
          array[index])));

  public static final Iterable<Arguments> AND_ITEMS =
    onlyEvery(3, chain(AND_CONTAINED_ITEMS, AND_NOT_CONTAINED_ITEMS));
}
