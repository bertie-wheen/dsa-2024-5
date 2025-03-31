package dsa.lab10.exercises;

import dsa.lab10.base.Edge;
import dsa.lib.DefaultDisplayNameGeneration;
import dsa.lib.DefaultMethodSource;
import dsa.lib.Source;
import dsa.lib.TestNames;
import dsa.lib.lab10.DirectedGraphData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DirectedGraph")
public class DirectedGraphTests
{

  @DefaultDisplayNameGeneration
  @DisplayName("contains vertex")
  public interface ContainsVertexTests
  {

    @ParameterizedTest
    @MethodSource
    default <Vertex, Weight> void returnsTrueIfContained(
      DirectedGraph<Vertex, Weight> graph,
      Vertex vertex)
    {
      assertTrue(graph.contains(vertex));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsTrueIfContained arguments">
    @SuppressWarnings("unchecked")
    static Source<Arguments> returnsTrueIfContained()
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
                  TestNames.method("contains", vertex)),
                graph,
                vertex)))
        .quadratic()
        .limit();
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Vertex, Weight> void returnsFalseIfNotContained(
      DirectedGraph<Vertex, Weight> graph,
      Vertex vertex)
    {
      assertFalse(graph.contains(vertex));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsFalseIfNotContained arguments">
    @SuppressWarnings({"unchecked", "rawtypes"})
    static Source<Arguments> returnsFalseIfNotContained()
    {
      return DirectedGraphData.NON_EMPTY
        .flatReplace(graph ->
          Source.from(graph.items())
            .replace((vertex) ->
            {
              DirectedGraph g = DirectedGraphData.withoutVertex(graph, vertex);
              return Arguments.argumentSet(
                TestNames.format(
                  TestNames.constructor(
                    DirectedGraph.class,
                    Source.from(g.items()),
                    Source.from(g.edges())),
                  TestNames.method("contains", vertex)),
                g,
                vertex);
            }))
        .quadratic()
        .limit();
    }
    //</editor-fold>

  }


  @DefaultDisplayNameGeneration
  @DisplayName("contains edge")
  public interface ContainsEdgeTests
  {

    @ParameterizedTest
    @MethodSource
    default <Vertex, Weight> void returnsTrueIfContained(
      DirectedGraph<Vertex, Weight> graph,
      Edge<Vertex, Weight> edge)
    {
      assertTrue(graph.contains(edge));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsTrueIfContained arguments">
    @SuppressWarnings("unchecked")
    static Source<Arguments> returnsTrueIfContained()
    {
      return DirectedGraphData.NON_EMPTY
        .flatReplace(graph ->
          Source.from(graph.edges())
            .replace((edge) ->
              Arguments.argumentSet(
                TestNames.format(
                  TestNames.constructor(
                    DirectedGraph.class,
                    Source.from(graph.items()),
                    Source.from(graph.edges())),
                  TestNames.method("contains", edge)),
                graph,
                edge)))
        .quadratic()
        .limit();
    }
    //</editor-fold>


    @ParameterizedTest
    @MethodSource
    default <Vertex, Weight> void returnsFalseIfNotContained(
      DirectedGraph<Vertex, Weight> graph,
      Edge<Vertex, Weight> edge)
    {
      assertFalse(graph.contains(edge));
    }


    //<editor-fold defaultstate="collapsed" desc="returnsFalseIfNotContained arguments">
    @SuppressWarnings({"unchecked", "rawtypes"})
    static Source<Arguments> returnsFalseIfNotContained()
    {
      return DirectedGraphData.NON_EMPTY
        .flatReplace(graph ->
          Source.from(graph.edges())
            .replace((edge) ->
            {
              DirectedGraph g =
                DirectedGraphData.withoutEdge(graph, (Edge) edge);
              return Arguments.argumentSet(
                TestNames.format(
                  TestNames.constructor(
                    DirectedGraph.class,
                    Source.from(g.items()),
                    Source.from(g.edges())),
                  TestNames.method("contains", edge)),
                g,
                edge);
            }))
        .quadratic()
        .limit();
    }
    //</editor-fold>

  }


  @DefaultDisplayNameGeneration
  @DisplayName("degree")
  public interface DegreeTests
  {

    @ParameterizedTest
    @DefaultMethodSource
    default <Vertex, Weight> void returnsSameAsSolution(
      DirectedGraph<Vertex, Weight> graph,
      dsa.lab10.solutions.DirectedGraph<Vertex, Weight> solutionGraph,
      Vertex vertex)
    {
      assertEquals(solutionGraph.degree(vertex), graph.degree(vertex));
    }


    //<editor-fold defaultstate="collapsed" desc="arguments">
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
    //</editor-fold>

  }


  @DefaultDisplayNameGeneration
  @DisplayName("edge")
  public interface EdgeTests
  {

    @ParameterizedTest
    @MethodSource
    default <Vertex, Weight> void returnsNullIfNotContained(
      DirectedGraph<Vertex, Weight> graph,
      Vertex source,
      Vertex target)
    {
      assertNull(graph.edge(source, target));
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    static Source<Arguments> returnsNullIfNotContained()
    {
      return DirectedGraphData.NON_EMPTY
        .flatReplace(graph ->
          Source.from(graph.edges())
            .replace((edge) ->
            {
              Edge e = (Edge) edge;
              DirectedGraph g = DirectedGraphData.withoutEdge(graph, e);
              return Arguments.argumentSet(
                TestNames.format(
                  TestNames.constructor(
                    DirectedGraph.class,
                    Source.from(g.items()),
                    Source.from(g.edges())),
                  TestNames.method("edge", e.source(), e.target())),
                g,
                e.source(),
                e.target());
            }))
        .quadratic()
        .limit();
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Vertex, Weight> void returnsEdgeBetweenVerticesIfContained(
      DirectedGraph<Vertex, Weight> graph,
      Vertex source,
      Vertex target)
    {
      Edge<Vertex, Weight> edge = graph.edge(source, target);
      assertEquals(source, edge.source());
      assertEquals(target, edge.target());
    }


    @ParameterizedTest
    @DefaultMethodSource
    default <Vertex, Weight> void returnsContainedEdgeIfContained(
      DirectedGraph<Vertex, Weight> graph,
      Vertex source,
      Vertex target)
    {
      assertTrue(graph.contains(graph.edge(source, target)));
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    static Source<Arguments> arguments()
    {
      return DirectedGraphData.NON_EMPTY
        .flatReplace(graph ->
          Source.from(graph.edges())
            .replace((edge) ->
            {
              Edge e = (Edge) edge;
              return Arguments.argumentSet(
                TestNames.format(
                  TestNames.constructor(
                    DirectedGraph.class,
                    Source.from(graph.items()),
                    Source.from(graph.edges())),
                  TestNames.method("edge", e.source(), e.target())),
                graph,
                e.source(),
                e.target());
            }))
        .quadratic()
        .limit();
    }

  }


  @Nested
  public class ContainsVertex
    implements ContainsVertexTests
  {
  }


  @Nested
  public class ContainsEdge
    implements ContainsEdgeTests
  {
  }


  @Nested
  public class Degree
    implements DegreeTests
  {
  }


  @Nested
  public class Edge_
    implements EdgeTests
  {
  }

}
