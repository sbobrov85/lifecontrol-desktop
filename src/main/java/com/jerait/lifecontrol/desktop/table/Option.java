package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage options table from database.
 */
@DatabaseTable(tableName = "option")
public final class Option {
    /**
     * Unique key.
     */
    @DatabaseField(columnName = "option_id", unique = true)
    private String optionId;

    /**
     * Contains value for optionId.
     */
    @DatabaseField(columnName = "value")
    private String value;

    /**
     * Get value of optionId field.
     * @return value of optionsId field.
     */
    public String getOptionId() {
        return optionId;
    }

    /**
     * Get value of value field.
     * @return value of value field.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set value of optionId field.
     * @param newOptionId new value of optionId field.
     */
    public void setOptionId(final String newOptionId) {
        this.optionId = newOptionId;
    }

    /**
     * Set value of value field.
     * @param newValue new value of value field.
     */
    public void setValue(final String newValue) {
        this.value = newValue;
    }
}
