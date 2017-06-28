package com.siematypie.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;

public class AppController extends AbstractController {
    private static AppController instance = new AppController();
    private Stage stage;
    private Scene startingScene;

    public static AppController getInstance() {
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage){
        this.stage = stage;
        stage.setTitle("Djikstra's Shortest path by Siematypie");
    }

    public void runWithStartingScene(){
        if (stage != null) {
            startingScene = new FileLoaderController(stage).getScene();
            showStartingScene();
            stage.show();
        }
    }

    void showStartingScene(){
        stage.setScene(startingScene);
        stage.setResizable(false);
        stage.sizeToScene();
    }

    void showGraphMenu(InputStream is) {
        try {
            stage.setScene(new GraphController(is).getScene());
        } catch (IOException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            String title = "Error during file reading";
            String content = "An error occured while reading file. Make sure you are using correct csv format, click on" +
                    "help button for more info, check your csv and try again";
            showAlertWindow(title, title,content, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    void showGraphMenu(File file){
        try {
            showGraphMenu(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            String title = "Error during file reading";
            String content = "File not found";
            showAlertWindow(title, title,content, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
