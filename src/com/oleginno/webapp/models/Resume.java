package com.oleginno.webapp.models;

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

    private String webSite;

    private String location;

    private Set<String> mailBox = new TreeSet<>();

    private Set<String> phones = new TreeSet<>();

    private String skype;


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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<String> getMailBox() {
        return mailBox;
    }

    public void setMailBox(Set<String> mailBox) {
        this.mailBox = mailBox;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String toString() {
        return fullName;
    }
}
