package dsa.lab10.exercises;

import dsa.lab03.base.Queue;
import dsa.lab03.solutions.LinkedQueue;
import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lab05.solutions.ChainingHashMap;
import dsa.lab10.base.Path;

/**
 * Breadth-first search (BFS).
 */
public class BreadthFirstSearcher
{

  /**
   * Perform breadth-first search on the given graph from the given vertex.
   * <p>
   * Returns a map whose keys are the connected vertices, i.e. the vertices that
   * are reachable starting from the given source, and whose values are
   * information about _how_ they're reachable, i.e. about a path that connects
   * the source to it.
   * <p>
   * The path information for each connected vertex is (a) the distance between
   * the source and it (since we're ignoring edge weights, this is the number of
   * steps/edges to get from the source to it) using the path found by BFS
   * (which will use the minimum possible number of steps, but may not be the
   * shortest if edge weights are taken into account), and (b) the previous
   * vertex in the path found by BFS.
   * <p>
   * For example, if the graph is
   * {@code a -> b -> c -> d    e}
   * and the source is b, then the map returned by BFS will be
   * {@code
   *   b => null, 0;
   *   c => b, 1;
   *   d => c, 2;
   * }.
   *
   * @param graph  the graph
   * @param source the source vertex
   * @return information about the vertices reachable from the given source
   * @param <Vertex> the vertex type
   * @param <Weight> the weight type (on the edges)
   */
  public static <Vertex, Weight> Map<Vertex, Path<Vertex, Integer>> search(
    DirectedGraph<Vertex, Weight> graph,
    Vertex source)
  {
    // NOTE: Initialise the map of path information.
    Map<Vertex, Path<Vertex, Integer>> paths = new ChainingHashMap<>();

    // NOTE: Initially, the only vertex we know is reachable from the given
    //       source is the source itself. This takes 0 steps, and there is no
    //       previous vertex we had to come from to reach it (hence (null, 0)).
    paths.insert(source, new Path<>(null, 0));

    // NOTE: Initialise the frontier, which for BFS is a queue.
    // NOTE: This contains vertices that we've found are connected but have yet
    //       to explore their neighbours.
    Queue<Vertex> frontier = new LinkedQueue<>(source);

    // NOTE: Initially, this is just the source vertex.
    frontier.enqueue(source);

    // NOTE: While there are still connected vertices to explore, dequeue the
    //       next one from the frontier and check its neighbours.
    // NOTE: Its neighbours will also be connected vertices, so - if we haven't
    //       already found paths to them - add information about this path we've
    //       found to them to the map, and also add them to the frontier so that
    //       we'll later also check their neighbours.
    // TODO: Implement BreadthFirstSearcher.search(DirectedGraph graph, Vertex source)

    // NOTE: Return the map of paths to all connected vertices.
    return paths;
  }

}
