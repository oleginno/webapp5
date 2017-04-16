package main;

import com.oleginno.webapp.model.Link;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        Link l = new Link("Oleh", null);
        System.out.println(Arrays.toString(l.getClass().getDeclaredFields()));
    }
}
