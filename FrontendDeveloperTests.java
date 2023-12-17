import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FrontendDeveloperTests {

    protected TripFrontend trip;
    protected TextUITester uiTester;

    /**                                                                                                                                                                                 
     * Test 1 - Test if main menu method is functioning as expected                                                                                                                     
     * Tester will only pass if the behavior is displayed as expected                                                                                                                  
     */
    @Test
    public void test1(){
        uiTester = new TextUITester("\n");
        trip  = new TripFrontend(new Scanner(System.in), new FrontendDeveloperBackendPlaceholder(), new FrontendDeveloperValidatorPlaceholder());
        trip.displayMainMenu();

        assertEquals("You are in the Main Menu:\n"+
                "\t1) List all Cities\n\t2) Create a Trip\n"+
                "\t3) Exit Application\n", uiTester.checkOutput());
    }

    /**                                                                                                                                                                                 
     * Test 2 - Tests if application will exit+                                                                                                                                         
     * Tester will only pass if the behavior is displayed as expected                                                                                                                  
     */
    @Test
    public void test2(){
        uiTester = new TextUITester("3\n");
        trip  = new TripFrontend(new Scanner(System.in), new FrontendDeveloperBackendPlaceholder(), new FrontendDeveloperValidatorPlaceholder());

        trip.runCommandLoop();
        assertEquals("Welcome to the Djikstra TripMapper Application!\n" +
                "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" +
                "You are in the Main Menu:\n"+
                "\t1) List all Cities\n\t2) Create a Trip\n"+
                "\t3) Exit Application\nGoodbye!\n", uiTester.checkOutput());
    }

    /**                                                                                                                                                                                 
     * Test 3 - Tests displayCities method                                                                                                                                              
     * Tester will only pass if the behavior is displayed as expected                                                                                                                  
     */
    @Test
    public void test3(){
        uiTester = new TextUITester("\n");
        trip  = new TripFrontend(new Scanner(System.in), new FrontendDeveloperBackendPlaceholder(), new FrontendDeveloperValidatorPlaceholder());

        String[] cities = new String[2];
        cities[0] = "Madison";
        cities[1] = "Milwaukee";

        trip.displayCities(cities);

        //Displays default City set by Placeholder class                                                                                                                                
        assertEquals("1. Name: Madison\n2. Name: Milwaukee\n", uiTester.checkOutput());
    }

    /**                                                                                                                                                                                 
     * Test 4 - Tests shortestPath method                                                                                                                                               
     * Tester will only pass if the behaviour is displayed as expected                                                                                                                  
     */
    @Test
    public void test4(){
        uiTester = new TextUITester("Madison\nMilwaukee\n");
        trip  = new TripFrontend(new Scanner(System.in), new FrontendDeveloperBackendPlaceholder(), new FrontendDeveloperValidatorPlaceholder());

        trip.createTrip();

        // Placeholder validator class has been set to true by default                                                                                                                           
        assertEquals("Enter a City to start your trip from:\n" +
                "Enter a destination city you would like to end at:\nYour trip is 80 miles long.\n" +
                "The cities you will cross are:\n" +
                "1. Name: Brookfield\n" +
                "2. Name: Pewaukee\n", uiTester.checkOutput());
    }

    /**
     * Test 5: Tests if application will say list is empty when an empty list is fed into the displayCities method.
     */
    @Test
    public void test5() {
        uiTester = new TextUITester("");
        trip  = new TripFrontend(new Scanner(System.in), new FrontendDeveloperBackendPlaceholder(), new FrontendDeveloperValidatorPlaceholder());

        String[] cities = new String[0];
        trip.displayCities(cities);

        assertEquals("List was empty! No cities in graph.\n", uiTester.checkOutput());
    }

    /**
     * Integration Test 1: Tests whether when the validator is given an invalid city the app will yield the correct result of no cities found
     */
    @Test
    public void testIntegration1() {
        String[] cities = {"Fake City"};
        uiTester = new TextUITester("Not a city\nNot a city\n");
        trip  = new TripFrontend(new Scanner(System.in), new CityBackend(new CityGraph<String, Integer>(), new CityValidator(), cities), new CityValidator());

        trip.createTrip();
        assertEquals("Enter a City to start your trip from:\n" +
                "Enter a destination city you would like to end at:\nThe city(s) entered was not included in this application.\n",uiTester.checkOutput());

    }

    /**
     * Integration Test 2: Tests whether the application shows the right number and cities crossed when tested from Madison to Chicago
     */
    @Test
    public void testIntegration2() {
        uiTester = new TextUITester("Madison\nChicago\n");
        String[] cities = {"Madison", "Chicago"};
        CityGraph<String, Integer> graph = new CityGraph<String, Integer>();
        graph.insertVertex("Madison");
        graph.insertVertex("Chicago");
        graph.insertEdge("Madison", "Chicago", 100);
        trip  = new TripFrontend(new Scanner(System.in), new CityBackend(graph, new CityValidator(), cities), new CityValidator());
        trip.createTrip();
        assertEquals("Enter a City to start your trip from:\n" +
                "Enter a destination city you would like to end at:\nYour trip is 100 miles long.\n" +
                "The cities you will cross are:\n" +
                "1. Name: Madison\n" +
                "2. Name: Chicago\n", uiTester.checkOutput());
    }

    /**
     * Code Review Backend Developer Test 1: Tests whether the backend developer listallcities function will display the cities that are given to it
     */
    @Test
    public void CodeReviewOfBackendDeveloper1() {
        String[] cities = {"Madison", "Chicago"};
        CityBackend backend = new CityBackend(new CityGraph<String, Integer>(), new CityValidator(), cities);
        assertEquals(backend.listallcities(),cities);
    }

    /**
     * Code Review Backend Developer Test 2: Tests whether the backend developer listallcities function will throw an exception when it is given cities that don't exist
     */
     @Test
    public void CodeReviewOfBackendDeveloper2() {
        String[] cities = {"Madison", "Chicago"};
        CityBackend backend = new CityBackend(new CityGraph<String, Integer>(), new CityValidator(), cities);
        assertThrows(NoSuchElementException.class, () -> {
            backend.findShortestpath("Not a city", "Not a city");
        });
    }
}