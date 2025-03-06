package dsa.lib;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

import static dsa.lib.Iterators.*;

public class Examples
{
  @SafeVarargs
  public static <T> Iterable<Arguments> arguments(
    Iterable<? extends T>... iterables)
  {
    return applyEach(
      Iterators.<Object>product(iterables),
      t -> Arguments.of(t));
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

  public static <T> Iterable<Arguments> andContainedItems(Iterable<T[]> arrays)
  {
    return andOnValidIndices(
      arrays,
      (array, index) -> Arguments.of(array, array[index]));
  }

  public static <A, B> Iterable<B> andOnValidIndices(
    Iterable<A[]> arrays,
    BiFunction<A[], Integer, B> argumentsOf)
  {
    return () -> new Iterator<B>()
    {
      private Iterator<A[]> arraysIterator = arrays.iterator();
      private A[] array = null;
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
        return Math.round((this.array.length - 1) * this.numerator / 4f);
      }

      @Override
      public B next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        int index = this.index();
        B arrayAndItem = argumentsOf.apply(this.array, index);
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
        return arrayAndItem;
      }
    };
  }

  public static Arguments flatten(Arguments[] array)
  {
    return Arguments.of(toArray(
      Object.class, Iterators.flatten(applyEach(
        iterable(array),
        arguments -> iterable(arguments.get())))));
  }

  @SafeVarargs
  public static Iterable<Arguments> flatProduct(
    Iterable<Arguments>... iterables)
  {
    return onProduct(Arguments.class, Examples::flatten, iterables);
  }
}
