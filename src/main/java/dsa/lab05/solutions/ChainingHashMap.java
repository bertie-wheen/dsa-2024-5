package dsa.lab05.solutions;

import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lab04.solutions.ArrayMap;
import dsa.lib.Iterators;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A chaining hash map.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class ChainingHashMap<Key, Value>
  implements Map<Key, Value>
{

  private ArrayMap<Key, Value>[] chains;


  private HashFunction hashFunction;


  private int size = 0;


  private float maxLoadFactor = 2;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty chaining hash map.
   */
  @SuppressWarnings("unchecked")
  public ChainingHashMap()
  {
    this.chains =
      (ArrayMap<Key, Value>[]) new ArrayMap[1];
    this.chains[0] = new ArrayMap<>();
    this.hashFunction = new HashFunction(1);
  }


  /**
   * Construct a chaining hash map containing the given items.
   *
   * @param items the items
   */
  public ChainingHashMap(Iterable<MapItem<Key, Value>> items)
  {
    this();
    for (MapItem<Key, Value> item : items)
    {
      this.insert(item);
    }
  }


  /**
   * Construct a chaining hash map containing the given items
   * more efficiently than {@link #ChainingHashMap(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  @SuppressWarnings("unchecked")
  public ChainingHashMap(Iterable<MapItem<Key, Value>> items, int size)
    throws IllegalArgumentException
  {
    int chainCount =
      Math.max(1, (int) Math.ceil((float) size / this.maxLoadFactor));
    this.chains =
      (ArrayMap<Key, Value>[]) new ArrayMap[chainCount];
    for (int chainIndex = 0; chainIndex < chainCount; chainIndex++)
    {
      this.chains[chainIndex] = new ArrayMap<>();
    }
    this.hashFunction = new HashFunction(chainCount);
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
   * Construct a chaining hash map containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public ChainingHashMap(MapItem<Key, Value>... items)
  {
    this(Arrays.asList(items), items.length);
  }


  //</editor-fold>


  @Override
  public int size()
  {
    return this.size;
  }


  private ArrayMap<Key, Value> chain(Key key)
  {
    return this.chains[this.hashFunction.hash(key)];
  }


  @Override
  public MapItem<Key, Value> find(Key key)
    throws NoSuchElementException
  {
    return this.chain(key).find(key);
  }


  @SuppressWarnings("unchecked")
  private void resize(int chainCount)
  {
    ArrayMap<Key, Value>[] oldChains = this.chains;
    this.chains = (ArrayMap<Key, Value>[]) new ArrayMap[chainCount];
    for (int i = 0; i < chainCount; i++)
    {
      this.chains[i] = new ArrayMap<>();
    }
    this.hashFunction = new HashFunction(chainCount);
    this.size = 0;
    for (ArrayMap<Key, Value> chain : oldChains)
    {
      for (MapItem<Key, Value> item : chain)
      {
        this.insert(item);
      }
    }
  }


  @Override
  public void insert(MapItem<Key, Value> item)
  {
    ArrayMap<Key, Value> chain = this.chain(item.key());
    for (MapItem<Key, Value> containedItem : chain.items())
    {
      if (Objects.equals(item.key(), containedItem.key()))
      {
        chain.insert(item);
        return;
      }
    }
    if ((float) (this.size + 1) / this.chains.length > this.maxLoadFactor)
    {
      this.resize(this.chains.length * 2);
      this.insert(item);
    }
    else
    {
      this.size++;
      chain.insert(item);
    }
  }


  @Override
  public MapItem<Key, Value> remove(Key key)
    throws NoSuchElementException
  {
    MapItem<Key, Value> item = this.chain(key).remove(key);
    this.size--;
    int chainCount = this.chains.length;
    if ((float) this.size / chainCount < this.maxLoadFactor / 4)
    {
      this.resize(Math.max(1, chainCount / 2));
    }
    return item;
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  @Override
  public Iterable<MapItem<Key, Value>> items()
  {
    return Iterators.flatten(Arrays.asList(this.chains));
  }


  //</editor-fold>

}
