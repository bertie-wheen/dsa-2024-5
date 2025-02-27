# Data Structures and Algorithms Lab 6


In this lab we implement hash maps (same thing as hash tables) with collisions
resolved via linear probing, and we also implement binary trees.


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
      *
      `git config --global --add safe.directory '%(prefix)///smbhome.uscs.susx.ac.uk/<username>/dsa'`
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

Open the `src\main\java\dsa\lab06` folder.


## Exercise 1: linear probing hash maps


Open and inspect the `lab06\exercises\ProbingHashMap.java` file, featuring the
`ProbingHashMap<Key, Value>` class, which implements the `Map<Key, Value>`
interface from `lab04\base`. This class has the following fields:
* A special object `REMOVED` of type `MapItem<Key, Value>`, which we use to flag
  entries that have been deleted. (The class `MapItem<Key, Value>` was defined
  in `lab04\base`.) This corresponds to the REMOVED flag we saw in the lectures.
  As you can see, it has `null` key and value.
* A Java array `items` of objects of type `MapItem<Key,Value>`. We could've used
  our home-made `StaticArray<MapItem<Key, Value>>`, but to simplify the code we
  decided to opt for the already baked-in Java arrays.
* An object `hashFunction` of type `HashFunction` that we use to hash our items.
  The `HashFunction` class is imported from `lab05\solutions`.
* An integer `size` keeping track of how many items we're currently storing in
  the map.
* A float number `maxLoadFactor`, set by default to 0.8, which represents the
  ratio between `size` and `items.length`. This ratio can't in fact be any
  higher than 1 in hash maps with collisions handled via linear probing, and it
  turns out that the efficiency of the data structure decreases dramatically the
  closer to 1 it gets. So, for our implementations, we decided to fix the
  maximum value we're happy with to 0.8, like in the lectures.

What you need to do is to implement the  `insert(newItem)`, `remove(key)`, and
`find(key)` methods. (It's best to do so in this order for the tests to run
properly.) We have discussed the strategy in the lectures and you even have the
pseudocode in the slides. Notice that we have already implemented a
`resize(slotCount)` method for you, which creates a new copy of the backing Java
array `items` of capacity `slotCount`, picks a new hash function at random from
a universal family of hash functions, sets the `size` to 0, and re-inserts the
currently stored items.

Remember to `Run Lab 6 tests` and to compare your code with the solutions, which
you can find in `lab06\solutions`.


## Exercise 2: binary trees


Open and inspect the `lab06\exercises\BinaryTree.java`, featuring the
`BinaryTree<Item>` class, which implements `Container<Item>`. In a way very
similar in spirit to `SinglyLinkedList<Item>` back from Lab 2, we only store one
field, `root`, of type `Node<Item>`. The class `Node<Item>` is defined as a
subclass of `BinaryTree<Item>` further down the file, around line 238. Before
analysing this subclass, however, have a look at the methods of
`BinaryTree<Item>`: there are quite a few, all implemented, and most of them
(such as `height()`, `size()`, `printPreOrder()`, `printInOrder()`,
`printPostOrder()`) call methods from the `Node<Item>` subclass on the node
`root`.

Let's then have a proper look at the `Node<Item>` subclass, which is the one
doing all the heavy lifting (and it contains some methods for you to implement).
As you can see, we have several private fields, some of which being
self-explanatory (`parent`, set by default to `null`; `left`, `right`,
`item`). The others are:
* `tree`, which keeps track of which object of type `BinaryTree<Item>` this node
  belongs to,
* `height`, which stores the height of this node in `tree`,
* `size`, which stores the number of nodes *in the subtree rooted at this node*.

From an implementation point of view, it turns out it's convenient to store this
information on each node. It does mean, however, that this information must be
kept up to date when things change in the tree (such as new items are inserted,
or nodes are rotated; we'll see these in the next few labs), and that is the
reason for which we have methods `calculateSize()` and `calculateHeight()`,
starting at around line 497. Both of them are left for you to implement, and
they should only require one line of code. `calculateSize()` requires you to
calculate the number of nodes stored in the subtree rooted at this node,
assuming you already know the answer for this node's left and right children. At
the moment it returns 0: you need to change this returned value to the correct
one. Similarly for `calculateHeight()`. These methods correspond to the
`height(node)` and `subtreeSize(node)` procedures we discussed in Problem Class
5, so if you need a hint, go have a look at the solutions for that.

There is also a method `level()`, at around line 483, which is for you to
implement. Remember that the level of a node coincides with its depth, which is
defined to be 0 for the root, 1 for the root's children, 2 for the root's
children's children, and so on. What you need to do is to figure out how to
translate this intuitive idea in actual code that calculates the level of this
node correctly! You might need to use the already implemented `hasParent()`
method...

Finally, there are three more methods for you to implement:
`printPreOrder()`, `printInOrder()`, and `printPostOrder()`, also in the
`Node<Item>` subclass. These methods should print to screen, using
`System.out.println()`, all the nodes of the subtree rooted at this node in
pre-order, in-order, and post-order traversal respectively. We saw the idea
behind this in the lectures. Because these are methods supported by objects of
type `Node<Item>`, you can only recursively call them on non-`null`
children. Therefore, make sure to use the already implemented `hasLeft()` and
`hasRight()` methods.

Remember to `Run Lab 6 tests` and to compare your code with the solutions, which
you can find in `lab06\solutions`.