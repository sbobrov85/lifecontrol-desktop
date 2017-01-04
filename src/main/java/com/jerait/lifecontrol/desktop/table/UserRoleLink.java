package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage links User to UserRole
 */
@DatabaseTable(tableName = "user_role_link")
public class UserRoleLink {
    /**
     * Link to UserRole.
     */
    @DatabaseField(columnName = "user_role_id")
    private int userRoleId;

    /**
     * Link to User.
     */
    @DatabaseField(columnName = "user_id")
    private int userId;

    /**
     * @return the userRoleId
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
