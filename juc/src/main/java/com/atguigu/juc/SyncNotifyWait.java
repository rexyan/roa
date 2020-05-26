package com.atguigu.juc;

/**
 * 四个线程 ABCD。两个线程进行 + 1。另外个线程进行 -1
 * 要求 +1 和 -1 交替进行。每个线程执行 10 次
 */
public class SyncNotifyWait {
    private int number = 0;//初始值为零的一个变量

    public synchronized void increment() throws InterruptedException {
        // 1判断
        // 这里使用 while 判断而不使用 if 的原因是因为 if 只判断一次
        // 如果使用 if 判断，线程被唤醒的时候不在进行判断，那么就有可能会绕过条件
        // 使用 while 的目的就是让线程每次被唤醒的时候，每次都进行判断
        while (number !=0 ) {
            this.wait();
        }
         //2干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
         //3通知
        this.notifyAll();
    }

     public synchronized void decrement() throws InterruptedException {
         // 1判断
         // 这里使用 while 判断而不使用 if 的原因是因为 if 只判断一次
         // 如果使用 if 判断，线程被唤醒的时候不在进行判断，那么就有可能会绕过条件
         // 使用 while 的目的就是让线程每次被唤醒的时候，每次都进行判断
        while (number == 0) {
            this.wait();
        }
         // 2干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
         // 3通知
        this.notifyAll();
    }

    public static void main(String[] args) {
        SyncNotifyWait syncNotifyWait = new SyncNotifyWait();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程C").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程D").start();
    }
}
