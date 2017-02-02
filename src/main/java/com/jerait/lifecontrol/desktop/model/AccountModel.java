package com.jerait.lifecontrol.desktop.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jerait.lifecontrol.desktop.table.Account;
import com.jerait.lifecontrol.desktop.table.AccountGroup;
import com.jerait.lifecontrol.desktop.table.AccountType;
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
     * @param accountGroupId account group id.
     * @param accountTypeId account type id.
     * @return accounts list or null.
     */
    public List<Account> getAccounts(
        final int accountGroupId,
        final int accountTypeId
    ) {
        List<Account> accounts = null;

        try {
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(
                Account.ACCOUNT_GROUP_ID_COLUMN_NAME,
                accountGroupId
            ).and().eq(
                Account.ACCOUNT_TYPE_ID_COLUMN_NAME,
                accountTypeId
            );
            accounts = queryBuilder.query();
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return accounts;
    }

    /**
     * Get all account types list.
     * @return account types list or null.
     */
    public List<AccountType> getAccountTypes() {
        List<AccountType> accountTypes = null;
        try {
            Dao<AccountType, ?> accountTypeDao = DaoManager.createDao(
                    Database.getDatabaseConnection(),
                    AccountType.class
            );
            accountTypes = accountTypeDao.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return accountTypes;
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
