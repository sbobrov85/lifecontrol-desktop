package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage database account table.
 */
@DatabaseTable(tableName = "account")
public final class Account {
    /**
     * Primary key.
     */
    @DatabaseField(id = true, columnName = "account_id")
    private int accountId;

    /**
     * Text label for account.
     */
    @DatabaseField
    private String label;

    /**
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
