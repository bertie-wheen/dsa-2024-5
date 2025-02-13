package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class Strings
{
  public static final Iterable<Arguments> AND_STRINGS =
    arguments(Data.Arrays.Strings.ALL, Data.Strings.ALL);

  public static final Iterable<Arguments> EMPTY =
    arguments(Data.Arrays.Strings.EMPTY);

  public static final Iterable<Arguments> SINGLETON =
    arguments(Data.Arrays.Strings.SINGLETON);

  public static final Iterable<Arguments> MULTI_ITEM =
    arguments(Data.Arrays.Strings.MULTI_ITEM);

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(Data.Arrays.Strings.NON_EMPTY);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Arrays.Strings.ALL);
}
