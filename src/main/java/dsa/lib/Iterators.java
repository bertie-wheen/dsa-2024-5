package dsa.lib;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("UnnecessaryBoxing")  // (it's to satisfy corretto-1.8)
public final class Iterators
{

  private Iterators()
  {
  }


  // applyEach(iterable, function)
  // reversed(items)
  // reversed(items, size)
  // flatten(iterable)
  // filter(predicate, iterable)


  public static <A, B> Iterable<B> applyEach(
    Iterable<A> iterable,
    Function<A, B> function)
  {
    return () -> new Iterator<B>()
    {
      private Iterator<A> iterator = iterable.iterator();


      @Override
      public boolean hasNext()
      {
        return this.iterator.hasNext();
      }


      @Override
      public B next()
      {
        return function.apply(this.iterator.next());
      }
    };
  }


  @SuppressWarnings("AssignmentUsedAsCondition")
  public static <T> Iterable<T> filter(
    Predicate<T> predicate,
    Iterable<T> iterable)
  {
    return () -> new Iterator<T>()
    {
      private Iterator<T> iterator = iterable.iterator();


      private T next = null;


      private boolean hasNext = false;


      {
        while (this.iterator.hasNext())
        {
          this.next = this.iterator.next();
          if (this.hasNext = predicate.test(this.next))
          {
            break;
          }
        }
      }


      @Override
      public boolean hasNext()
      {
        return this.hasNext;
      }


      @Override
      public T next()
      {
        if (!this.hasNext)
        {
          throw new NoSuchElementException();
        }
        T next = this.next;
        this.hasNext = false;
        while (this.iterator.hasNext())
        {
          this.next = this.iterator.next();
          if (this.hasNext = predicate.test(this.next))
          {
            break;
          }
        }
        return next;
      }
    };
  }


  public static <T> Iterable<T> flatten(
    Iterable<? extends Iterable<T>> iterable)
  {
    return () -> new Iterator<T>()
    {
      private Iterator<? extends Iterable<T>> iterator = iterable.iterator();


      private Iterator<T> innerIterator;


      {
        do
        {
          this.innerIterator =
            this.iterator.hasNext() ? this.iterator.next().iterator() : null;
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
            this.iterator.hasNext() ? this.iterator.next().iterator() : null;
        }
        return next;
      }
    };
  }


  public static <T> Iterable<T> reversed(Iterable<T> iterable)
  {
    return Source.from(iterable).reversed();
  }


  public static <T> Iterable<T> reversed(Iterable<T> iterable, int ignored)
  {
    return Source.from(iterable).reversed();
  }

}
