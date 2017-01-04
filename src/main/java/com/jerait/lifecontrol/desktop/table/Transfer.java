package com.jerait.lifecontrol.desktop.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Manage transfers list.
 */
@DatabaseTable(tableName = "transfer")
public class Transfer {
    /**
     * Link to Payment.
     */
    @DatabaseField(columnName = "payment_id")
    private int paymentId;

    /**
     * Link from Account.
     */
    @DatabaseField(columnName = "from_account_id")
    private int fromAccountId;

    /**
     * Link to Account;
     */
    @DatabaseField(columnName = "to_account_id")
    private int toAccountId;

    /**
     * Transfer total summ.
     */
    @DatabaseField
    private int summ;

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

    /**
     * @return the summ
     */
    public int getSumm() {
        return summ;
    }

    /**
     * @param summ the summ to set
     */
    public void setSumm(int summ) {
        this.summ = summ;
    }
}
