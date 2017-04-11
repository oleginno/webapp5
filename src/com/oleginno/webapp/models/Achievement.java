package com.oleginno.webapp.models;


public class Achievement extends Experience implements Comparable<Achievement> {

    private String achievementTitle;

    public Achievement(String description, String title) {
        this.setDescription(description);
        this.achievementTitle = title;
    }

    public Achievement() {}

    public Achievement(String achievementTitle) {
        this.achievementTitle = achievementTitle;
    }

    @Override
    public String getTitle() {
        return achievementTitle;
    }

    @Override
    public void setTitle(String title) {
        this.achievementTitle = title;
    }

    @Override
    public int compareTo(Achievement o) {
        int temp = this.achievementTitle.compareTo(o.achievementTitle);
        if (temp == 0 && this.getDescription() != null && o.getDescription() != null) {
            return this.getDescription().compareTo(o.getDescription());
        } else {
            return temp;
        }
    }
}
