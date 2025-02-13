package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class SortedUniques
{
  public static final Iterable<Arguments> EMPTY =
    arguments(Data.Arrays.SortedUniques.EMPTY);

  public static final Iterable<Arguments> SINGLETON =
    arguments(Data.Arrays.SortedUniques.SINGLETON);

  public static final Iterable<Arguments> MULTI_ITEM =
    arguments(Data.Arrays.SortedUniques.MULTI_ITEM);

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(Data.Arrays.SortedUniques.NON_EMPTY);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Arrays.SortedUniques.ALL);
}
