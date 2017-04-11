package com.oleginno.webapp.model.models;


public class Education extends Activity {

    private String schoolTitle;

    public Education(String schoolTitle, String startDate) {
        this.schoolTitle = schoolTitle;
        this.setStart(startDate);
    }

    public Education() {}

    @Override
    public String getTitle() {
        return schoolTitle;
    }

    @Override
    public void setTitle(String title) {
        this.schoolTitle = title;
    }
}
