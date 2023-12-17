public class FrontendDeveloperBackendPlaceholder implements ICityBackend{

    @Override
    public int findShortestpath(String City1, String City2) {
        return 80;
    }

    @Override
    public String[] listallcities() {
        String[] toReturn = {"Chicago", "New York"};
        return toReturn;
    }

    public String[] citiesinbetween(String city1, String city2) {
        String[] toReturn = {"Brookfield", "Pewaukee"}; 
        return toReturn;
    }
}