package main;


public class Main {

    private static int sum;
    public static void main(String[] args) {
        for(int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (Main.class) {
                    sum++;
                }
            }).start();
        }
        System.out.println(sum);
    }
}