package dsa.lab11.exercises;

import dsa.lab02.base.DynamicSequence;
import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.base.Map;
import dsa.lab05.solutions.ChainingHashMap;
import dsa.lab10.solutions.DirectedGraph;
import dsa.lab11.base.DepthFirstSearchResult;

/**
 * Depth-first search (DFS).
 */
public class DepthFirstSearcher
{

  /**
   * Visit the given vertex.
   * <p>
   * Adds paths to neighbours and visits them, then logs this vertex as visited.
   * <p>
   * Assumes that the vertex is in the graph, and that all other parameters are
   * non-{@code null}.
   *
   * @param graph    the graph containing the vertex
   * @param vertex   the vertex to visit
   * @param paths    the paths found
   * @param visited  the order of vertices visited
   * @param <Vertex> the vertex type
   * @param <Weight> the weight type
   */
  private static <Vertex, Weight> void visit(
    DirectedGraph<Vertex, Weight> graph,
    Vertex vertex,
    Map<Vertex, Vertex> paths,
    DynamicSequence<Vertex> visited)
  {
    // TODO: Implement DepthFirstSearcher.visit(DirectedGraph graph, Vertex vertex, Map paths, DynamicSequence visited)
    // NOTE: Iterate over the vertex's neighbours.
    // NOTE: If the neighbour is new (i.e. we haven't already found a path to
    //       it), note that we can get to it from this vertex (i.e. add this
    //       path) and visit it (i.e. visit its neighbours too).
    // NOTE: _Then_, when the vertex is fully-visited, i.e. all of its
    //       neighbours have been, append it to the visited vertices list.
  }


  /**
   * Perform depth-first search from the given source vertex.
   * <p>
   * Returns both the paths found to all reachable vertices and the order in
   * which they were fully visited.
   *
   * @param graph  the graph
   * @param source the source vertex
   * @return the result of the search
   * @param <Vertex> the vertex type
   * @param <Weight> the weight type
   */
  public static <Vertex, Weight> DepthFirstSearchResult<Vertex> search(
    DirectedGraph<Vertex, Weight> graph,
    Vertex source)
  {
    // NOTE: Initialise the paths map and visited vertices list.
    // NOTE: Unlike in breadth-first search, we're not going to worry about
    //       recording the paths' distances, just the paths themselves.
    // NOTE: For each vertex, then, we simply record its previous vertex, i.e.
    //       how we got to it (or if we started from this vertex, then null).
    Map<Vertex, Vertex> paths = new ChainingHashMap<>();
    paths.insert(source, null);
    DynamicSequence<Vertex> visited = new DynamicArray<>();

    // NOTE: Visit the source vertex's neighbours (and, recursively, its
    //       neighbours, and _their_ neighbours, and their neighbours'
    //       neighbours, etc., until we've found all vertices reachable from
    //       the given source).
    visit(graph, source, paths, visited);

    // NOTE: Return the result of the search.
    return new DepthFirstSearchResult<>(paths, visited);
  }


  /**
   * Perform full depth-first search, starting from every vertex in the graph.
   * <p>
   * Essentially runs single-source DFS from each source in turn, re-using the
   * same paths map and visited list (to not re-visit already-visited vertices).
   *
   * @param graph the graph
   * @return the result of the search
   * @param <Vertex> the vertex type
   * @param <Weight> the weight type
   */
  public static <Vertex, Weight> DepthFirstSearchResult<Vertex> fullSearch(
    DirectedGraph<Vertex, Weight> graph)
  {
    // NOTE: Initialise the paths map and visited vertices list.
    Map<Vertex, Vertex> paths = new ChainingHashMap<>();
    DynamicSequence<Vertex> visited = new DynamicArray<>();

    // NOTE: Iterate over each vertex in the graph.
    for (Vertex vertex : graph)
    {
      // NOTE: Check whether the vertex is new, i.e. we haven't already found a
      //       path to it in a previous iteration of this for loop, i.e. it
      //       wasn't reachable from any of the previous vertices.
      if (!paths.containsKey(vertex))
      {
        // NOTE: If it is new, add the trivial path to it (remember, vertices
        //       are trivially reachable from themselves, with a zero-edge
        //       path, in which case there is no previous vertex).
        paths.insert(vertex, null);

        // NOTE: Visit the vertex's neighbours (and, recursively, all the
        //       meta-neighbours, i.e. the neighbour's neighbours, etc.).
        visit(graph, vertex, paths, visited);
      }
    }

    // NOTE: Return the result of the search.
    return new DepthFirstSearchResult<>(paths, visited);
  }

}
