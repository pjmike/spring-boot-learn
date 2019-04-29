package com.pjmike.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 复用会话
 * @author: 13572
 * @create: 2019/04/28 20:52
 */
public class Zookeeper_with_reUsage_Session implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("39.106.63.214:2181", 5000, new Zookeeper_with_reUsage_Session());
        System.out.println("zookeeper state1 now is : " + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session is established");

        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

        //用错误的sessionId和密码企图复用Session
        zooKeeper = new ZooKeeper("39.106.63.214:2181", 5000, new Zookeeper_with_reUsage_Session()
                , 1L, "pjmike".getBytes());
        System.out.println("zookeeper state2 now is : " + zooKeeper.getState());
        //用正确的sessionId和密码复用session
        zooKeeper = new ZooKeeper("39.106.63.214:2181", 5000, new Zookeeper_with_reUsage_Session()
                , sessionId,passwd);
        System.out.println("zookeeper state3 now is : " + zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: "+watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
