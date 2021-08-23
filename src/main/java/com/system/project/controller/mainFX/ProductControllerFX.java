package com.system.project.controller.mainFX;

import com.system.project.controller.category.CategoryController;
import com.system.project.controller.product.ProductController;
import com.system.project.model.category.Category;
import com.system.project.model.product.Product;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ProductControllerFX {

    @FXML
    public TextField textFieldCreateProductName;
    public TextField textFieldCreateProductDescription;
    public TextField textFieldUpdateProductId;
    public TextField textFieldUpdateProductName;
    public TextField textFieldUpdateProductDescription;

    @FXML
    public ComboBox<Category> comboBoxCreateProductCategory;
    public ComboBox<Category> comboBoxUpdateProductCategory;

    @FXML
    public TableView<Product> tableProduct;
    public TableColumn<Product, String> tableColumnProductId;
    public TableColumn<Product, String> tableColumnProductName;
    public TableColumn<Product, String> tableColumnProductDescription;
    public TableColumn<Product, String> tableColumnProductCategory;
    public TableColumn<Product, Product> tableColumnProductDelete;
    public TableColumn<Product, Product> tableColumnProductUpdate;

    @FXML
    public void initialize() {
        tableSetUp();
    }

    private void tableSetUp() {
        ProductController productController = new ProductController();
        CategoryController categoryController = new CategoryController();

        List<Product> listProduct = productController.readProduct();
        List<Category> listCategory = categoryController.readCategory();

        tableProduct.setItems(FXCollections.observableArrayList(listProduct));
        comboBoxCreateProductCategory.setItems(FXCollections.observableArrayList(listCategory));
        comboBoxCreateProductCategory.setValue(listCategory.get(0));
        comboBoxUpdateProductCategory.setItems(FXCollections.observableArrayList(listCategory));
        comboBoxUpdateProductCategory.setValue(listCategory.get(0));

        tableColumnProductId.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getProductId())));
        tableColumnProductName.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductName()));
        tableColumnProductDescription.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductDescription()));
        tableColumnProductCategory.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getCategoryId())));

        // Delete Table Button
        tableColumnProductDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnProductDelete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Product productObj, boolean empty) {
                super.updateItem(productObj, empty);
                if (productObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    productController.deleteProduct(productObj.getProductId());
                    onButtonClickedRefreshProduct();
                });
            }
        });

        // Update Table Button
        tableColumnProductUpdate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnProductUpdate.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");

            @Override
            protected void updateItem(Product productObj, boolean empty) {
                super.updateItem(productObj, empty);
                if (productObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(updateButton);
                updateButton.setOnAction(event -> {
                    List<Category> listCategory = categoryController.readCategory();
                    comboBoxUpdateProductCategory.setItems(FXCollections.observableArrayList(listCategory));
                    int index = 0;
                    for (int i = 0; i < listCategory.size(); i++) {
                        if (listCategory.get(i).getCategoryId() == productObj.getCategoryId()) {
                            index = i;
                            break;
                        }
                    }
                    comboBoxUpdateProductCategory.setValue(listCategory.get(index));
                    textFieldUpdateProductId.setText(String.valueOf(productObj.getProductId()));
                    textFieldUpdateProductName.setText(productObj.getProductName());
                    textFieldUpdateProductDescription.setText(productObj.getProductDescription());
                });
            }
        });
    }

    // Refresh Read Product
    public void onButtonClickedRefreshProduct() {
        ProductController productController = new ProductController();
        List<Product> list = productController.readProduct();
        tableProduct.setItems(FXCollections.observableArrayList(list));
    }

    // Create Product
    public void onButtonClickedCreateProduct() {
        ProductController productController = new ProductController();
        Category category_id = comboBoxCreateProductCategory.getValue();
        productController.createProduct(textFieldCreateProductName.getText(), textFieldCreateProductDescription.getText(), category_id.getCategoryId());
        textFieldCreateProductName.setText("");
        textFieldCreateProductDescription.setText("");
        onButtonClickedRefreshProduct();
    }

    // Update Product
    public void onButtonClickedUpdateProduct() {
        ProductController controllerProduct = new ProductController();
        Category category_id = comboBoxUpdateProductCategory.getValue();
        controllerProduct.updateProduct(Integer.parseInt(textFieldUpdateProductId.getText()), textFieldUpdateProductName.getText(), textFieldUpdateProductDescription.getText(), category_id.getCategoryId());
        textFieldUpdateProductId.setText("");
        textFieldUpdateProductName.setText("");
        textFieldUpdateProductDescription.setText("");
        onButtonClickedRefreshProduct();
    }

}
