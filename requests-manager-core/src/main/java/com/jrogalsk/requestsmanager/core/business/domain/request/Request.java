package com.jrogalsk.requestsmanager.core.business.domain.request;

import com.jrogalsk.requestsmanager.core.business.domain.statetransition.State;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionHistoryEntry;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private String id;
    private final String title;
    private final String content;
    private List<StateTransitionHistoryEntry> stateTransitionHistory = new ArrayList<>();

    public Request(String title, String content) {
        this.title = title;
        this.content = content;
        this.addStateTransitionHistoryEntry(new StateTransitionHistoryEntry(null, State.CREATED, ""));
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public State getState() {
        return this.latestStatusChangeHistoryEntry().getTo();
    }

    private StateTransitionHistoryEntry latestStatusChangeHistoryEntry() {
        return this.getStateTransitionHistory()
                .get(getStateTransitionHistory().size() - 1);
    }

    public String getStatusChangeJustification() {
        return this.latestStatusChangeHistoryEntry().getJustification();
    }

    public void addStateTransitionHistoryEntry(StateTransitionHistoryEntry newEntry) {
        this.getStateTransitionHistory()
                .add(newEntry);
    }

    public List<StateTransitionHistoryEntry> getStateTransitionHistory() {
        return this.stateTransitionHistory;
    }
}
