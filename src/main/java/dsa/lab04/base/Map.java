package dsa.lab04.base;

import dsa.lab02.base.Container;
import dsa.lib.Iterators;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A map.
 * <p>
 * A container where the items have unique keys.
 * An item consists of a key and a value.
 * The key is the uniquely identifying part of the item.
 * The value is everything else.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public interface Map<Key, Value>
  extends Container<MapItem<Key, Value>>
{

  /**
   * Find the item with the given key.
   *
   * @param key the item's key
   * @return the item
   * @throws NoSuchElementException if no item has key {@code key}
   */
  MapItem<Key, Value> find(Key key)
    throws NoSuchElementException;


  /**
   * Insert the given item.
   * <p>
   * If there's already an item with the same key,
   * that item is replaced with this one.
   * <p>
   * This means that if the key was not already contained,
   * the size is incremented, otherwise it isn't.
   *
   * @param item the item
   */
  void insert(MapItem<Key, Value> item);


  /**
   * Insert an item with the given key and value.
   * <p>
   * If there's already an item with the same key,
   * that item is replaced with this one.
   * <p>
   * This means that if the key was not already contained,
   * the size is incremented, otherwise it isn't.
   *
   * @param key   the item's key
   * @param value the item's value
   */
  default void insert(Key key, Value value)
  {
    this.insert(new MapItem<>(key, value));
  }


  /**
   * Remove and return the item with the given key.
   * <p>
   * Decrements the size (assuming the item was actually in the map).
   *
   * @param key the item's key
   * @return the item
   * @throws NoSuchElementException if {@code key} is not contained
   */
  MapItem<Key, Value> remove(Key key)
    throws NoSuchElementException;


  /**
   * Check if the given key is equal to any of those contained.
   *
   * @param key the key to check for membership
   * @return whether such a key is contained
   */
  default boolean containsKey(Key key)
  {
    for (Key containedKey : this.keys())
    {
      if (Objects.equals(key, containedKey))
      {
        return true;
      }
    }
    return false;
  }


  /**
   * Check if the given value is equal to any of those contained.
   *
   * @param value the value to check for membership
   * @return whether such a value is contained
   */
  default boolean containsValue(Value value)
  {
    for (Value containedValue : this.values())
    {
      if (Objects.equals(value, containedValue))
      {
        return true;
      }
    }
    return false;
  }


  /**
   * Get the value associated with the given key.
   *
   * @param key the key
   * @return the corresponding value
   * @throws NoSuchElementException if {@code key} is not contained
   */
  default Value get(Key key)
    throws NoSuchElementException
  {
    // NOTE: Sometimes maps are thought of, rather than as storing items by key,
    //       instead as storing key/value mappings. In that case you may want to
    //       get the corresponding value for a given key, rather than find the
    //       item with a given key. For that, there's this!
    return this.find(key).value();
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  /**
   * Get an iterable that yields each key once.
   *
   * @return an iterable over the keys
   */
  default Iterable<Key> keys()
  {
    return Iterators.applyEach(this, MapItem::key);
  }


  /**
   * Get an iterable that yields each value once.
   *
   * @return an iterable over the values
   */
  default Iterable<Value> values()
  {
    return Iterators.applyEach(this, MapItem::value);
  }


  //</editor-fold>

}
