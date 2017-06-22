package com.oleginno.webapp.web;

import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Organization;
import com.oleginno.webapp.model.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * User: gkislin
 * Date: 06.03.14
 */

public class HtmlUtil {

    //public static final String EMPTY_TD = "<img src='img./s.gif'>";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static String mask(String str) {
        return (str == null || str.isEmpty()) ? "&nbsp;" : str;
    }

    public static String getContact(Resume r, ContactType type) {
        String contact = r.getContact(type);
        return contact == null ? "&nbsp;" : type.toHtml(contact);
    }

    public static String format(LocalDate date) {
        return date.equals(Organization.Period.NOW) ? "Today " : date.format(DATE_FORMATTER);
    }

    public static String textArea(String name, List<String> list) {
        return String.format("<textarea name=%s cols=75 rows=5>%s</textarea>",
                name, String.join("\n", list));
    }

    public static String input(String name, String value) {
        return String.format("<input type='text' name='%s' size=75 value='%s'>", name, value);
    }
}
