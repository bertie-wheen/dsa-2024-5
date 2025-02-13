package dsa.lab04.base;

import java.util.Objects;

/**
 * An item in a map.
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class MapItem<Key, Value>
{
  private Key key;
  private Value value;

  /**
   * Construct a map item.
   *
   * @param key   the key
   * @param value the value
   */
  public MapItem(Key key, Value value)
  {
    this.key = key;
    this.value = value;
  }

  /**
   * Get the key
   * (the unique/identifying part of the item).
   *
   * @return the key
   */
  public Key key()
  {
    return this.key;
  }

  /**
   * Get the value
   * (the rest of the item, or the corresponding value, depending on your view).
   *
   * @return the value
   */
  public Value value()
  {
    return this.value;
  }

  //<editor-fold defaultstate="collapsed" desc="equals() and hashCode()">

  @Override
  public boolean equals(Object that)
  {
    if (that == null || this.getClass() != that.getClass())
    {
      return false;
    }
    // NOTE: Don't worry too much about the ?s. The point is that, even though
    //       we've checked that `that` is a `MapItem`, we don't know what types
    //       its key and value are - they might be different that this object's,
    //       which is why don't write `MapItem<Key, Value>`. We could just write
    //       `MapItem` rather than `MapItem<?, ?>` but then we would get "raw
    //       use of parameterised class" warnings we'd have to suppress - and
    //       for various other reasons this is generally regarded as better.
    MapItem<?, ?> that_ = (MapItem<?, ?>) that;
    return Objects.equals(this.key, that_.key) &&
      Objects.equals(this.value, that_.value);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(this.key, this.value);
  }

  //</editor-fold>
}
