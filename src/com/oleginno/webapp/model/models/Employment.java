package com.oleginno.webapp.model.models;


public class Employment extends Activity {

    private String employmentTitle;

    public Employment(String employmentTitle) {
        this.employmentTitle = employmentTitle;
    }

    public Employment() {}

    @Override
    public String getTitle() {
        return employmentTitle;
    }

    @Override
    public void setTitle(String title) {
        this.employmentTitle = title;
    }
}
