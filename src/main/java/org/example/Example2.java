package org.example;

public class Example2 {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for(int i = 0 ; i < 5;i++){
                System.out.println("Hello world!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        thread.join();
        //Thread.sleep(3100);


    }
}