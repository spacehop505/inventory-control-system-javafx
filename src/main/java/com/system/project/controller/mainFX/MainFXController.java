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
        buttonChangeProductPane();

    }

    // Change to Product Window
    public void buttonChangeProductPane() {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(MainFX.class.getResource("paneProduct.fxml"));
            currentPane.getChildren().setAll(newLoadedPane);
        } catch (IOException e) {
            System.out.println("ERROR PANE:" + e);
        }

    }

    // Change to Category Window
    public void buttonChangeCategoryPane() {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(MainFX.class.getResource("paneCategory.fxml"));
            currentPane.getChildren().setAll(newLoadedPane);
        } catch (IOException e) {
            System.out.println("ERROR PANE:" + e);
        }

    }

    // Change to Category Order
    public void buttonChangeCreateOrder() {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(MainFX.class.getResource("paneOrderCart.fxml"));
            currentPane.getChildren().setAll(newLoadedPane);
        } catch (IOException e) {
            System.out.println("ERROR PANE:" + e);
        }

    }

    public void buttonChangeViewOrder() {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(MainFX.class.getResource("paneOrders.fxml"));
            currentPane.getChildren().setAll(newLoadedPane);
        } catch (IOException e) {
            System.out.println("ERROR PANE:" + e);
        }
    }
}
