package dsa.lib.lab11;

import dsa.lab10.base.Edge;
import dsa.lab10.solutions.DirectedGraph;
import dsa.lib.IntData;
import dsa.lib.Source;

@SuppressWarnings("rawtypes")
public class DirectedGraphSolutionData
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


  public static final Source<DirectedGraph>
    EMPTY = Source.singleton(new DirectedGraph()),
    NON_EMPTY = graphs(IntData.ALL).cast(),
    ALL = Source.chain(EMPTY, NON_EMPTY);

}
