package dsa.lib.examples.arrays.uniques;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.*;
import static dsa.lib.Iterators.skipIndex;

public class NonEmpty
{
  public static final Iterable<Arguments> AND_VALID_INDICES =
    andValidIndices(Data.Arrays.Uniques.NON_EMPTY);

  public static final Iterable<Arguments> AND_CONTAINED_ITEMS =
    andContainedItems(Data.Arrays.Uniques.NON_EMPTY);

  public static final Iterable<Arguments> AND_NOT_CONTAINED_ITEMS =
    andOnValidIndices(
      Data.Arrays.Uniques.MULTI_ITEM, (array, index) ->
        Arguments.of(skipIndex(index, array), array[index]));
}
