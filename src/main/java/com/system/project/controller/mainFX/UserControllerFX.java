package com.system.project.controller.mainFX;

import com.system.project.controller.category.CategoryController;
import com.system.project.controller.user.UserController;
import com.system.project.model.category.Category;
import com.system.project.model.user.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class UserControllerFX {
    @FXML
    public TextField textFieldCreateUserName;
    public TextField textFieldCreateUserPassword;
    public TextField textFieldUpdateUserId;
    public TextField textFieldUpdateUserName;
    public TextField textFieldUpdateUserPassword;

    @FXML
    public TableView<User> tableUser;
    public TableColumn<User, String> tableColumnUserId;
    public TableColumn<User, String> tableColumnUserName;
    public TableColumn<User, String> tableColumnUserPassword;
    public TableColumn<User, User> tableColumnUserDelete;
    public TableColumn<User, User> tableColumnUserUpdate;

    public void initialize() {
        tableSetUp();
    }


    @FXML
    private void tableSetUp() {
        UserController userController = new UserController();

        List<User> listUser = userController.readUser();

        tableUser.setItems(FXCollections.observableArrayList(listUser));
        tableColumnUserId.setCellValueFactory(user -> new SimpleStringProperty(String.valueOf(user.getValue().getId())));
        tableColumnUserName.setCellValueFactory(user -> new SimpleStringProperty(user.getValue().getName()));
        tableColumnUserPassword.setCellValueFactory(user -> new SimpleStringProperty(user.getValue().getPassword()));

        // Delete Table Button
        tableColumnUserDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnUserDelete.setCellFactory(param -> new TableCell<>(){
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(User userObj, boolean empty) {
                super.updateItem(userObj, empty);
                if (userObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    userController.deleteUser(userObj.getId());
                    onButtonClickedRefreshUser();
                });
            }
        });

        // Update Table Button
        tableColumnUserUpdate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnUserUpdate.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");

            @Override
            protected void updateItem(User userObj, boolean empty) {
                super.updateItem(userObj, empty);
                if (userObj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(updateButton);
                updateButton.setOnAction(event -> {
                    textFieldUpdateUserId.setText(String.valueOf(userObj.getId()));
                    textFieldUpdateUserName.setText(userObj.getName());
                    textFieldUpdateUserPassword.setText(userObj.getPassword());
                });
            }
        });
    }

    // Create User
    public void onButtonClickedCreateUser() {
        UserController userController = new UserController();
        userController.createUser(textFieldCreateUserName.getText(), textFieldCreateUserPassword.getText());
        textFieldCreateUserName.setText("");
        textFieldCreateUserPassword.setText("");
        onButtonClickedRefreshUser();
    }

    // Update User
    public void onButtonClickedUpdateUser() {
        UserController userController = new UserController();
        userController.updateUser(Integer.parseInt(textFieldUpdateUserId.getText()), textFieldUpdateUserName.getText(), textFieldUpdateUserPassword.getText());
        textFieldUpdateUserId.setText("");
        textFieldUpdateUserName.setText("");
        textFieldUpdateUserPassword.setText("");
        onButtonClickedRefreshUser();
    }

    // Refresh Read User
    public void onButtonClickedRefreshUser() {
        UserController userController = new UserController();
        List<User> list = userController.readUser();
        tableUser.setItems(FXCollections.observableArrayList(list));
    }


}
