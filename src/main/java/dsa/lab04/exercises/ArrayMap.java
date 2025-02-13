package dsa.lab04.exercises;

import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lib.TODO;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * An (unsorted) array map.
 * <p>
 * A map implemented using a dynamic array of key-value items.
 * <p>
 * {@link #find}, {@link #insert} and {@link #remove} are all O({@code n}),
 * where {@code n} is the size.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class ArrayMap<Key, Value>
  implements Map<Key, Value>
{
  private DynamicArray<MapItem<Key, Value>> items;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty array map.
   */
  public ArrayMap()
  {
    this.items = new DynamicArray<>();
  }

  /**
   * Construct an array map containing the given items.
   *
   * @param items the items
   */
  public ArrayMap(Iterable<MapItem<Key, Value>> items)
  {
    this.items = new DynamicArray<>(items);
  }

  /**
   * Construct an array map containing the given items
   * more efficiently than {@link #ArrayMap(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public ArrayMap(Iterable<MapItem<Key, Value>> items, int size)
    throws IllegalArgumentException
  {
    this.items = new DynamicArray<>(items, size);
  }

  /**
   * Construct an array map containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public ArrayMap(MapItem<Key, Value>... items)
  {
    this(Arrays.asList(items));
  }

  //</editor-fold>

  @Override
  public int size()
  {
    return this.items.size();
  }

  /**
   * Get the index of the item with the given key.
   * <p>
   * If no item has the given key, returns -1.
   *
   * @param key the key to find the index of
   * @return the index of {@code key} (or {@code -1} if there isn't one)
   */
  private int indexOf(Key key)
  {
    // TODO: Implement ArrayMap.indexOf(Key key)
    throw new TODO();
  }

  @Override
  public MapItem<Key, Value> find(Key key)
    throws NoSuchElementException
  {
    int index = this.indexOf(key);
    if (index == -1)
    {
      throw new NoSuchElementException();
    }
    return this.items.get(index);
  }

  @Override
  public void insert(MapItem<Key, Value> item)
  {
    int index = this.indexOf(item.key());
    if (index == -1)
    {
      this.items.insertLast(item);
    }
    else
    {
      this.items.set(index, item);
    }
  }

  @Override
  public MapItem<Key, Value> remove(Key key)
    throws NoSuchElementException
  {
    int index = this.indexOf(key);
    if (index == -1)
    {
      throw new NoSuchElementException();
    }
    return this.items.remove(index);
  }

  //<editor-fold defaultstate="collapsed" desc="Iteration">

  @Override
  public Iterable<MapItem<Key, Value>> items()
  {
    return this.items.items();
  }

  //</editor-fold>
}
