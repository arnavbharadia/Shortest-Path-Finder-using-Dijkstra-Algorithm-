import java.util.NoSuchElementException;
import java.util.List;

public class CityBackend implements ICityBackend{

    protected ICityGraph<String, Integer> Graph;
    protected ICityValidator Validator;
    protected String[] Cities;

    public CityBackend(ICityGraph<String,Integer> Graph, ICityValidator Validator, String[] Cities){
        this.Graph = Graph;
        this.Validator = Validator;
        if(Cities.length == 0){
            throw new IllegalArgumentException("No Cities in array!");
        }
        this.Cities = Cities;
    }

    /**
     * Will find the Shortest path from two Cities
     *
     * @param City1 String with name of City 1 (Origin)
     * @param City2 String with name of City 2 (Destination)
     * @return int with the shortest distance and -1 if no path exists
     */
    @Override
    public int findShortestpath(String City1, String City2) {
        if(Validator.validate(City1)) {
            if (Validator.validate(City2)) {
                try {
                    return (int) Graph.getPathCost(City1, City2);
                }
                catch (NoSuchElementException e){
                    return -1;
                }
            }
            else{
                throw new NoSuchElementException("Destination City Missing in database");
            }
        }
        else{
            throw new NoSuchElementException("Origin City missing in database");
        }
    }

    /**
     * Returns all the Cities between the two cities
     * @param City1
     * @param City2
     * @return String[] of all Cities
     */
    public String[] citiesinbetween(String City1, String City2){
        List<String> cities = Graph.shortestPath(City1, City2);
        String[] allcities = new String[cities.size()];
        for (String city: cities) {
            cities.toArray(allcities);
        }
        return allcities;
    }
    /**
     * Will List all Cities on the Graph
     *
     * @return String[]
     */
    @Override
    public String[] listallcities() {
        return Cities;
    }
}
