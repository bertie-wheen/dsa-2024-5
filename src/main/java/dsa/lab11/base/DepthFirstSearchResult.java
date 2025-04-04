package dsa.lab11.base;

import dsa.lab02.base.DynamicSequence;
import dsa.lab04.base.Map;

/**
 * The result of performing (normal or full) depth-first search.
 *
 * @param <Vertex> the vertex type
 */
public class DepthFirstSearchResult<Vertex>
{

  /** Connected vertices, and the paths found to them. */
  private Map<Vertex, Vertex> paths;


  /** The order in which vertices were fully explored. */
  private DynamicSequence<Vertex> visited;


  /**
   * Construct a depth-first search result.
   *
   * @param paths   the paths found to connected vertices
   * @param visited the order vertices were finished visiting in
   */
  public DepthFirstSearchResult(
    Map<Vertex, Vertex> paths,
    DynamicSequence<Vertex> visited)
  {
    this.paths = paths;
    this.visited = visited;
  }


  /**
   * Get the paths found to connected vertices.
   *
   * @return the paths
   */
  public Map<Vertex, Vertex> paths()
  {
    return this.paths;
  }


  /**
   * Get the order vertices were finished being visited in.
   *
   * @return the order of vertices visited
   */
  public DynamicSequence<Vertex> visited()
  {
    return this.visited;
  }

}
