package dsa.lab04.exercises;

import dsa.lab04.base.SorterTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("MergeSorter")
public class MergeSorterTests
{
  @ParameterizedTest
  @DisplayName("sorts in natural order")
  @FieldSource("dsa.lib.examples.arrays.Comparables#ALL")
  <Item extends Comparable<Item>> void sortsInNaturalOrder(Item[] items)
  {
    SorterTests.sortsInNaturalOrder(new MergeSorter(), items);
  }

  @ParameterizedTest
  @DisplayName("sorts in reverse order")
  @FieldSource("dsa.lib.examples.arrays.Comparables#ALL")
  <Item extends Comparable<Item>> void sortsInReverseOrder(Item[] items)
  {
    SorterTests.sortsInReverseOrder(new MergeSorter(), items);
  }
}
