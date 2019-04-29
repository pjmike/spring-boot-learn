package com.pjmike.zk.demo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @description: ZooKeeper API创建节点，使用同步(sync)接口
 * @author: 13572
 * @create: 2019/04/28 21:07
 */
public class ZooKeeper_Create_API_Sync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("39.106.63.214:2181", 5000, new ZooKeeper_Create_API_Sync_Usage());
        System.out.println("zookeeper state now is : " + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established");

        //创建临时顺序节点
        String path1 = zooKeeper.create("/zk_test", "hello world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create znode: " + path1);

        //创建临时节点
        String path2 = zooKeeper.create("/zk_test", "hello world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: " + path2);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: "+watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
