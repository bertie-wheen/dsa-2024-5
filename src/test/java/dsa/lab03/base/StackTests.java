package dsa.lab03.base;

import dsa.lib.*;
import dsa.lib.IntData;
import dsa.lib.SourceData;
import dsa.lib.StringData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface StackTests
{
  @DisplayName("push")
  @DefaultDisplayNameGeneration
  interface Push
  {
    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void pushesAsTop(
      Stack<Item> stack,
      Item item)
    {
      stack.push(item);
      assertEquals(item, stack.top());
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOthers(
      Stack<Item> stack,
      Item item)
    {
      Item[] oldOtherItems = Source.from(stack).array();
      stack.push(item);
      Item[] newOtherItems = Source.from(stack).skipFirst().array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void incrementsSize(
      Stack<Item> stack,
      Item item)
    {
      int oldSize = stack.size();
      stack.push(item);
      int newSize = stack.size();
      assertEquals(oldSize + 1, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> stackClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              source.validIndices().flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(stackClass, source),
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(stackClass, arguments[0]),
                TestNames.method("push", arguments[1])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.ALL.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.ALL.cast(), IntData.ALL));
    }
    //</editor-fold>
  }

  @DisplayName("top")
  @DefaultDisplayNameGeneration
  interface Top
  {
    @ParameterizedTest
    @MethodSource
    default <Item> void returnsTop(
      Stack<Item> stack,
      Item topItem)
    {
      assertEquals(topItem, stack.top());
    }

    //<editor-fold defaultstate="collapsed" desc="returnsTop arguments">
    static Source<Arguments> returnsTop(Class<?> stackClass)
    {
      return Source.from(SourceData.Strings.NON_EMPTY, SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(stackClass, source),
              source.getAt(0)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(stackClass, arguments[0]),
            TestNames.method("top")),
          arguments));
    }
    //</editor-fold>

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeTop(
      Stack<Item> stack)
    {
      Item topItem = stack.top();
      assertEquals(topItem, stack.top());
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeItems(
      Stack<Item> stack)
    {
      Item[] oldItems = Source.from(stack).array();
      stack.top();
      Item[] newItems = Source.from(stack).array();
      assertArrayEquals(oldItems, newItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeSize(
      Stack<Item> stack)
    {
      int size = stack.size();
      stack.top();
      assertEquals(size, stack.size());
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> stackClass)
    {
      return Source.from(SourceData.Strings.NON_EMPTY, SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(stackClass, source)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(stackClass, arguments[0]),
            TestNames.method("top")),
          arguments));
    }
    //</editor-fold>
  }

  @DisplayName("pop")
  @DefaultDisplayNameGeneration
  interface Pop
  {
    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void returnsTop(
      Stack<Item> stack)
    {
      Item topItem = stack.top();
      assertEquals(topItem, stack.pop());
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOthers(
      Stack<Item> stack)
    {
      Item[] oldOtherItems =
        Source.from(stack).skipFirst().array();
      stack.pop();
      Item[] newOtherItems =
        Source.from(stack).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void decrementsSize(
      Stack<Item> stack)
    {
      int oldSize = stack.size();
      stack.pop();
      int newSize = stack.size();
      assertEquals(oldSize - 1, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> stackClass)
    {
      return Source.from(SourceData.Strings.NON_EMPTY, SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(stackClass, source)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(stackClass, arguments[0]),
            TestNames.method("pop")),
          arguments));
    }
    //</editor-fold>
  }
}
