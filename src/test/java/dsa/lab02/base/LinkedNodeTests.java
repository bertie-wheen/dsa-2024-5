package dsa.lab02.base;

import dsa.lib.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface LinkedNodeTests
{

  @DisplayName("insertPrevious")
  @DefaultDisplayNameGeneration
  interface InsertPrevious
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void insertsAsPrevious(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item> node = linkedList.node(index);
      node.insertPrevious(item);
      assertEquals(item, node.previous().item());
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void changesFirstNodeOnlyIfShould(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item> node = linkedList.node(index);
      LinkedNode<Item> oldFirstNode = linkedList.firstNode();
      node.insertPrevious(item);
      LinkedNode<Item> newFirstNode = linkedList.firstNode();
      LinkedNode<Item> newPreviousNode = node.previous();
      assertSame(
        node == oldFirstNode ? newPreviousNode : oldFirstNode,
        newFirstNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeLastNode(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item> oldLastNode = linkedList.lastNode();
      linkedList.node(index).insertPrevious(item);
      LinkedNode<Item> newLastNode = linkedList.lastNode();
      assertSame(oldLastNode, newLastNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherItems(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      Item[] oldOtherItems =
        Source.from(linkedList).array();
      linkedList.node(index).insertPrevious(item);
      Item[] newOtherItems =
        Source.from(linkedList).skipIndex(index).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherNodes(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item>[] oldOtherNodes =
        Source.from(linkedList.nodes())
          .array(LinkedNode.class);
      linkedList.node(index).insertPrevious(item);
      LinkedNode<Item>[] newOtherNodes =
        Source.from(linkedList.nodes())
          .skipIndex(index)
          .array(LinkedNode.class);
      assertArrayEquals(oldOtherNodes, newOtherNodes);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void incrementsSize(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      int oldSize = linkedList.size();
      linkedList.node(index).insertPrevious(item);
      int newSize = linkedList.size();
      assertEquals(oldSize + 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> linkedListClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              source.validIndices().flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(linkedListClass, source),
                  index,
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(linkedListClass, arguments[0]),
                TestNames.method("node", arguments[1]),
                TestNames.method("insertPrevious", arguments[2])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.NON_EMPTY.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.NON_EMPTY.cast(), IntData.ALL));
    }
    //</editor-fold>

  }

  @DisplayName("insertNext")
  @DefaultDisplayNameGeneration
  interface InsertNext
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void insertsAsNext(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item> node = linkedList.node(index);
      node.insertNext(item);
      assertEquals(item, node.next().item());
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeFirstNode(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item> oldFirstNode = linkedList.firstNode();
      linkedList.node(index).insertNext(item);
      LinkedNode<Item> newFirstNode = linkedList.firstNode();
      assertSame(oldFirstNode, newFirstNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void changesLastNodeOnlyIfShould(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item> node = linkedList.node(index);
      LinkedNode<Item> oldLastNode = linkedList.lastNode();
      node.insertNext(item);
      LinkedNode<Item> newLastNode = linkedList.lastNode();
      LinkedNode<Item> newNextNode = node.next();
      assertSame(
        node == oldLastNode ? newNextNode : oldLastNode,
        newLastNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherItems(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      Item[] oldOtherItems =
        Source.from(linkedList).array();
      linkedList.node(index).insertNext(item);
      Item[] newOtherItems =
        Source.from(linkedList).skipIndex(index + 1).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherNodes(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      LinkedNode<Item>[] oldOtherNodes =
        Source.from(linkedList.nodes())
          .array(LinkedNode.class);
      linkedList.node(index).insertNext(item);
      LinkedNode<Item>[] newOtherNodes =
        Source.from(linkedList.nodes())
          .skipIndex(index + 1)
          .array(LinkedNode.class);
      assertArrayEquals(oldOtherNodes, newOtherNodes);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void incrementsSize(
      LinkedList<Item> linkedList,
      int index,
      Item item)
    {
      int oldSize = linkedList.size();
      linkedList.node(index).insertNext(item);
      int newSize = linkedList.size();
      assertEquals(oldSize + 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> linkedListClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              source.validIndices().flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(linkedListClass, source),
                  index,
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(linkedListClass, arguments[0]),
                TestNames.method("node", arguments[1]),
                TestNames.method("insertNext", arguments[2])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.NON_EMPTY.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.NON_EMPTY.cast(), IntData.ALL));
    }
    //</editor-fold>

  }

  @DisplayName("remove")
  @DefaultDisplayNameGeneration
  interface Remove
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void returnsItem(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item> node = linkedList.node(index);
      Item next = node.item();
      assertEquals(next, node.remove());
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void changesFirstNodeOnlyIfShould(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item> node = linkedList.node(index);
      LinkedNode<Item> oldNextNode = node.next();
      LinkedNode<Item> oldFirstNode = linkedList.firstNode();
      node.remove();
      LinkedNode<Item> newFirstNode = linkedList.firstNode();
      assertSame(
        node == oldFirstNode ? oldNextNode : oldFirstNode,
        newFirstNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void changesLastNodeOnlyIfShould(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item> node = linkedList.node(index);
      LinkedNode<Item> oldPreviousNode = node.previous();
      LinkedNode<Item> oldLastNode = linkedList.lastNode();
      node.remove();
      LinkedNode<Item> newLastNode = linkedList.lastNode();
      assertSame(
        node == oldLastNode ? oldPreviousNode : oldLastNode,
        newLastNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherItems(
      LinkedList<Item> linkedList,
      int index)
    {
      Item[] oldOtherItems =
        Source.from(linkedList).skipIndex(index).array();
      linkedList.node(index).remove();
      Item[] newOtherItems =
        Source.from(linkedList).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherNodes(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item>[] oldOtherNodes =
        Source.from(linkedList.nodes())
          .skipIndex(index)
          .array(LinkedNode.class);
      linkedList.node(index).remove();
      LinkedNode<Item>[] newOtherNodes =
        Source.from(linkedList.nodes())
          .array(LinkedNode.class);
      assertArrayEquals(oldOtherNodes, newOtherNodes);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void decrementsSize(
      LinkedList<Item> linkedList,
      int index)
    {
      int oldSize = linkedList.size();
      linkedList.node(index).remove();
      int newSize = linkedList.size();
      assertEquals(oldSize - 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> linkedListClass)
    {
      return Source.from(
          SourceData.Strings.NON_EMPTY,
          SourceData.Ints.NON_EMPTY)
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
            TestNames.method("node", arguments[1]),
            TestNames.method("remove")),
          arguments));
    }
    //</editor-fold>

  }

  @DisplayName("removeNext")
  @DefaultDisplayNameGeneration
  interface RemoveNext
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void returnsNext(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item> node = linkedList.node(index);
      Item nextItem = node.next().item();
      assertEquals(nextItem, node.removeNext());
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeFirstNode(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item> oldFirstNode = linkedList.firstNode();
      linkedList.node(index).removeNext();
      LinkedNode<Item> newFirstNode = linkedList.firstNode();
      assertSame(oldFirstNode, newFirstNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void changesLastNodeOnlyIfShould(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item> oldLastNode = linkedList.lastNode();
      LinkedNode<Item> node = linkedList.node(index);
      LinkedNode<Item> nextNode = node.next();
      node.removeNext();
      LinkedNode<Item> newLastNode = linkedList.lastNode();
      assertSame(
        nextNode == oldLastNode ? node : oldLastNode,
        newLastNode);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherItems(
      LinkedList<Item> linkedList,
      int index)
    {
      Item[] oldOtherItems =
        Source.from(linkedList).skipIndex(index + 1).array();
      linkedList.node(index).removeNext();
      Item[] newOtherItems =
        Source.from(linkedList).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOtherNodes(
      LinkedList<Item> linkedList,
      int index)
    {
      LinkedNode<Item>[] oldOtherNodes =
        Source.from(linkedList.nodes())
          .skipIndex(index + 1)
          .array(LinkedNode.class);
      linkedList.node(index).removeNext();
      LinkedNode<Item>[] newOtherNodes =
        Source.from(linkedList.nodes())
          .array(LinkedNode.class);
      assertArrayEquals(oldOtherNodes, newOtherNodes);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void decrementsSize(
      LinkedList<Item> linkedList,
      int index)
    {
      int oldSize = linkedList.size();
      linkedList.node(index).removeNext();
      int newSize = linkedList.size();
      assertEquals(oldSize - 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> linkedListClass)
    {
      return Source.from(
          SourceData.Strings.MULTI_ITEM,
          SourceData.Ints.MULTI_ITEM)
        .flatReplace((sources) ->
          sources.flatReplace((source) ->
              source.validNonLastIndices().replace((index) -> new Object[]{
                ClassUtils.construct(linkedListClass, source),
                index}))
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(linkedListClass, arguments[0]),
            TestNames.method("node", arguments[1]),
            TestNames.method("removeNext")),
          arguments));
    }
    //</editor-fold>

  }

}
