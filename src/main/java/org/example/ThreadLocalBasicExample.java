package org.example;

import java.util.concurrent.TimeUnit;

public class ThreadLocalBasicExample {
    public static void main(String[] args) throws InterruptedException {

        ThreadLocal<String> myThreadLocal = new ThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            myThreadLocal.set("thread 1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(myThreadLocal.get());
        });

        Thread thread2 = new Thread(() -> {
            myThreadLocal.set("thread 2");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(myThreadLocal.get());
        });
        thread1.start();
        thread2.start();

    }
}