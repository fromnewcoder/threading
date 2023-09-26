package org.example.threadpool;

import java.util.concurrent.*;

public class ExecutorExample2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(getRunnable("task1"));
        executorService.execute(getRunnable("task2"));
        executorService.execute(getRunnable("task3"));
        executorService.execute(getRunnable("task4"));
        executorService.execute(getRunnable("task5"));
        executorService.shutdown();
    }
    public static Runnable getRunnable(String task){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : " + task);
            }
        };
    }
}
