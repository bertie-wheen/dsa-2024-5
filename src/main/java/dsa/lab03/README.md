# Data Structures and Algorithms: Lab 3


In this lab we see how to implement in Java dynamic arrays, stacks using dynamic
arrays and singly linked lists, and queues using circular dynamic arrays and
singly linked lists. There are 4 exercises: Exercises 1 and 3 are the difficult
ones, while Exercises 2 and 4 are straightforward (because they use all the
heavy-lifting of Ex. 1 and 3). Remember to test your implementations using 
`Run lab 3 tests` on the top-right of IntelliJ , and also in the
`main.java` file, by creating new objects and inserting/removing objects (for
instance of type `String`), so you can see if your methods behave as 
expected. Finally, always compare your implementation with the provided 
solutions in `src\main\java\lab03\solutions`!

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

Open the `src\main\java\dsa\lab03` folder.


## Exercise 1: Dynamic Arrays


Open and inspect the `lab03\exercises\DynamicArray.java` file. Here we defined
the `DynamicArray<Item>` class for you, which implements the
`DynamicSequence<Item>`interface. You can see that we have two fields: an
array of objects of type `Item` called `items`, and an integer `size`, which
keep tracks of how many items we are currently storing in the dynamic array.
There are also a few methods already implemented:
* `capacity()`, which returns the actual length of the array `items`,
* `get(index)` and `set(index,item)` that do what they are supposed to do as
  instructed by the `StaticSequence<Item>` interface,
* `resize(capacity)`, which "resizes" the underlying `items` array to the new
  desired `capacity`, to be used appropriately when inserting and removing.

What you need to do is to implement the two methods
`insert(index, item)` and
`remove(index)`, using the ideas we saw in Lecture 3. Keep in mind that:

* If you create an empty dynamic array, for instance in the `main` method by
  typing
  ```java
  DynamicArray<String> myArray = new DynamicArray<>();
  ```
  the capacity of the underlying `items` array is 0. Hence make sure, in the
  `insert(index, item)` method, to consider the case in which one wants to
  insert an item in a dynamic array with capacity equal to 0.
* If resizing is appropriate (following the resizing strategy we saw in the
  lectures), then:
  * when inserting, *first* you need to resize the array, and then insert the
    new item in the correct spot,
  * when removing, *first* you remove the correct item, and then you resize the
    array.

### REMARK (Testing your implementations)


You can test whether your methods work as intended in the `main` method inside
the `Main.java` file.

Once you have implemented the insertion methods, you can create a new 
dynamic array in the `main.java` file by running for instance the code:
```java
DynamicArray<String> myArray = new DynamicArray<>("Hello", "World");
```
And you can print to screen your array to check its contents by typing:
```java
System.out.println(To.string(myArray));
```

You can also select the `Run Lab 3 tests` configuration on the top-right of the
IDE to run the tests we have prepared for you, and see if the 
`DynamicArrayTests`
are all ticked green. (If you don't see `Run Lab 3 tests`, expand
`All Configurations`.)

When you think you've got it right, compare your implementation with that
provided in the `lab03\solutions` files, to be sure you *really* got it right.
The automatic tests are not enough!


## Exercise 2: Stacks with Dynamic Arrays and Linked Lists


Open and inspect the `lab03\base\Stack.java` file. It contains the `Stack<Item>`
inteface reflecting what we saw in the lectures, no surprises here.

1. Now open and inspect the `lab03\exercises\ArrayStack.java` file, which
   contains the `ArrayStack<Item>` class implementing the `Stack<Item>`
   interface. As you can see, we have a private field `items` which is of type
   `DynamicArray<Item>` - the class you just implemented in Exercise 1!
   What you need to do is to implement the `push(item)`, `pop()`,
   and `top()` methods, using the appropriate methods that the object `items`
   supports, being of type `DynamicArray<Item>`. Each of these three methods
   should only require one line of code; see the slides in the lectures.
2. Now have a look at `lab03\exercises\LinkedStack.java`, which contains the
   `LinkedStack<Item>` class *also* implementing the `Stack<Item>` interface.
   This time, we have a private field `items` which is of type
   `SinglyLinkedList<Item>`. The compiler knows what the class
   `SinglyLinkedList<Item>` is because we have added the line
   ```java
   import dsa.lab02.solutions.SinglyLinkedList;
   ```
   at the beginning of the file, which means you can in fact use all the methods
   of that class. Similarly to part 1 of this exercise, implement the methods
   `push(item)`, `pop()`, and `top()` using the appropriate methods that the
   object `items` supports, being of type `SinglyLinkedList<Item>`. Again each
   of these three methods should only require one line of code. Make sure to use
   the correct strategy we saw in the lectures to ensure O(1) time for all these
   methods!

## Exercise 3: Circular Dynamic Arrays


Open the `lab03\exercises\CircularDynamicArray.java` file. Here you can find the
`CircularDynamicArray<Item>` class, implementing the `DynamicSequence` interface
which you can find in the imported `lab02\base\DynamicSequence.java` file.

We have three private fields:

* an `Item`-array `items` that will store the items in our sequence,
* an integer `start`, which by default is 0, keeping track of the index in
  `items` storing the first item in our sequence,
* another integer `size`, keeping track of how many items we are currently
  storing.

Like in the `DynamicArray<Item>` class, we have a `capacity()` method returning
the actual length of the array `items`, and a `resize(capacity)` method that
"resizes" the underlying array `items` to the new desired `capacity`. Notice
that it re-orders the items into the new array like we saw in the lectures,
and that it makes use of the `%` operator to wrap around the array.

What you have to do is:

1. Implement the methods `get(index)` and `set(index, item)`. You'll need to use
   the modulo operator `%` to calculate the appropriate indexes (have a look
   at the `resize(capacity)` method, or at the solutions to Problem Class 2
   available on Canvas). Remember that
   `get(0)` should return `items(start)`, `get(1)` should return
   `items((start + 1) % C)` if C is the capacity of the array `items`, and so
   on.
2. Implement the `insert(index, item)` method. Use the same strategy as in
   Exercise 1 for dynamic arrays, but remember to keep using the `%` operator
   and, when resizing is necessary, to call the already implemented `resize
   (capacity)` method with twice the current capacity as input. We wrote the
   pseudocode for this method during Problem Class 2, so make sure to have a 
   look at the solutions for that class.
3. Implement the `remove(index)` method. Again use the same strategy as in
   Exercise 1 for removal, but keep in mind that:
   * you need to use the `%` operator to calculate the right indexes,
   * you have to keep the field `start` up-to-date at all times, in particular when
     dealing with the case in which `index` is `0`.

## Exercise 4: Queues with Circular Dynamic Arrays and Linked Lists

Now we are ready to implement the Queue interface with circular dynamic arrays
and linked lists. Open and inspect the `lab03\base\Queue.java` file. It contains
the `Queue<Item>` interface reflecting what we saw in the lectures.

1. Open and inspect the `lab03\exercises\ArrayQueue.java` file. It contains the
   `ArrayQueue<Item>` class implementing the `Queue<Item>` interface, using a
   circular dynamic array `items`, indeed of type `CircularDynamicArray<Item>`.
   All you need to do is to sit down, relax, and implement the `enqueue(item)`,
   `dequeue()`, and `front()` methods using the appropriate methods supported by
   the object `items`. Each of them should only require one line of code.
2. Open and inspect the `lab03\exercises\LinkedQueue.java` file. It contains the
   `LinkedQueue<Item>` class which also implements the `Queue<Item>` interface,
   but this time we have an underlying singly linked list `items` (an object of
   the class `SinglyLinkedList<Item>`, imported from
   `lab02\base\SinglyLinkedList.java` again). Now implement once more the
   `enqueue(item)`, `dequeue()`, and `front()` methods using the appropriate
   methods supported by the object `items`. Each of them should only require one
   line of code. Think carefully about how to orient the linked list (should
   you put the front of the queue at the head or at the back of the linked
   list? See lecture slides...) to ensure O(1) runtime for all these methods.
