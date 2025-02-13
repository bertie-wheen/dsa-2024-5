package dsa.lib.examples.arrays;

import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Iterators.chain;

public class AndNegativeInts
{
  public static final Iterable<Arguments> AND_ITEMS =
    chain(
      dsa.lib.examples.arrays.ints.AndNegativeInts.AND_INTS,
      dsa.lib.examples.arrays.strings.AndNegativeInts.AND_STRINGS);
}
