package com.system.project.controller.mainFX;
import com.system.project.controller.order.OrderController;
import com.system.project.controller.product.ProductController;
import com.system.project.model.product.Product;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderCartControllerFX {


    @FXML
    public TextField textFieldCreateProductName;


    @FXML
    public TableView<Product> tableProduct;
    public TableColumn<Product, String> tableColumnProductId;
    public TableColumn<Product, String> tableColumnProductName;
    public TableColumn<Product, String> tableColumnProductDescription;
    public TableColumn<Product, String> tableColumnProductCategory;
    public TableColumn<Product, Product> tableColumnProductAddCart;


    @FXML
    public TableView<Product> tableProductCart;
    public TableColumn<Product, String> tableColumnProductCartId;
    public TableColumn<Product, String> tableColumnProductCartName;
    public TableColumn<Product, String> tableColumnProductCartDescription;
    public TableColumn<Product, String> tableColumnProductCartCategory;
    public TableColumn<Product, Product> tableColumnProductCartRemove;
    public CheckBox checkBoxCreate;
    List<Product> listOrderProduct = new ArrayList<>();

    @FXML
    public void initialize() {
        tableProductSetUp();
        tableProductCartSetUp();
    }

    private void tableProductSetUp() {
        ProductController productController = new ProductController();
        List<Product> listProduct = productController.readProduct();

        tableProduct.setItems(FXCollections.observableArrayList(listProduct));
        tableColumnProductId.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getProductId())));
        tableColumnProductName.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductName()));
        tableColumnProductDescription.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductDescription()));
        tableColumnProductCategory.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getCategoryId())));

        // Add To Cart Table Button
        tableColumnProductAddCart.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnProductAddCart.setCellFactory(param -> new TableCell<>() {
            private final Button addToCartButton = new Button("Add");

            @Override
            protected void updateItem(Product productObj, boolean empty) {
                super.updateItem(productObj, empty);
                if (productObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(addToCartButton);
                addToCartButton.setOnAction(event -> {
                    listOrderProduct.add(productObj);
                    tableProductCart.setItems(FXCollections.observableArrayList(listOrderProduct));
                });
            }
        });
    }

    private void tableProductCartSetUp() {
        tableProductCart.setItems(FXCollections.observableArrayList(listOrderProduct));
        tableColumnProductCartId.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getProductId())));
        tableColumnProductCartName.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductName()));
        tableColumnProductCartDescription.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductDescription()));
        tableColumnProductCartCategory.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getCategoryId())));

        // Add To Cart Table Button
        tableColumnProductCartRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnProductCartRemove.setCellFactory(param -> new TableCell<>() {
            private final Button removeFromCartButton = new Button("Remove");

            @Override
            protected void updateItem(Product productObj, boolean empty) {
                super.updateItem(productObj, empty);
                if (productObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(removeFromCartButton);
                removeFromCartButton.setOnAction(event -> {
                        Iterator<Product> iterator = listOrderProduct.iterator();
                        while (iterator.hasNext()){
                            Product productIterator = iterator.next();
                            if(productIterator.getProductId() == productObj.getProductId()){
                                iterator.remove();
                                break;
                            }
                        }
                    tableProductCart.setItems(FXCollections.observableArrayList(listOrderProduct));
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

    public void onButtonClickedCreateOrder(ActionEvent actionEvent) {
        if(checkBoxCreate.isSelected()){
        OrderController orderController = new OrderController();
        orderController.createOrderAndOrderProduct(listOrderProduct);
        listOrderProduct.clear();
        tableProductCart.setItems(FXCollections.observableArrayList(listOrderProduct));
            checkBoxCreate.setAllowIndeterminate(false);
          //  checkBoxCreate.setIndeterminate(false);
    }}

}
