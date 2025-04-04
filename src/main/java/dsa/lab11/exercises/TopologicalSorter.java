package dsa.lab11.exercises;

import dsa.lab10.solutions.DirectedGraph;
import dsa.lab11.solutions.DepthFirstSearcher;

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
    // TODO: Implement ToplogicalSorter.sort(DirectedGraph dag)
    // NOTE: You'll want to call either DepthFirstSearcher.search(...) or
    //       DepthFirstSearcher.fullSearch(...).
    // NOTE: A DynamicSequence s is Iterable, and so is s.reversed() (pick one).
    return null;
  }

}
