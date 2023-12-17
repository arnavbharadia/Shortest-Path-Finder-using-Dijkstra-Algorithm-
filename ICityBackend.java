/**                                                                                                                                                                                    
 * Instances of this interface implement the search                                                                                                                                     
 * functionality of the TripMapper app using Dijkstra's Algorithm                                                                                                                       
 */
public interface ICityBackend {

    /**                                                                                                                                                                                 
     * Will find the Shortest path from two Cities                                                                                                                                      
     * @return int with the shortest distance                                                                                                                                           
     */
    public int findShortestpath(String City1, String City2);

    /**
     * Returns all the Cities between the two cities
     * @param City1
     * @param City2
     * @return String[] of all Cities
     */	
    public String[] citiesinbetween(String City1, String City2);

   /**                                                                                                                                                                                  
    * Will List all Cities on the Graph                                                                                                                                                 
    * @return String[]                                                                                                                                                                   
    */
    public String[] listallcities();
}
