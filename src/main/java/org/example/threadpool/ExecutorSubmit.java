package org.example.threadpool;

import java.util.concurrent.*;

public class ExecutorSubmit {
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
            Future task1 = executorService.submit(getCallable("task1"));
            System.out.println(task1.get().toString() + "abc");
            executorService.submit(getCallable("task2"));
            executorService.submit(getCallable("task3"));
            executorService.submit(getCallable("task4"));
            executorService.submit(getCallable("task5"));
            executorService.shutdown();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try (ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10)) {
            scheduledExecutorService.submit(getCallable("task1"));
            scheduledExecutorService.submit(getCallable("task2"));
            scheduledExecutorService.shutdown();
        }
    }
    public static Callable getCallable(String task){
        return new Callable() {

            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " : " + task);
                return Thread.currentThread().getName() + " : " + task;
            }
        };
    }
}
