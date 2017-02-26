package com.jrogalsk.requestsmanager.core.business.application;

import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionTrigger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StateTransitionJustificationVerifierTest {
    private StateTransitionJustificationVerifier stateTransitionJustificationVerifier;

    @Before
    public void setUp() {
        this.stateTransitionJustificationVerifier = new StateTransitionJustificationVerifier();
    }

    @Test
    public void deleteTriggerNeedsJustification() {
        this.verifyThatJustificationIsRequiredFor(StateTransitionTrigger.DELETE);
    }

    @Test
    public void rejectTriggerNeedsJustification() {
        this.verifyThatJustificationIsRequiredFor(StateTransitionTrigger.REJECT);
    }

    @Test
    public void trigersWhichDoNotNeedJustification() {
        this.verifyThatJustificationIsOptionalFor(StateTransitionTrigger.CREATE);
        this.verifyThatJustificationIsOptionalFor(StateTransitionTrigger.ACCEPT);
        this.verifyThatJustificationIsOptionalFor(StateTransitionTrigger.PUBLISH);
        this.verifyThatJustificationIsOptionalFor(StateTransitionTrigger.VERIFY);
    }

    private void verifyThatJustificationIsRequiredFor(StateTransitionTrigger trigger) {
        this.verifyThatValidJustification(trigger, "moo");

        this.verifyThatInvalidJustification(trigger, "");
        this.verifyThatInvalidJustification(trigger, null);
    }

    private void verifyThatJustificationIsOptionalFor(StateTransitionTrigger trigger) {
        this.verifyThatValidJustification(trigger, "moo");
        this.verifyThatValidJustification(trigger, "");
        this.verifyThatValidJustification(trigger, null);
    }

    private void verifyThatValidJustification(StateTransitionTrigger trigger, String justification) {
        assertTrue(this.stateTransitionJustificationVerifier()
                        .hasValidJustification(trigger, justification)
        );
    }

    private void verifyThatInvalidJustification(StateTransitionTrigger trigger, String justification) {
        assertFalse(this.stateTransitionJustificationVerifier()
                        .hasValidJustification(trigger, justification)
        );
    }

    private StateTransitionJustificationVerifier stateTransitionJustificationVerifier() {
        return this.stateTransitionJustificationVerifier;
    }

}
