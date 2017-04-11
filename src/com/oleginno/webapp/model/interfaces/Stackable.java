package com.oleginno.webapp.model.interfaces;


import com.oleginno.webapp.model.models.Technology;

import java.util.Set;


public interface Stackable {

    Set getTechnologyStack();

    void setTechnologyStack(Set<Technology> technologyStack);
}
