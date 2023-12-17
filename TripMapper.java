import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TripMapper {
    public static void main(String[] args0) {
	String file  = "RoadNetwork.dot";
        List<IRoad> roads = new ArrayList<>();
        List<String> cities = new ArrayList<>();

        DWIdataLoader dataWrangler = new DWIdataLoader();
        dataWrangler.loadGraph(roads, cities, file);

        CityGraph graph = new CityGraph();
	//System.out.println(roads);
	
	for (String city : cities) {
		graph.insertVertex(city);
	}

	 for (IRoad road : roads) {
		graph.insertEdge(road.getOrigin(), road.getDestination(), road.getDistance());
	}

	CityValidator validator = new CityValidator();
	CityBackend backend;
        try {
            backend = new CityBackend(graph, validator, cities.toArray(new String[0]));

            Scanner userInput = new Scanner(System.in);

            TripFrontend frontend = new TripFrontend(userInput, backend, validator);
            frontend.runCommandLoop();
        } 
	catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
