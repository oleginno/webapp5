package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Contact;
import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Oleh Savych
 * 25.04.17
 */
public class MapStorageTest {

    private static Resume m1, m2, m3;

    static {
        m1 = new Resume("Oleh Map", "Ivano-Frankivsk");
        m1.addContact(new Contact(ContactType.MOBILE, "349032112"));

        m2 = new Resume("Oleh Map 2", "Kolomyia");
        m2.addContact(new Contact(ContactType.MAIL, "vert@ya.ru"));

        m3 = new Resume("Oleh Map 3", "Sniatyn");
        m3.addContact(new Contact(ContactType.HOME_PHONE, "2190211"));
    }

    private MapStorage mapStorage = new MapStorage();

    @Before
    public void before() {
        mapStorage.clear();
        mapStorage.save(m1);
        mapStorage.save(m2);
        mapStorage.save(m3);
    }

    @Test
    public void search() throws Exception {
        System.out.println(mapStorage.search(new Resume("ghj", "", "")));
    }

    @Test
    public void clear() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void load() throws Exception {

    }

    @Test
    public void doDelete() throws Exception {
    }

    @Test
    public void getAllSorted() throws Exception {
    }

    @Test
    public void size() throws Exception {
    }

}