package com.system.project.controller.product;

import com.system.project.database.product.ProductSQL;
import com.system.project.model.product.Product;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    public List<Product> readProduct() {
        List<Product> list = new ArrayList<>();
        try {
            ProductSQL productSQL = new ProductSQL();
            ResultSet result = productSQL.getData();
            while (result.next()) {
                Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                list.add(product);
            }
            productSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
        return list;
    }

    public void createProduct(String product_name, String product_description, int category_id) {
        try {
            Product product = new Product(product_name, product_description, category_id);

            ProductSQL productSQL = new ProductSQL();
            productSQL.insertData(product);

            productSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }

    public void updateProduct( int product_id,  String product_name,  String product_description, int category_id) {
        try {
            Product product = new Product(product_id, product_name, product_description, category_id);

            ProductSQL productSQL = new ProductSQL();
            productSQL.updateData(product);

            productSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }

    public void deleteProduct(int product_id) {
        try {
            Product product = new Product();
            product.setProductId(product_id);
            ProductSQL productSQL = new ProductSQL();
            productSQL.deleteData(product);

            productSQL.closeSQL();
        } catch (Exception e) {
            System.err.println("ERROR - CONTROLLER:" + e);
        }
    }

}
