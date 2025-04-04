package dsa.lib.lab11;

import dsa.lab10.base.Edge;
import dsa.lab10.solutions.DirectedGraph;
import dsa.lib.IntData;
import dsa.lib.Source;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("rawtypes")
public class WeightedDirectedGraphSolutionData
{

  private static <Vertex> Source<DirectedGraph<Vertex, Double>> graphs(
    Source<Vertex> vertices)
  {
    Random random = new Random();
    return vertices.uniques().cycledSubs().flatReplace((vs) ->
      Source.powerSet(vs, 2, 2)
        .replace((pair, index) -> new Edge<>(
          pair.getAt(0),
          pair.getAt(1),
          random.nextInt(16) / 2.0))
        .uniques((v1, v2) ->
          Objects.equals(v1.source(), v2.source()) &&
            Objects.equals(v1.target(), v2.target()))
        .cycledSubs()
        .replace((es) -> new DirectedGraph<>(vs, es)));
  }


  public static final Source<DirectedGraph>
    EMPTY = Source.singleton(new DirectedGraph()),
    NON_EMPTY = graphs(IntData.ALL).cast(),
    ALL = Source.chain(EMPTY, NON_EMPTY);

}
