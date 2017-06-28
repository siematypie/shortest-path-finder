package com.siematypie.controller;

import com.siematypie.engine.PathNotFoundException;
import com.siematypie.models.Graph;
import com.siematypie.models.Vertex;
import com.siematypie.utils.GraphFromCsvCreator;
import com.siematypie.utils.GraphVisualizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

class GraphController extends AbstractController{
    private Graph graph;

    GraphController(InputStream csvInputStream) throws IOException {
        graph = new GraphFromCsvCreator().graphFromInputStream(csvInputStream);
    }

    Scene getScene() throws IOException {
        Text title = new Text("Choose vertices to calculate shortest path");
        title.setFont(Font.font ("Verdana", FontWeight.BOLD, 35));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setWrappingWidth(1000);

        BufferedImage img = GraphVisualizer.getImage(graph);
        Image image = SwingFXUtils.toFXImage(img, null);
        ImageView graphView = new ImageView(image);
        VBox centerScr = new VBox(20);
        centerScr.setAlignment(Pos.CENTER);
        centerScr.getChildren().addAll(title, graphView);

        HBox bottomScr = new HBox(20);
        bottomScr.setAlignment(Pos.CENTER);

        ObservableList<Vertex> options =
                FXCollections.observableArrayList(graph.getVertices().values());

        Label fromLabel = new Label("Source:");
        final ComboBox fromBox = new ComboBox(options);

        Label toLabel = new Label("Destination:");
        final ComboBox toBox = new ComboBox(options);

        Button showSPBut = new Button("Calculate shortesh path!");
        showSPBut.setOnAction(e -> {
            calculateShortestPath(fromBox, toBox);

        });


        Button backBut = new Button("Back");
        backBut.setOnAction(event -> AppController.getInstance().showStartingScene());
        bottomScr.getChildren().addAll(fromLabel, fromBox, toLabel, toBox, showSPBut, backBut);

        BorderPane borderPane = new BorderPane(centerScr);
        borderPane.setBottom(bottomScr);

        return new Scene(borderPane);
    }

    private void calculateShortestPath(ComboBox fromBox, ComboBox toBox) {
        Vertex from = (Vertex) fromBox.getValue();
        Vertex to = (Vertex) toBox.getValue();
        if (from == null){
            showAlertWindow("Warning", "Can't calculate shortest path!",
                    "You have to choose source node before path calculating!", Alert.AlertType.WARNING);
        }

        else if (to == null){
            showAlertWindow("Warning", "Can't calculate shortest path!",
                    "You have to choose destination node before path calculating!", Alert.AlertType.WARNING);
        } else {

            try {
                ShortestPathController spc = new ShortestPathController(graph, from, to);
                spc.displayShortestPath();

            } catch (PathNotFoundException e) {
                String title = "Path not found";
                String content = "There's no connection between node " + from + " and node " + to;
                showAlertWindow(title, title,content, Alert.AlertType.WARNING);
                e.printStackTrace();
            } catch (IOException e){
                String title = "Error during graph visualisation";
                String content = "There's been unexpected error during graph creation, please try again";
                showAlertWindow(title, title,content, Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }
}
