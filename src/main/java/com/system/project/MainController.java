package com.system.project;

import com.system.project.controller.category.ControllerCategory;
import com.system.project.controller.product.ControllerProduct;
import com.system.project.model.category.Category;

import com.system.project.model.product.Product;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private TextField nameTextField;
    public TextField descriptionTextField;

    @FXML
    private ComboBox<Category> comboBox;

    @FXML
    public TableView<Product> tableProduct;
    public TableColumn<Product, String> tableColumnProductId;
    public TableColumn<Product, String> tableColumnProductName;
    public TableColumn<Product, String> tableColumnProductDescription;
    public TableColumn<Product, String> tableColumnProductCategory;
    public TableColumn<Product, Product> tableColumnProductDelete;
    public TableColumn<Product, Product> tableColumnProductUpdate;

    @FXML
    public TextField idTextFieldUpdate;
    public TextField nameTextFieldUpdate;
    public TextField descriptionTextFieldUpdate;

    @FXML
    public ComboBox<Category> comboBoxUpdate;





    public void onButtonClickedRefreshProduct() {
        ControllerProduct controllerProduct = new ControllerProduct();
        List<Product> list = controllerProduct.readProduct();
        tableProduct.setItems(FXCollections.observableArrayList(list));
    }

    public void onButtonClickedCreateProduct(ActionEvent actionEvent) {
        ControllerProduct controllerProduct = new ControllerProduct();
        Category category_id = comboBox.getValue();
        int test = category_id.getCategoryId();
        controllerProduct.createProduct(nameTextField.getText(), descriptionTextField.getText(), test);
        System.out.println("NAME: " + nameTextField.getText());
        nameTextField.setText("");
        descriptionTextField.setText("");
        onButtonClickedRefreshProduct();
    }


    @FXML
    public void initialize() {
        ControllerProduct controllerProduct = new ControllerProduct();
        List<Product> list1 = controllerProduct.readProduct();
        ControllerCategory category = new ControllerCategory();
        List<Category> list = category.readCategory();
        comboBox.setItems(FXCollections.observableArrayList(list));
        comboBox.setValue(list.get(0));

        List<String> list2 = new ArrayList<>();
        for (Product product : list1) {
            list2.add(product.getProductName());
        }

        tableColumnProductId.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getProductId())));
        tableColumnProductName.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductName()));
        tableColumnProductDescription.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductDescription()));
        tableColumnProductCategory.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getCategoryId())));


        tableColumnProductDelete.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );

        tableColumnProductUpdate.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );

        tableColumnProductDelete.setCellFactory(param -> new TableCell<Product, Product>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Product product, boolean empty) {
                System.out.println("1");
                super.updateItem(product, empty);

                if (product == null) {
                    System.out.println("2");
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {
                            controllerProduct.deleteProduct(product.getProductId());
                            //  getTableView().getItems().remove(product);
                            // ControllerProduct controllerProduct1 = new ControllerProduct();
                            //   int selectedIndex = getTableRow().getIndex();
                            List<Product> list11 = controllerProduct.readProduct();
                            tableProduct.setItems(FXCollections.observableArrayList(list11));
                            System.out.println("3");
                        }
                );
            }
        });


        tableColumnProductUpdate.setCellFactory(param -> new TableCell<Product, Product>() {
            private final Button updateButton = new Button("Update");

            @Override
            protected void updateItem(Product product, boolean empty) {
                System.out.println("1");
                super.updateItem(product, empty);

                if (product == null) {
                    System.out.println("2");
                    setGraphic(null);
                    return;
                }

                setGraphic(updateButton);
                updateButton.setOnAction(
                        event -> {
                            ControllerCategory category = new ControllerCategory();
                            List<Category> list = category.readCategory();
                            comboBoxUpdate.setItems(FXCollections.observableArrayList(list));
                            int index = 0;
                            for(int i =0; i < list.size(); i++ ){
                                if(list.get(i).getCategoryId() == product.getCategoryId()){
                                    index = i;
                                }
                            }
                            comboBoxUpdate.setValue(list.get(index));
                            idTextFieldUpdate.setText(String.valueOf(product.getProductId()));
                            nameTextFieldUpdate.setText(product.getProductName());
                            descriptionTextFieldUpdate.setText(product.getProductDescription());
                        }
                );
            }
        });

        tableProduct.setItems(FXCollections.observableArrayList(list1));

    }

    public void onButtonClickedUpdateProduct(ActionEvent actionEvent) {
        ControllerProduct controllerProduct = new ControllerProduct();
        Category category_id = comboBoxUpdate.getValue();
        int test = category_id.getCategoryId();
        controllerProduct.updateProduct(Integer.parseInt(idTextFieldUpdate.getText()), nameTextFieldUpdate.getText(), descriptionTextFieldUpdate.getText(), test);
        idTextFieldUpdate.setText("");
        nameTextFieldUpdate.setText("");
        descriptionTextFieldUpdate.setText("");
        onButtonClickedRefreshProduct();

    }
}
