package main;


import com.oleginno.webapp.models.Achievement;
import com.oleginno.webapp.models.Resume;


public class Main {

    public static void main(String[] args) {
        Resume mine = new Resume("Oleh", "Savych");
        mine.getAchievements().add(new Achievement("first"));
        System.out.println(mine);
    }
}
