/*
 * $Id: codetemplates.xml 562 2012-08-01 15:04:39Z fa-vieira $
 *
 * Copyright (c) Critical Software S.A., All Rights Reserved.
 * (www.criticalsoftware.com)
 *
 * This software is the proprietary information of Critical Software S.A.
 * Use is subject to license terms.
 *
 * Last changed on: $Date: 2012-08-01 16:04:39 +0100 (qua, 01 Ago 2012) $
 * Last changed by: $Author: fa-vieira $
 */
package droolscours;

import java.text.DateFormat;
import java.util.Date;

@SuppressWarnings("javadoc")
public class CashFlow {

    private Date mvtDate;
    private double amount;
    private int type;
    private long accountNo;
    public static int CREDIT = 1;
    public static int DEBIT = 2;

    public CashFlow() {
        super();
    }

    public CashFlow(final Date mvtDate, final double amount, final int type, final long accountNo) {
        super();
        this.mvtDate = mvtDate;
        this.amount = amount;
        this.type = type;
        this.accountNo = accountNo;
    }

    /**
     * @return the myDate
     */
    public Date getMvtDate() {
        return this.mvtDate;
    }

    /**
     * @param mvtDate the mvtDate to set
     */
    public void setMvtDate(final Date mvtDate) {
        this.mvtDate = mvtDate;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(final double amount) {
        this.amount = amount;
    }

    /**
     * @return the type
     */
    public int getType() {
        return this.type;
    }

    /**
     * @param type the type to set
     */
    public void setType(final int type) {
        this.type = type;
    }

    /**
     * @return the accountNo
     */
    public long getAccountNo() {
        return this.accountNo;
    }

    /**
     * @param accountNo the accountNo to set
     */
    public void setAccountNo(final long accountNo) {
        this.accountNo = accountNo;
    }

    @SuppressWarnings("javadoc")
    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("-----CashFlow-----)\n");
        buff.append("Account no=" + this.accountNo + "\n");
        if (this.mvtDate != null) {
            buff.append("Movement Date= " + DateFormat.getDateInstance().format(this.mvtDate) + "\n");
        } else {
            buff.append("No Movement date was set\n");
        }
        buff.append("Movement Amount=" + this.amount + "\n");
        buff.append("-----CashFlow end--)");
        return buff.toString();
    }

}
