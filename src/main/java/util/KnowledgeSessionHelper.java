package util;

import org.drools.core.event.AgendaGroupPoppedEvent;
import org.drools.core.event.RuleFlowGroupActivatedEvent;
import org.drools.core.event.RuleFlowGroupDeactivatedEvent;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

@SuppressWarnings("javadoc")
public class KnowledgeSessionHelper {

    public static KieContainer createRuleBase() {

        final KieServices kieServices = KieServices.Factory.get();
        final KieContainer kieContainer = kieServices.getKieClasspathContainer();
        return kieContainer;
    }

    public static StatelessKieSession getStatelessKnowledgeSession(
            final KieContainer kieContainer,
            final String sessionName) {

        final StatelessKieSession kieSession = kieContainer.newStatelessKieSession(sessionName);
        return kieSession;
    }

    public static KieSession getStatefulKnowledgeSession(final KieContainer kieContainer, final String sessionName) {

        final KieSession kieSession = kieContainer.newKieSession(sessionName);
        return kieSession;
    }

    public static KieSession getStatefulKnowledgeSessionWithCallback(
            final KieContainer kieContainer,
            final String sessionName) {

        final KieSession session = getStatefulKnowledgeSession(kieContainer, sessionName);

        session.addEventListener(new RuleRuntimeEventListener() {

            @Override
            public void objectInserted(final ObjectInsertedEvent event) {
                System.out.println("Object inserted \n" + event.getObject().toString());
            }

            @Override
            public void objectUpdated(final ObjectUpdatedEvent event) {
                System.out.println("Object was updated \n" + "new Content \n" + event.getObject().toString());
            }

            @Override
            public void objectDeleted(final ObjectDeletedEvent event) {
                System.out.println("Object retracted \n" + event.getOldObject().toString());
            }
        });

        session.addEventListener(new AgendaEventListener() {

            @Override
            public void matchCreated(final MatchCreatedEvent event) {
                System.out.println("The rule " + event.getMatch().getRule().getName() + " can be fired in agenda");
            }

            @Override
            public void matchCancelled(final MatchCancelledEvent event) {
                System.out.println("The rule " + event.getMatch().getRule().getName() + " cannot b in agenda");
            }

            @Override
            public void beforeMatchFired(final BeforeMatchFiredEvent event) {
                System.out.println("The rule " + event.getMatch().getRule().getName() + " will be fired");
            }

            @Override
            public void afterMatchFired(final AfterMatchFiredEvent event) {
                System.out.println("The rule " + event.getMatch().getRule().getName() + " has be fired");
            }

            public void agendaGroupPopped(final AgendaGroupPoppedEvent event) {
            }

            @Override
            public void agendaGroupPushed(final AgendaGroupPushedEvent event) {
            }

            public void beforeRuleFlowGroupActivated(final RuleFlowGroupActivatedEvent event) {
            }

            public void afterRuleFlowGroupActivated(final RuleFlowGroupActivatedEvent event) {
            }

            public void beforeRuleFlowGroupDeactivated(final RuleFlowGroupDeactivatedEvent event) {
            }

            public void afterRuleFlowGroupDeactivated(final RuleFlowGroupDeactivatedEvent event) {
            }

            @Override
            public void afterRuleFlowGroupActivated(final org.kie.api.event.rule.RuleFlowGroupActivatedEvent arg0) {
            }

            @Override
            public void afterRuleFlowGroupDeactivated(final org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent arg0) {
            }

            @Override
            public void agendaGroupPopped(final org.kie.api.event.rule.AgendaGroupPoppedEvent arg0) {
            }

            @Override
            public void beforeRuleFlowGroupActivated(final org.kie.api.event.rule.RuleFlowGroupActivatedEvent arg0) {
            }

            @Override
            public void beforeRuleFlowGroupDeactivated(
                    final org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent arg0) {
            }
        });

        return session;
    }
}
