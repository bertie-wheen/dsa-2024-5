package dsa.lib.examples;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class Ints
{
  public static final Iterable<Arguments> ZERO =
    arguments(Data.Ints.ZERO);

  public static final Iterable<Arguments> POSITIVE =
    arguments(Data.Ints.POSITIVE);

  public static final Iterable<Arguments> NEGATIVE =
    arguments(Data.Ints.NEGATIVE);

  public static final Iterable<Arguments> NON_ZERO =
    arguments(Data.Ints.NON_ZERO);

  public static final Iterable<Arguments> NON_NEGATIVE =
    arguments(Data.Ints.NON_NEGATIVE);

  public static final Iterable<Arguments> NON_POSITIVE =
    arguments(Data.Ints.NON_POSITIVE);

  public static final Iterable<Arguments> ALL =
    arguments(Data.Ints.ALL);
}
