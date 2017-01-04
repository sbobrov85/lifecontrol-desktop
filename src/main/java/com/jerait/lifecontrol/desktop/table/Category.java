package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage categories list.
 */
@DatabaseTable(tableName = "category")
public class Category {
    /**
     * Category type constant for expense.
     */
    final public int CATEGORY_TYPE_EXPENSE = 0;

    /**
     * Category type constant for income.
     */
    final public int CATEGORY_TYPE_INCOME = 1;

    /**
     * Primary key.
     */
    @DatabaseField(columnName = "category_id")
    private int categoryId;

    /**
     * Link to parent category_id.
     */
    @DatabaseField(columnName = "parent_id")
    private int parentId;

    /**
     * Text label for category.
     */
    @DatabaseField
    private String label;

    /**
     * Link to category type.
     * @see class constants.
     */
    @DatabaseField(columnName = "category_type")
    private int categoryType;

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

    /**
     * @return the parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
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
     * @return the categoryType
     */
    public int getCategoryType() {
        return categoryType;
    }

    /**
     * @param categoryType the categoryType to set
     */
    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }
}
