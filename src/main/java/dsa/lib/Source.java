package dsa.lib;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

public class Source<Item>
  implements Iterable<Item>
{

  private static final int DEFAULT_LIMIT = 100;


  private Item[] items;


  private Source(Item[] items)
  {
    this.items = items;
  }


  public static <Item> Source<Item> empty()
  {
    return new Source<>(ArrayUtils.empty());
  }


  public static <Item> Source<Item> singleton(Item item)
  {
    return new Source<>(ArrayUtils.singleton(item));
  }


  public static <Item> Source<Item> repeat(int repetitions, Item item)
  {
    return new Source<>(ArrayUtils.repeat(repetitions, item));
  }


  @SafeVarargs
  public static <Item> Source<Item> from(Item... items)
  {
    return new Source<>(ArrayUtils.copy(items));
  }


  @SuppressWarnings("unchecked")
  public static <Item> Source<Item> from(Collection<Item> items)
  {
    return new Source<>((Item[]) items.toArray());
  }


  public static <Item> Source<Item> from(Iterable<Item> items)
  {
    return new Source<>(ArrayUtils.from(items));
  }


  public static <Item> Source<Item> from(Iterator<Item> items)
  {
    return from(() -> items);
  }


  @SuppressWarnings("unchecked")
  public static <Item> Source<Item> from(Stream<Item> items)
  {
    return new Source<>((Item[]) items.toArray());
  }


  public static <Item> Source<Item> from(int size, IntFunction<Item> item)
  {
    return new Source<>(ArrayUtils.from(size, item));
  }


  @SafeVarargs
  public static <Item> Source<Item> chain(Source<Item>... sources)
  {
    return flatten(from(sources));
  }


  public static <Item> Source<Item> flatten(Source<Source<Item>> sources)
  {
    int size = 0;
    for (Source<Item> source : sources)
    {
      size += source.size();
    }
    Item[] items = ArrayUtils.make(size);
    int offset = 0;
    for (Source<Item> source : sources)
    {
      ArrayUtils.copy(source.items, items, offset);
      offset += source.size();
    }
    return new Source<>(items);
  }


  @SafeVarargs
  public static Source<Object[]> flatProductEach(Source<Source<?>>... sources)
  {
    return flatten(productEach(sources));
  }


  public static <Item> Source<Item> getAtEach(
    Source<Source<Item>> sources,
    Source<Integer> indices)
  {
    if (sources.size() != indices.size())
    {
      throw new IllegalArgumentException();
    }
    return new Source<>(ArrayUtils.replace(
      sources.items,
      (source, index) -> source.getAt(indices.items[index])));
  }


  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <Item> Source<Item[]> product(Source<? extends Item>... sources)
  {
    int size = 1;
    for (Source<? extends Item> source : sources)
    {
      size *= source.size();
    }
    int sourcesSize = sources.length;
    Item[][] products = (Item[][]) new Object[size][sourcesSize];
    for (int p = 0; p < size; p++)
    {
      int d = 1;
      for (int s = sourcesSize - 1; s >= 0; s--)
      {
        Source<? extends Item> source = sources[s];
        int sourceSize = source.size();
        products[p][s] = source.getAt((p / d) % sourceSize);
        d *= sourceSize;
      }
    }
    return new Source<>(products);
  }


  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <Item> Source<Item[]> productQuadraticLimit(
    int limit,
    Source<? extends Item>... sources)
  {
    int size = 1;
    for (Source<? extends Item> source : sources)
    {
      try
      {
        size = Math.multiplyExact(size, source.size());
      }
      catch (ArithmeticException ignored)
      {
        size = (int) Math.sqrt(Integer.MAX_VALUE);
        break;
      }
    }
    size = Math.min(size, limit);
    int sourcesSize = sources.length;
    Item[][] products = (Item[][]) new Object[size][sourcesSize];
    for (int p = 0; p < size; p++)
    {
      int d = 1;
      for (int s = sourcesSize - 1; s >= 0; s--)
      {
        Source<? extends Item> source = sources[s];
        int sourceSize = source.size();
        products[p][s] = source.getAt((p * p / d) % sourceSize);
        d *= sourceSize;
      }
    }
    return new Source<>(products);
  }


  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <Item> Source<Item[]> productQuadraticLimit(
    Source<? extends Item>... sources)
  {
    return productQuadraticLimit(DEFAULT_LIMIT, sources);
  }


  public static <Item> Source<Source<Item>> productSource(
    Source<Source<Item>> sources)
  {
    return product(sources.array(Source.class))
      .replace((items) -> new Source<>(items));
  }


  public static <Item> Source<Source<Item>> productSourceQuadraticLimit(
    int limit,
    Source<Source<Item>> sources)
  {
    return productQuadraticLimit(limit, sources.array(Source.class))
      .replace((items) -> new Source<>(items));
  }


  public static <Item> Source<Source<Item>> productSourceQuadraticLimit(
    Source<Source<Item>> sources)
  {
    return productQuadraticLimit(sources.array(Source.class))
      .replace((items) -> new Source<>(items));
  }


  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <Item> Source<Source<Item[]>> productEach(
    Source<Source<? extends Item>>... sources)
  {
    int sourcesSize = sources.length;
    if (sourcesSize == 0)
    {
      return singleton(singleton(ArrayUtils.empty()));
    }
    int size = sources[0].size();
    for (int i = 1; i < sourcesSize; i++)
    {
      if (sources[i].size() != size)
      {
        throw new IllegalArgumentException();
      }
    }
    return from(
      size, (int i) ->
      {
        Source<? extends Item>[] productSources =
          (Source<? extends Item>[]) new Source[sourcesSize];
        for (int j = 0; j < sourcesSize; j++)
        {
          productSources[j] = sources[j].getAt(i);
        }
        return product(productSources);
      });
  }


  public static <Item> Source<Source<Item>> powerSet(
    int limit,
    Source<Item> source,
    int minSize,
    int maxSize)
  {
    List<Source<Source<Item>>> sources = new ArrayList<>();
    for (int size = minSize; size <= maxSize; size++)
    {
      sources.add(
        productSourceQuadraticLimit(limit, from(size, source::cycle)));
    }
    return flatten(from(sources));
  }


  public static <Item> Source<Source<Item>> powerSet(
    int limit,
    Source<Item> source,
    int maxSize)
  {
    return powerSet(limit, source, 0, maxSize);
  }


  public static <Item> Source<Source<Item>> powerSet(
    Source<Item> source,
    int minSize,
    int maxSize)
  {
    return powerSet(DEFAULT_LIMIT, source, minSize, maxSize);
  }


  public static <Item> Source<Source<Item>> powerSet(
    Source<Item> source,
    int maxSize)
  {
    return powerSet(source, 0, maxSize);
  }


  public static <Item> Source<Source<Item>> repeatCycled(
    Source<Source<Item>> sources,
    int repetitions)
  {
    return sources.repeat(repetitions).replace(Source::cycle);
  }


  public static <Item> Source<Source<Item>> singletonEach(
    Source<Item> sources)
  {
    return sources.replace(Source::singleton);
  }


  public static <Item> Source<Source<Item>> sortedEach(
    Source<Source<Item>> sources)
  {
    return sources.replace((source) -> source.sorted());
  }


  public static <Item> Source<Source<Item>> sortedEach(
    Source<Source<Item>> sources,
    Comparator<Item> comparator)
  {
    return sources.replace((source) -> source.sorted(comparator));
  }


  @SafeVarargs
  public static <Item> Source<Item[]> transpose(
    IntFunction<Item[]> makeArray,
    Source<Item>... sources)
  {
    int sourcesSize = sources.length;
    if (sourcesSize == 0)
    {
      return singleton(makeArray.apply(0));
    }
    int size = sources[0].size();
    for (int i = 1; i < sourcesSize; i++)
    {
      if (sources[i].size() != size)
      {
        throw new IllegalArgumentException();
      }
    }
    return from(
      size,
      (i) ->
        ArrayUtils.from(makeArray, sourcesSize, (j) -> sources[j].getAt(i)));
  }


  @SafeVarargs
  public static <Item> Source<Item[]> transpose(Source<Item>... sources)
  {
    return transpose(ArrayUtils.makeUntyped(), sources);
  }


  @SafeVarargs
  public static <Item> Source<Item[]> transpose(
    Class<?> itemClass,
    Source<Item>... sources)
  {
    return transpose(ArrayUtils.makeTyped(itemClass), sources);
  }


  public static <Item> Source<Source<Item>> uniquesEach(
    Source<Source<Item>> sources)
  {
    return sources.replace((source) -> source.uniques());
  }


  public static <Item> Source<Source<Item>> uniquesEach(
    Source<Source<Item>> sources,
    BiPredicate<Item, Item> equals)
  {
    return sources.replace((source) -> source.uniques(equals));
  }


  public static <Item> Source<Source<Integer>> validIndicesEach(
    Source<Source<Item>> sources)
  {
    return sources.replace(Source::validIndices);
  }


  public static <Item> Source<Source<Integer>> validInsertIndicesEach(
    Source<Source<Item>> sources)
  {
    return sources.replace(Source::validInsertIndices);
  }


  public <Result> Source<Result> replace(BiFunction<Item, Integer, Result> function)
  {
    return new Source<>(ArrayUtils.replace(this.items, function));
  }


  public <Result> Source<Result> replace(Function<Item, Result> function)
  {
    return this.replace((item, ignored) -> function.apply(item));
  }


  public Source<Item> cycle(int offset)
  {
    int size = this.size();
    if (size == 0)
    {
      return this;
    }
    offset = Math.floorMod(offset, size);
    Item[] items = ArrayUtils.make(size);
    ArrayUtils.copy(size - offset, this.items, items, offset);
    ArrayUtils.copy(offset, this.items, size - offset, items);
    return new Source<>(items);
  }


  public Source<Source<Item>> cycledSubs()
  {
    return from(this.size() + 1, (index) ->
      this.cycle(index).limit(index));
  }


  public int firstIndex(Item item)
  {
    int size = this.size();
    for (int i = 0; i < size; i++)
    {
      if (Objects.equals(item, this.items[i]))
      {
        return i;
      }
    }
    return -1;
  }


  public int firstIndexSame(Item item)
  {
    int size = this.size();
    for (int i = 0; i < size; i++)
    {
      if (item == this.items[i])
      {
        return i;
      }
    }
    return -1;
  }


  public Source<Item> filter(Predicate<Item> predicate)
  {
    return this.filter((item, ignored) -> predicate.test(item));
  }


  @SuppressWarnings("unchecked")
  public Source<Item> filter(BiPredicate<Item, Integer> predicate)
  {
    List<Item> items = new ArrayList<>();
    int index = 0;
    for (Item item : this.items)
    {
      if (predicate.test(item, index++))
      {
        items.add(item);
      }
    }
    return new Source<>((Item[]) items.toArray());
  }


  public <Result> Source<Result> flatReplace(
    BiFunction<Item, Integer, Source<Result>> function)
  {
    int size = this.size();
    List<Result> results = new ArrayList<>();
    for (int i = 0; i < size; i++)
    {
      function.apply(this.items[i], i).forEach(results::add);
    }
    return from(results);
  }


  public <Result> Source<Result> flatReplace(Function<Item, Source<Result>> function)
  {
    return this.flatReplace((item, ignored) -> function.apply(item));
  }


  public Item getAt(int index)
  {
    return this.items[index];
  }


  public <Group> Source<Source<Item>> group(Function<Item, Group> grouper)
  {
    Map<Group, List<Item>> map = new HashMap<>();
    for (Item item : this.items)
    {
      Group group = grouper.apply(item);
      map.putIfAbsent(group, new ArrayList<>());
      map.get(group).add(item);
    }
    return from(map.values()).replace((items) -> from(items));
  }


  public Source<Item> limit(int size)
  {
    if (size < 0)
    {
      throw new IllegalArgumentException();
    }
    if (size == this.size())
    {
      return this;
    }
    size = Math.min(size, this.size());
    Item[] items = ArrayUtils.make(size);
    ArrayUtils.copy(this.items, items);
    return new Source<>(items);
  }


  public Source<Item> limit()
  {
    return this.limit(DEFAULT_LIMIT);
  }


  public Source<Item> quadratic()
  {
    List<Item> itemsList = new ArrayList<>(this.list());
    return from(
      Math.min(this.size(), (int) Math.sqrt(Integer.MAX_VALUE)),
      (i) -> itemsList.remove((i * i - i) % itemsList.size()));
  }


  public Source<Item> repeat(int repetitions)
  {
    if (repetitions < 0)
    {
      throw new IllegalArgumentException();
    }
    int size = this.size();
    return from(size * repetitions, (i) -> this.items[i % size]);
  }


  public Source<Item> reversed()
  {
    return new Source<>(ArrayUtils.reversed(this.items));
  }


  public Source<Item> shuffle()
  {
    return this.shuffle(new Random());
  }


  public Source<Item> shuffle(Random random)
  {
    List<Item> items = this.list();
    Collections.shuffle(items, random);
    return from(items);
  }


  public Source<Item> skipIndex(int index)
  {
    return this.filter((ignored, i) -> i != index);
  }


  public Source<Item> skipFirst()
  {
    return this.skipIndex(0);
  }


  public Source<Item> skipLast()
  {
    return this.skipIndex(this.size() - 1);
  }


  public Source<Item> skipFirst(Item item)
  {
    return this.skipIndex(this.firstIndex(item));
  }


  public Source<Item> skipFirstSame(Item item)
  {
    return this.skipIndex(this.firstIndexSame(item));
  }


  public Source<Item> sorted()
  {
    Item[] items = ArrayUtils.copy(this.items);
    Arrays.sort(items);
    return new Source<>(items);
  }


  public Source<Item> sorted(Comparator<Item> comparator)
  {
    Item[] items = ArrayUtils.copy(this.items);
    Arrays.sort(items, comparator);
    return new Source<>(items);
  }


  public Source<Item> step(int step)
  {
    if (step < 1)
    {
      throw new IllegalArgumentException();
    }
    return from(this.size() / step, (i) -> this.items[i * step]);
  }


  public Source<Item> then(Source<Item> that)
  {
    return new Source<>(ArrayUtils.chain(this.items, that.items));
  }


  public Source<Item> uniques()
  {
    return from(new LinkedHashSet<>(this.list()));
  }


  public Source<Item> uniques(BiPredicate<Item, Item> equals)
  {
    List<Item> list = new ArrayList<>();
    for (Item item : this.items)
    {
      if (list.stream().noneMatch((listItem) -> equals.test(item, listItem)))
      {
        list.add(item);
      }
    }
    return from(list);
  }


  public Source<Integer> validIndices()
  {
    return from(this.size(), (i) -> i);
  }


  public Source<Integer> validNonFirstIndices()
  {
    return from(this.size() - 1, (i) -> i + 1);
  }


  public Source<Integer> validNonLastIndices()
  {
    return from(this.size() - 1, (i) -> i);
  }


  public Source<Integer> validInsertIndices()
  {
    return this.validIndices().then(singleton(this.size()));
  }


  public Item[] array()
  {
    return ArrayUtils.copy(this.items);
  }


  public Item[] array(Class<?> itemClass)
  {
    return ArrayUtils.copy(itemClass, this.items);
  }


  public List<Item> list()
  {
    return Arrays.asList(this.array());
  }


  public Stream<Item> stream()
  {
    return Stream.of(this.array());
  }


  @SuppressWarnings("unchecked")
  public <Result> Source<Result> cast()
  {
    return (Source<Result>) this;
  }


  public int size()
  {
    return this.items.length;
  }


  @Override
  public Iterator<Item> iterator()
  {
    return new ArrayIterator<>(this.array());
  }


  @Override
  public String toString()
  {
    return String.join(
      ", ",
      ArrayUtils.replace(
        CharSequence.class,
        this.items,
        (item) -> To.string(item)));
  }

}
