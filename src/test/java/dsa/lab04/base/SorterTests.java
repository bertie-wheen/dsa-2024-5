package dsa.lab04.base;

import dsa.lab02.base.StaticSequence;
import dsa.lab02.solutions.StaticArray;
import dsa.lib.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface SorterTests
{
  @DisplayName("sort")
  @DefaultDisplayNameGeneration
  interface Sort
  {
    static <Item> void sorts(
      Sorter sorter,
      StaticSequence<Item> items,
      Comparator<Item> comparator)
    {
      Item[] oldItemsSorted = Source.from(items).sorted(comparator).array();
      sorter.sort(items, comparator);
      Item[] newItems = Source.from(items).array();
      assertArrayEquals(oldItemsSorted, newItems);
    }

    @ParameterizedTest
    @MethodSource
    default <Item extends Comparable<Item>> void sortsInNaturalOrder(
      Sorter sorter,
      StaticSequence<Item> items)
    {
      sorts(sorter, items, Comparator.nullsFirst(Comparator.naturalOrder()));
    }

    //<editor-fold defaultstate="collapsed" desc="sortsInNaturalOrder arguments">
    static Source<Arguments> sortsInNaturalOrder(Class<?> sorterClass)
    {
      return Source.from(SourceData.Strings.ALL, SourceData.Ints.ALL)
        .flatReplace((sources) -> sources.quadratic().limit())
        .replace((source) ->
          new StaticArray<Comparable<?>>(source.cast(), source.size()))
        .replace((sequence) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructorFor(StaticArray.class, sequence)) +
              "; " +
              TestNames.format(
                TestNames.constructor(sorterClass),
                TestNames.method("sort", new To.PassthroughString("items"))),
            ClassUtils.construct(sorterClass),
            sequence));
    }
    //</editor-fold>

    @ParameterizedTest
    @MethodSource
    default <Item extends Comparable<Item>> void sortsInReverseOrder(
      Sorter sorter,
      StaticSequence<Item> items)
    {
      sorts(sorter, items, Comparator.nullsLast(Comparator.reverseOrder()));
    }

    //<editor-fold defaultstate="collapsed" desc="sortsInReverseOrder arguments">
    static Source<Arguments> sortsInReverseOrder(Class<?> sorterClass)
    {
      return Source.from(SourceData.Strings.ALL, SourceData.Ints.ALL)
        .flatReplace((sources) -> sources.quadratic().limit())
        .replace((source) ->
          new StaticArray<Comparable<?>>(source.cast(), source.size()))
        .replace((sequence) ->
          argumentSet(
            "items = " +
              TestNames.format(
                TestNames.constructorFor(StaticArray.class, sequence)) +
              "; " +
              TestNames.format(
                TestNames.constructor(sorterClass),
                TestNames.method("sort", new To.PassthroughString("items"))),
            ClassUtils.construct(sorterClass),
            sequence));
    }
    //</editor-fold>
  }
}
