package dsa.lab08.exercises;

import dsa.lab04.base.MapItem;
import dsa.lab04.base.MapTests;
import dsa.lib.ClassUtils;
import dsa.lib.DefaultDisplayNameGeneration;
import dsa.lib.Source;
import dsa.lib.TestNames;
import dsa.lib.lab04.MapItemSourceData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

@DisplayName("AVLTree")
public class AVLTreeTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(AVLTree.class);

  @DisplayName("heights")
  @DefaultDisplayNameGeneration
  interface HeightsTests
  {

    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void remainCorrectInserting(
      Source<MapItem<Key, Value>> items)
    {
      AVLTree<Key, Value> avlTree = new AVLTree<>();
      for (MapItem<Key, Value> item : items)
      {
        avlTree.insert(item);
        assertTrue(avlTree._isHeightCacheCorrect(), "after inserting " + item);
      }
    }


    //<editor-fold defaultstate="collapsed" desc="remainCorrectInserting arguments">
    static Source<Arguments> remainCorrectInserting(Class<?> avlTreeClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) -> itemss.quadratic().limit())
        .replace((items) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructor(avlTreeClass)) +
              "; " +
              String.join(
                "; ",
                items.replace((item) ->
                  "items" +
                    TestNames.method("insert", item))),
            items));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void remainCorrectRemoving(
      AVLTree<Key, Value> avlTree,
      Source<Key> keys)
    {
      for (Key key : keys)
      {
        avlTree.remove(key);
        assertTrue(avlTree._isHeightCacheCorrect(), "after removing " + key);
      }
    }


    //<editor-fold defaultstate="collapsed" desc="remainCorrectRemoving arguments">
    static Source<Arguments> remainCorrectRemoving(Class<?> avlTreeClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) -> itemss.quadratic().limit())
        .replace((items) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructor(avlTreeClass, (Object[]) items.array())) +
              "; " +
              String.join(
                "; ",
                items.replace((item) ->
                  "items" +
                    TestNames.method("remove", item.key()))),
            ClassUtils.construct(avlTreeClass, items),
            items.replace(MapItem::key)));
    }
    //</editor-fold>

  }

  @DisplayName("avl condition")
  @DefaultDisplayNameGeneration
  interface AVLConditionTests
  {

    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void remainsSatisfiedInserting(
      Source<MapItem<Key, Value>> items)
    {
      AVLTree<Key, Value> avlTree = new AVLTree<>();
      for (MapItem<Key, Value> item : items)
      {
        avlTree.insert(item);
        assertTrue(
          avlTree._isAVLConditionSatisfied(),
          "after inserting " + item);
      }
    }


    //<editor-fold defaultstate="collapsed" desc="remainsSatisfiedInserting arguments">
    static Source<Arguments> remainsSatisfiedInserting(Class<?> avlTreeClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) -> itemss.quadratic().limit())
        .replace((items) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructor(avlTreeClass)) +
              "; " +
              String.join(
                "; ",
                items.replace((item) ->
                  "items" +
                    TestNames.method("insert", item))),
            items));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void remainsSatisfiedRemoving(
      AVLTree<Key, Value> avlTree,
      Source<Key> keys)
    {
      for (Key key : keys)
      {
        avlTree.remove(key);
        assertTrue(avlTree._isAVLConditionSatisfied(), "after removing " + key);
      }
    }


    //<editor-fold defaultstate="collapsed" desc="remainsSatisfiedRemoving arguments">
    static Source<Arguments> remainsSatisfiedRemoving(Class<?> avlTreeClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) -> itemss.quadratic().limit())
        .replace((items) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructor(avlTreeClass, (Object[]) items.array())) +
              "; " +
              String.join(
                "; ",
                items.replace((item) ->
                  "items" +
                    TestNames.method("remove", item.key()))),
            ClassUtils.construct(avlTreeClass, items),
            items.replace(MapItem::key)));
    }
    //</editor-fold>

  }

  @DisplayName("bst condition")
  @DefaultDisplayNameGeneration
  interface BSTConditionTests
  {

    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void remainsSatisfiedInserting(
      Source<MapItem<Key, Value>> items)
    {
      AVLTree<Key, Value> avlTree = new AVLTree<>();
      for (MapItem<Key, Value> item : items)
      {
        avlTree.insert(item);
        MapItem<Key, Value>[] treeItems =
          Source.from(avlTree)
            .array(MapItem.class);
        MapItem<Key, Value>[] treeItemsSorted =
          Source.from(avlTree)
            .sorted(Comparator.comparing(MapItem::key))
            .array(MapItem.class);
        assertArrayEquals(
          treeItemsSorted,
          treeItems,
          "after inserting " + item);
      }
    }


    //<editor-fold defaultstate="collapsed" desc="remainsSatisfiedInserting arguments">
    static Source<Arguments> remainsSatisfiedInserting(Class<?> avlTreeClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) -> itemss.quadratic().limit())
        .replace((items) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructor(avlTreeClass)) +
              "; " +
              String.join(
                "; ",
                items.replace((item) ->
                  "items" +
                    TestNames.method("insert", item))),
            items));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void remainsSatisfiedRemoving(
      AVLTree<Key, Value> avlTree,
      Source<Key> keys)
    {
      for (Key key : keys)
      {
        avlTree.remove(key);
        MapItem<Key, Value>[] treeItems =
          Source.from(avlTree)
            .array(MapItem.class);
        MapItem<Key, Value>[] treeItemsSorted =
          Source.from(avlTree)
            .sorted(Comparator.comparing(MapItem::key))
            .array(MapItem.class);
        assertArrayEquals(
          treeItemsSorted,
          treeItems,
          "after removing " + key);
      }
    }


    //<editor-fold defaultstate="collapsed" desc="remainsSatisfiedRemoving arguments">
    static Source<Arguments> remainsSatisfiedRemoving(Class<?> avlTreeClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) -> itemss.quadratic().limit())
        .replace((items) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructor(avlTreeClass, (Object[]) items.array())) +
              "; " +
              String.join(
                "; ",
                items.replace((item) ->
                  "items" +
                    TestNames.method("remove", item.key()))),
            ClassUtils.construct(avlTreeClass, items),
            items.replace(MapItem::key)));
    }
    //</editor-fold>

  }

  @Nested
  class Find
    implements MapTests.Find
  {

  }

  @Nested
  class Insert
    implements MapTests.Insert
  {

  }

  @Nested
  class Remove
    implements MapTests.Remove
  {

  }

  @Nested
  class Heights
    implements HeightsTests
  {

  }

  @Nested
  class AVLCondition
    implements AVLConditionTests
  {

  }

  @Nested
  class BSTCondition
    implements BSTConditionTests
  {

  }

}
