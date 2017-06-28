import com.siematypie.engine.DijkstraAlgorithm;
import com.siematypie.engine.PathNotFoundException;
import com.siematypie.models.Graph;
import com.siematypie.models.Vertex;
import com.siematypie.models.VertexNotFoundException;
import com.siematypie.utils.GraphFromCsvCreator;
import com.siematypie.utils.GraphVisualizer;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, VertexNotFoundException {
        File theDir = new File("src/output");
        theDir.mkdir();
        Graph g = new GraphFromCsvCreator().createFromResources("graphData/defaultGraph.csv");
        GraphVisualizer.saveImage(g, "src/output/graph.png");
        System.out.println("Visualisation of your graph has been saved to src/output/graph.png\n");
        DijkstraAlgorithm engine = new DijkstraAlgorithm(g);
        try {
            System.out.println(engine.calculateShortestPath("Athens", "Paris"));
            GraphVisualizer.saveImage(g, "src/output/shortestPath.png");
            System.out.println("\nShortest path image has been saved to src/output/graph.png");

        } catch (PathNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
