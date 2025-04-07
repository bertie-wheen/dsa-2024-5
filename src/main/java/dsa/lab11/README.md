# Data Structures and Algorithms Lab 11


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

Open the `src\main\java\dsa\lab11` folder.


## Exercise 1: Depth-First Search


Open and inspect the `lab11\exercises\DepthFirstSearcher.java` file, featuring the `DepthFirstSearcher` class. This class doesn't have any fields, and it only contains three methods:

* `visit(graph, vertex, paths, visited)`, corresponding to the recursive DFS-Visit procedure of the lecture slides and it's partially for you to implement;
*  `search(graph, source)`, which implements the DFS algorithm using `visit` as we saw in the lectures;
*  `fullSearch(graph)`, that implements the Full-DFS algorithm.

All three methods rely on the idea of storing a map `paths` of type `Map<Vertex, Vertex>`, where we store the vertices that we have already visited as keys, paired up with the vertex that discovered them during the execution of our algorithm (the "pred" attribute in the lectures), as values. This is similar to what we did in Lab 10 for BFS, except that now there's no need to store for each vertex its distance from the source, because DFS doesn't actually compute it correctly anyway.

We use this map `paths` as an alternative to the use of colours (as in the lectures) to flag which vertices we have discovered and which we haven't discovered yet. In particular, this means that checking in the lecture slides if a given vertex is white corresponds, in these labs, to checking if the vertex is not a key (of an item) in `paths`, for which you can use the conveniently already implemented method `containsKey(key)` of the `Map` interface, see `lab04\base\Map.java`.

Indeed, `search(graph, source)` first of all initialises an empty map `paths` (corresponding to painting white all vertices) and inserts `source` paired up with `null` in it (corresponding to painting `source` grey and setting its predecessor to `None`); it also initialises a dynamic array `visited` corresponding to the array $F$ in the slides, it calls the static method `visit` on input `graph, source, paths, visited` which starts off the search, and finally returns the resulting `paths` and `visited` wrapped together in an object of type `DepthFirstSearchResult`, which is a class we have defined for you and that you can find in `lab11\base\DepthFirstSearchResult.java`.

`fullSearch(graph)` also creates a new, empty `paths` map and a new, empty `visited` dynamic array; then it considers each vertex in the graph and, if it's not contained in `paths` as a key (that is, if it's painted white), then it inserts it in `paths` (in the lectures we paint it grey) and calls `visit` on it, again returning `paths` and `visited` together.

Now, what you need to do is to implement the `visit` method. You have the pseudocode available in Lecture 19, but you need to translate what it means to check if a vertex is painted white, what it means to paint a vertex grey, and what it means to set its "pred" attribute in this implementation (see above). Ignore the blackening of vertices, which isn't strictly necessary and indeed it's not reflected in this implementation. You can use the `neighbours(vertex)` method supported by `graph`, which returns an iterable of the vertices adjacent to `vertex`, to iterate through the neighbours of `vertex`.

As usual, you can `Run Lab 11 tests`, but always make sure to compare your work
with our solutions, to double-check whether you implemented your methods with
the appropriate efficiencies or in case your implementation is incorrect in some extreme cases we are not testing against.


## Exercise 2: Topological Sort


Open and inspect the `lab11\exercises\TopologicalSorter.java` file, featuring the `TopologicalSorter` class. We only have one method, `sort(dag)`, which is supposed to return an iterable of the vertices of `dag` (a Directed Acyclic Graph / DAG) in a topologically-sorted order. (We only assume that the `DirectedGraph` `dag` is acyclic, we don't enforce it.) Look at the lecture slides for the idea of what you need to do: you'll have to first run Full-DFS on the graph and return the reversed output array. Bear in mind that this array is a Dynamic Sequence, in particular a *Static* Sequence, and therefore it supports a method `reversed()` that returns an iterable, see `lab02\base\StaticSequence.java` (you'll have to expand the Iterators section of the file to see it).

As usual, you can `Run Lab 11 tests`, but always make sure to compare your work
with our solutions, to double-check whether you implemented your methods with
the appropriate efficiencies or in case your implementation is incorrect in some extreme cases we are not testing against.

## Exercise 3: DAG Relaxation


Open and inspect the `lab11\exercises\DAGRelaxer.java` file, featuring the `DAGRelaxer` class. There are only two methods here:

* `relax(paths, edge)`, which is for you to implement (see below),
* `relaxDAG(dag, source)`, which implements the DAG Relaxation algorithm we saw in the lectures.

  Here `dag` is assumed to be a directed acyclic graph (although it being acyclic is not enforced) with weights of type `Double` (to reinforce the idea that when we calculate the weights of paths we are really adding up the weights and not counting the number of edges involved).

  We use a `paths` map that pairs up each vertex `vertex` (as key) to an object of type `Path<Vertex, Double>` (see `lab10\base\Path.java`) consisting of the predecessor vertex in a shortest path from `source` to `vertex` and the distance estimate of `vertex` from `source`.

  This `paths` map is empty to begin with and we immediately insert the vertex `source` as key of a `MapItem` whose value contains `null` as predecessor and `0.0` as distance from `source` itself.
  
  Then it consider each vertex `vertex` of the graph in topologically-sorted order and, if `vertex` is stored in the `paths` map (which means that its distance estimate is not $\infty$, in the language of the lectures), it relaxes each outgoing edge from `vertex`. 

The `relax(paths, edge)` is for you to implement. It takes as input a map `paths` that is supposed to contain some vertices of a graph (in fact, when called in `relaxDAG(dag, source)` they will be exactly those vertices of `dag` which have been discovered so far to be reachable from `source`) paired up with a predecessor vertex and a distance estimate, and an edge.

What you need to do is to implement what we saw in the lecture slides: if the distance estimate of the edge's target is greater than the sum of the distance estimate of the edge's source and the weight of the edge, then set the target's predecessor to the source and update its distance estimate to match the new, shorter value we just found. In doing so, you'll need to use `paths` and its methods; in particular, you must handle the case when the target is *not* stored in `paths`.

As usual, you can `Run Lab 11 tests`, but always make sure to compare your work
with our solutions, to double-check whether you implemented your methods with
the appropriate efficiencies or in case your implementation is incorrect in some extreme cases we are not testing against.
