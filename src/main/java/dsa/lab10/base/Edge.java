package dsa.lab10.base;

import dsa.lib.To;

import java.util.Objects;

/**
 * An edge in a directed graph.
 *
 * @param <Vertex> the vertex type
 * @param <Weight> the weight type
 */
public class Edge<Vertex, Weight>
{

  /** The source vertex. */
  private Vertex source;


  /** The target vertex. */
  private Vertex target;


  /** The weight data. */
  private Weight weight;


  /**
   * Construct an edge between the given vertices with no weight data.
   *
   * @param source the source vertex
   * @param target the target vertex
   */
  public Edge(Vertex source, Vertex target)
  {
    this(source, target, null);
  }


  /**
   * Construct an edge between the given vertices with the given weight.
   *
   * @param source the source vertex
   * @param target the target vertex
   * @param weight the weight data
   */
  public Edge(Vertex source, Vertex target, Weight weight)
  {
    this.source = source;
    this.target = target;
    this.weight = weight;
  }


  /**
   * Get the source vertex.
   *
   * @return the source vertex
   */
  public Vertex source()
  {
    return this.source;
  }


  /**
   * Get the target vertex.
   *
   * @return the target vertex
   */
  public Vertex target()
  {
    return this.target;
  }


  /**
   * Get the weight data ({@code null} if there is none).
   *
   * @return the weight data
   */
  public Weight weight()
  {
    return this.weight;
  }


  //<editor-fold defaultstate="collapsed" desc="equals()+hashCode()+toString()">


  @Override
  public boolean equals(Object that)
  {
    if (that == null || this.getClass() != that.getClass())
    {
      return false;
    }
    Edge<?, ?> that_ = (Edge<?, ?>) that;
    return Objects.equals(this.source, that_.source) &&
      Objects.equals(this.target, that_.target) &&
      Objects.equals(this.weight, that_.weight);
  }


  @Override
  public int hashCode()
  {
    return Objects.hash(this.source, this.target, this.weight);
  }


  @Override
  public String toString()
  {
    return To.string(this);
  }


  //</editor-fold>

}
