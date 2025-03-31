package dsa.lab10.exercises;

import dsa.lab04.base.MapItem;
import dsa.lab10.base.Path;
import dsa.lib.DefaultDisplayNameGeneration;
import dsa.lib.DefaultMethodSource;
import dsa.lib.Source;
import dsa.lib.TestNames;
import dsa.lib.lab10.DirectedGraphData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("BreadthFirstSearcher")
@DefaultDisplayNameGeneration
public class BreadthFirstSearcherTests
{

  @ParameterizedTest
  @DefaultMethodSource
  <Vertex, Weight> void returnsSameAsSolution(
    DirectedGraph<Vertex, Weight> graph,
    dsa.lab10.solutions.DirectedGraph<Vertex, Weight> solutionGraph,
    Vertex vertex)
  {
    MapItem<Vertex, Path<Vertex, Integer>>[] paths =
      Source.from(
          BreadthFirstSearcher.search(graph, vertex))
        .sorted(Comparator.comparing((item) -> item.key().hashCode()))
        .array(MapItem.class);
    MapItem<Vertex, Path<Vertex, Integer>>[] solutionPaths =
      Source.from(
          dsa.lab10.solutions.BreadthFirstSearcher.search(solutionGraph, vertex))
        .sorted(Comparator.comparing((item) -> item.key().hashCode()))
        .array(MapItem.class);
    assertArrayEquals(
      solutionPaths,
      paths);
  }


  @SuppressWarnings({"unchecked", "rawtypes"})
  static Source<Arguments> arguments()
  {
    return DirectedGraphData.NON_EMPTY
      .flatReplace(graph ->
        Source.from(graph.items())
          .replace((vertex) ->
            Arguments.argumentSet(
              TestNames.format(
                TestNames.constructor(
                  DirectedGraph.class,
                  Source.from(graph.items()),
                  Source.from(graph.edges())),
                TestNames.method("degree", vertex)),
              graph,
              new dsa.lab10.solutions.DirectedGraph(
                graph.items(),
                graph.edges()),
              vertex)))
      .quadratic()
      .limit();
  }

}
