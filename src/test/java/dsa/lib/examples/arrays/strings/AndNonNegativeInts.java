package dsa.lib.examples.arrays.strings;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;
import static dsa.lib.Iterators.onlyEvery;

public class AndNonNegativeInts
{
  public static final Iterable<Arguments> AND_STRINGS =
    onlyEvery(
      17,
      arguments(
        Data.Arrays.Strings.ALL,
        Data.Ints.NON_NEGATIVE,
        Data.Strings.ALL));
}
