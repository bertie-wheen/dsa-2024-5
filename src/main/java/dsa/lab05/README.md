# Data Structures and Algorithms Lab 5

In this lab we implement sorted arrays as a solution to the Ordered Map 
interface, and hash maps (we called them hash tables in the lectures, they 
are the same thing) with collisions resolved with chaining using a universal 
family of hash functions to solve the Map interface.

## Setup


What to do at the start of EVERY lab session:

* Open the Software Hub and launch:
  * Git for Windows
  * IntelliJ Community Edition
* Open PowerShell and run:
  * If you have never cloned this repository before:
    * `N:` and `Enter` if using lab computers, otherwise pick a location you
      like
    * `git clone https://github.com/bertie-wheen/dsa-2024-5 dsa` and `Enter`
  * Else:
    * `cd N:/dsa` then `Enter` if using lab computer, or `cd` to the location
      where you cloned the repository before
    * `git pull` then `Enter`
    * If you get an error "fatal: detected dubious ownership in
      repository ..." (which you likely will):
      * `git config --global --add safe.directory '%(prefix)///smbhome.uscs.susx.ac.uk/<username>/dsa'`
        then `Enter` (where `<username>` is your username)
      * `git pull` then `Enter`
* Open IntelliJ IDEA and:
  * Open `N:/dsa` as a project if using lab computers, or the folder where you
    cloned your repository
  * In the menu, select `File -> Project Structure` (or press
    `Ctrl+Alt+Shift+S`)
  * Select `Project` in the left sidebar (if it isn't already selected)
  * If there is no SDK:
    * Click on the dropdown, and select `Download JDK`
    * Choose `Amazon Corretto` as the vendor, and click `Download`
    * Click `Apply`, then `OK` to close the project structure dialog

Open the `src\main\java\dsa\lab05` folder.

## Exercise 1: Sorted arrays for ordered maps


Open and inspect `lab05\base\OrderedMap.java`. It contains the
`OrderedMap<Key, Value>` interface, where `Key` must implement the
`Comparable(Key)` interface, which means (leaving the details aside, you'll 
discuss this properly in Further Programming later this term) that 
each key `key` must support a method called `compareTo(anotherKey)` that
takes as input an object `anotherKey` of type `Key` and returns an integer,
that defines for us whether `key` is less than, equal to, or greater than
`anotherKey` in what is called the *natural ordering* of the "comparable class"
`Key`. Specifically:

* if `key.compareTo(anotherKey)` is a negative integer, then `key` is defined
  to be less than `anotherKey` in the natural ordering,
* if `key.compareTo(anotherkey)` is 0, then `key` and `anotherKey` are
  defined to be "equal" in the natural ordering (that is, they are treated as if
  they were indistinguishable for sorting purposes, even if they happen not to
  be the exact same object)
* if `key.compareTo(anotherKey)` is a positive integer, then `key` is defined
  to be greater than `anotherKey` in the natural ordering.

For instance, the class `Integer` implements the `Comparable<Integer>` 
interface: it supports a method `compareTo` where `x.compareTo(y)` returns 
-1 if x < y, 0 if x = y, and 1 if x > y. The class `String` also implements 
the `Comparable<String>` interface, where the natural ordering defined above 
by the `compareTo(anotherString)` method is the lexicographic order, but the 
returned value is not simply -1, 0, or 1: see [the Java documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#compareTo(java.lang.String)) 
if you're curious.
Here we don't care what actual class `Key` is, as long as it implements the 
`Comparable<Key>` interface.

The `OrderedMap<Key, Value>` interface extends `Map<Key, Value>`. It 
features the
`previous(key)`, `next(key)`, `min()`, and `max()` methods as we saw in the
lectures. Notice that all these methods are meant to return an object of type
`MapItem<Key, Value>`, that is, they return an item in its entirety, not just a
key or a value.

Now open `lab05\exercises\SortedArrayMap.java`, which contains the class
`SortedArrayMap<Key, Value>` (again for comparable keys only) implementing the
`OrderedMap<Key, Value>` interface you just examined. It contains as
private field a dynamic array `items` of objects of type `MapItem<Key, Value>`,
where we assume that the entries in `items` are sorted by the keys'
natural ordering *at all times*. This class features all the methods of the 
Ordered Map interface already implemented for you, and as you can see, these 
implementations rely on two crucial methods:
* `indexFor(key)`, which takes as input a key `key` and is supposed to 
  return the index of `items` where an item with key `key` either
  is stored or, if it's not, where it would be stored if it were. I say it's 
  "supposed to" because all it does is to call an un-implemented 
  `indexFor(key, start, stop)` method, which you have to implement (see below).
* `indexOf(key)`, which given a key `key` as input it returns the index in 
  `items` where an item with key `key` is stored, if it's there at all, else 
  it returns `-1`. It does so by calling `indexFor(key)` and by checking if 
  the item at the index returned by `indexFor(key)` has `key` as key: if 
  that's the case, it returns that item, else it returns -1 as desired.

What you need to do is to implement the one method we left unimplemented:
`indexFor(key, start, stop)`. The procedure "BinarySearchMap" that we saw in
Lecture 7 does exactly this, so all you need to do is to translate its
pseudocode into Java code for `indexFor(key, start, stop)`. While doing this,
you must use the fact that `Key` implements the `Comparable(Key)` interface, 
which means that every object ot type `Key` supports a 
`compareTo(anotherKey)`method that you must use in
order to compare the keys. Once you're done, make sure to `Run Lab 5 tests` 
and to check the provided solutions! 


## Exercise 2: Universal hashing


Open `lab05\exercises\HashFunction.java`, which contains the `HashFunction`
class (no generic types for once!).


This has three private fields:
* a `long`, fixed prime number `LARGE_PRIME`,
* two `long` numbers `a` and `b`,
* an integer, `size`, that represents the maximum value that the hash codes
  can have, corresponding to the number N in the lecture slides (which is
  the capacity of the underlying array storing our items in the map).

The class has one constructor, `HashFunction(size)`, which takes as input an 
integer (expected to be at least 1) for the value to assign to the `size` 
field, and it randomely selects a value for `a` between 1 and 
`LARGE_PRIME - 1`, 
and for `b` between 0 and `LARGE_PRIME - 1`.

An object of this class supports a method, `hash(object)`, that given any
object of type `Object` returns an integer between 0 and `size - 1`. This is the
method you need to implement. What you need to do is to actually implement 
the `hash(object)` method, which we have only partially started implementing 
ourselves by calculating a long integer `initialHash` from the `object` 
passed as input. Now implement the hash function $h_{a,b}(k)$ we saw in 
Lecture 8, namely:

$h_{a,b}(k) = ( ( ak + b) \bmod p) \bmod N $

using `initialHash` as $k$, `this.a` and `this.b` as $a$ and $b$, 
`HashFunction.LARGE_PRIME` as $p$, and `this.size` as $N$.

As a result of this, an object of the class `HashFunction` encodes a member 
of the universal family of hash functions that we saw, and we can use it in 
our hash map implementations. 

Once you're done, make sure to `Run Lab 5 tests`
and to check the provided solutions!


## Exercise 3: Chaining hash maps


Open `lab05\exercises\ChainingHashMap.java`, which contains the
`ChainingHashMap<Key, Value>` interface, implementing the `Map<Key, Value>`
interface. It features the following fields:

* a Java-array, called `chains`, of objects of type `ArrayMap<Key, Value>`.
  The class `ArrayMap<Key, Value>` was defined in
  `lab04\solutions\ArrayMap.java` which we are importing. (We *could* have used
  `StaticArray<ArrayMap<Key, Value>>` for the type of `chains`, but this
  would have added a useless extra layer of complexity.)
* An object `hashFunction` of type `HashFunction`, which is (you guessed it!)
  a fixed hash function we need to use to hash our items into the hash map.
* An integer, `size`, which keeps track of how many items are currently
  stored - initially 0 by default.
* A `float` number `maxLoadFactor`, set to be 2 by default. This number is
  meant to coincide with the ratio between `size` and the length of the
  array `chains`, and we are saying that this ratio - which changes when we
  insert or remove items in the map - should never go above 2.

The constructors, which you can safely ignore, take care of initialising all
these fields appropriately. If you scroll down, you'll see that there is a
method `resize(chainCount)` already implemented. It's important that you
understand what this method actually does, so let's go through that together:
* First it makes a copy of the current array `chains` of chains, called 
  `oldChains`.
* It replaces `chains` with a new Java array of `ArrayMap<Key, Value>` objects,
  this time of capacity `chainCount`, the integer that is passed as input to
  this method.
* It constructs a new hash function `hashFunction` (by picking new random
  integers `a` and `b`, see Exercise 2) that can hash any object to an
  integer between 0 and `chainCount`.
* Re-sets the `size` to 0.
* Re-inserts all the items, of which we stored a copy in `oldChains` above, in 
  the map, now with a new hash function and a new
  underlying array of capacity `chainCount`.

What you need to do is:

1. Implement the `find(key)` method. See the lecture slides for the
   intuition. It should only require one line of code.
2. Implement the `insert(item)` method. This is quite delicate. First, you
   need to individuate the correct chain, let's call it `chain`, where to put
   your item, using `hashFunction` (again see lecture slides). Notice that
   `chain` is of type `ArrayMap<Key, Value>`, so you might be tempted to just
   use `chain.insert(item)` at this point, given that the object `chain` 
   supports an `insert` method that would correctly insert an input such as 
   `item` in `chain`.
   However, you need to be careful with when to update the variable `size` 
   of the current `ChainingHashMap` class we are working on (that is, `this.
   size`), so we can't call `chain.insert(item)` just yet. Instead,
   * check whether `chain` already contains an item whose key coincides
     with `item.key()`. For this, just do a linear search through the items 
     in `chain` and compare their keys to `item.key()` using 
     `Objects.equals(key1, key2)`.
   * If it does, then do call `chain.insert(item)`,
     which will correctly replace that item with our input `item`, but
     don't increase `this.size`!
   * If it doesn't, then you must check whether adding the new item to the 
     map will make the load factor go above `maxLoadFactor`. For this, you'll 
     have to compute the ratio between the current size of the map + 1 and 
     the current number of chains first, and then check if what you get as a 
     result is greater than `maxLoadFactor`. 
     * If it is, then first call the `resize(chainCount)` method we 
       discussed above, passing as input twice the current number of chains, 
       then recursively call `insert(item)`.
     * If it isn't, then simply call `chain.insert(item)` and increase the 
       `size`.
3. Implement the `remove(key)` method. This is also delicate but not quite 
   as complex as `insert(item)`.
   * First, you need again to individuate the correct chain where an item 
     with key `key` should be stored, using `hashFunction` 
     (again see lecture slides), and let's call that `chain`.
   * Call the `chain.remove(key)` method, saving its output item in a 
     variable that you'll need to return at the end of the method, and decrease 
     the `size`.
   * Check if the ratio between the current size of the map (after removal) 
     and the current number of chains is less than 1/4 of `maxLoadFactor`: 
     If it is, call the `resize(chainCount)` method, passing as input half 
     of the current number of chains or 1, whichever is greater.
   * Finally, return the output of `chain.remove(key)` you saved earlier.

Once you're done, make sure to `Run Lab 5 tests`
and to check the provided solutions! 


