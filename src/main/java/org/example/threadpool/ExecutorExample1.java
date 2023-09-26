package org.example.threadpool;

import java.util.concurrent.*;

public class ExecutorExample1 {
    public static void main(String[] args) {
//        IllegalArgumentException – if one of the following holds:
//        corePoolSize < 0
//        keepAliveTime < 0
//        maximumPoolSize <= 0
//        maximumPoolSize < corePoolSize
//        NullPointerException – if workQueue is null
        int corePoolSize = 10;
        int maximumPoolSize = 20;
        int keepAliveTime = 3000;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        try (ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, blockingQueue)) {
            executorService.execute(getRunnable("task1"));
            executorService.execute(getRunnable("task2"));
            executorService.execute(getRunnable("task3"));
            executorService.execute(getRunnable("task4"));
            executorService.execute(getRunnable("task5"));
            executorService.shutdown();
        }

        try (ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10)) {
            scheduledExecutorService.execute(getRunnable("task1"));
            scheduledExecutorService.execute(getRunnable("task2"));
            scheduledExecutorService.schedule(getRunnable("task4"), 1000, TimeUnit.MILLISECONDS);
            scheduledExecutorService.schedule(getRunnable("task3"), 999, TimeUnit.MILLISECONDS);
            scheduledExecutorService.schedule(getRunnable("task5"), 1000, TimeUnit.MILLISECONDS);
            scheduledExecutorService.shutdown();
        }
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
