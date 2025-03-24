package dsa.lab09.base;

import dsa.lib.*;
import dsa.lib.lab09.PriorityQueueItemSourceData.IntsToInts;
import dsa.lib.lab09.PriorityQueueItemSourceData.IntsToStrings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.BiFunction;

import static dsa.lib.lab09.PriorityQueueItemData.INTS_TO_INTS;
import static dsa.lib.lab09.PriorityQueueItemData.INTS_TO_STRINGS;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface PriorityQueueTests
{

  @DisplayName("max")
  @DefaultDisplayNameGeneration
  interface Max
  {

    @ParameterizedTest
    @MethodSource
    default <Priority extends Comparable<Priority>, Item> void returnsMaxPriority(
      PriorityQueue<Priority, Item> queue,
      Priority maxPriority)
    {
      assertEquals(maxPriority, queue.max().priority());
    }


    //<editor-fold defaultstate="collapsed" desc="returnsMaxPriority arguments">
    @SuppressWarnings({"rawtypes", "unchecked"})
    static Source<Arguments> returnsMaxPriority(Class<?> priorityQueueClass)
    {
      return Source.from(IntsToInts.NON_EMPTY, IntsToStrings.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(priorityQueueClass, source),
              Collections.<PriorityQueueItem>max(source.list()).priority()})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(priorityQueueClass, arguments[0]),
            TestNames.method("max")),
          arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void doesNotChangeMax(
      PriorityQueue<Priority, Item> queue)
    {
      PriorityQueueItem<Priority, Item> maxItem = queue.max();
      assertEquals(maxItem, queue.max());
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void doesNotChangeItems(
      PriorityQueue<Priority, Item> queue)
    {
      PriorityQueueItem<Priority, Item>[] oldItems =
        Source.from(queue).array(PriorityQueueItem.class);
      queue.max();
      PriorityQueueItem<Priority, Item>[] newItems =
        Source.from(queue).array(PriorityQueueItem.class);
      assertArrayEquals(oldItems, newItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void doesNotChangeSize(
      PriorityQueue<Priority, Item> queue)
    {
      int oldSize = queue.size();
      queue.max();
      int newSize = queue.size();
      assertEquals(oldSize, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> priorityQueueClass)
    {
      return Source.from(IntsToInts.NON_EMPTY, IntsToStrings.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(priorityQueueClass, source)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(priorityQueueClass, arguments[0]),
            TestNames.method("max")),
          arguments));
    }
    //</editor-fold>

  }

  @DisplayName("insert")
  @DefaultDisplayNameGeneration
  interface Insert
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void changesMaxOnlyIfShould(
      PriorityQueue<Priority, Item> queue,
      PriorityQueueItem<Priority, Item> item)
    {
      boolean wasEmpty = queue.isEmpty();
      PriorityQueueItem<Priority, Item> oldMaxItem = queue.max();
      queue.insert(item);
      boolean shouldBeMax = wasEmpty || item.compareTo(oldMaxItem) > 0;
      PriorityQueueItem<Priority, Item> newMaxItem = queue.max();
      assertEquals(shouldBeMax ? item : oldMaxItem, newMaxItem);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void doesNotChangeOthers(
      PriorityQueue<Priority, Item> queue,
      PriorityQueueItem<Priority, Item> item)
    {
      PriorityQueueItem<Priority, Item>[] oldOtherItems =
        Source.from(queue)
          .array(PriorityQueueItem.class);
      queue.insert(item);
      PriorityQueueItem<Priority, Item>[] newOtherItems =
        Source.from(queue)
          .skipFirstSame(item)
          .array(PriorityQueueItem.class);
      assertArrayEquals(oldOtherItems, newOtherItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void incrementsSize(
      PriorityQueue<Priority, Item> queue,
      PriorityQueueItem<Priority, Item> item)
    {
      int oldSize = queue.size();
      queue.insert(item);
      int newSize = queue.size();
      assertEquals(oldSize + 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> priorityQueueClass)
    {
      BiFunction<Source<Source<?>>, Source<?>, Source<Arguments>> forType =
        (sources, items) ->
          sources.flatReplace((source) ->
              source.validIndices().flatReplace((index) ->
                items.replace((item) -> new Object[]{
                  ClassUtils.construct(priorityQueueClass, source),
                  item})))
            .quadratic()
            .limit()
            .replace((arguments) -> argumentSet(
              TestNames.format(
                TestNames.constructorFor(priorityQueueClass, arguments[0]),
                TestNames.method("insert", arguments[1])),
              arguments));
      return Source.chain(
        forType.apply(IntsToInts.ALL.cast(), INTS_TO_INTS.cast()),
        forType.apply(IntsToStrings.ALL.cast(), INTS_TO_STRINGS.cast()));
    }
    //</editor-fold>

  }

  @DisplayName("removeMax")
  @DefaultDisplayNameGeneration
  interface RemoveMax
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void returnsMax(
      PriorityQueue<Priority, Item> queue)
    {
      PriorityQueueItem<Priority, Item> maxItem = queue.max();
      assertEquals(maxItem, queue.removeMax());
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void doesNotChangeOthers(
      PriorityQueue<Priority, Item> queue)
    {
      PriorityQueueItem<Priority, Item>[] oldItems =
        Source.from(queue)
          .skipFirstSame(queue.max())
          .sorted(Comparator.comparing(Object::hashCode))
          .array(PriorityQueueItem.class);
      queue.removeMax();
      PriorityQueueItem<Priority, Item>[] newItems =
        Source.from(queue)
          .sorted(Comparator.comparing(Object::hashCode))
          .array(PriorityQueueItem.class);
      assertArrayEquals(oldItems, newItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Priority extends Comparable<Priority>, Item> void decrementsSize(
      PriorityQueue<Priority, Item> queue)
    {
      int oldSize = queue.size();
      queue.removeMax();
      int newSize = queue.size();
      assertEquals(oldSize - 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> priorityQueueClass)
    {
      return Source.from(IntsToInts.NON_EMPTY, IntsToStrings.NON_EMPTY)
        .flatReplace((sources) ->
          sources.replace((source) -> new Object[]{
              ClassUtils.construct(priorityQueueClass, source)})
            .quadratic()
            .limit())
        .replace((arguments) -> argumentSet(
          TestNames.format(
            TestNames.constructorFor(priorityQueueClass, arguments[0]),
            TestNames.method("removeMax")),
          arguments));
    }
    //</editor-fold>

  }

}
