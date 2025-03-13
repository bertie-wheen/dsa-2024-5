# Data Structures and Algorithms Lab 7


In this lab we work only on one data structure: Binary Search Trees.


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

Open the `src\main\java\dsa\lab07` folder.


## Introduction


In this lab there is only one class file for you to examine and work on:
`lab07\exercises\BinarySearchTree.java`, featuring the `BinarySearchTree<Key, 
Value>` class. This class implements the `OrderedMap<Key, Value>` interface
imported from
`lab05\base`, and it's defined for generic types `Key` and `Value`, where
`Key` must implement the `Comparable<Key>` interface (see `lab05\README.md`
for a refresher on what this means).

The `BinarySearchTree<Key, Value>` class contains just two fields:
* a protected object `root` of type `Node<Key, Value>`, set to `null` by
  default,
* an integer `size`, keeping track of how many nodes (hence items) the tree is
  storing, set to 0 by default.

It also has several constructors, the last of which implements
the $\Theta(n \log n)$ algorithm we saw at the end of Lecture 11 to build a
binary search tree from an array of items.

Similarly to last week's `BinaryTree<Key, Value>` class, this class also
contains a nested class, `Node<Key, Value>`, which you can find at around line
193. Indeed, `BinarySearchTree<Key, Value>` features several methods, all of
which are already implemented, and most of them call analogous methods defined
in the `Node<Key, Value>` nested class on the `root` object.

Let's then have a proper look at the nested class `Node<Key, Value>`. As you can
see, each object of this class keeps a reference to the parent node, to the left
and right children nodes, to the tree it belongs, and to the item of type
`MapItem<Key, Value>` it contains.


## Exercise 1: finding


You can see that the `find(key)` method from the `OrderedMap<Key, Value>`
interface in the `BinarySearchTree<Key, Value>` outer class, around line 133,
returns `this.findNode(key).item`, that is it calls the `findNode(key)`
method just above it, which, in turn, calls `this.root.findNode(key)`. And
*this* is the method you need to implement, down in the `Node<Key, Value>`
class, at around line 222. It is supposed to return an object of type
`Node<Key, Value>`, namely the node in the subtree of `this` node (the one
calling this method we're implementing) whose item's key is the input `key`.
This method corresponds to `subtreeSearch(x,k)` that we have seen in the
lectures, and it works essentially like binary search: compare `key` with
`this.item.key()` (using `key.compareTo(this.item.key())`): if the result is
positive and `this.right` is not `null`, then recursively return
`this.right.findNode(key)`, but if `this.right` *is* null, then you need to
*throw an exception*, that is you need to type:
```java
throw new NoSuchElementException();
```
(you'll see how to handle exceptions properly in Further Programming, so don't
worry for now if you don't know what exceptions are for now). If the result of
the comparison is negative, do something analogous to the above; and just return
`this` if the result is `0`.

Remember to `Run Lab 7 tests` and to compare your code with the solutions, which
you can find in `lab07\solutions`.


## Exercise 2: inserting


The `insert(item)` method from the `OrderedMap<Key, Value>`
interface in the `BinarySearchTree<Key, Value>` outer class, around line 140,
creates a new root node containing the input `item` if the current tree is
empty, else it calls `this.root.insert(item)` from the `Node<Key, Value>`
class, at around line 242 (if you've done Exercise 1, it'll be further down due
to the code you wrote in `findNode(key)`).

This method is supposed to create a new object of type `Node<Key, Value>`,
containing `item`, to insert it as the appropriate child of the appropriate
node, and to return the node that was just inserted. Have a look at the lecture
slides for the intuitive idea (also in pseudocode in the procedure
`subtreeInsert(v,z)` in Lecture 11): you'll have to follow the same binary
search strategy as in `findNode(key)` in that you'll have to compare
`item.key()` with `this.item.key()` and recurse either left or right depending
on the outcome of the comparison. If you find yourself "falling off the tree",
which means that you'd like to recurse on a child that is however `null`, then
at that point create a new object of type `Node<key, Value>` containing `item`
and set that new object as the missing child. For this you'll need to use the
one constructor for the `Node<key, Value>` class, that forces you to specify at
once, for the new node, who the parent is (the current node), what tree it
should belong to (the same tree as the current node's), what left child this new
node should have (`null`), what item it should contain (the input `item`), and
what right child this new node should have (again in this case `null`). If, on
the other hand, you end up in your recursive calls on a node whose item's key is
the same as `item.key()`, then simply set `this.item` to be `item`, as dictated
by the Ordered Map interface.

In all of this, make sure to keep the tree's size (that is, `this.tree.size`)
up to date, and remember to return the node where the new item was inserted.
This is not actually required by the `OrederedMap<Key, Value>` interface, and
indeed the `insert(item)` method of the `BinarySeearchTree<Key, Value>`
outer class doesn't use this returned value, but it will be necessary for future
labs.

Remember to `Run Lab 7 tests` and to compare your code with the solutions, which
you can find in `lab07\solutions`.


## Exercise 3: removing


The `remove(key)` method from the `OrderedMap<Key, Value>`
interface in the `BinarySearchTree<Key, Value>` outer class, around line 154,
first calls `this.findNode(key)` to find the node whose item's key is `key`
(if unsuccessful, this call will throw an exception), saves the result in a
variable `node`, stores a copy of `node.item` in another variable `item`, calls
`node.remove()` from the `Node<Key, Value>` class, at around line 261
(actually further down if you've done Exercise 1 and/or 2), and finally returns
`item`.

The `remove()` method from the `Node<Key, Value>` class is supposed to remove
the node that's calling it (namely `this`) from the tree it belongs to, which is
`this.tree`. (In particular, it's responsible to update `this.tree.size`.) This
method corresponds to the `removeNode(z)` procedure we saw in Lecture 11, where
the input `z` in there is now `this` itself, calling `remove()`. This method
must return the node that ends up being effectively removed -- it might be
`this` or, if not, it'll be one of its descendant leaves.

All you need to do is to follow the pseudocode of `removeNode(z)` and
translating it into Java in this setting. You'll need to use the methods
`minNode()` and `maxNode()` of the `Node<Key, Value>`, further down the file, so
make sure to implement them first. (Again we discussed this in Lecture 11, where
you can find the very simple pseudocode if you're struggling). Also, remember to
update `this.tree.size` by decreasing it before actually returning the removed
node.

Remember to `Run Lab 7 tests` and to compare your code with the solutions, which
you can find in `lab07\solutions`.


## Exercise 4: next and prev


The `previous(key)` and `next(key)` methods from the `OrderedMap<Key, Value>`
interface in the `BinarySearchTree<Key, Value>` outer class, around line 164 and
170, call `this.root.previous(key)` and `this.root.next(key)`
respectively, from the `Node<Key, Value>` class, around lines 276 and 291
(further down if you've done any of the previous exercises). In Lecture 12 we
presented the pseudocode for `next(key)`, all you need to do here is to
translate it into Java, again remembering that keys are comparable. Are you able
to amend the code of `next(key)` to make it work correctly for
`previous(key)` instead?

Remember to `Run Lab 7 tests` and to compare your code with the solutions, which
you can find in `lab07\solutions`.
