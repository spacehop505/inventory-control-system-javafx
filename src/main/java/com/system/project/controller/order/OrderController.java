package com.system.project.controller.order;

import com.system.project.database.order.OrderSQL;
import com.system.project.model.order.Order;
import com.system.project.model.order.OrderProduct;
import com.system.project.model.product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderController {


    // order and order product
    public void createOrderAndOrderProduct(List<Product> listOfProducts) {
        try {
            Date date = new Date();
            Order orderObj = new Order(date);
            OrderSQL orderSQL = new OrderSQL();
            orderSQL.insertOrderAndBatchInsertOrderProduct(orderObj, listOfProducts);
            orderSQL.closeSQL();
        } catch (Exception e) {
            System.out.println("Controller Error:" + e);
        }
    }
}


