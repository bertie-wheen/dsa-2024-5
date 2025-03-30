package dsa.lab05.solutions;

import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.base.MapItem;
import dsa.lab04.solutions.MergeSorter;
import dsa.lab05.base.OrderedMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A sorted array map.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class SortedArrayMap<Key extends Comparable<Key>, Value>
  implements OrderedMap<Key, Value>
{

  private DynamicArray<MapItem<Key, Value>> items;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty sorted array map.
   */
  public SortedArrayMap()
  {
    this.items = new DynamicArray<>();
  }


  /**
   * Construct a sorted array map containing the given items.
   *
   * @param items the items
   */
  public SortedArrayMap(Iterable<MapItem<Key, Value>> items)
  {
    this.items = new DynamicArray<>(items);
    new MergeSorter().sort(this.items, Comparator.comparing(MapItem::key));
  }


  /**
   * Construct a sorted array map containing the given items
   * more efficiently than {@link #SortedArrayMap(Iterable)}.
   *
   * @param items the items
   * @param size  the number of items
   * @throws IllegalArgumentException if {@code size} != {@code n}
   *                                  (where {@code n} is {@code items}'s size)
   */
  public SortedArrayMap(Iterable<MapItem<Key, Value>> items, int size)
    throws IllegalArgumentException
  {
    this.items = new DynamicArray<>(items, size);
    new MergeSorter().sort(this.items, Comparator.comparing(MapItem::key));
  }


  /**
   * Construct a sorted array map containing the given items.
   *
   * @param items the items
   */
  @SafeVarargs
  public SortedArrayMap(MapItem<Key, Value>... items)
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
   * Get the index for an item with the given key.
   * <p>
   * If no currently-contained item has the given key,
   * returns where such an item would be if it were.
   *
   * @param key the key to find the index for
   * @return the index for {@code key}
   */
  private int indexFor(Key key)
  {
    return this.indexFor(key, 0, this.size());
  }


  /**
   * Get the index for an item with the given key in the given slice.
   * <p>
   * If there is an item with the given key between indices {@code start}
   * inclusive and {@code stop} exclusive, then get that index; else get the
   * index of where such an item would be if it were stored in the map.
   *
   * @param key   the searched-for key
   * @param start the (inclusive) start index of {@code items} to search
   * @param stop  the (exclusive) stop index of {@code items} to search
   * @return the index of the item
   *         (within {@code items}, between {@code start} and {@code stop})
   *         with the smallest key greater than or equal to {@code key},
   *         or {@code stop} if {@code start} {@literal ==} {@code stop}
   */
  private int indexFor(Key key, int start, int stop)
  {
    // NOTE: This corresponds to BinarySearchMap in the lecture slides.
    // NOTE: Compare this to BinarySearcher.search() - it's very much the same,
    //       just with a different base case if the key is not found (instead of
    //       returning -1 to indicate this, it returns the index reached, which
    //       is where an item with such a key should be - this will be useful
    //       particularly in insert(), which will then insert such an item
    //       at that index, as well as in previous() and next()).

    if (start >= stop)
    {
      return start;
    }

    int middleIndex = (start + stop) / 2;
    Key middleKey = this.items.get(middleIndex).key();

    int comparison = key.compareTo(middleKey);

    if (comparison < 0)
    {
      return this.indexFor(key, start, middleIndex);
    }

    if (comparison > 0)
    {
      return this.indexFor(key, middleIndex + 1, stop);
    }

    return middleIndex;
  }


  /**
   * Get the item at the given index, or {@code null} if it's out of bounds.
   *
   * @param index the index
   * @return the item (or {@code null} if {@code index} is out of bounds)
   */
  private MapItem<Key, Value> item(int index)
  {
    return index < 0 || index >= this.size() ? null : this.items.get(index);
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
    int index = this.indexFor(key);
    MapItem<Key, Value> item = this.item(index);
    return item == null || !Objects.equals(key, item.key()) ? -1 : index;
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
    int index = this.indexFor(item.key());
    if (index == this.size() || !Objects.equals(
      item.key(),
      this.items.get(index).key()))
    {
      this.items.insert(index, item);
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


  @Override
  public MapItem<Key, Value> previous(Key key)
  {
    return this.item(this.indexFor(key) - 1);
  }


  @Override
  public MapItem<Key, Value> next(Key key)
  {
    int index = this.indexFor(key);
    MapItem<Key, Value> item = this.item(index);
    if (item != null && Objects.equals(key, item.key()))
    {
      return this.item(index + 1);
    }
    return item;
  }


  @Override
  public MapItem<Key, Value> min()
  {
    return this.isEmpty() ? null : this.items.first();
  }


  @Override
  public MapItem<Key, Value> max()
  {
    return this.isEmpty() ? null : this.items.last();
  }


  //<editor-fold defaultstate="collapsed" desc="Iteration">


  @Override
  public Iterable<MapItem<Key, Value>> items()
  {
    return this.items.items();
  }


  @Override
  public Iterable<MapItem<Key, Value>> reversed()
  {
    return this.items.reversed();
  }


  //</editor-fold>

}
