package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class UseRunable implements Runnable{

    @Override
    public void run() {

    }
}

class UseCallable implements  Callable{

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName());
        return 200;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new UseRunable(), "Runable 方式实现的线程").start();

        FutureTask futureTask = new FutureTask<Integer>(new UseCallable());
        new Thread(futureTask, "CallableD 方式实现的线程 - A").start();
        new Thread(futureTask, "CallableD 方式实现的线程 - B").start();  // "CallableD 方式实现的线程 - B" 不会被执行
        while (!futureTask.isDone()){
            System.out.println("运算未完成!");
        }
        System.out.println(futureTask.get());  // 取值放在最后，不然 main 主进程会被阻塞。
    }
}
