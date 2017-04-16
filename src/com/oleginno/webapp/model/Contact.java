package com.oleginno.webapp.model;


/**
 * Oleh Savych
 * 13.04.17
 */

public class Contact implements Comparable<Contact> {

    private final ContactType type;

    private final String value;


    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Contact o) {
        return this.value.compareTo(o.value);
    }
}
