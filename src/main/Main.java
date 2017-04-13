package main;


import com.oleginno.webapp.model.Achievement;
import com.oleginno.webapp.model.Resume;


public class Main {

    public static void main(String[] args) {
        Resume mine = new Resume("Oleh Savych");
        mine.getAchievements().add(new Achievement("first"));
        System.out.println(mine);
    }
}
