package dsa.lib.examples.arrays.sorted_uniques;

import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.andValidIndices;

public class NonEmpty
{
  public static final Iterable<Arguments> AND_VALID_INDICES =
    andValidIndices(Data.Arrays.SortedUniques.NON_EMPTY);
}
