package dsa.lab05.base;

import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lib.Iterators;

/**
 * An ordered map.
 * <p>
 * A map whose {@code n} items have comparable keys,
 * that is, have keys that can be ordered from least to greatest.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public interface OrderedMap<Key extends Comparable<Key>, Value>
  extends Map<Key, Value>
{

  /**
   * Get the item that would be the predecessor of one with the given key.
   * <p>
   * The map may or may not contain an item with the given key.
   *
   * @param key a key
   * @return the previous item (by key), or {@code null} if there is none
   */
  MapItem<Key, Value> previous(Key key);


  /**
   * Get the item that would be the successor of one with the given key.
   * <p>
   * The map may or may not contain an item with the given key.
   *
   * @param key a key
   * @return the next item (by key), or {@code null} if there is none
   */
  MapItem<Key, Value> next(Key key);


  /**
   * Get the item with the least key.
   *
   * @return the minimum item (by key), or {@code null} if there is none
   */
  MapItem<Key, Value> min();


  /**
   * Get the item with the greatest key.
   *
   * @return the maximum item (by key), or {@code null} if there is none
   */
  MapItem<Key, Value> max();


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  /**
   * Get a forward iterable that yields each item once.
   * <p>
   * The items are iterated over in least-to-greatest order (by key).
   *
   * @return an iterable over the items
   */
  @Override
  Iterable<MapItem<Key, Value>> items();


  /**
   * Get a reverse iterable that yields each item once.
   * <p>
   * The items are iterated over in greatest-to-least order (by key).
   *
   * @return an iterable over the items
   */
  Iterable<MapItem<Key, Value>> reversed();


  /**
   * Get a forward iterable that yields each key once.
   * <p>
   * The items are iterated over in least-to-greatest order (by key).
   *
   * @return an iterable over the keys
   */
  @Override
  default Iterable<Key> keys()
  {
    return Iterators.applyEach(this, MapItem::key);
  }


  /**
   * Get a reverse iterable that yields each key once.
   * <p>
   * The items are iterated over in greatest-to-least order (by key).
   *
   * @return an iterable over the keys
   */
  default Iterable<Key> reversedKeys()
  {
    return Iterators.applyEach(this.reversed(), MapItem::key);
  }


  //</editor-fold>

}
