package com.system.project.controller.order;

import com.system.project.database.order.OrderSQL;
import com.system.project.database.product.ProductSQL;
import com.system.project.model.order.Order;
import com.system.project.model.product.Product;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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

    public List<Order> readOrder() {
        List<Order> list = new ArrayList<>();
        try {
            OrderSQL orderSQL = new OrderSQL();
            ResultSet result = orderSQL.getData();
            while (result.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Order orderObj = new Order(result.getInt(1), formatter.parse(result.getString(2)));
                list.add(orderObj);
            }
            orderSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
        return list;
    }

    public List<Product> readOrderProducts(Order order) {
        List<Product> list = new ArrayList<>();
        try {
            OrderSQL orderSQL = new OrderSQL();
            ResultSet result = orderSQL.readOrderedProduct(order);
            while (result.next()) {
                Product orderObj = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
                orderObj.setOrderId(result.getInt(5));
                list.add(orderObj);
            }
            orderSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
        return list;
    }


}


