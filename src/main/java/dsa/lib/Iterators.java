package dsa.lib;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

public final class Iterators
{
  private Iterators()
  {
  }


  public static <A, B> Iterable<B> applyEach(
    Iterable<A> iterable,
    Function<A, B> function)
  {
    return () ->
      new Iterator<B>()
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

  @SafeVarargs
  public static <T> List<T> asList(T... items)
  {
    return Arrays.asList(items);
  }

  public static <T> List<T> asList(Iterable<T> iterable)
  {
    List<T> list = new ArrayList<>();
    for (T item : iterable)
    {
      list.add(item);
    }
    return list;
  }

  public static <T> List<T> asList(Iterable<T> iterable, int size)
    throws IllegalArgumentException
  {
    List<T> list = new ArrayList<>(size);
    for (T item : iterable)
    {
      list.add(item);
    }
    if (list.size() != size)
    {
      throw new IllegalArgumentException();
    }
    return list;
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
    };
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

  public static <T> Iterable<T> flatten(
    Iterable<? extends Iterable<T>> iterable)
  {
    return () ->
      new Iterator<T>()
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

  @SafeVarargs
  public static <T> Iterable<T> iterable(T... items)
  {
    return Arrays.asList(items);
  }

  @SuppressWarnings("unchecked")
  private static <T> Function<Integer, T[]> newObjectArray()
  {
    return (size) -> (T[]) new Object[size];
  }

  @SuppressWarnings("unchecked")
  private static <T> Function<Integer, T[]> newTypedArray(Class<?> class_)
  {
    return (size) -> (T[]) Array.newInstance(class_, size);
  }

  public static <T> Iterable<T> nonNull(Iterable<T> iterable)
  {
    return () ->
      new Iterator<T>()
      {
        private Iterator<T> iterator = iterable.iterator();
        private T next;

        {
          do
          {
            this.next = this.iterator.hasNext() ? this.iterator.next() : null;
          }
          while (this.next == null && this.iterator.hasNext());
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
            this.next = this.iterator.hasNext() ? this.iterator.next() : null;
          }
          while (this.next == null && this.iterator.hasNext());
          return next;
        }
      };
  }

  @SafeVarargs
  public static <T> Iterable<T[]> product(Iterable<? extends T>... iterables)
  {
    return product(newObjectArray(), iterables);
  }

  @SafeVarargs
  public static <T> Iterable<T[]> product(
    Class<?> class_,
    Iterable<? extends T>... iterables)
  {
    return product(newTypedArray(class_), iterables);
  }

  @SafeVarargs
  private static <T> Iterable<T[]> product(
    Function<Integer, T[]> newArray,
    Iterable<? extends T>... iterables)
  {
    int count = iterables.length;
    if (count == 1)
    {
      return applyEach(
        iterables[0], (item) ->
        {
          T[] array = newArray.apply(1);
          array[0] = item;
          return array;
        });
    }
    return () -> new Iterator<T[]>()
    {
      @SuppressWarnings("unchecked")
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
          this.next = newArray.apply(count);
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
        this.next = newArray.apply(count);
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

  public static <T> Iterable<T> reversed(Iterable<T> iterable)
  {
    List<T> list = asList(iterable);
    Collections.reverse(list);
    return list;
  }

  public static <T> Iterable<T> reversed(Iterable<T> iterable, int size)
    throws IllegalArgumentException
  {
    List<T> list = asList(iterable, size);
    Collections.reverse(list);
    return list;
  }

  public static <T> Iterable<T> singletonIterable(T item)
  {
    return iterable(item);
  }

  public static <T> Iterable<T> skipIndex(int index, Iterable<T> iterable)
  {
    return () ->
      new Iterator<T>()
      {
        private Iterator<T> iterator = iterable.iterator();
        private int i = 0;

        {
          if (index == 0 && this.iterator.hasNext())
          {
            this.iterator.next();
            this.i++;
          }
        }

        @Override
        public boolean hasNext()
        {
          return this.iterator.hasNext();
        }

        @Override
        public T next()
        {
          if (!this.hasNext())
          {
            throw new NoSuchElementException();
          }
          T item = this.iterator.next();
          if (++this.i == index && this.iterator.hasNext())
          {
            this.iterator.next();
          }
          return item;
        }
      };
  }

  public static <T extends Comparable<T>> Iterable<T> sorted(
    Iterable<T> iterable)
  {
    List<T> sorted = asList(iterable);
    int size = sorted.size();
    T[] temp = toArray(Comparable.class, sorted, size);
    Arrays.sort(temp, Comparator.nullsFirst(Comparator.naturalOrder()));
    for (int i = 0; i < size; i++)
    {
      sorted.set(i, temp[i]);
    }
    return sorted;
  }

  public static <T extends Comparable<T>> Iterable<T> sortedUniques(
    Iterable<T> iterable)
  {
    return sorted(uniques(iterable));
  }

  public static <T extends Comparable<T>> T[] sortedUniques(T[] array)
  {
    return toArray(Comparable.class, sortedUniques(asList(array)));
  }

  public static <T extends Comparable<T>> Iterable<T[]> sortedUniquesEach(
    Iterable<T[]> iterable)
  {
    return applyEach(iterable, Iterators::sortedUniques);
  }

  public static <T> Object[] toArray(Iterable<T> iterable)
  {
    List<T> list = asList(iterable);
    return toArray(list, list.size());
  }

  public static <T> T[] toArray(Iterable<T> iterable, int size)
    throws IllegalArgumentException
  {
    return toArray(newObjectArray(), iterable, size);
  }

  public static <T> T[] toArray(Class<?> class_, Iterable<T> iterable)
  {
    List<T> list = asList(iterable);
    return toArray(class_, list, list.size());
  }

  public static <T> T[] toArray(Class<?> class_, Iterable<T> iterable, int size)
    throws IllegalArgumentException
  {
    return toArray(newTypedArray(class_), iterable, size);
  }

  private static <T> T[] toArray(
    Function<Integer, T[]> newArray,
    Iterable<T> iterable,
    int size)
    throws IllegalArgumentException
  {
    T[] array = newArray.apply(size);
    if (fillArray(array, iterable) != size)
    {
      throw new IllegalArgumentException();
    }
    return array;
  }

  public static <T> Iterable<T> uniques(Iterable<T> iterable)
  {
    List<T> uniques = new ArrayList<>();
    for (T item : iterable)
    {
      if (!uniques.contains(item))
      {
        uniques.add(item);
      }
    }
    return uniques;
  }

  @SuppressWarnings("unchecked")
  public static <T> T[] uniques(T[] array)
  {
    return (T[]) toArray(uniques(asList(array)));
  }

  public static <T> T[] uniques(Class<?> class_, T[] array)
  {
    return toArray(class_, uniques(asList(array)));
  }

  public static <T> Iterable<T[]> uniquesEach(Iterable<T[]> iterable)
  {
    return applyEach(iterable, Iterators::uniques);
  }
}
