package dsa.lib;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static dsa.lib.Iterators.*;

public class Examples
{
  @SafeVarargs
  public static <T> Iterable<Arguments> arguments(
    Iterable<? extends T>... iterables)
  {
    return applyEach(Iterators.<Object>product(iterables), Arguments::of);
  }

  public static <T> Iterable<Arguments> andValidIndices(Iterable<T[]> arrays)
  {
    return andValidIndices(arrays, false);
  }

  public static <T> Iterable<Arguments> andValidInsertIndices(Iterable<T[]> arrays)
  {
    return andValidIndices(arrays, true);
  }

  public static <T> Iterable<Arguments> andValidIndices(
    Iterable<T[]> arrays,
    boolean sizeValid)
  {
    return () -> new Iterator<Arguments>()
    {
      private Iterator<T[]> arraysIterator = arrays.iterator();
      private T[] array = null;
      private int numerator = 0;

      {
        while ((this.array == null || this.array.length == 0)
          && this.arraysIterator.hasNext())
        {
          this.array = this.arraysIterator.next();
        }
      }

      @Override
      public boolean hasNext()
      {
        return this.array != null && this.array.length != 0;
      }

      private int index()
      {
        int size = sizeValid ? this.array.length : this.array.length - 1;
        return Math.round(size * this.numerator / 4f);
      }

      @Override
      public Arguments next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        int index = this.index();
        Arguments arrayAndIndex = Arguments.of(this.array, index);
        do
        {
          this.numerator++;
        }
        while (this.index() == index && this.numerator <= 4);
        if (this.numerator > 4)
        {
          this.numerator = 0;
          if (this.arraysIterator.hasNext())
          {
            do
            {
              this.array = this.arraysIterator.next();
            }
            while ((this.array == null || this.array.length == 0)
              && this.arraysIterator.hasNext());
          }
          else
          {
            this.array = null;
          }
        }
        return arrayAndIndex;
      }
    };
  }
}
