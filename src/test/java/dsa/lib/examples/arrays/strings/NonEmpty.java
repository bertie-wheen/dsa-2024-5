package dsa.lib.examples.arrays.strings;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class NonEmpty
{
  public static final Iterable<Arguments> AND_STRINGS =
    arguments(Data.Arrays.Strings.NON_EMPTY, Data.Strings.ALL);
}
