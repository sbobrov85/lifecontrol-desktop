package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 * Manage list of payments.
 */
@DatabaseTable(tableName = "payment")
public class Payment {
    /**
     * Constant for default status field value.
     */
    final public int CATEGORY_STATUS_NOT_CHECKED = 0;

    /**
     * Constant for status field.
     */
    final public int CATEGORY_STATUS_CHECKED = 1;

    /**
     * Primary key.
     */
    @DatabaseField(columnName = "payment_id", id = true)
    private int paymentId;

    /**
     * Payment date.
     */
    @DatabaseField
    private Date date;

    /**
     * Payment status.
     * @see class constants.
     */
    @DatabaseField
    private int status;

    /**
     * Link to Account.
     */
    @DatabaseField(columnName = "account_id")
    private int accountId;

    /**
     * Payment comment.
     */
    @DatabaseField
    private String comment;

    /**
     * @return the paymentId
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

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
