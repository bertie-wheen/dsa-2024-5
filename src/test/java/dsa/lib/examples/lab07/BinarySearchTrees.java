package dsa.lib.examples.lab07;

import dsa.lab04.base.MapItem;
import dsa.lab07.exercises.BinarySearchTree;
import dsa.lib.Data;
import org.junit.jupiter.params.provider.Arguments;

import static dsa.lib.Examples.andOnValidIndices;
import static dsa.lib.Examples.arguments;
import static dsa.lib.Iterators.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BinarySearchTrees
{
  private static Iterable<BinarySearchTree> makeEach(
    Iterable<MapItem[]> iterable)
  {
    return applyEach(iterable, (items) -> new BinarySearchTree<>(items));
  }

  public static final Iterable<Arguments> AND_CONTAINED_KEYS =
    andOnValidIndices(
      Data.Arrays.Lab04.SortedUniqueMapItems.NON_EMPTY, (array, index) ->
        Arguments.of(new BinarySearchTree(array), array[index].key()));

  public static final Iterable<Arguments> AND_NOT_CONTAINED_KEYS =
    andOnValidIndices(
      Data.Arrays.Lab04.SortedUniqueMapItems.NON_EMPTY, (array, index) ->
        Arguments.of(
          new BinarySearchTree(skipIndex(MapItem.class, index, array)),
          array[index].key()));

  public static final Iterable<Arguments> AND_KEYS =
    chain(AND_CONTAINED_KEYS, AND_NOT_CONTAINED_KEYS);

  public static final Iterable<Arguments> AND_CONTAINED_ITEMS =
    andOnValidIndices(
      Data.Arrays.Lab04.SortedUniqueMapItems.NON_EMPTY, (array, index) ->
        Arguments.of(new BinarySearchTree(array), array[index]));

  public static final Iterable<Arguments> AND_NOT_CONTAINED_ITEMS =
    andOnValidIndices(
      Data.Arrays.Lab04.SortedUniqueMapItems.NON_EMPTY, (array, index) ->
        Arguments.of(
          new BinarySearchTree(skipIndex(MapItem.class, index, array)),
          array[index]));

  public static final Iterable<Arguments> AND_ITEMS =
    chain(AND_CONTAINED_ITEMS, AND_NOT_CONTAINED_ITEMS);

  public static final Iterable<Arguments> EMPTY =
    arguments(makeEach(Data.Arrays.Lab04.SortedUniqueMapItems.EMPTY));

  public static final Iterable<Arguments> SINGLETON =
    arguments(makeEach(Data.Arrays.Lab04.SortedUniqueMapItems.SINGLETON));

  public static final Iterable<Arguments> MULTI_ITEM =
    arguments(makeEach(Data.Arrays.Lab04.SortedUniqueMapItems.MULTI_ITEM));

  public static final Iterable<Arguments> NON_EMPTY =
    arguments(makeEach(Data.Arrays.Lab04.SortedUniqueMapItems.NON_EMPTY));

  public static final Iterable<Arguments> ALL =
    arguments(makeEach(Data.Arrays.Lab04.SortedUniqueMapItems.ALL));
}
