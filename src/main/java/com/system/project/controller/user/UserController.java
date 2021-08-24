package com.system.project.controller.user;

import com.system.project.database.category.CategorySQL;
import com.system.project.database.user.UserSQL;
import com.system.project.model.category.Category;
import com.system.project.model.user.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public List<User> readUser() {
        List<User> list = new ArrayList<>();
        try {
            UserSQL userSQL = new UserSQL();
            ResultSet result = userSQL.getData();
            while (result.next()) {
                User user = new User(result.getInt(1), result.getString(2), result.getString(3));
                list.add(user);
            }

            userSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
        return list;
    }

    public void createUser(String user_name, String user_password) {
        try {
            User userObj = new User(user_name, user_password);
            UserSQL userSQL = new UserSQL();
            userSQL.insertData(userObj);

            userSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }

    public void updateUser(int user_id, String user_name, String user_password) {
        try {
            User userObj = new User(user_name, user_password);
            userObj.setId(user_id);
            UserSQL userSQL = new UserSQL();
            userSQL.updateData(userObj);

            userSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }

    public void deleteUser(int user_id) {
        try {
            User userObj = new User();
            userObj.setId(user_id);
            UserSQL userSQL = new UserSQL();
            userSQL.deleteData(userObj);

            userSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }
}
