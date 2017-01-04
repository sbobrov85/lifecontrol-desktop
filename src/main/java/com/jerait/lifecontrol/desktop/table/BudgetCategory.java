package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage budgets for category.
 */
@DatabaseTable(tableName = "budget_category")
public class BudgetCategory {
    /**
     * Link to BudgetItem.
     */
    @DatabaseField(columnName = "budget_item_id")
    private int budgetItemId;

    /**
     * Link to Category.
     */
    @DatabaseField(columnName = "category_id")
    private int categoryId;

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
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
