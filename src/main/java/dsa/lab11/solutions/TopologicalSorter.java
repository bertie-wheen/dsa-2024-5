package dsa.lab11.solutions;

import dsa.lab10.solutions.DirectedGraph;

/**
 * Topological sort.
 */
public class TopologicalSorter
{

  /**
   * Get an iterable that yields each vertex of the given directed acyclic graph
   * (DAG) in a topologically-sorted order.
   *
   * @param dag the directed acyclic graph
   * @return the topologically-sorted vertices
   * @param <Vertex> the vertex type
   * @param <Weight> the weight type
   */
  public static <Vertex, Weight> Iterable<Vertex> sort(
    DirectedGraph<Vertex, Weight> dag)
  {
    // NOTE: Perform Full-DFS, then return the vertices in the reversed order
    //       that they were fully visited in.
    return DepthFirstSearcher.fullSearch(dag).visited().reversed();
  }

}
