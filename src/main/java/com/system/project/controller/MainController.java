package com.system.project.controller;

import com.system.project.controller.category.ControllerCategory;
import com.system.project.controller.order.ControllerOrder;
import com.system.project.controller.product.ControllerProduct;

import java.util.Scanner;

public class MainController {


    private ControllerCategory controllerCategory = new ControllerCategory();
    private ControllerOrder controllerOrder = new ControllerOrder();
    private ControllerProduct controllerProduct = new ControllerProduct();
    public void code() {

        boolean isTrue1 = true;
        boolean isTrue2 = true;

        while (isTrue1) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter expression:");
            String expression = myObj.nextLine();

            switch (expression) {
                case "1":
                    controllerProduct.readProduct();
                    break;

                case "2":
                    controllerProduct.createProduct("ff","fff", 1);
                    break;

                case "3":
                  //  controllerProduct.updateProduct();
                    break;

                case "4":
                    controllerProduct.deleteProduct(1);
                    break;

                case "q":
                    isTrue1 = false;
                    break;
            }
        }

        while (isTrue2) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter expression:");
            String expression = myObj.nextLine();

            switch (expression) {
                case "1":
                    controllerCategory.readCategory();
                    break;

                case "2":
                    controllerCategory.createCategory("ffff");
                    break;

                case "3":
                    controllerCategory.updateCategory();
                    break;

                case "4":
                    controllerCategory.deleteCategory();
                    break;

                case "q":
                    isTrue2 = false;
                    break;
            }
        }

    }

}
