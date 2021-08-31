package com.system.project.controller.mainFX;

import com.system.project.controller.order.OrderController;
import com.system.project.controller.product.ProductController;
import com.system.project.model.order.Order;
import com.system.project.model.product.Product;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderControllerFX {
    @FXML
    public TableView<Order> tableOrder;
        public TableColumn<Order, String> tableColumnOrderId;
    public TableColumn<Order, String> tableColumnOrderDate;
    public TableColumn<Order, Order> tableColumnViewOrder;

    @FXML
    public TableView<Product> tableOrderProduct;
    public TableColumn<Product, String> tableColumnProductId;
    public TableColumn<Product, String>  tableColumnProductName;
    public TableColumn<Product, String>  tableColumnProductDescription;
    public TableColumn<Product, String>  tableColumnProductCategory;
    public TableColumn<Product, String> tableColumnProductOrderId;
   public  List<Product> listProduct = new ArrayList<>();


    @FXML
    public void initialize() {
        tableOrderSetUp();
       tableProductSetUp();
    }

    private void  tableProductSetUp(){
        tableColumnProductOrderId.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getOrderId())));
        tableColumnProductId.setCellValueFactory(product -> new SimpleStringProperty(String.valueOf(product.getValue().getProductId())));
        tableColumnProductName.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductName()));
        tableColumnProductDescription.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getProductDescription()));
        tableColumnProductCategory.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getCategoryName()));
        tableOrderProduct.setItems(FXCollections.observableArrayList(listProduct));
    }

    private void tableOrderSetUp() {
        OrderController orderController = new OrderController();
        List<Order> listOrder = orderController.readOrder();

        tableOrder.setItems(FXCollections.observableArrayList(listOrder));
        tableColumnOrderId.setCellValueFactory(order -> new SimpleStringProperty(String.valueOf(order.getValue().getOrderId())));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tableColumnOrderDate.setCellValueFactory(order -> new SimpleStringProperty( formatter.format(order.getValue().getOrderDate())));

        // Add To Cart Table Button
        tableColumnViewOrder.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnViewOrder.setCellFactory(param -> new TableCell<>() {
            private final Button addToCartButton = new Button("View Order");

            @Override
            protected void updateItem(Order orderObj, boolean empty) {
                super.updateItem(orderObj, empty);
                if (orderObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(addToCartButton);
                addToCartButton.setOnAction(event -> {
                    listProduct = orderController.readOrderProducts(orderObj);
                    tableOrderProduct.setItems(FXCollections.observableArrayList(listProduct));
                });
            }
        });
    }



    public void onButtonClickedRefreshOrder(ActionEvent actionEvent) {
        OrderController orderController = new OrderController();
        List<Order> listOrder = orderController.readOrder();
        tableOrder.setItems(FXCollections.observableArrayList(listOrder));
        listProduct.clear();
        tableOrderProduct.setItems(FXCollections.observableArrayList(listProduct));
    }


}
