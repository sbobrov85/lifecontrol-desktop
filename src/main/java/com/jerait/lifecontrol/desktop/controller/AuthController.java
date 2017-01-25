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
import javafx.stage.Stage;

/**
 * FXML Controller class.
 */
public class AuthController implements Initializable {

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
     * Contained dialog Stage loaded from fxml view.
     */
    private Stage dialogStage;

    /**
     * Contained dialog result after close.
     */
    private boolean okClicked = false;

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
                okClicked = true;
                dialogStage.close();
            } else {
                messagesLabel.setText("%Wrong user password");
            }
        } else {
            messagesLabel.setText("%User loading error");
        }
    }

    /**
     * Handle dialog Cancel button action event.
     */
    @FXML
    private void handleCancelButton() {
        okClicked = false;
        dialogStage.close();
    }

    /**
     * Set dialog Stage into property.
     * @param dialogStage dialog Stage instance.
     */
    public final void setDialogStage(Stage dialogStage) {
        dialogStage.resizableProperty().set(false);
        this.dialogStage = dialogStage;
    }

    /**
     * Check dialog result.
     * @return true, if Enter button (Ok) pressed.
     */
    public final boolean isOkClicked() {
        return okClicked;
    }
}
