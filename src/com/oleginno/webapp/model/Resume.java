package com.oleginno.webapp.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


public class Resume implements Comparable<Resume> {

    private String uuid;

    private String fullName;

    private String location;

    private String homePage;

    private List<Contact> contacts = new LinkedList<>();

    private List<Section> sections = new LinkedList<>();


    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName)
                && location.equals(resume.location)
                && (homePage != null ? homePage.equals(resume.homePage) : resume.homePage == null)
                && (contacts != null ? contacts.equals(resume.contacts) : resume.contacts == null)
                && (sections != null ? sections.equals(resume.sections) : resume.sections == null);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Resume o) throws NullPointerException {
        return this.getUuid().compareTo(o.getUuid());
    }
}
