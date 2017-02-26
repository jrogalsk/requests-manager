package com.jrogalsk.requestsmanager.core.business.domain.request;

public class Request {
    private String id;
    private final String title;
    private final String content;
    private State state;

    public Request(String title, String content) {
        this.title = title;
        this.content = content;
        this.state = State.CREATED;
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
        return this.state;
    }

    public void setState(State aState) {
        this.state = aState;
    }
}
