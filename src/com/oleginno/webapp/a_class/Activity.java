package com.oleginno.webapp.a_class;


import com.oleginno.webapp.interfaces.Dated;
import com.oleginno.webapp.interfaces.Titled;


public abstract class Activity implements Dated, Titled {

    private String start;

    private  String finish;

    @Override
    public String getStart() {
        return start;
    }

    @Override
    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public String getFinish() {
        return finish;
    }

    @Override
    public void setFinish(String finish) {
        this.finish = finish;
    }
}
