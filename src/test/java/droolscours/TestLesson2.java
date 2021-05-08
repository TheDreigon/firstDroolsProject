package droolscours;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import util.DateHelper;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

@SuppressWarnings("restriction")
public class TestLesson2 {

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
    public void testTwoFacts() {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson2");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResults", display);
        final Account a = new Account();
        a.setAccountNo(1);
        a.setBalance(0);
        this.sessionStateful.insert(a);
        final CashFlow cash1 = new CashFlow();
        cash1.setAccountNo(1);
        cash1.setAmount(1000);
        cash1.setType(CashFlow.CREDIT);
        this.sessionStateful.insert(cash1);
        this.sessionStateful.fireAllRules();
        Assert.assertEquals(a.getBalance(), 1000, 0);
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testTwofactsTwocashFlowMovement() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson2");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResults", display);
        final Account a = new Account();
        a.setAccountNo(1);
        a.setBalance(0);
        this.sessionStateful.insert(a);
        final CashFlow cash1 = new CashFlow();
        cash1.setAccountNo(1);
        cash1.setAmount(1000);
        cash1.setMvtDate(DateHelper.getDate("2010-01-15"));
        cash1.setType(CashFlow.CREDIT);
        this.sessionStateful.insert(cash1);
        final CashFlow cash2 = new CashFlow();
        cash2.setAccountNo(2);
        cash2.setAmount(1000);
        cash2.setMvtDate(DateHelper.getDate("2010-01-15"));
        cash2.setType(CashFlow.CREDIT);
        this.sessionStateful.insert(cash2);
        this.sessionStateful.fireAllRules();
        Assert.assertEquals(a.getBalance(), 1000, 0);
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testcalculateBalance() throws Exception {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSessionWithCallback(kieContainer,
                "ksession-lesson2");
        final OutputDisplay display = new OutputDisplay();
        this.sessionStateful.setGlobal("showResults", display);
        final Account a = new Account();
        a.setAccountNo(1);
        a.setBalance(0);
        this.sessionStateful.insert(a);
        final CashFlow cash1 = new CashFlow();
        cash1.setAccountNo(1);
        cash1.setAmount(1000);
        cash1.setMvtDate(DateHelper.getDate("2016-01-15"));
        cash1.setType(CashFlow.CREDIT);
        this.sessionStateful.insert(cash1);
        final CashFlow cash2 = new CashFlow();
        cash2.setAccountNo(1);
        cash2.setAmount(500);
        cash2.setMvtDate(DateHelper.getDate("2016-02-15"));
        cash2.setType(CashFlow.DEBIT);
        this.sessionStateful.insert(cash2);
        final CashFlow cash3 = new CashFlow();
        cash3.setAccountNo(1);
        cash3.setAmount(1000);
        cash3.setMvtDate(DateHelper.getDate("2016-04-15"));
        cash3.setType(CashFlow.CREDIT);
        this.sessionStateful.insert(cash3);
        final AccountingPeriod period = new AccountingPeriod();
        period.setStartDate(DateHelper.getDate("2016-01-01"));
        period.setEndDate(DateHelper.getDate("2016-03-31"));
        this.sessionStateful.insert(period);
        this.sessionStateful.fireAllRules();
        Assert.assertTrue(a.getBalance() == 500);
    }
}