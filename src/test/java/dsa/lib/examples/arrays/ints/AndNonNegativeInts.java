package dsa.lib.examples.arrays.ints;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class AndNonNegativeInts
{
  public static final Iterable<Arguments> AND_INTS =
    arguments(
      Data.Arrays.Ints.ALL,
      Data.Ints.NON_NEGATIVE,
      Data.Ints.ALL);
}
