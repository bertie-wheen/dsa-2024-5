package dsa.lab02.base;

import dsa.lib.*;
import dsa.lib.IntData;
import dsa.lib.SourceData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface LinkedListTests
{
  @DisplayName("node")
  @DefaultDisplayNameGeneration
  interface Node
  {
    @ParameterizedTest
    @MethodSource
    default <Item> void throwsIfIndexBelowBound(
      LinkedList<Item> linkedList,
      int index)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> linkedList.node(index));
    }

    //<editor-fold defaultstate="collapsed" desc="throwsIfIndexBelowBound arguments">
    static Source<Arguments> throwsIfIndexBelowBound(
      Class<?> linkedListClass)
    {
      return Source.from(SourceData.Strings.ALL, SourceData.Ints.ALL)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              IntData.NEGATIVE.replace((index) -> new Object[]{
                ClassUtils.construct(linkedListClass, source),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(linkedListClass, arguments[0]),
            TestNames.method("node", arguments[1])),
          arguments));
    }
    //</editor-fold>

    @ParameterizedTest
    @MethodSource
    default <Item> void throwsIfIndexAboveBound(
      LinkedList<Item> linkedList,
      int index)
    {
      assertThrows(
        IndexOutOfBoundsException.class,
        () -> linkedList.node(index));
    }

    //<editor-fold defaultstate="collapsed" desc="throwsIfIndexBelowBound arguments">
    static Source<Arguments> throwsIfIndexAboveBound(
      Class<?> linkedListClass)
    {
      return Source.from(SourceData.Strings.ALL, SourceData.Ints.ALL)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              IntData.NON_NEGATIVE.replace((offset) -> new Object[]{
                ClassUtils.construct(linkedListClass, source),
                MathUtils.addSaturating(source.size(), offset)}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(linkedListClass, arguments[0]),
            TestNames.method("node", arguments[1])),
          arguments));
    }
    //</editor-fold>

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void getsCorrectIndex(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item> node = linkedList.node(index);
      int nodeIndex = 0;
      for (LinkedNode<Item> n : linkedList.nodes())
      {
        if (node == n)
        {
          break;
        }
        nodeIndex++;
      }
      assertEquals(index, nodeIndex);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeItems(
      LinkedList<Item> linkedList,
      int index)
    {
      Item[] oldItems = Source.from(linkedList).array();
      linkedList.node(index);
      Item[] newItems = Source.from(linkedList).array();
      assertArrayEquals(oldItems, newItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeSize(
      LinkedList<Item> linkedList,
      int index)
    {
      int oldSize = linkedList.size();
      linkedList.node(index);
      int newSize = linkedList.size();
      assertEquals(oldSize, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> linkedListClass)
    {
      return Source.from(SourceData.Strings.NON_EMPTY, SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              source.validIndices().replace((index) -> new Object[]{
                ClassUtils.construct(linkedListClass, source),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(linkedListClass, arguments[0]),
            TestNames.method("node", arguments[1])),
          arguments));
    }
    //</editor-fold>
  }
}
