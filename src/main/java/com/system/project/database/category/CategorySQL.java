package com.system.project.database.category;

import com.system.project.model.category.Category;

import java.sql.*;

public class CategorySQL {

    private Connection con;
    private ResultSet result = null;

    public CategorySQL() {
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
            result = stmt.executeQuery("select * from category");
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
        return result;
    }

    // CREATE
    public void insertData(Category category) {
        try {
            String query = " insert into category (category_name) values (?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, category.getCategoryName());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    // UPDATE
    public void updateData(Category category) {
        try {
            String query = " update category set category_name=? where category_id=?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, category.getCategoryName());
            preparedStmt.setInt(2, category.getCategoryId());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    // DELETE
    public void deleteData(Category category) {
        try {
            String query = " delete from category where category_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, category.getCategoryId());
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
