package dsa.lab04.exercises;

import dsa.lab04.base.MapItem;
import dsa.lib.Iterators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayMapTests
{
  @Nested
  @DisplayName("index of")
  class IndexOf
  {
    @ParameterizedTest
    @DisplayName("returns correct index if contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_CONTAINED_KEYS")
    <Key, Value> void returnsCorrectIndexIfContained(
      MapItem<Key, Value>[] items,
      Key containedKey)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      ArrayMap<Key, Value> map = new ArrayMap<>(items);
      // NOTE: The following line is _supposed_ to be incomprehensible, and is
      //       not at all the way you should implement `indexOf` - we do it like
      //       this here to avoid giving the game away re how you implement it.
      int index = Iterators.asList(items).stream().map(MapItem::key)
        .collect(Collectors.toList()).indexOf(containedKey);
      // NOTE: You shouldn't ever really use the following hack to access
      //       private members. Normally one doesn't test private members - all
      //       one cares about usually is that a class implements its public
      //       members correctly, and doesn't care _how_ it does that (e.g.
      //       whether it uses a private helper method like `indexOf` or not).
      //       In this case, though, `indexOf` is the core of this exercise,
      //       so we want to test it directly, rather than indirectly through
      //       e.g. testing `find`, `insert` and `remove`, which are
      //       implemented in terms it.
      Method indexOfMethod = ArrayMap.class.getDeclaredMethod(
        "indexOf",
        Object.class);
      indexOfMethod.setAccessible(true);
      assertEquals(index, indexOfMethod.invoke(map, containedKey));
    }

    @ParameterizedTest
    @DisplayName("returns -1 if not contained")
    @FieldSource(
      "dsa.lib.examples.arrays.lab04.UniqueMapItems#AND_NOT_CONTAINED_KEYS")
    <Key, Value> void returnsNegativeOneIfNotContained(
      MapItem<Key, Value>[] items,
      Key notContainedKey)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      ArrayMap<Key, Value> map = new ArrayMap<>(items);
      // NOTE: You shouldn't ever really use the following hack to access
      //       private members. Normally one doesn't test private members - all
      //       one cares about usually is that a class implements its public
      //       members correctly, and doesn't care _how_ it does that (e.g.
      //       whether it uses a private helper method like `indexOf` or not).
      //       In this case, though, `indexOf` is the core of this exercise,
      //       so we want to test it directly, rather than indirectly through
      //       e.g. testing `find`, `insert` and `remove`, which are
      //       implemented in terms it.
      Method indexOfMethod = ArrayMap.class.getDeclaredMethod(
        "indexOf",
        Object.class);
      indexOfMethod.setAccessible(true);
      assertEquals(-1, indexOfMethod.invoke(map, notContainedKey));
    }
  }
}
