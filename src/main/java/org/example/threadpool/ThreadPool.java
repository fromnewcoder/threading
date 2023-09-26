package org.example.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    BlockingQueue<Runnable> blockingQueue = null;
    List<ThreadPoolRunnable> threadPoolRunnableList = new ArrayList<>();

    private boolean isStop = false;

    public ThreadPool(int noOfThread) {
        blockingQueue = new LinkedBlockingQueue<>();
        for(int i = 0; i< noOfThread;i++){
            ThreadPoolRunnable threadPoolRunnable = new ThreadPoolRunnable(blockingQueue);
            Thread thread1 = new Thread(threadPoolRunnable,"tread" + i);
            thread1.start();
            threadPoolRunnableList.add(threadPoolRunnable);
        }
    }

    public synchronized void execute(Runnable runnable){
        if(isStop){
            throw new IllegalStateException("thread is stopped");
        }
        blockingQueue.offer(runnable);
    }

    public synchronized void stop(){
        this.isStop = true;
        for(ThreadPoolRunnable runnable : threadPoolRunnableList){
            runnable.doStop();
        }
    }
    public synchronized void waitUntilFinished() throws InterruptedException {
        while (blockingQueue.size()>0){
            Thread.sleep(1);
        }
    }
}
