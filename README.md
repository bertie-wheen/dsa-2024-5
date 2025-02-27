# Data Structures and Algorithms, 2024-5: Labs


## Docs


[JavaDoc for the labs is hosted here.](https://bertie-wheen.github.io/dsa-2024-5)


## Notes


1. [Getting Started](https://github.com/bertie-wheen/dsa-2024-5/tree/trunk/src/main/java/dsa/lab01)
2. [Static Arrays & Linked Lists](https://github.com/bertie-wheen/dsa-2024-5/tree/trunk/src/main/java/dsa/lab02)
3. [Dynamic Arrays, Stacks & Queues](https://github.com/bertie-wheen/dsa-2024-5/tree/trunk/src/main/java/dsa/lab03)
4. [Sorting, Searching & Maps](https://github.com/bertie-wheen/dsa-2024-5/tree/trunk/src/main/java/dsa/lab04)
5. [Ordered Maps, Hashing & Chaining](https://github.com/bertie-wheen/dsa-2024-5/tree/trunk/src/main/java/dsa/lab05)
6. [Probing & Binary Trees](https://github.com/bertie-wheen/dsa-2024-5/tree/trunk/src/main/java/dsa/lab06)


## Setup


What to do at the start of a lab session (if using lab computers):

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


## Structure


These labs are structured into
[packages](https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html), one
package per lab (e.g. `dsa.lab01` for lab 1). All are subpackages of the
top-level `dsa` package, and each is further structured into subpackages.

Each lab contains an `exercises` subpackage (e.g. `dsa.lab01.exercises`)
that contains one or more exercises. Each exercise is a partially-implemented
[class](https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html) with one
or more `TODO`s that you are expected to fill in in order to complete the
implementation.

Model solutions for each of the exercises are provided in the `solutions`
subpackage (e.g. `dsa.lab01.solutions`).

Some labs also include a `base` subpackage that contains given code that is
needed for the exercises, and thus - though these files aren't exercises and you
don't need to edit them - you should have a look through them.

You often don't need to worry too much about their actual code - the most
important thing is to look at their type signatures: For example, if it's a
class, what you should look for are what methods it has, what parameters they
take, what they're supposed to do and what they return.

The most commonly-provided `base` code are interfaces defining abstract data
types (such as in `dsa.lab01.base.StringContainer`), as exercises will often be
to implement various concrete data types as subclasses of these ADTs (e.g. as in
`dsa.lab01.exercises.StringArray`).
