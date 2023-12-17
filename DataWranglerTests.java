import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class DataWranglerTests {

    /**
     * Tests that the number of roads loaded is correct
     */
    @Test public void testRoadSize() {
        DWIdataLoader loaderTest = new DWIdataLoader();// create a new loader
        try { // try to load the graph
            List<IRoad> roads = new ArrayList<IRoad>();
            List<String> cities = new ArrayList<String>();
            loaderTest.loadGraph(roads, cities, "RoadNetwork.dot");
            assertEquals(roads.size(), 9); // assert that the number of roads is correct
        } catch (Exception e) { // if an exception is thrown, fail the test
            assertEquals(1, 2);
            System.out.println(e);
        }
    }

    /**
     * Tests that the number of cities loaded is correct
     */
    @Test public void testCitiesSize() {
        DWIdataLoader loaderTest = new DWIdataLoader();// create a new loader
        try {
            List<IRoad> roads = new ArrayList<IRoad>();
            List<String> cities = new ArrayList<String>();
            loaderTest.loadGraph(roads, cities, "RoadNetwork.dot");
            assertEquals(cities.size(), 7); // assert that the number of cities is correct
        } catch (Exception e) {// if an exception is thrown, fail the test
            assertEquals(1, 2);
            System.out.println(e);
        }
    }

    /**
     * Tests that the origin of the first road is correct
     */
    @Test public void testgetOrigin() {
        DWIdataLoader loaderTest = new DWIdataLoader();// create a new loader
        try {
            List<IRoad> roads = new ArrayList<IRoad>();
            List<String> cities = new ArrayList<String>();
            loaderTest.loadGraph(roads, cities, "RoadNetwork.dot");
            IRoad firstRoad = roads.get(0);
            assertEquals(firstRoad.getOrigin(), "Seattle");
            // assert that the origin of the first road is correct
        } catch (Exception e) {// if an exception is thrown, fail the test
            assertEquals(1, 2);
            System.out.println(e);
        }
    }

    /**
     * Tests that the destination of the last road is correct
     */
    @Test public void testgetDestination() {
        DWIdataLoader loaderTest = new DWIdataLoader();// create a new loader
        try {
            List<IRoad> roads = new ArrayList<IRoad>();
            List<String> cities = new ArrayList<String>();
            loaderTest.loadGraph(roads, cities, "RoadNetwork.dot");
            IRoad lastRoad = roads.get(roads.size() - 1);
            assertEquals(lastRoad.getDestination(), "Washington DC");
            // assert that the destination of the last road is correct
        } catch (Exception e) {// if an exception is thrown, fail the test
            assertEquals(1, 2);
            System.out.println(e);
        }
    }

    /**
     * Tests that the correct exception is thrown when the file is not found
     */
    @Test public void testgetDistance() {
        DWIdataLoader loaderTest = new DWIdataLoader(); // create a new loader
        try {
            List<IRoad> roads = new ArrayList<IRoad>();
            List<String> cities = new ArrayList<String>();
            loaderTest.loadGraph(roads, cities, "RoadNetwork.dot");
            IRoad firstRoad = roads.get(0);
            assertEquals(firstRoad.getDistance(), 1137);
            // assert that the distance of the first road is correct
        } catch (Exception e) {// if an exception is thrown, fail the test
            assertEquals(1, 2);
            System.out.println(e);
        }
    }

    @Test public void CodeReviewOfFrontendDeveloper1() {
        DWIdataLoader loaderTest = new DWIdataLoader(); // create a new loader
        List<IRoad> roads = new ArrayList<IRoad>(); // create a new list of roads
        List<String> cities = new ArrayList<String>(); // create a new list of cities
        loaderTest.loadGraph(roads, cities, "RoadNetwork.dot"); // load the graph


        TextUITester uiTester = new TextUITester("1\n3\n");
        // create a new text ui tester

        CityGraph graph = new CityGraph();

        String[] citiesArray = new String[cities.size()];
        for (String city : cities) { // for each city in the list of cities add it to the array
            graph.insertVertex(city); // insert the city into the graph
            citiesArray[cities.indexOf(city)] = city; // add the city to the array
        }

        for (IRoad road : roads) {
            graph.insertEdge(road.getOrigin(), road.getDestination(), road.getDistance());
            // insert the road into the graph
            graph.insertEdge(road.getDestination(), road.getOrigin(), road.getDistance());
            // insert the road into the graph
        }

        CityValidator validator = new CityValidator(); // create a new city validator

        TripFrontend trip =
            new TripFrontend(new Scanner(System.in), new CityBackend(graph, validator, citiesArray),
                validator); // create a new trip frontend

        trip.runCommandLoop(); // run the command loop

        //Displays default City set by Placeholder class
        assertEquals("Welcome to the Djikstra TripMapper Application!\n"
            + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" + "You are in the Main Menu:\n"
            + "\t1) List all Cities\n" + "\t2) Create a Trip\n" + "\t3) Exit Application\n"
            + "Listing All Cities... \n" + "1. Name: Seattle\n" + "2. Name: Los Angeles\n"
            + "3. Name: Minneapolis\n" + "4. Name: Madison\n" + "5. Name: Chicago\n"
            + "6. Name: Washington DC\n" + "7. Name: New York\n" + "You are in the Main Menu:\n"
            + "\t1) List all Cities\n" + "\t2) Create a Trip\n" + "\t3) Exit Application\n"
            + "Goodbye!\n", uiTester.checkOutput()); // assert that the output is correct
    }

    @Test public void CodeReviewOfFrontendDeveloper2() {

        DWIdataLoader loaderTest = new DWIdataLoader(); // create a new loader
        List<IRoad> roads = new ArrayList<IRoad>(); // create a new list of roads
        List<String> cities = new ArrayList<String>(); // create a new list of cities
        loaderTest.loadGraph(roads, cities, "RoadNetwork.dot");// load the graph


        TextUITester uiTester = new TextUITester("2\nNew York\nWashington DC\n3\n");
        // create a new text ui tester

        CityGraph graph = new CityGraph();

        String[] citiesArray = new String[cities.size()];
        for (String city : cities) { // for each city in the list of cities add it to the array
            graph.insertVertex(city);
            citiesArray[cities.indexOf(city)] = city;
        }

        for (IRoad road : roads) { // for each road in the list of roads
            graph.insertEdge(road.getOrigin(), road.getDestination(), road.getDistance());
            graph.insertEdge(road.getDestination(), road.getOrigin(), road.getDistance());
        }

        CityValidator validator = new CityValidator(); // create a new city validator

        TripFrontend trip =
            new TripFrontend(new Scanner(System.in), new CityBackend(graph, validator, citiesArray),
                validator); // create a new trip frontend

        trip.runCommandLoop(); // run the command loop

        //Displays default City set by Placeholder class
        assertEquals("Welcome to the Djikstra TripMapper Application!\n"
            + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" + "You are in the Main Menu:\n"
            + "\t1) List all Cities\n" + "\t2) Create a Trip\n" + "\t3) Exit Application\n"
            + "Enter a City to start your trip from:\n"
            + "Enter a destination city you would like to end at:\n"
            + "Your trip is 226 miles long.\n" + "The cities you will cross are:\n"
            + "1. Name: New York\n" + "2. Name: Washington DC\n" + "You are in the Main Menu:\n"
            + "\t1) List all Cities\n" + "\t2) Create a Trip\n" + "\t3) Exit Application\n"
            + "Goodbye!\n", uiTester.checkOutput());
            // assert that the output is correct
    }

    @Test public void IntegrationTest1() {
        DWIdataLoader loaderTest = new DWIdataLoader(); // create a new loader
        List<IRoad> roads = new ArrayList<IRoad>(); // create a new list of roads
        List<String> cities = new ArrayList<String>(); // create a new list of cities
        loaderTest.loadGraph(roads, cities, "RoadNetwork.dot"); // load the graph

        CityGraph graph = new CityGraph();// create a new city graph

        String[] citiesArray = new String[cities.size()];
        for (String city : cities) { // for each city in the list of cities add it to the array
            graph.insertVertex(city); // insert the city into the graph
            citiesArray[cities.indexOf(city)] = city; // add the city to the array
        }


        for (IRoad road : roads) { // for each road in the list of roads
            graph.insertEdge(road.getOrigin(), road.getDestination(), road.getDistance());
            graph.insertEdge(road.getDestination(), road.getOrigin(), road.getDistance());
        }

        CityValidator validator = new CityValidator();// create a new city validator

        CityBackend backend = new CityBackend(graph, validator, citiesArray);
        // create a new city backend

        assertEquals(citiesArray.length, backend.listallcities().length);
        // assert that the length of the array is correct

    }

    @Test public void IntegrationTest2() {

        DWIdataLoader loaderTest = new DWIdataLoader(); // create a new loader
        List<IRoad> roads = new ArrayList<IRoad>();
        List<String> cities = new ArrayList<String>();
        loaderTest.loadGraph(roads, cities, "RoadNetwork.dot");

        CityGraph graph = new CityGraph();// create a new city graph

        String[] citiesArray = new String[cities.size()];
        for (String city : cities) { // for each city in the list of cities add it to the array
            graph.insertVertex(city);
            citiesArray[cities.indexOf(city)] = city;
        }


        for (IRoad road : roads) { // for each road in the list of roads
            graph.insertEdge(road.getOrigin(), road.getDestination(), road.getDistance());
            graph.insertEdge(road.getDestination(), road.getOrigin(), road.getDistance());
        }

        CityValidator validator = new CityValidator();// create a new city validator

        CityBackend backend = new CityBackend(graph, validator, citiesArray);
        // create a new city backend

        String[] citiesFromBackend = backend.listallcities();
        // get the list of cities from the backend

        for (int i = 0; i < citiesFromBackend.length; i++) { // for each city in the array
            assertEquals(citiesArray[i], citiesFromBackend[i]);
        }
    }
}
