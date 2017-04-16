package com.oleginno.webapp.model;

/**
 * Oleh Savych
 * 15.04.17
 */

public enum SectionType {
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация "),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
