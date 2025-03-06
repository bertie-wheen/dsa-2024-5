package dsa.lib.examples.lab07.binary_search_trees;

import dsa.lab07.exercises.BinarySearchTree;
import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.andOnValidIndices;
import static dsa.lib.Iterators.*;

public class AndContainedKeys
{
  public static final Iterable<Arguments> AND_VALUES = onlyEvery(
    7,
    chain(
      flatten(
        andOnValidIndices(
          Data.Arrays.Lab04.SortedUniqueMapItems.IntsToStrings.NON_EMPTY,
          (array, index) ->
            applyEach(
              Data.Strings.ALL,
              (value) -> Arguments.of(
                new BinarySearchTree<>(array),
                array[index].key(),
                value)))),
      flatten(
        andOnValidIndices(
          Data.Arrays.Lab04.SortedUniqueMapItems.StringsToInts.NON_EMPTY,
          (array, index) ->
            applyEach(
              Data.Ints.ALL,
              (value) -> Arguments.of(
                new BinarySearchTree<>(array),
                array[index].key(),
                value))))));
}
