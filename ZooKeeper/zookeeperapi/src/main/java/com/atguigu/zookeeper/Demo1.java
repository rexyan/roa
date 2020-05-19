package com.atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.rmi.server.ExportException;

/**
 * ZK 基础使用
 */
public class Demo1 {
    private static final String CONNECTSRING="127.0.0.1:2181";
    private static final String PATH="/root";
    private static final int TIMEOUT= 20 * 1000;

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
     * @param zooKeeper
     * @throws InterruptedException
     */
    public void stopZk(ZooKeeper zooKeeper) throws InterruptedException {
        if(zooKeeper!=null){
            zooKeeper.close();
        }
    }

    /**
     * 创建 ZNode 节点
     * @param zooKeeper
     * @param path
     * @param data
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String createZNode(ZooKeeper zooKeeper, String path, String data) throws KeeperException, InterruptedException {
        // ZooDefs.Ids.OPEN_ACL_UNSAFE 代表无 ACL
        // CreateMode.PERSISTENT 表示非临时节点
       return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取数据
     * @param zooKeeper
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getZNode(ZooKeeper zooKeeper, String path) throws KeeperException, InterruptedException {
        // 这里没有 watch，所以 watch 为 false
        // 第三个参数为 Stat，传入一个空对象即可
        byte[] data = zooKeeper.getData(path, false, new Stat());
        return new String(data);
    }

    public static void main(String[] args) throws Exception {
        Demo1 demo1 = new Demo1();
        ZooKeeper zooKeeper = demo1.startZK();
        if (zooKeeper.exists(PATH, false)==null){
            demo1.createZNode(zooKeeper, PATH, "test data");
            String zNode = demo1.getZNode(zooKeeper, PATH);
            System.out.println(zNode);
        }else{
            System.out.println("znode exists!!");
        }
    }
}
