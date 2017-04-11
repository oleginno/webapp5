package com.oleginno.webapp.interfaces;


import com.oleginno.webapp.models.Technology;

import java.util.Set;


public interface Stackable {

    Set getTechnologyStack();

    void setTechnologyStack(Set<Technology> technologyStack);
}
