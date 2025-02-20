package dsa.lib.examples.arrays.comparables;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;
import static dsa.lib.Iterators.onlyEvery;

public class NonEmpty
{
  public static final Iterable<Arguments> AND_NON_NEGATIVE_INTS =
    onlyEvery(
      17,
      arguments(Data.Arrays.Comparables.NON_EMPTY, Data.Ints.NON_NEGATIVE));
}
