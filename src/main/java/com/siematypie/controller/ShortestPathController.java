package com.siematypie.controller;

import com.siematypie.engine.DijkstraAlgorithm;
import com.siematypie.engine.PathNotFoundException;
import com.siematypie.models.Graph;
import com.siematypie.models.ShortestPath;
import com.siematypie.models.Vertex;
import com.siematypie.utils.GraphVisualizer;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;

class ShortestPathController extends AbstractController {
    private Graph graph;
    private Vertex from;
    private Vertex to;
    private ShortestPath sp;

    ShortestPathController(Graph graph, Vertex from, Vertex to) throws PathNotFoundException {
        this.graph = graph;
        this.from = from;
        this.to = to;
        this.sp = new DijkstraAlgorithm(graph).calculateShortestPath(from, to);
    }

    void displayShortestPath() throws IOException {

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #FFFF;");

        ImageView graphViz = createGraphImage();
        Text title = createTitleNode();
        Text pathDescription = createPathDescription();
        layout.getChildren().addAll(title, graphViz, pathDescription);

        Scene scene = new Scene(layout);
        Stage stage = new Stage();
        stage.setTitle("Shortest Path");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.showAndWait();

    }

    private ImageView createGraphImage() throws IOException {
        BufferedImage img = GraphVisualizer.getImage(graph);
        Image image = SwingFXUtils.toFXImage(img, null);
        return new ImageView(image);
    }

    private Text createTitleNode() {
        Text title = new Text("Shortest path from " + from + " to " + to);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setWrappingWidth(800);
        return title;
    }

    private Text createPathDescription() {
        Text desc = new Text("Visited Nodes:" + sp.pathListToString("-->") + "\nTotal cost: " + sp.getWeight());
        desc.setFont(Font.font("Verdana", 20));
        desc.setTextAlignment(TextAlignment.CENTER);
        desc.setWrappingWidth(800);
        return desc;
    }
}
