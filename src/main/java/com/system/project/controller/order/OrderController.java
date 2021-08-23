package com.system.project.controller.order;

import com.system.project.database.order.OrderSQL;
import com.system.project.model.order.Order;
import com.system.project.model.order.OrderProduct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderController {


    // order and order product
    public void createOrderAndOrderProduct( int product_id) {
        List<OrderProduct> list = new ArrayList<>();
        try {
            for (int i = 0; i < 2; i++) {
                OrderProduct orderProduct = new OrderProduct(product_id);
                list.add(orderProduct);
            }
            System.out.println(list);

            Date aa = new Date();
            Order order = new Order(aa);
            System.out.println(order.getOrderDate());
            OrderSQL orderSQL = new OrderSQL();
            orderSQL.insertOrderAndBatchInsertOrderProduct(order, list);
            orderSQL.closeSQL();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


