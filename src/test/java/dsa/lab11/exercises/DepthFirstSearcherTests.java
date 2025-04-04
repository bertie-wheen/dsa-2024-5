package dsa.lab11.exercises;

import dsa.lab04.base.MapItem;
import dsa.lab10.solutions.DirectedGraph;
import dsa.lab11.base.DepthFirstSearchResult;
import dsa.lib.*;
import dsa.lib.lab11.DirectedGraphSolutionData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("DepthFirstSearcher")
public class DepthFirstSearcherTests
{

  @DefaultDisplayNameGeneration
  @DisplayName("search")
  public interface SearchTests
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Vertex, Weight> void returnsSamePathsAsSolution(
      DirectedGraph<Vertex, Weight> graph,
      Vertex source)
    {
      DepthFirstSearchResult<Vertex> result =
        DepthFirstSearcher.search(graph, source);
      DepthFirstSearchResult<Vertex> solutionResult =
        dsa.lab11.solutions.DepthFirstSearcher.search(graph, source);

      MapItem<Vertex, Vertex>[] paths =
        Source.from(result.paths())
          .sorted(Comparator.comparing((item) -> item.key().hashCode()))
          .array(MapItem.class);
      MapItem<Vertex, Vertex>[] solutionPaths =
        Source.from(solutionResult.paths())
          .sorted(Comparator.comparing((item) -> item.key().hashCode()))
          .array(MapItem.class);
      assertArrayEquals(solutionPaths, paths);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Vertex, Weight> void returnsSameVisitedAsSolution(
      DirectedGraph<Vertex, Weight> graph,
      Vertex source)
    {
      DepthFirstSearchResult<Vertex> result =
        DepthFirstSearcher.search(graph, source);
      DepthFirstSearchResult<Vertex> solutionResult =
        dsa.lab11.solutions.DepthFirstSearcher.search(graph, source);

      Vertex[] visited = Source.from(result.visited()).array();
      Vertex[] solutionVisited = Source.from(solutionResult.visited()).array();
      assertArrayEquals(solutionVisited, visited);
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
                "DepthFirstSearcher" +
                  TestNames.method(
                    "search",
                    new To.PassthroughString(TestNames.constructor(
                      DirectedGraph.class,
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


  @DefaultDisplayNameGeneration
  @DisplayName("full search")
  public interface FullSearchTests
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Vertex, Weight> void returnsSamePathsAsSolution(
      DirectedGraph<Vertex, Weight> graph)
    {
      DepthFirstSearchResult<Vertex> result =
        DepthFirstSearcher.fullSearch(graph);
      DepthFirstSearchResult<Vertex> solutionResult =
        dsa.lab11.solutions.DepthFirstSearcher.fullSearch(graph);

      MapItem<Vertex, Vertex>[] paths =
        Source.from(result.paths())
          .sorted(Comparator.comparing((item) -> item.key().hashCode()))
          .array(MapItem.class);
      MapItem<Vertex, Vertex>[] solutionPaths =
        Source.from(solutionResult.paths())
          .sorted(Comparator.comparing((item) -> item.key().hashCode()))
          .array(MapItem.class);
      assertArrayEquals(solutionPaths, paths);
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Vertex, Weight> void returnsSameVisitedAsSolution(
      DirectedGraph<Vertex, Weight> graph)
    {
      DepthFirstSearchResult<Vertex> result =
        DepthFirstSearcher.fullSearch(graph);
      DepthFirstSearchResult<Vertex> solutionResult =
        dsa.lab11.solutions.DepthFirstSearcher.fullSearch(graph);

      Vertex[] visited = Source.from(result.visited()).array();
      Vertex[] solutionVisited = Source.from(solutionResult.visited()).array();
      assertArrayEquals(solutionVisited, visited);
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
                "DepthFirstSearcher" +
                  TestNames.method(
                    "fullSearch",
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


  @Nested
  public class Search
    implements SearchTests
  {
  }


  @Nested
  public class FullSearch
    implements FullSearchTests
  {
  }

}
