import java.util.List;
import java.util.Scanner;

public interface ITripFrontend {
    /**                                                                                                        
     * The constructor that the implementation this interface will                                             
     * provide. It takes the Scanner that will read user input as                                              
     * a parameter as well as the backend and the IPokeValidator.                                              
     */
    // public ITripFrontend(Scanner userInputScanner, ICityBackend backend, ICityValidator validator);                  

    /**                                                                                                        
     * This method starts the command loop for the frontend, and will                                          
     * terminate when the user exists the app.                                                                 
     */
    public void runCommandLoop();

    // to help make it easier to test the functionality of this program,                                       
    // the following helper methods will help support runCommandLoop():                                        

    public void displayMainMenu(); // prints command options to System.out                                     


    public void displayCities(String[] cities); // displays a list of Cities                        
    public void createTrip(); // reads starting location and destination from System.in, displays distance and cities passed by                             
}
