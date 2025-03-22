package dsa.lab04.exercises;

import dsa.lab02.base.StaticSequence;
import dsa.lab02.solutions.StaticArray;
import dsa.lib.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

@DisplayName("BinarySearcher")
public class BinarySearcherTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(BinarySearcher.class);

  @DisplayName("search")
  @DefaultDisplayNameGeneration
  interface SearchTests
  {

    @ParameterizedTest
    @MethodSource
    default <Item extends Comparable<Item>> void returnsCorrectIndexIfContained(
      StaticSequence<Item> sequence,
      Item item)
    {
      int index = new BinarySearcher().search(sequence, item);
      assertNotEquals(-1, index);
      assertEquals(item, sequence.get(index));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsCorrectIndexIfContained arguments">
    static Source<Arguments> returnsCorrectIndexIfContained(
      Class<?> binarySearcherClass)
    {
      return Source.from(
          SourceData.Sorted.Ints.NON_EMPTY,
          SourceData.Sorted.Strings.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item) -> new Object[]{
                new StaticArray<>(items, items.size()),
                item}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructorFor(StaticArray.class, arguments[0])) +
              "; " +
              TestNames.format(
                TestNames.constructor(binarySearcherClass),
                TestNames.method(
                  "search",
                  new To.PassthroughString("items"),
                  arguments[1])),
            arguments));
    }
    //</editor-fold>


    @DisplayName("returns -1 if not contained")
    @ParameterizedTest
    @MethodSource
    default <Item extends Comparable<Item>> void returnsNegativeOneIfNotContained(
      StaticSequence<Item> sequence,
      Item item)
    {
      assertEquals(-1, new BinarySearcher().search(sequence, item));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsNegativeOneIfNotContained arguments">
    static Source<Arguments> returnsNegativeOneIfNotContained(Class<?> binarySearcherClass)
    {
      return Source.from(
          SourceData.Sorted.Ints.NON_EMPTY,
          SourceData.Sorted.Strings.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item) -> new Object[]{
                new StaticArray<>(items.filter((i) -> !Objects.equals(i, item))),
                item}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructorFor(StaticArray.class, arguments[0])) +
              "; " +
              TestNames.format(
                TestNames.constructor(binarySearcherClass),
                TestNames.method(
                  "search",
                  new To.PassthroughString("items"),
                  arguments[1])),
            arguments));
    }
    //</editor-fold>

  }

  @Nested
  class Search
    implements SearchTests
  {

  }

}
