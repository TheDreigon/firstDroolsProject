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

@SuppressWarnings("javadoc")
public class Account {

    private long accountNo;
    private double balance;

    public Account() {
        super();
    }

    /**
     * @param long accountNo
     * @param double balance
     */
    public Account(final long accountNo, final double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    /**
     * @return the accountno
     */
    public long getAccountno() {
        return this.accountNo;
    }

    /**
     * @param accountno the accountno to set
     */
    public void setAccountno(final long accountno) {
        this.accountNo = accountno;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(final double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [accountNo=" + this.accountNo + ", balance=" + this.balance + "]";
    }

    /**
     * @return the accountNo
     */
    public long getAccountNo() {
        return this.accountNo;
    }

    /**
     * @param i
     * @param set the accountNo
     */
    public void setAccountNo(final long i) {
        this.accountNo = i;
    }

}
