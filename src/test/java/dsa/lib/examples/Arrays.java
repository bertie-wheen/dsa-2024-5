package dsa.lib.examples;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.andValidIndices;
import static dsa.lib.Examples.arguments;
import static dsa.lib.Iterators.*;

public class Arrays
{
  public static final Iterable<Arguments> AND_ITEMS =
    chain(
      dsa.lib.examples.arrays.Ints.AND_INTS,
      dsa.lib.examples.arrays.Strings.AND_STRINGS);

  public static final Iterable<Arguments> AND_VALID_INDICES =
    andValidIndices(Data.Arrays.ALL);

  public static final Iterable<Arguments> AND_NON_NEGATIVE_INTS =
    arguments(Data.Arrays.ALL, Data.Ints.NON_NEGATIVE);

  public static final Iterable<Arguments> AND_NEGATIVE_INTS =
    arguments(Data.Arrays.ALL, Data.Ints.NEGATIVE);

  public static final Iterable<Arguments> EMPTY =
    arguments(Data.Arrays.EMPTY);

  public static final Iterable<Arguments> SINGLETON =
    arguments(Data.Arrays.SINGLETON);

  public static final Iterable<Arguments> MULTI_ITEM =
    arguments(Data.Arrays.MULTI_ITEM);

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(Data.Arrays.NON_EMPTY);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Arrays.ALL);
}
