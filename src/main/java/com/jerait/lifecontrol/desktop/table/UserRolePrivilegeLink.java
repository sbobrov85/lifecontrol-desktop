package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage link UserRole to UserPrivilege.
 */
@DatabaseTable(tableName = "user_role_privilege_link")
public class UserRolePrivilegeLink {
    /**
     * Link to UserRole.
     */
    @DatabaseField(columnName = "user_role_id")
    private int userRoleId;

    /**
     * Link to UserPrivilege.
     */
    @DatabaseField(columnName = "user_privilege_id")
    private int userPrivilegeId;

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
     * @return the userPrivilegeId
     */
    public int getUserPrivilegeId() {
        return userPrivilegeId;
    }

    /**
     * @param userPrivilegeId the userPrivilegeId to set
     */
    public void setUserPrivilegeId(int userPrivilegeId) {
        this.userPrivilegeId = userPrivilegeId;
    }
}
