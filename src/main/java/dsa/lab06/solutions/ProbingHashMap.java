package dsa.lab06.solutions;

import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lab05.solutions.HashFunction;
import dsa.lib.Iterators;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A (linearly-)probing hash map.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class ProbingHashMap<Key, Value>
  implements Map<Key, Value>
{
  private final MapItem<Key, Value> REMOVED = new MapItem<>(null, null);
  private MapItem<Key, Value>[] items;
  private HashFunction hashFunction;
  private int size = 0;
  private float maxLoadFactor = 0.8f;

  //<editor-fold defaultstate="collapsed" desc="Constructors">

  /**
   * Construct an empty probing hash map.
   */
  @SuppressWarnings("unchecked")
  public ProbingHashMap()
  {
    this.items = (MapItem<Key, Value>[]) new MapItem[1];
    this.hashFunction = new HashFunction(1);
  }

  /**
   * Construct a probing hash map containing the given items.
   *
   * @param items the items
   */
  public ProbingHashMap(Iterable<MapItem<Key, Value>> items)
  {
    this();
    for (MapItem<Key, Value> item : items)
    {
      this.insert(item);
    }
  }

  /**
   * Construct a probing hash map containing the given items
   * more efficiently than {@link #ProbingHashMap(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  @SuppressWarnings("unchecked")
  public ProbingHashMap(Iterable<MapItem<Key, Value>> items, int size)
    throws IllegalArgumentException
  {
    int capacity =
      Math.max(1, (int) Math.ceil((float) size / this.maxLoadFactor));
    this.items = (MapItem<Key, Value>[]) new MapItem[capacity];
    this.hashFunction = new HashFunction(capacity);
    int itemsSize = 0;
    for (MapItem<Key, Value> item : items)
    {
      this.insert(item);
      itemsSize++;
    }
    if (itemsSize != size)
    {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Construct a probing hash map containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public ProbingHashMap(MapItem<Key, Value>... items)
  {
    this(Arrays.asList(items), items.length);
  }

  //</editor-fold>

  @Override
  public int size()
  {
    return this.size;
  }

  @SuppressWarnings("unchecked")
  private void resize(int slotCount)
  {
    MapItem<Key, Value>[] oldItems = this.items;
    this.items = (MapItem<Key, Value>[]) new MapItem[slotCount];
    this.hashFunction = new HashFunction(slotCount);
    this.size = 0;
    for (MapItem<Key, Value> item : oldItems)
    {
      if (item != null && item != this.REMOVED)
      {
        this.insert(item);
      }
    }
  }

  @Override
  public void insert(MapItem<Key, Value> newItem)
  {
    int idealIndex = this.hashFunction.hash(newItem.key());
    int slotCount = this.items.length;
    int availableIndex = -1;
    for (int offset = 0; offset < slotCount; offset++)
    {
      int index = (idealIndex + offset) % slotCount;
      MapItem<Key, Value> item = this.items[index];
      if (item == null)
      {
        availableIndex = index;
        break;
      }
      if (item == this.REMOVED)
      {
        if (availableIndex == -1)
        {
          availableIndex = index;
        }
      }
      else if (newItem.key().equals(item.key()))
      {
        this.items[index] = newItem;
        return;
      }
    }
    if ((float) (this.size + 1) / slotCount > this.maxLoadFactor)
    {
      this.resize(slotCount * 2);
      this.insert(newItem);
    }
    else
    {
      this.items[availableIndex] = newItem;
      this.size++;
    }
  }

  @Override
  public MapItem<Key, Value> find(Key key)
    throws NoSuchElementException
  {
    int idealIndex = this.hashFunction.hash(key);
    int itemsSize = this.items.length;
    for (int offset = 0; offset < itemsSize; offset++)
    {
      int index = (idealIndex + offset) % itemsSize;
      MapItem<Key, Value> item = this.items[index];
      if (item == null)
      {
        break;
      }
      if (key.equals(item.key()))
      {
        return item;
      }
    }
    throw new NoSuchElementException();
  }

  @Override
  public MapItem<Key, Value> remove(Key key)
    throws NoSuchElementException
  {
    int idealIndex = this.hashFunction.hash(key);
    int slotCount = this.items.length;
    for (int offset = 0; offset < slotCount; offset++)
    {
      int index = (idealIndex + offset) % slotCount;
      MapItem<Key, Value> item = this.items[index];
      if (item == null)
      {
        break;
      }
      if (key.equals(item.key()))
      {
        this.items[index] = this.REMOVED;
        this.size--;
        if ((float) this.size / slotCount < this.maxLoadFactor / 4)
        {
          this.resize(Math.max(1, slotCount / 2));
        }
        return item;
      }
    }
    throw new NoSuchElementException();
  }

  //<editor-fold defaultstate="collapsed" desc="Iteration">

  @Override
  public Iterable<MapItem<Key, Value>> items()
  {
    return Iterators.filter(
      (item) -> item != null && item != this.REMOVED,
      Arrays.asList(this.items));
  }

  //</editor-fold>
}
