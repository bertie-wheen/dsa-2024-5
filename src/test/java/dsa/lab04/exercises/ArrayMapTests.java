package dsa.lab04.exercises;

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

@DisplayName("ArrayMap")
public class ArrayMapTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(ArrayMap.class);

  @DisplayName("index of")
  @DefaultDisplayNameGeneration
  interface IndexOfTests
  {

    static <Key, Value> int callIndexOf(ArrayMap<Key, Value> map, Key key)
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
      Method indexOfMethod = ArrayMap.class.getDeclaredMethod(
        "indexOf",
        Object.class);
      indexOfMethod.setAccessible(true);
      return (int) indexOfMethod.invoke(map, key);
    }


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void returnsCorrectIndexIfContained(
      ArrayMap<Key, Value> map,
      Key key,
      int index)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      assertEquals(index, callIndexOf(map, key));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsCorrectIndexIfContained arguments">
    static Source<Arguments> returnsCorrectIndexIfContained(
      Class<?> arrayMapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item, index) -> new Object[]{
                ClassUtils.construct(arrayMapClass, items),
                item.key(),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(arrayMapClass, arguments[0]),
              TestNames.method("indexOf", arguments[1])),
            arguments));
    }
    //</editor-fold>


    @DisplayName("returns -1 if not contained")
    @ParameterizedTest
    @MethodSource
    default <Key, Value> void returnsNegativeOneIfNotContained(
      ArrayMap<Key, Value> map,
      Key key)
      throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
    {
      assertEquals(-1, callIndexOf(map, key));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsNegativeOneIfNotContained arguments">
    static Source<Arguments> returnsNegativeOneIfNotContained(
      Class<?> arrayMapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item, index) -> new Object[]{
                ClassUtils.construct(arrayMapClass, items.skipIndex(index)),
                item.key()}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(arrayMapClass, arguments[0]),
              TestNames.method("indexOf", arguments[1])),
            arguments));
    }
    //</editor-fold>

  }

  @Nested
  class IndexOf
    implements IndexOfTests
  {

  }

}
