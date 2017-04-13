package com.oleginno.webapp.model;

import java.util.Set;
import java.util.TreeSet;

/**
 * Oleh Savych
 * 13.04.17
 */

public class Contact implements Comparable<Contact> {

    private String mailBox;

    private Set<String> phones = new TreeSet<>();

    private String skype;


    public Contact(String phone, String mail) {
        getPhones().add(phone);
        this.mailBox = mail;
    }

    public Contact() {
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public int compareTo(Contact o) {
        return this.mailBox.compareTo(o.mailBox);
    }
}
