package com.system.project.model.product;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private int categoryId;
    private String categoryName;
    private int orderId;
    public Product(){

    }

    public Product(int productId, String productName, String productDescription, int categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Product(int productId, String productName, String productDescription, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
    }

    public Product(String productName, String productDescription, int categoryId) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
    }


    public Product(int productId, String productName, String productDescription, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryName = categoryName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
