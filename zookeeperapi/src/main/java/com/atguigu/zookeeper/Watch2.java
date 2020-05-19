package com.atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class Watch2 {
    private static final String CONNECTSRING="127.0.0.1:2181";
    private static final String PATH="/root";
    private static final int TIMEOUT= 20 * 1000;

    private ZooKeeper zooKeeper;

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    /**
     * 获取 ZK 实例连接实例
     * @return
     * @throws IOException
     */
    public ZooKeeper startZK() throws IOException {
        return new ZooKeeper(CONNECTSRING, TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("连接监听");
            }
        });
    }

    /**
     * 关闭 ZK
     * @throws InterruptedException
     */
    public void stopZk() throws InterruptedException {
        if(this.zooKeeper!=null){
            this.zooKeeper.close();
        }
    }

    /**
     * 创建 ZNode 节点
     * @param path
     * @param data
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String createZNode(String path, String data) throws KeeperException, InterruptedException {
        // ZooDefs.Ids.OPEN_ACL_UNSAFE 代表无 ACL
        // CreateMode.PERSISTENT 表示非临时节点
        return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取数据
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getZNode(String path) throws KeeperException, InterruptedException {
        // 这里添加一个监听器
        // 第三个参数为 Stat，传入一个空对象即可
        byte[] data = this.zooKeeper.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                String newValue = null;
                try {
                    triggerValue(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        String oldValue = new String(data);
        System.out.println("old value: " + oldValue);
        return oldValue;
    }

    private String triggerValue(String path) throws KeeperException, InterruptedException {
        byte[] data = this.zooKeeper.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    // 再次调用自己，为 path 节点添加监听器
                    triggerValue(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        String newValue = new String(data);
        System.out.println("newValue: " + newValue);
        return newValue;
    }

    public static void main(String[] args) throws Exception {
        Watch2 watch1 = new Watch2();
        watch1.setZooKeeper(watch1.startZK());
        if(watch1.getZooKeeper().exists(PATH, false)==null){
            watch1.createZNode(PATH, "test data");
            String zNode = watch1.getZNode(PATH);
            System.out.println(zNode);
        }else{
            System.out.println("znode exists!!");
        }

        Thread.sleep(Integer.MAX_VALUE);
    }
}
