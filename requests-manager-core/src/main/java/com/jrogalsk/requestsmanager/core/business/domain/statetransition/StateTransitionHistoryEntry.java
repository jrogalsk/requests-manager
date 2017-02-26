package com.jrogalsk.requestsmanager.core.business.domain.statetransition;

import java.util.Date;

public class StateTransitionHistoryEntry {
    private State from;
    private State to;
    private String justification;
    private Date createDate;

    public StateTransitionHistoryEntry(State from, State to, String justification) {
        this.from = from;
        this.to = to;
        this.justification = justification;
        this.createDate = new Date();
    }

    public State getFrom() {
        return this.from;
    }

    public State getTo() {
        return this.to;
    }

    public String getJustification() {
        return this.justification;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
}
