package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable1 = () -> {
            for(int i = 0 ; i < 5;i++){
                System.out.println("Hello1!");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        };
        Thread start = Thread.ofVirtual().start(runnable1);

        Runnable runnable2 = () -> {
            for(int i = 0 ; i < 5;i++){
                System.out.println("Hello2!");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            try {
                start.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };


        Thread unstarted = Thread.ofVirtual().unstarted(runnable2);
        unstarted.start();
        unstarted.join();
        //TimeUnit.SECONDS.sleep(1);

        List<Thread> threadList = new ArrayList<>();

        for(int i = 0; i< 100_000; i++){
            int index = i + 1;
            Thread start1 = Thread.ofVirtual().start(() -> {
                long r = 1;
                for (int j = 1; j <= 10; j++) {
                    r *= j;
                }
                System.out.println("Result [" + index + "]: " + r);
            });
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