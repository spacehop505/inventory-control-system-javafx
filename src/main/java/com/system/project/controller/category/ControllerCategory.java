package com.system.project.controller.category;

import com.system.project.database.category.CategorySQL;
import com.system.project.model.category.Category;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControllerCategory {

    Scanner myObj = new Scanner(System.in);

    public List<Category> readCategory() {
        List<Category> list = new ArrayList<>();
        try {
            CategorySQL categorySQL = new CategorySQL();
            ResultSet result = categorySQL.getData();
            while (result.next()) {
                Category category = new Category(result.getInt(1), result.getString(2));
                list.add(category);
            }

            categorySQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
        return list;
    }

    public void createCategory(String category_name) {
        try {
            Category category = new Category(category_name);

            CategorySQL categorySQL = new CategorySQL();
            categorySQL.insertData(category);

            categorySQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }

    public void updateCategory() {
        try {
            System.out.println("Enter category_name:");
            String category_name = myObj.next();
            System.out.println("Enter category_id:");
            int category_id = myObj.nextInt();
            Category category = new Category(category_id, category_name);

            CategorySQL categorySQL = new CategorySQL();
            categorySQL.updateData(category);

            categorySQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }

    public void deleteCategory() {
        try {
            System.out.println("Enter category_id:");
            int category_id = myObj.nextInt();
            Category category = new Category(category_id);
            CategorySQL categorySQL = new CategorySQL();
            categorySQL.deleteData(category);

            categorySQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }
}
