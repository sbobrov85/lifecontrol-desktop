package com.jerait.lifecontrol.desktop.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.jerait.lifecontrol.desktop.table.Account;
import com.jerait.lifecontrol.desktop.table.AccountGroup;
import com.jerait.lifecontrol.desktop.utils.Database;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountModel {
    /**
     * Contain dao for work with accounts tables.
     */
    private Dao<Account, ?> dao;

    /**
     * Contain singleton instance of AccountModel.
     */
    private static AccountModel instance;

    /**
     * Close constructor for Singleton.
     */
    private AccountModel() {
        try {
            dao = DaoManager.createDao(
                Database.getDatabaseConnection(),
                Account.class
            );
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get instance of Account model.
     * @return AccountModel instance.
     */
    public static AccountModel getInstance() {
        if (!(instance instanceof AccountModel)) {
            instance = new AccountModel();
        }

        return instance;
    }

    /**
     * Get all accounts list.
     * @return accounts list or null.
     */
    public List<Account> getAccountsAll() {
        List<Account> accounts = null;

        try {
            accounts = dao.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return accounts;
    }

    /**
     * Get all accounts groups list.
     * @return groups list or null.
     */
    public List<AccountGroup> getAccountGroupAll() {
        List<AccountGroup> groups = null;

        Dao<AccountGroup, ?> groupsDao;

        try {
            groupsDao = DaoManager.createDao(
                    Database.getDatabaseConnection(),
                    AccountGroup.class
            );
            groups = groupsDao.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return groups;
    }
}
