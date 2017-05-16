package com.oleginno.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

    static final long serialVersionUID = 1L;

    private String uuid;

    private String fullName;

    private String location;

    private String homePage;

    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);


    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
            this.uuid = uuid;
            this.fullName = fullName;
            this.location = location;
    }

    private Resume() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    public void addObjective(String value) {
        addSection(SectionType.OBJECTIVE, new TextSection(value));
    }

    public void addMultiTextSection(SectionType type, String... values) {
        addSection(type, new MultiTextSection(values));
    }

    public void addOrganizationSection(SectionType type, Organization... organizations) {
        addSection(type, new OrganizationSection(organizations));
    }


    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
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

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public int compareTo(Resume o) {
        return this.getUuid().compareTo(o.getUuid());
    }
}