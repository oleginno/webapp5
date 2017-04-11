package com.oleginno.webapp.model.models;


public class Objective extends Experience {

    private String title;

    public Objective(String title) {
        this.title = title;
    }

    public Objective() {}

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
