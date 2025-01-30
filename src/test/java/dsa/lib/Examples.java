package dsa.lib;

public class Examples
{
  private static final Iterable<Integer[]> emptyIntArray =
    Iterators.singletonIterable(new Integer[]{});
  private static final Iterable<Integer[]> singletonIntArrays =
    Iterators.singletonIterable(
      new Integer[]{2});
  private static final Iterable<Integer[]> multiItemIntArrays =
    Iterators.iterable(
      new Integer[]{1, 42, 3},
      new Integer[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE, -1, -2});
  private static final Iterable<Integer[]> nonEmptyIntArrays =
    Iterators.chain(singletonIntArrays, multiItemIntArrays);
  private static final Iterable<Integer[]> intArrays =
    Iterators.chain(emptyIntArray, nonEmptyIntArrays);
  private static final Iterable<Integer> zeroInt = Iterators.iterable(
    0);
  private static final Iterable<Integer> positiveInts = Iterators.iterable(
    1,
    100,
    Integer.MAX_VALUE);
  private static final Iterable<Integer> negativeInts = Iterators.iterable(
    -1,
    -40,
    Integer.MIN_VALUE);
  private static final Iterable<Integer> nonZeroInts = Iterators.chain(
    positiveInts,
    negativeInts);
  private static final Iterable<Integer> nonNegativeInts = Iterators.chain(
    zeroInt,
    positiveInts);
  private static final Iterable<Integer> nonPositiveInts = Iterators.chain(
    zeroInt,
    negativeInts);
  private static final Iterable<Integer> ints = Iterators.chain(
    zeroInt,
    nonZeroInts);
  private static final Iterable<String[]> emptyStringArray =
    Iterators.singletonIterable(new String[]{});
  private static final Iterable<String[]> singletonStringArrays =
    Iterators.singletonIterable(
      new String[]{"supercalifragilisticexpialidocious"});
  private static final Iterable<String[]> multiItemStringArrays =
    Iterators.iterable(
      new String[]{"foo", "bar", "quux"},
      new String[]{"Hello", null},
      new String[]{"LOREM", "IPSUM", "DOLOR", "SIT", "AMET!"});
  private static final Iterable<String[]> nonEmptyStringArrays =
    Iterators.chain(singletonStringArrays, multiItemStringArrays);
  private static final Iterable<String[]> stringArrays =
    Iterators.chain(emptyStringArray, nonEmptyStringArrays);
  private static final Iterable<String> strings = Iterators.iterable(
    null,
    "",
    "CS",
    ")(*&^%$£\"!");
  private static final Iterable<Object[]> emptyArrays =
    Iterators.chain(emptyIntArray, emptyStringArray);
  private static final Iterable<Object[]> singletonArrays =
    Iterators.chain(singletonIntArrays, singletonStringArrays);
  private static final Iterable<Object[]> multiItemArrays =
    Iterators.chain(multiItemIntArrays, multiItemStringArrays);
  private static final Iterable<Object[]> nonEmptyArrays =
    Iterators.chain(nonEmptyIntArrays, nonEmptyStringArrays);
  private static final Iterable<Object[]> arrays =
    Iterators.chain(intArrays, stringArrays);

  static Iterable<Object[]> emptyArrays()
  {
    return Iterators.applyEach(emptyArrays, (array) -> new Object[]{array});
  }

  static Iterable<Object[]> emptyArraysAndInts()
  {
    return Iterators.product(emptyArrays, ints);
  }

  static Iterable<Object[]> singletonArrays()
  {
    return Iterators.applyEach(singletonArrays, (array) -> new Object[]{array});
  }

  static Iterable<Object[]> multiItemArrays()
  {
    return Iterators.applyEach(multiItemArrays, (array) -> new Object[]{array});
  }

  static Iterable<Object[]> nonEmptyArrays()
  {
    return Iterators.applyEach(nonEmptyArrays, (array) -> new Object[]{array});
  }

  static Iterable<Object[]> arrays()
  {
    return Iterators.applyEach(arrays, (array) -> new Object[]{array});
  }

  static Iterable<Object[]> nonEmptyArraysAndItems()
  {
    return Iterators.chain(
      Iterators.product(nonEmptyIntArrays, ints),
      Iterators.product(nonEmptyStringArrays, strings));
  }

  static Iterable<Object[]> arraysAndItems()
  {
    return Iterators.chain(
      Iterators.product(intArrays, ints),
      Iterators.product(stringArrays, strings));
  }

  static Iterable<Object[]> arraysAndNegativeInts()
  {
    return Iterators.product(arrays, negativeInts);
  }

  static Iterable<Object[]> arraysAndNonNegativeInts()
  {
    return Iterators.product(arrays, nonNegativeInts);
  }

  static Iterable<Object[]> arraysNegativeIntsAndItems()
  {
    return Iterators.chain(
      Iterators.product(intArrays, negativeInts, ints),
      Iterators.product(stringArrays, negativeInts, strings));
  }

  static Iterable<Object[]> arraysNonNegativeIntsAndItems()
  {
    return Iterators.chain(
      Iterators.product(intArrays, nonNegativeInts, ints),
      Iterators.product(stringArrays, nonNegativeInts, strings));
  }
}
