package com.pjmike.zk.demo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @description: Zookeeper API创建节点，使用异步(async)接口
 * @author: 13572
 * @create: 2019/04/28 21:37
 */
public class ZooKeeper_Create_API_ASync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("39.106.63.214:2181", 5000, new ZooKeeper_Create_API_ASync_Usage());
        System.out.println("zookeeper state now is : " + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established");

        //异步创建临时节点
        zooKeeper.create("/zk-test-1", "hello world".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "I am context.");
        Thread.sleep(5000);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: "+watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }

    static class IStringCallback implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("Create path result: ["+rc+","+path+","+ctx+", real path name："+name);
        }
    }
}
