package dsa.lab02.base;

import dsa.lib.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface StaticSequenceTests
{

  @DisplayName("set")
  @DefaultDisplayNameGeneration
  interface Get
  {

    @ParameterizedTest
    @MethodSource
    default <Item> void throwsIfIndexBelowBound(
      StaticSequence<Item> staticSequence,
      int index)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> staticSequence.get(index));
    }


    //<editor-fold defaultstate="collapsed" desc="throwsIfIndexBelowBound arguments">
    static Source<Arguments> throwsIfIndexBelowBound(
      Class<?> sequenceClass)
    {
      return Source.from(SourceData.Strings.ALL, SourceData.Ints.ALL)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              IntData.NEGATIVE.replace((index) -> new Object[]{
                ClassUtils.construct(sequenceClass, source),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(sequenceClass, arguments[0]),
            TestNames.method("get", arguments[1])),
          arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Item> void throwsIfIndexAboveBound(
      StaticSequence<Item> staticSequence,
      int index)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> staticSequence.get(index));
    }


    //<editor-fold defaultstate="collapsed" desc="throwsIfIndexAboveBound arguments">
    static Source<Arguments> throwsIfIndexAboveBound(
      Class<?> sequenceClass)
    {
      return Source.from(SourceData.Strings.ALL, SourceData.Ints.ALL)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              IntData.NON_NEGATIVE.replace((index) -> new Object[]{
                ClassUtils.construct(sequenceClass, source),
                MathUtils.addSaturating(source.size(), index)}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(sequenceClass, arguments[0]),
            TestNames.method("get", arguments[1])),
          arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Item> void getsCorrectIndex(
      StaticSequence<Item> staticSequence,
      int index,
      Item item)
    {
      assertEquals(item, staticSequence.get(index));
    }


    //<editor-fold defaultstate="collapsed" desc="getsCorrectIndex arguments">
    static Source<Arguments> getsCorrectIndex(
      Class<?> sequenceClass)
    {
      return Source.from(SourceData.Strings.ALL, SourceData.Ints.ALL)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              source.validIndices().replace((index) -> new Object[]{
                ClassUtils.construct(sequenceClass, source),
                index,
                source.getAt(index)}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(sequenceClass, arguments[0]),
            TestNames.method("get", arguments[1])),
          arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeItems(
      StaticSequence<Item> staticSequence,
      int index)
    {
      Item[] oldItems = Source.from(staticSequence).array();
      staticSequence.get(index);
      Item[] newItems = Source.from(staticSequence).array();
      assertArrayEquals(oldItems, newItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeSize(
      StaticSequence<Item> staticSequence,
      int index)
    {
      int oldSize = staticSequence.size();
      staticSequence.get(index);
      int newSize = staticSequence.size();
      assertEquals(oldSize, newSize);
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
                ClassUtils.construct(sequenceClass, source),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(sequenceClass, arguments[0]),
            TestNames.method("get", arguments[1])),
          arguments));
    }
    //</editor-fold>

  }

  @DisplayName("set")
  @DefaultDisplayNameGeneration
  interface Set
  {

    @ParameterizedTest
    @MethodSource
    default <Item> void throwsIfIndexBelowBound(
      StaticSequence<Item> staticSequence,
      int index,
      Item item)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> staticSequence.set(index, item));
    }


    //<editor-fold defaultstate="collapsed" desc="throwsIfIndexBelowBound arguments">
    static Source<Arguments> throwsIfIndexBelowBound(
      Class<?> sequenceClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              IntData.NEGATIVE.flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(sequenceClass, source),
                  index,
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(sequenceClass, arguments[0]),
                TestNames.method("set", arguments[1], arguments[2])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.ALL.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.ALL.cast(), IntData.ALL));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Item> void throwsIfIndexAboveBound(
      StaticSequence<Item> staticSequence,
      int index,
      Item item)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> staticSequence.set(index, item));
    }


    //<editor-fold defaultstate="collapsed" desc="throwsIfIndexAboveBound arguments">
    static Source<Arguments> throwsIfIndexAboveBound(
      Class<?> sequenceClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              IntData.NON_NEGATIVE.flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(sequenceClass, source),
                  MathUtils.addSaturating(source.size(), index),
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(sequenceClass, arguments[0]),
                TestNames.method("set", arguments[1], arguments[2])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.ALL.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.ALL.cast(), IntData.ALL));
    }
    //</editor-fold>


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void setsCorrectIndex(
      StaticSequence<Item> staticSequence,
      int index,
      Item item)
    {
      staticSequence.set(index, item);
      assertEquals(item, staticSequence.get(index));
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOthers(
      StaticSequence<Item> staticSequence,
      int index,
      Item item)
    {
      Item[] oldOtherItems =
        Source.from(staticSequence).skipIndex(index).array();
      staticSequence.set(index, item);
      Item[] newOtherItems =
        Source.from(staticSequence).skipIndex(index).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeSize(
      StaticSequence<Item> staticSequence,
      int index,
      Item item)
    {
      int oldSize = staticSequence.size();
      staticSequence.set(index, item);
      int newSize = staticSequence.size();
      assertEquals(oldSize, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> sequenceClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              source.validIndices().flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(sequenceClass, source),
                  index,
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(sequenceClass, arguments[0]),
                TestNames.method("set", arguments[1], arguments[2])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.NON_EMPTY.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.NON_EMPTY.cast(), IntData.ALL));
    }
    //</editor-fold>

  }

}
