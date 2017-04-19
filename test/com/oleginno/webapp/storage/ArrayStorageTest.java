package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Contact;
import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Oleh Savych
 * 17.04.17
 */

public class ArrayStorageTest {

    private static Resume r1, r2, r3;

    static {
        r1 = new Resume("Oleh Savych", "Ivano-Frankivsk");
        r1.addContact(new Contact(ContactType.MOBILE, "349032112"));

        r2 = new Resume("Oleh Savych 2", "Kolomyia");
        r2.addContact(new Contact(ContactType.MAIL, "vert@ya.ru"));

        r3 = new Resume("Oleh Savych 3", "Sniatyn");
        r3.addContact(new Contact(ContactType.HOME_PHONE, "2190211"));
    }

    private ArrayStorage arrayStorage = new ArrayStorage();

    @Before
    public void before() {
        arrayStorage.clear();
        arrayStorage.save(r1);
        arrayStorage.save(r2);
        arrayStorage.save(r3);
    }

    @Test
    public void clear() throws Exception {
        //arrayStorage.clear();
        Assert.assertEquals(3, arrayStorage.aliveInstancesCount());
    }

    @Test
    public void save() throws Exception {
        //arrayStorage.save(new Resume("Oleh Savych 4", "None"));
        Assert.assertEquals(3, arrayStorage.aliveInstancesCount());
    }

    @Test
    public void update() throws Exception {
        //arrayStorage.update(new Resume(r3.getUuid(), "Innuendo", "Earth"));
        System.out.println(arrayStorage.getAllSorted().toString());
    }

    @Test
    public void load() throws Exception {
        //System.out.println(arrayStorage.load(r2.getUuid()));
        Assert.assertEquals("Kolomyia", arrayStorage.load(r2.getUuid()).getLocation());
    }

    @Test
    public void delete() throws Exception {
        arrayStorage.delete(r1.getUuid());
        for (Resume item: arrayStorage.getAllSorted()) {
            System.out.print(item.getFullName() + " ");
        }
        Assert.assertEquals(2, arrayStorage.aliveInstancesCount());
    }

    @Test
    public void getAllSorted() throws Exception {
        for (Resume item: arrayStorage.getAllSorted()) {
            System.out.print(item.getFullName() + " ");
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(100, arrayStorage.size());
    }
}