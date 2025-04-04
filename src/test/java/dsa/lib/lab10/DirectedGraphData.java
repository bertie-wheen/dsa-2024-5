package dsa.lib.lab10;

import dsa.lab10.base.Edge;
import dsa.lab10.exercises.DirectedGraph;
import dsa.lib.IntData;
import dsa.lib.Source;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public class DirectedGraphData
{

  private static <Vertex, Weight> Source<DirectedGraph<Vertex, Weight>> graphs(
    Source<Vertex> vertices)
  {
    return vertices.uniques().cycledSubs().flatReplace((vs) ->
      Source.powerSet(vs, 2, 2)
        .replace((pair) ->
          new Edge<Vertex, Weight>(pair.getAt(0), pair.getAt(1)))
        .uniques()
        .cycledSubs()
        .replace((es) -> new DirectedGraph<>(vs, es)));
  }


  public static <Vertex, Weight> DirectedGraph<Vertex, Weight> withoutVertex(
    DirectedGraph<Vertex, Weight> graph,
    Vertex vertex)
  {
    return new DirectedGraph<>(
      Source.from(graph.items()).skipFirst(vertex),
      Source.from(graph.edges()).filter((edge) ->
        !Objects.equals(edge.source(), vertex) &&
          !Objects.equals(edge.target(), vertex)));
  }


  public static <Vertex, Weight> DirectedGraph<Vertex, Weight> withoutEdge(
    DirectedGraph<Vertex, Weight> graph,
    Edge<Vertex, Weight> edge)
  {
    return new DirectedGraph<>(
      graph.items(),
      Source.from(graph.edges()).skipFirst(edge));
  }


  public static final Source<DirectedGraph>
    EMPTY = Source.singleton(new DirectedGraph()),
    NON_EMPTY = graphs(IntData.ALL).cast(),
    ALL = Source.chain(EMPTY, NON_EMPTY);

}
