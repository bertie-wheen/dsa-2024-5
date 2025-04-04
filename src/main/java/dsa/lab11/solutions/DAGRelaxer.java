package dsa.lab11.solutions;

import dsa.lab04.base.Map;
import dsa.lab05.solutions.ChainingHashMap;
import dsa.lab10.base.Edge;
import dsa.lab10.base.Path;
import dsa.lab10.solutions.DirectedGraph;

public class DAGRelaxer
{

  /**
   * Relax the given edge.
   * <p>
   * Assumes that a path has been found to the source.
   * <p>
   * If a path hasn't been found to the target, or if it is longer than this one
   * (from the source, via the edge), update paths to include this new path.
   *
   * @param paths the paths map
   * @param edge  the edge to relax
   * @param <Vertex> the vertex type
   */
  private static <Vertex> void relax(
    Map<Vertex, Path<Vertex, Double>> paths,
    Edge<Vertex, Double> edge)
  {
    Vertex source = edge.source();
    double distance = paths.get(source).distance();
    Vertex target = edge.target();
    double weight = edge.weight();

    // NOTE: Compare this path (which is the path to the source plus this edge)
    //       to the one already found - if the one previously found was longer,
    //       update the path to the vertex to be this newly-found one.
    // NOTE: If a path hasn't already been found, then this is trivially better.
    if (!paths.containsKey(target) ||
      paths.get(target).distance() > distance + weight)
    {
      // NOTE: Update the paths map to contain this newly-found path.
      paths.insert(target, new Path<>(source, distance + weight));
    }
  }


  /**
   * Find the shortest (weighted) paths to each of the vertices reachable from
   * the given source vertex in the given directed acyclic graph (DAG).
   * <p>
   * The weighted distance between two vertices is the sum of the path's edges'
   * weights. Here we consider only weights that are {@code double}s (e.g. 8.2).
   *
   * @param dag    the directed acyclic graph
   * @param source the source vertex
   * @return the shortest (weighted) paths to each reachable vertex and their
   *         (weighted) distances
   * @param <Vertex> the vertex type
   */
  public static <Vertex> Map<Vertex, Path<Vertex, Double>> relaxDAG(
    DirectedGraph<Vertex, Double> dag,
    Vertex source)
  {
    // NOTE: Initialise the paths map.
    Map<Vertex, Path<Vertex, Double>> paths = new ChainingHashMap<>();
    paths.insert(source, new Path<>(null, 0.0));

    // NOTE: Iterate over the vertices in topological order.
    for (Vertex vertex : TopologicalSorter.sort(dag))
    {
      // NOTE: Check whether the vertex is reachable from the source.
      // NOTE: This works for source because it's already in the path map (from
      //       our initialisation of it).
      // NOTE: Any reachable vertices will come in later iterations (due to the
      //       topological ordering), by which time they will have been
      //       relax()ed (in the iterations of the vertices that have outgoing
      //       edges to it, which - again, due to the topological ordering -
      //       will have already happened) and thus added to the path map.
      // NOTE: In the slides we don't worry about this because the infinite
      //       distances mean we can safely relax() even unreachable vertices,
      //       since a distance of infinity + a weight = a distance of infinity,
      //       and so they will be marked as having infinite distance in the
      //       result, which - in that convention - means they're not reachable
      //       (in this convention that's indicated by them not being present
      //       in the map).
      if (paths.containsKey(vertex))
      {
        // NOTE: Relax each of the outgoing edges from this vertex.
        for (Edge<Vertex, Double> edge : dag.adjacencyList(vertex))
        {
          relax(paths, edge);
        }
      }
    }

    // NOTE: Return the shortest paths to each vertex.
    return paths;
  }

}
