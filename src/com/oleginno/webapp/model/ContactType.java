package com.oleginno.webapp.model;

import java.io.Serializable;

/**
 * Oleh Savych
 * 13.04.17
 */

public enum ContactType implements Serializable {

    PHONE("Тел."),
    MOBILE("Мобильный"),
    HOME_PHONE("Домашний тел."),
    SKYPE("Skype "),
    MAIL("Почта"),
    ICQ("ICQ");

    static final long serialVersionUID = 1L;

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
