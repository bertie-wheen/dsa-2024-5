package dsa.lab02.base;

import dsa.lib.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;

import static dsa.lib.ClassUtils.construct;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface DynamicSequenceTests
{
  @DisplayName("insert")
  @DefaultDisplayNameGeneration
  interface Insert
  {
    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void insertsIntoCorrectIndex(
      DynamicSequence<Item> dynamicSequence,
      int index,
      Item item)
    {
      dynamicSequence.insert(index, item);
      assertEquals(item, dynamicSequence.get(index));
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOthers(
      DynamicSequence<Item> dynamicSequence,
      int index,
      Item item)
    {
      Item[] oldOtherItems =
        Source.from(dynamicSequence).array();
      dynamicSequence.insert(index, item);
      Item[] newOtherItems =
        Source.from(dynamicSequence).skipIndex(index).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void incrementsSize(
      DynamicSequence<Item> dynamicSequence,
      int index,
      Item item)
    {
      int oldSize = dynamicSequence.size();
      dynamicSequence.insert(index, item);
      int newSize = dynamicSequence.size();
      assertEquals(oldSize + 1, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> sequenceClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              source.validInsertIndices().flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  construct(sequenceClass, source),
                  index,
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(sequenceClass, arguments[0]),
                TestNames.method("insert", arguments[1], arguments[2])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.ALL.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.ALL.cast(), IntData.ALL));
    }
    //</editor-fold>
  }

  @DisplayName("remove")
  @DefaultDisplayNameGeneration
  interface Remove
  {
    @ParameterizedTest
    @MethodSource
    default <Item> void throwsIfEmpty(
      DynamicSequence<Item> dynamicSequence,
      int index)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> dynamicSequence.remove(index));
    }

    //<editor-fold defaultstate="collapsed" desc="throwsIfEmpty arguments">
    static Source<Arguments> throwsIfEmpty(
      Class<?> sequenceClass)
    {
      return IntData.ALL.quadratic().limit()
        .replace((index) -> argumentSet(
          TestNames.format(
            TestNames.constructor(sequenceClass),
            TestNames.method("remove", index)),
          construct(sequenceClass),
          index));
    }
    //</editor-fold>

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void removesFromCorrectIndex(
      DynamicSequence<Item> dynamicSequence,
      int index)
    {
      Item item = dynamicSequence.get(index);
      assertEquals(item, dynamicSequence.remove(index));
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOthers(
      DynamicSequence<Item> dynamicSequence,
      int index)
    {
      Item[] oldOtherItems =
        Source.from(dynamicSequence).skipIndex(index).array();
      dynamicSequence.remove(index);
      Item[] newOtherItems =
        Source.from(dynamicSequence).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void decrementsSize(
      DynamicSequence<Item> dynamicSequence,
      int index)
    {
      int oldSize = dynamicSequence.size();
      dynamicSequence.remove(index);
      int newSize = dynamicSequence.size();
      assertEquals(oldSize - 1, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> sequenceClass)
    {
      return Source.from(
          SourceData.Strings.NON_EMPTY,
          SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              source.validIndices().replace((index) -> new Object[]{
                construct(sequenceClass, source),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(sequenceClass, arguments[0]),
            TestNames.method("remove", arguments[1])),
          arguments));
    }
    //</editor-fold>
  }
}
