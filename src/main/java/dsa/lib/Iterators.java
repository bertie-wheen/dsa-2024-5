package dsa.lib;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("UnnecessaryBoxing")  // (it's to satisfy corretto-1.8)
public final class Iterators
{
  private Iterators()
  {
  }


  public static <A, B> Iterable<B> applyEach(
    Iterable<A> iterable,
    BiFunction<A, Integer, B> function)
  {
    return () -> new Iterator<B>()
    {
      private Iterator<A> iterator = iterable.iterator();
      private int index = 0;

      @Override
      public boolean hasNext()
      {
        return this.iterator.hasNext();
      }

      @Override
      public B next()
      {
        return function.apply(
          this.iterator.next(),
          Integer.valueOf(this.index++));
      }
    };
  }

  public static <A, B> Iterable<B> applyEach(
    Iterable<A> iterable,
    Function<A, B> function)
  {
    return applyEach(iterable, (item, index) -> function.apply(item));
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

  public static <T> Iterable<T> atMost(int maxSize, Iterable<T> iterable)
  {
    return () -> new Iterator<T>()
    {
      private Iterator<T> iterator = iterable.iterator();
      private int size = 0;

      @Override
      public boolean hasNext()
      {
        return this.size < maxSize && this.iterator.hasNext();
      }

      @Override
      public T next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        this.size++;
        return this.iterator.next();
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

  @SuppressWarnings("unchecked")
  public static <T> T[] cycle(Class<?> class_, T[] array, int offset)
  {
    if (offset == 0)
    {
      return array;
    }
    int size = array.length;
    T[] newArray = (T[]) newTypedArray(class_).apply(size);
    for (int i = 0; i < size; i++)
    {
      newArray[i] = array[(i + offset) % size];
    }
    return newArray;
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

  /*
  public static <T> T[] flatten(
    T[][] arrays)
  {
    return toArray(flatten(applyEach(
      iterable(arrays),
      (array) -> iterable(array))));
  }

  public static <T> T[] flatten(
    Class<?> class_,
    T[][] arrays)
  {
    return toArray(class_, flatten(applyEach(
      iterable(arrays),
      (array) -> iterable(array))));
  }
   */

  public static <T, G> Iterable<Iterable<T>> group(
    Iterable<T> items,
    Function<T, G> grouper)
  {
    HashMap<G, Iterable<T>> map = new HashMap<>();
    for (T item : items)
    {
      G group = grouper.apply(item);
      map.putIfAbsent(group, new ArrayList<>());
      ((List<T>) map.get(group)).add(item);
    }
    return map.values();
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
    return () -> new Iterator<T>()
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

  public static <T> Iterable<T> onlyEvery(
    int start, int step,
    Iterable<T> iterable)
  {
    return () -> new Iterator<T>()
    {
      private Iterator<T> iterator = iterable.iterator();

      {
        for (int i = 0; i < start && this.iterator.hasNext(); i++)
        {
          this.iterator.next();
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
        T next = this.iterator.next();
        for (int i = 1; i < step && this.iterator.hasNext(); i++)
        {
          this.iterator.next();
        }
        return next;
      }
    };
  }

  public static <T> Iterable<T> onlyEvery(int step, Iterable<T> iterable)
  {
    return onlyEvery(0, step, iterable);
  }

  public static <T> T[] onlyEvery(int start, int step, T[] array)
  {
    return toArray(onlyEvery(start, step, iterable(array)));
  }

  public static <T> T[] onlyEvery(int step, T[] array)
  {
    return toArray(onlyEvery(step, iterable(array)));
  }

  public static <T> T[] onlyEvery(
    Class<?> class_,
    int start,
    int step,
    T[] array)
  {
    return toArray(class_, onlyEvery(start, step, iterable(array)));
  }

  public static <T> T[] onlyEvery(Class<?> class_, int step, T[] array)
  {
    return toArray(class_, onlyEvery(step, iterable(array)));
  }

  @SafeVarargs
  public static <A, B> Iterable<B> onProduct(
    Function<A[], B> function,
    Iterable<? extends A>... iterables)
  {
    return onProduct(newObjectArray(), function, iterables);
  }

  @SafeVarargs
  public static <A, B> Iterable<B> onProduct(
    Class<?> class_,
    Function<A[], B> function,
    Iterable<? extends A>... iterables)
  {
    return onProduct(newTypedArray(class_), function, iterables);
  }

  @SafeVarargs
  private static <A, B> Iterable<B> onProduct(
    Function<Integer, A[]> newArray,
    Function<A[], B> function,
    Iterable<? extends A>... iterables)
  {
    int count = iterables.length;
    if (count == 1)
    {
      return applyEach(
        iterables[0], (item) ->
        {
          A[] array = newArray.apply(Integer.valueOf(1));
          array[0] = item;
          return function.apply(array);
        });
    }
    return () -> new Iterator<B>()
    {
      @SuppressWarnings("unchecked")
      private Iterator<? extends A>[] iterators =
        (Iterator<? extends A>[]) new Iterator[count];
      private A[] next;

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
          this.next = newArray.apply(Integer.valueOf(count));
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
      public B next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        A[] next = this.next;
        this.next = newArray.apply(Integer.valueOf(count));
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
        return function.apply(next);
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
          T[] array = newArray.apply(Integer.valueOf(1));
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
          this.next = newArray.apply(Integer.valueOf(count));
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
        this.next = newArray.apply(Integer.valueOf(count));
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

  public static <T> Iterable<T> repeat(int n, Iterable<T> iterable)
  {
    return () -> new Iterator<T>()
    {
      private int i = 0;
      private Iterator<T> iterator = iterable.iterator();
      private T next = null;

      {
        if (this.iterator.hasNext())
        {
          this.next = this.iterator.next();
        }
        else
        {
          this.i = n;
        }
      }

      @Override
      public boolean hasNext()
      {
        return this.iterator.hasNext() || this.i < n;
      }

      @Override
      public T next()
      {
        if (!this.hasNext())
        {
          throw new NoSuchElementException();
        }
        T next = this.next;
        if (++this.i == n && this.iterator.hasNext())
        {
          this.i = 0;
          this.next = this.iterator.next();
        }
        return next;
      }
    };
  }

  public static <T> Iterable<T[]> repeatCycled(
    Class<?> class_,
    int n,
    Iterable<T[]> iterable)
  {
    return applyEach(
      repeat(n, iterable),
      (array, index) -> cycle(class_, array, index % n));
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

  public static <T> int size(Iterable<T> iterable)
  {
    int size = 0;
    for (T ignored : iterable)
    {
      size++;
    }
    return size;
  }

  public static <T> T[] skipIndex(int index, T[] array)
  {
    return toArray(skipIndex(index, iterable(array)), array.length - 1);
  }

  public static <T> T[] skipIndex(Class<?> class_, int index, T[] array)
  {
    return toArray(class_, skipIndex(index, iterable(array)), array.length - 1);
  }

  public static <T> Iterable<T> skipIndex(int index, Iterable<T> iterable)
  {
    return () -> new Iterator<T>()
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
    return sorted(iterable, Comparator.nullsFirst(Comparator.naturalOrder()));
  }

  public static <T> Iterable<T> sorted(
    Iterable<T> iterable, Comparator<T> comparator)
  {
    List<T> sorted = asList(iterable);
    int size = sorted.size();
    T[] temp = toArray(sorted, size);
    Arrays.sort(temp, comparator);
    for (int i = 0; i < size; i++)
    {
      sorted.set(i, temp[i]);
    }
    return sorted;
  }

  public static <T extends Comparable<T>> T[] sorted(T[] array)
  {
    return sorted(array, Comparator.nullsFirst(Comparator.naturalOrder()));
  }

  public static <T> T[] sorted(
    T[] array,
    Comparator<T> comparator)
  {
    T[] sorted = array.clone();
    Arrays.sort(sorted, comparator);
    return sorted;
  }

  public static <T extends Comparable<T>> Iterable<T[]> sortedEach(Iterable<T[]> arrays)
  {
    return applyEach(arrays, (array) -> Iterators.sorted(array));
  }

  public static <T> Iterable<T[]> sortedEach(
    Iterable<T[]> arrays,
    Comparator<T> comparator)
  {
    return applyEach(arrays, (array) -> Iterators.sorted(array, comparator));
  }

  public static <T extends Comparable<T>> Iterable<T> sortedUniques(
    Iterable<T> iterable)
  {
    return sorted(uniques(iterable));
  }

  public static <T> Iterable<T> sortedUniques(
    Iterable<T> iterable,
    Comparator<T> comparator)
  {
    return sorted(
      uniques(iterable, (a, b) -> comparator.compare(a, b) == 0),
      comparator);
  }

  public static <T extends Comparable<T>> T[] sortedUniques(
    Class<?> class_,
    T[] array)
  {
    return toArray(class_, sortedUniques(asList(array)));
  }

  public static <T extends Comparable<T>> T[] sortedUniques(T[] array)
  {
    return sortedUniques(Comparable.class, array);
  }

  public static <T> T[] sortedUniques(
    T[] array,
    Comparator<T> comparator)
  {
    return toArray(sortedUniques(asList(array), comparator));
  }

  public static <T> T[] sortedUniques(
    Class<?> class_,
    T[] array,
    Comparator<T> comparator)
  {
    return toArray(class_, sortedUniques(asList(array), comparator));
  }

  public static <T extends Comparable<T>> Iterable<T[]> sortedUniquesEach(
    Iterable<T[]> iterable)
  {
    return applyEach(iterable, (array) -> sortedUniques(array));
  }

  public static <T extends Comparable<T>> Iterable<T[]> sortedUniquesEach(
    Class<?> class_,
    Iterable<T[]> iterable)
  {
    return applyEach(iterable, (array) -> sortedUniques(class_, array));
  }

  public static <T> Iterable<T[]> sortedUniquesEach(
    Iterable<T[]> iterable,
    Comparator<T> comparator)
  {
    return applyEach(iterable, (array) -> sortedUniques(array, comparator));
  }

  public static <T> Iterable<T[]> sortedUniquesEach(
    Class<?> class_,
    Iterable<T[]> iterable,
    Comparator<T> comparator)
  {
    return applyEach(
      iterable,
      (array) -> sortedUniques(class_, array, comparator));
  }

  public static <T> T[] toArray(Iterable<T> iterable)
  {
    List<T> list = asList(iterable);
    return toArray(list, list.size());
  }

  public static <T> T[] toArray(Iterable<T> iterable, int size)
    throws IllegalArgumentException
  {
    return toArray(newObjectArray(), iterable, size);
  }

  @SuppressWarnings("unchecked")
  public static <A, B> B[] toArray(Class<?> class_, A[] array)
  {
    B[] newArray = (B[]) newTypedArray(class_).apply(array.length);
    System.arraycopy(array, 0, newArray, 0, array.length);
    return newArray;
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
    T[] array = newArray.apply(Integer.valueOf(size));
    if (fillArray(array, iterable) != size)
    {
      throw new IllegalArgumentException();
    }
    return array;
  }

  public static <T> Iterable<T> uniques(
    Iterable<T> iterable,
    BiPredicate<T, T> equals)
  {
    List<T> uniques = new ArrayList<>();
    for (T item : iterable)
    {
      if (uniques.stream().noneMatch(
        (containedItem) -> equals.test(item, containedItem)))
      {
        uniques.add(item);
      }
    }
    return uniques;
  }

  public static <T> Iterable<T> uniques(Iterable<T> iterable)
  {
    return uniques(iterable, Objects::equals);
  }

  public static <T> T[] uniques(T[] array)
  {
    return toArray(uniques(asList(array)));
  }

  public static <T> T[] uniques(Class<?> class_, T[] array)
  {
    return toArray(class_, uniques(asList(array)));
  }

  public static <T> Iterable<T[]> uniquesEach(Iterable<T[]> iterable)
  {
    return applyEach(iterable, (array) -> uniques(array));
  }

  public static <T> Iterable<T[]> uniquesEach(
    Class<?> class_,
    Iterable<T[]> iterable)
  {
    return applyEach(iterable, (array) -> uniques(class_, array));
  }
}
