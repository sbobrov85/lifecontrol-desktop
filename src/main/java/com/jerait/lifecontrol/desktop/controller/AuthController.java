/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jerait.lifecontrol.desktop.controller;

import com.jerait.lifecontrol.desktop.model.UserModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class.
 */
public class AuthController extends DialogController  implements Initializable {

    /**
     * Locale bundle resource.
     */
    @FXML
    private ResourceBundle resources;

    /**
     * Users list label.
     */
    @FXML
    private Label usersListLabel;

    /**
     * user list select box.
     */
    @FXML
    private ChoiceBox usersList;

    /**
     * User password field.
     */
    @FXML
    private PasswordField password;

    /**
     * Contained messages label.
     */
    @FXML
    private Label messagesLabel;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        resources = rb;
        String[] users = UserModel.getInstance().getUsernameList();
        usersList.setItems(FXCollections.observableArrayList(users));
        usersList.getSelectionModel().selectFirst();
    }

    /**
     * Handle dialog Enter (Ok) button action event.
     */
    @FXML
    private void handleEnterButton() {
        Object username = usersList.getSelectionModel().getSelectedItem();

        if (UserModel.getInstance().loadUserByUsername((String) username)) {
            if (UserModel.getInstance().checkPassword(password.getText())) {
                closeDialog(true);
            } else {
                messagesLabel.setText(
                    resources.getString("Wrong user password")
                );
            }
        } else {
            messagesLabel.setText(
                resources.getString("User loading error")
            );
        }
    }

    /**
     * Handle dialog Cancel button action event.
     */
    @FXML
    private void handleCancelButton() {
        closeDialog(false);
    }
}
