import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**                                                                                                                                                                                     
* Frontend Class for the TripMapper Application                                                                                                                                         
*                                                                                                                                                                                       
*/
public class TripFrontend implements ITripFrontend{
    private Scanner userInputScanner;
    private ICityBackend backend;
    private ICityValidator validator;

    /**                                                                                                                                                                                 
     * The constructor that the implementation this interface will                                                                                                                      
     * provide. It takes the Scanner that will read user input as                                                                                                                       
     * a parameter as well as the backend and the ICityValidator.                                                                                                                       
     */
     TripFrontend(Scanner userInputScanner, ICityBackend backend, ICityValidator validator) {
        this.userInputScanner = userInputScanner;
        this.backend = backend;
        this.validator = validator;
    }


    /**                                                                                                                                                                                 
     * This method starts the command loop for the frontend, and will                                                                                                                   
     * terminate when the user exists the app.                                                                                                                                          
     */
    @Override
    public void runCommandLoop() {
        System.out.print("Welcome to the Djikstra TripMapper Application!\n");
        System.out.print("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n");
        while (true) {
            displayMainMenu();
            int option = userInputScanner.nextInt();
            userInputScanner.nextLine(); // move past the new line created after int                                                                                                    
            if (option == 1) {
                String[] cities = backend.listallcities();
                System.out.print("Listing All Cities... \n");
                displayCities(cities);
            }
            else if (option == 2) {
                createTrip();
            }     
            else if (option == 3) {
                System.out.print("Goodbye!\n");
                return;
            }
        }

    }

    /**                                                                                                                                                                                  
     * This method starts the main menu for the frontend                                                                                                                                
     *                                                                                                                                                                                  
     */
    @Override
    public void displayMainMenu() {
        System.out.print("You are in the Main Menu:\n");
        System.out.print("\t1) List all Cities\n\t2) Create a Trip\n");
        System.out.print("\t3) Exit Application\n");
    }

    /**                                                                                                                                                                                 
     * This method will print all Cities in the list for the frontend,                                                                                                                  
     * @param String[]  List of all Cities to be printed                                                                                                                                 
     */
    @Override
    public void displayCities(String[] cities) {
        if (cities.length == 0) {
            System.out.println("List was empty! No cities in graph.");
        }
        int count = 1;
        for (String city: cities ){
            System.out.println(count +  ". " + printCity(city));
            count++;
        }

    }
                                                                                                                                           
    /**                                                                                                                                                                                 
     * This method will print the shortest path length and all the Cities crossed for the trip                                                                                          
     */
    @Override
    public void createTrip() {
        try {
            System.out.println("Enter a City to start your trip from:");
            String startCity = userInputScanner.nextLine();
            System.out.println("Enter a destination city you would like to end at:");
            String destinationCity = userInputScanner.nextLine();
            if (validator.validate(startCity) == true && validator.validate(destinationCity) == true) {
                System.out.println("Your trip is " + String.valueOf(backend.findShortestpath(startCity, destinationCity)) + " miles long.");
                System.out.println("The cities you will cross are:");
                String[] citiesReturned = backend.citiesinbetween(startCity, destinationCity);
                displayCities(citiesReturned);
            } else {
                System.out.println("The city(s) entered was not included in this application.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("The city(s) entered was not included in this application.");
        }


    }

    /**                                                                                                                                                                                 
     * Private Method to print Cities as a String                                                                                                                                       
     * @param ICity City to be printed                                                                                                                                                  
     */
    private String printCity(String city){
        return "Name: " + city;
    }
    
    }