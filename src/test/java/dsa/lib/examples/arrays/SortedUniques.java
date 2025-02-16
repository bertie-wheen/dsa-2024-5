package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.*;
import static dsa.lib.Iterators.skipIndex;

public class SortedUniques
{
  public static final Iterable<Arguments> EMPTY =
    arguments(Data.Arrays.SortedUniques.EMPTY);

  public static final Iterable<Arguments> SINGLETON =
    arguments(Data.Arrays.SortedUniques.SINGLETON);

  public static final Iterable<Arguments> MULTI_ITEM =
    arguments(Data.Arrays.SortedUniques.MULTI_ITEM);

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(Data.Arrays.SortedUniques.NON_EMPTY);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Arrays.SortedUniques.ALL);

  public static final Iterable<Arguments> AND_VALID_INDICES =
    andValidIndices(Data.Arrays.SortedUniques.NON_EMPTY);

  public static final Iterable<Arguments> AND_CONTAINED_ITEMS =
    andOnValidIndices(
      Data.Arrays.SortedUniques.NON_EMPTY, (array, index) ->
        Arguments.of(array, array[index]));

  public static final Iterable<Arguments> AND_NOT_CONTAINED_ITEMS =
    andOnValidIndices(
      Data.Arrays.SortedUniques.NON_EMPTY,
      (array, index) -> Arguments.of(
        skipIndex(Comparable.class, index, array),
        array[index]));
}
