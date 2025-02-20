package dsa.lab05.exercises;

import dsa.lab04.base.MapItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SortedArrayMap")
public class SortedArrayMapTests
{
  @Nested
  @DisplayName("index for")
  class IndexFor
  {
    private <Key extends Comparable<Key>, Value> int correctIndex(
      MapItem<Key, Value>[] items,
      Key key)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      dsa.lab05.solutions.SortedArrayMap<Key, Value> map =
        new dsa.lab05.solutions.SortedArrayMap<>(items);
      Method indexForMethod =
        dsa.lab05.solutions.SortedArrayMap.class.getDeclaredMethod(
          "indexFor",
          Comparable.class);
      indexForMethod.setAccessible(true);
      return (int) indexForMethod.invoke(map, key);
    }

    private <Key extends Comparable<Key>, Value> int actualIndex(
      MapItem<Key, Value>[] items,
      Key key)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      SortedArrayMap<Key, Value> map = new SortedArrayMap<>(items);
      // NOTE: You shouldn't ever really use the following hack to access
      //       private members. Normally one doesn't test private members - all
      //       one cares about usually is that a class implements its public
      //       members correctly, and doesn't care _how_ it does that (e.g.
      //       whether it uses a private helper method like `indexOf` or not).
      //       In this case, though, `indexOf` is the core of this exercise,
      //       so we want to test it directly, rather than indirectly through
      //       e.g. testing `find`, `insert` and `remove`, which are
      //       implemented in terms it.
      Method indexForMethod =
        SortedArrayMap.class.getDeclaredMethod("indexFor", Comparable.class);
      indexForMethod.setAccessible(true);
      return (int) indexForMethod.invoke(map, key);
    }

    @ParameterizedTest
    @DisplayName("if contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.SortedUniqueMapItems#AND_CONTAINED_KEYS")
    <Key extends Comparable<Key>, Value> void returnsCorrectIndexIfContained(
      MapItem<Key, Value>[] items,
      Key containedKey)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      assertEquals(
        this.correctIndex(items, containedKey),
        this.actualIndex(items, containedKey));
    }

    @ParameterizedTest
    @DisplayName("if not contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.SortedUniqueMapItems#AND_NOT_CONTAINED_KEYS")
    <Key extends Comparable<Key>, Value> void returnsNegativeOneIfNotContained(
      MapItem<Key, Value>[] items,
      Key notContainedKey)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      assertEquals(
        this.correctIndex(items, notContainedKey),
        this.actualIndex(items, notContainedKey));
    }
  }
}
