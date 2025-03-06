package dsa.lab05.base;

import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lib.Iterators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class MapTests
{
  public static class Find
  {
    public static <Key, Value> void findsIfContained(
      Map<Key, Value> map,
      MapItem<Key, Value> containedItem)
    {
      assertEquals(containedItem, map.find(containedItem.key()));
    }

    public static <Key, Value> void doesNotFindIfNotContained(
      Map<Key, Value> map,
      Key notContainedKey)
    {
      assertThrows(
        NoSuchElementException.class,
        () -> map.find(notContainedKey));
    }

    public static <Key, Value> void doesNotChangeItems(
      Map<Key, Value> map,
      Key key)
    {
      MapItem<Key, Value>[] before = Iterators.toArray(MapItem.class, map);
      try
      {
        map.find(key);
      }
      catch (NoSuchElementException ignored)
      {
      }
      MapItem<Key, Value>[] after = Iterators.toArray(MapItem.class, map);
      Arrays.sort(
        before,
        Comparator.comparing((item) -> Objects.hashCode(item.key())));
      Arrays.sort(
        after,
        Comparator.comparing((item) -> Objects.hashCode(item.key())));
      assertArrayEquals(before, after);
    }

    public static <Key, Value> void doesNotChangeSize(
      Map<Key, Value> map,
      Key key)
    {
      int size = map.size();
      try
      {
        map.find(key);
      }
      catch (NoSuchElementException ignored)
      {
      }
      assertEquals(size, map.size());
    }
  }

  public static class Insert
  {
    public static <Key, Value> void inserts(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      map.insert(item);
      assertEquals(item, map.find(item.key()));
    }

    public static <Key, Value> void doesNotChangeOthers(
      Map<Key, Value> map,
      MapItem<Key, Value> item)
    {
      MapItem<Key, Value>[] before = Iterators.toArray(
        MapItem.class,
        Iterators.filter(
          (containedItem) -> !Objects.equals(item.key(), containedItem.key()),
          map));
      map.insert(item);
      MapItem<Key, Value>[] after = Iterators.toArray(
        MapItem.class,
        Iterators.filter(
          (containedItem) -> !Objects.equals(item.key(), containedItem.key()),
          map));
      Arrays.sort(
        before,
        Comparator.comparing((other) -> Objects.hashCode(other.key())));
      Arrays.sort(
        after,
        Comparator.comparing((other) -> Objects.hashCode(other.key())));
      assertArrayEquals(before, after);
    }

    public static <Key, Value> void doesNotChangeSizeIfContained(
      Map<Key, Value> map,
      Key containedKey,
      Value value)
    {
      int size = map.size();
      map.insert(containedKey, value);
      assertEquals(size, map.size());
    }

    public static <Key, Value> void incrementsSizeIfNotContained(
      Map<Key, Value> map,
      Key notContainedKey,
      Value value)
    {
      int size = map.size();
      map.insert(notContainedKey, value);
      assertEquals(size + 1, map.size());
    }
  }

  public static class Remove
  {
    public static <Key, Value> void returnsItemIfContained(
      Map<Key, Value> map,
      MapItem<Key, Value> containedItem)
    {
      assertEquals(containedItem, map.remove(containedItem.key()));
    }

    public static <Key, Value> void removesIfContained(
      Map<Key, Value> map,
      Key containedKey)
    {
      map.remove(containedKey);
      assertFalse(map.containsKey(containedKey));
    }

    public static <Key, Value> void throwsIfNotContained(
      Map<Key, Value> map,
      Key notContainedKey)
    {
      assertThrows(
        NoSuchElementException.class,
        () -> map.remove(notContainedKey));
    }

    public static <Key, Value> void doesNotChangeOthers(
      Map<Key, Value> map,
      Key key)
    {
      MapItem<Key, Value>[] before = Iterators.toArray(
        MapItem.class,
        Iterators.filter((item) -> !Objects.equals(key, item.key()), map));
      try
      {
        map.remove(key);
      }
      catch (NoSuchElementException ignored)
      {
      }
      MapItem<Key, Value>[] after = Iterators.toArray(MapItem.class, map);
      Arrays.sort(
        before,
        Comparator.comparing((item) -> Objects.hashCode(item.key())));
      Arrays.sort(
        after,
        Comparator.comparing((item) -> Objects.hashCode(item.key())));
      assertArrayEquals(before, after);
    }

    public static <Key, Value> void decrementsSizeIfContained(
      Map<Key, Value> map,
      Key containedKey)
    {
      int size = map.size();
      map.remove(containedKey);
      assertEquals(size - 1, map.size());
    }

    public static <Key, Value> void doesNotChangeSizeIfNotContained(
      Map<Key, Value> map,
      Key notContainedKey)
    {
      int size = map.size();
      try
      {
        map.remove(notContainedKey);
      }
      catch (NoSuchElementException ignored)
      {
      }
      assertEquals(size, map.size());
    }
  }
}
