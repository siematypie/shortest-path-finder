package com.siematypie;

import com.siematypie.controller.AppController;
import javafx.application.Application;
import javafx.stage.*;

public class jfxMain extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AppController ac = AppController.getInstance();
        ac.setStage(primaryStage);
        ac.runWithStartingScene();
    }

}