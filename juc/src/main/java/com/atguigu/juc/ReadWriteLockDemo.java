package com.atguigu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class MyCache{
    // volatile 表示这个对象是经常变化的
    private volatile Map<String, Object> map = new HashMap<>();

    // 设置值
    public void set(String key, Object value) {
        try{
            System.out.println("线程：" + Thread.currentThread().getName() + "正在写"+ key);
            // 暂停一会儿线程
            TimeUnit.MILLISECONDS.sleep(100);
            map.put(key, value);
            System.out.println("线程：" + Thread.currentThread().getName() + "写入"+ key+"完成");
        }catch(Exception e) {
            e.printStackTrace();
        }finally{

        }
    }

    // 获取值
    public Object get(String key){
        Object result = null;
        try{
            System.out.println("线程：" + Thread.currentThread().getName() + "正在获取"+ key + "的值");
            result = map.get(key);
            System.out.println("线程：" + Thread.currentThread().getName() + "正在获取"+ key + "的值结束");

        }catch(Exception e) {
            e.printStackTrace();
        }finally{

        }
        return result;
    }

}

public class ReadWriteLockDemo {
    // 五个线程进行写操作，五个线程进行读操作

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.set(String.valueOf(num), UUID.randomUUID().toString().replace("-", ""));
                }
            }, String.valueOf(num)).start();
        }

        TimeUnit.MILLISECONDS.sleep(100);

        for (int i = 5; i < 10; i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.get(String.valueOf(num));
                }
            }, String.valueOf(num)).start();
        }
    }

}
