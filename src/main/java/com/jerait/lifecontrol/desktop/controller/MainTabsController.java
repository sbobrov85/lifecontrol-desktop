package com.jerait.lifecontrol.desktop.controller;

import com.jerait.lifecontrol.desktop.model.AccountModel;
import com.jerait.lifecontrol.desktop.table.Account;
import com.jerait.lifecontrol.desktop.table.AccountGroup;
import com.jerait.lifecontrol.desktop.table.AccountType;
import com.jerait.lifecontrol.desktop.utils.GUI;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

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
     * Accounts tab instance.
     */
    @FXML
    private Tab accountsTab;

    /**
     * Budgets tab instance.
     */
    @FXML
    private Tab budgetsTab;

    /**
     * Reports tab instance.
     */
    @FXML
    private Tab reportsTab;

    /**
     * Settings tab instance.
     */
    @FXML
    private Tab settingsTab;

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
                    oldValue.setContent(null);

                    switch (newValue.getId()) {
                        case "accountsTab":
                            setAccountsTabData();
                            break;
                        case "settingsTab":
                            setSettingsTabData();
                            break;

                        default:
                    }
                }
            }
        );

        setAccountsTabData();
    }

    /**
     * Create user interface for accounts tab.
     */
    protected final void setAccountsTabData() {
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

                accountGroupTab.setContent(
                    getAccountsAccordion(accountGroup.getAccountGroupId())
                );

                accountGroupTabs.getTabs().add(accountGroupTab);
            }

            mainTabs.getTabs().get(0).setContent(accountGroupTabs);
        }
    }

    /**
     * Create accounts list accordion node.
     * @param accountGroupId account group id from db.
     * @return Accordion node with accounts list.
     */
    protected final Accordion getAccountsAccordion(final int accountGroupId) {
        Accordion accountsList = new Accordion();

        List<AccountType> accountTypes = AccountModel.getInstance()
            .getAccountTypes();

        for (AccountType accountType: accountTypes) {
            int accountTypeId = accountType.getAccountType();

            List<Account> accounts = AccountModel.getInstance()
                .getAccounts(accountGroupId, accountTypeId);

            if (!accounts.isEmpty()) {
                TitledPane accountTypePane = new TitledPane();
                accountTypePane.setText(accountType.getLabel());
                accountTypePane.setExpanded(false);

                GridPane accountsGridPane = new GridPane();

                int accountsIndex = 0;

                for (Account account: accounts) {
                    accountsGridPane.add(
                        new Label(account.getLabel()),
                        1,
                        accountsIndex++
                    );
                }

                accountTypePane.setContent(accountsGridPane);

                accountsList.getPanes().add(accountTypePane);
                accountsList.setExpandedPane(accountsList.getPanes().get(0));
            }

        }

        return accountsList;
    }

    /**
     * Create user interface for settings tab.
     */
    protected final void setSettingsTabData() {
        try {
            Parent root = GUI.getLayout("SettingsTab");
            settingsTab.setContent(root);
        } catch (IOException ex) {
            Logger.getLogger(MainTabsController.class.getName())
                .log(Level.SEVERE, null, ex);
        }
    }
}
