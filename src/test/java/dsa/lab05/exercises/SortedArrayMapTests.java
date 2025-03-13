package dsa.lab05.exercises;

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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

@DisplayName("SortedArrayMap")
public class SortedArrayMapTests
{
  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(SortedArrayMap.class);

  @DisplayName("index for")
  @DefaultDisplayNameGeneration
  interface IndexForTests
  {
    static <Key extends Comparable<Key>> int callIndexFor(
      Object sortedArrayMap,
      Key key)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
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
        sortedArrayMap.getClass().getDeclaredMethod(
          "indexFor",
          Comparable.class);
      indexForMethod.setAccessible(true);
      return (int) indexForMethod.invoke(sortedArrayMap, key);
    }

    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void returnsCorrectIndexIfContained(
      SortedArrayMap<Key, Value> map,
      dsa.lab05.solutions.SortedArrayMap<Key, Value> solution,
      Key key)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      int solutionIndex = callIndexFor(solution, key);
      int index = callIndexFor(map, key);
      assertEquals(solutionIndex, index);
    }

    //<editor-fold defaultstate="collapsed" desc="returnsCorrectIndexIfContained arguments">
    static Source<Arguments> returnsCorrectIndexIfContained(
      Class<?> sortedArrayMapClass)
    {
      return Source.from(
          MapItemSourceData.SortedUniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.SortedUniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item, index) -> new Object[]{
                ClassUtils.construct(sortedArrayMapClass, items),
                ClassUtils.construct(
                  dsa.lab05.solutions.SortedArrayMap.class,
                  items),
                item.key(),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(sortedArrayMapClass, arguments[0]),
              TestNames.method("indexFor", arguments[1])),
            arguments));
    }
    //</editor-fold>

    @DisplayName("returns -1 if not contained")
    @ParameterizedTest
    @MethodSource
    default <Key extends Comparable<Key>, Value> void returnsNegativeOneIfNotContained(
      SortedArrayMap<Key, Value> map,
      dsa.lab05.solutions.SortedArrayMap<Key, Value> solution,
      Key key)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      int solutionIndex = callIndexFor(solution, key);
      int index = callIndexFor(map, key);
      assertEquals(solutionIndex, index);
    }

    //<editor-fold defaultstate="collapsed" desc="returnsNegativeOneIfNotContained arguments">
    static Source<Arguments> returnsNegativeOneIfNotContained(
      Class<?> sortedArrayMapClass)
    {
      return Source.from(
          MapItemSourceData.SortedUniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.SortedUniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item, index) -> new Object[]{
                ClassUtils.construct(
                  sortedArrayMapClass,
                  items.skipIndex(index)),
                ClassUtils.construct(
                  dsa.lab05.solutions.SortedArrayMap.class,
                  items.skipIndex(index)),
                item.key()}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(sortedArrayMapClass, arguments[0]),
              TestNames.method("indexFor", arguments[1])),
            arguments));
    }
    //</editor-fold>
  }

  @Nested
  class IndexFor
    implements IndexForTests
  {
  }
}
