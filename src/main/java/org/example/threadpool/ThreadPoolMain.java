package org.example.threadpool;

public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(4);

        for(int i=0; i<1000; i++){
            int finalI = i;
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName() + " running task " + finalI);
            });
        }
        threadPool.waitUntilFinished();
        threadPool.stop();

    }
}