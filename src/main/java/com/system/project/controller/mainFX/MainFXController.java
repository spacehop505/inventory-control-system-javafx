package com.system.project.controller.mainFX;

import com.system.project.MainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainFXController {

    @FXML
    public Pane currentPane;

    @FXML
    public void initialize() {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(MainFX.class.getResource("paneProduct.fxml"));
        } catch (IOException e) {
            System.out.println("ERROR PANE:" + e);
        }
        currentPane.getChildren().setAll(newLoadedPane);
    }

    // Change to Product Window
    public void buttonChangeProductPane(ActionEvent actionEvent) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(MainFX.class.getResource("paneProduct.fxml"));
        } catch (IOException e) {
            System.out.println("ERROR PANE:" + e);
        }
        currentPane.getChildren().setAll(newLoadedPane);
    }

    // Change to Category Window
    public void buttonChangeCategoryPane(ActionEvent actionEvent) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(MainFX.class.getResource("paneCategory.fxml"));
        } catch (IOException e) {
            System.out.println("ERROR PANE:" + e);
        }
        currentPane.getChildren().setAll(newLoadedPane);
    }

}
