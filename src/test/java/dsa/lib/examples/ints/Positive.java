package dsa.lib.examples.ints;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.arguments;

public class Positive
{
  public static final Iterable<Arguments> AND_OBJECTS =
    arguments(Data.Ints.POSITIVE, Data.Objects.ALL);
}
