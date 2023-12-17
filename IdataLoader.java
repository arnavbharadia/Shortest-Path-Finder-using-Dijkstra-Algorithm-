import java.util.List;

/**
 * Loads the graph from a file into a list to be used by the Algorithm Engineer
 */
public interface IdataLoader {
    public void loadGraph(List<IRoad> roadList, List<String> cityNames, String filePath);

}

