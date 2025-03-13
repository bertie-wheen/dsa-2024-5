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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface QueueTests
{
  @DisplayName("enqueue")
  @DefaultDisplayNameGeneration
  interface Enqueue
  {
    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void enqueuesAsBack(
      Queue<Item> queue,
      Item item)
    {
      queue.enqueue(item);
      boolean isEmpty = true;
      Item newBackItem = null;
      for (Item item_ : queue)
      {
        isEmpty = false;
        newBackItem = item_;
      }
      if (isEmpty)
      {
        fail("queue empty after enqueue");
      }
      else
      {
        assertEquals(item, newBackItem);
      }
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void changesFrontOnlyIfShould(
      Queue<Item> queue,
      Item item)
    {
      boolean wasEmpty = queue.isEmpty();
      Item oldFrontItem = queue.front();
      queue.enqueue(item);
      Item newFrontItem = queue.front();
      assertEquals(wasEmpty ? item : oldFrontItem, newFrontItem);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOthers(
      Queue<Item> queue,
      Item item)
    {
      Item[] oldOtherItems = Source.from(queue).array();
      queue.enqueue(item);
      Item[] newOtherItems = Source.from(queue).skipLast().array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void incrementsSize(
      Queue<Item> queue,
      Item item)
    {
      int oldSize = queue.size();
      queue.enqueue(item);
      int newSize = queue.size();
      assertEquals(oldSize + 1, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> queueClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              source.validIndices().flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(queueClass, source),
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(queueClass, arguments[0]),
                TestNames.method("enqueue", arguments[1])),
              arguments));
      return Source.chain(
        forType.apply(SourceData.Strings.ALL.cast(), StringData.ALL),
        forType.apply(SourceData.Ints.ALL.cast(), IntData.ALL));
    }
    //</editor-fold>
  }

  @DisplayName("front")
  @DefaultDisplayNameGeneration
  interface Front
  {
    @ParameterizedTest
    @MethodSource
    default <Item> void returnsFront(
      Queue<Item> queue,
      Item frontItem)
    {
      assertEquals(frontItem, queue.front());
    }

    //<editor-fold defaultstate="collapsed" desc="returnsFront arguments">
    static Source<Arguments> returnsFront(Class<?> queueClass)
    {
      return Source.from(SourceData.Strings.NON_EMPTY, SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(queueClass, source),
              source.getAt(0)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(queueClass, arguments[0]),
            TestNames.method("front")),
          arguments));
    }
    //</editor-fold>

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeFront(
      Queue<Item> queue)
    {
      Item frontItem = queue.front();
      assertEquals(frontItem, queue.front());
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeItems(
      Queue<Item> queue)
    {
      Item[] oldItems = Source.from(queue).array();
      queue.front();
      Item[] newItems = Source.from(queue).array();
      assertArrayEquals(oldItems, newItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeSize(
      Queue<Item> queue)
    {
      int oldSize = queue.size();
      queue.front();
      int newSize = queue.size();
      assertEquals(oldSize, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> queueClass)
    {
      return Source.from(SourceData.Strings.NON_EMPTY, SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(queueClass, source)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(queueClass, arguments[0]),
            TestNames.method("front")),
          arguments));
    }
    //</editor-fold>
  }

  @DisplayName("dequeue")
  @DefaultDisplayNameGeneration
  interface Dequeue
  {
    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void returnsFront(
      Queue<Item> queue)
    {
      Item frontItem = queue.front();
      assertEquals(frontItem, queue.dequeue());
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void doesNotChangeOthers(
      Queue<Item> queue)
    {
      Item[] oldOtherItems = Source.from(queue).skipFirst().array();
      queue.dequeue();
      Item[] newOtherItems = Source.from(queue).array();
      assertArrayEquals(oldOtherItems, newOtherItems);
    }

    @ParameterizedTest
    @DefaultMethodSource
    default <Item> void decrementsSize(
      Queue<Item> queue)
    {
      int oldSize = queue.size();
      queue.dequeue();
      int newSize = queue.size();
      assertEquals(oldSize - 1, newSize);
    }

    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> queueClass)
    {
      return Source.from(SourceData.Strings.NON_EMPTY, SourceData.Ints.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(queueClass, source)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(queueClass, arguments[0]),
            TestNames.method("dequeue")),
          arguments));
    }
    //</editor-fold>
  }
}
