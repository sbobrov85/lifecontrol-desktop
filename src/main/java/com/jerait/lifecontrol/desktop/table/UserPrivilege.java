package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage user privileges.
 */
@DatabaseTable(tableName = "user_privilege")
public class UserPrivilege {
    /**
     * Primary key.
     */
    @DatabaseField(columnName = "user_privilege_id", id = true)
    private int userPrivilegeId;

    /**
     * Privilege text alias.
     */
    @DatabaseField
    private String syskey;

    /**
     * Privilege text label.
     */
    @DatabaseField
    private String label;

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

    /**
     * @return the syskey
     */
    public String getSyskey() {
        return syskey;
    }

    /**
     * @param syskey the syskey to set
     */
    public void setSyskey(String syskey) {
        this.syskey = syskey;
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
