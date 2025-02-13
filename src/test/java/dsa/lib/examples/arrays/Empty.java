package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class Empty
{
  public static final Iterable<Arguments> AND_INTS =
    arguments(Data.Arrays.EMPTY, Data.Ints.ALL);
}
