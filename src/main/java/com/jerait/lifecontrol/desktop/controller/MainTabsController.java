package com.jerait.lifecontrol.desktop.controller;

import com.jerait.lifecontrol.desktop.model.AccountModel;
import com.jerait.lifecontrol.desktop.table.Account;
import com.jerait.lifecontrol.desktop.table.AccountGroup;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 * FXML Controller class.
 */
public class MainTabsController implements Initializable {

    /**
     * Locale bundle resource.
     */
    @FXML
    private ResourceBundle resources;

    /**
     * Contain main tabs instance.
     */
    @FXML
    private TabPane mainTabs;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        resources = rb;

        mainTabs.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Tab>() {
                @Override
                public void changed(
                    ObservableValue<? extends Tab> observable,
                    Tab oldValue,
                    Tab newValue
                ) {
                    //todo write destroy tabs content
                }
            }
        );

        setAccountsTabData();
    }

    /**
     * Create user interface for accounts tab.
     */
    protected final void setAccountsTabData()
    {
        List<AccountGroup> accountGroups = AccountModel.getInstance()
            .getAccountGroupAll();

        if (!accountGroups.isEmpty()) {
            TabPane accountGroupTabs = new TabPane();
            accountGroupTabs.setTabClosingPolicy(
                TabClosingPolicy.UNAVAILABLE
            );

            for (AccountGroup accountGroup: accountGroups) {
                String tabLabel = accountGroup.getLabel();

                if (tabLabel.contains("%")) {
                    tabLabel = resources.getString(
                        tabLabel.replaceAll("%", "")
                    );
                }

                Tab accountGroupTab = new Tab(tabLabel);
                accountGroupTabs.getTabs().add(accountGroupTab);
            }

            mainTabs.getTabs().get(0).setContent(accountGroupTabs);
        }
    }
}
