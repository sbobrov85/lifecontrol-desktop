package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 * Store log entities for any events.
 */
@DatabaseTable(tableName = "log")
public class Log {
    /**
     * Primary key.
     */
    @DatabaseField(columnName = "log_id", id = true)
    private int logId;

    /**
     * Log insert date.
     */
    @DatabaseField(columnName = "insert_date")
    private Date insertDate;

    /**
     * Link to User.
     */
    @DatabaseField(columnName = "user_id")
    private int userId;

    /**
     * Link to UserPrivilege.
     */
    @DatabaseField(columnName = "user_privilege_id")
    private int userPrivilegeId;

    /**
     * Additional comment for log event.
     */
    @DatabaseField
    private String comment;

    /**
     * @return the logId
     */
    public int getLogId() {
        return logId;
    }

    /**
     * @param logId the logId to set
     */
    public void setLogId(int logId) {
        this.logId = logId;
    }

    /**
     * @return the insertDate
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * @param insertDate the insertDate to set
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
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
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
