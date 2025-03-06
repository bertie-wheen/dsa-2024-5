package dsa.lab06.exercises;

import dsa.lab04.base.MapItem;
import dsa.lab05.base.MapTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

@DisplayName("ProbingHashMap")
public class ProbingHashMapTests
{
  @Nested
  @DisplayName("find")
  class Find
  {
    @ParameterizedTest
    @DisplayName("finds if contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_CONTAINED_ITEMS")
    <Key, Value> void findsIfContained(
      MapItem<Key, Value>[] items,
      MapItem<Key, Value> containedItem)
    {
      MapTests.Find.findsIfContained(
        new ProbingHashMap<>(items),
        containedItem);
    }

    @ParameterizedTest
    @DisplayName("does not find if not contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_NOT_CONTAINED_KEYS")
    <Key, Value> void doesNotFindIfNotContained(
      MapItem<Key, Value>[] items,
      Key notContainedKey)
    {
      MapTests.Find.doesNotFindIfNotContained(
        new ProbingHashMap<>(items),
        notContainedKey);
    }

    @ParameterizedTest
    @DisplayName("does not change items")
    @FieldSource("dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_KEYS")
    <Key, Value> void doesNotChangeItems(
      MapItem<Key, Value>[] items,
      Key key)
    {
      MapTests.Find.doesNotChangeItems(
        new ProbingHashMap<>(items),
        key);
    }

    @ParameterizedTest
    @DisplayName("does not change size")
    @FieldSource("dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_KEYS")
    <Key, Value> void doesNotChangeSize(
      MapItem<Key, Value>[] items,
      Key key)
    {
      MapTests.Find.doesNotChangeSize(
        new ProbingHashMap<>(items),
        key);
    }
  }

  @Nested
  @DisplayName("insert")
  class Insert
  {
    @ParameterizedTest
    @DisplayName("inserts")
    @FieldSource("dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_ITEMS")
    <Key, Value> void inserts(
      MapItem<Key, Value>[] items,
      MapItem<Key, Value> item)
    {
      MapTests.Insert.inserts(
        new ProbingHashMap<>(items),
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource("dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_ITEMS")
    <Key, Value> void doesNotChangeOthers(
      MapItem<Key, Value>[] items,
      MapItem<Key, Value> item)
    {
      MapTests.Insert.doesNotChangeOthers(
        new ProbingHashMap<>(items),
        item);
    }

    @ParameterizedTest
    @DisplayName("does not change size if contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.unique_map_items.AndContainedKeys#AND_VALUES")
    <Key, Value> void doesNotChangeSizeIfContained(
      MapItem<Key, Value>[] items,
      Key containedKey,
      Value value)
    {
      MapTests.Insert.doesNotChangeSizeIfContained(
        new ProbingHashMap<>(items),
        containedKey,
        value);
    }

    @ParameterizedTest
    @DisplayName("increments size if not contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.unique_map_items.AndNotContainedKeys#AND_VALUES")
    <Key, Value> void incrementsSizeIfNotContained(
      MapItem<Key, Value>[] items,
      Key notContainedKey,
      Value value)
    {
      MapTests.Insert.incrementsSizeIfNotContained(
        new ProbingHashMap<>(items),
        notContainedKey,
        value);
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove
  {
    @ParameterizedTest
    @DisplayName("returns item if contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_CONTAINED_ITEMS")
    <Key, Value> void returnsItemIfContained(
      MapItem<Key, Value>[] items,
      MapItem<Key, Value> containedItem)
    {
      MapTests.Remove.returnsItemIfContained(
        new ProbingHashMap<>(items),
        containedItem);
    }

    @ParameterizedTest
    @DisplayName("removes if contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_CONTAINED_KEYS")
    <Key, Value> void removesIfContained(
      MapItem<Key, Value>[] items,
      Key containedKey)
    {
      MapTests.Remove.removesIfContained(
        new ProbingHashMap<>(items),
        containedKey);
    }

    @ParameterizedTest
    @DisplayName("throws if not contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_NOT_CONTAINED_KEYS")
    <Key, Value> void throwsIfNotContained(
      MapItem<Key, Value>[] items,
      Key notContainedKey)
    {
      MapTests.Remove.throwsIfNotContained(
        new ProbingHashMap<>(items),
        notContainedKey);
    }

    @ParameterizedTest
    @DisplayName("does not change others")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_KEYS")
    <Key, Value> void doesNotChangeOthers(
      MapItem<Key, Value>[] items,
      Key key)
    {
      MapTests.Remove.doesNotChangeOthers(
        new ProbingHashMap<>(items),
        key);
    }

    @ParameterizedTest
    @DisplayName("decrements size if contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_CONTAINED_KEYS")
    <Key, Value> void decrementsSizeIfContained(
      MapItem<Key, Value>[] items,
      Key containedKey)
    {
      MapTests.Remove.decrementsSizeIfContained(
        new ProbingHashMap<>(items),
        containedKey);
    }

    @ParameterizedTest
    @DisplayName("does not change size if not contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_NOT_CONTAINED_KEYS")
    <Key, Value> void doesNotChangeSizeIfNotContained(
      MapItem<Key, Value>[] items,
      Key notContainedKey)
    {
      MapTests.Remove.doesNotChangeSizeIfNotContained(
        new ProbingHashMap<>(items),
        notContainedKey);
    }
  }
}
