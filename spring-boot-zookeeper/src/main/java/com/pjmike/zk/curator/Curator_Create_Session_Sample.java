package com.pjmike.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @description:
 * @author: 13572
 * @create: 2019/04/29 15:59
 */
public class Curator_Create_Session_Sample {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString("39.106.63.214:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        String path = "/zk-book/c2";
        client.create().creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(path,"hello world".getBytes());
        byte[] data = client.getData().forPath(path);
        System.out.println(new String(data));

        Stat stat = new Stat();
        //读取一个节点的数据内容，同时获取到该节点的stat
        //curator通过传入一个旧的stat变量的方式来存储服务端返回的最新的节点状态信息
        client.getData().storingStatIn(stat).forPath(path);
        //删除节点，强制指定版本进行递归删除
        client.delete().deletingChildrenIfNeeded()
                .withVersion(stat.getVersion()).forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
