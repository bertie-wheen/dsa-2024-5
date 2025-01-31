# Data Structures and Algorithms: Lab 1


A very warm welcome to the Data Structures and Algorithms labs!

In this first lab we'll learn how to set up your machine and a few basic
programming concepts in Java that we'll need for this module.


## Setup


What to do at the start of EVERY lab session (if using lab computers):

* Open the Software Hub and launch:
  * Git for Windows
  * IntelliJ Community Edition
* Open PowerShell and run:
  * If lab 1:
    * `N:`
    * `git clone https://github.com/bertie-wheen/dsa-2024-5 dsa`
  * Else (labs 2 onwards):
    * `cd N:/dsa`
    * `git pull`
    * If you get an error "fatal: detected dubious ownership in
      repository ..." (which you likely will):
      * `git config --global --add safe.directory '%(prefix)///smbhome.uscs.susx.ac.uk/<username>/dsa'`
        (where `<username>` is your username)
      * `git pull`
* Open IntelliJ IDEA and:
  * Open `N:/dsa` as a project
  * In the menu, select `File -> Project Structure` (or press
    `Ctrl+Alt+Shift+S`)
  * Select `Project` in the left sidebar (if it isn't already selected)
  * If there is no SDK:
    * Click on the dropdown, and select `Download JDK`
    * Choose `Amazon Corretto` as the vendor, and click `Download`
    * Click `Apply`, then `OK` to close the project structure dialog


## Interfaces


What are they? A list of method declarations. A "contract": a bunch of methods
usually coming together with an informal explanation, in plain English, of what
they are supposed to do.

Example: have a look at the `lab01\base\StringContainer.java` file, containing
the `StringContainer` interface.

Interfaces can have some methods already implemented "by default". Indeed, the
method `isEmpty()` in the `StringContainer` interface is fully implemented.


## Classes implementing interfaces


A class "implementing" an interface is a class that supports ALL the methods of
the given interface, that is, that has all the methods listed in the interface
together with code that actually implements them. (Methods that have a default
implementation in the interface may be overridden, but they don't have to.)
Although not required from a purely syntactic point of view, we also want these
methods to implement the methods *correctly*, by which we mean that we want them
to behave as instructed in the informal explanations in the interface.

In other words: a class implementing an interface is a class that "fulfills the
contract" specified by the interface. It can also do *more* stuff, have its own
fields and extra methods.

Example: have a look at the `lab01\exercises\StringArray.java` file containing
the `StringArray` class. It's got a Java array as a private field "holding" the
strings. If you're using IntelliJ (as you should), you can click on the
collapsed link "Constructors" to see that we also have two constructors methods:
you can safely ignore them. There's also an implementation of the
`contains(string)` method. You'll notice that the `size()` method contains just
```java
// TODO: Implement StringArray.size()
throw new TODO();
```
This is your very first exercise of this class! In these labs, whenever you see
methods with the lines above in them, you can tell that that method is for *you*
to implement. Simply delete these lines and replace them with the correct code
that does what the method in question is supposed to do. In this case, it should
only be one line. When you think you've got it right, compare your solution with
ours, provided in the `lab01\solutions\StringArray.java` file.

An object of type `StringArray` consists of an underlying array of strings: it's
a *container* of strings, and the user can only interact with such an object by
querying how many strings it contains, by asking if it's empty or not, or by
checking whether a given string does or does not appear in the container. The
user cannot see or access the underlying array of strings.

If you select the `Run tests` run configuration in the top-right, you can run
all the tests for the exercises that have been released thus far (assuming
you're reading this in the first week, currently two: `StringArray` and `Array`)
and check which are passing. If you implemented `StringArray.size()` correctly,
you should see a green tick next to `StringArrayTests`, but still a red
exclamation mark symbol next to `ArrayTests` - these are for the next exercise,
which we turn to now.

(One more thing before we do - getting all green ticks on the tests is great,
but they aren't exhaustive, so there could still be issues with your solution.
We highly recommend looking at the model solutions provided and comparing your
code with it, method by method, to check that you considered all cases and/or
approached it in the right way.)


## Generic types


What if I want to talk about a container of, say, integers? Or characters? Or
Booleans? It would be impractical to define several different interfaces for
each of them. Instead, we use *generic types* as part of the interface's
signature.

Example: have a look at the `lab01\base\Container.java` file, containing the
`Container<Item>` interface. Notice how its method declarations are similar to
those in the `Container` interface, except that now all occurrences of the
`String` type have been replaced with the generic type `Item`.

Now this interface does not specify what actual type `Item` should be: it can be
any type at all, whether a basic one already defined in Java or a custom one
defined by a class that we wrote. (It can even be an arbitrary other interface,
but let's not worry about that for now.) Notice how the definition of the method
`contains(item)` has changed so that it takes, as input, an object of generic
type `Item`.

We can make our class `StringArray` explicitly implementing `Container<String>`
without changing anything inside it: all the methods implemented there actually
fulfill the "contract" given by the interface `Container<Item>` for one specific
type: `String`. All we would need to do is to modify its declaration from
`public class StringArray implements StringContainer` to
`public class StringArray implements Container<String>`. We can, however, do
better: we can define a new class, `Array<Item>`, which *also* features a
generic, unspecified type.

Example: have a look at the `lab01\exercises\Array.java` file containing the
`Array<Item>` interface, which implements `Container<Item>` with methods updated
to accommodate the generic type. Can *you* implement the `size()` method? It
will only require one line of code.

(If you managed `StringArray.size()`, this should be pretty straightforward. The
point of this week's exercises aren't to be hard, but to introduce you to the
mechanics of working on and testing your solutions. Try 'Run tests' again, and
check whether you now have all green ticks!)

But we can't go on forever with unspecified types: when we want to create an
actual *object*, **then** we must specify what actual type we want for our items
to be contained. That is, if we need a container of strings, then we need to
write `Array<String> myStringContainer = new Array<>()`; if we need a container
of integers, then we write `Array<Integer> myIntegerContainer = new Array<>()`,
and so on. We can use whatever type we want instead of `Item`, either already
defined by classes implemented in imported Java libraries or by a class defined
by us. We can even create a container of containers of containers of... some
specified type. Eventually we *must* specify a type. For example,
`Array<Array<Boolean>> myContainerOfBooleanContainers = new Array<>()`.

Try to play with our classes. For instance, try to run the following code in the
main method, and see what happens:
```java
Array<String> myArray = new Array<>("Hello", "world");

System.out.println("size = " + myArray.size());
System.out.println("Is it empty? " + myArray.isEmpty());
System.out.println("Does it contain the word 'Hello'? " + myArray.contains("Hello"));
System.out.println("Does it contain the word 'hello'? " + myArray.contains("hello"));
System.out.println("Does it contain the word `bye`? " + myArray.contains("bye"));
```
(Remember to add `import dsa.lab01.exercises.Array;` at the top of the
`Main.java` file first.)


## Interfaces extending other interfaces


Our `Container<Item>` interface is pretty basic. As we saw, an object belonging
to a class implementing it does not have to do much. Let's make things a bit
more exciting by defining a new interface `BadStaticSequence<Item>` with the
same methods of `Container<Item>` plus some extra ones. We want to give the user
the ability to store a *sequence* of items of the same type given in a specified
order, so that we can talk about the "first" item, the "second" item, and so on,
in the sequence.

Example: have a look at the `lab01\base\BadStaticSequence.java` file, containing
the `BadStaticSequence<Item>` interface with the methods from `Container<Item>`
copied and pasted plus `get(index)` and `set(index, string)`.

(Don't worry about the details of what a sequence is or what the methods should
do, we'll see this better in the lectures.) As the name tries to suggest, this
is not great: there is quite a bit of repeated code, namely the method
declarations already present in the `Container<Item>` interface. To avoid this,
and to make Java actually aware of the close relationship between
`Container<Item>` and the new interface we want, we can define an interface
`StaticSequence<Item>` that explicitly *extends* `Container<Item>`, like so:

Example: have a look at the `lab01\base\StaticSequence.java` file, and notice
the syntax
```java
public interface StaticSequence<Item>
  extends Container<Item>
```
Now any class implementing `StaticSequence<Item>` *must* contain implementations
of all the (not already implemented by default) methods in
`StaticSequence<Item>` **and** in `Container<Item>`. The Java compiler will
complain if you try to define a class implementing `StaticSequence<Item>` and
you fail to provide code for some non-default methods in any of those two
interfaces.

Interfaces can also extend other interfaces which in turn extend other
interfaces, and so on. We will see examples of these throughout this class.

This covers all the Java concepts we'll need for most of the rest of the module.
You're now ready to start! On Thursday, after the last lab session, you will be
able to access `lab02`, which you should start working on as soon as possible.
