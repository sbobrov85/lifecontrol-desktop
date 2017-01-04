package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage budgets for transfers.
 */
@DatabaseTable(tableName = "budget_transfer")
public class BudgetTransfer {
    /**
     * Link to BudgetItem.
     */
    @DatabaseField(columnName = "budget_item_id")
    private int budgetItemId;

    /**
     * Link from Account.
     */
    @DatabaseField(columnName = "from_account_id")
    private int fromAccountId;

    /**
     * Link to Account.
     */
    @DatabaseField(columnName = "to_account_id")
    private int toAccountId;

    /**
     * @return the budgetItemId
     */
    public int getBudgetItemId() {
        return budgetItemId;
    }

    /**
     * @param budgetItemId the budgetItemId to set
     */
    public void setBudgetItemId(int budgetItemId) {
        this.budgetItemId = budgetItemId;
    }

    /**
     * @return the fromAccountId
     */
    public int getFromAccountId() {
        return fromAccountId;
    }

    /**
     * @param fromAccountId the fromAccountId to set
     */
    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    /**
     * @return the toAccountId
     */
    public int getToAccountId() {
        return toAccountId;
    }

    /**
     * @param toAccountId the toAccountId to set
     */
    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }
}
