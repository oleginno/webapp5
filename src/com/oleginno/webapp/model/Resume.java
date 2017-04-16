package com.oleginno.webapp.model;

import java.util.List;


public class Resume {

    private String fullName;

    private String location;

    private String homePage;

    private List<Contact> contacts;

    private List<Section> sections;

    public String toString() {
        return fullName;
    }
}
