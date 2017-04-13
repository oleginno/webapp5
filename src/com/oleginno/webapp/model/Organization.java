package com.oleginno.webapp.model;

import com.oleginno.webapp.interfaces.Descripted;
import com.oleginno.webapp.interfaces.Titled;

/**
 * Oleh Savych
 * 13.04.17
 */

public class Organization implements Descripted, Titled {

    private String title;

    private String description;

    private Link webSite;

    private Contact contact;

    private String address;


    public Organization(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Organization() {
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Link getWebSite() {
        return webSite;
    }

    public void setWebSite(Link webSite) {
        this.webSite = webSite;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
