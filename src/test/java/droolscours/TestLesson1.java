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
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import util.KnowledgeSessionHelper;
import util.OutputDisplay;

/**
 * @author mamateus
 * @version $Revision: 562 $
 */
@SuppressWarnings("restriction")
public class TestLesson1 {

    StatelessKieSession sessionStateless = null;
    KieSession sessionStateful = null;
    static KieContainer kieContainer;

    @SuppressWarnings("javadoc")
    @BeforeClass
    public static void BeforeClass() {
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }

    @SuppressWarnings("javadoc")
    @Before
    public void setUp() {
        System.out.println("----------Before----------");
    }

    @SuppressWarnings("javadoc")
    @After
    public void tearDown() {
        System.out.println("----------After----------");

    }

    @SuppressWarnings("javadoc")
    @Test
    public void testFirstOne() {
        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");
        final OutputDisplay outputDisplay = new OutputDisplay();
        this.sessionStateful.setGlobal("showResults", outputDisplay);
        final Account account = new Account();
        this.sessionStateful.insert(account);
        this.sessionStateful.fireAllRules();
    }

    @SuppressWarnings("javadoc")
    @Test
    public void testRuleOneFactWithFactAndUsageOfGlobalAndCallBack() {

        this.sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");
        this.sessionStateful.addEventListener(new RuleRuntimeEventListener() {

            @Override
            public void objectUpdated(final ObjectUpdatedEvent event) {
                System.out.println("Object was updated \n" + "new Content \n" + event.getObject().toString());
            }

            @Override
            public void objectInserted(final ObjectInsertedEvent event) {
                System.out.println("Object inserted \n" + event.getObject().toString());
            }

            @Override
            public void objectDeleted(final ObjectDeletedEvent event) {
                System.out.println("Object deleted \n" + event.getOldObject().toString());
            }
        });

        final Account account = new Account();
        account.setAccountno(10);
        final FactHandle handleAccount = this.sessionStateful.insert(account);
        account.setBalance(12.0);
        this.sessionStateful.update(handleAccount, account);
        this.sessionStateful.delete(handleAccount);
        this.sessionStateful.fireAllRules();
    }

}
