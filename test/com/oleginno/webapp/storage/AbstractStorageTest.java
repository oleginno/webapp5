package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Oleh Savych
 * 17.04.17
 */

public class AbstractStorageTest {

    private static Resume r1, r2, r3;

    static {
        r1 = new Resume("qqqwwwwweeee", "Oleh Savych", "Ivano-Frankivsk");
        r1.addContact(ContactType.MOBILE, "349032112");

        r2 = new Resume("qqqwwwwweeee222", "Oleh Savych 2", "Kolomyia");
        r2.addContact(ContactType.MAIL, "vert@ya.ru");

        r3 = new Resume("qqqwwwwweeee333", "Oleh Savych 3", "Sniatyn");
        r3.addContact(ContactType.HOME_PHONE, "2190211");
    }

    private ArrayStorage arrayStorage = new ArrayStorage();

    private MapStorage mapStorage = new MapStorage();

    @Before
    public void before() {
        arrayStorage.clear();
        arrayStorage.save(r1);
        arrayStorage.save(r2);
        arrayStorage.save(r3);

        mapStorage.clear();
        mapStorage.save(r1);
        mapStorage.save(r2);
        mapStorage.save(r3);
    }

    @Test
    public void search() throws Exception {
        System.out.println(mapStorage.exist("gh"));
    }

    @Test
    public void clear() throws Exception {
        arrayStorage.clear();
        Assert.assertEquals(0, arrayStorage.size());

        mapStorage.clear();
        Assert.assertEquals(0, mapStorage.size());
    }

    @Test
    public void save() throws Exception {
        arrayStorage.save(new Resume("Oleh Savych 4", "None"));
        for (Resume item: arrayStorage.getArray()) {
            if (item != null) {
                System.out.print(item.getFullName() + " ");
            } else {
                break;
            }
        }
        Assert.assertEquals(4, arrayStorage.size());

        mapStorage.save(new Resume("Oleh Savych 4", "None"));
        Assert.assertEquals(4, mapStorage.size());
    }

    @Test
    public void update() throws Exception {
        arrayStorage.update(new Resume(r3.getUuid(), "Innuendo", "Earth"));
        System.out.println(arrayStorage.getAllSorted().toString());

        mapStorage.update(new Resume(r3.getUuid(), "Innuendo", "Earth"));
        System.out.println(mapStorage.getAllSorted().toString());
    }

    @Test
    public void load() throws Exception {
        System.out.println(arrayStorage.load(r2.getUuid()));
        Assert.assertEquals("Kolomyia", arrayStorage.load(r2.getUuid()).getLocation());

        System.out.println(mapStorage.load(r2.getUuid()));
        Assert.assertEquals("Kolomyia", mapStorage.load(r2.getUuid()).getLocation());
    }

    @Test
    public void delete() throws Exception {
        arrayStorage.delete(r1.getUuid());
        for (Resume item: arrayStorage.getArray()) {
            System.out.print(item);
        }
        Assert.assertEquals(2, arrayStorage.size());

        System.out.println();

        mapStorage.delete(r1.getUuid());
        for (Resume item: mapStorage.getAllSorted()) {
            System.out.print(item);
        }
        Assert.assertEquals(2, mapStorage.size());
    }

    @Test
    public void getAllSorted() throws Exception {
        for (Resume item: arrayStorage.getAllSorted()) {
            System.out.print(item.getFullName() + " ");
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, arrayStorage.size());

        Assert.assertEquals(3, mapStorage.size());

        mapStorage.printCurrentMap();

        System.out.println(Arrays.toString(arrayStorage.getArray()));
    }
}