package dsa.lib.examples.arrays.uniques;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.*;
import static dsa.lib.Iterators.onlyEvery;
import static dsa.lib.Iterators.skipIndex;

public class NonEmpty
{
  public static final Iterable<Arguments> AND_VALID_INDICES =
    onlyEvery(3, andValidIndices(Data.Arrays.Uniques.NON_EMPTY));

  public static final Iterable<Arguments> AND_CONTAINED_ITEMS =
    onlyEvery(3, andContainedItems(Data.Arrays.Uniques.NON_EMPTY));

  public static final Iterable<Arguments> AND_NOT_CONTAINED_ITEMS =
    onlyEvery(
      3,
      andOnValidIndices(
        Data.Arrays.Uniques.MULTI_ITEM, (array, index) ->
          Arguments.of(skipIndex(index, array), array[index])));
}
