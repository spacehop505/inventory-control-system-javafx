package com.system.project.database.product;

import com.system.project.model.product.Product;

import java.sql.*;

public class ProductSQL {

    private Connection con;
    private ResultSet result = null;

    public ProductSQL() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "admin");
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    // READ
    public ResultSet getData() {
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeQuery("select * from product");
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
        return result;
    }

    // CREATE
    public void insertData(Product product) {
        try {
            String query = " insert into product (product_name, product_description, fk_category_id) values (?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, product.getProductName());
            preparedStmt.setString(2, product.getProductDescription());
            preparedStmt.setInt(3, product.getCategoryId());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    // UPDATE
    public void updateData(Product product) {
        try {
            String query = " update product set product_name=?, product_description=?,fk_category_id=?  where product_id=?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, product.getProductName());
            preparedStmt.setString(2, product.getProductDescription());
            preparedStmt.setInt(3, product.getCategoryId());
            preparedStmt.setInt(4, product.getProductId());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    // DELETE
    public void deleteData(Product product) {
        try {
            String query = " delete from product where product_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, product.getProductId());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    public void closeSQL() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }


}
