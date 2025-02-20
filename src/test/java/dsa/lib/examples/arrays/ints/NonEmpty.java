package dsa.lib.examples.arrays.ints;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;
import static dsa.lib.Iterators.onlyEvery;

public class NonEmpty
{
  public static final Iterable<Arguments> AND_INTS =
    onlyEvery(17, arguments(Data.Arrays.Ints.NON_EMPTY, Data.Ints.ALL));
}
