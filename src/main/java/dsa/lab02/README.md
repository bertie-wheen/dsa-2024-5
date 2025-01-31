# Data Structures and Algorithms: Lab 2


In this lab we see how to implement in Java the Container, Static Sequence, and
Dynamic Sequence interfaces, as well as their solutions discussed in the
lectures of Week 1, namely Static Arrays and Linked Lists.


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

Open the `src\main\java\dsa\lab02` folder.


## Exercise 1: Static Arrays


In the `base` subproject there are five `.java` files, each containing a
different interface. Open the following files one by one: first read the
overview given here, then read through the actual file to check its contents:

* `Container.java`, featuring the `Container<Item>` interface: this is a
  "revamped" version of the interface in `lab01`. It contains declarations for
  the methods `size()`, `isEmpty()`, and `contains(item)`, the latter of which
  is already implemented by default. It also has some extra code for iteration,
  which you can safely ignore.
* `StaticSequence.java`, featuring the `StaticSequence<Item>` interface:
  this interface extends `Container<Item>`, so any class implementing
  `StaticSequence<Item>` must also implement the methods listed in
  `Container<Item>`. The new methods in `StaticSequence<Item>` are the ones seen
  in the lectures: `get(index)`, `set(index, item)`, together with extra methods
  `first()`, `setFirst(item)`, `last()`, and `setLast(item)` which are already
  implemented by default as special cases of `get(index)` and `set(index)`. It
  also features a `swap(indexA, indexB)` method which will be useful in later
  labs. You can then ignore the iterator methods at the bottom of the file.
* `DynamicSequence.java`, featuring the `DynamicSequence<Item>` interface: this
  interface extends in turn `StaticSequence<Item>` and contains the declarations
  for the methods we saw in class, namely `insert(index, item)` and
  `remove(index)`, as well as (already implemented as special cases of the
  previous two methods) `insertFirst/Last(item)` and `removeFirst/Last()`. Again
  you can ignore the iterators and the forward/reverse iterator classes.

Now, open the `exercises\StaticArray.java` file. Here we defined the
`StaticArray<Item>` class as an implementation of the `DynamicSequence<Item>`
interface for you, except we didn't implement the methods `insert(index, item)`
and `remove(index)`. That's your job! Delete the lines:
```java
// TODO: Implement StaticArray.insert(int index, Item item)
throw new TODO();
```
and replace them with code that correctly implements those two methods. Check
the lecture slides to see what the intuitive idea is behind insertion and
removal in a static array.

Note that to create a generic array in Java, you can't write
```java
Item[] array = new Item[size];
```
and instead have to write something like
```java
Item[] array = (Item[]) new Object[size];
```
(there are some problems with this, and there are some other ways to do it, but
often you should find that this is okay - for more info
[see here](https://stackoverflow.com/questions/529085/how-can-i-create-a-generic-array-in-java)).


### REMARK (Testing your implementations)


You can test whether your methods work as intended in the `main` method inside
the `Main.java` file.

You can create new `StaticArray`s like so:
```java
StaticArray<String> myArray = new StaticArray<>("Hello", "World");
```
and then print to screen its contents like this:
```java
for (String name : myArray) { System.out.println(name); }
```
Try inserting and deleting items at the beginning, end, or in the middle of the
array, and see what happens!

You can also select the `Run Lab 2 tests` configuration on the top-right of the
IDE to run the tests we have prepared for you, and see if the `StaticArrayTests`
are all ticked green. (If you don't see `Run Lab 2 tests`, expand
`All Configurations`.)

When you think you've got it right, compare your implementation with that
provided in the `lab02\solutions` files, to be sure you *really* got it right.
The automatic tests are not enough!


## Exercise 2: Singly-Linked Lists


For *practical convenience*, in the `lab02\base` subproject we have also defined
two interfaces `LinkedNode` and `LinkedList`. These are there so that we can
define Singly-Linked Lists and Doubly-Linked Lists (which are data structures)
as two different classes, `SinglyLinkedList` and `DoublyLinkedList`,
implementing the same `LinkedList` interface, without having to repeat lots of
code.

The idea is that an object of a class implementing the `LinkedList` interface
consists of zero or more nodes (objects of a class implementing the interface
`LinkedNode`), each of which stores a reference to the list it belongs, and to
the item it contains.

Now, open the following files in `lab02\base`:

* `LinkedList.java`, featuring the `LinkedList<Item>` interface: this interface
  extends `DynamicSequence<Item>` by adding a new method, `node(index)`, that is
  supposed to return the node in the list at the given index. There are also a
  couple of methods, `firstNode()` and `lastNode()`, already implemented using
  `node(index)`. Finally, the `get(index)` and `set(index, item)` methods coming
  from the underlying `DynamicSequence<Item>` interface are also already
  implemented (in fact, *overridden*), this time using `node(index)` and also
  methods from the `LinkedNode` interface. You can ignore the iteration methods
  at the bottom of the file.

* `LinkedNode.java`, featuring the `LinkedNode<Item>` interface: this interface
  contains declarations for methods:
  * returning the list to which the node belongs (`list()`) and the item it
    contains (`item()`),
  * replacing the current item stored in the node with a new item
    (`setItem(item)`),
  * returning the node sitting before (`previous()`) and after (`next()`) the
    current node in the list it belongs to,
  * inserting an item in a new node in the list the current node belongs to,
    either immediately before it (`insertPrevious(item)`) or immediately after
    it (`insertNext(item)`), and similarly for removing the item stored in the
    node before or after the current one (`removePrevious()` and
    `removeNext()`)
  * removing the current node from the list it belongs to and returning the
    stored item (`remove()`).

  It also contains already-defined methods to check whether the current node is
  the first/last one, or if it has a previous or next node, in the list it
  belongs to.

Now open the `exercises\SinglyLinkedList.java` file. Here we defined the
`SinglyLinkedList<Item>` class as an implementation of the `LinkedList<Item>`
interface we discussed at length above. Notice that **we keep a reference to
both the first and the last item** in the list, as suggested at the end of
Lecture 2; this will be useful for next week's labs. Notice also that this file
contains a subclass, `Node<Item>` (at line 109), which implements the
`LinkedNode<Item>` interface and features, as private fields, a
`SinglyLinkedList<Item>` list which is meant to be the linked list to which the
node belongs to, an item of type `Item` which is meant to contain the item the
node is storing, and the next `Node<Item>` of the node. In both classes there
are methods left for you to implement. Do them in the following order:

1. `insertPrevious(item)` and `insertNext(item)` in the `Node<Item>` subclass.
2. `removeNext()` in the `Node<Item>` subclass. (Note that this should be O(1)!)
   This will make also the `remove()` and `removePrevious()` automatically work,
   as we implemented them already for you.
3. `node(index)` in the `SinglyLinkedList<Item>` class.
4. `insert(index, item)` and `remove(index)` in the `SinglyLinkedList<Item>`
   class.

When implementing the methods in the `SinglyLinkedList<Item>` class, use the
already-implemented methods in the `Node<Item>` subclass. And remember to update
the references to `first` and `last` throughout!

Once you have implemented the insertion methods, you can create a new linked
list in the `main.java` file by running for instance the code:
```java
SinglyLinkedList<String> myList = new SinglyLinkedList<>("Hello", "World");
```
And you can print to screen your list to check its contents by typing:
```java
System.out.println(To.string(myList));
```
Test your implementations by trying to insert and remove items at various
indexes! And also with `Run tests`. When everything looks correct, compare your
implementation with ours, which you can find in `lab02\solutions`.


## Exercise 3: Doubly-Linked Lists


Open the `exercises\DoublyLinkedList.java` file. Here we defined the
`DoublyLinkedList<Item>` class as an implementation of the `LinkedList<Item>`
interface we discussed at length above. Notice that it contains a subclass,
`DoublyLinkedList.Node<Item>`, which implements the `LinkedNode<Item>`
interface. In both classes there are methods left for you to implement. Follow
the same order as in the singly-linked case of Exercise 2, but remember to take
into account the fact that now nodes store a reference not just to the next
node, but also the previous. This means that you should be able to implement
some of the methods more efficiently, but also means that you need to remember
to update `previous` references as well as `next` ones when making
modifications. Also, note that for the `Node` class, you are asked to implement
`remove` rather than `removeNext` (again in O(1) - though note that for
`SinglyLinkedList`, `remove`, defined in terms of `removeNext`, was O(n)!).
