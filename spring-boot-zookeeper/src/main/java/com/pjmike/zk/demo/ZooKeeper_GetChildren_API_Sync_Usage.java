package com.pjmike.zk.demo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description: ZooKeeper API创建节点，使用同步(sync)接口
 * @author: 13572
 * @create: 2019/04/28 21:07
 */
public class ZooKeeper_GetChildren_API_Sync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("39.106.63.214:2181", 5000, new ZooKeeper_GetChildren_API_Sync_Usage());
        System.out.println("zookeeper state now is : " + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established");

        String path = "/zk-book";
        zooKeeper.create(path, "pjmike".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(path+"/c1", "pjmike".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        List<String> childrenList = zooKeeper.getChildren(path, true);
        System.out.println(childrenList);
        zooKeeper.create(path+"/c2", "pjmike".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Thread.sleep(5000);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: "+watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("ReGetChild: " + zooKeeper.getChildren(watchedEvent.getPath(), true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
