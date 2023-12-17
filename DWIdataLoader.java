import java.io.File;
import java.util.List;
import java.util.Scanner;

public class DWIdataLoader implements IdataLoader {

    /**
     * Loads the graph from a file into 2 lists to be used by the Algorithm Engineer
     *
     * @param roadList  List of IRoads to be populated
     * @param cityNames List of Strings of city names
     * @param filePath  String of the file path
     */
    @Override public void loadGraph(List<IRoad> roadList, List<String> cityNames, String filePath) {

        try {
            File file = new File(filePath); // create a File object
            Scanner sc = new Scanner(file); // create a Scanner object
            String doc = ""; // create a String to hold the file contents
            while (sc.hasNextLine()) { // while there is another line
                String line = sc.nextLine(); // read the next line
                if (line.contains("{") || line.contains("}") || line.strip().isEmpty())
                    continue; // if the line is empty or contains a curly brace, skip it
                doc += line; // otherwise, add the line to the String

            }
            String[] cities = doc.split("--");
            // split the String into an array of Strings, each containing a city
            for (String city : cities) { // for each city
                String[] cityNamesArray = city.split("\\[");
                // split the city into an array of Strings
                String cityName = cityNamesArray[0].strip();
                // the first element of the array is the city name
                cityName = cityName.replace("\"", "");
                // remove the quotes from the city name
                if (!cityNames.contains(cityName)) {
                    // if the city name is not already in the list add it to the list
                    cityNames.add(cityName);
                }
            }

            String[] roads = doc.split(";");
            // split the String into an array of Strings, each
            for (String road : roads) { // for each road
                String[] roadArray = road.split("\\[");
                // split the road into an array of Strings
                String[] roadArray2 = roadArray[0].split("--");
                // split the first element of the array into an array of Strings
                String origin = roadArray2[0].strip().replace("\"", "");
                // the first element of the array is the origin
                String destination = roadArray2[1].strip().replace("\"", "");
                // the second element of the array is the destination
                String distance = roadArray[1].split("=")[1].split("]")[0].strip();
                // the second element of the array is the distance
                roadList.add(new DWIRoad(origin, destination, distance));
                // add a new road to the list
            }

        } catch (Exception e) { // if an exception is thrown, print the stack trace
            e.printStackTrace();
        }

    }
}
