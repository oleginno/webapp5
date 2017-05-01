package main;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            integers.add((int)(Math.random()*11));
        }

        integers
                .parallelStream()
                .filter(i -> i % 2 == 0)
                .sorted()
                .forEachOrdered(System.out::println);
    }
}
