package com.pjmike.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 使用Curator的异步接口
 * @author: 13572
 * @create: 2019/04/29 17:05
 */
public class Curator_Create_Node_Background_Sample {
    static String path = "/zk-pj";
    static CuratorFramework client = CuratorFrameworkFactory
            .builder()
            .connectString("39.106.63.214")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    static CountDownLatch latch = new CountDownLatch(2);
    static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws Exception {
        client.start();

        System.out.println("Main thread: " + Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground((curatorFramework, curatorEvent) -> {
                    System.out.println("event[code: " + curatorEvent.getResultCode() + ", type: " + curatorEvent.getType() + "]");
                    System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                    latch.countDown();
                }, executorService).forPath(path, "hello world".getBytes());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground((curatorFramework, curatorEvent) -> {
                    System.out.println("event[code: " + curatorEvent.getResultCode() + ", type: " + curatorEvent.getType() + "]");
                    System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                    latch.countDown();
                }).forPath(path, "hello world".getBytes());
        latch.await();
        executorService.shutdown();
        Thread.sleep(5000);
    }
}
