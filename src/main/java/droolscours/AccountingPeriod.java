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

import java.util.Date;

@SuppressWarnings("javadoc")
public class AccountingPeriod {

    private Date startDate;
    private Date endDate;

    public AccountingPeriod() {
    }

    public AccountingPeriod(final Date startDate, final Date endDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "AccountingPeriod [startDate=" + this.startDate + ", endDate=" + this.endDate + "]";
    }

}
