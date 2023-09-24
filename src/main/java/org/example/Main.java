package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
          while (true) {
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
        //thread.setDaemon(true);
        thread.start();
        Thread.sleep(3100);
        thread.interrupt();

    }
}