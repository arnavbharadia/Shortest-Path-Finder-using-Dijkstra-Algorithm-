/**
 * This interface implements only one method, of which
 * validates if a city is within the internal CityGraph
 */
public interface ICityValidator {
    /**
     * Finds if the city passed is within the graph
     * 
     * Searches through the internal CityGraph and finds
     * if the city passed is within
     * @param city the city to search for
     * @return true if the city is found, false otherwise
     */
    public boolean validate(String city);
}
