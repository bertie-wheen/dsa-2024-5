package dsa.lib;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayIterator<Item>
  implements Iterator<Item>
{

  private Item[] items;


  private int index = 0;


  public ArrayIterator(Item[] items)
  {
    this.items = items;
  }


  @Override
  public boolean hasNext()
  {
    return this.index < this.items.length;
  }


  @Override
  public Item next()
  {
    if (!this.hasNext())
    {
      throw new NoSuchElementException();
    }
    return this.items[this.index++];
  }

}
