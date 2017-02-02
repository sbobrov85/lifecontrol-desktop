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
    @DatabaseField(generatedId = true, columnName = "account_id")
    private int accountId;

    /**
     * Text label for account.
     */
    @DatabaseField
    private String label;

    /**
     * Contained account group id column name constant.
     */
    public static final
        String ACCOUNT_GROUP_ID_COLUMN_NAME = "account_group_id";

    /**
     * Link to AccountGroup.
     */
    @DatabaseField(columnName = ACCOUNT_GROUP_ID_COLUMN_NAME)
    private int $accountGroupId;

    /**
     * Contained account type id column name constant.
     */
    public static final
        String ACCOUNT_TYPE_ID_COLUMN_NAME = "account_type_id";

    /**
     * Link to AccountType.
     */
    @DatabaseField(columnName = ACCOUNT_TYPE_ID_COLUMN_NAME)
    private int $accountTypeId;

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

    /**
     * @return the $accountGroupId
     */
    public int get$accountGroupId() {
        return $accountGroupId;
    }

    /**
     * @param $accountGroupId the $accountGroupId to set
     */
    public void set$accountGroupId(int $accountGroupId) {
        this.$accountGroupId = $accountGroupId;
    }

    /**
     * @return the $accountTypeId
     */
    public int get$accountTypeId() {
        return $accountTypeId;
    }

    /**
     * @param $accountTypeId the $accountTypeId to set
     */
    public void set$accountTypeId(int $accountTypeId) {
        this.$accountTypeId = $accountTypeId;
    }
}
