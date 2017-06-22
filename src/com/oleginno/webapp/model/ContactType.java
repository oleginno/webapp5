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

    SKYPE("Skype"){
        @Override
        public String toHtml(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },

    MAIL("Почта") {
        @Override
        public String toHtml(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },

    ICQ("ICQ");

    static final long serialVersionUID = 1L;

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toHtml(String value) {
        return title + ": " + value;
    }
}
