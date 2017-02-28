package com.jerait.lifecontrol.desktop.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * FXML Controller class.
 */
public class SettingsTabController implements Initializable {

    /**
    * Locale bundle resource.
    */
    @FXML
    private ResourceBundle resources;

    /**
     * Contains categories tree instance.
     */
    @FXML
    private TreeView<String> categoriesTree;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        resources = rb;

        loadCategoriesTree();
    }

    /**
     * Load categories into TreeView.
     */
    protected final void loadCategoriesTree() {
        TreeItem<String> rootItem = new TreeItem<>(
            resources.getString("AllCategories")
        );

        rootItem.setExpanded(true);

        categoriesTree.setRoot(rootItem);
    }
}
