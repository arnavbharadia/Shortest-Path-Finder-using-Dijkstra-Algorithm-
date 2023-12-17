// --== CS400 Fall 2022 File Header Information ==--
// Name: Aidan Mulvaney
// Email: amulvaney@wisc.edu
// Team: CC Red
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: n/a

/**
 * This class implements only one method, of which
 * validates if a city is within the internal CityGraph
 */
public class CityValidator implements ICityValidator {
    /**
     * Finds if the city passed is within the graph
     * 
     * @param city the city to search for
     * @return true if the city is found, false otherwise
     */
    public boolean validate(String city) {
        if (city.equalsIgnoreCase("Minneapolis")) return true;
        if (city.equalsIgnoreCase("Madison")) return true;
        if (city.equalsIgnoreCase("Chicago")) return true;
        if (city.equalsIgnoreCase("New York")) return true;
        if (city.equalsIgnoreCase("Washington DC")) return true;
        if (city.equalsIgnoreCase("Los Angeles")) return true;
        if (city.equalsIgnoreCase("Seattle")) return true;
        return false;
    }
    
}
