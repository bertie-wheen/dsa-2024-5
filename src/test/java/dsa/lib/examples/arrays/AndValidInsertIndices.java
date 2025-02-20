package dsa.lib.examples.arrays;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.*;
import static dsa.lib.Iterators.*;

public class AndValidInsertIndices
{
  public static final Iterable<Arguments> AND_ITEMS = onlyEvery(
    7,
    chain(
      flatProduct(
        andValidInsertIndices(Data.Arrays.Ints.ALL),
        applyEach(Data.Ints.ALL, (i) -> Arguments.of(i))),
      flatProduct(
        andValidInsertIndices(Data.Arrays.Strings.ALL),
        applyEach(Data.Strings.ALL, (s) -> Arguments.of(s)))));
}
