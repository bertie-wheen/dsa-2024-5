package dsa.lab10.solutions;

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
    //       next one from the frontier and explore its neighbours.
    while (!frontier.isEmpty())
    {
      Vertex vertex = frontier.dequeue();

      // NOTE: If this vertex (which we already know is connected) has any
      //       neighbours, then transitively they are also connected.
      // NOTE: The source is connected to this vertex, and this vertex is
      //       connected to its neighbour, so the source is connected to the
      //       neighbour.
      // NOTE: The path information to any of the neighbours is going to be the
      //       same. The previous vertex in the path is this vertex (that we
      //       dequeued from frontier), and the number of steps is however many
      //       steps it took to get from the source to this vertex, plus one
      //       (representing the step from this vertex to its neighbour).
      int steps = paths.get(vertex).distance();
      Path<Vertex, Integer> newPath = new Path<>(vertex, steps + 1);
      for (Vertex neighbour : graph.neighbours(vertex))
      {
        // NOTE: Check that we haven't already found a path to this neighbour.
        // NOTE: If we have, then skip it.
        if (paths.containsKey(neighbour))
        {
          continue;
        }

        // NOTE: Insert path information about how to get from the source to the
        //       neighbour to the map, and enqueue the neighbour to the frontier
        //       (so that later we'll also check if it has any new neighbours).
        paths.insert(neighbour, newPath);
        frontier.enqueue(neighbour);
      }
    }

    // NOTE: Return the map of paths to all connected vertices.
    return paths;
  }

}
