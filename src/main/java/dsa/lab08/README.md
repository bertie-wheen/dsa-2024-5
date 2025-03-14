# Data Structures and Algorithms Lab 8

In this lab we work only on one data structure: AVL trees.

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
        * `cd N:/dsa` then `Enter` if using lab computer, or `cd` to the
          location
          where you cloned the repository before
        * `git pull` then `Enter`
        * If you get an error "fatal: detected dubious ownership in
          repository ..." (which you likely will):
            * copy and paste the line that the terminal suggests you to run,
              then
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

Open the `src\main\java\dsa\lab08` folder.

## Introduction

Also in this lab there is only one class file for you to examine and work on:
`AVLTree.java`, featuring the `AVLTree<Key, Value>` class, which extends the
`BinarySearchTree<Key, Value>` class (imported from `lab07\solutions`)
that we worked on in the last lab. I refer you to the Further Programming module
for the details of what `AVLTree` being a subclass of
`BinarySearchTree` means, but the key things to remember are:

* Every object of type `AVLTree<Key, Value>` is automatically also an object of
  type `BinarySearchTree<Key, Value>`, reflecting the theoretical definitions of
  AVL trees as particular kinds of binary search trees;
* Every method and field of the superclass `BinarySearchTree<Key, Value>`, as
  well as its inner class `Node<Key, Value>`, is *inherited*, meaning they are
  also present in the subclass `AVLTree<Key, Value>`. Of course, if any were
  private (or package-private, since we're in a different package), they would
  not be directly accessible in the subclass. In `BinarySearchTree<Key, Value>`,
  however, the only such things are a couple of internal helper methods that we
  won't need to call in `AVLTree<Key, Value>` (specifically: `buildNode`, used
  in the constructors; and a `toString` overload, used in the normal
  zero-parameter `toString`).

We have only one private field, `heights`, of type `ChainingHashMap<Node<Key,
 Value>, Integer>`, which is a hash map containing items whose keys are of type
`Node<Key, Value>` (the inner class of `BinarySearchTree<Key, Value>`) and whose
values are integers. The idea is that we store a copy of the nodes of our AVL
tree in this hash map, using the nodes themselves as keys, and these are paired
up with their height -- which is an integer -- in the tree. This is the first
time we make use of the *values* stored in our objects of type `MapItem<Key, 
Value>` and, if I may, what a clever way of doing so! In this case, the items
stored in `heights` are of type `MapItem<Node<Key, Value>, Integer>`. You'll
find the `ChainingHashMap<Key, Value>` class in `lab05\solutions`, and the
`MapItem<Key, Value>` class in `lab04\base`, if you need to refresh your memory
about how they work.

You'll see immediately that the `insert(item)` method is already implemented. It
works as we discussed in the lectures:

* if the tree is empty,
    * it creates a new node containing `item` and makes that the root;
    * it sets the size to 1;
    * and it adds the newly inserted node -- that is,
      `this.root` -- in the map `heights` using the `insert(key, value)`
      method from the `Map<Key, Value>` interface (which you can find in
      `lab04\base`), which effectively creates a new `MapItem` object whose key
      is
      `inserted` and whose value is its height, namely 0;
* otherwise,
    * it inserts a new node containing `item` using the `insert(item)` of the
      `Node<Key, Value>` inner class inherited from
      `BinarySearchTree<Key, Value>`
      on `this.root`, which takes care of updating the tree's `size`;
    * if the size has changed compared to before insertion, then it means we
      have effectively inserted a new node containing `item` (in other words, we
      haven't overwritten another already-present node with the same key as
      `item`'s key), in which case it adds the newly inserted node `inserted` in
      the map `heights` as the key of a `MapItem` with associated value 0 (as
      it's necessarily a leaf, see lecture slides for the intuition); and
      finally it calls the `updateAncestors(node, canStopAfterRebalance)` method
      with input `inserted.parent` and `True`.

The `remove(key)` method is very similar in spirit, the main difference being
that in there we call `updateAncestors` with inputs the removed node and
`False`.

You can find the `updateAncestors` method further down the file. This method
corresponds exactly to what we have seen at the end of Lecture 14. Note that it
calls `recalculateHeight(node)`, which is the next method below, and this in
turns calls `cachedHeight(node)`, which simply returns `-1` if
`node` is `null`, otherwise it gets the value associated with key `node` in
`heights`, namely its height, in $\mathcal{O}(1)$ expected time. This approach
for `recalculateHeight(node)` works because we are working bottom-up: we only
used height values of the children of the input `node`, which are *below* `node`
and which we have therefore already recalculated before, starting from the newly
inserted or just removed node's parent (whose height is calculated by using the
cached height of this new/removed node, which is correctly 0, and that of its
sibling, which has not changed since before the insertion or removal operation).

***REMARK.*** Our implementation, that relies on storing the heights of the
nodes
in a hash map, actually has all the Ordered Map methods perform in
$\mathcal{O}(\log n)$ *expected* time, rather than in worst case scenario. An
alternative strategy would be to store the heights of a node within the node
itself, but this would've made both `BinarySearchTree<Key, Value>` and
`AVLTree<Key, Value>` more complicated, therefore we decided against it for
pedagogical reasons.

## Exercise 1: rotations

The `updateAncestors(node, canStopAfterRebalance)` method not only recalculates
the height of `node`, but it also calls `rebalance(node)`, which is for you to
implement. Before you can do that though, you must work on `rotateC(node)`
and `rotateA(node)`. This is the main task for you in this lab: you can see the
pictorial representation of these two methods in the slides (again at the end of
Lecture 14), and what you need to do is to delete the lines

```java
// TODO: Implement AVLTree.rotateC(Node node)
// NOTE: You will need to do a LOT of link juggling!
// NOTE: Expect to write approx 10+ lines.
```

and translate the picture in the slides in actual code that updates all the
pointers of the appropriate nodes.

Notice that at the end of `rotateC(node)` we are calling `recalculateHeight` on
`node` and on `left`, which is what used to be its left child prior to the
rotation, because their heights might have changed and, after the rotation,
`node` is now below `left`. Don't delete these two lines of code! Same for
`rotateA(node)`.

Remember to `Run Lab 8 tests` and to compare your code with the solutions, which
you can find in `lab08\solutions`.

## Exercise 2: rebalancing

*Now* you're ready to implement `rebalance(node)`. We have discussed this method
and its pseudocode at the end of Lecture 14. Just delete the lines

```java
// TODO: Implement AVLTree.rebalance(Node node)
// NOTE: There are two cases to consider here!
```

and translate the pseudocode in Java code.

Remember to `Run Lab 8 tests` and to compare your code with the solutions, which
you can find in `lab08\solutions`.