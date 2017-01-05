package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage list of accounts types.
 */
@DatabaseTable(tableName = "account_type")
public class AccountType {
    /**
     * Primary key.
     */
    @DatabaseField(columnName = "account_type", generatedId = true)
    private int accountType;

    /**
     * Text label for accounts type.
     */
    @DatabaseField
    private String label;

    /**
     * @return the accountType
     */
    public int getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(int accountType) {
        this.accountType = accountType;
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
