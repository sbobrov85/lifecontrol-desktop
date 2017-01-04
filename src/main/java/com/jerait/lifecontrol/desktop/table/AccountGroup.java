package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage table with accounts groups.
 */
@DatabaseTable(tableName = "account_group")
public final class AccountGroup {
    /**
     * Primary key.
     */
    @DatabaseField(columnName = "account_group_id", id = true)
    private int accountGroupId;

    /**
     * Text label for group.
     */
    @DatabaseField
    private String label;

    /**
     * @return the accountGroupId
     */
    public int getAccountGroupId() {
        return accountGroupId;
    }

    /**
     * @param accountGroupId the accountGroupId to set
     */
    public void setAccountGroupId(int accountGroupId) {
        this.accountGroupId = accountGroupId;
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
