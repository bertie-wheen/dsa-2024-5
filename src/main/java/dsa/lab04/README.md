# Data Structures and Algorithms Lab 4

In this lab we'll implement the three sorting algorithms we saw in the lectures 
(selection sort, insertion sort, and merge sort) as well as binary search. 
We also implement our first Map data structure: (unsorted) arrays.

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

Open the `src\main\java\dsa\lab04` folder.


## Exercise 1: Sorting


In the lectures we saw various sorting algorithms: insertion sort, selection
sort, merge sort. In this exercise we will implement each of them as the `sort`
method of a different Java class (`InsertionSorter`, `SelectionSorter`,
`MergeSorter`), each implementing the `Sorter` interface, which is defined in
`lab04\base\Sorter.java`.

We will sort objects of any class implementing the `StaticSequence<Item>`
interface (found in `lab02\base\StaticSequence.java`), which contains an already
defined method `swap(index1, index2)` that allows us to swap two items in the
sequence at the given indexes. However, in order to be able to *sort* a sequence
of generic items, we need to be able to compare them to each other, that is,
we need to be able to tell, given any two items `item1` and `item2` of type 
`Item`, which
is "less than the other". In Java, there are two ways to do this: either by
requiring the user to provide a *comparator* for objects of type `Item`, or by
requiring the class `Item` to already have a *natural way* to compare its
objects. We see both approaches in the two `sort` methods featured in the
`Sorter` interface as follows.

First, open and inspect the `lab04\base\Sorter.java` file. You can see that it
contains a method, `sort(items, comparator)`, that takes as input a static
sequence of items of generic type `Item` and also an object "comparator" of type
`Comparator<Item>`. Leaving the details aside (you will learn about this
properly in Further Programming), the fact that `comparator` is of type
`Comparator<Item>` means that it supports a method `compare(item1, item2)` which
can take as input any two objects of type `Item` and returns an integer. We can
use the value returned by `comparator.compare(item1, item2)` to *compare* the
two objects `item1` and `item2` for our sorting purposes:

* if `comparator.compare(item1, item2)` is a negative integer, then we consider
  `item1` to be less than `item2`,
* if `comparator.compare(item1, item2)` is 0, then we consider `item1` and
  `item2` to be equal to each other,
* if `comparator.compare(item1, item2)` is a positive integer, then we consider
  `item` to be greater than `item2`.

The `Sorter` interface also contains another method `sort(items)`, where `items`
is a static sequence of objects of type `Item` and this method can be used 
**only if** the class `Item` implements the `Comparable<Item>` interface, which
means (again leaving the details aside) that each item `item` stored in the
sequence `items` must support a method called `compareTo(anotherItem)` that
takes as input an object `anotherItem` of type `Item` and returns an integer,
that defines for us whether `item` is less than, equal to, or greater than
`anotherItem` in what is called the *natural ordering* of the "comparable class"
`Item`. In other words:

* if `item.compareTo(anotherItem)` is a negative integer, then `item` is defined
  to be less than `anotherItem` in the natural ordering,
* if `item.compareTo(anotherItem)` is 0, then `item` and `anotherItem` are
  defined to be "equal" in the natural ordering (that is, they are treated as if
  they were indistinguishable for sorting purposes, even if they happen not to
  be the exact same object)
* if `item.compareTo(anotherItem)` is a positive integer, then `item` is defined
  to be greater than `anotherItem` in the natural ordering.

The `sort(items)` method has a default implementation that simply calls the
`sort(items, comparator)` method previously discussed, where we pass as second
input a comparator defined using the natural ordering we just described. But you
don't have to worry about this.

Now you're ready (!) to tackle the actual first exercise: open
`lab04\exercises\InsertionSorter.java` and implement the insertion sort
algorithm by simply adapting the pseudocode provided in the lectures to Java
code to define the `sort(items, comparator)` method. Remember to use
`comparator.compare(item1, item2)` to be able to tell whether `item1` is less
than, equal to, or greater than `item2`, and the `swap(item1, item2)` method
supported by the static sequence `items`. Then, do the same for
`lab04\exercises\SelectionSorter.java` and `lab04\exercises\MergeSorter.
java`. 

For merge sort, you have to be careful: the `sort(items, comparator)` 
method expects `items` to be an object of any class implementing the 
`StaticSequence<Item>` interface. This means that in order to create the two 
copies `L` and `R` of the left and right half of the input, you'll have to 
construct two new objects, say `itemsL` and `itemsR`, belonging to any class 
implementing the `StaticSequence<Item>` interface, and then copy over the 
contents of `items` into them. The important thing you have to ensure is 
that building these objects and copying over the items must take O(n) times 
(if n is the size of `items`). For example, the following code:
```Java
StaticArray<Item> itemsL = new StaticArray<>();
StaticArray<Item> itemsR = new StaticArray<>();
// copy first half in itemsL with n/2 calls to itemsL.insertLast
// copy second half in itemsR with n/2 calls to itemsR.insertLast
```
would *not* be good, because it would run in O(n^2) time. Therefore, either 
you use a different data structure for `itemsL` and `itemsR` so that the 
code above takes O(n) time (namely, a `DynamicArray` or a 
`SinglyLinkedList`, which in our implementation keeps a reference to the last 
node so `insertLast(item)` takes O(1) time), or you use the `StaticArray
(Item... items)` constructor like we did in the solutions, where we created new 
static arrays of the correct capacity in O(n) time and we used `itemL.set` 
and `itemsR.set` which take O(1) time per item each.

## Exercise 2: Searching


Next, open and inspect `lab04\exercises\BinarySearcher.java`. For once, this
class doesn't implement any interface! We use it to implement in Java the binary
search algorithm we saw in the lectures. It features a method
`search(items, item)` taking an object `items` of any class implementing 
the `StaticSequence<Item>` interface (in other words, `items` is a 
"sequence") and a particular `item`, and returns the index in `items` at 
which `item` is stored, or -1 if it's not in the
sequence at all. It does so by simply calling a recursive method `search
(items, comparator, item)`, which in turn calls a recursive method
`search(items, comparator, item, start, stop)`, with default inputs `0` and 
`items.size()`
for its last two parameters. What you need to do is to implement this latter 
method,
using `comparator.compare(item1, item2)` again like in the previous exercise 
to determine whether `item1` is less than, equal to, or great than `item2`.


## Exercise 3: Unsorted arrays for Maps


Now we change to another topic: maps!

First, have a look at the `lab04\base\MapItem.java` file. This contains a
`MapItem<Key, Value>` class, which we use to implement the concept, seen in the
lectures, of an item consisting of a *key*, of generic type `Key`, and a
*value*, of generic type `Value`.

Now, open and inspect the `lab04\base\Map.java` file, which contains the
`Map<Key, Value>` interface. It declares the following methods:

* `find(key)`, where `key` is of type `Key`,
* `insert(item)`, where `item` is of type `MapItem<Key, Value>`,
* `insert(key, value)`, already implemented by default,
* `remove(key)`, returning an object of type `MapItem<Key, Value>`,
* `containsKey(key)` and `containsValue(value)`, returning a `boolean` and 
  already implemented by default.

`Map<Key, Value>`, as an interface, extends `Container<MapItem<Key, Value>>`,
which means that any class implementing `Map<Key, Value>` must provide
definitions for all the methods listed there *and also* all the methods of the
`Container<Item>` interface (defined in the imported `lab02\base\Container.java`
file) where, instead of the generic type `Item`, we have the type
`MapItem<Key, Value>` defined in `lab04\base\Map.java`.

Now, time for you to open the `lab04\exercises\ArrayMap.java` file, which
defines the class `ArrayMap<Key, Value>` implementing the `Map<Key, Value>`
interface. This class contains only one private field, `items`, which is of type
`DynamicArray<MapItem<Key, Value>>`, that is, it's a dynamic array - implemented
in `lab03\solutions\DynamicArray.java` - of objects of type
`MapItem<Key, Value>`. This means that `items` supports all the dynamic array
methods: for instance, if `i` is an integer, then `items.get(i)` returns the `i`
-th object - of type `MapItem<Key, Value>` - stored in the array `items`. If you
want the *key* of this object, you can call `items.get(i).key()`; if you want
its *value*, call instead `items.get(i).value()`. (`key()` and `value()` are
methods defined in the class `MapItem<Key, Value>`.)

You will see that there is only one method for you to implement: `indexOf
(key)`. This method is *not* in the `Map<Key, Value>` interface, 
but it contains all the logic of all the other methods (`find(key)`, `insert
(item)`, `remove(key)`), which are already implemented and use it. 
`indexOf(key)` takes a key as input and:

* if there is an item `item` currently stored in the map whose key coincides 
  with the input `key`, then `indexOf(key)` should return the index of the 
  dynamic array `items` where this `item` is stored;
* else (if there is no item in the map whose key is the input `key`), 
  `indexOf(key)` should return `-1`.

While implementing `indexOf(key)`, keep in mind that the items are ***not*** 
stored in key order. You can see this by looking at the code for `insert(item)`, 
where we always insert a new item at the end of the array, if there 
is no currently-stored item with the same key as the new one. Also, use 
`Objects.equals(key1, key2)` to check whether two keys `key1` and `key2` 
coincide or not.

**REMARK.** You can test if your methods are implemented correctly by defining,
in the `main` method of `Main.java`, a new array map and trying to
insert/get/remove items. You can then print to screen the contents of the array
map by using the `To.string` method that we implemented for you. For instance,
try to type:
```java
ArrayMap<Integer, String> myArrayMap = new ArrayMap<>();
myArrayMap.insert(1, "A");
myArrayMap.insert(2, "B");
System.out.println(To.string(myArrayMap));
```
It should print on screen:
```
ArrayMap(
  1 => "A"
  2 => "B"
)
```
You can also `Run Lab 4 tests` and make sure to check the solutions.
