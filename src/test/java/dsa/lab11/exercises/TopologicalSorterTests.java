package dsa.lab11.exercises;

import dsa.lab10.solutions.DirectedGraph;
import dsa.lib.*;
import dsa.lib.lab11.DirectedGraphSolutionData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DefaultDisplayNameGeneration
@DisplayName("TopologicalSorter")
public class TopologicalSorterTests
{

  @ParameterizedTest
  @DefaultMethodSource
  <Vertex, Weight> void returnsSameAsSolution(
    DirectedGraph<Vertex, Weight> graph)
  {
    Iterable<Vertex> vertices =
      TopologicalSorter.sort(graph);
    Iterable<Vertex> solutionVertices =
      dsa.lab11.solutions.TopologicalSorter.sort(graph);

    Vertex[] vertexArray = Source.from(vertices).array();
    Vertex[] solutionVertexArray = Source.from(solutionVertices).array();
    assertArrayEquals(solutionVertexArray, vertexArray);
  }


  //<editor-fold defaultstate="collapsed" desc="arguments">
  @SuppressWarnings("unchecked")
  static Source<Arguments> arguments()
  {
    return DirectedGraphSolutionData.NON_EMPTY
      .flatReplace(graph ->
        Source.from(graph.items())
          .replace((vertex) ->
            Arguments.argumentSet(
              "TopologicalSorter" +
                TestNames.method(
                  "sort",
                  new To.PassthroughString(TestNames.constructor(
                    DirectedGraph.class,
                    Source.from(graph.items()),
                    Source.from(graph.edges())).toString())),
              graph)))
      .quadratic()
      .limit();
  }
  //</editor-fold>

}
