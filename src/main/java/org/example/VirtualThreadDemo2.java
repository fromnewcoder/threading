package org.example;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();

        for(int i = 0; i< 100_000; i++){
            int index = i + 1;
            Thread start1 = new Thread(() -> {
                long r = 1;
                for (int j = 1; j <= 10; j++) {
                    r *= j;
                }
                System.out.println("Result [" + index + "]: " + r);
            });
            start1.start();
            threadList.add(start1);
        }
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}