package com.oleginno.webapp.model.models;


import com.oleginno.webapp.model.interfaces.Dated;
import com.oleginno.webapp.model.interfaces.Descripted;
import com.oleginno.webapp.model.interfaces.Stackable;
import com.oleginno.webapp.model.interfaces.Titled;

import java.util.Set;
import java.util.TreeSet;


public abstract class Experience implements Titled, Dated, Descripted, Stackable {

    private String description;

    private Set technologyStack = new TreeSet<>();

    private String start;

    private  String finish;

    @Override
    public String getStart() {
        return start;
    }

    @Override
    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public String getFinish() {
        return finish;
    }

    @Override
    public void setFinish(String finish) {
        this.finish = finish;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Set getTechnologyStack() {
        return technologyStack;
    }

    @Override
    public void setTechnologyStack(Set<Technology> technologyStack) {
        this.technologyStack = technologyStack;
    }
}
