package com.atguigu.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 创建一个线程池，并指定大小为 3
        // ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 创建一个线程池，里面就只有一个线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 创建一个线程池，里面的线程自动扩充
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try{
            // 将 10 个或者更多的任务交给线程池中的线程执行
            for (int i = 0; i < 30; i++) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + "执行了任务！");
                    }
                });
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }
}
