package com.oleginno.webapp.model.models;


public class Qualification extends Experience implements Comparable<Qualification> {

    private String qualificationTitle;

    public Qualification(String title) {
        this.qualificationTitle = title;
    }

    public Qualification() {}

    @Override
    public String getTitle() {
        return qualificationTitle;
    }

    @Override
    public void setTitle(String title) {
        this.qualificationTitle = title;
    }

    @Override
    public int compareTo(Qualification o) {
        int temp = this.qualificationTitle.compareTo(o.qualificationTitle);
        if (temp == 0 && this.getDescription() != null && o.getDescription() != null) {
            return this.getDescription().compareTo(o.getDescription());
        } else {
            return temp;
        }
    }
}
