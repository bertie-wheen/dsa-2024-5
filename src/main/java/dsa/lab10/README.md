# Data Structures and Algorithms Lab 10


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

Open the `src\main\java\dsa\lab10` folder.


## Introduction


In this lab we implement directed graphs using adjacency lists, as well as the
Breadth First Search algorithm. The implementations will differ in certain
aspects compared to the theoretical presentation in the lectures, but only
marginally, and only when this was necessary due to the quirks of Java or in
order to make the code simpler to understand.

First, open and inspect the `lab10\base\Edge.java` file, that contains the
`Edge<Vertex, Weight>` class. This is quite straightforward to understand: an
object of this class consists of two objects of generic type `Vertex`, namely
its `source` and its `target`, and a `weight` of generic type `Weight`. We have
only seen unweighted graphs for now, so don't worry about this latter field:
we'll need it in the next lab.


## Exercise 1: directed graphs


Open and inspect the `lab10\exercises\DirectedGraph.java` file, that features
the `DirectedGraph<Vertex, Weight>` class, extending `Container<Vertex>`. We
have only field: `adjacencyLists`, which is of type
`Map<Vertex, DynamicSequence<Edge<Vertex, Weight>>>`. Let's unravel all of this:
the object `adjacencyLists` contains objects of type `MapItem<Key, Value>` where
the type `Key` is `Vertex` here, and the type `Value` is (the type of any class
implementing) `DynamicSequence<Item>`, where `Item` here is
`Edge<Vertex, Weight>`. In other words, we are storing in the map
`adjacencyLists` the vertices of our graph paired up with a sequence of edges,
which are supposed to be the *outgoing* edges from that vertex. This is one
inconsistency with what we saw in the lectures, where I said that one stores the
sets $Adj(u)$ (consisting of the vertices adjacent to $u$) as items with a
unique key, namely the vertex $u$ itself, in a map: in this implementation, we
are calling *adjacency list of a vertex `u`* the sequence of all the outgoing
*edges* of `u`, rather than the outgoing *neighbours* of `u`. But of course
these two approaches carry the same information and are very similar in spirit;
the reason for which we decided to go for this is so that the class
`DirectedGraph<Vertex, Weight>` can implement both weighted and unweighted
graphs at once. (If we instead used a sequence of outgoing neighbours to match
the theory, then we would have to create a new class for weighted graphs so that
the adjacency lists would have to store the neighbours paired up somehow with
the weight of the edge to that neighbour, and Java doesn't have pair types...
all rather inelegant.) 

There are a few methods for you to implement:

* `contains(vertex)` is self-explanatory.
* `contains(edge)`: return whether `edge` is stored in this graph or not. For 
this, you'll have to use `this.adjacencyLists`, which supports all the methods 
of the `Map` interface, see `lab04\base`: first find the appropriate adjacency 
list (which is a dynamic sequence of edges) that stands a chance of containing 
`edge`, and then check if that list contains it, using the method `contains` 
provided by the Dynamic Sequence interface.
* `degree(vertex)`: return the outgoing degree of `vertex` (see the lecture 
slides if you can't remember what that is). Again use `this.adjacencyLists` and
 its supported methods; you should only require one line of code.
* `edge(source, target)`: return, if it's stored in this graph, the edge from 
`source` to `target`. Again you'll have to use `this.adjacencyLists`, which 
supports all the methods of the `Map` interface: first find the appropriate 
adjacency list (which is a dynamic sequence of edges) that stands a chance of 
containing an edge from `source` to `target`, and then linearly search for such
 an edge in it, using the methods of the `DynamicSequence` interface, see 
 `lab02\base`. Consider that our adjacency lists are not stored in a sorted 
 order, given that our vertices are not necessarily comparable.

As usual, you can `Run Lab 10 tests`, but always make sure to compare your work
with our solutions, to double check whether you implemented your methods with
the appropriate efficiencies or in case your implementation is incorrect in some
extreme cases we are not testing against.


## Exercise 2: Breadth First Search


First, open and inspect the `lab10\base\Path.java` file, which contains the
`Path<Vertex, Distance>` class. We are going to use (objects of) this class in
the implementations of our Single Source Shortest Paths algorithms, the first of
which we see this week: Breadth First Search. As you can see, it contains a
field `previous` of type `Vertex`, and another field `distance` of generic type
`Distance`. (In our implementations `Distance` will always be `Integer`, but in
principle can be anything.) We also have two *getter* methods for these two
private fields. It'll become clear how to use this class in a moment.

Now open and inspect the `lab10\exercises\BreadthFirstSearcher`, which contains
the `BreadthFirstSearcher` class. This class contains only one method,
`search(graph, source)`, and this is the method that is supposed to implement
the Breadth First Search algorithm. We have started off the implementation
ourselves by defining a queue of vertices, `frontier`, initially containing the
`source` vertex only, like we saw in the pseudocode. However, the rest of our
implementation will differ from the pseudocode we have seen in the lectures in
that:

1. it does not involve colouring vertices,
2. it does not store the values of the current distance estimate of each vertex
   $v$ from the source and its predecessor vertex in a shortest path from the
   source as attributes of $v$. 

In the lectures we used colours of the vertices to be able to distinguish
already-visited vertices from those that we haven't discovered before, and for
convenience we stored the distance estimates and predecessor pointers directly
in the vertices. We can handle both points above in one go by instead storing a
map, `paths`, of `MapItem`s whose keys are vertices and whose values are objects
of type `Path`: the idea is that when, during the execution of BFS, we look at
the neighbours of the current node one by one, if a neighbour `vertex` is not
contained in `paths`, then we insert it into the map in the form of a `MapItem`
whose key is `vertex` and whose value is an object of type `Path` storing the
appropriate `distance` value *for `vertex`*  and its appropriate `previous`
value (following the same strategy described in the pseudocode). There's no need
now to use colours for the vertices: previously discovered vertices will be in
`paths`, and those who have not been discovered yet won't be. Also, no need for
the *initialisation* step we do in the pseudocode (namely setting all distance
estimates to $\infty$ and all `PRED` attributes to `None`): we simply initialise
the appropriate distance estimates and predecessor vertices when we insert a
vertex into `paths`.

The "soul" of the algorithm though is very much reflected in the implementation:
while the queue `frontier` is not empty, first dequeue a vertex, then look at
its neighbours (you can use the already implemented `neighbours` method
supported by the input `graph` for this) and, if a neighbour hasn't been
discovered before, insert it into `paths` as described above and enqueue it in
`frontier`. 

As usual, you can `Run Lab 10 tests`, but always make sure to compare your work
with our solutions, to double check whether you implemented your methods with
the appropriate efficiencies or in case your implementation is incorrect in some
extreme cases we are not testing against.

**REMARK**. By storing the distance estimates and predecessor pointers in a
chaining hash map, instead of as attributes of the vertices themselves, we
achieve a total *expected* runtime of $\mathcal{O}(|V| + |E|)$ for BFS, rather
than worst-case scenario. One *could* instead use a hash map with perfect
hashing, thus achieving a total *worst-case* runtime of $\mathcal{O}(|V| + |E|)$
for BFS, but it would be more complicated to implement, so we decided not to do
that for the sake of clarity.