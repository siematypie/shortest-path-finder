package com.siematypie.engine;

import com.siematypie.models.Graph;
import com.siematypie.models.ShortestPath;
import com.siematypie.models.Vertex;
import com.siematypie.models.VertexNotFoundException;
import com.siematypie.utils.GraphFromCsvCreator;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class DijkstraAlgorithmTest {
    DijkstraAlgorithm engine;

    @Test
    @DisplayName("when there's no path")
    void calculateShortestPathThrowsPathNotFountExceptionWhenPathDoesntExist() throws IOException, VertexNotFoundException {
        Graph g = new GraphFromCsvCreator().createFromResources("graphData/defaultGraph.csv");
        Vertex start = g.getVertex("Warsaw");
        Vertex destination = new Vertex("I don't have any edges");
        g.addVertexIfAbsent(destination);
        assertThrows(PathNotFoundException.class, () -> new DijkstraAlgorithm(g).calculateShortestPath(start, destination));
    }

    @Nested
    @DisplayName("with default codecool city graph from defaultGraph.csv in resources")
    class withDefaultGraph {

        @BeforeEach
        void init() throws IOException {
            Graph g = new GraphFromCsvCreator().createFromResources("graphData/defaultGraph.csv");
            engine = new DijkstraAlgorithm(g);
        }

        @Test
        void testIfCalculateShortestPathFromWarsawToAthensReturnsCorrectShortestPath() throws VertexNotFoundException, PathNotFoundException {
            String expectedString = "Warsaw --> London --> Athens";
            Integer expectedWeight = 950;
            ShortestPath outcome = engine.calculateShortestPath("Warsaw", "Athens");
            assertAll("Path and Weight",
                    () -> assertEquals(expectedString, outcome.pathListToString(" --> ")),
                    () -> assertEquals(expectedWeight, outcome.getWeight())
            );
        }

        @Test
        void testIfCalculateShortestPathFromAthensToParisReturnsCorrectShortestPath() throws VertexNotFoundException, PathNotFoundException {
            String expectedString = "Athens --> London --> Lisboa --> Paris";
            Integer expectedWeight = 1050;
            ShortestPath outcome = engine.calculateShortestPath("Athens", "Paris");
            assertAll("Path and Weight",
                    () -> assertEquals(expectedString, outcome.pathListToString(" --> ")),
                    () -> assertEquals(expectedWeight, outcome.getWeight())
            );
        }
    }

    @Nested
    @DisplayName("with advanced graph from advancedGraph.csv in resources")
    class withAdvancedGraph {

        @BeforeEach
        void init() throws IOException {
                Graph g = new GraphFromCsvCreator().createFromResources("graphData/advancedGraph.csv");
                engine = new DijkstraAlgorithm(g);
        }

        @Test
        void testIfCalculateShortestPathFrom_A_To_G_ReturnsCorrectShortestPath() throws VertexNotFoundException, PathNotFoundException {
            String expectedString = "A --> B --> D --> E --> G";
            Integer expectedWeight = 57;
            ShortestPath outcome = engine.calculateShortestPath("A", "G");
            assertAll("Path and Weight",
                    () -> assertEquals(expectedString, outcome.pathListToString(" --> ")),
                    () -> assertEquals(expectedWeight, outcome.getWeight())
            );
        }

        @Test
        void testIfCalculateShortestPathFrom_B_To_F_ReturnsCorrectShortestPath() throws VertexNotFoundException, PathNotFoundException {
            String expectedString = "B --> D --> C --> F";
            Integer expectedWeight = 28;
            ShortestPath outcome = engine.calculateShortestPath("B", "F");
            assertAll("Path and Weight",
                    () -> assertEquals(expectedString, outcome.pathListToString(" --> ")),
                    () -> assertEquals(expectedWeight, outcome.getWeight())
            );
        }
    }




}