package com.system.project.database.user;

import com.system.project.model.user.User;

import java.sql.*;

public class UserSQL {

    private Connection con;
    private ResultSet result = null;

    public UserSQL() {
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
            result = stmt.executeQuery("select * from users");
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
        return result;
    }

    // CREATE
    public void insertData(User user) {
        try {
            String query = " insert into users (user_name, user_password) values (?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, user.getName());
            preparedStmt.setString(2, user.getPassword());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    // UPDATE
    public void updateData(User user) {
        try {
            String query = " update users set user_name=?, user_password=? where user_id=?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, user.getName());
            preparedStmt.setString(2, user.getPassword());
            preparedStmt.setInt(3, user.getId());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("ERROR - SQL: " + e);
        }
    }

    // DELETE
    public void deleteData(User user) {
        try {
            String query = " delete from users where user_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, user.getId());
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
