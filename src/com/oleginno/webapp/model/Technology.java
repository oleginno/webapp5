package com.oleginno.webapp.model;


import com.oleginno.webapp.interfaces.Titled;


public class Technology implements Titled, Comparable<Technology> {

    private String title;

    private String branch;

    public Technology(String title, String branch) {
        this.title = title;
        this.branch = branch;
    }

    public Technology() {}

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public int compareTo(Technology o) {
        int temp = this.title.compareTo(o.title);
        if (temp == 0) {
            return branch.compareTo(o.branch);
        } else {
            return temp;
        }
    }
}
