package dsa.lab07.exercises;

import dsa.lab04.base.MapItem;
import dsa.lab05.base.MapTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("BinarySearchTree")
public class BinarySearchTreeTests
{
  @Nested
  @DisplayName("find")
  class Find
  {
    @ParameterizedTest
    @DisplayName("finds if contained")
    @FieldSource("dsa.lib.examples.lab07.BinarySearchTrees#AND_CONTAINED_ITEMS")
    <Key extends Comparable<Key>, Value> void findsIfContained(
      BinarySearchTree<Key, Value> bst,
      MapItem<Key, Value> containedItem)
    {
      MapTests.Find.findsIfContained(bst, containedItem);
    }

    @ParameterizedTest
    @DisplayName("does not find if not contained")
    @FieldSource(
      "dsa.lib.examples.lab07.BinarySearchTrees#AND_NOT_CONTAINED_KEYS")
    <Key extends Comparable<Key>, Value> void doesNotFindIfNotContained(
      BinarySearchTree<Key, Value> bst,
      Key notContainedKey)
    {
      MapTests.Find.doesNotFindIfNotContained(bst, notContainedKey);
    }

    @ParameterizedTest
    @DisplayName("does not change items")
    @FieldSource("dsa.lib.examples.lab07.BinarySearchTrees#AND_KEYS")
    <Key extends Comparable<Key>, Value> void doesNotChangeItems(
      BinarySearchTree<Key, Value> bst,
      Key key)
    {
      MapTests.Find.doesNotChangeItems(bst, key);
    }

    @ParameterizedTest
    @DisplayName("does not change size")
    @FieldSource("dsa.lib.examples.lab07.BinarySearchTrees#AND_KEYS")
    <Key extends Comparable<Key>, Value> void doesNotChangeSize(
      BinarySearchTree<Key, Value> bst,
      Key key)
    {
      MapTests.Find.doesNotChangeSize(bst, key);
    }
  }

  @Nested
  @DisplayName("insert")
  class Insert
  {
    @ParameterizedTest
    @DisplayName("inserts")
    @FieldSource("dsa.lib.examples.lab07.BinarySearchTrees#AND_ITEMS")
    <Key extends Comparable<Key>, Value> void inserts(
      BinarySearchTree<Key, Value> bst,
      MapItem<Key, Value> item)
    {
      MapTests.Insert.inserts(bst, item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.lab07.BinarySearchTrees#AND_ITEMS")
    <Key extends Comparable<Key>, Value> void doesNotChangeOthers(
      BinarySearchTree<Key, Value> bst,
      MapItem<Key, Value> item)
    {
      MapTests.Insert.doesNotChangeOthers(bst, item);
    }

    @ParameterizedTest
    @DisplayName("does not change size if contained")
    @FieldSource(
      "dsa.lib.examples.lab07.binary_search_trees.AndContainedKeys#AND_VALUES")
    <Key extends Comparable<Key>, Value> void doesNotChangeSizeIfContained(
      BinarySearchTree<Key, Value> bst,
      Key containedKey,
      Value value)
    {
      MapTests.Insert.doesNotChangeSizeIfContained(bst, containedKey, value);
    }

    @ParameterizedTest
    @DisplayName("increments size if not contained")
    @FieldSource(
      "dsa.lib.examples.lab07.binary_search_trees.AndNotContainedKeys#AND_VALUES")
    <Key extends Comparable<Key>, Value> void incrementsSizeIfNotContained(
      BinarySearchTree<Key, Value> bst,
      Key notContainedKey,
      Value value)
    {
      MapTests.Insert.incrementsSizeIfNotContained(bst, notContainedKey, value);
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove
  {
    @ParameterizedTest
    @DisplayName("returns item if contained")
    @FieldSource(
      "dsa.lib.examples.lab07.BinarySearchTrees#AND_CONTAINED_ITEMS")
    <Key extends Comparable<Key>, Value> void returnsItemIfContained(
      BinarySearchTree<Key, Value> bst,
      MapItem<Key, Value> containedItem)
    {
      MapTests.Remove.returnsItemIfContained(bst, containedItem);
    }

    @ParameterizedTest
    @DisplayName("removes if contained")
    @FieldSource(
      "dsa.lib.examples.lab07.BinarySearchTrees#AND_CONTAINED_KEYS")
    <Key extends Comparable<Key>, Value> void removesIfContained(
      BinarySearchTree<Key, Value> bst,
      Key containedKey)
    {
      MapTests.Remove.removesIfContained(bst, containedKey);
    }

    @ParameterizedTest
    @DisplayName("throws if not contained")
    @FieldSource(
      "dsa.lib.examples.lab07.BinarySearchTrees#AND_NOT_CONTAINED_KEYS")
    <Key extends Comparable<Key>, Value> void throwsIfNotContained(
      BinarySearchTree<Key, Value> bst,
      Key notContainedKey)
    {
      MapTests.Remove.throwsIfNotContained(bst, notContainedKey);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource(
      "dsa.lib.examples.lab07.BinarySearchTrees#AND_KEYS")
    <Key extends Comparable<Key>, Value> void doesNotChangeOthers(
      BinarySearchTree<Key, Value> bst,
      Key key)
    {
      MapTests.Remove.doesNotChangeOthers(bst, key);
    }

    @ParameterizedTest
    @DisplayName("decrements size if contained")
    @FieldSource(
      "dsa.lib.examples.lab07.BinarySearchTrees#AND_CONTAINED_KEYS")
    <Key extends Comparable<Key>, Value> void decrementsSizeIfContained(
      BinarySearchTree<Key, Value> bst,
      Key containedKey)
    {
      MapTests.Remove.decrementsSizeIfContained(bst, containedKey);
    }

    @ParameterizedTest
    @DisplayName("does not change size if not contained")
    @FieldSource(
      "dsa.lib.examples.lab07.BinarySearchTrees#AND_NOT_CONTAINED_KEYS")
    <Key extends Comparable<Key>, Value> void doesNotChangeSizeIfNotContained(
      BinarySearchTree<Key, Value> bst,
      Key notContainedKey)
    {
      MapTests.Remove.doesNotChangeSizeIfNotContained(bst, notContainedKey);
    }
  }
}
