package dsa.lib.examples.arrays.strings;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class AndNegativeInts
{
  public static final Iterable<Arguments> AND_STRINGS =
    arguments(
      Data.Arrays.Strings.ALL,
      Data.Ints.NEGATIVE,
      Data.Strings.ALL);
}
