package dsa.lab10.base;

/**
 * Information about a path (of zero or more edges) from some source to some
 * target vertex.
 * <p>
 * Contains the previous vertex visited in the path from source to target, and
 * some measure of the total distance from the source to the target.
 * <p>
 * The idea is that there will be other such {@link Path} objects giving
 * information about any previous steps in the path (e.g. how to get to the
 * previous vertex).
 *
 * @param <Vertex>   the vertex type
 * @param <Distance> the distance measure type
 */
public class Path<Vertex, Distance>
{

  /**
   * The previously-visited vertex before the reaching the target.
   * <p>
   * If the source and target are the same, i.e. this is a zero-edge path, then
   * there is no previous vertex, so in that case this is {@code null}.
   */
  private Vertex previous;


  /**
   * The distance from the source to the target, using this path.
   * <p>
   * This could be the sum of edge weights, or just the number of edges.
   */
  private Distance distance;


  /**
   * Construct a path step with the given information.
   *
   * @param previous the previous vertex in the path
   * @param distance the distance from the source
   */
  public Path(Vertex previous, Distance distance)
  {
    this.previous = previous;
    this.distance = distance;
  }


  /**
   * Return the previously-visited vertex.
   *
   * @return the previous vertex
   */
  public Vertex previous()
  {
    return this.previous;
  }


  /**
   * Return the cumulative distance.
   *
   * @return the distance
   */
  public Distance distance()
  {
    return this.distance;
  }

}
