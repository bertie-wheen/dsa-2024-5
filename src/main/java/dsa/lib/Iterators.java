package dsa.lib;

import dsa.lab03.solutions.DynamicArray;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Iterators
{
  private Iterators()
  {
  }

  @SafeVarargs
  public static <T> Iterable<T> iterable(T... items)
  {
    return Arrays.asList(items);
  }

  @SafeVarargs
  public static <T> Iterator<T> iterator(T... items)
  {
    return iterable(items).iterator();
  }

  public static <T> Iterable<T> singletonIterable(T item)
  {
    return iterable(item);
  }

  public static <T> Iterator<T> singletonIterator(T item)
  {
    return iterator(item);
  }

  public static Iterable<Object[]> product(Iterable<?>... iterables)
  {
    int count = iterables.length;
    return () -> new Iterator<Object[]>()
    {
      private Iterator<?>[] iterators = new Iterator[count];
      private Object[] next;

      {
        boolean hasNext = count > 0;
        if (hasNext)
        {
          for (int i = 0; i < count; i++)
          {
            this.iterators[i] = iterables[i].iterator();
            if (!this.iterators[i].hasNext())
            {
              hasNext = false;
            }
          }
        }
        if (hasNext)
        {
          this.next = new Object[count];
          for (int i = 0; i < count; i++)
          {
            this.next[i] = this.iterators[i].next();
          }
        }
      }

      @Override
      public boolean hasNext()
      {
        return this.next != null;
      }

      @Override
      public Object[] next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        Object[] next = this.next;
        this.next = new Object[count];
        int i = count - 1;
        for (; i >= 0; i--)
        {
          boolean flip = !this.iterators[i].hasNext();
          if (flip)
          {
            if (i == 0)
            {
              this.next = null;
              break;
            }
            this.iterators[i] = iterables[i].iterator();
          }
          this.next[i] = this.iterators[i].next();
          if (!flip)
          {
            break;
          }
        }
        for (int j = 0; j < i; j++)
        {
          this.next[j] = next[j];
        }
        return next;
      }
    };
  }

  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <T> Iterable<T[]> product(
    Class<T> class_,
    Iterable<? extends T>... iterables)
  {
    int count = iterables.length;
    return () -> new Iterator<T[]>()
    {
      private Iterator<? extends T>[] iterators =
        (Iterator<? extends T>[]) new Iterator[count];
      private T[] next;

      {
        boolean hasNext = count > 0;
        if (hasNext)
        {
          for (int i = 0; i < count; i++)
          {
            this.iterators[i] = iterables[i].iterator();
            if (!this.iterators[i].hasNext())
            {
              hasNext = false;
            }
          }
        }
        if (hasNext)
        {
          this.next = (T[]) Array.newInstance(class_, count);
          for (int i = 0; i < count; i++)
          {
            this.next[i] = this.iterators[i].next();
          }
        }
      }

      @Override
      public boolean hasNext()
      {
        return this.next != null;
      }

      @Override
      public T[] next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        T[] next = this.next;
        this.next = (T[]) Array.newInstance(class_, count);
        int i = count - 1;
        for (; i >= 0; i--)
        {
          boolean flip = !this.iterators[i].hasNext();
          if (flip)
          {
            if (i == 0)
            {
              this.next = null;
              break;
            }
            this.iterators[i] = iterables[i].iterator();
          }
          this.next[i] = this.iterators[i].next();
          if (!flip)
          {
            break;
          }
        }
        for (int j = 0; j < i; j++)
        {
          this.next[j] = next[j];
        }
        return next;
      }
    };
  }

  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <T> Iterable<T> chain(Iterable<? extends T>... iterables)
  {
    return () ->
    {
      Iterator<? extends T>[] iterators =
        (Iterator<T>[]) new Iterator[iterables.length];
      for (int i = 0; i < iterables.length; i++)
      {
        iterators[i] = iterables[i].iterator();
      }
      return chain(iterators);
    };
  }

  @SafeVarargs
  public static <T> Iterator<T> chain(Iterator<? extends T>... iterators)
  {
    return new Iterator<T>()
    {
      private int index = 0;

      {
        while (this.index < iterators.length
          && !iterators[this.index].hasNext())
        {
          this.index++;
        }
      }

      @Override
      public boolean hasNext()
      {
        return this.index < iterators.length;
      }

      @Override
      public T next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        T next = iterators[this.index].next();
        while (this.index < iterators.length
          && !iterators[this.index].hasNext())
        {
          this.index++;
        }
        return next;
      }
    };
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

  public static <T> Iterator<T> reversed(Iterator<T> iterator)
  {
    return reversed(() -> iterator).iterator();
  }

  public static <T> Iterable<T> reversed(Iterable<T> iterable)
  {
    return new DynamicArray<>(iterable).reversed();
  }

  public static <T> Iterator<T> reversed(Iterator<T> iterator, int size)
    throws IllegalArgumentException
  {
    return reversed(() -> iterator, size).iterator();
  }

  public static <T> Iterable<T> reversed(Iterable<T> iterable, int size)
    throws IllegalArgumentException
  {
    return new DynamicArray<>(iterable, size).reversed();
  }

  public static <T> int fillArray(T[] array, Iterator<T> iterator)
  {
    return fillArray(array, () -> iterator);
  }

  public static <T> int fillArray(T[] array, Iterable<T> iterable)
  {
    int i = 0;
    for (T item : iterable)
    {
      if (i == array.length)
      {
        break;
      }
      array[i++] = item;
    }
    return i;
  }

  @SuppressWarnings("rawtypes")
  public static <T> Object[] toArray(Iterator iterator, int size)
  {
    return toArray(() -> iterator, size);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static <T> Object[] toArray(Iterable iterable, int size)
  {
    T[] array = (T[]) new Object[size];
    if (fillArray(array, (Iterable<Object>) iterable) != size)
    {
      throw new IllegalArgumentException();
    }
    return array;
  }

  public static <T> T[] toArray(Class<T> class_, Iterator<T> iterator, int size)
  {
    return toArray(class_, () -> iterator, size);
  }

  public static <T> T[] toArray(Class<T> class_, Iterable<T> iterable, int size)
  {
    @SuppressWarnings("unchecked")
    T[] array = (T[]) Array.newInstance(class_, size);
    if (fillArray(array, iterable) != size)
    {
      throw new IllegalArgumentException();
    }
    return array;
  }

  public static <T> Iterator<T> skipIndex(int index, Iterator<T> iterator)
  {
    return new Iterator<T>()
    {
      private int i = 0;

      {
        if (index == 0 && iterator.hasNext())
        {
          iterator.next();
          i++;
        }
      }

      @Override
      public boolean hasNext()
      {
        return iterator.hasNext();
      }

      @Override
      public T next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        T item = iterator.next();
        if (++i == index && iterator.hasNext())
        {
          iterator.next();
        }
        return item;
      }
    };
  }

  public static <T> Iterable<T> skipIndex(int index, Iterable<T> iterable)
  {
    return () -> skipIndex(index, iterable.iterator());
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
        }
        while (this.next == null && iterator.hasNext());
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
        }
        while (this.next == null && iterator.hasNext());
        return next;
      }
    };
  }

  public static <T> Iterable<T> nonNull(Iterable<T> iterable)
  {
    return () -> Iterators.nonNull(iterable.iterator());
  }
}
