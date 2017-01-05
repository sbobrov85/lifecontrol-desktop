package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage budgets variants list.
 */
@DatabaseTable(tableName = "budget")
public class Budget {
    /**
     * Primary key.
     */
    @DatabaseField(columnName = "budget_id", generatedId = true)
    private int budgetId;

    /**
     * Budget text label.
     */
    @DatabaseField
    private String label;

    /**
     * @return the budgetId
     */
    public int getBudgetId() {
        return budgetId;
    }

    /**
     * @param budgetId the budgetId to set
     */
    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
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
