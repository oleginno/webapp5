package com.oleginno.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Resume {

    private String fullName;

    private Objective objective;

    private Set<Achievement> achievements = new TreeSet<>();

    private Set<Qualification> qualifications = new TreeSet<>();

    private List<Employment> employments = new ArrayList<>();

    private List<Education> educations = new ArrayList<>();

    private String photo;

    private Link webSite;

    private String location;

    private Set<Contact> contacts = new TreeSet<>();


    public Resume(String name) {
        this.fullName = name;
    }

    public Resume() {
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public List<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Employment> employments) {
        this.employments = employments;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Link getWebSite() {
        return webSite;
    }

    public void setWebSite(Link webSite) {
        this.webSite = webSite;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public String toString() {
        return fullName;
    }
}
