package dsa.lab10.exercises;

import dsa.lab02.base.Container;
import dsa.lab02.base.DynamicSequence;
import dsa.lab03.solutions.DynamicArray;
import dsa.lab04.base.Map;
import dsa.lab05.solutions.ChainingHashMap;
import dsa.lab10.base.Edge;
import dsa.lib.Iterators;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A directed graph implemented using adjacency lists.
 *
 * @param <Vertex> the vertex type
 * @param <Weight> the weight type (on the edges)
 */
public class DirectedGraph<Vertex, Weight>
  implements Container<Vertex>
{

  /** The outgoing edges from each vertex in the graph. */
  private Map<Vertex, DynamicSequence<Edge<Vertex, Weight>>> adjacencyLists =
    new ChainingHashMap<>();


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty directed graph.
   */
  public DirectedGraph()
  {
  }


  /**
   * Construct a directed graph containing the given vertices and edges.
   *
   * @param vertices the vertices
   * @param edges    the edges
   * @throws IllegalArgumentException if a source/target vertex of an edge isn't
   *                                  in vertices, or if multiple edges are
   *                                  between the same pair of source and target
   *                                  vertices
   */
  public DirectedGraph(
    Iterable<Vertex> vertices,
    Iterable<Edge<Vertex, Weight>> edges)
    throws IllegalArgumentException
  {
    // NOTE: Insert the vertices.
    // NOTE: We're storing the vertices as the keys of the adjacency lists map.
    // NOTE: Initially they don't have any outgoing edges, so the adjacency list
    //       is an empty sequence, but we will add those in a moment.
    for (Vertex vertex : vertices)
    {
      this.adjacencyLists.insert(vertex, new DynamicArray<>());
    }

    // NOTE: Insert the edges.
    for (Edge<Vertex, Weight> edge : edges)
    {
      Vertex source = edge.source();
      Vertex target = edge.target();

      // NOTE: Check that the edge is valid, i.e. that it is between vertices
      //       that are actually in this graph.
      if (!this.contains(source) || !this.contains(target))
      {
        throw new IllegalArgumentException();
      }

      // NOTE: Get the relevant adjacency list (outgoing edges from the source).
      DynamicSequence<Edge<Vertex, Weight>> adjacencyList =
        this.adjacencyLists.get(source);

      // NOTE: Check if it already contains an edge between the same vertices,
      //       since we're allowing at most one edge between any pair.
      for (Edge<Vertex, Weight> e : adjacencyList)
      {
        if (Objects.equals(target, e.target()))
        {
          throw new IllegalArgumentException();
        }
      }

      // NOTE: Now that we've checked the edge is valid, add it.
      adjacencyList.insertLast(edge);
    }
  }


  //</editor-fold>


  @Override
  public int size()
  {
    return this.adjacencyLists.size();
  }


  @Override
  public boolean contains(Vertex vertex)
  {
    // NOTE: We store an adjacency list for each vertex in the graph, so if the
    //       vertex is in the graph, it will be in our adjacency lists map.
    // TODO: Implement DirectedGraph.contains(Vertex vertex)
    return false;
  }


  public boolean contains(Edge<Vertex, Weight> edge)
  {
    // NOTE: Check whether the edge _might_ be an edge in this graph,
    //       i.e. whether its vertices are in the graph.
    // NOTE: If the vertices that the edge is between are not in the graph, then
    //       there's no chance that the edge itself will be.
    // NOTE: This also means that we can do a lookup in our adjacencyLists map
    //       without worrying about a NoSuchElementException being thrown.
    if (!this.contains(edge.source()) || !this.contains(edge.target()))
    {
      return false;
    }

    // NOTE: Check whether the edge actually is an edge in this graph or not.
    // NOTE: Think about where it would be stored if it were, and check there.
    // TODO: Implement DirectedGraph.contains(Edge edge)
    return false;
  }


  public int degree(Vertex vertex)
    throws NoSuchElementException
  {
    // NOTE: Because this is a directed graph, we really mean the _outgoing_
    //       degree, i.e. deg^+, the number of outgoing edges (or, equivalently,
    //       the number of neighbouring vertices that we can get to in one step
    //       from the given vertex).
    // TODO: Implement DirectedGraph.degree(Vertex vertex)
    return 0;
  }


  public Edge<Vertex, Weight> edge(Vertex source, Vertex target)
  {
    // NOTE: Check whether there _might be_ an edge between the given vertices,
    //       i.e. whether the vertices are in the graph.
    // NOTE: If the vertices aren't in the graph, there can't be an edge between
    //       them in the graph.
    // NOTE: This also means that we can do a lookup in our adjacencyLists map
    //       without worrying about a NoSuchElementException being thrown.
    if (!this.contains(source) || !this.contains(target))
    {
      return null;
    }

    // NOTE: Similarly to contains(Edge edge), consider _where_ such an edge
    //       would be stored, and then look for it there.
    // NOTE: If you don't find it there, then return null.
    // TODO: Implement DirectedGraph.edge(Vertex source, Vertex target)
    return null;
  }


  @Override
  public Iterable<Vertex> items()
  {
    return this.adjacencyLists.keys();
  }


  public Iterable<Edge<Vertex, Weight>> edges()
  {
    return Iterators.flatten(this.adjacencyLists.values());
  }


  public Iterable<Edge<Vertex, Weight>> adjacencyList(Vertex source)
    throws NoSuchElementException
  {
    return this.adjacencyLists.get(source);
  }


  public Iterable<Vertex> neighbours(Vertex source)
    throws NoSuchElementException
  {
    return Iterators.applyEach(this.adjacencyList(source), Edge::target);
  }

}
