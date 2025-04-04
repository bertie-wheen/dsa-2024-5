package dsa.lab11.exercises;

import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;
import dsa.lab10.base.Path;
import dsa.lab10.solutions.DirectedGraph;
import dsa.lib.*;
import dsa.lib.lab11.WeightedDirectedGraphSolutionData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DefaultDisplayNameGeneration
@DisplayName("DAGRelaxer")
public class DAGRelaxerTests
{

  @ParameterizedTest
  @DefaultMethodSource
  <Vertex> void returnsSameAsSolution(
    DirectedGraph<Vertex, Double> graph,
    Vertex source)
  {
    Map<Vertex, Path<Vertex, Double>> paths =
      DAGRelaxer.relaxDAG(graph, source);
    Map<Vertex, Path<Vertex, Double>> solutionPaths =
      dsa.lab11.solutions.DAGRelaxer.relaxDAG(graph, source);

    MapItem<Vertex, Path<Vertex, Double>>[] items =
      Source.from(paths)
        .sorted(Comparator.comparing((item) -> item.key().hashCode()))
        .array(MapItem.class);
    MapItem<Vertex, Path<Vertex, Double>>[] solutionItems =
      Source.from(solutionPaths)
        .sorted(Comparator.comparing((item) -> item.key().hashCode()))
        .array(MapItem.class);
    assertArrayEquals(solutionItems, items);
  }


  //<editor-fold defaultstate="collapsed" desc="arguments">
  @SuppressWarnings("unchecked")
  static Source<Arguments> arguments()
  {
    return WeightedDirectedGraphSolutionData.NON_EMPTY
      .flatReplace(graph ->
        Source.from(graph.items())
          .replace((vertex) ->
            Arguments.argumentSet(
              "DAGRelaxer" +
                TestNames.method(
                  "relaxDAG",
                  new To.PassthroughString(TestNames.constructor(
                    dsa.lab10.exercises.DirectedGraph.class,
                    Source.from(graph.items()),
                    Source.from(graph.edges())).toString()),
                  vertex),
              graph,
              vertex)))
      .quadratic()
      .limit();
  }
  //</editor-fold>

}
