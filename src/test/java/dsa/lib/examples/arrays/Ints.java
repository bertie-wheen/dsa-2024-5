package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class Ints
{
  public static final Iterable<Arguments> AND_INTS =
    arguments(Data.Arrays.Ints.ALL, Data.Ints.ALL);

  public static final Iterable<Arguments> EMPTY =
    arguments(Data.Arrays.Ints.EMPTY);

  public static final Iterable<Arguments> SINGLETON =
    arguments(Data.Arrays.Ints.SINGLETON);

  public static final Iterable<Arguments> MULTI_ITEM =
    arguments(Data.Arrays.Ints.MULTI_ITEM);

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(Data.Arrays.Ints.NON_EMPTY);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Arrays.Ints.ALL);
}
