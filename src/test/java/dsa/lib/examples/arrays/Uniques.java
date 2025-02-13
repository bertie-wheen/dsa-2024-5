package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class Uniques
{
  public static final Iterable<Arguments> EMPTY =
    arguments(Data.Arrays.Uniques.EMPTY);

  public static final Iterable<Arguments> SINGLETON =
    arguments(Data.Arrays.Uniques.SINGLETON);

  public static final Iterable<Arguments> MULTI_ITEM =
    arguments(Data.Arrays.Uniques.MULTI_ITEM);

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(Data.Arrays.Uniques.NON_EMPTY);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Arrays.Uniques.ALL);
}
