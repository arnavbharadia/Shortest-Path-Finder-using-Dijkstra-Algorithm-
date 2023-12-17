// --== CS400 Fall 2022 File Header Information ==--
// Name: Aidan Mulvaney
// Email: amulvaney@wisc.edu
// Team: CC Red
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: n/a

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * This class comprises of tests for the Algorithm Engineer for P3 in CS400
 */
public class AlgorithmEngineerTests {
    private CityGraph<String, Integer> individualGraph;
    private DWIdataLoader dataLoader;
    private List<IRoad> roads;
    private List<String> cities;
    private CityGraph<String, Integer> integrationGraph;

    /**
     * Initializes a graph for each test
     * and the dataLoader for the DW code review
     */
    @BeforeEach
    public void createGraph() {
        // initialize graph for the individual tests:
        individualGraph = new CityGraph<>();

        // add vertices for individual tests:
        individualGraph.insertVertex("Minneapolis");
        individualGraph.insertVertex("Madison");
        individualGraph.insertVertex("Chicago");
        individualGraph.insertVertex("New York");
        individualGraph.insertVertex("Washington DC");
        individualGraph.insertVertex("Seattle");
        individualGraph.insertVertex("Los Angeles");

        // add edges for individual tests:
        individualGraph.insertEdge("Minneapolis", "Madison", 269);
        individualGraph.insertEdge("Chicago", "Madison", 173);
        individualGraph.insertEdge("New York", "Chicago", 790);
        individualGraph.insertEdge("New York", "Washington DC", 226);
        individualGraph.insertEdge("Washington DC", "Chicago", 701);
        individualGraph.insertEdge("Seattle", "Minneapolis", 1705);
        individualGraph.insertEdge("Seattle", "Los Angeles", 1137);
        individualGraph.insertEdge("Los Angeles", "Chicago", 2207);
        individualGraph.insertEdge("Los Angeles", "Washington DC", 2668);

        // initialize the dataLoader for the DataWrangler code review
        dataLoader = new DWIdataLoader();

        // load the data into Lists
        roads = new ArrayList<>();
        cities = new ArrayList<>();
        dataLoader.loadGraph(roads, cities, "RoadNetwork.dot");

        // create a graph and attempt to add information from DW
        integrationGraph = new CityGraph<>();
        for (String city : cities) {
            integrationGraph.insertVertex(city);
        }
        for (IRoad road : roads) {
            integrationGraph.insertEdge(road.getOrigin(), road.getDestination(), 
                                        road.getDistance());
        }
    }

    /**
     * This test verifies that inserting vertices functions properly,
     * meaning that data is stored inside of the vertex correctly, and
     * no edges are associated with it.
     */
    @Test
    public void individualTest1() {
        // We will do work on a new graph, not individualGraph
        CityGraph<String, Integer> graph = new CityGraph<>();
        assertEquals(graph.getVertexCount(), 0);

        // Insert a new vertex, verify
        assertTrue(graph.insertVertex("Minneapolis"));
        assertTrue(graph.containsVertex("Minneapolis"));
        assertEquals(graph.getVertexCount(), 1);
        assertEquals(graph.getEdgeCount(), 0);

        // Insert another vertex, verify no edge was created between them
        assertTrue(graph.insertVertex("Madison"));
        assertTrue(graph.containsVertex("Madison"));
        assertTrue(graph.containsVertex("Minneapolis"));
        assertEquals(graph.getVertexCount(), 2);
        assertEquals(graph.getEdgeCount(), 0);

    }

    /**
     * This test verifies that inserting edges functions properly,
     * meaning that it creates an undirected edge between two vertices.
     */
    @Test
    public void individualTest2() {
        // We will do work on a new graph, not individualGraph
        CityGraph<String, Integer> graph = new CityGraph<>();
        assertEquals(graph.getEdgeCount(), 0);

        // Insert two vertices, and put an undirected edge between them.
        graph.insertVertex("Minneapolis");
        graph.insertVertex("Madison");
        assertTrue(graph.insertEdge("Minneapolis", "Madison", 269));
        assertEquals(graph.getEdgeCount(), 1);
        assertTrue(graph.containsEdge("Minneapolis", "Madison"));
        assertTrue(graph.containsEdge("Madison", "Minneapolis"));
        assertEquals(graph.getWeight("Minneapolis", "Madison"), 269);

        // update the old edge to a new weight, verify it was changed correctly
        assertTrue(graph.insertEdge("Madison", "Minneapolis", 999));
        assertEquals(graph.getEdgeCount(), 1);
        assertTrue(graph.containsEdge("Minneapolis", "Madison"));
        assertTrue(graph.containsEdge("Madison", "Minneapolis"));
        assertEquals(graph.getWeight("Minneapolis", "Madison"), 999);

        // add one more vertex, and add an edge to this new vertex
        graph.insertVertex("Chicago");
        assertTrue(graph.insertEdge("Madison", "Chicago", 173));
        assertEquals(graph.getEdgeCount(), 2);
        // verify nothing else changed
        assertTrue(graph.containsEdge("Minneapolis", "Madison"));
        assertTrue(graph.containsEdge("Madison", "Minneapolis"));
        assertEquals(graph.getWeight("Minneapolis", "Madison"), 999);
        // verify new edge added correctly
        assertTrue(graph.containsEdge("Chicago", "Madison"));
        assertTrue(graph.containsEdge("Madison", "Chicago"));
        assertEquals(graph.getWeight("Chicago", "Madison"), 173);

    }

    /**
     * This test verifies that removing vertices and edges functions properly,
     * meaning that when a vertex is removed, an edge is removed, and when an edge
     * is removed, nothing else is removed.
     */
    @Test
    public void individualTest3() {
        // attempt to remove Seattle from individualGraph
        assertTrue(individualGraph.removeVertex("Seattle"));
        assertEquals(individualGraph.getVertexCount(), 6);
        assertEquals(individualGraph.getEdgeCount(), 7);
        assertFalse(individualGraph.containsEdge("Seattle", "Los Angeles"));

        // attempt to remove Seattle again
        assertFalse(individualGraph.removeVertex("Seattle"));
        assertEquals(individualGraph.getVertexCount(), 6);
        assertEquals(individualGraph.getEdgeCount(), 7);

        // attempt to remove the edge between LA and Chicago
        assertTrue(individualGraph.removeEdge("Los Angeles", "Chicago"));
        assertEquals(individualGraph.getVertexCount(), 6);
        assertEquals(individualGraph.getEdgeCount(), 6);
        assertFalse(individualGraph.containsEdge("Los Angeles", "Chicago"));

        // attempt to remove the edge between LA and Chicago again
        assertFalse(individualGraph.removeEdge("Los Angeles", "Chicago"));
        assertEquals(individualGraph.getVertexCount(), 6);
        assertEquals(individualGraph.getEdgeCount(), 6);
    }

    /**
     * This test verifies that the Djikstra's Shortest Path algorithm continues to
     * function properly even when the graph was converted into an undirected graph.
     */
    @Test
    public void individualTest4() {
        // path between Seattle and Minneapolis (direct path)
        // and the other way around
        assertEquals(individualGraph.shortestPath("Seattle", "Minneapolis").toString(),
        "[Seattle, Minneapolis]");
        assertEquals(individualGraph.getPathCost("Seattle", "Minneapolis"), 1705);

        assertEquals(individualGraph.shortestPath("Minneapolis", "Seattle").toString(),
        "[Minneapolis, Seattle]");
        assertEquals(individualGraph.getPathCost("Minneapolis", "Seattle"), 1705);

        // path between New York and Los Angeles (indirect path)
        // and the other way around
        assertEquals(individualGraph.shortestPath("New York", "Los Angeles").toString(),
        "[New York, Washington DC, Los Angeles]");
        assertEquals(individualGraph.getPathCost("New York", "Los Angeles"), 2894);

        assertEquals(individualGraph.shortestPath("Los Angeles", "New York").toString(),
        "[Los Angeles, Washington DC, New York]");
        assertEquals(individualGraph.getPathCost("Los Angeles", "New York"), 2894);

        // path between Portland and Chicago (DNE)
        try {
            individualGraph.shortestPath("Portland", "Chicago");
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "shortestPath() encountered an exception: " +
            "Either the start node or the end node could not be found.");
        }
    }

    /**
     * This test verifies that the validator functions properly,
     * meaning that it detects valid city names.
     */
    @Test
    public void individualTest5() {
        CityValidator validator = new CityValidator();

        // test on valid names, case insensitive
        assertTrue(validator.validate("SeaTtLe"));
        assertTrue(validator.validate("minneapolis"));
        assertTrue(validator.validate("mADISON"));
        assertTrue(validator.validate("Chicago"));
        assertTrue(validator.validate("new YORK"));
        assertTrue(validator.validate("WashiNGton DC"));
        assertTrue(validator.validate("LOS ANGELES"));

        // test on invalid names, case insensitive
        assertFalse(validator.validate("PorTLAND"));
        assertFalse(validator.validate("orlando"));
        assertFalse(validator.validate("LonDoN"));
        assertFalse(validator.validate("OSAKA"));
        assertFalse(validator.validate("Munich"));
    }

    /**
     * This test verifies that adding information given from the DataWrangler
     * to the graph continues to function as expected
     */
    @Test
    public void integrationTest1() {
        // validate that the information was successfully added
        // check edge info
        assertEquals(integrationGraph.getEdgeCount(), 9);
        assertTrue(integrationGraph.containsEdge("Seattle", "Los Angeles"));
        assertTrue(integrationGraph.containsEdge("Minneapolis", "Madison"));
        assertFalse(integrationGraph.containsEdge("Los Angeles", "New York"));

        // check vertex info
        assertEquals(integrationGraph.getVertexCount(), 7);
        assertTrue(integrationGraph.containsVertex("Madison"));
        assertTrue(integrationGraph.containsVertex("Washington DC"));
        assertTrue(integrationGraph.containsVertex("Chicago"));
        assertFalse(integrationGraph.containsVertex("Oconomowoc"));
        assertFalse(integrationGraph.containsVertex("Duluth"));
    }

    /**
     * This test verifies that the shortest path continues to work as expected
     * after integration
     */
    @Test
    public void integrationTest2() {
        // validate that the shortest paths are still correct
        // check on a direct path
        assertEquals(integrationGraph.shortestPath("Seattle", "Minneapolis").toString(),
        "[Seattle, Minneapolis]");
        assertEquals(integrationGraph.getPathCost("Seattle", "Minneapolis"), 1705);

        // check on a nondirect path
        assertEquals(integrationGraph.shortestPath("New York", "Los Angeles").toString(),
        "[New York, Washington DC, Los Angeles]");
        assertEquals(integrationGraph.getPathCost("New York", "Los Angeles"), 2894);

        // check on a path that does not exist
        try {
            individualGraph.shortestPath("Portland", "Chicago");
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "shortestPath() encountered an exception: " +
            "Either the start node or the end node could not be found.");
        }
    }

    /**
     * This test verifies that the roads returned from the DataWrangler are correct
     */
    @Test
    public void codeReviewOfDataWranglerTest1() {
        // check the number of roads is correct
        assertEquals(roads.size(), 9);

        // check that information gathered from the file
        // is correctly stored in a road, like origin, destination
        // and distance on two arbitrary roads
        IRoad road1 = roads.get(0);
        IRoad road2 = roads.get(6);
        assertTrue(road1.getOrigin().equals("Seattle"));
        assertTrue(road1.getDestination().equals("Los Angeles"));
        assertEquals(road1.getDistance(), 1137);

        assertTrue(road2.getOrigin().equals("Chicago"));
        assertTrue(road2.getDestination().equals("New York"));
        assertEquals(road2.getDistance(), 770);
    }

    /**
     * This test verifies that the cities returned from the DataWrangler are correct
     */
    @Test
    public void codeReviewOfDataWranglerTest2() {
        // check the number of cities is correct
        assertEquals(cities.size(), 7);

        // check that information gathered from the file
        // is correctly stored in a city on 4 arbitrary cities
        assertTrue(cities.get(0).equals("Seattle"));
        assertTrue(cities.get(1).equals("Los Angeles"));
        assertTrue(cities.get(3).equals("Madison"));
        assertTrue(cities.get(6).equals("New York"));

        // check that other data from the file is not stored in the
        // list of cities
        assertFalse(cities.contains("--"));
        assertFalse(cities.contains("distance"));
        assertFalse(cities.contains(";"));
    }
}
