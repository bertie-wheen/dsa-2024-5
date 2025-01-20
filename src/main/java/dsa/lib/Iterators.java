package dsa.lib;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Iterators
{
  private Iterators()
  {
  }

  public static <A, B> Iterator<B> applyEach(
    Iterator<A> iterator,
    Function<A, B> function)
  {
    return new Iterator<B>()
    {
      @Override
      public boolean hasNext()
      {
        return iterator.hasNext();
      }

      @Override
      public B next()
      {
        return function.apply(iterator.next());
      }
    };
  }

  public static <A, B> Iterable<B> applyEach(
    Iterable<A> iterable,
    Function<A, B> function)
  {
    return () -> Iterators.applyEach(iterable.iterator(), function);
  }

  public static <T> Iterator<T> flatten(Iterator<? extends Iterable<T>> iterator)
  {
    return new Iterator<T>()
    {
      private Iterator<T> innerIterator;

      {
        do
        {
          this.innerIterator =
            iterator.hasNext() ? iterator.next().iterator() : null;
        }
        while (this.innerIterator != null && !this.innerIterator.hasNext());
      }

      @Override
      public boolean hasNext()
      {
        return this.innerIterator != null;
      }

      @Override
      public T next()
      {
        if (this.innerIterator == null)
        {
          throw new NoSuchElementException();
        }
        T next = this.innerIterator.next();
        while (this.innerIterator != null && !this.innerIterator.hasNext())
        {
          this.innerIterator =
            iterator.hasNext() ? iterator.next().iterator() : null;
        }
        return next;
      }
    };
  }

  public static <T> Iterable<T> flatten(Iterable<? extends Iterable<T>> iterable)
  {
    return () -> Iterators.flatten(iterable.iterator());
  }

  public static <T> Iterator<T> nonNull(
    Iterator<T> iterator)
  {
    return new Iterator<T>()
    {
      private T next;

      {
        do
        {
          this.next = iterator.hasNext() ? iterator.next() : null;
        } while (this.next == null && iterator.hasNext());
      }

      @Override
      public boolean hasNext()
      {
        return this.next != null;
      }

      @Override
      public T next()
      {
        if (this.next == null)
        {
          throw new NoSuchElementException();
        }
        T next = this.next;
        do
        {
          this.next = iterator.hasNext() ? iterator.next() : null;
        } while (this.next == null && iterator.hasNext());
        return next;
      }
    };
  }

  public static <T> Iterable<T> nonNull(Iterable<T> iterable)
  {
    return () -> Iterators.nonNull(iterable.iterator());
  }
}
