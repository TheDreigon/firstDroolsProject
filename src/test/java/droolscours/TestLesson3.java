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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import droolscours.service.CustomerService;
import util.DateHelper;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

@SuppressWarnings("javadoc")
public class TestLesson3 {

    static KieContainer kieContainer;
    StatelessKieSession sessionStateless = null;
    KieSession sessionStateful = null;

    @SuppressWarnings("javadoc")
    @BeforeClass
    public static void beforeClass() {
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }

    @SuppressWarnings("javadoc")
    @Before
    public void setUp() throws Exception {
        System.out.println("------------Before------------");
    }

    @SuppressWarnings("javadoc")
    @After
    public void tearDown() throws Exception {
        System.out.println("------------After------------");
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testInConstrait() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        final CashFlow cashFlow = new CashFlow();
        cashFlow.setType(CashFlow.CREDIT);
        this.sessionStateful.insert(cashFlow);
        this.sessionStateful.fireAllRules();
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testNestedAccessor() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        final Customer customer = new Customer();
        customer.setName("Héron");
        customer.setSurname("Nicolas");
        final PrivateAccount pAccount = new PrivateAccount();
        pAccount.setOwner(customer);
        this.sessionStateful.insert(pAccount);
        this.sessionStateful.fireAllRules();
    }

    // giving off error
    @SuppressWarnings("javadoc")
    @Test
    public void testInOrFact() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        final Customer customer = new Customer();

        customer.setCountry("GB");
        this.sessionStateful.insert(customer);
        final PrivateAccount pAccount = new PrivateAccount();
        pAccount.setOwner(customer);
        this.sessionStateful.insert(pAccount);
        this.sessionStateful.fireAllRules();
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testNotCondition() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        this.sessionStateful.fireAllRules();
    }

    // giving off error
    @SuppressWarnings("javadoc")
    @Test
    public void testExistsCondition() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        final Account pAccount = new Account();
        this.sessionStateful.insert(pAccount);
        final Customer c = new Customer();
        this.sessionStateful.insert(c);
        this.sessionStateful.fireAllRules();
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testForAll() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        final Account a = new Account();
        a.setAccountNo(1);
        a.setBalance(0);
        this.sessionStateful.insert(a);
        final CashFlow cash1 = new CashFlow();
        cash1.setAccountNo(1);

        this.sessionStateful.insert(cash1);
        final CashFlow cash2 = new CashFlow();
        cash2.setAccountNo(1);

        this.sessionStateful.insert(cash2);
        final Account a2 = new Account();
        a2.setAccountNo(2);
        a2.setBalance(0);
        this.sessionStateful.insert(a2);
        final CashFlow cash3 = new CashFlow();
        cash3.setAccountNo(2); // if here we put "cash3.setAccountNo(1);", the forAll rule will not be fired
        this.sessionStateful.insert(cash3);
        this.sessionStateful.fireAllRules();
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testFromLHS() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        this.sessionStateful.setGlobal("serviceCustomer", new CustomerService());
        final Customer c = new Customer("Héron", "Nicolas", "A");
        this.sessionStateful.insert(c);
        this.sessionStateful.fireAllRules();
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testCollecting() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        final Account a = new Account();
        a.setAccountNo(1);
        a.setBalance(0);
        this.sessionStateful.insert(a);
        this.sessionStateful.insert(new CashFlow(DateHelper.getDate("2010-01-15"), 1000, CashFlow.CREDIT, 1));
        this.sessionStateful.insert(new CashFlow(DateHelper.getDate("2010-02-15"), 500, CashFlow.DEBIT, 1));
        this.sessionStateful.insert(new CashFlow(DateHelper.getDate("2010-04-15"), 1000, CashFlow.CREDIT, 1));
        this.sessionStateful
                .insert(new AccountingPeriod(DateHelper.getDate("2010-01-01"), DateHelper.getDate("2010-31-31")));
        this.sessionStateful.fireAllRules();
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testAccumulate() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson3");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResult", display);
        this.sessionStateful.insert(new Account(1, 0));

        final FactHandle fa = this.sessionStateful
                .insert(new CashFlow(DateHelper.getDate("2010-01-15"), 1000, CashFlow.CREDIT, 1));
        this.sessionStateful.insert(new CashFlow(DateHelper.getDate("2010-02-15"), 500, CashFlow.DEBIT, 1));
        this.sessionStateful.insert(new CashFlow(DateHelper.getDate("2010-04-15"), 1000, CashFlow.CREDIT, 1));
        this.sessionStateful
                .insert(new AccountingPeriod(DateHelper.getDate("2010-01-01"), DateHelper.getDate("2010-12-31")));
        this.sessionStateful.fireAllRules();
        this.sessionStateful.delete(fa);
        this.sessionStateful.fireAllRules();
    }
}
