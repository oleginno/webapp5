package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.*;
import org.junit.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Oleh Savych
 * 16.05.17
 */

abstract public class CoreAbstractStorageTest {


    public static final String FILE_STORAGE = "./file_storage";

    private Resume R1, R2, R3;

    protected IStorage storage;

    @BeforeClass
    public static void beforeClass() {
        // the same as static {}
    }

    @Before
    public void before() {
        R1 = new Resume("Полное Имя1", "location1", "Location111", "WEBWEBWEB");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R2 = new Resume("Полное Имя2", "Location1", "WEBWEBWEB222");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R3 = new Resume("Полное Имя3", "llllllll", "WEBWEBWEB333");

        R1.addObjective("Objective1");
        R1.addMultiTextSection(SectionType.ACHIEVEMENT, "Achivment11", "Achivment12");
        R1.addMultiTextSection(SectionType.QUALIFICATIONS, "Java", "SQL");

        R1.addOrganizationSection(SectionType.EXPERIENCE,
                new Organization("Organization11", null,
                        new Organization.Period(LocalDate.of(2005, Month.JANUARY, 1),
                                Organization.Period.NOW, "position1", "content1"),
                        new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY,
                                "position2", "content2")));

        R1.addOrganizationSection(SectionType.EDUCATION,
                new Organization("Institute", null,
                        new Organization.Period(1996, Month.JANUARY, 2000, Month.DECEMBER,
                                "aspirant", null),
                        new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY,
                                "student", "IT facultet")),
                new Organization("Organization12", "http://Organization12.ru"));

        storage.clear();
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
    }

    @After
    public void tearDown() throws Exception {
        //storage.clear();
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void testUpdate() throws Exception {
        R2.setFullName("Updated N2");
        storage.update(R2);
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
    }

    @Test
    public void testLoad() throws Exception {
        Assert.assertEquals(R1, storage.load(R1.getUuid()));
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
        Assert.assertEquals(R3, storage.load(R3.getUuid()));
//        OrganizationSection section = (OrganizationSection) R1.getSection(SectionType.EDUCATION);
//        for (Organization organization: section.getValues()) {
//            for (Organization.Period period: organization.getPeriods()) {
//                System.out.println(period.getStartDate());
//                System.out.println(period.getEndDate());
//                System.out.println(period.getPosition());
//                System.out.println(period.getContent());
//            }
//        }
//        OrganizationSection section2 = (OrganizationSection) R1.getSection(SectionType.EXPERIENCE);
//        for (Organization organization2: section2.getValues()) {
//            for (Organization.Period period2: organization2.getPeriods()) {
//                System.out.println(period2.getStartDate());
//                System.out.println(period2.getEndDate());
//                System.out.println(period2.getPosition());
//                System.out.println(period2.getContent());
//            }
//        }
    }

    @Test(expected = WebAppException.class)
    public void testDeleteNotFound() throws Exception {
        storage.load("dummy");
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void testGetAllSorted() throws Exception {
        List<Resume> list = Arrays.asList(R1, R2, R3);
        list.sort((o1, o2) -> 0);
        //Assert.assertEquals(list, new ArrayList<>(storage.getAllSorted()));
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = WebAppException.class)
    public void testDeleteMissed() throws Exception {
        storage.delete("dummy");
    }

    @Test(expected = WebAppException.class)
    public void testSavePresented() throws Exception {
        storage.save(R1);
    }

    @Test(expected = WebAppException.class)
    public void testUpdateMissed() throws Exception {
        Resume resume = new Resume("dummy", "fullName_U1", "location_U1");
        storage.update(resume);
    }
}