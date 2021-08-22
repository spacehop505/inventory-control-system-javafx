package com.system.project.database.order;

import com.system.project.model.order.Order;
import com.system.project.model.order.OrderProduct;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderSQL {
    private Connection con;
    private ResultSet result = null;

    public OrderSQL() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "admin");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // BATCH CREATE
    public void insertOrderAndBatchInsertOrderProduct(Order order, List<OrderProduct> list) {
        String lastInsertQuery = "select last_insert_id();";
        String orderQuery = " insert into orders (order_date) values (?)";
        String orderProductQuery = " insert into order_product (fk_order_id, fk_product_id) values (?,?)";

        try (PreparedStatement orderStatement = con.prepareStatement(orderQuery);
             PreparedStatement orderProductStatement = con.prepareStatement(orderProductQuery);
             PreparedStatement lastInsertStatement = con.prepareStatement(lastInsertQuery);
        ) {
            con.setAutoCommit(false);
            int orderId = 0;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String aa = formatter.format(order.getOrderDate());
            orderStatement.setString(1, aa);
            orderStatement.execute();

            result = lastInsertStatement.executeQuery();
            while (result.next()) {
                orderId = result.getInt(1);
            }

            for (OrderProduct orderProduct : list) {
                System.out.println(orderId + " " + orderProduct.getOrderProductId());
                orderProductStatement.setInt(1, orderId);
                orderProductStatement.setInt(2, orderProduct.getOrderProductId());
                orderProductStatement.execute();
            }
            con.commit();
            // employeeStmt.executeBatch();
        } catch (Exception e) {
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch (SQLException excep) {
                    // JDBCTutorialUtilities.printSQLException(excep);
                }
            }
        }
    }

    public void closeSQL() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}