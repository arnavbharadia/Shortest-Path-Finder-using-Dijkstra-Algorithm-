/**
 * This class implements only one method, of which
 * validates if a city is within the internal CityGraph
 */
public class CityValidatorBackendPlaceholder implements ICityValidator {
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
