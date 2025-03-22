package dsa.lab04.base;

import dsa.lib.*;
import dsa.lib.lab04.MapItemSourceData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

public interface MapTests
{

  static <Key, Value> Source<MapItem<Key, Value>> sortedItems(
    Map<Key, Value> map)
  {
    return Source.from(map)
      .sorted(Comparator.comparing((item) -> Objects.hashCode(item.key())));
  }


  static <Key, Value> Predicate<MapItem<Key, Value>> keyNotEquals(Key key)
  {
    return (item) -> !Objects.equals(key, item.key());
  }


  @DisplayName("find")
  @DefaultDisplayNameGeneration
  interface Find
  {

    @ParameterizedTest
    @MethodSource
    default <Key, Value> void findsIfContained(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      assertEquals(item, map.find(item.key()));
    }


    //<editor-fold defaultstate="collapsed" desc="findsIfContained arguments">
    static Source<Arguments> findsIfContained(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item) -> new Object[]{
                ClassUtils.construct(mapClass, items),
                item}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("find", arguments[1])),
            arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void doesNotFindIfNotContained(
      Map<Key, Value> map,
      Key key)
    {
      assertThrows(
        NoSuchElementException.class,
        () -> map.find(key));
    }


    //<editor-fold defaultstate="collapsed" desc="doesNotFindIfNotContained arguments">
    static Source<Arguments> doesNotFindIfNotContained(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item, index) -> new Object[]{
                ClassUtils.construct(mapClass, items.skipIndex(index)),
                item.key()}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("find", arguments[1])),
            arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @DefaultMethodSource
    default <Key, Value> void doesNotChangeItems(
      Map<Key, Value> map,
      Key key)
    {
      MapItem<Key, Value>[] oldItems = sortedItems(map).array(MapItem.class);
      try
      {
        map.find(key);
      }
      catch (NoSuchElementException ignored)
      {
      }
      MapItem<Key, Value>[] newItems = sortedItems(map).array(MapItem.class);
      assertArrayEquals(oldItems, newItems);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Key, Value> void doesNotChangeSize(
      Map<Key, Value> map,
      Key key)
    {
      int oldSize = map.size();
      try
      {
        map.find(key);
      }
      catch (NoSuchElementException ignored)
      {
      }
      int newSize = map.size();
      assertEquals(oldSize, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.flatReplace((item, index) ->
                Source.from(items, items.skipIndex(index))
                  .replace((items_) -> new Object[]{
                    ClassUtils.construct(mapClass, items_),
                    item.key()})))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("find", arguments[1])),
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
    default <Key, Value> void inserts(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      map.insert(item);
      assertEquals(item, map.find(item.key()));
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Key, Value> void doesNotChangeOthers(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      MapItem<Key, Value>[] before =
        sortedItems(map).filter(keyNotEquals(item.key())).array(MapItem.class);
      map.insert(item);
      MapItem<Key, Value>[] after =
        sortedItems(map).filter(keyNotEquals(item.key())).array(MapItem.class);
      assertArrayEquals(before, after);
    }


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void doesNotChangeSizeIfContained(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      int oldSize = map.size();
      map.insert(item);
      int newSize = map.size();
      assertEquals(oldSize, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="doesNotChangeSizeIfContained arguments">
    static Source<Arguments> doesNotChangeSizeIfContained(Class<?> mapClass)
    {
      BiFunction<Source<Source<MapItem<?, ?>>>, Source<?>, Source<Arguments>>
        forType = (itemss, values) ->
        itemss.flatReplace((items) ->
            items.flatReplace((item) ->
              values.replace((value) -> new Object[]{
                ClassUtils.construct(mapClass, items),
                new MapItem<>(item.key(), value)})))
          .quadratic()
          .limit()
          .replace((arguments) ->
            argumentSet(
              TestNames.format(
                TestNames.constructorFor(mapClass, arguments[0]),
                TestNames.method("insert", arguments[1])),
              arguments));
      return Source.chain(
        forType.apply(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY.cast(),
          StringData.ALL),
        forType.apply(
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY.cast(),
          IntData.ALL));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void incrementsSizeIfNotContained(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      int oldSize = map.size();
      map.insert(item);
      int newSize = map.size();
      assertEquals(oldSize + 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="incrementsSizeIfNotContained arguments">
    static Source<Arguments> incrementsSizeIfNotContained(Class<?> mapClass)
    {
      BiFunction<Source<Source<MapItem<?, ?>>>, Source<?>, Source<Arguments>>
        forType = (itemss, values) ->
        itemss.flatReplace((items) ->
            items.flatReplace((item, index) ->
              values.replace((value) -> new Object[]{
                ClassUtils.construct(mapClass, items.skipIndex(index)),
                new MapItem<>(item.key(), value)})))
          .quadratic()
          .limit()
          .replace((arguments) ->
            argumentSet(
              TestNames.format(
                TestNames.constructorFor(mapClass, arguments[0]),
                TestNames.method("insert", arguments[1])),
              arguments));
      return Source.chain(
        forType.apply(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY.cast(),
          StringData.ALL),
        forType.apply(
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY.cast(),
          IntData.ALL));
    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="arguments">
    static Source<Arguments> arguments(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.flatReplace((item, index) ->
                Source.from(items, items.skipIndex(index))
                  .replace((items_) -> new Object[]{
                    ClassUtils.construct(mapClass, items_),
                    item})))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("insert", arguments[1])),
            arguments));
    }
    //</editor-fold>

  }

  @DisplayName("remove")
  @DefaultDisplayNameGeneration
  interface Remove
  {

    @ParameterizedTest
    @MethodSource
    default <Key, Value> void returnsItemIfContained(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      assertEquals(item, map.remove(item.key()));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsItemIfContained arguments">
    static Source<Arguments> returnsItemIfContained(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item) -> new Object[]{
                ClassUtils.construct(mapClass, items),
                item}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("remove", ((MapItem<?, ?>) arguments[1]).key())),
            arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void removesIfContained(
      Map<Key, Value> map,
      Key key)
    {
      map.remove(key);
      assertFalse(map.containsKey(key));
    }


    //<editor-fold defaultstate="collapsed" desc="removesIfContained arguments">
    static Source<Arguments> removesIfContained(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item) -> new Object[]{
                ClassUtils.construct(mapClass, items),
                item.key()}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("remove", arguments[1])),
            arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void throwsIfNotContained(
      Map<Key, Value> map,
      Key key)
    {
      assertThrows(
        NoSuchElementException.class,
        () -> map.remove(key));
    }


    //<editor-fold defaultstate="collapsed" desc="throwsIfNotContained arguments">
    static Source<Arguments> throwsIfNotContained(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item, index) -> new Object[]{
                ClassUtils.construct(mapClass, items.skipIndex(index)),
                item.key()}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("remove", arguments[1])),
            arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void doesNotChangeOthers(
      Map<Key, Value> map,
      Key key)
    {
      MapItem<Key, Value>[] oldOtherItems =
        sortedItems(map).filter(keyNotEquals(key)).array(MapItem.class);
      try
      {
        map.remove(key);
      }
      catch (NoSuchElementException ignored)
      {
      }
      MapItem<Key, Value>[] newOtherItems =
        sortedItems(map).array(MapItem.class);
      assertArrayEquals(oldOtherItems, newOtherItems);
    }


    //<editor-fold defaultstate="collapsed" desc="doesNotChangeOthers arguments">
    static Source<Arguments> doesNotChangeOthers(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.flatReplace((item, index) ->
                Source.from(items, items.skipIndex(index))
                  .replace((items_) -> new Object[]{
                    ClassUtils.construct(mapClass, items_),
                    item.key()})))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("remove", arguments[1])),
            arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void decrementsSizeIfContained(
      Map<Key, Value> map,
      Key containedKey)
    {
      int oldSize = map.size();
      map.remove(containedKey);
      int newSize = map.size();
      assertEquals(oldSize - 1, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="decrementsSizeIfContained arguments">
    static Source<Arguments> decrementsSizeIfContained(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item) -> new Object[]{
                ClassUtils.construct(mapClass, items),
                item.key()}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("remove", arguments[1])),
            arguments));
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Key, Value> void doesNotChangeSizeIfNotContained(
      Map<Key, Value> map,
      Key notContainedKey)
    {
      int oldSize = map.size();
      try
      {
        map.remove(notContainedKey);
      }
      catch (NoSuchElementException ignored)
      {
      }
      int newSize = map.size();
      assertEquals(oldSize, newSize);
    }


    //<editor-fold defaultstate="collapsed" desc="doesNotChangeSizeIfNotContained arguments">
    static Source<Arguments> doesNotChangeSizeIfNotContained(Class<?> mapClass)
    {
      return Source.from(
          MapItemSourceData.Uniques.IntsToStrings.NON_EMPTY,
          MapItemSourceData.Uniques.StringsToInts.NON_EMPTY)
        .flatReplace((itemss) ->
          itemss.flatReplace((items) ->
              items.replace((item, index) -> new Object[]{
                ClassUtils.construct(mapClass, items.skipIndex(index)),
                item.key()}))
            .quadratic()
            .limit())
        .replace((arguments) ->
          argumentSet(
            TestNames.format(
              TestNames.constructorFor(mapClass, arguments[0]),
              TestNames.method("remove", arguments[1])),
            arguments));
    }
    //</editor-fold>

  }

}
