# Data Structures and Algorithms Lab 9

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
      * copy and paste the line that the terminal suggests you to run, then
        `Enter`
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

Open the `src\main\java\dsa\lab09` folder.


## Introduction


FIrst, open and inspect `lab09\base\PriorityQueueItem.java`, which features the
`PriorityQueueItem<Priority, Item>` class. We use this class to encapsulate our
items coming with a *priority*, where objects of type `Priority` must be
*comparable*, see `lab05\README.md` for a refresher on what this means. Notice
that we make the `PriorityQueueItem<Priority, Item>` class implement the
`Comparable<PriorityQueueItem<Priority, Item>>` interface by providing a method
`compareTo(that)` (where `that` is another object of type
`PriorityQueueItem<Priority, Item>`) which establishes that `this` is "less
than" `that` precisely when `this.priority` is less than `that.priority`
according to the natural ordering of the type `Priority`.

Next, open and inspect `lab09\base\PriorityQueue.java`, which features the
`PriorityQueue<Priority, Item>` interface. You can see that this interface
extends `Container<PriorityQueueItem<Priority, Item>>`, this is analogous to
what we did with the `Map<Key, Value>` interface back in `lab04`, which extends
the `Container<MapItem<Key, Value>` interface. This
`PriorityQueue<Priority, Item>` interface is straightforward to understand.


## Exercise 1: (unsorted) array priority queues


Open and inspect `lab09\exercises\ArrayPriorityQueue.java`, featuring the
`ArrayPriorityQueue<Priority, Item>` class that implements the
`PriorityQueue<Priority, Item>` interface. In this class we have a dynamic array
`items` of objects of type `PriorityQueueItem<Priority, Item>` that stores our
items with priorities. As we saw in the lectures, this data structures follows a
very simple strategy: it keeps its items unsorted, with a fast ($\mathcal{O}(1)$
amortized) `insert(item)` method that simply inserts `item` at the end of the
array, at the cost then of having to do a linear search for `max()` and
`removeMax()`. Now, implement these methods here, keeping in mind that `items`
supports all the methods of the `DynamicSequence<Item>` interface from
`lab02\base` (where the generic type `Item` is here replaced by
`PriorityQueueItem<Priority, Item>`), and that each of `items`'s entries are
comparable, see the Introduction to this lab notes. Have a look at the slides
for the intuition, and don't forget to compare your work with our (now
commented!) solutions in `lab09\solutions`.


## Exercise 2: sorted array priority queues


Open and inspect `lab09\exercises\SortedArrayPriorityQueue.java`. This contains
a class that is very similar to that of Exercise 1, in that also here we have a
dynamic array `items` storing our items with priority, but this time we maintain
them in *priority order* (from the lowest priority at index 0 to the highest at
index `items.size() - 1`). This means that `max()` and `removeMax()` can be very
easily implemented in $\mathcal{O}(1)$ time, at the cost of a more complicated
`insert(item)` method. Again remember that `items` supports all the methods of
the Dynamic Sequence interface, and that its entries are comparable.


## Exercise 3: binary heaps


Open and inspect `lab09\exercises\BinaryHeapPriorityQueue.java`, featuring the
`BinaryHeapPriorityQueue<Priority, Item>` class. Again we have a dynamic array
`items` of objects of type `PriorityQueueItem<Priority, Item>`, but here we take
a very different approach compared to the previous two exercises: we see this
array as the array-representation of a complete binary tree, which only lives in
our heads! To this end, we talk about the "parent" of (the item at) index $i$,
as well as it's "left" and "right child", which are other (items at other)
indexes of `items`. Remember that:

 * (The item at) index 0 is the root of the corresponding tree,
 * The left child of index $i$ is index $2i+1$,
 * The right child of index $i$ is index $2i+2$.

1. Can you use this information to implement the methods `left(index)`,
   `right(index)` and (more challengingly) `parent(index)`? We did already see
   how to do this in the lecture slides, if you struggle. They require only one
   line of code.

2. Next, implement the methods `heapifyUp(index)` and `heapifyDown(index)`.
   These correspond to the `maxHeapifyUp(Q,c)` and `maxHeapifyDown(Q,p,k)`
   procedures we saw in the lectures, and they encode the following ideas:

   * `heapifyUp(index)` checks whether the item stored at index `index` is
     greater than its parent (which is the item stored at `parent(index)`): if
     it is, it swaps them, and then it recursively calls itself on `parent`;
   * `heapifyDown(index)` checks whether the item stored at index `index` is
     less than its greatest child: if it is, it swaps them, and it recursively
     calls itself on that child. 

   You can use the already implemented `greaterPriority(indexA, indexB)` method
   to save you some time writing the same long line of code repeatedly. 

3. Now you're ready to implement the methods from the Priority Queue interface:
   `max()`, `insert(item)`, `removeMax()`. We saw the intuitive idea in the
   lectures: you'll need to use `heapifyUp(index)`, `heapifyDown(index)`, and
   the fact that `items` supports all the methods from the Dynamic Sequence
   interface.

Don't forget to compare your implementations with our solutions, and to
`Run Lab 9 tests`. 

### Remark
Note that the last constructor of this class implements the linear build up of a
heap we saw at the end of Lecture 16.


## Exercise 4: heap sort


Finally, time to work on heap sort!

Open and inspect `lab09\exercises\HeapSorter.java`, featuring the `HeapSorter`
class that implements the `Sorter` interface from `lab04\base`. Here we don't
have a field `items` of items to sort, because the `Sorter` interface requires
the presence of a method `sort` that takes *as a parameter* a static sequence
(of the items to be sorted) and a comparator. 

First you'll need to implement the `parent(index)`, `left(index)`, and
`right(index)` methods, by using the exact same strategy as in Exercise 3. 

Then, you'll need to implement `heapifyUp(items, comparator, index)`, where
`items` is a static sequence of objects of generic type `Item` and `comparator`
is a comparator for `Item` (see the notes for `lab04` for a refresher on
comparators). The idea is the same as in Exercise 3, you just have to adapt it
a bit. Similarly for `heapifyDown(items, comparator, index, size)`. You will
later call these methods in the implementation of `sort(items, comparator)`: in
there, the last parameter of `heapifyDown` is the size of the prefix of `items`
that we are maintaining as a heap. We've seen the pseudocode for this in the
lectures.

Don't forget to compare your implementations with our solutions, and to
`Run Lab 9 tests`. 