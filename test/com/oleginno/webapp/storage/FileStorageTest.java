package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Resume;
import com.oleginno.webapp.model.SectionType;
import com.oleginno.webapp.model.TextSection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Oleh Savych
 * 09.05.17
 */

public class FileStorageTest {

    private static Resume f1, f2, f3, f4;

    private FileStorage fileStorage;

    @Before
    public void setUp() throws Exception {
        f1 = new Resume("Oleh Savych", "Ivano-Frankivsk");
        f1.addContact(ContactType.PHONE, "12345678910");
        f1.addMultiTextSection(SectionType.EDUCATION, "Section about education");

        f2 = new Resume("Ivan Savych", "Kyiv");
        f2.addContact(ContactType.MAIL, "johhny@ya.ru");
        f2.addSection(SectionType.ACHIEVEMENT, new TextSection("Certified Java 6 Programmer"));

        f3 = new Resume("Vasyl Savych", "Sniatyn");
        f3.addContact(ContactType.MOBILE, "545533211212");

        f4 = new Resume("Anonimus", "earth");

        //fileStorage = new SerializeFileStorage(FileStorage.DIR_PATH);

        fileStorage.save(f1);
        fileStorage.save(f2);
        fileStorage.save(f3);
    }

    @After
    public void tearDown() throws Exception {
        fileStorage.clear();
    }

    @Test
    public void exist() throws Exception {
        System.out.println(fileStorage.exist(new File("./file_storage/test")));
    }

    @Test
    public void getContext() throws Exception {
        System.out.println(fileStorage.getContext(f2.getUuid()).getAbsolutePath());
    }

    @Test
    public void doClear() throws Exception {
        fileStorage.clear();
        Assert.assertEquals(0, fileStorage.size());
    }

    @Test
    public void doSave() throws Exception {
        fileStorage.save(f4);
        Assert.assertEquals(4, fileStorage.size());
    }

    @Test
    public void doUpdate() throws Exception {
        fileStorage.doUpdate(new File(FileStorage.DIR_PATH, f3.getUuid()), f4);
        fileStorage.getAllSorted().forEach(System.out::println);
    }

    @Test
    public void doLoad() throws Exception {
        System.out.println(fileStorage.load(f2.getUuid()).getSection(SectionType.ACHIEVEMENT));
    }

    @Test
    public void doDelete() throws Exception {
        fileStorage.delete(f1.getUuid());
        fileStorage.getAllSorted().forEach(System.out::println);
    }

    @Test
    public void size() throws Exception {
        System.out.println(fileStorage.size());
    }

    @Test
    public void doGetAllSorted() throws Exception {
        fileStorage.getAllSorted().forEach(System.out::println);
    }
}