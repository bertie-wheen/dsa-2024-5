package dsa.lib.examples.arrays;

import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Iterators.chain;

public class AndNonNegativeInts
{
  public static final Iterable<Arguments> AND_ITEMS =
    chain(
      dsa.lib.examples.arrays.ints.AndNonNegativeInts.AND_INTS,
      dsa.lib.examples.arrays.strings.AndNonNegativeInts.AND_STRINGS);
}
