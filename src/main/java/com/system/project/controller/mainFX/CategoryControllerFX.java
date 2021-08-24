package com.system.project.controller.mainFX;

import com.system.project.controller.category.CategoryController;
import com.system.project.model.category.Category;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class CategoryControllerFX {

    @FXML
    public TextField textFieldCreateCategoryName;
    public TextField textFieldUpdateCategoryId;
    public TextField textFieldUpdateCategoryName;

    @FXML
    public TableView<Category> tableCategory;
    public TableColumn<Category, String> tableColumnProductId;
    public TableColumn<Category, String> tableColumnProductName;
    public TableColumn<Category, Category> tableColumnProductDelete;
    public TableColumn<Category, Category> tableColumnProductUpdate;

    public void initialize() {
        tableSetUp();
    }

    @FXML
    private void tableSetUp() {
        CategoryController categoryController = new CategoryController();

        List<Category> listCategory = categoryController.readCategory();

        tableCategory.setItems(FXCollections.observableArrayList(listCategory));
        tableColumnProductId.setCellValueFactory(category -> new SimpleStringProperty(String.valueOf(category.getValue().getCategoryId())));
        tableColumnProductName.setCellValueFactory(category -> new SimpleStringProperty(category.getValue().getCategoryName()));

        // Delete Table Button
        tableColumnProductDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnProductDelete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Category categoryObj, boolean empty) {
                super.updateItem(categoryObj, empty);
                if (categoryObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    categoryController.deleteCategory(categoryObj.getCategoryId());
                    onButtonClickedRefreshCategory();
                });
            }
        });

        // Update Table Button
        tableColumnProductUpdate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnProductUpdate.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");

            @Override
            protected void updateItem(Category categoryObj, boolean empty) {
                super.updateItem(categoryObj, empty);
                if (categoryObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(updateButton);
                updateButton.setOnAction(event -> {
                    textFieldUpdateCategoryId.setText(String.valueOf(categoryObj.getCategoryId()));
                    textFieldUpdateCategoryName.setText(categoryObj.getCategoryName());
                });
            }
        });
    }
    // Refresh Read Product
    public void onButtonClickedRefreshCategory() {
        CategoryController categoryController = new CategoryController();
        List<Category> list = categoryController.readCategory();
        tableCategory.setItems(FXCollections.observableArrayList(list));
    }

    // Create Product
    public void onButtonClickedCreateCategory() {
        CategoryController categoryController = new CategoryController();
        categoryController.createCategory(textFieldCreateCategoryName.getText());
        textFieldCreateCategoryName.setText("");
        onButtonClickedRefreshCategory();
    }

    // Update Product
    public void onButtonClickedUpdateCategory() {
        CategoryController categoryController = new CategoryController();
        categoryController.updateCategory(Integer.parseInt(textFieldUpdateCategoryId.getText()), textFieldUpdateCategoryName.getText());
        textFieldUpdateCategoryId.setText("");
        textFieldUpdateCategoryName.setText("");
        onButtonClickedRefreshCategory();
    }

}
