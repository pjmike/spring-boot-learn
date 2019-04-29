package com.pjmike.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 使用Curator实现分布式锁
 * @author: 13572
 * @create: 2019/04/29 17:37
 */
public class Recipes_Lock {
    static String lock_path = "/curator_recipes_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory
            .builder()
            .connectString("39.106.63.214:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();

        final InterProcessMutex lock = new InterProcessMutex(client, lock_path);
        //初始化一个计数器为1，多个线程在执行任务之前首先countDownLatch.await()
        //当主线程执行CountDownLatch.countDown()时，计数器变为0，多个线程同时被唤醒
        final CountDownLatch downLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i <20 ; i++) {

            executorService.submit(() -> {
                try {
                    downLatch.await();
                    lock.acquire();
                } catch (Exception e) {
                }
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                String orderNo = sdf.format(new Date());
                System.out.println("生成的订单号为： " + orderNo);
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        downLatch.countDown();
        executorService.shutdown();
    }
}
