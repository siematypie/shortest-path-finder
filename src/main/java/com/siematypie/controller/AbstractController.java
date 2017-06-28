package com.siematypie.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

abstract class AbstractController {

    static InputStream getResourceAsInputStream(String path) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(path);
    }

    static ImageView imgFromResource(String path, Integer height) {
        InputStream is = getResourceAsInputStream(path);
        ImageView iv = new ImageView(new Image(is));
        iv.setFitHeight(height);
        iv.setPreserveRatio(true);
        return iv;
    }

    static void showAlertWindow(String title, String header, String content, Alert.AlertType alertType) {
        Label label = new Label(content);
        label.setWrapText(true);
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().setContent(label);
        alert.showAndWait();
    }
}
