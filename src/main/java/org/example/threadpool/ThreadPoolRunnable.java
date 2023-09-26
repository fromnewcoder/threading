package org.example.threadpool;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolRunnable implements Runnable{

    Thread thread = null;
    private boolean isStop;
    private final BlockingQueue<Runnable> blockingQueue;
    public ThreadPoolRunnable(BlockingQueue<Runnable> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped()){
            try {
                Runnable take = blockingQueue.take();
                take.run();;
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }

        }
    }

    private synchronized boolean isStopped() {
        return this.isStop;
    }

    public synchronized void doStop() {
        this.isStop = true;
        this.thread.interrupt();
    }

}
