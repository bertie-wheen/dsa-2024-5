package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;
import static dsa.lib.Iterators.chain;

public class NonEmpty
{
  public static final Iterable<Arguments> AND_NON_NEGATIVE_INTS =
    arguments(Data.Arrays.NON_EMPTY, Data.Ints.NON_NEGATIVE);

  public static final Iterable<Arguments> AND_ITEMS =
    chain(
      dsa.lib.examples.arrays.ints.NonEmpty.AND_INTS,
      dsa.lib.examples.arrays.strings.NonEmpty.AND_STRINGS);
}
